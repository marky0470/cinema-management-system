/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.bookings;

import com.mycompany.cms.models.Ticket;
import com.mycompany.cms.util.Connector;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author francisjamestolentino
 */
public class BookingsTabbedPanel extends javax.swing.JPanel {
    
    final private Connection connection = new Connector().getConnection();
    
    private ArrayList<Ticket> tickets;
    private int selectedMovieId;
    private int selectedScreeningId;
    private int ticketQuantity;
    private Date screeningDate;
    private Time screeningTimeStart;
    private Time screeningTimeEnd;
    private int price;
    private BufferedImage moviePoster;
    private String movieTitle;
    
    BookingsPanel bookingsPanel = new BookingsPanel(this);
    BookingsSchedulesPanel bookingsSchedulesPanel = new BookingsSchedulesPanel(this);
    BookingsSeatPanel bookingsSeatPanel = new BookingsSeatPanel(this);
    BookingsSummaryPanel bookingsSummaryPanel = new BookingsSummaryPanel(this);

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
        this.selectedMovieId = id;
    }
    
    public int getSelectedMovieId() {
        return this.selectedMovieId;
    }
    
    public void setSelectedScreeningId(int id) {
        this.selectedScreeningId = id;
    }
    
    public int getSelectedScreeningId() {
        return this.selectedScreeningId;
    }
    
    public void setTicketQuantity(int quantity) {
        this.ticketQuantity = quantity;
    }
    
    public int getTicketQuantity() {
        return this.ticketQuantity;
    }
    
    public void setMoviePoster(BufferedImage image) {
        this.moviePoster = image;
    }
    
    public BufferedImage getMoviePoster() {
        return this.moviePoster;
    }   
    
    public void setMovieTitle(String title) {
        this.movieTitle = title;
    }
    
    public String getMovieTitle() {
        return this.movieTitle;
    }
    
    public void setScreeningDate(Date screeningDate) {
        this.screeningDate = screeningDate;
    }
    
    public Date getScreeningDate() {
        return this.screeningDate;
    }
    
    public void setScreeningTimeStart(Time timeStart) {
        this.screeningTimeStart = timeStart;
    }
    
    public Time getScreeningTimeStart() {
        return this.screeningTimeStart;
    }
    
    public void setScreeningTimeEnd(Time timeEnd){
        this.screeningTimeEnd = timeEnd;
    }
    
    public Time getScreeningTimeEnd() {
        return this.screeningTimeEnd;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }
    
    public Connection getDBConnection() {
        return this.connection;
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
        bookingsSeatPanel.refresh();
        revalidate();
        repaint();
    }
    
    public void openSummaryTab() {
        setEnabledTabs(3);
        jTabbedPane.setSelectedIndex(3);
        jTabbedPane.revalidate();
        jTabbedPane.repaint();
        bookingsSummaryPanel.refresh();
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
