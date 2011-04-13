/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Scheduling_UI2.java
 *
 * Created on Apr 7, 2011, 2:03:32 AM
 */

package commandservice;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaret
 */
public class CommandUI extends javax.swing.JFrame {

    /** Creates new form Scheduling_UI2 */
    public CommandUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGit = new java.awt.Button();
        cboCommandType = new javax.swing.JComboBox();
        txtQARSP = new java.awt.TextArea();
        txtORDERS = new java.awt.TextArea();
        btnReset = new java.awt.Button();
        btnStartService = new java.awt.Button();
        btnStopService = new java.awt.Button();
        txtURL = new javax.swing.JTextField();
        txtUname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mGet = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mStartService = new javax.swing.JMenu();
        mStopService = new javax.swing.JMenu();
        mReset = new javax.swing.JMenu();

        btnGit.setBackground(new java.awt.Color(123, 177, 214));
        btnGit.setLabel("Git");
        btnGit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGitActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cboCommandType.setEditable(true);
        cboCommandType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FIFO", "LIFO", "Closest" }));
        cboCommandType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCommandTypeActionPerformed(evt);
            }
        });

        btnReset.setLabel("Reset");
        btnReset.setVisible(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnStartService.setBackground(new java.awt.Color(175, 197, 152));
        btnStartService.setLabel("Start Service");
        btnStartService.setVisible(false);
        btnStartService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartServiceActionPerformed(evt);
            }
        });

        btnStopService.setBackground(new java.awt.Color(227, 142, 142));
        btnStopService.setLabel("Stop Service");
        btnStopService.setVisible(false);
        btnStopService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopServiceActionPerformed(evt);
            }
        });

        txtURL.setText("127.0.0.1:8889");

        txtUname.setText("qarsp");

        jLabel1.setText("URL");

        jLabel2.setText("Uname");

        jLabel3.setText("Pass");

        txtPass.setText("syse2011");

        jLabel4.setText("Scheduling Rule");

        mGet.setText("Get");
        mGet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mGetMouseClicked(evt);
            }
        });
        mGet.add(jSeparator1);

        jMenuBar1.add(mGet);

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

        mReset.setText("Reset");
        mReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mResetMouseClicked(evt);
            }
        });
        jMenuBar1.add(mReset);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnStartService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQARSP, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(194, 194, 194)
                                .addComponent(btnStopService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 342, Short.MAX_VALUE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtORDERS, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUname, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnStartService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStopService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtORDERS, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                    .addComponent(txtQARSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtUname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cboCommandType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //BUTTON CODE ==============================================================
    private void btnGitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGitActionPerformed
        try {
            txtORDERS.setText(GetORDERinfo());
            txtQARSP.setText(GetQARSPinfo());
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Thread thrGit = new Thread(Git); thrGit.start();
//        try {
//            thrGit.join();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnGitActionPerformed
  
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        //Resetting
        try {
            // TODO add your handling code here:
            TableMods tableMods = new TableMods();
            try {
                tableMods.DropCreateTables();
            } catch (SQLException ex) {
                Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                tableMods.RequestsMod();
            } catch (SQLException ex) {
                Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableMods.PositionMod();
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Display
                try {
            // TODO add your handling code here:
            txtORDERS.setText(GetORDERinfo());
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            txtQARSP.setText(GetQARSPinfo());
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnStartServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartServiceActionPerformed

        System.out.println("Service Started");

        if (ServiceMade == false)
        {
            Thread thr1 = new Thread(rStart); thr1.start();
            ServiceMade = true;
            cmd.on = true;
        } else
        {
            cmd.on = true;
        }


    }//GEN-LAST:event_btnStartServiceActionPerformed

    private void btnStopServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopServiceActionPerformed
        // TODO add your handling code here:

        cmd.on = false;
        System.out.println("Service Stopped");
    }//GEN-LAST:event_btnStopServiceActionPerformed

    private void cboCommandTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCommandTypeActionPerformed
        // TODO add your handling code here:
        cmd.Ctype = cboCommandType.getSelectedIndex();
    }//GEN-LAST:event_cboCommandTypeActionPerformed

    private void mGetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mGetMouseClicked
        // TODO add your handling code here:
        try {
            txtORDERS.setText(GetORDERinfo());
            txtQARSP.setText(GetQARSPinfo());
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mGetMouseClicked

    private void mStartServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mStartServiceMouseClicked
        // TODO add your handling code here:
        System.out.println("Service Started");

        if (ServiceMade == false)
        {
            Thread thr1 = new Thread(rStart); thr1.start();
            ServiceMade = true;
            cmd.on = true;
        } else
        {
            cmd.on = true;
        }
    }//GEN-LAST:event_mStartServiceMouseClicked

    private void mStopServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mStopServiceMouseClicked
        // TODO add your handling code here:
        cmd.on = false;
        System.out.println("Service Stopped");
    }//GEN-LAST:event_mStopServiceMouseClicked

    private void mResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mResetMouseClicked
         //Resetting
        try {
            // TODO add your handling code here:
            TableMods tableMods = new TableMods();
            try {
                tableMods.DropCreateTables();
            } catch (SQLException ex) {
                Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                tableMods.RequestsMod();
            } catch (SQLException ex) {
                Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableMods.PositionMod();
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Display
        try {
            // TODO add your handling code here:
            txtORDERS.setText(GetORDERinfo());
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            txtQARSP.setText(GetQARSPinfo());
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mResetMouseClicked
    //END BUTTON CODE===========================================================


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CommandUI().setVisible(true);
            }
        });
    }

    //FUNCTIONS FOR BUTTON CODE ================================================
    public void Resetter()throws SQLException, ClassNotFoundException {
        TableMods tableMods = new TableMods();
        tableMods.DropCreateTables();
	tableMods.RequestsMod();
	tableMods.PositionMod();
    }

    public Command cmd = new Command();
    public Boolean ServiceMade = false;

    Runnable rStart = new Runnable() {
    public void run() {
        //Command cmd = new Command();
        cmd.on = true;

        String[] args = {txtURL.getText(), txtUname.getText(), txtPass.getText()};
        try {
            cmd.main(args);
        } catch (SQLException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommandUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    };
    //END FUNCTIONS FOR BUTTON CODE ============================================
    //GET QARSP DATA ==================================================================================
    public String GetQARSPinfo() throws SQLException, ClassNotFoundException
    {
            Connection conn = getConnection("jdbc:mysql://127.0.0.1/qarsp_db", "root", "root");
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

                    //System.out.println(rResult);

                    System.out.println("[+]Requests Selected.");
            }
            catch(Exception e){
                    System.out.println("[-]Requests Selected.");
                    //e.printStackTrace();
            }

            DisconnectIt(conn);

            return rResult;
    }
    public  String GetORDERinfo( ) throws SQLException, ClassNotFoundException
    {
            Connection conn = getConnection("jdbc:mysql://127.0.0.1/qarsp_db", "root", "root");
            
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

                    //System.out.print(qResult);

                    System.out.println("[+]Positions Selected.");
            }
            catch (Exception e)
            {
                    System.out.println("[-]Positions Selected.");
                    //e.printStackTrace();
            }

            DisconnectIt(conn);

            return qResult;
    }
    //END GET QARSP DATA ==============================================================================
    //CONNECTION FUNCTIONS ============================================================================

    public  Connection getConnection(String dbURL, String user, String pass) throws SQLException, ClassNotFoundException 	//creates connection
    {
        dbURL = "jdbc:mysql://" + txtURL.getText() + "/qarsp_db";
        user = txtUname.getText();
        pass = txtPass.getText();

            //try{
                return DriverManager.getConnection(dbURL, user, pass);
            //} catch (Exception e) {
            //        System.out.println("[-]Connected.");
            //        return null;
            //}
    }
    public void DisconnectIt(Connection conn) throws SQLException
    {
        //close connection
            if(!conn.isClosed()) { conn.close();
            System.out.println("[+]Disconnected.");
            }else { System.out.println("[-]Disconnected.");}
    }
    //END CONNECTION FUNCTIONS ========================================================================



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnGit;
    private java.awt.Button btnReset;
    private java.awt.Button btnStartService;
    private java.awt.Button btnStopService;
    private javax.swing.JComboBox cboCommandType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu mGet;
    private javax.swing.JMenu mReset;
    private javax.swing.JMenu mStartService;
    private javax.swing.JMenu mStopService;
    private java.awt.TextArea txtORDERS;
    private javax.swing.JTextField txtPass;
    private java.awt.TextArea txtQARSP;
    public javax.swing.JTextField txtURL;
    private javax.swing.JTextField txtUname;
    // End of variables declaration//GEN-END:variables

}
