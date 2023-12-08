/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.cms.gui.movies;
import javax.swing.JPanel;

/**
 *
 * @author marks
 */
public class DateTimePanel extends javax.swing.JPanel {

    JPanel scrollCont;
    /**
     * Creates new form dateTimePanel
     */
    public DateTimePanel(JPanel scrollCont) {
        initComponents();
        jDateSpinner.setName("dateSpinner");
        jTimeStartSpinner.setName("timeStartSpinner");
        jTimeLabel1.setName("timeLabel");
        jShowDateLabel1.setName("dateLabel");
        jDeleteShowtimeButton.setName("deleteButton");
        this.scrollCont = scrollCont;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jShowDateLabel1 = new javax.swing.JLabel();
        jTimeLabel1 = new javax.swing.JLabel();
        jDateSpinner = new javax.swing.JSpinner();
        jTimeStartSpinner = new javax.swing.JSpinner();
        jDeleteShowtimeButton = new javax.swing.JButton();

        jShowDateLabel1.setText("Show Date");

        jTimeLabel1.setText("Time");

        jDateSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1701677403931L), null, null, java.util.Calendar.DAY_OF_MONTH));
        jDateSpinner.setEditor(new javax.swing.JSpinner.DateEditor(jDateSpinner, "MM/dd/yyyy"));
        jDateSpinner.setName(""); // NOI18N

        jTimeStartSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jTimeStartSpinner.setEditor(new javax.swing.JSpinner.DateEditor(jTimeStartSpinner, "hh:mm aa"));

        jDeleteShowtimeButton.setText("Delete");
        jDeleteShowtimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteShowtimeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTimeLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jShowDateLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTimeStartSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDeleteShowtimeButton))
                    .addComponent(jDateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jShowDateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDeleteShowtimeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTimeStartSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jDeleteShowtimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteShowtimeButtonActionPerformed
        scrollCont.remove(this);
        scrollCont.revalidate();
        scrollCont.repaint();
    }//GEN-LAST:event_jDeleteShowtimeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner jDateSpinner;
    private javax.swing.JButton jDeleteShowtimeButton;
    private javax.swing.JLabel jShowDateLabel1;
    private javax.swing.JLabel jTimeLabel1;
    private javax.swing.JSpinner jTimeStartSpinner;
    // End of variables declaration//GEN-END:variables
}
