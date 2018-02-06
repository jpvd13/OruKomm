package orukomm.gui.panels;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import orukomm.data.entities.Meeting;
import orukomm.data.entities.User;
import orukomm.data.repositories.MeetingRepository;
import orukomm.gui.MainWindow;

public class CreatedMeeting extends javax.swing.JPanel {

    private Meeting meeting;
    private MainWindow parentFrame;
    
    private MeetingRepository meetingRepo;
    
    private ArrayList<User> attendingUsers;
    private DefaultListModel<User> lstMdlAttendingUsers;
    
    public CreatedMeeting(Meeting meeting, MainWindow parentFrame) {
        initComponents();
        this.meeting = meeting;
        this.parentFrame = parentFrame;
        attendingUsers = new ArrayList<User>();
        meetingRepo = new MeetingRepository();
        
        lstMdlAttendingUsers = new DefaultListModel<>();
        lstAttendingUsers.setModel(lstMdlAttendingUsers);
        refreshAttendingUsers();
    }

    public void refreshAttendingUsers() {
        lstMdlAttendingUsers.removeAllElements();
        // Add confirmed attendees.
        for (User user : meeting.getInvitedUsers()) {
            if (meetingRepo.getMeetingAttendance(user.getId(), meeting.getId())) {
                lstMdlAttendingUsers.addElement(user);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMeetingCreatedContainer = new javax.swing.JPanel();
        lblMeetingTitle = new javax.swing.JLabel();
        lblDuration = new javax.swing.JLabel();
        lblDurationData = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblDataData = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        pnlCheckBoxes = new javax.swing.JPanel();
        lblChooseTime = new javax.swing.JLabel();
        scrLstAttendingUsers = new javax.swing.JScrollPane();
        lstAttendingUsers = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();

        pnlMeetingCreatedContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(193, 193, 193), 1, true));

        lblMeetingTitle.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblMeetingTitle.setText("Mötestitel");

        lblDuration.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblDuration.setText("Varaktighet");

        lblDurationData.setText("2^32 timmar");

        lblDate.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblDate.setText("Datum");

        lblDataData.setText("2018-01-01");

        btnBack.setText("Tillbaka");

        javax.swing.GroupLayout pnlCheckBoxesLayout = new javax.swing.GroupLayout(pnlCheckBoxes);
        pnlCheckBoxes.setLayout(pnlCheckBoxesLayout);
        pnlCheckBoxesLayout.setHorizontalGroup(
            pnlCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );
        pnlCheckBoxesLayout.setVerticalGroup(
            pnlCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        lblChooseTime.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblChooseTime.setText("Tidsförslag");

        scrLstAttendingUsers.setViewportView(lstAttendingUsers);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setText("Bekräftade deltagare");

        javax.swing.GroupLayout pnlMeetingCreatedContainerLayout = new javax.swing.GroupLayout(pnlMeetingCreatedContainer);
        pnlMeetingCreatedContainer.setLayout(pnlMeetingCreatedContainerLayout);
        pnlMeetingCreatedContainerLayout.setHorizontalGroup(
            pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingCreatedContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeetingCreatedContainerLayout.createSequentialGroup()
                        .addGroup(pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMeetingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMeetingCreatedContainerLayout.createSequentialGroup()
                                .addComponent(lblDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDataData, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(pnlMeetingCreatedContainerLayout.createSequentialGroup()
                        .addGroup(pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMeetingCreatedContainerLayout.createSequentialGroup()
                                .addComponent(lblDuration)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDurationData, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMeetingCreatedContainerLayout.createSequentialGroup()
                                .addGroup(pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblChooseTime)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCheckBoxes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrLstAttendingUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 139, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMeetingCreatedContainerLayout.setVerticalGroup(
            pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingCreatedContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeetingCreatedContainerLayout.createSequentialGroup()
                        .addComponent(lblMeetingTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDate)
                            .addComponent(lblDataData)))
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuration)
                    .addComponent(lblDurationData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingCreatedContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCheckBoxes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMeetingCreatedContainerLayout.createSequentialGroup()
                        .addComponent(lblChooseTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrLstAttendingUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(pnlMeetingCreatedContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMeetingCreatedContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblChooseTime;
    private javax.swing.JLabel lblDataData;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblDurationData;
    private javax.swing.JLabel lblMeetingTitle;
    private javax.swing.JList<User> lstAttendingUsers;
    private javax.swing.JPanel pnlCheckBoxes;
    private javax.swing.JPanel pnlMeetingCreatedContainer;
    private javax.swing.JScrollPane scrLstAttendingUsers;
    // End of variables declaration//GEN-END:variables
}
