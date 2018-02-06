package orukomm.gui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import orukomm.data.entities.Meeting;
import orukomm.data.entities.TimeSuggestion;
import orukomm.data.entities.User;
import orukomm.data.repositories.MeetingRepository;
import orukomm.data.repositories.UserRepository;
import orukomm.gui.MainWindow;

/*
 * Panel that displays response options such as RSVP and submitting meeting's starting
 * time preference.
 */
public class MeetingResponse extends javax.swing.JPanel {

    private UserRepository userRepo;
    private MeetingRepository meetingRepo;

    private Meeting meeting;
    private User createdBy;
    
    // Mapping time suggestion id to checkboxes.
    private HashMap<Integer, JCheckBox> checkboxes;

    public MeetingResponse(Meeting meeting, MainWindow parentFrame) {
        initComponents();
        this.meeting = meeting;
        userRepo = new UserRepository();
        meetingRepo = new MeetingRepository();
        createdBy = userRepo.getById(meeting.getMeetingCallerUserId());

        // Set meeting info.
        lblMeetingTitle.setText(meeting.getTitle());
        lblDataData.setText(meeting.getDate().toString());
        lblCreatedByUser.setText(createdBy.toString());

        // Back to meetings panel button event.
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchPanel(new Meetings((parentFrame)));
            }
        });

        // Add time suggestion checkboxes if time suggestions exists.
        if (meeting.getTimeSuggestions().size() > 0) {
            lblChooseTime.setVisible(true);
            pnlCheckBoxes.setLayout(new GridLayout(0, 5, 20, 20));

            checkboxes = new HashMap<Integer, JCheckBox>();
            for (TimeSuggestion timeSuggestion : meeting.getTimeSuggestions()) {
                boolean checked = false;
                try {
                    checked = meetingRepo.existsTimeSuggestionResponse(timeSuggestion.getId(), parentFrame.loggedInUser.getId());
                } catch (SQLException ex) {
                    Logger.getLogger(MeetingResponse.class.getName()).log(Level.SEVERE, null, ex);
                }
                JCheckBox cb = new JCheckBox(timeSuggestion.getTime().toString());
                cb.setSelected(checked);
                checkboxes.put(timeSuggestion.getId(), cb);
                
                pnlCheckBoxes.add(checkboxes.get(timeSuggestion.getId()));
                pnlCheckBoxes.revalidate();
                pnlCheckBoxes.repaint();
            }
        } else {
            lblChooseTime.setVisible(false);
        }

        // Attendance confirmation event.
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Write selected time suggestions to database.
                for (Map.Entry<Integer, JCheckBox> entry : checkboxes.entrySet()) {
                    meetingRepo.updateTimeSuggestionResponse(entry.getKey(), parentFrame.loggedInUser.getId(), entry.getValue().isSelected());
                }
                meetingRepo.setMeetingAttendance(parentFrame.loggedInUser.getId(), meeting.getId(),  true);
            }
        });
        
        // Decline meeting attendance.
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meetingRepo.setMeetingAttendance(parentFrame.loggedInUser.getId(), meeting.getId(),  false);
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
        pnlCheckBoxes = new javax.swing.JPanel();
        lblChooseTime = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        pnlMeetingContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(193, 193, 193), 1, true));

        lblMeetingTitle.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblMeetingTitle.setText("Mötestitel");

        lblCreatedBy.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        lblCreatedBy.setText("Skapat av");

        lblCreatedByUser.setText("User");

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
        lblChooseTime.setText("Välj passande tider");

        btnConfirm.setText("Deltar");

        btnCancel.setText("Deltar ej");

        javax.swing.GroupLayout pnlMeetingContainerLayout = new javax.swing.GroupLayout(pnlMeetingContainer);
        pnlMeetingContainer.setLayout(pnlMeetingContainerLayout);
        pnlMeetingContainerLayout.setHorizontalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMeetingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(lblDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDataData, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(lblCreatedBy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCreatedByUser, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(lblDuration)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDurationData, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(lblChooseTime)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCheckBoxes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(btnConfirm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel)))
                        .addGap(0, 139, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMeetingContainerLayout.setVerticalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addComponent(lblMeetingTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDate)
                            .addComponent(lblDataData)))
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCreatedBy)
                    .addComponent(lblCreatedByUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuration)
                    .addComponent(lblDurationData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChooseTime)
                    .addComponent(pnlCheckBoxes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm)
                    .addComponent(btnCancel))
                .addContainerGap(441, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel lblChooseTime;
    private javax.swing.JLabel lblCreatedBy;
    private javax.swing.JLabel lblCreatedByUser;
    private javax.swing.JLabel lblDataData;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblDurationData;
    private javax.swing.JLabel lblMeetingTitle;
    private javax.swing.JPanel pnlCheckBoxes;
    private javax.swing.JPanel pnlMeetingContainer;
    // End of variables declaration//GEN-END:variables
}
