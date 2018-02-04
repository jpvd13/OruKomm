package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import orukomm.data.entities.Meeting;
import orukomm.data.entities.User;
import orukomm.data.repositories.UserRepository;
import orukomm.gui.MainWindow;

/*
 * Panel that displays response options such as RSVP and submitting meeting's starting
 * time preference.
 */
public class MeetingResponse extends javax.swing.JPanel {

    private UserRepository userRepo;
    
    private Meeting meeting;
    private User createdBy;
    
    public MeetingResponse(Meeting meeting, MainWindow parentFrame) {
        initComponents();
        this.meeting = meeting;
        userRepo = new UserRepository();
        createdBy = userRepo.getById(meeting.getMeetingCallerUserId());
        
        
        // Set meeting info.
        lblMeetingTitle.setText(meeting.getTitle());
        lblDataData.setText(meeting.getDate().toString());
        lblCreatedByUser.setText(createdBy.toString());
        
        // Back to my meetings panel button event.
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchPanel(new Meetings((parentFrame)));
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMeetingContainer = new javax.swing.JPanel();
        lblMeetingTitle = new javax.swing.JLabel();
        lblCreatedBy = new javax.swing.JLabel();
        lblCreatedByUser = new javax.swing.JLabel();
        lblDuration = new javax.swing.JLabel();
        lblDurationData = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblDataData = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        pnlMeetingContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(193, 193, 193), 1, true));

        lblMeetingTitle.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblMeetingTitle.setText("Mötestitel");

        lblCreatedBy.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblCreatedBy.setText("Möte skapat av");

        lblCreatedByUser.setText("User");

        lblDuration.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblDuration.setText("Varaktighet");

        lblDurationData.setText("2^32 timmar");

        lblDate.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblDate.setText("Datum");

        lblDataData.setText("2018-01-01");

        btnBack.setText("Tillbaka");

        javax.swing.GroupLayout pnlMeetingContainerLayout = new javax.swing.GroupLayout(pnlMeetingContainer);
        pnlMeetingContainer.setLayout(pnlMeetingContainerLayout);
        pnlMeetingContainerLayout.setHorizontalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addComponent(lblMeetingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(lblDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDataData, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(lblCreatedBy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCreatedByUser, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(lblDuration)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDurationData, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 437, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMeetingContainerLayout.setVerticalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMeetingTitle)
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(lblDataData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCreatedBy)
                    .addComponent(lblCreatedByUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuration)
                    .addComponent(lblDurationData))
                .addContainerGap(501, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel lblCreatedBy;
    private javax.swing.JLabel lblCreatedByUser;
    private javax.swing.JLabel lblDataData;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblDurationData;
    private javax.swing.JLabel lblMeetingTitle;
    private javax.swing.JPanel pnlMeetingContainer;
    // End of variables declaration//GEN-END:variables
}
