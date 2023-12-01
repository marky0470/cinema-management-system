/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.bookings;

import com.mycompany.cms.util.Connector;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
        int id = this.panel.selectedMovieId;
        getMovieDetails(id);
    }
    
    private void getMovieDetails(int id) {
        try {
            String sql = """
                        SELECT
                            *,
                            220 - COUNT(ticket_id) as available_seats
                        FROM
                            (
                            SELECT
                                screening.screening_id,
                                movies.title,
                                screening.price,
                                movies.image,
                                screening.movie_id,
                                screening.time_start,
                                screening.time_end,
                                screening.date_start,
                                screening.date_end,
                                cinemas.name,
                                cinemas.type,
                                tickets.ticket_id
                            FROM
                                (
                                    (
                                        screening
                                    LEFT JOIN movies ON screening.movie_id = movies.movie_id
                                    LEFT JOIN cinemas ON screening.cinema_id = cinemas.cinema_id
                                    )
                                LEFT JOIN tickets ON screening.screening_id = tickets.screening_id
                                )
                        ) screening
                        WHERE
                            screening.movie_id = ? AND(
                                ? BETWEEN screening.date_start AND screening.date_end
                            )
                        GROUP BY
                            screening.screening_id;;
                         """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, new java.sql.Date(new Date().getTime()));
            ResultSet result = preparedStatement.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel(new String[] {"Time Start", "Time End", "Cinema", "Type", "Available Seats"}, 0);
                       
            int index = 0;
            while(result.next()) {
                if (index == 0) {
                    String title = result.getString("title");
                    BufferedImage im = ImageIO.read(result.getBinaryStream("image"));
                    
                    ImageIcon imageIcon = new ImageIcon(im);
                    
                    int panelWidth = jPosterPanel.getWidth();
                    int panelHeight = jPosterPanel.getHeight();
                    
                    Image scaledImage = imageIcon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
                    
                    JLabel poster = new JLabel(new ImageIcon(scaledImage));
                    jPosterPanel.removeAll();
                    jPosterPanel.add(poster);
                    jTitleLabel.setText(title);
                }
                
                Time timeStart = result.getTime("time_start");
                Time timeEnd = result.getTime("time_end");
                int cinema = result.getInt("name");
                String type = result.getString("type");
                int availableSeats = result.getInt("available_seats");
                
                
                model.addRow(new Object[] {timeStart, timeEnd, cinema, type, availableSeats});
                index++;
            }
            
            jTable1.setModel(model);
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (IOException ex) {
            Logger.getLogger(BookingsSchedulesPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        jPosterPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
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
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
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
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setRowHeight(25);
        jTable1.setRowSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Quantity");

        jQuantityTextField.setPreferredSize(new java.awt.Dimension(78, 30));
        jQuantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQuantityTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jQuantityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jContinueButton.setText("Continue");
        jContinueButton.setPreferredSize(new java.awt.Dimension(150, 35));
        jContinueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jContinueButtonActionPerformed(evt);
            }
        });

        jBackButton.setText("Back");
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
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(55, Short.MAX_VALUE))
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
        
        if (quantity.isBlank() || quantity.isEmpty() || quantity.matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(this, "Invalid Quantity! Please make sure quantity is valid", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        this.panel.openSeatsTab();
    }//GEN-LAST:event_jContinueButtonActionPerformed

    private void jQuantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQuantityTextFieldActionPerformed

    }//GEN-LAST:event_jQuantityTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBackButton;
    private javax.swing.JButton jContinueButton;
    private javax.swing.JLabel jLabel1;
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
