/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.cinemas;

import com.mycompany.cms.util.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author francisjamestolentino
 */
public class CinemasPanel extends javax.swing.JPanel {

    /**
     * Creates new form CinemasPanel
     */
    public CinemasPanel() {
        initComponents();
        refreshTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jCinemaTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSearchbar = new javax.swing.JTextField();
        jCinemaType = new javax.swing.JComboBox<>();
        jSearchButton = new javax.swing.JButton();
        jCinemaName = new javax.swing.JTextField();
        jAddButton = new javax.swing.JButton();
        jClearButton = new javax.swing.JButton();
        jDeleteButton = new javax.swing.JButton();
        jUpdateButton = new javax.swing.JButton();
        jRefreshButton = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Cinemas");

        jCinemaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Cinema", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jCinemaTable.getTableHeader().setResizingAllowed(false);
        jCinemaTable.getTableHeader().setReorderingAllowed(false);
        jCinemaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCinemaTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jCinemaTable);
        if (jCinemaTable.getColumnModel().getColumnCount() > 0) {
            jCinemaTable.getColumnModel().getColumn(0).setResizable(false);
            jCinemaTable.getColumnModel().getColumn(1).setResizable(false);
            jCinemaTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel2.setText("Cinema");

        jLabel3.setText("Type");

        jCinemaType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "Standard", "3D", "IMAX" }));

        jSearchButton.setBackground(new java.awt.Color(239, 124, 18));
        jSearchButton.setForeground(new java.awt.Color(255, 255, 255));
        jSearchButton.setText("Search");
        jSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSearchButton.setBorderPainted(false);
        jSearchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jAddButton.setBackground(new java.awt.Color(239, 124, 18));
        jAddButton.setForeground(new java.awt.Color(255, 255, 255));
        jAddButton.setText("Add");
        jAddButton.setBorderPainted(false);
        jAddButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddButtonActionPerformed(evt);
            }
        });

        jClearButton.setBackground(new java.awt.Color(247, 196, 149));
        jClearButton.setText("Clear");
        jClearButton.setBorderPainted(false);
        jClearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearButtonActionPerformed(evt);
            }
        });

        jDeleteButton.setBackground(new java.awt.Color(247, 196, 149));
        jDeleteButton.setText("Delete");
        jDeleteButton.setBorderPainted(false);
        jDeleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteButtonActionPerformed(evt);
            }
        });

        jUpdateButton.setBackground(new java.awt.Color(239, 124, 18));
        jUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        jUpdateButton.setText("Update");
        jUpdateButton.setBorderPainted(false);
        jUpdateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateButtonActionPerformed(evt);
            }
        });

        jRefreshButton.setBackground(new java.awt.Color(247, 196, 149));
        jRefreshButton.setText("Refresh");
        jRefreshButton.setBorderPainted(false);
        jRefreshButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCinemaName)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jCinemaType, javax.swing.GroupLayout.Alignment.TRAILING, 0, 336, Short.MAX_VALUE))
                        .addGap(88, 88, 88))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSearchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSearchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCinemaName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCinemaType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshTable() {
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();
            
            String query = "SELECT cinema_id, name, type FROM cinemas";
            
            try (PreparedStatement pstmt = con.prepareStatement(query);
                    ResultSet resultSet = pstmt.executeQuery()) {

                    DefaultTableModel model = (DefaultTableModel) jCinemaTable.getModel();
                    model.setRowCount(0);

                    while (resultSet.next()) {
                    int cinemaId = resultSet.getInt("cinema_id");
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    

                    model.addRow(new Object[]{cinemaId, name, type});
                    }
                }
            } catch (SQLException e) {
              System.out.println(e);
              }
    }
    
    private void jAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddButtonActionPerformed
        // TODO add your handling code here:
        
            String cinemaName = jCinemaName.getText();
            String cinemaType = (String) jCinemaType.getSelectedItem();

            String query = "INSERT INTO cinemas (name, type) VALUES (?, ?)";

            try {
                Connector connector = new Connector();
                Connection con = connector.getConnection();

                PreparedStatement prepStmt = con.prepareStatement(query);

                String dupliError = "select * from cinemas where name ="+cinemaName+"";
                ResultSet result = prepStmt.executeQuery(dupliError);

                if (result.next()) {
                    JOptionPane.showMessageDialog(this, "Cinema "+cinemaName+" Already Exist.");
                    jCinemaName.setText("");
                    jCinemaType.setSelectedIndex(0);
                }else{

                String cinemaAdd = "INSERT INTO cinemas VALUES (cinema_id, "+cinemaName+", '"+cinemaType+"')";
                prepStmt.executeUpdate(cinemaAdd);

                JOptionPane.showMessageDialog(this, "Cinema "+cinemaName+" successfully added!");
                jCinemaName.setText("");
                jCinemaType.setSelectedIndex(0);
                }
                
            } catch (SQLException e) {
            System.out.println(e);
            }
            
            jCinemaName.setText("");
            jCinemaType.setSelectedIndex(0);
            jSearchbar.setText("");
            refreshTable();
    }//GEN-LAST:event_jAddButtonActionPerformed

    private void jClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearButtonActionPerformed
        // TODO add your handling code here:
        int confirmation=JOptionPane.showConfirmDialog(this,"Are you sure you want to clear your inputs?");
            if(confirmation==JOptionPane.YES_OPTION){
            jCinemaName.setText("");
            jCinemaType.setSelectedIndex(0);
            jSearchbar.setText("");
        }
        refreshTable();
    }//GEN-LAST:event_jClearButtonActionPerformed

    private void jDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteButtonActionPerformed
        // TODO add your handling code here:
        int confirmation=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this cinema?");
            if(confirmation==JOptionPane.YES_OPTION){
        
                try {

                    Connector connector = new Connector();
                    Connection con = connector.getConnection();

                    int selectedRow = jCinemaTable.getSelectedRow();
                    int cinemaIDColumn = 0;
                    int cinemaTableID = (int) jCinemaTable.getModel().getValueAt(selectedRow, cinemaIDColumn);

                    String query = "DELETE FROM cinemas WHERE cinema_id = ?";


                    PreparedStatement prepStmt = con.prepareStatement(query);
                    prepStmt.setInt(1, cinemaTableID);

                    prepStmt.executeUpdate();

                } catch (SQLException e) {
                System.out.println(e);
                }

                jCinemaName.setText("");
                jCinemaType.setSelectedIndex(0);
                jSearchbar.setText("");
                refreshTable();
                }
    }//GEN-LAST:event_jDeleteButtonActionPerformed

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        // This block will help to search data on the table.
        String searchInput = jSearchbar.getText();

        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

            String query = "SELECT cinema_id, name, type FROM cinemas WHERE name LIKE ?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%" + searchInput + "%");
            ResultSet resultSet = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jCinemaTable.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                int cinemaId = resultSet.getInt("cinema_id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");

                model.addRow(new Object[]{cinemaId, name, type,});
                }

        } catch (SQLException e) {
        System.out.println(e);
        }
    }//GEN-LAST:event_jSearchButtonActionPerformed

    private void jUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateButtonActionPerformed
        // This updates the selected row in the table.
        String cinemaName = jCinemaName.getText();
        String cinemaType = (String) jCinemaType.getSelectedItem();

        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

            int selectedRow = jCinemaTable.getSelectedRow();
            int cinemaIDColumn = 0;
            int cinemaTableID = (int) jCinemaTable.getModel().getValueAt(selectedRow, cinemaIDColumn);

            String query = "UPDATE cinemas SET name = ?, type = ? WHERE cinema_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(query);
                    
            //This if/else block will prevent data duplication in the database.
            //At the same time it will normally update the selected row.
            String dupliError = "select * from cinemas where name ="+cinemaName+"";
            ResultSet result = prepStmt.executeQuery(dupliError);
                    
            if(result.next()){
                JOptionPane.showMessageDialog(this, "Cinema "+cinemaName+" Already Exist.");
                jCinemaName.setText("");
                jCinemaType.setSelectedIndex(0);
            }else{
                        
                    String cinemaUpdate = "UPDATE cinemas SET name = ?, type = ? WHERE cinema_id = ?";
                        
                    

                    JOptionPane.showMessageDialog(this, "Cinema "+cinemaName+" successfully updated!");
                    jCinemaName.setText("");
                    jCinemaType.setSelectedIndex(0);
                        
                    try (PreparedStatement prepStatement = con.prepareStatement(cinemaUpdate)) {
                        prepStatement.setString(1, cinemaName);
                        prepStatement.setString(2, cinemaType);
                        prepStatement.setInt(3, cinemaTableID);

                        prepStatement.executeUpdate();
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        } catch (SQLException e) {
        System.out.println(e);
        }
        refreshTable();
    }//GEN-LAST:event_jUpdateButtonActionPerformed

    private void jCinemaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCinemaTableMouseClicked
        // Helps to find index in the table.
        int selectedRow = jCinemaTable.getSelectedRow();
        int nameColumn = 1;
        int typeColumn = 2;

        if(selectedRow!=-1){

	jCinemaName.setText(jCinemaTable.getModel().getValueAt(selectedRow, nameColumn).toString());
	jCinemaType.setSelectedItem(jCinemaTable.getModel().getValueAt(selectedRow, typeColumn).toString());
        }
    }//GEN-LAST:event_jCinemaTableMouseClicked

    private void jRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshButtonActionPerformed
        // Refreshes the entire table and clearing the search bar.
        jSearchbar.setText("");
        refreshTable();
    }//GEN-LAST:event_jRefreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddButton;
    private javax.swing.JTextField jCinemaName;
    private javax.swing.JTable jCinemaTable;
    private javax.swing.JComboBox<String> jCinemaType;
    private javax.swing.JButton jClearButton;
    private javax.swing.JButton jDeleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchbar;
    private javax.swing.JButton jUpdateButton;
    // End of variables declaration//GEN-END:variables
}
