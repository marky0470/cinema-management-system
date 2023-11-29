package com.mycompany.cms.gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import com.mycompany.cms.util.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author ikelm
 */
public class Movies_Tab extends javax.swing.JFrame {

    /**
     * Creates new form moviesPanel
     */
    public Movies_Tab() {
        initComponents();
    }
 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMovieLabel = new javax.swing.JLabel();
        jImageHolder = new javax.swing.JPanel();
        jTitleLabel = new javax.swing.JLabel();
        jRatingLabel = new javax.swing.JLabel();
        jDateLabel = new javax.swing.JLabel();
        jGenreLabel = new javax.swing.JLabel();
        jDurationLabel = new javax.swing.JLabel();
        jTitleText = new javax.swing.JTextField();
        jRatingText = new javax.swing.JTextField();
        jReleaseDateText = new javax.swing.JTextField();
        jGenreText = new javax.swing.JTextField();
        jDurationText = new javax.swing.JTextField();
        jClearButton = new javax.swing.JButton();
        jUpdateButton = new javax.swing.JButton();
        jAddButton = new javax.swing.JButton();
        jSearchText = new javax.swing.JTextField();
        jSearchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMovieTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(780, 720));

        jMovieLabel.setText("Movies");

        jImageHolder.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jImageHolderLayout = new javax.swing.GroupLayout(jImageHolder);
        jImageHolder.setLayout(jImageHolderLayout);
        jImageHolderLayout.setHorizontalGroup(
            jImageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jImageHolderLayout.setVerticalGroup(
            jImageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTitleLabel.setText("Title");

        jRatingLabel.setText("Rating");

        jDateLabel.setText("Release date");

        jGenreLabel.setText("Genre");

        jDurationLabel.setText("Duration");

        jTitleText.setEnabled(false);
        jTitleText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTitleTextActionPerformed(evt);
            }
        });

        jRatingText.setEnabled(false);
        jRatingText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRatingTextActionPerformed(evt);
            }
        });

        jReleaseDateText.setEnabled(false);

        jGenreText.setEnabled(false);

        jDurationText.setEnabled(false);

        jClearButton.setText("Clear");
        jClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearButtonActionPerformed(evt);
            }
        });

        jUpdateButton.setText("Update");
        jUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateButtonActionPerformed(evt);
            }
        });

        jAddButton.setBackground(new java.awt.Color(153, 0, 255));
        jAddButton.setForeground(new java.awt.Color(255, 255, 255));
        jAddButton.setText("Add");
        jAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddButtonActionPerformed(evt);
            }
        });

        jSearchButton.setBackground(new java.awt.Color(204, 0, 255));
        jSearchButton.setForeground(new java.awt.Color(255, 255, 255));
        jSearchButton.setText("Search");
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jMovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title ", "Rating", "Release date", "Genre", "Duration", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jMovieTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jMovieTable);
        if (jMovieTable.getColumnModel().getColumnCount() > 0) {
            jMovieTable.getColumnModel().getColumn(0).setResizable(false);
            jMovieTable.getColumnModel().getColumn(1).setResizable(false);
            jMovieTable.getColumnModel().getColumn(2).setResizable(false);
            jMovieTable.getColumnModel().getColumn(3).setResizable(false);
            jMovieTable.getColumnModel().getColumn(4).setResizable(false);
            jMovieTable.getColumnModel().getColumn(5).setResizable(false);
            jMovieTable.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jMovieLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateLabel)
                    .addComponent(jRatingLabel)
                    .addComponent(jTitleLabel)
                    .addComponent(jGenreLabel)
                    .addComponent(jDurationLabel)
                    .addComponent(jClearButton))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jImageHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDurationText)
                            .addComponent(jGenreText)
                            .addComponent(jTitleText)
                            .addComponent(jRatingText)
                            .addComponent(jReleaseDateText)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jUpdateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(jAddButton)))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jMovieLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jImageHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTitleText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTitleLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRatingText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRatingLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDateLabel)
                            .addComponent(jReleaseDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jGenreLabel)
                            .addComponent(jGenreText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDurationLabel)
                            .addComponent(jDurationText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jUpdateButton)
                            .addComponent(jAddButton)
                            .addComponent(jClearButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSearchText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(227, 227, 227))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearButtonActionPerformed
        // TODO add your handling code here:
        
        //CLEAR//
        
        jTitleText.setText("");
	jRatingText.setText("");
	jReleaseDateText.setText("");
	jGenreText.setText("");
	jDurationText.setText("");
    }//GEN-LAST:event_jClearButtonActionPerformed

    private void jRatingTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRatingTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRatingTextActionPerformed

    private void jAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddButtonActionPerformed
        // TODO add your handling code here:
        
        //ADD//
        
        String title = jTitleText.getText();
        String rating = jRatingText.getText();
        String release_date = jReleaseDateText.getText();
        String genre = jGenreText.getText();
        String duration = jDurationText.getText();  
        
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

	    String query = "INSERT INTO movies (title, rating, release_date, genre, duration) VALUES (?, ?, ?, ?, ?)";

	    PreparedStatement prepStmt = con.prepareStatement(query);
	    prepStmt.setString(1, title);
            prepStmt.setString(2, rating);
            prepStmt.setString(3, release_date);
            prepStmt.setString(4, genre);
            prepStmt.setString(5, duration);
	    
	    } catch (SQLException e) {
            System.out.println(e);
	    }
        
        jTitleText.setText("");
	jRatingText.setText("");
	jReleaseDateText.setText("");
	jGenreText.setText("");
	jDurationText.setText("");
        
    }//GEN-LAST:event_jAddButtonActionPerformed

    private void jUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateButtonActionPerformed
        // TODO add your handling code here:
        
        //UPDATE//
        
        String title = jTitleText.getText();
        String rating = jRatingText.getText();
        String release_date = jReleaseDateText.getText();
        String genre = jGenreText.getText();
        String duration = jDurationText.getText();  
        
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();
            
            int selectedRow = jMovieTable.getSelectedRow();
	    int movie_id_column = 0;

	    selectedRow = jMovieTable.getSelectedRow();
 	    String movie_id = jMovieTable.getModel().getValueAt(selectedRow, movie_id_column).toString();

	    String query = "UPDATE movies SET (title = ?, rating = ?, release_date = ?, genre = ?, duration = ?) WHERE (movie_id = ?)";

	    PreparedStatement prepStmt = con.prepareStatement(query);
	    prepStmt.setString(1, title);
            prepStmt.setString(2, rating);
            prepStmt.setString(3, release_date);
            prepStmt.setString(4, genre);
            prepStmt.setString(5, duration);
            prepStmt.setString(6, movie_id);
	    
	    } catch (SQLException e) {
            System.out.println(e);
	    }
        
        jTitleText.setText("");
	jRatingText.setText("");
	jReleaseDateText.setText("");
	jGenreText.setText("");
	jDurationText.setText("");
        
    }//GEN-LAST:event_jUpdateButtonActionPerformed

    private void jTitleTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTitleTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTitleTextActionPerformed

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        // TODO add your handling code here:
        
        //SEARCH//
        
        String searchInput = jSearchText.getText();
        
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

	    String query = "SELECT * FROM movies WHERE title LIKE ?";

	    PreparedStatement prepStmt = con.prepareStatement(query);
	    prepStmt.setString(1, searchInput);
            
            ResultSet resultSet = prepStmt.executeQuery();
            resultSet.next();
            
            while(resultSet.next()){
                
                String id = String.valueOf(resultSet.getInt("movie_id"));
                String title = resultSet.getString("title");
                String rating = resultSet.getString("rating");
                String release_date = resultSet.getString("release_date");
                String genre = resultSet.getString("genre");
                String duration = String.valueOf(resultSet.getInt("duration"));
                
                String tblData[] = {id, title, rating, release_date, genre, duration};
                DefaultTableModel tblModel = (DefaultTableModel)jMovieTable.getModel();
                
            }
            
            
	    } catch (SQLException e) {
            System.out.println(e);
	    }
        
    }//GEN-LAST:event_jSearchButtonActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Movies_Tab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Movies_Tab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Movies_Tab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Movies_Tab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Movies_Tab().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddButton;
    private javax.swing.JButton jClearButton;
    private javax.swing.JLabel jDateLabel;
    private javax.swing.JLabel jDurationLabel;
    private javax.swing.JTextField jDurationText;
    private javax.swing.JLabel jGenreLabel;
    private javax.swing.JTextField jGenreText;
    private javax.swing.JPanel jImageHolder;
    private javax.swing.JLabel jMovieLabel;
    private javax.swing.JTable jMovieTable;
    private javax.swing.JLabel jRatingLabel;
    private javax.swing.JTextField jRatingText;
    private javax.swing.JTextField jReleaseDateText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchText;
    private javax.swing.JLabel jTitleLabel;
    private javax.swing.JTextField jTitleText;
    private javax.swing.JButton jUpdateButton;
    // End of variables declaration//GEN-END:variables
}
