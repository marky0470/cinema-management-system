/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.admin;

import com.mycompany.cms.models.SalesData;
import com.mycompany.cms.util.Connector;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author francisjamestolentino
 */
public class DashboardPanel extends javax.swing.JPanel {
    
    private final Connector connector = new Connector();
    private final Connection conn = connector.getConnection();
    private final SpinnerDateModel spinnerModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
    private final HashMap<Integer, Object[]> posters = new HashMap();

    /**
     * Creates new form DashboardPanel
     */
    public DashboardPanel() {
        initComponents();
        
        jDateSpinner.setModel(spinnerModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jDateSpinner, "MM/dd/yyyy");
        jDateSpinner.setEditor(editor);
        jDateSpinner.addChangeListener(getDateSpinnerChangeListener());
        
        posters.put(1, new Object[]{ jMoviePoster1, jMovieTitle1 } );
        posters.put(2, new Object[]{ jMoviePoster2, jMovieTitle2 } );
        posters.put(3, new Object[]{ jMoviePoster3, jMovieTitle3 } );
        
        changeDateLabel();
        refresh();
    }
    
    private void refresh() {
        getNumberOfCinemas();
        getNumberOfMovies();
        getTicketsSoldOnDate();
        getTotalSalesOnDate();
        getTotalWeeklySalesOnDate();
        getTotalMonthlySalesOnDate();
        createSalesChart();
        clearTopMoviesPlaceHolders();
        getTopMovies();
    }
    
    private void clearTopMoviesPlaceHolders() {
        for (Map.Entry<Integer, Object[]> entry : posters.entrySet()) {
            Object[] objects = entry.getValue();
            JPanel poster = (JPanel) objects[0];
            JLabel title = (JLabel) objects[1];
            poster.removeAll();
            title.setText("");
        }
    }
    
    private void getTopMovies() {
        try {
            clearTopMoviesPlaceHolders();
            Date selectedDate = getSelectedDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(selectedDate);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date earlierDate = cal.getTime();
            
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            Date lastDate = cal.getTime();
            
            String sql = """
                         SELECT
                             SUM(tickets.price) AS sales,
                             movies.title,
                             movies.image
                         FROM
                             tickets
                         LEFT JOIN screening ON tickets.screening_id = screening.screening_id
                         JOIN movies ON screening.movie_id = movies.movie_id
                         WHERE
                             tickets.date BETWEEN ? AND ?
                         GROUP BY
                             movies.title
                         ORDER BY
                             sales DESC
                         LIMIT 3;
                         """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(earlierDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(lastDate.getTime()));
            
            ResultSet result = preparedStatement.executeQuery();
            
            int index = 1;
            while (result.next()) {
                JPanel posterPanel = (JPanel) posters.get(index)[0];
                JLabel movieTitleLabel = (JLabel) posters.get(index)[1];
                
                String title = result.getString("title");
                InputStream stream = result.getBinaryStream("image");
                
                if (stream != null) {
                    BufferedImage image = ImageIO.read(stream);
                    ImageIcon imageIcon = new ImageIcon(image);
                    Image scaledImage = imageIcon.getImage().getScaledInstance(150,250, Image.SCALE_SMOOTH);
                    
                    JLabel labelImage = new JLabel(new ImageIcon(scaledImage));
                    posterPanel.add(labelImage, BorderLayout.CENTER);
                } else {
                    posterPanel.add(new JLabel("No Image"), BorderLayout.CENTER);
                }
                
                movieTitleLabel.setText(title);
                
                index++;
            }
        } catch (SQLException | IOException e) {
            System.out.println(e);
        }
    }
    
    private void createSalesChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<SalesData> salesData = getSalesData();
        
        Collections.sort(salesData, new Comparator<SalesData>(){
            @Override
            public int compare(SalesData o1, SalesData o2) {
                return o1.compareTo(o2);
            }
        });
        
        for (SalesData sales : salesData) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dataset.addValue(sales.getSales(), "Sales", formatter.format(sales.getDate()));
        }
        
        JFreeChart chart = ChartFactory.createLineChart(
            "5 Day Span Sales",
            "Dates",
            "Sales",
            dataset
        );
        
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(555, 433));
        
        chart.setBackgroundPaint(new Color(253,253,253));
        chart.getPlot().setBackgroundPaint(new Color(253,253,253));
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(239,124,18));
        
        CategoryPlot plot = chart.getCategoryPlot();

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

        float thickness = 2.0f; // Adjust the thickness as needed
        BasicStroke lineStroke = new BasicStroke(thickness);
        renderer.setSeriesStroke(0, lineStroke);
        renderer.setSeriesFillPaint(0, new Color(239, 124, 18));
        
        jChartPanel.removeAll();
        jChartPanel.add(panel, BorderLayout.CENTER);
        jChartPanel.revalidate();
        jChartPanel.repaint();
        revalidate();
        repaint();
    }
    
    private boolean containsDate(ArrayList<SalesData> array, Date date) {
        for (SalesData salesData : array) {
            if (salesData.getDate().getTime() == date.getTime()) return true;
        }
        return false;
    }
    
    private ArrayList<SalesData> getSalesData() {
        try {
            Date selectedDate = getSelectedDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(selectedDate);
            cal.add(Calendar.DATE, -4);
            Date earlierDate = cal.getTime();
            
            String sql = """
                         SELECT
                             COUNT(tickets.ticket_id) as tickets,
                             SUM(tickets.price) as sales,
                             tickets.date
                         FROM
                             tickets
                         LEFT JOIN screening on tickets.screening_id = screening.screening_id
                         JOIN movies on screening.movie_id = movies.movie_id
                         WHERE 
                         	tickets.date BETWEEN ? AND ?
                         GROUP BY tickets.date
                         ORDER BY tickets.date ASC;
                         """;
            PreparedStatement preparedStatement = conn.prepareCall(sql);
            preparedStatement.setDate(1, new java.sql.Date(earlierDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(selectedDate.getTime()));
            ResultSet result = preparedStatement.executeQuery();
            
            ArrayList<SalesData> array = new ArrayList();
            
            while (result.next()) {
                int sales = result.getInt("sales");
                int tickets = result.getInt("tickets");
                Date date = result.getDate("date");
                array.add(new SalesData(tickets, sales, date));
            }
            
            for (int i=0; i < 5; i++) {
                cal.setTime(selectedDate);
                cal.add(Calendar.DATE, -i);
                Date salesDate = cal.getTime();
                
                if (containsDate(array, salesDate)) continue;
                array.add(new SalesData(0, 0, salesDate));
            }
            
            return array;
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return null;
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
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jMonthlySalesDateLabel = new javax.swing.JLabel();
        jMonthlySalesLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jWeeklySalesLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jWeeklySalesDateLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jDailySalesDateLabel = new javax.swing.JLabel();
        jDailySalesLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTicketsSoldLabel = new javax.swing.JLabel();
        jTicketsSoldDateLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDateSpinner = new javax.swing.JSpinner();
        jDateLabel = new javax.swing.JLabel();
        jTodayButton = new javax.swing.JButton();
        jRefreshButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jMoviePoster1 = new javax.swing.JPanel();
        jMovieTitle1 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jMoviePoster2 = new javax.swing.JPanel();
        jMovieTitle2 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jMoviePoster3 = new javax.swing.JPanel();
        jMovieTitle3 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTopMoviesMonthLabel = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jNumberOfCinemasLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jNumberOfMoviesLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jChartPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1172, 150));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 0);
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        jPanel4.setBackground(new java.awt.Color(253, 253, 253));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanel4.setToolTipText("");
        jPanel4.setPreferredSize(new java.awt.Dimension(260, 150));

        jLabel3.setText("Total Monthly Sales");

        jMonthlySalesDateLabel.setText("Month");

        jMonthlySalesLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 30)); // NOI18N
        jMonthlySalesLabel.setText("jLabel5");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jMonthlySalesLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(jMonthlySalesDateLabel)
                        .addGap(21, 21, 21))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jMonthlySalesDateLabel))
                .addGap(41, 41, 41)
                .addComponent(jMonthlySalesLabel)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(253, 253, 253));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanel5.setToolTipText("");
        jPanel5.setPreferredSize(new java.awt.Dimension(260, 150));

        jWeeklySalesLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 30)); // NOI18N
        jWeeklySalesLabel.setText("jLabel5");

        jLabel7.setText("Total Weekly Sales");

        jWeeklySalesDateLabel.setText("Week");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jWeeklySalesLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(jWeeklySalesDateLabel)
                        .addGap(23, 23, 23))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jWeeklySalesDateLabel))
                .addGap(39, 39, 39)
                .addComponent(jWeeklySalesLabel)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(253, 253, 253));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanel6.setToolTipText("");
        jPanel6.setPreferredSize(new java.awt.Dimension(260, 150));

        jLabel9.setText("Total Daily Sales");

        jDailySalesDateLabel.setText("Date");

        jDailySalesLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 30)); // NOI18N
        jDailySalesLabel.setText("jLabel5");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jDailySalesLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jDailySalesDateLabel)
                        .addGap(23, 23, 23))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jDailySalesDateLabel))
                .addGap(41, 41, 41)
                .addComponent(jDailySalesLabel)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(253, 253, 253));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanel7.setToolTipText("");
        jPanel7.setPreferredSize(new java.awt.Dimension(260, 150));

        jTicketsSoldLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 30)); // NOI18N
        jTicketsSoldLabel.setText("jLabel5");

        jTicketsSoldDateLabel.setText("Date");

        jLabel14.setText("Total Tickets Sold");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jTicketsSoldLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(jTicketsSoldDateLabel)
                        .addGap(20, 20, 20))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTicketsSoldDateLabel))
                .addGap(43, 43, 43)
                .addComponent(jTicketsSoldLabel)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(1172, 70));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Dashboard");

        jDateLabel.setText("Date");

        jTodayButton.setBackground(new java.awt.Color(247, 222, 200));
        jTodayButton.setText("Today");
        jTodayButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(247, 222, 200)));
        jTodayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTodayButtonActionPerformed(evt);
            }
        });

        jRefreshButton.setBackground(new java.awt.Color(239, 124, 18));
        jRefreshButton.setForeground(new java.awt.Color(255, 255, 255));
        jRefreshButton.setText("Refresh");
        jRefreshButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(239, 124, 18), 1, true));
        jRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 639, Short.MAX_VALUE)
                .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTodayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateLabel))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jDateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTodayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setPreferredSize(new java.awt.Dimension(1172, 580));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel8.setPreferredSize(new java.awt.Dimension(600, 500));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel14.setPreferredSize(new java.awt.Dimension(587, 300));

        jPanel16.setPreferredSize(new java.awt.Dimension(162, 300));

        jMoviePoster1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        jMoviePoster1.setLayout(new java.awt.BorderLayout());

        jMovieTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMovieTitle1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMovieTitle1.setInheritsPopupMenu(false);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jMovieTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMoviePoster1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jMoviePoster1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMovieTitle1)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel16);

        jPanel17.setPreferredSize(new java.awt.Dimension(162, 300));

        jMoviePoster2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        jMoviePoster2.setLayout(new java.awt.BorderLayout());

        jMovieTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMovieTitle2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMovieTitle2.setInheritsPopupMenu(false);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jMovieTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMoviePoster2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jMoviePoster2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMovieTitle2)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel17);

        jPanel18.setPreferredSize(new java.awt.Dimension(162, 300));

        jMoviePoster3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        jMoviePoster3.setLayout(new java.awt.BorderLayout());

        jMovieTitle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMovieTitle3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMovieTitle3.setInheritsPopupMenu(false);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jMovieTitle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMoviePoster3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jMoviePoster3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMovieTitle3)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel18);

        jPanel10.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel15.setPreferredSize(new java.awt.Dimension(587, 50));

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel19.setText("Top Movies of the Month");

        jTopMoviesMonthLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jTopMoviesMonthLabel.setText("Month");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(jTopMoviesMonthLabel)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTopMoviesMonthLabel))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel15, java.awt.BorderLayout.PAGE_START);

        jPanel8.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel11.setPreferredSize(new java.awt.Dimension(587, 150));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 15));

        jPanel12.setBackground(new java.awt.Color(253, 253, 253));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanel12.setToolTipText("");
        jPanel12.setPreferredSize(new java.awt.Dimension(235, 130));
        jPanel12.setRequestFocusEnabled(false);

        jNumberOfCinemasLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 28)); // NOI18N
        jNumberOfCinemasLabel.setText("jLabel5");

        jLabel16.setText("# of Cinemas");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jNumberOfCinemasLabel)
                    .addComponent(jLabel16))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jNumberOfCinemasLabel)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(253, 253, 253));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanel13.setToolTipText("");
        jPanel13.setPreferredSize(new java.awt.Dimension(235, 130));
        jPanel13.setRequestFocusEnabled(false);

        jLabel18.setText("# of Movies");

        jNumberOfMoviesLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 28)); // NOI18N
        jNumberOfMoviesLabel.setText("jLabel5");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jNumberOfMoviesLabel)
                    .addComponent(jLabel18))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jNumberOfMoviesLabel)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel13);

        jPanel8.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel9.setPreferredSize(new java.awt.Dimension(585, 500));

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel17.setText("Sales Chart (Last 5 Days)");

        jChartPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel9, java.awt.BorderLayout.WEST);

        add(jPanel3, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jTodayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTodayButtonActionPerformed
        spinnerModel.setValue(new Date());
        refresh();
    }//GEN-LAST:event_jTodayButtonActionPerformed

    private void jRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshButtonActionPerformed
        refresh();
    }//GEN-LAST:event_jRefreshButtonActionPerformed

    private void getTotalMonthlySalesOnDate() {
        try {
            Date selectedDate = getSelectedDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(selectedDate);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date earlierDate = cal.getTime();
            
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            Date lastDate = cal.getTime();
            String sql = """
                         SELECT SUM(price) as sales FROM tickets WHERE date BETWEEN ? AND ?;
                         """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(earlierDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(lastDate.getTime()));
            ResultSet result = preparedStatement.executeQuery();
            
            while(result.next()) {
                int tickets = result.getInt("sales");
                jMonthlySalesLabel.setText(String.format("P%,d", tickets));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void getTotalWeeklySalesOnDate() {
        try {
            Date selectedDate = getSelectedDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(selectedDate);
            cal.add(Calendar.DATE, -7);
            Date earlierDate = cal.getTime();
            String sql = """
                         SELECT SUM(price) as sales FROM tickets WHERE date BETWEEN ? AND ?;
                         """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(earlierDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(selectedDate.getTime()));
            ResultSet result = preparedStatement.executeQuery();
            
            while(result.next()) {
                int tickets = result.getInt("sales");
                jWeeklySalesLabel.setText(String.format("P%,d", tickets));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void getTotalSalesOnDate() {
        try {
            Date selectedDate = getSelectedDate();
            String sql = """
                         SELECT SUM(price) as sales FROM tickets WHERE date = ?
                         """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(selectedDate.getTime()));
            ResultSet result = preparedStatement.executeQuery();
            
            while(result.next()) {
                int tickets = result.getInt("sales");
                jDailySalesLabel.setText(String.format("P%,d", tickets));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void getTicketsSoldOnDate() {
        try {
            Date selectedDate = getSelectedDate();
            String sql = """
                         SELECT COUNT(*) as tickets FROM tickets WHERE date = ?
                         """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(selectedDate.getTime()));
            ResultSet result = preparedStatement.executeQuery();
            
            while(result.next()) {
                int tickets = result.getInt("tickets");
                jTicketsSoldLabel.setText(String.format("%,d", tickets));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void getNumberOfCinemas() {
        try {
            String sql = """
                     SELECT COUNT(*) as cinemas FROM cinemas;
                     """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            
            while(result.next()) {
                int cinemas = result.getInt("cinemas");
                jNumberOfCinemasLabel.setText(String.format("%,d", cinemas));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void getNumberOfMovies() {
        try {
            String sql = """
                     SELECT COUNT(*) as movies FROM movies;
                     """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            
            while(result.next()) {
                int cinemas = result.getInt("movies");
                jNumberOfMoviesLabel.setText(String.format("%,d", cinemas));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void changeDateLabel() {
        Date selectedDate = (Date) getSelectedDate();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        jDateLabel.setText(selectedDate.toString());
        jTicketsSoldDateLabel.setText(formatter.format(selectedDate));
        jDailySalesDateLabel.setText(formatter.format(selectedDate));
        
        SimpleDateFormat formatterTwo = new SimpleDateFormat("MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(selectedDate);
        cal.add(Calendar.DATE, -7);
        Date earlierDate = cal.getTime();
        
        jWeeklySalesDateLabel.setText(formatterTwo.format(earlierDate) + " - " + formatterTwo.format(selectedDate));
        
        SimpleDateFormat formatterThree = new SimpleDateFormat("MMMM");
        Calendar calTwo = Calendar.getInstance();
        calTwo.setTime(selectedDate);
        calTwo.set(Calendar.DAY_OF_MONTH, 1);
        Date month = calTwo.getTime();
        
        jMonthlySalesDateLabel.setText(formatterThree.format(month));
        jTopMoviesMonthLabel.setText(formatterThree.format(month));
    }
    
    private Date getSelectedDate() {
        Date selectedDate = (Date) jDateSpinner.getValue();
        return selectedDate;
    }
    
    private ChangeListener getDateSpinnerChangeListener() {
        return (ChangeEvent e) -> {
            changeDateLabel();
            refresh();
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jChartPanel;
    private javax.swing.JLabel jDailySalesDateLabel;
    private javax.swing.JLabel jDailySalesLabel;
    private javax.swing.JLabel jDateLabel;
    private javax.swing.JSpinner jDateSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jMonthlySalesDateLabel;
    private javax.swing.JLabel jMonthlySalesLabel;
    private javax.swing.JPanel jMoviePoster1;
    private javax.swing.JPanel jMoviePoster2;
    private javax.swing.JPanel jMoviePoster3;
    private javax.swing.JLabel jMovieTitle1;
    private javax.swing.JLabel jMovieTitle2;
    private javax.swing.JLabel jMovieTitle3;
    private javax.swing.JLabel jNumberOfCinemasLabel;
    private javax.swing.JLabel jNumberOfMoviesLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JLabel jTicketsSoldDateLabel;
    private javax.swing.JLabel jTicketsSoldLabel;
    private javax.swing.JButton jTodayButton;
    private javax.swing.JLabel jTopMoviesMonthLabel;
    private javax.swing.JLabel jWeeklySalesDateLabel;
    private javax.swing.JLabel jWeeklySalesLabel;
    // End of variables declaration//GEN-END:variables
}
