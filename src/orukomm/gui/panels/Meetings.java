package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import orukomm.data.entities.Meeting;
import orukomm.data.repositories.MeetingRepository;
import orukomm.gui.MainWindow;

/*
 * Panel that displays all meetings related to the logged in user.
 */
public class Meetings extends javax.swing.JPanel {

    private MainWindow parentFrame;
    private MeetingRepository meetingRepo;

    private DefaultListModel<Meeting> lstMdlInvitations;
    private DefaultListModel<Meeting> lstMdlCreatedMeeting;

    private ArrayList<Meeting> meetingInvitations;
    private ArrayList<Meeting> meetingsCreated;

    private Meeting selectedInvitationMeeting;
    private Meeting selectedCreatedMeeting;

    public Meetings(MainWindow parentFrame) {
        initComponents();
        this.parentFrame = parentFrame;

        meetingRepo = new MeetingRepository();

        lstMdlCreatedMeeting = new DefaultListModel<>();
        lstMeetingsCreated.setModel(lstMdlCreatedMeeting);
        lstMdlInvitations = new DefaultListModel<>();
        lstMeetingInvitations.setModel(lstMdlInvitations);

        refreshInvitedMeetingsList();
        refreshCreatedMeetingsList();
        
        // Show invited meeting event.
        btnInvitationsShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchPanel(new MeetingResponse(lstMeetingInvitations.getSelectedValue(), parentFrame));
            }
        });
        
        // Show created meeting event.
    }

    private void refreshInvitedMeetingsList() {
        lstMdlInvitations.removeAllElements();

        meetingInvitations = meetingRepo.getMeetingInvitations(parentFrame.loggedInUser.getId());

        if (meetingInvitations.size() > 0)
            for (Meeting meeting : meetingInvitations)
                lstMdlInvitations.addElement(meeting);
    }

    private void refreshCreatedMeetingsList() {
        lstMdlCreatedMeeting.removeAllElements();

        meetingsCreated = meetingRepo.getCreatedMeetings(parentFrame.loggedInUser.getId());

        if (meetingsCreated.size() > 0)
            for (Meeting meeting : meetingsCreated)
                lstMdlCreatedMeeting.addElement(meeting);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMeetingsContainer = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        lblCreated = new javax.swing.JLabel();
        lblInvitations = new javax.swing.JLabel();
        scrLstInvitations = new javax.swing.JScrollPane();
        lstMeetingsCreated = new javax.swing.JList<>();
        scrLstInvitations1 = new javax.swing.JScrollPane();
        lstMeetingInvitations = new javax.swing.JList<>();
        btnInvitationsShow = new javax.swing.JButton();
        btnCreatedShow = new javax.swing.JButton();

        pnlMeetingsContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(193, 193, 193), 1, true));

        lblHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblHeading.setText("Mina möten");

        lblCreated.setText("Skapade möten");

        lblInvitations.setText("Inbjudningar");

        scrLstInvitations.setViewportView(lstMeetingsCreated);

        scrLstInvitations1.setViewportView(lstMeetingInvitations);

        btnInvitationsShow.setText("Visa");

        btnCreatedShow.setText("Visa");

        javax.swing.GroupLayout pnlMeetingsContainerLayout = new javax.swing.GroupLayout(pnlMeetingsContainer);
        pnlMeetingsContainer.setLayout(pnlMeetingsContainerLayout);
        pnlMeetingsContainerLayout.setHorizontalGroup(
            pnlMeetingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingsContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeading)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMeetingsContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMeetingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnInvitationsShow)
                    .addGroup(pnlMeetingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblInvitations)
                        .addComponent(scrLstInvitations1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlMeetingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeetingsContainerLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(pnlMeetingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrLstInvitations, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCreated)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMeetingsContainerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreatedShow)))
                .addContainerGap())
        );
        pnlMeetingsContainerLayout.setVerticalGroup(
            pnlMeetingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingsContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCreated)
                    .addComponent(lblInvitations))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrLstInvitations, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrLstInvitations1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMeetingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInvitationsShow)
                    .addComponent(btnCreatedShow))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(pnlMeetingsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMeetingsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreatedShow;
    private javax.swing.JButton btnInvitationsShow;
    private javax.swing.JLabel lblCreated;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblInvitations;
    private javax.swing.JList<Meeting> lstMeetingInvitations;
    private javax.swing.JList<Meeting> lstMeetingsCreated;
    private javax.swing.JPanel pnlMeetingsContainer;
    private javax.swing.JScrollPane scrLstInvitations;
    private javax.swing.JScrollPane scrLstInvitations1;
    // End of variables declaration//GEN-END:variables
}
