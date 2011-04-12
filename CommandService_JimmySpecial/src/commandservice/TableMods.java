package commandservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableMods {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		// TODO Auto-generated method stub
		DropCreateTables();
		RequestsMod();
		PositionMod();
	}

//CONNECTION FUNCTIONS ============================================================================
	public static Connection getConnection(String dbURL, String user, String pass) throws SQLException, ClassNotFoundException 	//creates connection
	{

			return DriverManager.getConnection(dbURL, user, pass);

	}
	public static void DisconnectIt(Connection conn) throws SQLException
	{
	    //close connection
		if(!conn.isClosed()) { conn.close(); 
		System.out.println("[+]Disconnected.");
		}else { System.out.println("[-]Disconnected.");}
	}
//END CONNECTION FUNCTIONS 
//TABLE MODs ======================================================================================
	public static void DropCreateTables () throws SQLException, ClassNotFoundException
	{
		Connection conn = getConnection("jdbc:mysql://127.0.0.1/qarsp_db", "root", "root");
		Statement st = conn.createStatement();		//use this to write any sql statements
		
		st.execute("DROP TABLE orders_db");
		//st.execute("DROP TABLE device_loc");
		
		st.execute("CREATE TABLE `orders_db` ( `ID` INT(11)  NOT NULL AUTO_INCREMENT COMMENT 'unique id', `lat` FLOAT(10,7) DEFAULT '0.000' NOT NULL COMMENT 'coordinate', `lng` FLOAT(10,7) DEFAULT '0.000' NOT NULL COMMENT 'coordinate', `status` INT(11), `priority` INT(11), `owner_ID` INT(11), PRIMARY KEY (`ID`))");
		//st.execute("CREATE TABLE `device_loc` ( `ID` INT(11)  NOT NULL AUTO_INCREMENT, `type` INT(11)  NOT NULL, `lat` FLOAT(10,7)  NOT NULL, `lng` FLOAT(10,7)  NOT NULL, `heading` FLOAT  NOT NULL, `status_code` INT(11)  NOT NULL, PRIMARY KEY (`ID`) )");

		System.out.println("system re-up'ed");
	}
	public static void RequestsMod() throws SQLException, ClassNotFoundException
	{
		//Connects to DB
		Connection conn = getConnection("jdbc:mysql://127.0.0.1/qarsp_db", "root", "root");
		Statement st = conn.createStatement();		//use this to write any sql statements

		for(int j = 0; j < 25; j++)
		{
			//order status levels -1: availible, >1: order being serviced by q#
			//priority levels 0: unimportant, 1: pay attention, 2: urgent
			//insert or append a row				 (    ID,          lat,                    long,                    status,   priority,                           ownerID)
			st.executeUpdate("INSERT orders_db VALUES(" + null + "," + (32.6 + Math.random()*0.01) + "," + (-85.5 + Math.random()*.01) + "," + -1 + "," + Math.floor(Math.random()*3) + "," + Math.floor(Math.random()*10) +")");
		}
		
		System.out.println("orders_db Table: 10 rows appended");
		
		DisconnectIt(conn);
	}
	public static void PositionMod() throws SQLException, ClassNotFoundException
	{
		Connection conn = getConnection("jdbc:mysql://127.0.0.1/qarsp_db", "root", "root");
		Statement st = conn.createStatement();		//use this to write any sql statements

		for(int j = 0; j < 1; j++)
		{
			//qstatus levels -1: availible, >1: serving order#
			//insert or append a row				  (    ID,          type,                               lat,                     long,                    heading,                  status_code)
			//st.executeUpdate("INSERT device_loc VALUES(" + null + "," + Math.floor(Math.random()*4) + "," + (32.6 + Math.random()*0.01) + "," + (-85.5 + Math.random()*.01) + "," + Math.random()*360 + "," + -1 + ")");
		}
		System.out.println("device_loc Table: 0 rows appended");
		
		DisconnectIt(conn);
	}
//END TABLE MODs
	

}
