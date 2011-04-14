/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CommandUI3.java
 *
 * Created on Apr 13, 2011, 10:55:30 PM
 */

package commandserviceapplet3;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaret
 */
public class CommandUI3 extends javax.swing.JFrame {

    /** Creates new form CommandUI3 */
    public CommandUI3() {
        initComponents();
                try {
            txtORDERS.setText(GetORDERinfo());
            txtQARSP.setText(GetQARSPinfo());
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboCommandType = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        txtUname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtURL = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtORDERS = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtQARSP = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        mStartService = new javax.swing.JMenu();
        mStopService = new javax.swing.JMenu();
        mReset = new javax.swing.JMenu();
        smAddOrde = new javax.swing.JMenuItem();
        smAddQARSP = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        smClearOrdersT = new javax.swing.JMenuItem();
        smClearQARSPT = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        smClearRand = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cboCommandType.setEditable(true);
        cboCommandType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FIFO", "LIFO", "Closest" }));
        cboCommandType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCommandTypeActionPerformed(evt);
            }
        });

        jLabel4.setText("Scheduling Rule");

        txtPass.setText("root");

        txtUname.setText("root");

        jLabel1.setText("URL");

        jLabel2.setText("Uname");

        jLabel3.setText("Pass");

        txtURL.setText("127.0.0.1");

        txtORDERS.setColumns(20);
        txtORDERS.setRows(5);
        jScrollPane1.setViewportView(txtORDERS);

        txtQARSP.setColumns(20);
        txtQARSP.setRows(5);
        jScrollPane2.setViewportView(txtQARSP);

        mStartService.setText("Start Service");
        mStartService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mStartServiceMouseClicked(evt);
            }
        });
        jMenuBar1.add(mStartService);

        mStopService.setText("Stop Service");
        mStopService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mStopServiceMouseClicked(evt);
            }
        });
        jMenuBar1.add(mStopService);

        mReset.setText("Manipulate");
        mReset.setPreferredSize(new java.awt.Dimension(88, 21));

        smAddOrde.setText("Add Order");
        smAddOrde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                smAddOrdeMousePressed(evt);
            }
        });
        mReset.add(smAddOrde);

        smAddQARSP.setText("Add Device");
        smAddQARSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                smAddQARSPMousePressed(evt);
            }
        });
        mReset.add(smAddQARSP);
        mReset.add(jSeparator2);

        smClearOrdersT.setText("Clear orders_db Table");
        smClearOrdersT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                smClearOrdersTMousePressed(evt);
            }
        });
        mReset.add(smClearOrdersT);

        smClearQARSPT.setText("Clear device_loc Table");
        smClearQARSPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                smClearQARSPTMousePressed(evt);
            }
        });
        mReset.add(smClearQARSPT);
        mReset.add(jSeparator5);
        mReset.add(jSeparator4);

        smClearRand.setText("Clear2Random");
        smClearRand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                smClearRandMousePressed(evt);
            }
        });
        mReset.add(smClearRand);
        mReset.add(jSeparator3);

        jMenuBar1.add(mReset);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCommandType, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtUname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cboCommandType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//Menu code ==================================================================================================================
    
    private void mGetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mGetMouseClicked
        try {
            txtORDERS.setText(GetORDERinfo());
            txtQARSP.setText(GetQARSPinfo());
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}//GEN-LAST:event_mGetMouseClicked

    private void mStartServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mStartServiceMouseClicked
        if (ServiceMade == false) {
            Thread thr1 = new Thread(rStart); thr1.start();
            ServiceMade = true;
            On = true;
        } else {
            On = true;
        }
        
        System.out.println("Service Started");
}//GEN-LAST:event_mStartServiceMouseClicked

    private void mStopServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mStopServiceMouseClicked
        // TODO add your handling code here:
        On = false;
        System.out.println("Service Stopped");
}//GEN-LAST:event_mStopServiceMouseClicked

    private void cboCommandTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCommandTypeActionPerformed
        //Ctype = cboCommandType.getSelectedIndex();
        //TODO check if there needs to be a variable for CommandType
}//GEN-LAST:event_cboCommandTypeActionPerformed

    private void smAddOrdeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_smAddOrdeMousePressed
        try {
            //Connect
            Connection conn = null;
            conn = getConnection();
            Statement st = null;
            st = conn.createStatement();
            //Add
            st.executeUpdate("INSERT orders_db VALUES(" + null + "," + (32.6 + Math.random() * 0.01) + "," + (-85.5 + Math.random() * .01) + "," + -1 + "," + Math.floor(Math.random() * 3) + "," + Math.floor(Math.random() * 10) + ")");
            //Display
            txtORDERS.setText(GetORDERinfo());
            txtQARSP.setText(GetQARSPinfo());
            //Disconnect
            DisconnectIt(conn);
            System.out.println("[+]Random Order Added.");
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_smAddOrdeMousePressed

    private void smAddQARSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_smAddQARSPMousePressed
        try {
            //Connect
            Connection conn = null;
            conn = getConnection();
            Statement st = null;
            st = conn.createStatement();
            //Add
            st.executeUpdate("INSERT device_loc VALUES(" + null + "," + (32.6 + Math.random() * 0.01) + "," + (-85.5 + Math.random() * .01) + "," + -1 + "," + Math.floor(Math.random() * 3) + "," + Math.floor(Math.random() * 10) + ")");
            //Display
            txtORDERS.setText(GetORDERinfo());
            txtQARSP.setText(GetQARSPinfo());
            //Disconnect
            DisconnectIt(conn);
            System.out.println("[+]Random QARSP Added.");
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_smAddQARSPMousePressed

    private void smClearOrdersTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_smClearOrdersTMousePressed
        try {
            Connection conn = getConnection();
            Statement st;
            st = conn.createStatement();
            st.executeUpdate("DELETE FROM orders_db WHERE 0 < ID");
            //Display
            txtORDERS.setText(GetORDERinfo());
            txtQARSP.setText(GetQARSPinfo());
            //Disconnect
            DisconnectIt(conn);
            System.out.println("[+]orders_db Table Cleared");
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_smClearOrdersTMousePressed

    private void smClearQARSPTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_smClearQARSPTMousePressed
        try {
            Connection conn = getConnection();
            Statement st;
            st = conn.createStatement();
            st.executeUpdate("DELETE FROM device_loc WHERE 0 < ID");
            //Display
            txtORDERS.setText(GetORDERinfo());
            txtQARSP.setText(GetQARSPinfo());
            //Disconnect
            DisconnectIt(conn);
            System.out.println("[+]device_loc Table Cleared");
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_smClearQARSPTMousePressed

    private void smClearRandMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_smClearRandMousePressed
        try {
            MakeRandomData();
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_smClearRandMousePressed
//END Menu Code ==============================================================================================================
    /**
    * @param args the command line arguments ========================================================================
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CommandUI3().setVisible(true);
            }
        });
    }
//END Main ===================================================================================================================
//Scheduling Functions =======================================================================================================
    public Boolean ServiceMade = false;
    public Boolean On = false;
    
    Runnable rStart = new Runnable() {
        public void run() {
            //main service loop
            
            //boolean on = true;
            while (On == true)
            {
                //Do Work
                try {
                    ControlModQPos(cboCommandType.getSelectedIndex());
                    System.out.println("Command Service Running");
                } catch (SQLException ex) {
                    Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Display
                try {
                    txtORDERS.setText(GetORDERinfo());
                    txtQARSP.setText(GetQARSPinfo());
                } catch (SQLException ex) {
                    Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Wait for a few seconds
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CommandUI3.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
        }
    };
    
    public String GetQARSPinfo() throws SQLException, ClassNotFoundException
    {
            Connection conn = getConnection();
            Statement stmt = (Statement) conn.createStatement();

            //Selects all QARSPs
            String rSQL = "SELECT * FROM device_loc";
            String rResult = "DEVICE_LOC\nID\tType\tX\tY\tHeading\tStatus\n";

            try{
                    ResultSet rs = stmt.executeQuery(rSQL);

                    while(rs.next())
                    {
                            rResult +=rs.getInt("ID") + "\t" + rs.getInt("type") + "\t" + rs.getFloat("lat") + "\t" + rs.getFloat("lng") + "\t" + rs.getFloat("heading") + "\t" + rs.getInt("status_code") + "\n";
                    }

                    System.out.println("[+]Requests Selected.");
            }
            catch(Exception e){
                    System.out.println("[-]Requests Selected.");
                    e.printStackTrace();;
            }

            DisconnectIt(conn);

            return rResult;
    }
    public  String GetORDERinfo() throws SQLException, ClassNotFoundException
    {
            Connection conn = getConnection();
            
            Statement stmt =  conn.createStatement();

            //Selects all Orders
            String qSQL = "SELECT * FROM orders_db";
            String qResult = "ODERS_DB\nID\tlat\tlng\tStatus\tPriority\tOwner_ID\n";

            try
            {
                    ResultSet rs = stmt.executeQuery(qSQL);

                    while(rs.next())
                    {
                            qResult += rs.getInt("ID") + "\t" + rs.getFloat("lat") + "\t" + rs.getFloat("lng") + "\t" + rs.getInt("status") + "\t" + rs.getInt("priority") + "\t" + rs.getInt("owner_ID") + "\n";
                    }

                    System.out.println("[+]Positions Selected.");
            }
            catch (Exception e)
            {
                    System.out.println("[-]Positions Selected.");
            }

            DisconnectIt(conn);

            return qResult;
    }
    public void MakeRandomData() throws SQLException, ClassNotFoundException
    {
        Connection conn  = getConnection();
        Statement st; st = conn.createStatement();
        
        //REMOVE
        st.executeUpdate("DELETE FROM orders_db WHERE 0 < ID");
        st.executeUpdate("DELETE FROM device_loc WHERE 0 < ID");

        //ADD RANDOM
        for(int j = 12; j < 25; j++)
        {
            //order status levels -1: availible, >1: order being serviced by q#
            //priority levels 0: unimportant, 1: pay attention, 2: urgent
            //insert or append a row                 (    ID,          lat,                                long,                               status,    priority,                           ownerID)
            st.executeUpdate("INSERT orders_db VALUES(" + null + "," + (32.6 + Math.random()*0.01) + "," + (-85.5 + Math.random()*.01) + "," + -1 + "," + Math.floor(Math.random()*3) + "," + Math.floor(Math.random()*10) +")");
        }
        System.out.println("orders_db Table: 10 rows appended");

        for(int j = 0; j < 17; j++)
        {
            //qstatus levels -1: availible, >1: serving order#
            //insert or append a row		      (    ID,          type,                               lat,                                long,                               heading,                  status_code)
            st.executeUpdate("INSERT device_loc VALUES(" + null + "," + Math.floor(Math.random()*4) + "," + (32.6 + Math.random()*0.01) + "," + (-85.5 + Math.random()*.01) + "," + Math.random()*360 + "," + -1 + ")");
        }
        System.out.println("device_loc Table: 0 rows appended");

        //Display
        txtORDERS.setText(GetORDERinfo());
        txtQARSP.setText(GetQARSPinfo());
        
        DisconnectIt(conn);
    }
    
    
    public  Connection getConnection() throws SQLException, ClassNotFoundException 	//creates connection
    {
        String dbURL = "jdbc:mysql://" + txtURL.getText() + "/qarsp_db";
        String user = txtUname.getText();
        String pass = txtPass.getText();

        return DriverManager.getConnection(dbURL, user, pass);
    }
    public void DisconnectIt(Connection conn) throws SQLException
    {
        //close connection
        if(!conn.isClosed()) { conn.close();
        System.out.println("[+]Disconnected.");
        }else { System.out.println("[-]Disconnected.");}
    }
//END Scheduling Functions ===================================================================================================
//Scheduling =================================================================================================================
    public void ControlModQPos(int CommandRule) throws SQLException, ClassNotFoundException
    {
        //Connects --------------------------------------------------------------------------------
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        //Selects all unanswered orders (status = -1) ---------------------------------------------
        String oSQL = "SELECT * FROM orders_db WHERE status = -1";
        String oResult = "ID\tlat\t\tlng\t\tstat\tprior\towner_ID\n";

        Vector<Integer> t_IDo = new Vector<Integer>();		Vector<Float> t_lat = new Vector<Float>();
        Vector<Float> t_lng = new Vector<Float>();		Vector<Integer> t_stt = new Vector<Integer>();	
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
//END COMMAND ================================================================================================================
//LOGICS =====================================================================================================================
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
//END LOGICS =================================================================================================================
//HELPER FUNCTIONS ===========================================================================================================
    public static Vector<Vector<Float>> FromToMatrix(Vector<Float> ox, Vector<Float> oy, Vector<Float> qx, Vector<Float> qy)
    {
        Vector<Vector<Float>> FromTo = new Vector<Vector<Float>>();

        for(int o = 0; o < ox.size(); o++)
        {
                Vector<Float> From = new Vector<Float>();

                for(int q = 0; q < qx.size(); q++)
                {
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
//END HELPER FUNCTIONS ======================================================================================================= 
    
//END Scheduling functions ===================================================================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboCommandType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JMenu mReset;
    private javax.swing.JMenu mStartService;
    private javax.swing.JMenu mStopService;
    private javax.swing.JMenuItem smAddOrde;
    private javax.swing.JMenuItem smAddQARSP;
    private javax.swing.JMenuItem smClearOrdersT;
    private javax.swing.JMenuItem smClearQARSPT;
    private javax.swing.JMenuItem smClearRand;
    private javax.swing.JTextArea txtORDERS;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextArea txtQARSP;
    private javax.swing.JTextField txtURL;
    private javax.swing.JTextField txtUname;
    // End of variables declaration//GEN-END:variables

}
