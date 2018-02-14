/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import orukomm.data.entities.Meeting;
import orukomm.data.entities.TimeSuggestion;
import orukomm.data.entities.User;
import orukomm.data.repositories.MeetingRepository;
import orukomm.data.repositories.UserRepository;
import orukomm.gui.MainWindow;
import orukomm.logic.security.Validation;


/**
 *
 * @author admin
 */
public class editMeetingpnl extends javax.swing.JPanel {
     private MainWindow parentFrame;

    private ArrayList<User> users;
    private UserRepository userRepo;
    private MeetingRepository meetingRepo;

    private DefaultListModel<User> lstMdlAllUsers;
    private DefaultListModel<User> lstMdlAddedUsers;

    private User selectedUserFromAllUsers;
    private User selectedUserFromAddedUsers;
    private ArrayList<User> invitedUsers;
    private Meeting meeting;
    private ArrayList<TimeSuggestion> timeSuggestions;
    
    private TimeSuggestion timeSuggestion;
    private DateFormat dateFormat;
    private Meeting meetingToUpdate;
   
    public editMeetingpnl(MainWindow parentFrame, Meeting met) {
        initComponents();
        
        meetingToUpdate = met;
        meeting = meetingToUpdate;
         this.parentFrame = parentFrame;
        dateFormat = new SimpleDateFormat("HH:mm");
        timeSuggestions = new ArrayList<>();
        meetingRepo = new MeetingRepository();
        invitedUsers = new ArrayList<>();

        userRepo = new UserRepository();

        lstMdlAddedUsers = new DefaultListModel<>();
        lstAddedUsers1.setModel(lstMdlAddedUsers);
        lstMdlAllUsers = new DefaultListModel<>();
        lstAllUsers1.setModel(lstMdlAllUsers);
        
        lstAllUsers1.setSelectedIndex(0);
        
        setMeetingInfo();
    
           btnCreate1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate fields and invited users.
                if (Validation.isEmptyOrNull(txtfTitle1.getText())) {
                    JOptionPane.showMessageDialog(parentFrame, "Ange en mötestitel.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                if (txtfTitle1.getText().length() > 64) {
                    JOptionPane.showMessageDialog(parentFrame, "Mötestiteln får inte vara längre än 64 tecken.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }
                
                if (txtaDescription1.getText().length() > 512) {
                    JOptionPane.showMessageDialog(parentFrame, "Mötesbeskriningen får inte vara längre än 512 tecken..", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }
                
                // Add all invited users to array list.
                for (int i = 0; i < lstMdlAddedUsers.getSize(); i++) {
                    invitedUsers.add(lstMdlAddedUsers.getElementAt(i));
                }

                if (invitedUsers.size() < 1) {
                    JOptionPane.showMessageDialog(parentFrame, "Du måste bjuda in användare till mötet.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                // Convert calendar to sql.Date.
                java.sql.Date date = new java.sql.Date(clnDatePicker1.getCalendar().getTime().getTime());

                // Survived validation: Create meeting and add it to database.
                meeting.setMeetingCallerUserId(parentFrame.loggedInUser.getId());
                meeting.setTitle(txtfTitle1.getText());
                meeting.setDescription(txtaDescription1.getText());
                meeting.setDate(date);
                meeting.setInvitedUsers(invitedUsers);
                meeting.setTimeSuggestions(timeSuggestions);
                
               
                //meetingRepo.deleteMeetingSuggestions(meetingToUpdate);
                meetingRepo.insertMeetingTimeSuggestion(meetingToUpdate);
                meetingRepo.editUserMeeting(meetingToUpdate);
                meetingRepo.editMeeting(meetingToUpdate);
                
                JOptionPane.showMessageDialog(parentFrame, "Mötet har uppdaterats.", "Möte uppdaterat", JOptionPane.INFORMATION_MESSAGE);

                // TODO send email notifications to users that wants to receive them.
                
                // Reset and update panel components.
                invitedUsers.removeAll(invitedUsers);
                
            }
        });

        // All users-list selection events.
        lstAllUsers1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedUserFromAllUsers = lstAllUsers1.getSelectedValue();
            }
        });

        // Added users-list selection event.
        lstAddedUsers1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedUserFromAddedUsers = lstAddedUsers1.getSelectedValue();
            }
        });

        // Add user event.
        btnAddUser1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add new user to meeting.
                if (selectedUserFromAllUsers != null) {
                    // Move user from all users-list to added users-list.
                    lstMdlAddedUsers.addElement(selectedUserFromAllUsers);
                    lstMdlAllUsers.removeElement(selectedUserFromAllUsers);
                }
            }
        });

        // Remove user event.
        btnRemoveUser1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedUserFromAddedUsers != null) {
                    // Move user from added users-list to all users-list.
                    lstMdlAllUsers.addElement(selectedUserFromAddedUsers);
                    lstMdlAddedUsers.removeElement(selectedUserFromAddedUsers);
                }
            }
        });

        // Time suggestion focus events.
        txtfTimeSuggestion1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtfTimeSuggestion1.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        // Live validation and constraints for time suggestion input.
        txtfTimeSuggestion1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                // Only allow digits and colon as input, no longer than 5 chars.
                if (!((input >= '0') && (input <= '9') || input == ':') || txtfTimeSuggestion1.getText().length() > 4) {
                    e.consume();
                }
            }
        });

        // Add time suggestion event.
        btnAddTimeSuggestion1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeSuggestion = new TimeSuggestion();
                // Validate time input then add it to the meeting.
                if (!Validation.is24HourFormat(txtfTimeSuggestion1.getText())) {
                    JOptionPane.showMessageDialog(parentFrame, "Ange tiden enligt 24-timmarsformatet HH:mm, e.g.: 19:15, 8:30.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                } else {
                    timeSuggestion.setMeetingid(meeting.getId());
                    try {
                        timeSuggestion.setTime(new Time(dateFormat.parse(txtfTimeSuggestion1.getText()).getTime()));
                    } catch (ParseException ex) {
                        Logger.getLogger(editMeetingpnl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                     if (timeSuggestions.size() > 4) {
                         JOptionPane.showMessageDialog(parentFrame, "Högst 5 tidsförslag får anges.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                     } else {
                        if (lblAddedTimeSuggestions1.getText().isEmpty())
                            lblAddedTimeSuggestions1.setText(timeSuggestion.getTime().toString());
                        else
                            lblAddedTimeSuggestions1.setText(lblAddedTimeSuggestions1.getText() + ", " + timeSuggestion.getTime());

                        timeSuggestions.add(timeSuggestion);
                     }
                }
            }
        });
    }

  
    private void clearFields() {
        txtfTitle1.setText("");
        txtaDescription1.setText("");
    }
    
    private void setMeetingInfo(){
        txtfTitle1.setText(meetingToUpdate.getTitle());
        txtaDescription1.setText(meetingToUpdate.getDescription());
        
        for(TimeSuggestion suggestions :meetingRepo.getTimeSuggestions(meetingToUpdate)){
        timeSuggestions.add(suggestions);
    }
        ArrayList<User> InvitedUsers = meetingRepo.getInvitedUsers(meetingToUpdate);
        ArrayList<User> notInvitedUsers = meetingRepo.getNotInvitedUsers(meetingToUpdate);

        for(User user : InvitedUsers){
            if (user.getId() != parentFrame.loggedInUser.getId())
             lstMdlAddedUsers.addElement(user);
        }
        
        for(User user : notInvitedUsers){
            if (user.getId() != parentFrame.loggedInUser.getId())
                lstMdlAllUsers.addElement(user);
        }
    
            for(TimeSuggestion suggestions : timeSuggestions){
                if (lblAddedTimeSuggestions1.getText().isEmpty())
                            lblAddedTimeSuggestions1.setText(suggestions.getTime().toString());
                        else
                            lblAddedTimeSuggestions1.setText(lblAddedTimeSuggestions1.getText() + ", " + suggestions.getTime().toString());
            
        
            }
                 clnDatePicker1.setDate(meetingToUpdate.getDate());
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMeetingContainer1 = new javax.swing.JPanel();
        lblCreateMeetingHeading1 = new javax.swing.JLabel();
        scrLstAllUsers1 = new javax.swing.JScrollPane();
        lstAllUsers1 = new javax.swing.JList<>();
        lblChooseUsers1 = new javax.swing.JLabel();
        scrLstAddedUsers1 = new javax.swing.JScrollPane();
        lstAddedUsers1 = new javax.swing.JList<>();
        lblAddedUsers1 = new javax.swing.JLabel();
        btnAddUser1 = new javax.swing.JButton();
        btnRemoveUser1 = new javax.swing.JButton();
        lblMeetingDescription1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaDescription1 = new javax.swing.JTextArea();
        btnCreate1 = new javax.swing.JButton();
        lblTitle1 = new javax.swing.JLabel();
        txtfTitle1 = new javax.swing.JTextField();
        lblDate1 = new javax.swing.JLabel();
        clnDatePicker1 = new com.toedter.calendar.JCalendar();
        lblTimeSuggestion1 = new javax.swing.JLabel();
        txtfTimeSuggestion1 = new javax.swing.JTextField();
        btnAddTimeSuggestion1 = new javax.swing.JButton();
        lblAddedTimeSuggestions1 = new javax.swing.JLabel();

        pnlMeetingContainer1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 193, 193)));
        pnlMeetingContainer1.setPreferredSize(new java.awt.Dimension(919, 768));

        lblCreateMeetingHeading1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblCreateMeetingHeading1.setText("Redigera möte");

        scrLstAllUsers1.setViewportView(lstAllUsers1);

        lblChooseUsers1.setText("Välj mötesdeltagare");

        scrLstAddedUsers1.setViewportView(lstAddedUsers1);

        lblAddedUsers1.setText("Tillagda mötesdeltagare");

        btnAddUser1.setText("Välj");

        btnRemoveUser1.setText("Ta bort");

        lblMeetingDescription1.setText("Mötesbeskrivning");

        txtaDescription1.setColumns(20);
        txtaDescription1.setLineWrap(true);
        txtaDescription1.setRows(5);
        jScrollPane2.setViewportView(txtaDescription1);

        btnCreate1.setText("Redigera möte");

        lblTitle1.setText("Mötestitel");

        lblDate1.setText("Datum för möte");

        lblTimeSuggestion1.setText("Tidsförslag");

        txtfTimeSuggestion1.setText("HH:mm");

        btnAddTimeSuggestion1.setText("Lägg till");

        javax.swing.GroupLayout pnlMeetingContainer1Layout = new javax.swing.GroupLayout(pnlMeetingContainer1);
        pnlMeetingContainer1.setLayout(pnlMeetingContainer1Layout);
        pnlMeetingContainer1Layout.setHorizontalGroup(
            pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                        .addComponent(lblMeetingDescription1)
                        .addGap(808, 808, 808))
                    .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMeetingContainer1Layout.createSequentialGroup()
                            .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(btnCreate1))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMeetingContainer1Layout.createSequentialGroup()
                                    .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblChooseUsers1)
                                        .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                                                .addGap(224, 224, 224)
                                                .addComponent(btnAddUser1))
                                            .addComponent(scrLstAllUsers1)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblAddedUsers1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnRemoveUser1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(scrLstAddedUsers1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(25, 25, 25)
                            .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(clnDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDate1)
                                .addComponent(lblTimeSuggestion1)
                                .addComponent(lblAddedTimeSuggestions1)
                                .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                                    .addComponent(txtfTimeSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAddTimeSuggestion1)))
                            .addGap(23, 23, 23))
                        .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                            .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitle1)
                                .addComponent(lblCreateMeetingHeading1)
                                .addComponent(txtfTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        pnlMeetingContainer1Layout.setVerticalGroup(
            pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCreateMeetingHeading1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                        .addComponent(lblChooseUsers1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrLstAllUsers1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddUser1))
                    .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                        .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                                .addComponent(lblAddedUsers1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrLstAddedUsers1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                                .addComponent(lblDate1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clnDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoveUser1)))
                .addGap(18, 18, 18)
                .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMeetingDescription1)
                    .addComponent(lblTimeSuggestion1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreate1))
                    .addGroup(pnlMeetingContainer1Layout.createSequentialGroup()
                        .addGroup(pnlMeetingContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfTimeSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddTimeSuggestion1))
                        .addGap(18, 18, 18)
                        .addComponent(lblAddedTimeSuggestions1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1035, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(pnlMeetingContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, Short.MAX_VALUE)
                    .addGap(18, 18, 18)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlMeetingContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTimeSuggestion1;
    private javax.swing.JButton btnAddUser1;
    private javax.swing.JButton btnCreate1;
    private javax.swing.JButton btnRemoveUser1;
    private com.toedter.calendar.JCalendar clnDatePicker1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAddedTimeSuggestions1;
    private javax.swing.JLabel lblAddedUsers1;
    private javax.swing.JLabel lblChooseUsers1;
    private javax.swing.JLabel lblCreateMeetingHeading1;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblMeetingDescription1;
    private javax.swing.JLabel lblTimeSuggestion1;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JList<User> lstAddedUsers1;
    private javax.swing.JList<User> lstAllUsers1;
    private javax.swing.JPanel pnlMeetingContainer1;
    private javax.swing.JScrollPane scrLstAddedUsers1;
    private javax.swing.JScrollPane scrLstAllUsers1;
    private javax.swing.JTextArea txtaDescription1;
    private javax.swing.JTextField txtfTimeSuggestion1;
    private javax.swing.JTextField txtfTitle1;
    // End of variables declaration//GEN-END:variables
}
