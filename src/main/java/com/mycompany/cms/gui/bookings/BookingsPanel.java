/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.bookings;

import com.mycompany.cms.util.Connector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
    private final SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
    private final BookingsTabbedPanel panel;
    
    /**
     * Creates new form BookingsPanel
     * @param panel
     */
    public BookingsPanel(BookingsTabbedPanel panel) {
        initComponents();
        
        this.panel = panel;
        
        Connector connector = new Connector();
        conn = connector.getConnection();
        
        jDateSpinner.setModel(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jDateSpinner, "MM/dd/yyyy");
        jDateSpinner.setEditor(editor);
        jDateSpinner.addChangeListener(getDateSpinnerChangeListener());
        
        changeDateLabel();
        getScreenings();
    }
    
    private void changeDateLabel() {
        Date selectedDate = (Date) getSelectedDate();
        jDateLabel.setText(selectedDate.toString());
    }
    
    private void getScreeningsBySearch() {
        try {
            String movieTitle = jSearchTextField.getText();
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
                                    screening.date,
                                    movies.movie_id,
                                    movies.title,
                                    movies.image
                                FROM
                                    screening
                                LEFT JOIN movies ON screening.movie_id = movies.movie_id
                            ) screening
                         WHERE 
                            (screening.date = ?) AND 
                            (title LIKE ?);
                         """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setString(2, "%" + movieTitle + "%");
            ResultSet result = preparedStatement.executeQuery();
            
            displayScreenings(result);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    private void getScreenings() {
        try {
            Date date = getSelectedDate();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String sql = """
                        SELECT DISTINCT
                            (title),
                            image,
                            movie_id
                        FROM
                            (
                            SELECT
                                screening.date,
                                movies.movie_id,
                                movies.title,
                                movies.image
                            FROM
                                screening
                            LEFT JOIN movies ON screening.movie_id = movies.movie_id
                        ) screening
                        WHERE
                            screening.date = ?;""";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDate(1, sqlDate);
            ResultSet result = st.executeQuery();
            
            displayScreenings(result);
        } catch  (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void displayScreenings(ResultSet res) {
        try {
            jMoviesPanel.removeAll();
            jMoviesPanel.setLayout(new BoxLayout(jMoviesPanel, BoxLayout.Y_AXIS));
            jMoviesPanel.setBackground(Color.WHITE);

            int index = 0;
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

            while (res.next()) {
                String title = res.getString("title");
                int movieId = res.getInt("movie_id");
                InputStream inputStream = res.getBinaryStream("image");

                if (inputStream == null) {
                    JPanel card = createCardPanelNoImage(movieId, title);
                    panel.add(card);
                } else {
                    BufferedImage im = ImageIO.read(inputStream);
                    JPanel card = createCardPanel(movieId, title, im);
                    panel.add(card);
                }

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
        } catch (SQLException | IOException e) {
            System.out.println(e.toString());
        }
    }
    
    private JPanel createCardPanelNoImage(int movieId, String title) {
        JPanel cardPanel = new JPanel();
        
        cardPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(45, 45, 45, 50), 1, true),
            new EmptyBorder(1, 1, 1, 1)
        ));
        cardPanel.setPreferredSize(new Dimension(200, 350));
        cardPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(180, 300));
        JLabel poster = new JLabel("No Image");
        card.add(poster);
        cardPanel.add(card);
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(180, 20));
        cardPanel.add(titleLabel);
        
        cardPanel.addMouseListener(getMouseListenerNoImage(movieId, title, cardPanel));

        return cardPanel;
    }
    
    private JPanel createCardPanel(int movieId, String title, BufferedImage image) {
        JPanel cardPanel = new JPanel();
        
        cardPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(45, 45, 45, 50), 1, true),
            new EmptyBorder(1, 1, 1, 1)
        ));
        cardPanel.setPreferredSize(new Dimension(200, 350));
        cardPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(180, 300));
        ImageIcon imageIcon = new ImageIcon(image);
        Image scaledImage = imageIcon.getImage().getScaledInstance(180, 300, Image.SCALE_SMOOTH);
        JLabel poster = new JLabel(new ImageIcon(scaledImage));
        card.add(poster);
        cardPanel.add(card);
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(180, 20));
        cardPanel.add(titleLabel);
        
        cardPanel.addMouseListener(getMouseListener(movieId, title, image, cardPanel));

        return cardPanel;
    }
    
    private Date getSelectedDate() {
        Date selectedDate = (Date) jDateSpinner.getValue();
        return selectedDate;
    }
    
    private ChangeListener getDateSpinnerChangeListener() {
        return (ChangeEvent e) -> {
            changeDateLabel();
            getScreenings();
        };
    }
    
    private MouseAdapter getMouseListenerNoImage(int id, String title, JPanel card) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BookingsPanel.this.panel.setSelectedMovieId(id);
                BookingsPanel.this.panel.setMovieTitle(title);
                BookingsPanel.this.panel.setMoviePoster(null);
                BookingsPanel.this.panel.setScreeningDate(getSelectedDate());
                BookingsPanel.this.panel.openSchedulesTab();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(new CompoundBorder(
                    new LineBorder(new Color(45, 45, 45, 70), 1, true),
                    new EmptyBorder(1, 1, 1, 1)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(new CompoundBorder(
                    new LineBorder(new Color(45, 45, 45, 50), 1, true),
                    new EmptyBorder(1, 1, 1, 1)
                ));
            }
        };
    }
    
    private MouseAdapter getMouseListener(int id, String title, BufferedImage image, JPanel card) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BookingsPanel.this.panel.setSelectedMovieId(id);
                BookingsPanel.this.panel.setMovieTitle(title);
                BookingsPanel.this.panel.setMoviePoster(image);
                BookingsPanel.this.panel.setScreeningDate(getSelectedDate());
                BookingsPanel.this.panel.openSchedulesTab();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(new CompoundBorder(
                    new LineBorder(new Color(45, 45, 45, 100), 1, true),
                    new EmptyBorder(1, 1, 1, 1)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(new CompoundBorder(
                    new LineBorder(new Color(45, 45, 45, 50), 1, true),
                    new EmptyBorder(1, 1, 1, 1)
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
        jPanel3 = new javax.swing.JPanel();
        jSearchTextField = new javax.swing.JTextField();
        jSearchButton = new javax.swing.JButton();
        jDateSpinner = new javax.swing.JSpinner();
        jTodayButton = new javax.swing.JButton();
        jRefreshButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jDateLabel = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jMoviesPanel = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1140, 800));
        setPreferredSize(new java.awt.Dimension(1140, 800));
        setRequestFocusEnabled(false);
        setSize(new java.awt.Dimension(1140, 800));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1440, 120));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1076, 50));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jPanel4.setPreferredSize(new java.awt.Dimension(18, 45));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Bookings");
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(1076, 70));

        jSearchTextField.setPreferredSize(new java.awt.Dimension(200, 30));

        jSearchButton.setBackground(new java.awt.Color(239, 124, 18));
        jSearchButton.setForeground(new java.awt.Color(255, 255, 255));
        jSearchButton.setText("Search");
        jSearchButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(239, 124, 18), 1, true));
        jSearchButton.setPreferredSize(new java.awt.Dimension(85, 30));
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jDateSpinner.setPreferredSize(new java.awt.Dimension(100, 30));

        jTodayButton.setBackground(new java.awt.Color(247, 222, 200));
        jTodayButton.setText("Today");
        jTodayButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 222, 200), 1, true));
        jTodayButton.setPreferredSize(new java.awt.Dimension(100, 30));
        jTodayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTodayButtonActionPerformed(evt);
            }
        });

        jRefreshButton.setBackground(new java.awt.Color(247, 222, 200));
        jRefreshButton.setText("Refresh");
        jRefreshButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 222, 200), 1, true));
        jRefreshButton.setPreferredSize(new java.awt.Dimension(100, 30));
        jRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Search");

        jDateLabel.setText("Date");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(471, 471, 471)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jDateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTodayButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateLabel))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTodayButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jScrollPane.setBackground(new java.awt.Color(242, 242, 242));
        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setToolTipText("");

        jMoviesPanel.setPreferredSize(new java.awt.Dimension(1440, 600));
        jMoviesPanel.setSize(new java.awt.Dimension(1440, 800));

        javax.swing.GroupLayout jMoviesPanelLayout = new javax.swing.GroupLayout(jMoviesPanel);
        jMoviesPanel.setLayout(jMoviesPanelLayout);
        jMoviesPanelLayout.setHorizontalGroup(
            jMoviesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1440, Short.MAX_VALUE)
        );
        jMoviesPanelLayout.setVerticalGroup(
            jMoviesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(jMoviesPanel);

        add(jScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTodayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTodayButtonActionPerformed
        model.setValue(new Date());
    }//GEN-LAST:event_jTodayButtonActionPerformed

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        getScreeningsBySearch();
        jSearchTextField.setText("");
    }//GEN-LAST:event_jSearchButtonActionPerformed

    private void jRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshButtonActionPerformed
        getScreeningsBySearch();
    }//GEN-LAST:event_jRefreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jDateLabel;
    private javax.swing.JSpinner jDateSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jMoviesPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchTextField;
    private javax.swing.JButton jTodayButton;
    // End of variables declaration//GEN-END:variables
}
