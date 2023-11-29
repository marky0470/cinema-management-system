/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.bookings;

import com.mycompany.cms.util.Connector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author francisjamestolentino
 */
public class BookingsPanel extends javax.swing.JPanel {
    
    Connection conn;
    SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);

    /**
     * Creates new form BookingsPanel
     */
    public BookingsPanel() {
        initComponents();
        
        Connector connector = new Connector();
        conn = connector.getConnection();
        
        try {
//            SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
            jDateSpinner.setModel(model);
            JSpinner.DateEditor editor = new JSpinner.DateEditor(jDateSpinner, "MM/dd/yyyy");
            jDateSpinner.setEditor(editor);
            jDateSpinner.addChangeListener(getDateSpinnerChangeListener());
            
            changeDateLabel();
            getScreenings();    
        } catch (IOException e) {
            
        }
        
    }
    
    private void changeDateLabel() {
        Date selectedDate = (Date) getSelectedDate();
        jDateLabel.setText(selectedDate.toString());
    }
    
    private void getScreenings() throws IOException {
        try {
            Date date = getSelectedDate();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String sql = """
                        SELECT 
                            DISTINCT(title),
                            image, 
                            movie_id
                        FROM 
                            (
                                SELECT 
                                    screening.date_start,
                                    screening.date_end,
                                    movies.movie_id,
                                    movies.title,
                                    movies.image
                                FROM 
                                    screening
                                LEFT JOIN movies on screening.movie_id = movies.movie_id
                            ) screening
                        WHERE
                            ? BETWEEN screening.date_start AND screening.date_end;""";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDate(1, sqlDate);
            ResultSet res = st.executeQuery();
            
            jMoviesPanel.removeAll();
            jMoviesPanel.setLayout(new BoxLayout(jMoviesPanel, BoxLayout.Y_AXIS));
            jMoviesPanel.setBackground(Color.WHITE);
                     
            int index = 0;
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
            
            while (res.next()) {
                String title = res.getString("title");
                int movieId = res.getInt("movie_id");
                BufferedImage im = ImageIO.read(res.getBinaryStream("image"));
                JPanel card = createCardPanel(movieId, title, im);
                panel.add(card);
                
                index++;
                
                if (index == 5) {
                    jMoviesPanel.add(panel);
                    index = 0;
                    panel = new JPanel();
                    panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
                }
            }
            
            jMoviesPanel.add(panel);
            
            jMoviesPanel.revalidate();
            jMoviesPanel.repaint();
            
            revalidate();
            repaint();
        } catch  (SQLException e) {
            System.out.println(e);
        }
    }
    
    private static JPanel createCardPanel(int movieId, String title, BufferedImage image) {
        JPanel cardPanel = new JPanel();
        
        cardPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(45, 45, 45, 20), 1, true),
            new EmptyBorder(1, 1, 1, 1)
        ));
        cardPanel.setPreferredSize(new Dimension(200, 350));
        cardPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(180, 300));
        JLabel poster = new JLabel(new ImageIcon(image));
        card.add(poster);
        cardPanel.add(card);
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(180, 20));
        cardPanel.add(titleLabel);
        
        cardPanel.addMouseListener(getMouseListener(movieId, title, cardPanel));

        return cardPanel;
    }
    
    private Date getSelectedDate() {
        Date selectedDate = (Date) jDateSpinner.getValue();
        return selectedDate;
    }
    
    private ChangeListener getDateSpinnerChangeListener() {
        return (ChangeEvent e) -> {
            try  {
               changeDateLabel();
               getScreenings();
            } catch (IOException ioException) {
                //
            }
        };
    }
    
    static private MouseAdapter getMouseListener(int id, String title, JPanel card) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BookingsSchedulesForm schedulesForm = new BookingsSchedulesForm(id, title);
                schedulesForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                schedulesForm.setVisible(true);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(new CompoundBorder(
                        new LineBorder(Color.GRAY, 1, true),
                        new EmptyBorder(5,5,5,5)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(new CompoundBorder(
                        new LineBorder(Color.WHITE, 1, true),
                        new EmptyBorder(5,5,5,5)
                ));
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jDateLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jSearchTextField = new javax.swing.JTextField();
        jSearchButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jDateSpinner = new javax.swing.JSpinner();
        jTodayButton = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jMoviesPanel = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(955, 0));
        setRequestFocusEnabled(false);
        setSize(new java.awt.Dimension(955, 0));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1440, 100));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1076, 50));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jPanel4.setPreferredSize(new java.awt.Dimension(20, 50));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Bookings");
        jPanel2.add(jLabel1);

        jPanel7.setPreferredSize(new java.awt.Dimension(750, 30));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7);

        jDateLabel.setText("Date");
        jPanel2.add(jDateLabel);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(1076, 50));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel5.setPreferredSize(new java.awt.Dimension(20, 30));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5);

        jSearchTextField.setPreferredSize(new java.awt.Dimension(200, 30));
        jSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchTextFieldActionPerformed(evt);
            }
        });
        jPanel3.add(jSearchTextField);

        jSearchButton.setText("Search");
        jSearchButton.setPreferredSize(new java.awt.Dimension(78, 30));
        jPanel3.add(jSearchButton);

        jPanel6.setPreferredSize(new java.awt.Dimension(550, 30));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6);

        jDateSpinner.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel3.add(jDateSpinner);

        jTodayButton.setText("Today");
        jTodayButton.setPreferredSize(new java.awt.Dimension(100, 30));
        jTodayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTodayButtonActionPerformed(evt);
            }
        });
        jPanel3.add(jTodayButton);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jScrollPane.setBackground(new java.awt.Color(242, 242, 242));
        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setToolTipText("");

        jMoviesPanel.setPreferredSize(new java.awt.Dimension(1440, 800));
        jMoviesPanel.setSize(new java.awt.Dimension(1440, 800));

        javax.swing.GroupLayout jMoviesPanelLayout = new javax.swing.GroupLayout(jMoviesPanel);
        jMoviesPanel.setLayout(jMoviesPanelLayout);
        jMoviesPanelLayout.setHorizontalGroup(
            jMoviesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1440, Short.MAX_VALUE)
        );
        jMoviesPanelLayout.setVerticalGroup(
            jMoviesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(jMoviesPanel);

        add(jScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchTextFieldActionPerformed

    private void jTodayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTodayButtonActionPerformed
        model.setValue(new Date());
    }//GEN-LAST:event_jTodayButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jDateLabel;
    private javax.swing.JSpinner jDateSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jMoviesPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchTextField;
    private javax.swing.JButton jTodayButton;
    // End of variables declaration//GEN-END:variables
}
