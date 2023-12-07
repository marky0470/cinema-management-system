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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        
        getMovieList();
        getCinemasList();
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(12);
        //Populate movie selector with movies
        String[] movieList = getMovieTitles();
        jMovieDropDown.setModel(new javax.swing.DefaultComboBoxModel<>( movieList));
        String[] cinemaList = getCinemaNames();
        jScreenDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(cinemaList));
        jCinemaSelector.setModel(new javax.swing.DefaultComboBoxModel<>(cinemaList));
        
        jCinemaSelector.addActionListener(cinemaSelectActionListener());
        
        jDateSelector.addChangeListener(dateSelectChangeListener());

    }
    
    private ActionListener cinemaSelectActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connector connector = new Connector();
                Connection con = connector.getConnection();
                
                try {
                    int selectedCinema = getCinemaByName((String) jCinemaSelector.getSelectedItem()).getId();

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
                            cinemas c ON s.cinema_id = c.cinema_id
                        WHERE
                            s.cinema_id = ?""";

                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setInt(1, selectedCinema);
                    ResultSet rs = pstmt.executeQuery();
                    
                    DefaultTableModel model = (DefaultTableModel) jMovieTable.getModel();
                    model.setRowCount(0);
                    
                    while (rs.next()) {
                        int screeningId = rs.getInt("screening_id");
                        int movieId = rs.getInt("movie_id");
                        String movieTitle = rs.getString("movie_title");
                        int cinemaId = rs.getInt("cinema_id");
                        String cinemaName = rs.getString("cinema_name");
                        Time timeStart = rs.getTime("time_start");
                        Time timeEnd = rs.getTime("time_end");
                        Date date = rs.getDate("date");
                        int price = rs.getInt("price");

                        model.addRow(new Object[]{screeningId, movieId, movieTitle, cinemaId, cinemaName, timeStart, timeEnd, date, price});
                    }
                    
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                revalidate();
                repaint();
                
            }
        };
    }
    
    private ChangeListener dateSelectChangeListener() {
        return new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Connector connector = new Connector();
                Connection con = connector.getConnection();
                
                try {
                    Date selectedDate = (Date) jDateSelector.getValue();

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
                            cinemas c ON s.cinema_id = c.cinema_id
                        WHERE
                            s.date = ?""";

                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setObject(1, selectedDate);
                    ResultSet rs = pstmt.executeQuery();
                    
                    DefaultTableModel model = (DefaultTableModel) jMovieTable.getModel();
                    model.setRowCount(0);
                    
                    while (rs.next()) {
                        int screeningId = rs.getInt("screening_id");
                        int movieId = rs.getInt("movie_id");
                        String movieTitle = rs.getString("movie_title");
                        int cinemaId = rs.getInt("cinema_id");
                        String cinemaName = rs.getString("cinema_name");
                        Time timeStart = rs.getTime("time_start");
                        Time timeEnd = rs.getTime("time_end");
                        Date date = rs.getDate("date");
                        int price = rs.getInt("price");

                        model.addRow(new Object[]{screeningId, movieId, movieTitle, cinemaId, cinemaName, timeStart, timeEnd, date, price});
                    }
                    
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                revalidate();
                repaint();
            }
        };
    }
    
    private String[] getMovieTitles() {
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<Movie> arrangedMovies = arrangeByMovieId();
        for (Movie m : arrangedMovies) {
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
    
    public void refreshTable() {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jMovieTable = new javax.swing.JTable();
        jSearchField = new javax.swing.JTextField();
        jSearchButton = new javax.swing.JButton();
        jClearAllButton = new javax.swing.JButton();
        jAddShowtimeButton = new javax.swing.JButton();
        jMovieDropDown = new javax.swing.JComboBox<>();
        jScreenDropDown = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollContPanel = new javax.swing.JPanel();
        jEditButton = new javax.swing.JButton();
        jDeleteButton = new javax.swing.JButton();
        jPriceTextField = new javax.swing.JTextField();
        jScreenLabel1 = new javax.swing.JLabel();
        jAddDateButton = new javax.swing.JButton();
        jRefreshButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jCinemaSelector = new javax.swing.JComboBox<>();
        jMovieLabel1 = new javax.swing.JLabel();
        jMovieLabel2 = new javax.swing.JLabel();
        jDateSelector = new javax.swing.JSpinner();

        jMovieLabel.setText("Movie");

        jScreenLabel.setText("Cinema");

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

        jSearchButton.setBackground(new java.awt.Color(239, 124, 18));
        jSearchButton.setForeground(new java.awt.Color(255, 255, 255));
        jSearchButton.setText("Search");
        jSearchButton.setPreferredSize(new java.awt.Dimension(72, 30));
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jClearAllButton.setBackground(new java.awt.Color(247, 196, 149));
        jClearAllButton.setText("Clear All");
        jClearAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearAllButtonActionPerformed(evt);
            }
        });

        jAddShowtimeButton.setBackground(new java.awt.Color(239, 124, 18));
        jAddShowtimeButton.setForeground(new java.awt.Color(255, 255, 255));
        jAddShowtimeButton.setText("Add Showtime");
        jAddShowtimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddShowtimeButtonActionPerformed(evt);
            }
        });

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
            .addGap(0, 510, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jScrollContPanel);

        jEditButton.setBackground(new java.awt.Color(239, 124, 18));
        jEditButton.setForeground(new java.awt.Color(255, 255, 255));
        jEditButton.setText("Edit");
        jEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditButtonActionPerformed(evt);
            }
        });

        jDeleteButton.setBackground(new java.awt.Color(247, 196, 149));
        jDeleteButton.setText("Delete");
        jDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteButtonActionPerformed(evt);
            }
        });

        jPriceTextField.setPreferredSize(new java.awt.Dimension(64, 30));

        jScreenLabel1.setText("Price");

        jAddDateButton.setBackground(new java.awt.Color(239, 124, 18));
        jAddDateButton.setForeground(new java.awt.Color(255, 255, 255));
        jAddDateButton.setText("Add Date");
        jAddDateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAddDateButton.setPreferredSize(new java.awt.Dimension(79, 30));
        jAddDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddDateButtonActionPerformed(evt);
            }
        });

        jRefreshButton.setBackground(new java.awt.Color(247, 196, 149));
        jRefreshButton.setText("Refresh");
        jRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Screenings");

        jCinemaSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCinemaSelector.setPreferredSize(new java.awt.Dimension(72, 30));

        jMovieLabel1.setText("Cinema");

        jMovieLabel2.setText("Date");

        jDateSelector.setModel(new javax.swing.SpinnerDateModel());
        jDateSelector.setEditor(new javax.swing.JSpinner.DateEditor(jDateSelector, "MM/dd/yyyy"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jEditButton)
                        .addGap(18, 18, 18)
                        .addComponent(jDeleteButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScreenLabel)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScreenDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(jAddDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jMovieLabel)
                                                .addGap(242, 242, 242))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jMovieDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScreenLabel1)
                                .addComponent(jPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jClearAllButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(jAddShowtimeButton))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCinemaSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jMovieLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jMovieLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jDateSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jRefreshButton)))))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMovieLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMovieLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMovieLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCinemaSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jMovieDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScreenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jAddDateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScreenDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScreenLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jAddShowtimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jClearAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jClearAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearAllButtonActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear the entry field?", "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.NO_OPTION) {
            return;
        }
        

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
    
    public Movie getMovieByTitle(String title) {
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
    
    public Cinema getCinemaByName(String name) {
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
    
    public ArrayList<Movie> arrangeByMovieId() {
        ArrayList<Movie> arrangedMovies = new ArrayList<>(movies);

        Collections.sort(arrangedMovies, Comparator.comparingInt(Movie::getId).reversed());

        return arrangedMovies;
    }
    
    private void jAddShowtimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddShowtimeButtonActionPerformed
        
        if (jPriceTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a price", "Confirm", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
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
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this entry?", "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.NO_OPTION) {
            return;
        }
        
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


        String[] movieList = getMovieTitles();
        String[] cinemaList = getCinemaNames();
        
        int selectedRow = jMovieTable.getSelectedRow(); 
        
        String selectedMovie = getMovieById((int) jMovieTable.getValueAt(selectedRow, 1)).getTitle();

        String selectedCinema = getCinemaById((int) jMovieTable.getValueAt(selectedRow, 3)).getName();
        UpdateScreeningForm updateScreening = new UpdateScreeningForm(this, movieList, cinemaList, selectedMovie, selectedCinema);
        updateScreening.setVisible(true);
    }//GEN-LAST:event_jEditButtonActionPerformed

    public Object getScreeningTime() {
        int selectedRow = jMovieTable.getSelectedRow(); 
        return jMovieTable.getValueAt(selectedRow, 5);
    }
    
    public Object getScreeningDate() {
        int selectedRow = jMovieTable.getSelectedRow(); 
        return jMovieTable.getValueAt(selectedRow, 7);
    }
    
    public int getScreeningId() {
        int selectedRow = jMovieTable.getSelectedRow(); 
        return (int) jMovieTable.getValueAt(selectedRow, 0);
    }
    
    public String getPrice() {
        int selectedRow = jMovieTable.getSelectedRow(); 
        return String.valueOf(jMovieTable.getValueAt(selectedRow, 8));
    }
    
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

    private void jMovieDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMovieDropDownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMovieDropDownActionPerformed

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

    private void jRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshButtonActionPerformed
        // TODO add your handling code here:
        refreshTable();
    }//GEN-LAST:event_jRefreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddDateButton;
    private javax.swing.JButton jAddShowtimeButton;
    private javax.swing.JComboBox<String> jCinemaSelector;
    private javax.swing.JButton jClearAllButton;
    private javax.swing.JSpinner jDateSelector;
    private javax.swing.JButton jDeleteButton;
    private javax.swing.JButton jEditButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> jMovieDropDown;
    private javax.swing.JLabel jMovieLabel;
    private javax.swing.JLabel jMovieLabel1;
    private javax.swing.JLabel jMovieLabel2;
    private javax.swing.JTable jMovieTable;
    private javax.swing.JTextField jPriceTextField;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JComboBox<String> jScreenDropDown;
    private javax.swing.JLabel jScreenLabel;
    private javax.swing.JLabel jScreenLabel1;
    private javax.swing.JPanel jScrollContPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchField;
    // End of variables declaration//GEN-END:variables
}
