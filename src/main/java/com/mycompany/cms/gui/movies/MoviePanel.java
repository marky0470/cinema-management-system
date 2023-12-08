package com.mycompany.cms.gui.movies;

import com.mycompany.cms.util.Connector;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author ikelm, l4ckluster
 */
public class MoviePanel extends javax.swing.JPanel {

    /**
     * Creates new form MoviePanel
     */
    public MoviePanel() {
        initComponents();
        refreshTable();

    }
    
    private void refreshTable() {       
        
        //A function that refreshes the table//
        
        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

            String query = "SELECT movie_id, title, rating, released, genre, duration FROM movies";

                try (PreparedStatement pstmt = con.prepareStatement(query);
                    ResultSet resultSet = pstmt.executeQuery()) {

                    DefaultTableModel model = (DefaultTableModel) jMovieTable1.getModel();
                    model.setRowCount(0);

                    while (resultSet.next()) {
                    int movieId = resultSet.getInt("movie_id");
                    String title = resultSet.getString("title");
                    String rating = resultSet.getString("rating");
                    int released = resultSet.getInt("released");
                    String genre = resultSet.getString("genre");
                    int duration = resultSet.getInt("duration");

                    model.addRow(new Object[]{movieId, title, rating, released, genre, duration});
                    }
                }
            } catch (SQLException e) {
              System.out.println(e);
            }
    }
    
    private Image convertBlobToImage(byte[] blobData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(blobData);
        BufferedImage bufferedImage = ImageIO.read(bis);
        bis.close();
        return bufferedImage;
    }
    
    private void clearEverything(){
        
        // A Function that clears everything//
        
        jTitleText.setText("");
	jRatingComboBox.setSelectedIndex(0);
	jReleasedComboBox.setSelectedIndex(0);
	jGenreComboBox.setSelectedIndex(0);
	jDurationText.setText("");
        jSearchText1.setText("");
        moviePosterLabel.setIcon(null);
        jFilePathText.setText("");
        refreshTable();
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
        jGenreComboBox = new javax.swing.JComboBox<>();
        jDurationLabel = new javax.swing.JLabel();
        jRatingComboBox = new javax.swing.JComboBox<>();
        jTitleText = new javax.swing.JTextField();
        jReleasedComboBox = new javax.swing.JComboBox<>();
        jDurationText = new javax.swing.JTextField();
        chooseImageButton = new javax.swing.JButton();
        jTitleLabel = new javax.swing.JLabel();
        jRatingLabel = new javax.swing.JLabel();
        jDateLabel = new javax.swing.JLabel();
        jGenreLabel = new javax.swing.JLabel();
        jMovieLabel = new javax.swing.JLabel();
        moviePosterLabel = new javax.swing.JLabel();
        jFilePathText = new javax.swing.JTextField();
        jDeleteButton = new javax.swing.JButton();
        jClearButton = new javax.swing.JButton();
        jUpdateButton = new javax.swing.JButton();
        jAddButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMovieTable1 = new javax.swing.JTable();
        jRefreshButton = new javax.swing.JButton();
        jSearchText1 = new javax.swing.JTextField();
        jSearchButton = new javax.swing.JButton();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1080, 800));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(253, 253, 253));

        jGenreComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Action",
            "Comedy",
            "Drama",
            "Fantasy",
            "Horror",
            "Mystery",
            "Romance",
            "Thriller",
            "Western" }));

jDurationLabel.setText("Duration (in seconds)");

jRatingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "G", "PG", "R-13", "R-16", "R-18" }));

jTitleText.setPreferredSize(null);

jReleasedComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959",
    "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
    "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979",
    "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
    "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999",
    "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
    "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
    "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
    "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039",
    "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049",
    "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059",
    "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069",
    "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079",
    "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089",
    "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099",
    "2100"
    }));

    jDurationText.setPreferredSize(null);
    jDurationText.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jDurationTextActionPerformed(evt);
        }
    });

    chooseImageButton.setBackground(new java.awt.Color(242, 242, 242));
    chooseImageButton.setText("Choose image");
    chooseImageButton.setBorderPainted(false);
    chooseImageButton.setPreferredSize(new java.awt.Dimension(85, 30));
    chooseImageButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            chooseImageButtonActionPerformed(evt);
        }
    });

    jTitleLabel.setText("Title");

    jRatingLabel.setText("Rating");

    jDateLabel.setText("Release date");

    jGenreLabel.setText("Genre");

    jMovieLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
    jMovieLabel.setText("Movies");

    moviePosterLabel.setText(" ");
    moviePosterLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    moviePosterLabel.setPreferredSize(new java.awt.Dimension(145, 225));

    jFilePathText.setEditable(false);
    jFilePathText.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFilePathTextActionPerformed(evt);
        }
    });

    jDeleteButton.setBackground(new java.awt.Color(242, 242, 242));
    jDeleteButton.setText("Delete");
    jDeleteButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
    jDeleteButton.setBorderPainted(false);
    jDeleteButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jDeleteButtonActionPerformed(evt);
        }
    });

    jClearButton.setBackground(new java.awt.Color(242, 242, 242));
    jClearButton.setText("Clear");
    jClearButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
    jClearButton.setBorderPainted(false);
    jClearButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jClearButtonActionPerformed(evt);
        }
    });

    jUpdateButton.setBackground(new java.awt.Color(242, 242, 242));
    jUpdateButton.setText("Update");
    jUpdateButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
    jUpdateButton.setBorderPainted(false);
    jUpdateButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jUpdateButtonActionPerformed(evt);
        }
    });

    jAddButton.setBackground(new java.awt.Color(239, 124, 18));
    jAddButton.setForeground(new java.awt.Color(255, 255, 255));
    jAddButton.setText("Add");
    jAddButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 124, 18)));
    jAddButton.setBorderPainted(false);
    jAddButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jAddButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jDurationText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jGenreComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jReleasedComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRatingComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTitleText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(chooseImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jFilePathText))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTitleLabel)
                        .addComponent(jGenreLabel)
                        .addComponent(jRatingLabel)
                        .addComponent(jDateLabel)
                        .addComponent(jDurationLabel)
                        .addComponent(jMovieLabel))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
            .addGap(30, 30, 30))
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(113, 113, 113)
            .addComponent(moviePosterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(60, 60, 60)
            .addComponent(jMovieLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(moviePosterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(chooseImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFilePathText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jTitleLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTitleText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(8, 8, 8)
            .addComponent(jRatingLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jRatingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(8, 8, 8)
            .addComponent(jDateLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jReleasedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(8, 8, 8)
            .addComponent(jGenreLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jGenreComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jDurationLabel)
            .addGap(8, 8, 8)
            .addComponent(jDurationText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(54, 54, 54))
    );

    add(jPanel1, java.awt.BorderLayout.WEST);

    jPanel2.setOpaque(false);
    jPanel2.setPreferredSize(new java.awt.Dimension(780, 720));
    jPanel2.setRequestFocusEnabled(false);
    jPanel2.setVerifyInputWhenFocusTarget(false);
    jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            jPanel2formMouseMoved(evt);
        }
    });

    jMovieTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jMovieTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "ID", "Title ", "Rating", "Release year", "Genre", "Duration"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jMovieTable1.getTableHeader().setReorderingAllowed(false);
    jMovieTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jMovieTable1MouseReleased(evt);
        }
    });
    jScrollPane1.setViewportView(jMovieTable1);
    if (jMovieTable1.getColumnModel().getColumnCount() > 0) {
        jMovieTable1.getColumnModel().getColumn(0).setResizable(false);
        jMovieTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jMovieTable1.getColumnModel().getColumn(1).setResizable(false);
        jMovieTable1.getColumnModel().getColumn(2).setResizable(false);
        jMovieTable1.getColumnModel().getColumn(3).setResizable(false);
        jMovieTable1.getColumnModel().getColumn(4).setResizable(false);
        jMovieTable1.getColumnModel().getColumn(5).setResizable(false);
    }

    jRefreshButton.setBackground(new java.awt.Color(247, 222, 200));
    jRefreshButton.setText("Refresh");
    jRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(247, 222, 200)));
    jRefreshButton.setBorderPainted(false);
    jRefreshButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jRefreshButtonActionPerformed(evt);
        }
    });

    jSearchText1.setPreferredSize(null);
    jSearchText1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jSearchText1ActionPerformed(evt);
        }
    });

    jSearchButton.setBackground(new java.awt.Color(239, 124, 18));
    jSearchButton.setForeground(new java.awt.Color(255, 255, 255));
    jSearchButton.setText("Search");
    jSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 124, 18)));
    jSearchButton.setBorderPainted(false);
    jSearchButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jSearchButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addGap(55, 55, 55)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jSearchText1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(55, 55, 55))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(60, 60, 60)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jRefreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addComponent(jSearchText1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(56, 56, 56))
    );

    add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:     
    }//GEN-LAST:event_formMouseMoved

    private void jFilePathTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFilePathTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFilePathTextActionPerformed

    private void jDurationTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDurationTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDurationTextActionPerformed

    private void chooseImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseImageButtonActionPerformed

        //CHOOSE IMAGE//
        //Opens the file chooser to select an image//

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "png", "jpg");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filepath = fileChooser.getSelectedFile().getAbsolutePath();
            Image image = null;
            try {
                image = ImageIO.read(selectedFile).getScaledInstance(150,225,Image.SCALE_DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ImageIcon icon = new ImageIcon(image);
            moviePosterLabel.setIcon(icon);
            jFilePathText.setText(filepath);
        }
    }//GEN-LAST:event_chooseImageButtonActionPerformed

    private void jDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteButtonActionPerformed
        // TODO add your handling code here:

        //DELETE//
        //Deletes the entry selected on the table//

        if(!jMovieTable1.getSelectionModel().isSelectionEmpty()){

            int confirmation=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this entry?");

            if(confirmation==JOptionPane.YES_OPTION){
                try {

                    Connector connector = new Connector();
                    Connection con = connector.getConnection();

                    int selectedRow = jMovieTable1.getSelectedRow();
                    int movieid_column = 0;
                    int movie_table_id = (int) jMovieTable1.getModel().getValueAt(selectedRow, movieid_column);

                    String query = "DELETE FROM movies WHERE movie_id = ?";

                    PreparedStatement prepStmt = con.prepareStatement(query);
                    prepStmt.setInt(1, movie_table_id);

                    prepStmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Entry has been deleted!");

                } catch (SQLException e) {
                    System.out.println(e);
                }

                clearEverything();
            }
        }

        else{
            JOptionPane.showMessageDialog(this,"No table row was selected.","Alert",JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jDeleteButtonActionPerformed

    private void jAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddButtonActionPerformed
        // TODO add your handling code here:

        //ADD//
        //Adds an entry into both the database and the table//

        String title = jTitleText.getText();
        String rating =  (String) jRatingComboBox.getSelectedItem();
        String released = (String) jReleasedComboBox.getSelectedItem();
        String genre = (String) jGenreComboBox.getSelectedItem();
        String duration = jDurationText.getText();
        String imagePath = jFilePathText.getText();

        if("".equals(title)){
            JOptionPane.showMessageDialog(this,"Some of the information needed is missing.","Alert",JOptionPane.WARNING_MESSAGE);
        }

        else if("".equals(duration)){
            JOptionPane.showMessageDialog(this,"Some of the information needed is missing.","Alert",JOptionPane.WARNING_MESSAGE);
        }

        else{

            int releasedInt = Integer.parseInt(released);
            int durationInt = Integer.parseInt(jDurationText.getText());

            String query = "INSERT INTO movies (title, rating, released, genre, duration, image) VALUES (?, ?, ?, ?, ?, ?)";

            try{

                Connector connector = new Connector();
                Connection con = connector.getConnection();

                PreparedStatement prepStmt = con.prepareStatement(query);

                prepStmt.setString(1, title);
                prepStmt.setString(2, rating);
                prepStmt.setInt(3, releasedInt);
                prepStmt.setString(4, genre);
                prepStmt.setInt(5, durationInt);
                InputStream in;
                try {
                    in = new FileInputStream(imagePath);
                    prepStmt.setBlob(6, in);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MoviePanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                prepStmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e);
            }

            clearEverything();

            JOptionPane.showMessageDialog(this, "Your entry has been added!");
        }

    }//GEN-LAST:event_jAddButtonActionPerformed

    private void jUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateButtonActionPerformed
        // TODO add your handling code here:

        //UPDATE//
        //Updates both the database and the table//

        if("".equals(jTitleText.getText())){
            JOptionPane.showMessageDialog(this,"Some of the information needed is missing.","Alert",JOptionPane.WARNING_MESSAGE);
        }

        else if("".equals(jDurationText.getText())){
            JOptionPane.showMessageDialog(this,"Some of the information needed is missing.","Alert",JOptionPane.WARNING_MESSAGE);
        }

        else{

            if(!jMovieTable1.getSelectionModel().isSelectionEmpty()){

                String title = jTitleText.getText();
                String rating = (String) jRatingComboBox.getSelectedItem();
                String released = (String) jReleasedComboBox.getSelectedItem();
                String genre = (String) jGenreComboBox.getSelectedItem();
                String duration = jDurationText.getText();
                String imagePath = jFilePathText.getText();

                int selectedRow = jMovieTable1.getSelectedRow();
                int movie_id_column = 0;
                int movie_id = (int)jMovieTable1.getModel().getValueAt(selectedRow, movie_id_column);

                if(!"".equals(jFilePathText.getText())){

                    try {
                        Connector connector = new Connector();
                        Connection con = connector.getConnection();

                        String query = "UPDATE movies SET image = ? WHERE movie_id = ?";

                        PreparedStatement prepStmt = con.prepareStatement(query);

                        InputStream in;
                        try {
                            in = new FileInputStream(imagePath);
                            prepStmt.setBlob(1, in);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MoviePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        prepStmt.setInt(2, movie_id);

                        prepStmt.executeUpdate();

                        clearEverything();

                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                }

                String query = "UPDATE movies SET title = ?, rating = ?, released = ?, genre = ?, duration = ? WHERE movie_id = ?";

                try {
                    Connector connector = new Connector();
                    Connection con = connector.getConnection();

                    int releasedInt = Integer.parseInt(released);
                    int durationInt = Integer.parseInt(jDurationText.getText());

                    PreparedStatement prepStmt = con.prepareStatement(query);

                    prepStmt.setString(1, title);
                    prepStmt.setString(2, rating);
                    prepStmt.setInt(3, releasedInt);
                    prepStmt.setString(4, genre);
                    prepStmt.setInt(5, durationInt);
                    prepStmt.setInt(6, movie_id);

                    prepStmt.executeUpdate();

                } catch (SQLException e) {
                    System.out.println(e);
                }

                clearEverything();

                JOptionPane.showMessageDialog(this, "Your changes have been applied!");

            }

            else{
                JOptionPane.showMessageDialog(this,"No table row was selected.","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }

        refreshTable();

    }//GEN-LAST:event_jUpdateButtonActionPerformed

    private void jClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearButtonActionPerformed
        // TODO add your handling code here:

        //CLEAR//
        int confirmation=JOptionPane.showConfirmDialog(this,"Are you sure you want to clear everything?");

        if(confirmation==JOptionPane.YES_OPTION){
            clearEverything();
        }

    }//GEN-LAST:event_jClearButtonActionPerformed

    private void jPanel2formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2formMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2formMouseMoved

    private void jRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshButtonActionPerformed
        // TODO add your handling code here:

        //REFRESH//
        //Refreshes/resets the table//

        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

            String query = "SELECT movie_id, title, rating, released, genre, duration FROM movies";

            try (PreparedStatement pstmt = con.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery()) {

                DefaultTableModel model = (DefaultTableModel) jMovieTable1.getModel();
                model.setRowCount(0);

                while (resultSet.next()) {
                    int movieId = resultSet.getInt("movie_id");
                    String title = resultSet.getString("title");
                    String rating = resultSet.getString("rating");
                    int released = resultSet.getInt("released");
                    String genre = resultSet.getString("genre");
                    int duration = resultSet.getInt("duration");

                    model.addRow(new Object[]{movieId, title, rating, released, genre, duration});
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jRefreshButtonActionPerformed

    private void jMovieTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMovieTable1MouseReleased
        // TODO add your handling code here:

        //TABLE IS CLICKED//
        //Provides the needed variables when a row is selected on the table//

        int selectedRow = jMovieTable1.getSelectedRow();
        int movie_id_column = 0;
        int title_column = 1;
        int rating_column = 2;
        int release_date_column = 3;
        int genre_column = 4;
        int duration_column = 5;
        String movie_id = jMovieTable1.getModel().getValueAt(selectedRow, movie_id_column).toString();

        if(selectedRow!=-1){

            jTitleText.setText(jMovieTable1.getModel().getValueAt(selectedRow, title_column).toString());
            jRatingComboBox.setSelectedItem(jMovieTable1.getModel().getValueAt(selectedRow, rating_column).toString());
            jReleasedComboBox.setSelectedItem(jMovieTable1.getModel().getValueAt(selectedRow, release_date_column).toString());
            jGenreComboBox.setSelectedItem(jMovieTable1.getModel().getValueAt(selectedRow, genre_column).toString());
            jDurationText.setText(jMovieTable1.getModel().getValueAt(selectedRow, duration_column).toString());

            try{

                Connector connector = new Connector();
                Connection con = connector.getConnection();

                String query = "SELECT image FROM movies WHERE movie_id = ?";

                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, movie_id);
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    byte[] blobImage = resultSet.getBytes("image");
                    Image image = convertBlobToImage(blobImage);
                    image = image.getScaledInstance(150,225,Image.SCALE_DEFAULT);

                    ImageIcon icon = new ImageIcon(image);
                    moviePosterLabel.setIcon(icon);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }   catch (IOException ex) {
                Logger.getLogger(MoviePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMovieTable1MouseReleased

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        // TODO add your handling code here:

        //SEARCH//
        //Scours the dattabase for the inputed movie title and shows results in the table//

        String searchInput = jSearchText1.getText();

        try {
            Connector connector = new Connector();
            Connection con = connector.getConnection();

            String query = "SELECT movie_id, title, rating, released, genre, duration FROM movies WHERE title LIKE ?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%" + searchInput + "%");
            ResultSet resultSet = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jMovieTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                int movieId = resultSet.getInt("movie_id");
                String title = resultSet.getString("title");
                String rating = resultSet.getString("rating");
                int released = resultSet.getInt("released");
                String genre = resultSet.getString("genre");
                int duration = resultSet.getInt("duration");

                model.addRow(new Object[]{movieId, title, rating, released, genre, duration});
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jSearchButtonActionPerformed

    private void jSearchText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchText1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseImageButton;
    private javax.swing.JButton jAddButton;
    private javax.swing.JButton jClearButton;
    private javax.swing.JLabel jDateLabel;
    private javax.swing.JButton jDeleteButton;
    private javax.swing.JLabel jDurationLabel;
    private javax.swing.JTextField jDurationText;
    private javax.swing.JTextField jFilePathText;
    private javax.swing.JComboBox<String> jGenreComboBox;
    private javax.swing.JLabel jGenreLabel;
    private javax.swing.JLabel jMovieLabel;
    private javax.swing.JTable jMovieTable1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> jRatingComboBox;
    private javax.swing.JLabel jRatingLabel;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JComboBox<String> jReleasedComboBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchText1;
    private javax.swing.JLabel jTitleLabel;
    private javax.swing.JTextField jTitleText;
    private javax.swing.JButton jUpdateButton;
    private javax.swing.JLabel moviePosterLabel;
    // End of variables declaration//GEN-END:variables
}
