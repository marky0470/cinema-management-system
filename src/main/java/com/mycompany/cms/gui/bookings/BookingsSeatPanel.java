/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.bookings;

import com.mycompany.cms.models.Ticket;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author francisjamestolentino
 */
public class BookingsSeatPanel extends javax.swing.JPanel {

    private final BookingsTabbedPanel panel;
    private Connection conn;
    private HashMap<String, Component> seats = new HashMap();
    final private ArrayList<String> selectedSeats = new ArrayList();
    final private ArrayList<String> unavailableSeats = new ArrayList();
    
    /**
     * Creates new form BookingsSeatPanel
     * @param panel
     */
    public BookingsSeatPanel(BookingsTabbedPanel panel) {
        initComponents();
        this.panel = panel;
        this.conn = panel.getDBConnection();
        
        refresh();
    }
    
    public void createRow(JPanel panel, String title) {
        panel.removeAll();
        JPanel leftLevel = new JPanel();
        leftLevel.setPreferredSize(new Dimension(28, 28));
        leftLevel.add(new JLabel(title));
        panel.add(leftLevel);
        
        for (int i=0; i<20; i++) {
            JPanel seat = new JPanel();
            String seatTitle = title + String.valueOf(i+1);
            seat.setCursor(new Cursor(Cursor.HAND_CURSOR));
            seat.setPreferredSize(new Dimension(28, 28));
            seat.setBackground(new Color(200, 200, 200));
            seat.setLayout(new CardLayout());
            seat.addMouseListener(getSeatMouseListener(seatTitle));
            
            JLabel seatNumber = new JLabel(String.valueOf(i+1));
            seatNumber.setHorizontalAlignment(SwingConstants.CENTER);
            seatNumber.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
            
            seat.add(seatNumber);
            panel.add(seat);
            
            seats.put(seatTitle, seat);
        }
        
        JPanel rightLevel = new JPanel();
        rightLevel.setPreferredSize(new Dimension(28, 28));
        rightLevel.add(new JLabel(title));
        panel.add(rightLevel);
    }
    
    public void refresh() {
        unavailableSeats.clear();
        selectedSeats.clear();
        seats.clear();
        jSelectedSeatsTextField.setText("");
        createRow(A, "A");
        createRow(B, "B");
        createRow(C, "C");
        createRow(D, "D");
        createRow(E, "E");
        createRow(F, "F");
        createRow(G, "G");
        createRow(H, "H");
        createRow(I, "I");
        createRow(J, "J");
        createRow(K, "K");
        displayMovieDetails();
        getAvailableSeats();
        displayAvailableSeats();
    }
    
    private void displayMovieDetails() {
        BufferedImage image = this.panel.getMoviePoster();
        
        System.out.println(image);
        
        if (image != null) {        
            ImageIcon imageIcon = new ImageIcon(image);

            int panelWidth = jPosterPanel.getWidth();
            int panelHeight = jPosterPanel.getHeight();

            Image scaledImage = imageIcon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            JLabel poster = new JLabel(new ImageIcon(scaledImage));

            jPosterPanel.removeAll();
            jPosterPanel.add(poster, BorderLayout.CENTER);
            jMovieTitle.setText(this.panel.getMovieTitle());
            jQuantityTextField.setText(String.valueOf(this.panel.getTicketQuantity()));
            jDateTextField.setText(this.panel.getScreeningDate().toString());
            jTimeStartTextField.setText(this.panel.getScreeningTimeStart().toString());
            jTimeEndTextField.setText(this.panel.getScreeningTimeEnd().toString());
        }
        
    }
    
    private void getAvailableSeats() {
        try {
            int screeningId = this.panel.getSelectedScreeningId();
            String sql = """
                         SELECT 
                            DISTINCT(seat),
                             date
                         FROM
                            (
                            SELECT
                                 screening.screening_id,
                                 tickets.seat,
                                 tickets.date,
                                 tickets.ticket_id
                             FROM 
                                 screening
                             LEFT JOIN tickets ON screening.screening_id = tickets.screening_id
                             ) screening
                         WHERE 
                            screening.screening_id = ?;
                         """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, screeningId);
            
            ResultSet result = preparedStatement.executeQuery();
            
            while(result.next()) {
                String name = result.getString("seat");
                unavailableSeats.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingsSeatPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayAvailableSeats() {
        for (String unavailableSeat : unavailableSeats) {
            JPanel seat = (JPanel) seats.get(unavailableSeat);
            if (seat == null) return;
            seat.setBackground(Color.RED);
        }
    }
    
    private void addSeatNumberToSelection(String seatNumber) {
        JPanel seat = (JPanel) seats.get(seatNumber);
        seat.setBackground(Color.YELLOW);
        selectedSeats.add(seatNumber);
        jSelectedSeatsTextField.setText(String.join(", ", selectedSeats));
    }
    
    private void removeSeatNumberFromSelection(String seatNumber) {
        JPanel seat = (JPanel) seats.get(seatNumber);
        seat.setBackground(new Color(200,200,200));
        selectedSeats.remove(selectedSeats.indexOf(seatNumber));
        jSelectedSeatsTextField.setText(String.join(", ", selectedSeats));
    }
    
    private MouseListener getSeatMouseListener(String seatNumber) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int quantity = BookingsSeatPanel.this.panel.getTicketQuantity();
                if (unavailableSeats.contains(seatNumber)) return;
                if (selectedSeats.contains(seatNumber)) {
                    removeSeatNumberFromSelection(seatNumber);
                    return;
                }
                if (selectedSeats.size() >= quantity) return;
                addSeatNumberToSelection(seatNumber);
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel26 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPosterPanel = new javax.swing.JPanel();
        jMovieTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jQuantityTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSelectedSeatsTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateTextField = new javax.swing.JTextField();
        jTimeStartTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTimeEndTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel233 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel234 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        A = new javax.swing.JPanel();
        B = new javax.swing.JPanel();
        C = new javax.swing.JPanel();
        D = new javax.swing.JPanel();
        E = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        F = new javax.swing.JPanel();
        G = new javax.swing.JPanel();
        H = new javax.swing.JPanel();
        I = new javax.swing.JPanel();
        J = new javax.swing.JPanel();
        K = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jContinueButton = new javax.swing.JButton();
        jBackButton = new javax.swing.JButton();
        jClearButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(300, 733));

        jPosterPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPosterPanel.setPreferredSize(new java.awt.Dimension(250, 350));
        jPosterPanel.setLayout(new java.awt.BorderLayout());

        jMovieTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMovieTitle.setText("Movie Title");

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Seat Selection");

        jQuantityTextField.setFocusable(false);
        jQuantityTextField.setPreferredSize(new java.awt.Dimension(200, 20));
        jQuantityTextField.setSize(new java.awt.Dimension(200, 20));

        jLabel2.setText("Quantity: ");

        jSelectedSeatsTextField.setFocusable(false);

        jLabel3.setText("Selected Seats:");

        jLabel4.setText("Date: ");

        jDateTextField.setText("jTextField1");
        jDateTextField.setFocusable(false);
        jDateTextField.setPreferredSize(new java.awt.Dimension(200, 30));

        jTimeStartTextField.setText("jTextField1");
        jTimeStartTextField.setFocusable(false);
        jTimeStartTextField.setPreferredSize(null);

        jLabel5.setText("Time start");

        jTimeEndTextField.setText("jTextField1");
        jTimeEndTextField.setFocusable(false);
        jTimeEndTextField.setPreferredSize(null);

        jLabel6.setText("Time end");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jSelectedSeatsTextField)
                    .addComponent(jLabel2)
                    .addComponent(jQuantityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMovieTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPosterPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTimeStartTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTimeEndTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPosterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMovieTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTimeEndTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTimeStartTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSelectedSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel3.setPreferredSize(new java.awt.Dimension(500, 733));
        jPanel3.setSize(new java.awt.Dimension(500, 733));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1067, 712));
        jPanel1.setSize(new java.awt.Dimension(1067, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setPreferredSize(new java.awt.Dimension(50, 712));
        jPanel6.setLayout(new java.awt.CardLayout(3, 5));

        jPanel233.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Stairs");

        javax.swing.GroupLayout jPanel233Layout = new javax.swing.GroupLayout(jPanel233);
        jPanel233.setLayout(jPanel233Layout);
        jPanel233Layout.setHorizontalGroup(
            jPanel233Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel233Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel233Layout.setVerticalGroup(
            jPanel233Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel233Layout.createSequentialGroup()
                .addContainerGap(317, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel233, "card2");

        jPanel1.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel7.setPreferredSize(new java.awt.Dimension(50, 712));
        jPanel7.setLayout(new java.awt.CardLayout(3, 5));

        jPanel234.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Stairs");

        javax.swing.GroupLayout jPanel234Layout = new javax.swing.GroupLayout(jPanel234);
        jPanel234.setLayout(jPanel234Layout);
        jPanel234Layout.setHorizontalGroup(
            jPanel234Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel234Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel234Layout.setVerticalGroup(
            jPanel234Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel234Layout.createSequentialGroup()
                .addContainerGap(317, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel234, "card2");

        jPanel1.add(jPanel7, java.awt.BorderLayout.EAST);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setMaximumSize(new java.awt.Dimension(1600, 40));
        jPanel10.setMinimumSize(new java.awt.Dimension(761, 30));
        jPanel10.setPreferredSize(new java.awt.Dimension(761, 60));
        jPanel10.setRequestFocusEnabled(false);
        jPanel10.setSize(new java.awt.Dimension(761, 555));
        jPanel10.setLayout(new java.awt.CardLayout());

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("SCREEN");
        jPanel10.add(jLabel20, "card2");

        jPanel5.add(jPanel10);

        jPanel11.setMaximumSize(new java.awt.Dimension(1600, 40));
        jPanel11.setMinimumSize(new java.awt.Dimension(761, 30));
        jPanel11.setPreferredSize(new java.awt.Dimension(761, 60));
        jPanel11.setRequestFocusEnabled(false);
        jPanel11.setSize(new java.awt.Dimension(761, 555));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel11);

        A.setMaximumSize(new java.awt.Dimension(1600, 40));
        A.setMinimumSize(new java.awt.Dimension(761, 30));
        A.setPreferredSize(new java.awt.Dimension(761, 60));
        A.setRequestFocusEnabled(false);
        A.setSize(new java.awt.Dimension(761, 555));
        A.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(A);

        B.setMaximumSize(new java.awt.Dimension(1600, 40));
        B.setMinimumSize(new java.awt.Dimension(761, 30));
        B.setPreferredSize(new java.awt.Dimension(761, 60));
        B.setRequestFocusEnabled(false);
        B.setSize(new java.awt.Dimension(761, 555));
        B.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(B);

        C.setMaximumSize(new java.awt.Dimension(1600, 40));
        C.setMinimumSize(new java.awt.Dimension(761, 30));
        C.setPreferredSize(new java.awt.Dimension(761, 60));
        C.setRequestFocusEnabled(false);
        C.setSize(new java.awt.Dimension(761, 555));
        C.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(C);

        D.setMaximumSize(new java.awt.Dimension(1600, 40));
        D.setMinimumSize(new java.awt.Dimension(761, 30));
        D.setPreferredSize(new java.awt.Dimension(761, 60));
        D.setRequestFocusEnabled(false);
        D.setSize(new java.awt.Dimension(761, 555));
        D.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(D);

        E.setMaximumSize(new java.awt.Dimension(1600, 40));
        E.setMinimumSize(new java.awt.Dimension(761, 30));
        E.setPreferredSize(new java.awt.Dimension(761, 60));
        E.setRequestFocusEnabled(false);
        E.setSize(new java.awt.Dimension(761, 555));
        E.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(E);

        jPanel17.setMaximumSize(new java.awt.Dimension(1600, 40));
        jPanel17.setMinimumSize(new java.awt.Dimension(761, 30));
        jPanel17.setPreferredSize(new java.awt.Dimension(761, 60));
        jPanel17.setRequestFocusEnabled(false);
        jPanel17.setSize(new java.awt.Dimension(761, 555));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel17);

        F.setMaximumSize(new java.awt.Dimension(1600, 40));
        F.setMinimumSize(new java.awt.Dimension(761, 30));
        F.setPreferredSize(new java.awt.Dimension(761, 60));
        F.setRequestFocusEnabled(false);
        F.setSize(new java.awt.Dimension(761, 555));
        F.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(F);

        G.setMaximumSize(new java.awt.Dimension(1600, 40));
        G.setMinimumSize(new java.awt.Dimension(761, 30));
        G.setPreferredSize(new java.awt.Dimension(761, 60));
        G.setRequestFocusEnabled(false);
        G.setSize(new java.awt.Dimension(761, 555));
        G.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(G);

        H.setMaximumSize(new java.awt.Dimension(1600, 40));
        H.setMinimumSize(new java.awt.Dimension(761, 30));
        H.setPreferredSize(new java.awt.Dimension(761, 60));
        H.setRequestFocusEnabled(false);
        H.setSize(new java.awt.Dimension(761, 555));
        H.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(H);

        I.setMaximumSize(new java.awt.Dimension(1600, 40));
        I.setMinimumSize(new java.awt.Dimension(761, 30));
        I.setPreferredSize(new java.awt.Dimension(761, 60));
        I.setRequestFocusEnabled(false);
        I.setSize(new java.awt.Dimension(761, 555));
        I.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(I);

        J.setMaximumSize(new java.awt.Dimension(1600, 40));
        J.setMinimumSize(new java.awt.Dimension(761, 30));
        J.setPreferredSize(new java.awt.Dimension(761, 60));
        J.setRequestFocusEnabled(false);
        J.setSize(new java.awt.Dimension(761, 555));
        J.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(J);

        K.setMaximumSize(new java.awt.Dimension(1600, 40));
        K.setMinimumSize(new java.awt.Dimension(761, 30));
        K.setPreferredSize(new java.awt.Dimension(761, 60));
        K.setRequestFocusEnabled(false);
        K.setSize(new java.awt.Dimension(761, 555));
        K.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        jPanel5.add(K);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(805, 80));

        jContinueButton.setBackground(new java.awt.Color(239, 124, 18));
        jContinueButton.setForeground(new java.awt.Color(242, 242, 242));
        jContinueButton.setText("Continue");
        jContinueButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 124, 18)));
        jContinueButton.setPreferredSize(new java.awt.Dimension(130, 35));
        jContinueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jContinueButtonActionPerformed(evt);
            }
        });

        jBackButton.setBackground(new java.awt.Color(242, 242, 242));
        jBackButton.setForeground(new java.awt.Color(239, 124, 18));
        jBackButton.setText("Back");
        jBackButton.setBorder(null);
        jBackButton.setPreferredSize(new java.awt.Dimension(130, 35));
        jBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBackButtonActionPerformed(evt);
            }
        });

        jClearButton.setBackground(new java.awt.Color(247, 222, 200));
        jClearButton.setText("Clear");
        jClearButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 222, 200), 1, true));
        jClearButton.setPreferredSize(new java.awt.Dimension(130, 35));
        jClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 459, Short.MAX_VALUE)
                .addComponent(jBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.SOUTH);

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBackButtonActionPerformed
        this.panel.openSchedulesTab();
    }//GEN-LAST:event_jBackButtonActionPerformed

    private void jContinueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jContinueButtonActionPerformed
        if (selectedSeats.size() < this.panel.getTicketQuantity()) {
            JOptionPane.showMessageDialog(this, "Seat selection is incomplete!", "Seat Selection Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ArrayList<Ticket> tickets = new ArrayList();
        Time time = this.panel.getScreeningTimeStart();
        Date date = this.panel.getScreeningDate();
        int screeningId = this.panel.getSelectedScreeningId();
        float price = this.panel.getPrice();
        
        for (String selectedSeat : selectedSeats) {
            Ticket newTicket = new Ticket(time, date, screeningId, selectedSeat, price);
            System.out.println(newTicket.getSeat());
            tickets.add(newTicket);
        }
        
        this.panel.setTickets(tickets);
        this.panel.openSummaryTab();
    }//GEN-LAST:event_jContinueButtonActionPerformed

    private void jClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearButtonActionPerformed
        int result = (int) JOptionPane.showConfirmDialog(this, "Are you sure you want clear the selection?", "Clear Seat Selection", JOptionPane.WARNING_MESSAGE);
        System.out.println(result);
        if (result == -1 || result == 2) return;
        for (String selectedSeat : selectedSeats) {
            JPanel seat = (JPanel) seats.get(selectedSeat);
            seat.setBackground(new Color(200,200,200));
        }
        selectedSeats.clear();
        jSelectedSeatsTextField.setText("");
    }//GEN-LAST:event_jClearButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel A;
    private javax.swing.JPanel B;
    private javax.swing.JPanel C;
    private javax.swing.JPanel D;
    private javax.swing.JPanel E;
    private javax.swing.JPanel F;
    private javax.swing.JPanel G;
    private javax.swing.JPanel H;
    private javax.swing.JPanel I;
    private javax.swing.JPanel J;
    private javax.swing.JPanel K;
    private javax.swing.JButton jBackButton;
    private javax.swing.JButton jClearButton;
    private javax.swing.JButton jContinueButton;
    private javax.swing.JTextField jDateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jMovieTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel233;
    private javax.swing.JPanel jPanel234;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPosterPanel;
    private javax.swing.JTextField jQuantityTextField;
    private javax.swing.JTextField jSelectedSeatsTextField;
    private javax.swing.JTextField jTimeEndTextField;
    private javax.swing.JTextField jTimeStartTextField;
    // End of variables declaration//GEN-END:variables
}
