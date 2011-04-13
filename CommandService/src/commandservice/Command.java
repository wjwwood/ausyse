package commandservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Command {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
    public static boolean on = false;
    public static  int Ctype = 1;
    public static String url;
    public static String uname;
    public static String passw;
//MAIN PROGRAM EXECUTION ==========================================================================	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

            url = args[0];
            uname = args[1];
            passw = args[2];

		//boolean on = true;
		while (on == true)
		{
			//put a thread for the position setting


			ControlModQPos(Ctype);
			try {
                            System.out.println("Command Service Running");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//put a thread for the q to order point checking
	}
//END MAIN PROGRAM EXECUTION
//CONNECTION FUNCTIONS ============================================================================
	public static Connection getConnection(String dbURL, String user, String pass) throws SQLException, ClassNotFoundException 	//creates connection
	{
            dbURL = "jdbc:mysql://" + url + "/qarsp_db";
            user = uname;
            pass = passw;
            
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("[+]Connected.");
			return DriverManager.getConnection(dbURL, user, pass);
		} catch (Exception e) {
			System.out.println("[-]Connected.");
			e.printStackTrace();
			return null;
		}
	}
	public static void DisconnectIt(Connection conn) throws SQLException
	{
	    //close connection
		if(!conn.isClosed()) { conn.close(); 
		System.out.println("[+]Disconnected.");
		}else { System.out.println("[-]Disconnected.");}
	}
//END CONNECTION FUNCTIONS 
//COMMAND =========================================================================================
	public static void ControlModQPos(int CommandRule) throws SQLException, ClassNotFoundException
	{
		//Connects --------------------------------------------------------------------------------
		Connection conn = getConnection("jdbc:mysql://127.0.0.1/qarsp_db", "root", "root");
		Statement stmt = conn.createStatement();
	
		//Selects all unanswered orders (status = -1) ---------------------------------------------
		String oSQL = "SELECT * FROM orders_db WHERE status = -1";
		String oResult = "ID\tlat\t\tlng\t\tstat\tprior\towner_ID\n";
		
		Vector<Integer> t_IDo = new Vector<Integer>();		Vector<Float> t_lat = new Vector<Float>();
		Vector<Float> t_lng = new Vector<Float>();			Vector<Integer> t_stt = new Vector<Integer>();	
		Vector<Integer> t_pri = new Vector<Integer>();		Vector<Integer> t_own = new Vector<Integer>();
		
		try{
			ResultSet rso = stmt.executeQuery(oSQL);
			
			int j = 0; 
			while(rso.next())
			{
				t_IDo.add(rso.getInt("ID"));						t_lat.add(rso.getFloat("lat"));			
				t_lng.add(rso.getFloat("lng"));					t_stt.add(rso.getInt("status"));
				t_pri.add(rso.getInt("priority"));					t_own.add(rso.getInt("owner_ID"));
				
				oResult += t_IDo.get(j)+"\t"+t_lat.get(j)+"\t"+t_lng.get(j)+"\t"+t_stt.get(j)+"\t"+t_pri.get(j)+"\t"+t_own.get(j)+"\n";
				
				j++;
			}
			
			System.out.println("\norders_db where (status = -1) Result Table:"); System.out.println(oResult);
			System.out.println("[+]orders_db Selected.");
		}
		catch(Exception e){
			System.out.println("[-]orders_db Selected.");
			e.printStackTrace();
		}
		
		//Selects all QARSPs ----------------------------------------------------------------------
		String qSQL = "SELECT * FROM device_loc WHERE status_code = -1";
		String qResult = "ID\ttype\tlat\t\tlng\t\theading\t\tstatus_code\n";
		
		Vector<Integer> t_IDq = new Vector<Integer>();		Vector<Integer> t_typ = new Vector<Integer>();
		Vector<Float> t_ltq = new Vector<Float>();			Vector<Float> t_lgq = new Vector<Float>();
		Vector<Float> t_hed = new Vector<Float>();			Vector<Integer> t_stc = new Vector<Integer>();
		
		try{
			ResultSet rsq = stmt.executeQuery(qSQL);
			
			int j = 0; 
			while(rsq.next())
			{
				t_IDq.add(rsq.getInt("ID"));				t_typ.add(rsq.getInt("type"));
				t_ltq.add(rsq.getFloat("lat"));				t_lgq.add(rsq.getFloat("lng"));
				t_hed.add(rsq.getFloat("heading"));			t_stc.add(rsq.getInt("status_code"));
				
				qResult += t_IDq.get(j) + "\t" + t_typ.get(j) +"\t"+ t_ltq.get(j) +"\t"+ t_lgq.get(j) +"\t"+ t_hed.get(j) + "\t\t" + t_stc.get(j) + "\n";
				
				j++;
			}
			
			System.out.println("\ndevice_loc where (status_code = -1) Results Table ."); System.out.println(qResult);
			System.out.println("[+]device_loc Selected.\n");
		}
		catch (Exception e)
		{
			System.out.println("[-]device_loc Selected.\n");
			e.printStackTrace();
		}
		
		//Positioning Logic -----------------------------------------------------------------------
		if (CommandRule == 0){
                FIFO(stmt, t_IDo, t_lat, t_lng, t_stt, t_pri, t_own, t_IDq, t_typ, t_ltq, t_lgq, t_hed, t_stc);
                } else if (CommandRule == 1) {
		LIFO(stmt, t_IDo, t_lat, t_lng, t_stt, t_pri, t_own, t_IDq, t_typ, t_ltq, t_lgq, t_hed, t_stc);
                } else if (CommandRule == 2){
		Closest(stmt ,t_IDo, t_lat, t_lng, t_stt, t_pri, t_own, t_IDq, t_typ, t_ltq, t_lgq, t_hed, t_stc);
                }
		//VRP(stmt ,t_IDo, t_lat, t_lng, t_stt, t_pri, t_own, t_IDq, t_typ, t_ltq, t_lgq, t_hed, t_stc);
		
		//Disconnect ------------------------------------------------------------------------------
		DisconnectIt(conn);
	}
//END COMMAND
//LOGICS ==========================================================================================
	public static void FIFO(Statement st,
		Vector<Integer> oID, Vector<Float> olat, Vector<Float> olng, Vector<Integer> ostt, Vector<Integer> opri, Vector<Integer> oown, 
		Vector<Integer> qID, Vector<Integer> qtyp, Vector<Float> qlat, Vector<Float> qlng, Vector<Float> qhed, Vector<Integer> qstc) throws SQLException
	{																							//makes no distinction for what qarsp can fulfill what request
		String oRes =  "ID\t\tstat\n";
		String qRes =  "ID\tstatus_code\n";
		
		int o = 0; 																			
		//index for issued orders
		for(int q = 0; q < qID.size(); q++)														//for each availible qarsp
		{
			//set both to busy
			if(o < oID.size())
			{
				st.execute("UPDATE orders_db SET status='" + qID.get(q).toString() + "' WHERE  ID = '" + oID.get(o).toString() + "'");
				st.execute("UPDATE device_loc SET status_code='" + oID.get(o).toString() +"' WHERE ID = '" + qID.get(q).toString() +"'");

				
				oRes += oID.get(o).toString() + "\t" + qID.get(q).toString() + "\n";
				qRes += qID.get(q).toString() + "\t" + qID.get(q).toString() + "\n";
				
				o++;
			}
		}
		
		System.out.print("Modified Orders mini-table\n"); System.out.println(oRes);
		System.out.print("Modified QAESPs mini-table\n"); System.out.println(qRes);
	}
	public static void LIFO(Statement st,
		Vector<Integer> oID, Vector<Float> olat, Vector<Float> olng, Vector<Integer> ostt, Vector<Integer> opri, Vector<Integer> oown, 
		Vector<Integer> qID, Vector<Integer> qtyp, Vector<Float> qlat, Vector<Float> qlng, Vector<Float> qhed, Vector<Integer> qstc) throws SQLException
	{
		String oRes =  "ID\tstat\n";
		String qRes =  "ID\tstatus_code\n";
		
		int o = oID.size()-1; 																			
		//index for issued orders
		for(int q = 0 ; q < qID.size(); q++)														//for each availible qarsp
		{
			//set both to busy
			if(o < oID.size())
			{
				st.execute("UPDATE orders_db SET status='" + qID.get(q).toString() + "' WHERE  ID = '" + oID.get(o).toString() + "'");
				st.execute("UPDATE device_loc SET status_code='" + oID.get(o).toString() +"' WHERE ID = '" + qID.get(q).toString() +"'");

				
				oRes += oID.get(o).toString() + "\t" + qID.get(q).toString() + "\n";
				qRes += qID.get(q).toString() + "\t" + oID.get(o).toString() + "\n";
				
				o--;
			}
		}
		
		System.out.print("Modified Orders mini-table\n"); System.out.println(oRes);
		System.out.print("Modified QAESPs mini-table\n"); System.out.println(qRes);
	}
	public static void Closest(Statement st, 
		Vector<Integer> oID, Vector<Float> olat, Vector<Float> olng, Vector<Integer> ostt, Vector<Integer> opri, Vector<Integer> oown, 
		Vector<Integer> qID, Vector<Integer> qtyp, Vector<Float> qlat, Vector<Float> qlng, Vector<Float> qhed, Vector<Integer> qstc) throws SQLException
	{
		String oRes =  "ID\t\tstat\n";
		String qRes =  "ID\tstatus_code\n";
		
		while(0 < oID.size() && 0 < qID.size())
		{
			//Make the matrix	
			Vector<Vector<Float>> FROMorderTOqarsp = new Vector<Vector<Float>>();	FROMorderTOqarsp = FromToMatrix(olat, olng, qlat, qlng);
			
			//find min form-to distance
			Vector<Float> FTmin = new Vector<Float>();	FTmin = FindMatrixMin(FROMorderTOqarsp);
			
			//Set pairing
			st.execute("UPDATE orders_db SET status = '" + FTmin.get(1).toString() + "' WHERE ID = '" + FTmin.get(0).toString() + "'");
			st.execute("UPDATE device_loc SET status_code = '" + FTmin.get(0).toString() + "' WHERE ID = '" + FTmin.get(1).toString() + "'");
			
			oRes += FTmin.get(1).toString() + "\t" + FTmin.get(0).toString() + "\n";
			qRes += FTmin.get(0).toString() + "\t" + FTmin.get(1).toString() + "\n";
			
			
			//remove paired			
			oID.removeElementAt((int) Math.floor(FTmin.get(0))); olat.removeElementAt((int) Math.floor(FTmin.get(0))); olng.removeElementAt((int) Math.floor(FTmin.get(0))); ostt.removeElementAt((int) Math.floor(FTmin.get(0))); opri.removeElementAt((int) Math.floor(FTmin.get(0))); oown.removeElementAt((int) Math.floor(FTmin.get(0))); 
			qID.removeElementAt((int) Math.floor(FTmin.get(1))); qtyp.removeElementAt((int) Math.floor(FTmin.get(1))); qlat.removeElementAt((int) Math.floor(FTmin.get(1))); qlng.removeElementAt((int) Math.floor(FTmin.get(1))); qhed.removeElementAt((int) Math.floor(FTmin.get(1))); 
		}
				
		System.out.print("Modified Orders mini-table\n"); System.out.println(oRes);
		System.out.print("Modified QAESPs mini-table\n"); System.out.println(qRes);
	}
	public static void VRP(Statement st, 
			Vector<Integer> oID, Vector<Float> olat, Vector<Float> olng, Vector<Integer> ostt, Vector<Integer> opri, Vector<Integer> oown, 
			Vector<Integer> qID, Vector<Integer> qtyp, Vector<Float> qlat, Vector<Float> qlng, Vector<Float> qhed, Vector<Integer> qstc) throws SQLException
		{
			//encoding
			Vector<Vector<Integer>> qRoute = new Vector<Vector<Integer>>();
			float distance = 0;
				
			//Vector<Vector<Integer>> qBest = new Vector<Vector<Integer>>();
			//float bestDist = 0;
				
			for(int q = 0; q < qID.size(); q++)
			{
				Vector<Integer> route = new Vector<Integer>();
				qRoute.add(route);
			}
				
			Vector<Integer> coID = new Vector<Integer>(); //coID = (Vector<Integer>)oID.clone();
				
			//randomly assign orders to each availible qarsp
			for(int o = 0; o < oID.size(); o++)
			{
				
				for(int q = 0; q < qID.size(); q++)
				{
					if(0 < coID.size())
					{
						int oindex = (int)Math.floor(Math.random()*coID.size());	//get index
						qRoute.get(q).add(coID.get(oindex));						//add to route
						coID.remove(oindex); o++;									//remove from availible 
					}
				}
			}
			
			/*output for debug
			for(int q = 0; q < qID.size(); q++)
			{
				System.out.print("[ ");
				for(int o = 0; o < qRoute.get(q).size(); o++)
				{
					System.out.print(qRoute.get(q).get(o) + " ");
				}	
				System.out.println("]");
			}
			*/
			
			
			//caclulate ofv = sum of distances
			for(int q = 0; q < qRoute.size(); q++)
			{
				//--from qarsp current position to first order
				distance += Distance(qlat.get(q), qlng.get(q), olat.get(oID.indexOf(qRoute.get(q).get(0))), olng.get(oID.indexOf(qRoute.get(q).get(0)))   ); System.out.println("Distance: " + distance);
				
				//--from first to each additional order
				for(int o = 0; o < qRoute.get(q).size(); o++)
				{
					if(o+1 < (qRoute.get(q).size()-1))
					{
						
						distance += Distance(
								olat.get(oID.indexOf(qRoute.get(q).get(o))), 
								olng.get(oID.indexOf(qRoute.get(q).get(o))), 
								olat.get(oID.indexOf(qRoute.get(q).get(o+1))), 
								olng.get(oID.indexOf(qRoute.get(q).get(o+1)))); 
								
						/* Output
						System.out.println(
							"qRoute.get(q).get(o): " + qRoute.get(q).get(o) + 
							"\tqRoute.get(q).get(o+1): " + qRoute.get(q).get(o+1) +
							"\tDistance: " + distance);
						*/
					}
				}
				
			}
				
			//set best
				
			
			//petrub (Random or define a neighborhood)
		
			//iterate
			
			//use best solution
			
			}

//END LOGICS
//HELPER FUNCTIONS ================================================================================
	public static Vector<Vector<Float>> FromToMatrix(Vector<Float> ox, Vector<Float> oy, Vector<Float> qx, Vector<Float> qy)
	{
		Vector<Vector<Float>> FromTo = new Vector<Vector<Float>>();
		
		for(int o = 0; o < ox.size(); o++)
		{
			Vector<Float> From = new Vector<Float>();
			
			for(int q = 0; q < qx.size(); q++)
			{
				//From.add((float)Math.sqrt(Math.pow(ox.get(o) - qx.get(q), 2) + Math.pow(oy.get(o) - qy.get(q),2)));
				From.add(Distance(ox.get(o),oy.get(o), qx.get(q), qy.get(q)));
			}
			FromTo.add(From);
		}
		return FromTo;
	}
	public static Vector<Float> FindMatrixMin(Vector<Vector<Float>> FT)
	{
		Vector<Float> minv = new Vector<Float>();
		
		minv.add(-1f); minv.add(-1f); minv.add(999999.99f);
		
		//System.out.println("From(order) - To(device) Table");					used to write the entire table
		
		for (int o = 0; o < FT.size(); o++)
		{
			for(int q = 0; q < FT.get(0).size(); q++)
			{
				if(FT.get(o).get(q) < minv.get(2)) { minv.set(0, (float)o); minv.set(1, (float)q); minv.set(2, (float)FT.get(o).get(q)); }		//if this value is smaller, keep it and its indices
				
				//System.out.print("(" + FT.get(o).get(q) + ")\t");							//writes the entire table
			}	
			//System.out.print("\n");															//used to write the entire table
		}
		
		System.out.println("Min distance pair : (" + minv.get(0) + ", " + minv.get(1) + ") " + minv.get(2) + "\n");
		
		return minv;
	}
	public static float Distance(float x1, float y1, float x2, float y2)
	{
		return (float)Math.sqrt(Math.pow( x1 - x2, 2) + Math.pow( y1 - y2, 2));
	
	}
//END HELPER FUNCTIONS
}
