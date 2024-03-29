/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Options.java
 *
 * Created on 03-Aug-2010, 14:19:17
 */

package phoniex.GUI;

import phoniex.GUI.WTextPane;
import phoniex.GUI.PhoenixView;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class Options extends javax.swing.JFrame {

    /** Creates new form Options */
    public Options() {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        watermark.setSelected(!WTextPane.isShowWatermark());
        offline.setSelected(PhoenixView.getReusageView().isOffline());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        watermark = new javax.swing.JCheckBox();
        offline = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(Options.class);
        watermark.setText(resourceMap.getString("watermark.text")); // NOI18N
        watermark.setName("watermark"); // NOI18N
        watermark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                watermarkActionPerformed(evt);
            }
        });

        offline.setText(resourceMap.getString("offline.text")); // NOI18N
        offline.setName("offline"); // NOI18N
        offline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offlineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(offline, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(watermark, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(watermark)
                .addGap(32, 32, 32)
                .addComponent(offline)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void watermarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_watermarkActionPerformed
        WTextPane.setShowWatermark(!WTextPane.isShowWatermark());
        PhoenixView.getReusageView().update();
    }//GEN-LAST:event_watermarkActionPerformed

    private void offlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offlineActionPerformed
       PhoenixView.getReusageView().setOffline(!PhoenixView.getReusageView().isOffline());
       PhoenixView.getReusageView().update("offline");
    }//GEN-LAST:event_offlineActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox offline;
    private javax.swing.JCheckBox watermark;
    // End of variables declaration//GEN-END:variables

}
