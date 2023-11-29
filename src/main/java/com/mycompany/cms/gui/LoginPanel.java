/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui;

import com.mycompany.cms.gui.movies.MoviePanel;
import com.mycompany.cms.util.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author marks
 */
public class LoginPanel extends javax.swing.JPanel {

    LoginForm loginForm;
    /**
     * Creates new form JLoginPanel
     */
    public LoginPanel(LoginForm loginForm) {
        initComponents();
        this.loginForm = loginForm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jEmailLabel = new javax.swing.JLabel();
        jPasswordLabel = new javax.swing.JLabel();
        jEmailTextField = new javax.swing.JTextField();
        jPasswordPasswordField = new javax.swing.JPasswordField();
        jLoginButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jEmailLabel.setText("Email");

        jPasswordLabel.setText("Password");

        jEmailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmailTextFieldActionPerformed(evt);
            }
        });

        jLoginButton.setText("Login");
        jLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLoginButtonActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLoginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jEmailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jPasswordPasswordField))))
                .addGap(109, 109, 109))
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jEmailLabel)
                    .addComponent(jEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordLabel)
                    .addComponent(jPasswordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLoginButton)
                .addGap(90, 90, 90))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jEmailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmailTextFieldActionPerformed

    }//GEN-LAST:event_jEmailTextFieldActionPerformed

    private void jLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLoginButtonActionPerformed
        // TODO add your handling code here:
        String email = jEmailTextField.getText(); //(i)
        String password = jPasswordPasswordField.getText();

        try {
            // Create a connection to the database
            Connector connector = new Connector();
            Connection con = connector.getConnection();

            // Prepare the SQL statement, di pwede mag add add sa string dito bawal bawal
            String query = "SELECT password, is_admin FROM users WHERE email = ?";  //Means get the password and is_admin, from users table, but only if they have this specific email
            PreparedStatement prepStmt = con.prepareStatement(query); //Converts string into statement
            prepStmt.setString(1, email); //replaces the first ? with the our email variable (i)

            // Execute/send query to SQL to be processed, all results are stored in resultset
            ResultSet resultSet = prepStmt.executeQuery();
            resultSet.next();
            //A ResultSet is like a table, but it only sees a certain row of the table at a time and at first, it can only see the header and cannot give you any values back
            //That is why we need to do resultSet.next();

            if (resultSet == null) {
                System.out.println("No user exists");
                return;
            }
            if (!password.equals(resultSet.getString("password"))) {
                System.out.println("Wrong password");
                return;
            }
            System.out.println("Authenticated Successfully");
            boolean isAdmin = resultSet.getBoolean("is_admin");
            if (isAdmin) {
                System.out.println("Admin Detected");
            }
            proceedToMainForm(isAdmin);
            

        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jLoginButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Start main window/form
        
        //TEMPORARY FOR QUICK ACCESS, REMOVE LATER
        SwingUtilities.invokeLater(() -> {
            
            MainForm mainForm = new MainForm();
            
            mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainForm.pack();
            mainForm.setVisible(true);
            loginForm.setVisible(false);
            
            MoviePanel moviePanel = new MoviePanel();
            mainForm.showPanel(moviePanel);
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void proceedToMainForm(boolean isAdmin) {
        SwingUtilities.invokeLater(() -> {
            
            MainForm mainForm = new MainForm();
            
            mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            mainForm.pack();
            mainForm.setVisible(true);
            loginForm.setVisible(false);
            if (isAdmin) {
                MoviePanel moviePanel = new MoviePanel();
                mainForm.showPanel(moviePanel);
            } else {
                System.out.println("Staff dash not yet implemented");
//                mainForm.showStaffDashboardPanel();
            }
        });
    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jEmailLabel;
    private javax.swing.JTextField jEmailTextField;
    private javax.swing.JButton jLoginButton;
    private javax.swing.JLabel jPasswordLabel;
    private javax.swing.JPasswordField jPasswordPasswordField;
    // End of variables declaration//GEN-END:variables
}
