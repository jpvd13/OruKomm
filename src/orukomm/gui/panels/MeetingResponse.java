package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import orukomm.data.entities.Meeting;
import orukomm.gui.MainWindow;

/*
 * Panel that displays response options such as RSVP and time selection.
 */
public class MeetingResponse extends javax.swing.JPanel {

    private Meeting meeting;
    
    public MeetingResponse(Meeting meeting, MainWindow parentFrame) {
        initComponents();
        lblMeetingTitle.setText(meeting.getTitle());
        
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
        btnBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        lblMeetingTitle.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        javax.swing.GroupLayout pnlMeetingContainerLayout = new javax.swing.GroupLayout(pnlMeetingContainer);
        pnlMeetingContainer.setLayout(pnlMeetingContainerLayout);
        pnlMeetingContainerLayout.setHorizontalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMeetingTitle)
                .addContainerGap(498, Short.MAX_VALUE))
        );
        pnlMeetingContainerLayout.setVerticalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMeetingTitle)
                .addContainerGap(460, Short.MAX_VALUE))
        );

        btnBack.setText("Tillbaka");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(308, Short.MAX_VALUE)
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(26, 26, 26)
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel lblMeetingTitle;
    private javax.swing.JPanel pnlMeetingContainer;
    // End of variables declaration//GEN-END:variables
}
