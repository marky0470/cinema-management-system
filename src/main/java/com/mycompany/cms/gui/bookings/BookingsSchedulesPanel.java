/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.bookings;

import com.mycompany.cms.util.Connector;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author francisjamestolentino
 */
public class BookingsSchedulesPanel extends javax.swing.JPanel {

    private final BookingsTabbedPanel panel;
    Connection conn;

    /**
     * Creates new form BookingsSchedulesPanel
     * @param panel
     */
    public BookingsSchedulesPanel(BookingsTabbedPanel panel) {
        initComponents();
        this.panel = panel;
        
        Connector connector = new Connector();
        conn = connector.getConnection();
        
        refresh();
    }
    
    public void refresh() {
        int id = this.panel.getSelectedMovieId();
        
        displayMovieDetails();
        getMovieScreenings(id);
    }
    
    private void displayMovieDetails() {
        BufferedImage image = this.panel.getMoviePoster();
        Date screeningDate = this.panel.getScreeningDate();
        String title = this.panel.getMovieTitle();
        
        if (image != null) {        
            ImageIcon imageIcon = new ImageIcon(image);

            int panelWidth = jPosterPanel.getWidth();
            int panelHeight = jPosterPanel.getHeight();

            Image scaledImage = imageIcon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            JLabel poster = new JLabel(new ImageIcon(scaledImage));

            jPosterPanel.removeAll();
            jPosterPanel.add(poster);
            jTitleLabel.setText(title);
            jDateTextField.setText(screeningDate.toString());
        }
        if (title != null) jTitleLabel.setText(title);
        if (screeningDate != null) jDateTextField.setText(screeningDate.toString());
    }
    
    private void getMovieScreenings(int id) {
        try {
            Date screeningDate = this.panel.getScreeningDate();
            if (screeningDate == null) return;
            String sql = """
                        SELECT
                            	screening.*,
                                movies.*,
                                cinemas.*, 
                                tickets.*,
                                220 - COUNT(tickets.ticket_id) as available_seats
                            FROM 
                            	screening
                            JOIN movies ON screening.movie_id = movies.movie_id
                            JOIN cinemas ON screening.cinema_id = cinemas.cinema_id
                            LEFT JOIN tickets ON screening.screening_id = tickets.screening_id
                            WHERE screening.movie_id = ? AND screening.date = ?
                            GROUP BY time_start, time_end
                            ORDER BY screening.screening_id;
                         """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, new java.sql.Date(screeningDate.getTime()));
            ResultSet result = preparedStatement.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel(new String[] {"Screening ID", "Time Start", "Time End", "Cinema", "Type", "Available Seats", "Price"}, 0);
            
            int index = 0;
            while(result.next()) {
                int screeningId = result.getInt("screening_id");
                Time timeStart = result.getTime("time_start");
                Time timeEnd = result.getTime("time_end");
                int cinema = result.getInt("name");
                String type = result.getString("type");
                int availableSeats = result.getInt("available_seats");
                int price = result.getInt("price");
                
                
                model.addRow(new Object[] {screeningId, timeStart, timeEnd, cinema, type, availableSeats, price});
                index++;
            }
            
            jTable1.setModel(model);
            
            for (int i=0; i<model.getColumnCount(); i++) {
                Class<?> c = model.getColumnClass(i);
                jTable1.setDefaultEditor(c, null);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
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
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTitleLabel = new javax.swing.JLabel();
        jPosterPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jQuantityTextField = new javax.swing.JTextField();
        jDateTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jContinueButton = new javax.swing.JButton();
        jBackButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(940, 600));
        setPreferredSize(new java.awt.Dimension(940, 600));
        setSize(new java.awt.Dimension(940, 600));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1140, 800));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setPreferredSize(new java.awt.Dimension(400, 600));
        jPanel6.setSize(new java.awt.Dimension(450, 600));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel3.setText("Screening Schedules");

        jTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTitleLabel.setText("Movie Title");

        jPosterPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        jPosterPanel.setForeground(new java.awt.Color(200, 200, 200));
        jPosterPanel.setPreferredSize(new java.awt.Dimension(405, 405));
        jPosterPanel.setRequestFocusEnabled(false);
        jPosterPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPosterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addComponent(jTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPosterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel7.setPreferredSize(new java.awt.Dimension(594, 600));
        jPanel7.setSize(new java.awt.Dimension(594, 600));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Quantity:");

        jQuantityTextField.setPreferredSize(new java.awt.Dimension(78, 30));

        jDateTextField.setText("jTextField1");
        jDateTextField.setFocusable(false);

        jLabel2.setText("Date:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateTextField)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jContinueButton.setBackground(new java.awt.Color(239, 124, 18));
        jContinueButton.setForeground(new java.awt.Color(242, 242, 242));
        jContinueButton.setText("Continue");
        jContinueButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(239, 124, 18), 1, true));
        jContinueButton.setPreferredSize(new java.awt.Dimension(150, 35));
        jContinueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jContinueButtonActionPerformed(evt);
            }
        });

        jBackButton.setBackground(new java.awt.Color(242, 242, 242));
        jBackButton.setForeground(new java.awt.Color(239, 124, 18));
        jBackButton.setText("Back");
        jBackButton.setBorder(null);
        jBackButton.setPreferredSize(new java.awt.Dimension(150, 35));
        jBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBackButtonActionPerformed
        this.panel.openMoviesTab();
    }//GEN-LAST:event_jBackButtonActionPerformed

    private void jContinueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jContinueButtonActionPerformed
        String quantity = jQuantityTextField.getText();
        
        int row = jTable1.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a screening schedule first", "Select Schedule", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (quantity.isBlank() || quantity.isEmpty() || quantity.matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(this, "Invalid Quantity! Please make sure quantity is valid", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedScreeningId = (int) jTable1.getValueAt(row, 0);
        Time timeStart = (Time) jTable1.getValueAt(row, 1);
        Time timeEnd = (Time) jTable1.getValueAt(row, 2);
        int price = (int) jTable1.getValueAt(row, 6);
        
        jQuantityTextField.setText("");
        this.panel.setSelectedScreeningId(selectedScreeningId);
        this.panel.setTicketQuantity(Integer.parseInt(quantity));
        this.panel.setScreeningTimeStart(timeStart);
        this.panel.setScreeningTimeEnd(timeEnd);
        this.panel.setPrice(price);
        this.panel.openSeatsTab();
    }//GEN-LAST:event_jContinueButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBackButton;
    private javax.swing.JButton jContinueButton;
    private javax.swing.JTextField jDateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPosterPanel;
    private javax.swing.JTextField jQuantityTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jTitleLabel;
    // End of variables declaration//GEN-END:variables
}
