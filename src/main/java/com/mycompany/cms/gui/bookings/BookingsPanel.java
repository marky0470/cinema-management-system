/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.bookings;

import com.mycompany.cms.util.Connector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author francisjamestolentino
 */
public class BookingsPanel extends javax.swing.JPanel {
    
    Connection conn;

    /**
     * Creates new form BookingsPanel
     */
    public BookingsPanel() {
        initComponents();
        
        Connector connector = new Connector();
        conn = connector.getConnection();
        
        try {
            getScreenings();    
        } catch (IOException e) {
            
        }
        
    }
    
    private void getScreenings() throws IOException {
        try {
            Statement statement = conn.createStatement();
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
                             LEFT JOIN cinemas ON screening.cinema_id = cinemas.cinema_id
                         ) screening
                         WHERE
                             screening.date = CURRENT_DATE();""";
            
            ResultSet res = statement.executeQuery(sql);
            
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
            
        }
    }
    
    private static JPanel createCardPanel(int movieId, String title, BufferedImage image) {
        JPanel cardPanel = new JPanel();
        
        cardPanel.setBorder(new CompoundBorder(
            new LineBorder(Color.getHSBColor(216, 3, 70), 1, true),
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jMoviesPanel = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(955, 0));
        setRequestFocusEnabled(false);
        setSize(new java.awt.Dimension(955, 0));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Bookings");

        jScrollPane.setBackground(new java.awt.Color(242, 242, 242));
        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setToolTipText("");

        javax.swing.GroupLayout jMoviesPanelLayout = new javax.swing.GroupLayout(jMoviesPanel);
        jMoviesPanel.setLayout(jMoviesPanelLayout);
        jMoviesPanelLayout.setHorizontalGroup(
            jMoviesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1235, Short.MAX_VALUE)
        );
        jMoviesPanelLayout.setVerticalGroup(
            jMoviesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 762, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(jMoviesPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(1116, Short.MAX_VALUE))
            .addComponent(jScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jMoviesPanel;
    private javax.swing.JScrollPane jScrollPane;
    // End of variables declaration//GEN-END:variables
}
