/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cms.gui;

import com.mycompany.cms.gui.movies.MoviesPanel;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.cms.gui.bookings.BookingsPanel;
import com.mycompany.cms.gui.bookings.BookingsTabbedPanel;
import com.mycompany.cms.gui.cinemas.CinemasPanel;
import com.mycompany.cms.gui.movies.MoviePanel;
import com.mycompany.cms.gui.screenings.ScreeningsPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author marks
 */
public final class MainFormStaff extends javax.swing.JFrame {

    static private JLabel previousActive = null;
    
    /**
     * Creates new form JLoginFrame
     */
    public MainFormStaff() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        jBookingsButton.addMouseListener(getMouseAdapter(jBookingsButton));
        jScreeningsButton.addMouseListener(getMouseAdapter(jScreeningsButton));
        jLogOutButton.addMouseListener(getMouseAdapter(jLogOutButton));
        
        BookingsTabbedPanel bookingsPanel = new BookingsTabbedPanel();
        showPanel(bookingsPanel, jBookingsButton);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jNavbarPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jBookingsButton = new javax.swing.JLabel();
        jScreeningsButton = new javax.swing.JLabel();
        jScreeningsButton1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLogOutButton = new javax.swing.JLabel();
        jContentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1440, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jNavbarPanel.setBackground(new java.awt.Color(239, 124, 18));
        jNavbarPanel.setAlignmentX(0.0F);
        jNavbarPanel.setAlignmentY(0.0F);
        jNavbarPanel.setMinimumSize(new java.awt.Dimension(300, 800));
        jNavbarPanel.setPreferredSize(new java.awt.Dimension(300, 800));
        jNavbarPanel.setSize(new java.awt.Dimension(300, 800));
        jNavbarPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 0));

        jPanel2.setBackground(new java.awt.Color(239, 124, 18));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 180));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cinema Management");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("System");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7)))
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(56, 56, 56))
        );

        jNavbarPanel.add(jPanel2);

        jBookingsButton.setBackground(new java.awt.Color(239, 124, 18));
        jBookingsButton.setForeground(new java.awt.Color(255, 255, 255));
        jBookingsButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jBookingsButton.setText("Bookings");
        jBookingsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBookingsButton.setOpaque(true);
        jBookingsButton.setPreferredSize(new java.awt.Dimension(300, 45));
        jBookingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBookingsButtonMouseClicked(evt);
            }
        });
        jNavbarPanel.add(jBookingsButton);

        jScreeningsButton.setBackground(new java.awt.Color(239, 124, 18));
        jScreeningsButton.setForeground(new java.awt.Color(255, 255, 255));
        jScreeningsButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScreeningsButton.setText("Screenings");
        jScreeningsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScreeningsButton.setOpaque(true);
        jScreeningsButton.setPreferredSize(new java.awt.Dimension(300, 45));
        jScreeningsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScreeningsButtonMouseClicked(evt);
            }
        });
        jNavbarPanel.add(jScreeningsButton);

        jScreeningsButton1.setBackground(new java.awt.Color(239, 124, 18));
        jScreeningsButton1.setForeground(new java.awt.Color(255, 255, 255));
        jScreeningsButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScreeningsButton1.setText("Settings");
        jScreeningsButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScreeningsButton1.setOpaque(true);
        jScreeningsButton1.setPreferredSize(new java.awt.Dimension(300, 45));
        jScreeningsButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScreeningsButton1MouseClicked(evt);
            }
        });
        jNavbarPanel.add(jScreeningsButton1);

        jPanel1.setBackground(new java.awt.Color(239, 124, 18));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 430));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        jNavbarPanel.add(jPanel1);

        jLogOutButton.setBackground(new java.awt.Color(239, 124, 18));
        jLogOutButton.setForeground(new java.awt.Color(255, 255, 255));
        jLogOutButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLogOutButton.setText("Log Out");
        jLogOutButton.setAlignmentY(0.0F);
        jLogOutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLogOutButton.setOpaque(true);
        jLogOutButton.setPreferredSize(new java.awt.Dimension(300, 45));
        jLogOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogOutButtonMouseClicked(evt);
            }
        });
        jNavbarPanel.add(jLogOutButton);

        getContentPane().add(jNavbarPanel, java.awt.BorderLayout.WEST);

        jContentPanel.setMinimumSize(new java.awt.Dimension(1140, 800));
        jContentPanel.setPreferredSize(new java.awt.Dimension(1140, 800));
        jContentPanel.setSize(new java.awt.Dimension(1140, 800));
        jContentPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jContentPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jBookingsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBookingsButtonMouseClicked
//        BookingsPanel bookingsPanel = new BookingsPanel();
        BookingsTabbedPanel bookingsPanel = new BookingsTabbedPanel();
        showPanel(bookingsPanel, jBookingsButton);
    }//GEN-LAST:event_jBookingsButtonMouseClicked

    private void jScreeningsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScreeningsButtonMouseClicked
        ScreeningsPanel screeningsPanel = new ScreeningsPanel();
        showPanel(screeningsPanel, jScreeningsButton);
    }//GEN-LAST:event_jScreeningsButtonMouseClicked

    private void jScreeningsButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScreeningsButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScreeningsButton1MouseClicked

    private void jLogOutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogOutButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_jLogOutButtonMouseClicked

    private static MouseAdapter getMouseAdapter(JLabel label) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (previousActive != label) {
                    label.setBackground(new Color(242,242,242));
                    label.setForeground(new Color(239, 124, 18));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (previousActive != label) {
                    label.setBackground(new Color(239, 124, 18));
                    label.setForeground(Color.WHITE);
                }
            }
        };
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            
            MainFormStaff mainWindow = new MainFormStaff();
            
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.pack();
            mainWindow.setVisible(true);
//            MoviesPanel moviesPanel = new MoviesPanel();
//            mainWindow.showPanel(moviesPanel);
        });
    }
    
    public void showPanel(JPanel panel, JLabel labelButton) {
        labelButton.setBackground(new Color(242,242,242));
        labelButton.setForeground(new Color(239, 124, 18));
        jContentPanel.removeAll();
        jContentPanel.add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
        
        if (previousActive != null && previousActive != labelButton) {
            previousActive.setBackground(new Color(239, 124, 18));
            previousActive.setForeground(Color.WHITE);
        }
        
        previousActive = labelButton;
    }
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBookingsButton;
    private javax.swing.JPanel jContentPanel;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLogOutButton;
    private javax.swing.JPanel jNavbarPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jScreeningsButton;
    private javax.swing.JLabel jScreeningsButton1;
    // End of variables declaration//GEN-END:variables
}
