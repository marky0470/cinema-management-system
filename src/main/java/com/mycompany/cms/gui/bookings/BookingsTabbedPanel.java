/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.bookings;

/**
 *
 * @author francisjamestolentino
 */
public class BookingsTabbedPanel extends javax.swing.JPanel {
    
    public int selectedMovieId;
    
    BookingsPanel bookingsPanel = new BookingsPanel(this);
    BookingsSchedulesPanel bookingsSchedulesPanel = new BookingsSchedulesPanel(this);
    BookingsSeatPanel bookingsSeatPanel = new BookingsSeatPanel();
    BookingsSummaryPanel bookingsSummaryPanel = new BookingsSummaryPanel();

    /**
     * Creates new form BookingsTabbedPanel
     */
    public BookingsTabbedPanel() {
        initComponents();
        
        jTabbedPane.addTab("Movies", bookingsPanel);
        jTabbedPane.addTab("Schedules", bookingsSchedulesPanel);
        jTabbedPane.addTab("Seats", bookingsSeatPanel);
        jTabbedPane.add("Summary", bookingsSummaryPanel);
        
        setEnabledTabs(0);
    }
    
    public void setSelectedMovieId(int id) {
        selectedMovieId = id;
    }
    
    private void setEnabledTabs(int index) {
        for (int i=0; i < jTabbedPane.getTabCount(); i++) {
            if (i == index) {
                jTabbedPane.setEnabledAt(i, true);
            } else {
                jTabbedPane.setEnabledAt(i, false);
            }
        }
    }
    
    public void openMoviesTab() {
        setEnabledTabs(0);
        jTabbedPane.setSelectedIndex(0);
        jTabbedPane.revalidate();
        jTabbedPane.repaint();
        revalidate();
        repaint();
    }
    
    public void openSchedulesTab() {
        setEnabledTabs(1);
        jTabbedPane.setSelectedIndex(1);
        jTabbedPane.revalidate();
        jTabbedPane.repaint();
        bookingsSchedulesPanel.refresh();
        revalidate();
        repaint();
    }
    
    public void openSeatsTab() {
        setEnabledTabs(2);
        jTabbedPane.setSelectedIndex(2);
        jTabbedPane.revalidate();
        jTabbedPane.repaint();
        revalidate();
        repaint();
    }
    
    public void openSummaryTab() {
        setEnabledTabs(3);
        jTabbedPane.setSelectedIndex(3);
        jTabbedPane.revalidate();
        jTabbedPane.repaint();
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();

        setMinimumSize(new java.awt.Dimension(1140, 800));
        setPreferredSize(new java.awt.Dimension(1140, 800));
        setSize(new java.awt.Dimension(1140, 800));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane.setMinimumSize(new java.awt.Dimension(1140, 800));
        jTabbedPane.setPreferredSize(new java.awt.Dimension(1140, 800));
        jTabbedPane.setSize(new java.awt.Dimension(1140, 800));
        add(jTabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane;
    // End of variables declaration//GEN-END:variables
}
