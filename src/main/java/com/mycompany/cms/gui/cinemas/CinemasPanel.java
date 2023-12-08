/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.cinemas;

import com.mycompany.cms.util.Connector;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author francisjamestolentino
 */
public class CinemasPanel extends javax.swing.JPanel {
    
    private int selectedAction = 0;

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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCinemaType = new javax.swing.JComboBox<>();
        jCinemaName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jClearButton = new javax.swing.JButton();
        jDeleteButton = new javax.swing.JButton();
        jUpdateButton = new javax.swing.JButton();
        jAddButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSaveButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jCinemaTable = new javax.swing.JTable();
        jSearchbar = new javax.swing.JTextField();
        jSearchButton = new javax.swing.JButton();
        jRefreshButton = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(253, 253, 253));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Cinemas");

        jLabel2.setText("Cinema");

        jLabel3.setText("Type");

        jCinemaType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "Standard", "3D", "IMAX" }));
        jCinemaType.setEnabled(false);

        jCinemaName.setEnabled(false);

        jPanel3.setBackground(new java.awt.Color(253, 253, 253));

        jClearButton.setBackground(new java.awt.Color(247, 222, 200));
        jClearButton.setText("Clear");
        jClearButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 222, 200), 1, true));
        jClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearButtonActionPerformed(evt);
            }
        });

        jDeleteButton.setBackground(new java.awt.Color(240, 240, 240));
        jDeleteButton.setForeground(new java.awt.Color(239, 124, 18));
        jDeleteButton.setText("Delete");
        jDeleteButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteButtonActionPerformed(evt);
            }
        });

        jUpdateButton.setBackground(new java.awt.Color(240, 240, 240));
        jUpdateButton.setForeground(new java.awt.Color(239, 124, 18));
        jUpdateButton.setText("Update");
        jUpdateButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateButtonActionPerformed(evt);
            }
        });

        jAddButton.setBackground(new java.awt.Color(240, 240, 240));
        jAddButton.setForeground(new java.awt.Color(239, 124, 18));
        jAddButton.setText("Add");
        jAddButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
        jAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Actions (Select operation to enable inputs)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jSaveButton.setBackground(new java.awt.Color(239, 124, 18));
        jSaveButton.setForeground(new java.awt.Color(255, 255, 255));
        jSaveButton.setText("Save");
        jSaveButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 0), 1, true));
        jSaveButton.setEnabled(false);
        jSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveButtonActionPerformed(evt);
            }
        });

        jCancelButton.setBackground(new java.awt.Color(253, 253, 253));
        jCancelButton.setForeground(new java.awt.Color(239, 124, 18));
        jCancelButton.setText("Cancel");
        jCancelButton.setBorder(null);
        jCancelButton.setEnabled(false);
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jCinemaType, 0, 321, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jCinemaName))
                            .addComponent(jLabel1))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCinemaName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCinemaType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(189, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.WEST);

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

        jSearchButton.setBackground(new java.awt.Color(239, 124, 18));
        jSearchButton.setForeground(new java.awt.Color(255, 255, 255));
        jSearchButton.setText("Search");
        jSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 124, 18)));
        jSearchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jRefreshButton.setBackground(new java.awt.Color(247, 222, 200));
        jRefreshButton.setText("Refresh");
        jRefreshButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 222, 200), 1, true));
        jRefreshButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSearchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSearchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
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
    
    private boolean cinemaDupli(String cinemaName) {
         String query = "SELECT COUNT(name) FROM cinemas WHERE name = ?";
        try (Connection con = new Connector().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, cinemaName);
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    
                    int count = result.getInt(1);
                return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        }
    
    private void addCinema() {
        if("".equals(jCinemaName.getText())){
            JOptionPane.showMessageDialog(this,"Some of the information needed is missing.","Alert!",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        else if("Select Type".equals(jCinemaType.getSelectedItem())){
            JOptionPane.showMessageDialog(this,"Some of the information needed is missing.","Alert!",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();
            
            String cinemaName = jCinemaName.getText();
            String cinemaType = (String) jCinemaType.getSelectedItem();

            // Check for duplicate cinema names
            String query = "SELECT * FROM cinemas WHERE name = ?";
            PreparedStatement prepStmtCheck = con.prepareStatement(query);
            prepStmtCheck.setString(1, cinemaName);
            
            if (prepStmtCheck.executeQuery().next()) {
                JOptionPane.showMessageDialog(this, "Cinema " + cinemaName + " Already Exist.");
                jCinemaName.setText("");
                jCinemaType.setSelectedIndex(0);
                return;
            }
            
            // Add cinema to db
            query = "INSERT INTO cinemas (name, type) VALUES (?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, cinemaName);
            prepStmt.setString(2, cinemaType);
            
            prepStmt.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Cinema " + cinemaName + " successfully added!");
            jCinemaName.setText("");
            jCinemaType.setSelectedIndex(0);
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jCinemaName.setText("");
        jCinemaType.setSelectedIndex(0);
        jSearchbar.setText("");
        refreshTable();
    }
    
    private void deleteCinema() {
        if(!jCinemaTable.getSelectionModel().isSelectionEmpty()){
        
            int confirmation=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this cinema?");
                if(confirmation==JOptionPane.YES_OPTION){

                    try {

                        Connector connector = new Connector();
                        Connection con = connector.getConnection();
                        
                        int selectedRow = jCinemaTable.getSelectedRow();
                        int cinemaIDColumn = 0;
                        int cinemaTableID = (int) jCinemaTable.getModel().getValueAt(selectedRow, cinemaIDColumn);
                        String cinemaName = jCinemaName.getText();

                        String query = "DELETE FROM cinemas WHERE cinema_id = ?";


                        PreparedStatement prepStmt = con.prepareStatement(query);
                        prepStmt.setInt(1, cinemaTableID);

                        prepStmt.executeUpdate();
                        
                        JOptionPane.showMessageDialog(this, "Cinema " + cinemaName + " successfully deleted!");
                        jCinemaName.setText("");
                        jCinemaType.setSelectedIndex(0);

                    } catch (SQLException e) {
                    System.out.println(e);
                    }

                    jCinemaName.setText("");
                    jCinemaType.setSelectedIndex(0);
                    jSearchbar.setText("");
                    refreshTable();
                    }
        }else {
                JOptionPane.showMessageDialog(this,"Please select an item.","Alert!",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        // This block will help to search data on the table.
        String searchInput = jSearchbar.getText();

        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

            String query = "SELECT cinema_id, name, type FROM cinemas WHERE name LIKE ? OR type LIKE ?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%" + searchInput + "%");
            pstmt.setString(2, "%" + searchInput + "%");
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

    private String getCurrentCinema(){
        int selectedRow = jCinemaTable.getSelectedRow();
        String cinemaName = (String) jCinemaTable.getValueAt(selectedRow, 1);
        return cinemaName;
    }
    
    private void updateCinema() {
        String currentCinema = getCurrentCinema();
        String cinemaType = (String) jCinemaType.getSelectedItem();
        String newCinema = jCinemaName.getText();

        if ("Select Type".equals(jCinemaType.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Some of the information needed is missing.", "Alert!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!currentCinema.equals(newCinema) && cinemaDupli(newCinema)) {
            JOptionPane.showMessageDialog(this, "Cinema " + newCinema + " Already Exist.");
            return;
        }


        if (!jCinemaTable.getSelectionModel().isSelectionEmpty()) {
            try {
                Connector connector = new Connector();
                Connection con = connector.getConnection();

                int selectedRow = jCinemaTable.getSelectedRow();
                int cinemaIDColumn = 0;
                int cinemaTableID = (int) jCinemaTable.getModel().getValueAt(selectedRow, cinemaIDColumn);

                String cinemaUpdate = "UPDATE cinemas SET name = ?, type = ? WHERE cinema_id = ?";

                JOptionPane.showMessageDialog(this, "Cinema " + newCinema + " successfully updated!");
                jCinemaName.setText("");
                jCinemaType.setSelectedIndex(0);

                PreparedStatement prepStatement = con.prepareStatement(cinemaUpdate);
                prepStatement.setString(1, newCinema);
                prepStatement.setString(2, cinemaType);
                prepStatement.setInt(3, cinemaTableID);

                prepStatement.executeUpdate();

            } catch (SQLException e) {
            System.out.println(e);
            }
        }else {
            JOptionPane.showMessageDialog(this,"Please select an item.","Alert!",JOptionPane.INFORMATION_MESSAGE);
            }
        refreshTable();
    }
    
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

    private void jClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearButtonActionPerformed
        int confirmation=JOptionPane.showConfirmDialog(this,"Are you sure you want to clear your inputs?");
            if(confirmation==JOptionPane.YES_OPTION){
            jCinemaName.setText("");
            jCinemaType.setSelectedIndex(0);
            jSearchbar.setText("");
        }
    }//GEN-LAST:event_jClearButtonActionPerformed

    private void jDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteButtonActionPerformed
        selectedAction = 3;
        refreshActionButtons();
        deleteCinema();
    }//GEN-LAST:event_jDeleteButtonActionPerformed

    private void jUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateButtonActionPerformed
        selectedAction = 2;
        jCancelButton.setEnabled(true);
        jSaveButton.setEnabled(true);
        refreshActionButtons();
        jCinemaName.setEnabled(true);
        jCinemaType.setEnabled(true);
    }//GEN-LAST:event_jUpdateButtonActionPerformed

    private void refreshActionButtons() {
        jAddButton.setBackground(selectedAction == 1 ? new Color(247,222,200) : new Color(240,240,240));
        jAddButton.setBorder(selectedAction == 1 ? BorderFactory.createLineBorder(new Color(255,153,0)) : BorderFactory.createLineBorder(new Color(240,240,240)));
        jAddButton.setForeground(selectedAction == 1 ? new Color(0,0,0) : new Color(255,153,0));
        jUpdateButton.setBackground(selectedAction == 2 ? new Color(247,222,200) : new Color(240,240,240));
        jUpdateButton.setBorder(selectedAction == 2 ? BorderFactory.createLineBorder(new Color(255,153,0)) : BorderFactory.createLineBorder(new Color(240,240,240)));
        jUpdateButton.setForeground(selectedAction == 2 ? new Color(0,0,0) : new Color(255,153,0));
        jDeleteButton.setBackground(selectedAction == 3 ? new Color(247,222,200) : new Color(240,240,240));
        jDeleteButton.setBorder(selectedAction == 3 ? BorderFactory.createLineBorder(new Color(255,153,0)) : BorderFactory.createLineBorder(new Color(240,240,240)));
        jDeleteButton.setForeground(selectedAction == 3 ? new Color(0,0,0) : new Color(255,153,0));
        jClearButton.setBackground(selectedAction == 4 ? new Color(247,222,200) : new Color(240,240,240));
        jClearButton.setBorder(selectedAction == 4 ? BorderFactory.createLineBorder(new Color(255,153,0)) : BorderFactory.createLineBorder(new Color(240,240,240)));
        jClearButton.setForeground(selectedAction == 4 ? new Color(0,0,0) : new Color(255,153,0));
    }
    
    private void jAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddButtonActionPerformed
        selectedAction = 1;
        jCancelButton.setEnabled(true);
        jSaveButton.setEnabled(true);
        refreshActionButtons();
        jCinemaName.setEnabled(true);
        jCinemaType.setEnabled(true);
    }//GEN-LAST:event_jAddButtonActionPerformed

    private void jSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveButtonActionPerformed
        if (selectedAction == 1) addCinema();
        if (selectedAction == 2) updateCinema();
        selectedAction = 0;
        refreshActionButtons();
        jCancelButton.setEnabled(false);
        jSaveButton.setEnabled(false);
        jCinemaName.setEnabled(false);
        jCinemaType.setEnabled(false);
    }//GEN-LAST:event_jSaveButtonActionPerformed

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        selectedAction = 0;
        refreshActionButtons();
        jCancelButton.setEnabled(false);
        jSaveButton.setEnabled(false);
        jCinemaName.setEnabled(false);
        jCinemaType.setEnabled(false);
    }//GEN-LAST:event_jCancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddButton;
    private javax.swing.JButton jCancelButton;
    private javax.swing.JTextField jCinemaName;
    private javax.swing.JTable jCinemaTable;
    private javax.swing.JComboBox<String> jCinemaType;
    private javax.swing.JButton jClearButton;
    private javax.swing.JButton jDeleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JButton jSaveButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchbar;
    private javax.swing.JButton jUpdateButton;
    // End of variables declaration//GEN-END:variables
}
