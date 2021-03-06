
package ru;

import ru.modeles.ErrorTableModel;
import ru.modeles.ExpressionSetTableModel;
import ru.modeles.OkModel;
import ru.modeles.ResultSetTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Main extends javax.swing.JFrame {
    private Main mainForm = this;
    private final String URL = "jdbc:postgresql://192.168.56.200:5432/test";
    private Connection conn = null;

    /**
     * Creates new form Main
     */
    public Main() {

        this.setTitle("SQL Monitor " + LocalTime.now());
        initComponents();

        new Thread(
                () -> {
            while (true) {
                mainForm.setTitle("SQL Monitor " + LocalTime.now());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(
                () -> {
                    while (true) {
                        checkConnection();
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    private void checkConnection() {
        if (conn == null) {
            exec.setEnabled(false);
        } else {
            try (Statement stmt = conn.createStatement()){
                stmt.execute("select now()");
                exec.setEnabled(true);
            } catch (Exception e) {
                exec.setEnabled(false);
            }
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        connect = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        user = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sql = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        result = new javax.swing.JTable();
        exec = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                Close(evt);
            }
        });

        connect.setText("Connect");
        connect.setName("conn"); // NOI18N
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        password.setText("tiger");
        password.setName("password"); // NOI18N

        user.setText("scott");

        jLabel1.setText("SQL");

        sql.setColumns(20);
        sql.setRows(5);
        sql.setText("select now()");
        sql.setName("sql"); // NOI18N
        jScrollPane1.setViewportView(sql);

        jScrollPane2.setName("resultPane"); // NOI18N

        result.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {"1 Введите логин - scott"},
                        {"2 Введите пароль - tiger"},
                        {"3 Соединитесь с POSTGRESQL"},
                        {"4 Введите запрос и выполните его"}
                },
                new String[]{
                        "Порядок использования"
                }
        ));
        jScrollPane2.setViewportView(result);

        exec.setText("Выполнить");
        exec.setName("exec"); // NOI18N
        exec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                execActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(37, 37, 37)
                                                                .addComponent(exec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(43, 43, 43)
                                                                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(26, 26, 26)
                                                .addComponent(connect)))
                                .addGap(69, 69, 69)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(connect)
                                                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(exec)
                                                )
                                                .addGap(21, 21, 21)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents


    //Выполнение запроса с подменой модели данных результата,
    //в зависимости от результата
    private void execActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_execActionPerformed
        //System.out.println("Executing");
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            if (stmt.execute(sql.getText())) {
                ResultSet r = stmt.getResultSet();
                System.out.println(r);
                result.setModel(new ResultSetTableModel(r));
            } else {
                result.setModel(new ExpressionSetTableModel(stmt.getUpdateCount()));
            }


        } catch (Exception e) {
            //System.err.println(e.toString());
            result.setModel(new ErrorTableModel(e.getMessage()));
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }

    }//GEN-LAST:event_execActionPerformed

    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                result.setModel(new ErrorTableModel(e.getMessage()));
            }
            conn = null;

        }
        try {
            conn = DriverManager.getConnection(URL, user.getText(),
                    String.valueOf(password.getPassword()));
            //System.out.println("---connected");
            result.setModel(new OkModel(conn));
        } catch (Exception e) {
            result.setModel(new ErrorTableModel(e.getMessage()));
        }
    }//GEN-LAST:event_connectActionPerformed

    private void Close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Close
        //System.out.println("Closing...");
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_Close

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing
                    .UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing
                            .UIManager
                            .setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util
                    .logging
                    .Logger
                    .getLogger(Main.class.getName())
                    .log(java.util
                            .logging
                            .Level
                            .SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util
                    .logging
                    .Logger
                    .getLogger(Main.class.getName())
                    .log(java.util
                            .logging
                            .Level
                            .SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util
                    .logging
                    .Logger
                    .getLogger(Main.class.getName())
                    .log(java.util
                            .logging
                            .Level
                            .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util
                    .logging
                    .Logger
                    .getLogger(Main.class.getName())
                    .log(java.util
                            .logging
                            .Level
                            .SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt
                .EventQueue
                .invokeLater(new Runnable() {
                    public void run() {

                        new Main().setVisible(true);
                    }
                });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connect;
    private javax.swing.JButton exec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPasswordField password;
    private javax.swing.JTable result;
    private javax.swing.JTextArea sql;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables

}
