/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.gui.panels;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import orukomm.data.entities.Meeting;
import orukomm.data.repositories.MeetingRepository;
import orukomm.gui.MainWindow;

/**
 *
 * @author admin
 */
public class EditAndDeleteMeetings extends javax.swing.JPanel {
    
    private MeetingRepository meetingRepo;
    
    private DefaultListModel<Meeting> listMdlAllMeetings;
    private ArrayList<Meeting> allMeetings;
    private MainWindow parentFrame;
    
    
    public EditAndDeleteMeetings(MainWindow parentFrame) {
        initComponents();
        
        listMdlAllMeetings = new DefaultListModel();
        listAllMeetings.setModel(listMdlAllMeetings);
        
        allMeetings = new ArrayList<>();
        this.parentFrame = parentFrame;
        meetingRepo = new MeetingRepository();
        
        listAllMeetings.setSelectedIndex(0);
        refreshMeetingList();
    }
    
    private void refreshMeetingList(){
        listMdlAllMeetings.removeAllElements();
        allMeetings = meetingRepo.getAllMeetings();
        
        for(Meeting mets : allMeetings){
            listMdlAllMeetings.addElement(mets);
        }
    }
        
    private void deleteMeeting(){
      Meeting met = listAllMeetings.getSelectedValue();

       int id = met.getId();

       meetingRepo.deleteMeeting(id);
   
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listAllMeetings = new javax.swing.JList<>();
        btnDeleteMeeting = new javax.swing.JButton();
        btnEditMeeting = new javax.swing.JButton();

        jScrollPane1.setViewportView(listAllMeetings);

        btnDeleteMeeting.setText("Radera möte");
        btnDeleteMeeting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMeetingActionPerformed(evt);
            }
        });

        btnEditMeeting.setText("Redigera möte");
        btnEditMeeting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMeetingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(btnDeleteMeeting)
                        .addGap(125, 125, 125)
                        .addComponent(btnEditMeeting)))
                .addContainerGap(419, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteMeeting)
                    .addComponent(btnEditMeeting))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteMeetingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMeetingActionPerformed
         JOptionPane.showMessageDialog(null, "Möte: " + listAllMeetings.getSelectedValue() + " borttaget.");
         deleteMeeting();
         refreshMeetingList();
    }//GEN-LAST:event_btnDeleteMeetingActionPerformed

    private void btnEditMeetingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMeetingActionPerformed
        parentFrame.switchPanel(new editMeetingpnl(parentFrame, listAllMeetings.getSelectedValue()));
    }//GEN-LAST:event_btnEditMeetingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteMeeting;
    private javax.swing.JButton btnEditMeeting;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<Meeting> listAllMeetings;
    // End of variables declaration//GEN-END:variables
}
