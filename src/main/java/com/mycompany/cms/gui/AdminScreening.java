/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui;


import com.mycompany.cms.models.Movie;
import com.mycompany.cms.gui.movies.DateTimePanel;
import com.mycompany.cms.models.Cinema;
import com.mycompany.cms.util.Connector;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gilbert
 */
public class AdminScreening extends javax.swing.JPanel {

    ArrayList<Cinema> cinemas = new ArrayList<>();
    ArrayList<Movie> movies = new ArrayList<>();
    /**
     * Creates new form AdminScreening
     */
    
    //AdminScreening adminScreening = new AdminScreening();
    //    showPanel(adminScreening);
    
    
    public AdminScreening() {
        initComponents();
        refreshTable();
        
//        jPanel3.setVisible(false);
//        jPanel3.setEnabled(false);
        jEditButton.setVisible(false);
        jEditButton.setEnabled(false);
        jDeleteButton.setVisible(false);
        jDeleteButton.setEnabled(false);
        
        jUpdatePanel.setVisible(false);
        jUpdateButton.setVisible(false);
        jUpdateButton.setEnabled(false);
        jCancelButton.setVisible(false);
        jCancelButton.setEnabled(false);
        
        getMovieList();
        getCinemasList();
        //Populate movie selector with movies
        String[] movieList;
        movieList = getMovieTitles();
        jMovieDropDown.setModel(new javax.swing.DefaultComboBoxModel<>( movieList));
        jMovieUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(movieList));
        
        String[] cinemaList;
        cinemaList = getCinemaNames();
        jScreenDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(cinemaList));
        jScreenUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(cinemaList));
        
    }
    
    private String[] getMovieTitles() {
        ArrayList<String> movies = new ArrayList<String>();
        for (Movie m : this.movies) {
            movies.add(m.getTitle());
        }
        return movies.toArray(new String[(movies.size())]);
    }
    
    // Movie(int id, String title, String rating, int released, String genre, int duration, byte[] image) 
    private void getMovieList() {
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();
            
            String query = "SELECT * FROM movies";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("movie_id");
                String title = rs.getString("title");
                String rating = rs.getString("rating");
                int released = rs.getInt("released");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");
                Movie mov = new Movie(id, title, rating, released, genre, duration);
                this.movies.add(mov);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void getCinemasList() {
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();
            
            String query = "SELECT * FROM cinemas";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("cinema_id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                Cinema c = new Cinema(id, name, type);
                this.cinemas.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private String[] getCinemaNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Cinema c : this.cinemas) {
            names.add(c.getName());
        }
        return names.toArray(new String[(names.size())]);
    }
    
    private void refreshTable() {
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();
            
            String query = """
            SELECT 
                s.screening_id, 
                s.movie_id, 
                m.title AS movie_title,
                s.cinema_id, 
                c.name AS cinema_name,
                s.time_start, 
                s.time_end, 
                s.date, 
                s.price 
            FROM 
                screening s 
            JOIN 
                movies m ON s.movie_id = m.movie_id 
            JOIN 
                cinemas c ON s.cinema_id = c.cinema_id""";
            
            try (PreparedStatement pstmt = con.prepareStatement(query);
                    ResultSet resultSet = pstmt.executeQuery()) {

                    DefaultTableModel model = (DefaultTableModel) jMovieTable.getModel();
                    model.setRowCount(0);

                    while (resultSet.next()) {
                    int screeningId = resultSet.getInt("screening_id");
                    int movieId = resultSet.getInt("movie_id");
                    String movieTitle = resultSet.getString("movie_title");
                    int cinemaId = resultSet.getInt("cinema_id");
                    String cinemaName = resultSet.getString("cinema_name");
                    Time timeStart = resultSet.getTime("time_start");
                    Time timeEnd = resultSet.getTime("time_end");
                    Date date = resultSet.getDate("date");
                    int price = resultSet.getInt("price");

                    model.addRow(new Object[]{screeningId, movieId, movieTitle, cinemaId, cinemaName, timeStart, timeEnd, date, price});
//                    this.movies.add(new Movie())
                    }
                }
            } catch (SQLException e) {
              System.out.println(e);
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

        jMovieLabel = new javax.swing.JLabel();
        jScreenLabel = new javax.swing.JLabel();
        jAddDateButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMovieTable = new javax.swing.JTable();
        jSearchField = new javax.swing.JTextField();
        jSearchButton = new javax.swing.JButton();
        jClearAllButton = new javax.swing.JButton();
        jAddShowtimeButton = new javax.swing.JButton();
        jCinemaDateLabel = new javax.swing.JLabel();
        jMovieDropDown = new javax.swing.JComboBox<>();
        jScreenDropDown = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollContPanel = new javax.swing.JPanel();
        jEditButton = new javax.swing.JButton();
        jDeleteButton = new javax.swing.JButton();
        jUpdatePanel = new javax.swing.JPanel();
        jMovieUpdate = new javax.swing.JComboBox<>();
        jScreenUpdate = new javax.swing.JComboBox<>();
        jShowDateUpdate = new javax.swing.JSpinner();
        jEndtime8 = new javax.swing.JLabel();
        jMovieLabelUpdate = new javax.swing.JLabel();
        jScreenLabelUpdate = new javax.swing.JLabel();
        jShowDateLabelUpdate = new javax.swing.JLabel();
        jTimeUpdate = new javax.swing.JLabel();
        jPriceUpdate = new javax.swing.JTextField();
        jPriceLabelUpdate = new javax.swing.JLabel();
        jShowDate7 = new javax.swing.JSpinner();
        jUpdateButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jPriceTextField = new javax.swing.JTextField();
        jScreenLabel1 = new javax.swing.JLabel();

        jMovieLabel.setText("Movie");

        jScreenLabel.setText("Screen");

        jAddDateButton.setText("Add Date");
        jAddDateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAddDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddDateButtonActionPerformed(evt);
            }
        });

        jMovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Movie ID", "Movie", "Cinema ID", "Cinema", "Start", "End", "Date", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jMovieTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMovieTable.setGridColor(new java.awt.Color(0, 0, 0));
        jMovieTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMovieTableMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMovieTableMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(jMovieTable);
        if (jMovieTable.getColumnModel().getColumnCount() > 0) {
            jMovieTable.getColumnModel().getColumn(0).setResizable(false);
            jMovieTable.getColumnModel().getColumn(1).setResizable(false);
            jMovieTable.getColumnModel().getColumn(2).setResizable(false);
            jMovieTable.getColumnModel().getColumn(3).setResizable(false);
            jMovieTable.getColumnModel().getColumn(4).setResizable(false);
            jMovieTable.getColumnModel().getColumn(5).setResizable(false);
            jMovieTable.getColumnModel().getColumn(6).setResizable(false);
            jMovieTable.getColumnModel().getColumn(7).setResizable(false);
            jMovieTable.getColumnModel().getColumn(8).setResizable(false);
        }

        jSearchButton.setText("Search");
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jClearAllButton.setText("Clear All");
        jClearAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearAllButtonActionPerformed(evt);
            }
        });

        jAddShowtimeButton.setText("Add Showtime");
        jAddShowtimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddShowtimeButtonActionPerformed(evt);
            }
        });

        jCinemaDateLabel.setBackground(new java.awt.Color(255, 255, 255));
        jCinemaDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCinemaDateLabel.setText("Cinema:    C                  Date:     29/11/2023");
        jCinemaDateLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCinemaDateLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jMovieDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Movie", "Transformer", "9", "CJ7", "Avengers" }));
        jMovieDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMovieDropDownActionPerformed(evt);
            }
        });

        jScreenDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Screen", "1", "2", "3", "4" }));

        javax.swing.GroupLayout jScrollContPanelLayout = new javax.swing.GroupLayout(jScrollContPanel);
        jScrollContPanel.setLayout(jScrollContPanelLayout);
        jScrollContPanelLayout.setHorizontalGroup(
            jScrollContPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );
        jScrollContPanelLayout.setVerticalGroup(
            jScrollContPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jScrollContPanel);

        jEditButton.setText("Edit");
        jEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditButtonActionPerformed(evt);
            }
        });

        jDeleteButton.setText("Delete");
        jDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteButtonActionPerformed(evt);
            }
        });

        jMovieUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Movie", "Transformer", "9", "CJ7", "Avengers" }));

        jScreenUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Screen", "1", "2", "3", "4" }));

        jShowDateUpdate.setModel(new javax.swing.SpinnerDateModel());
        jShowDateUpdate.setEditor(new javax.swing.JSpinner.DateEditor(jShowDateUpdate, "MM/dd/yyyy"));

        jEndtime8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jEndtime8.setText("6:48 PM");
        jEndtime8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jEndtime8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jMovieLabelUpdate.setText("Movie");

        jScreenLabelUpdate.setText("Screen");

        jShowDateLabelUpdate.setText("Show Date");

        jTimeUpdate.setText("Time");

        jPriceLabelUpdate.setText("Price");

        jShowDate7.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jShowDate7.setEditor(new javax.swing.JSpinner.DateEditor(jShowDate7, "hh:mm aa"));

        javax.swing.GroupLayout jUpdatePanelLayout = new javax.swing.GroupLayout(jUpdatePanel);
        jUpdatePanel.setLayout(jUpdatePanelLayout);
        jUpdatePanelLayout.setHorizontalGroup(
            jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jUpdatePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jUpdatePanelLayout.createSequentialGroup()
                        .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jMovieLabelUpdate)
                            .addComponent(jScreenLabelUpdate))
                        .addGap(76, 76, 76)
                        .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScreenUpdate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jMovieUpdate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2))
                    .addGroup(jUpdatePanelLayout.createSequentialGroup()
                        .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jShowDateLabelUpdate)
                            .addComponent(jTimeUpdate)
                            .addComponent(jPriceLabelUpdate))
                        .addGap(55, 55, 55)
                        .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPriceUpdate)
                            .addGroup(jUpdatePanelLayout.createSequentialGroup()
                                .addComponent(jShowDate7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jEndtime8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jShowDateUpdate)))))
        );
        jUpdatePanelLayout.setVerticalGroup(
            jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jUpdatePanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMovieUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMovieLabelUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jScreenUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScreenLabelUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jShowDateUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jShowDateLabelUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jShowDate7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jTimeUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jEndtime8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPriceUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPriceLabelUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jUpdateButton.setText("Update");
        jUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateButtonActionPerformed(evt);
            }
        });

        jCancelButton.setText("Cancel");
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        jScreenLabel1.setText("Price");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jScreenLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScreenDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jMovieLabel)
                                    .addGap(59, 59, 59)
                                    .addComponent(jMovieDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScreenLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(jAddDateButton))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jClearAllButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jAddShowtimeButton)
                        .addGap(56, 56, 56)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jEditButton)
                                .addGap(18, 18, 18)
                                .addComponent(jDeleteButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jUpdateButton)
                                .addGap(18, 18, 18)
                                .addComponent(jCancelButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(jUpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(118, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSearchButton))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCinemaDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(340, 340, 340))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jMovieDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jMovieLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jScreenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScreenDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jAddDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPriceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jScreenLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jAddShowtimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jClearAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jCinemaDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jUpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(177, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jAddDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddDateButtonActionPerformed
        // TODO add your handling code here:
        DateTimePanel dateTimePanel = new DateTimePanel();
        jScrollContPanel.setLayout(new BoxLayout(jScrollContPanel, BoxLayout.Y_AXIS));
        jScrollContPanel.add(dateTimePanel);
        jScrollContPanel.repaint();
        jScrollContPanel.revalidate();
        
        repaint();
        revalidate();
        
    }//GEN-LAST:event_jAddDateButtonActionPerformed

    private void jClearAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearAllButtonActionPerformed
        // TODO add your handling code here:
        
        jMovieDropDown.setSelectedIndex(0);
	jScreenDropDown.setSelectedIndex(0);
	
    }//GEN-LAST:event_jClearAllButtonActionPerformed

    public int getMovieLength(int movieId) {
        Connector connector = new Connector();
        Connection con = connector.getConnection();
        try {
            String query = "SELECT duration FROM movies WHERE movie_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, movieId);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            int duration = rs.getInt("movie_id");
            return duration;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
    private Movie getMovieByTitle(String title) {
        for (Movie m : this.movies) {
            if (m.getTitle().equals(title)) {
                return m;
            }
        }
        return null;
    }
    
    private Movie getMovieById(int id) {
        for (Movie m : this.movies) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
    
    private Cinema getCinemaByName(String name) {
        for (Cinema c : this.cinemas) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
    
    private Cinema getCinemaById(int id) {
        for (Cinema c : this.cinemas) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    
    private void jAddShowtimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddShowtimeButtonActionPerformed
        Component[] panels = jScrollContPanel.getComponents();
        for (Component p : panels) { //go thru every panel in scroll pane (each panel is one screening to be added)
            Component[] innerComponents = ((JPanel) p).getComponents();
            
            Movie mov = getMovieByTitle((String) jMovieDropDown.getSelectedItem());
            Cinema cin = getCinemaByName((String) jScreenDropDown.getSelectedItem());
            int movieId = mov.getId();
            int cinemaId = cin.getId();
            int price = Integer.parseInt(jPriceTextField.getText());
            LocalDate screeningDate = null;
            LocalTime screeningTime = null;
            LocalTime endTime = null;
            
            for (Component innerComp : innerComponents) {
                
                if (innerComp.getName().equals("dateSpinner")) {
                    JSpinner dateSpinner = (JSpinner) innerComp;
                    SpinnerDateModel dateModel = (SpinnerDateModel) dateSpinner.getModel();

                    Object spinnerValue = dateModel.getValue();
                    screeningDate = ((Date) spinnerValue).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                }

                if (innerComp.getName().equals("timeStartSpinner")) {
                    JSpinner timeSpinner = (JSpinner) innerComp;
                    Date _timeStart = (Date) timeSpinner.getValue();
                    screeningTime = _timeStart.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
                    endTime = screeningTime.plusSeconds(mov.getDuration());
                }
            }
            
            try {
                Connector connector = new Connector();
                Connection con = connector.getConnection();
                
                String query = "INSERT INTO screening (movie_id, date, time_start, time_end, cinema_id, price) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(query);

                pstmt.setInt(1, movieId);
                pstmt.setObject(2, screeningDate);
                pstmt.setTime(3, Time.valueOf(screeningTime));// akjdflsajf
                pstmt.setTime(4,Time.valueOf(endTime));
                pstmt.setInt(5, cinemaId);
                pstmt.setInt(6, price);
                
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            refreshTable();
            
        }
    }//GEN-LAST:event_jAddShowtimeButtonActionPerformed

    private void jDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteButtonActionPerformed
        // TODO add your handling code here:
        
        try {

	    Connector connector = new Connector();
            Connection con = connector.getConnection();

	    int selectedRow = jMovieTable.getSelectedRow();
	    int movieid_column = 0;
 	    int movie_table_id = (int) jMovieTable.getModel().getValueAt(selectedRow, movieid_column);

            
            String query = "DELETE FROM screening WHERE screening_id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, movie_table_id);
            
            prepStmt.executeUpdate();

	} catch (SQLException e) {
        System.out.println(e);
	}
        refreshTable();
    }//GEN-LAST:event_jDeleteButtonActionPerformed

    private void jMovieTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMovieTableMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_jMovieTableMouseExited

    private void jMovieTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMovieTableMouseClicked
        // TODO add your handling code here:
        jEditButton.setVisible(true);
        jEditButton.setEnabled(true);
        jDeleteButton.setVisible(true);
        jDeleteButton.setEnabled(true);
    }//GEN-LAST:event_jMovieTableMouseClicked

    private void jEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditButtonActionPerformed
        // TODO add your handling code here:
        jUpdatePanel.setVisible(true);
        jUpdateButton.setVisible(true);
        jUpdateButton.setEnabled(true);
        jCancelButton.setVisible(true);
        jCancelButton.setEnabled(true);
        
        int selectedRow = jMovieTable.getSelectedRow(); 
        Movie mov = getMovieById((int) jMovieTable.getValueAt(selectedRow, 1));
        Cinema cin = getCinemaById((int) jMovieTable.getValueAt(selectedRow, 3));
        
        jMovieUpdate.setSelectedItem(mov.getTitle());
        jScreenUpdate.setSelectedItem(cin.getName());
        jShowDateUpdate.setValue(jMovieTable.getValueAt(selectedRow, 7));
        jShowDate7.setValue(jMovieTable.getValueAt(selectedRow, 5));
        jPriceUpdate.setText(String.valueOf(jMovieTable.getValueAt(selectedRow, 8)));
    }//GEN-LAST:event_jEditButtonActionPerformed

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        // TODO add your handling code here:
        
        String searchInput = jSearchField.getText();
        
        
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

            String query = "SELECT screening_id, movie_id, cinema_id, time_start, time_end, date, price FROM screening WHERE screening_id LIKE ?";
            
                    PreparedStatement pstmt = con.prepareStatement(query);                    
                    pstmt.setString(1, "%" + searchInput + "%");
                    ResultSet resultSet = pstmt.executeQuery();

                    DefaultTableModel model = (DefaultTableModel) jMovieTable.getModel();
                    model.setRowCount(0);

                    while (resultSet.next()) {
                    int screeningId = resultSet.getInt("screening_id");
                    int movieId = resultSet.getInt("movie_id");
                    int cinemaId = resultSet.getInt("cinema_id");
                    Time timeStart = resultSet.getTime("time_start");
                    Time timeEnd = resultSet.getTime("time_end");
                    Date date = resultSet.getDate("date");
                    int price = resultSet.getInt("price");

                    model.addRow(new Object[]{screeningId, movieId, cinemaId, timeStart, timeEnd, date, price});
                    }

            } catch (SQLException e) {
              System.out.println(e);
            }
    }//GEN-LAST:event_jSearchButtonActionPerformed

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        // TODO add your handling code here:
        jMovieUpdate.setSelectedIndex(0);
	jScreenUpdate.setSelectedIndex(0);
        jUpdatePanel.setVisible(false);
        jUpdateButton.setVisible(false);
        jUpdateButton.setEnabled(false);
        jCancelButton.setVisible(false);
        jCancelButton.setEnabled(false);
    }//GEN-LAST:event_jCancelButtonActionPerformed

    private void jUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateButtonActionPerformed
        // TODO add your handling code here:
        
        Movie mov = getMovieByTitle((String) jMovieUpdate.getSelectedItem());
        Cinema cin = getCinemaByName((String) jScreenDropDown.getSelectedItem());
        int movieId = mov.getId();
        int cinemaId = cin.getId();
        int price = Integer.parseInt(jPriceUpdate.getText());
        LocalDate screeningDate = ((Date) jShowDateUpdate.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalTime screeningTime = ((Date) jShowDate7.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        LocalTime endTime = screeningTime.plusSeconds(mov.getDuration());
        
        int selectedRow = jMovieTable.getSelectedRow();
	int screening_id_column = 0;
 	int screeningID = (int)jMovieTable.getModel().getValueAt(selectedRow, screening_id_column);
        
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

            String query = "UPDATE screening SET movie_id = ?, cinema_id = ?, time_start = ?, time_end = ?, date = ?, price = ? WHERE screening_id = ?";
                    
                PreparedStatement prepStmt = con.prepareStatement(query);

                prepStmt.setInt(1, movieId);
                prepStmt.setInt(2, cinemaId);
                prepStmt.setTime(3, Time.valueOf(screeningTime));
                prepStmt.setTime(4, Time.valueOf(endTime));
                prepStmt.setObject(5, screeningDate);
                prepStmt.setInt(6, price);
                prepStmt.setInt(7, screeningID);
                
                prepStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        refreshTable();
    }//GEN-LAST:event_jUpdateButtonActionPerformed

    private void jMovieDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMovieDropDownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMovieDropDownActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddDateButton;
    private javax.swing.JButton jAddShowtimeButton;
    private javax.swing.JButton jCancelButton;
    private javax.swing.JLabel jCinemaDateLabel;
    private javax.swing.JButton jClearAllButton;
    private javax.swing.JButton jDeleteButton;
    private javax.swing.JButton jEditButton;
    private javax.swing.JLabel jEndtime8;
    private javax.swing.JComboBox<String> jMovieDropDown;
    private javax.swing.JLabel jMovieLabel;
    private javax.swing.JLabel jMovieLabelUpdate;
    private javax.swing.JTable jMovieTable;
    private javax.swing.JComboBox<String> jMovieUpdate;
    private javax.swing.JLabel jPriceLabelUpdate;
    private javax.swing.JTextField jPriceTextField;
    private javax.swing.JTextField jPriceUpdate;
    private javax.swing.JComboBox<String> jScreenDropDown;
    private javax.swing.JLabel jScreenLabel;
    private javax.swing.JLabel jScreenLabel1;
    private javax.swing.JLabel jScreenLabelUpdate;
    private javax.swing.JComboBox<String> jScreenUpdate;
    private javax.swing.JPanel jScrollContPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchField;
    private javax.swing.JSpinner jShowDate7;
    private javax.swing.JLabel jShowDateLabelUpdate;
    private javax.swing.JSpinner jShowDateUpdate;
    private javax.swing.JLabel jTimeUpdate;
    private javax.swing.JButton jUpdateButton;
    private javax.swing.JPanel jUpdatePanel;
    // End of variables declaration//GEN-END:variables
}
