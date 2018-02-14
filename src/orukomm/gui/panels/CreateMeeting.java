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

public class CreateMeeting extends javax.swing.JPanel {

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
    
    public CreateMeeting(MainWindow parentFrame) {
        initComponents();
        this.parentFrame = parentFrame;
        dateFormat = new SimpleDateFormat("HH:mm");
        timeSuggestions = new ArrayList<>();
        
        meetingRepo = new MeetingRepository();
        meeting = new Meeting();
        invitedUsers = new ArrayList<>();

        userRepo = new UserRepository();

        lstMdlAddedUsers = new DefaultListModel<>();
        lstAddedUsers.setModel(lstMdlAddedUsers);
        lstMdlAllUsers = new DefaultListModel<>();
        lstAllUsers.setModel(lstMdlAllUsers);

        refreshAllUsersList();
        lstAllUsers.setSelectedIndex(0);

        selectedUserFromAllUsers = lstAllUsers.getSelectedValue();

        // Add meeting submit event.
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate fields and invited users.
                if (Validation.isEmptyOrNull(txtfTitle.getText())) {
                    JOptionPane.showMessageDialog(parentFrame, "Ange en mötestitel.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                if (txtfTitle.getText().length() > 64) {
                    JOptionPane.showMessageDialog(parentFrame, "Mötestiteln får inte vara längre än 64 tecken.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }
                
                if (txtaDescription.getText().length() > 512) {
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
                java.sql.Date date = new java.sql.Date(clnDatePicker.getCalendar().getTime().getTime());

                // Survived validation: Create meeting and add it to database.
                meeting.setMeetingCallerUserId(parentFrame.loggedInUser.getId());
                meeting.setTitle(txtfTitle.getText());
                meeting.setDescription(txtaDescription.getText());
                meeting.setDate(date);
                meeting.setInvitedUsers(invitedUsers);
                meeting.setTimeSuggestions(timeSuggestions);

                meetingRepo.add(meeting);
                JOptionPane.showMessageDialog(parentFrame, "Mötet har skapats.", "Möte skapat", JOptionPane.INFORMATION_MESSAGE);

                // TODO send email notifications to users that wants to receive them.
                
                // Reset and update panel components.
                lstMdlAddedUsers.removeAllElements();
                invitedUsers.removeAll(invitedUsers);
                refreshAllUsersList();
                clearFields();
                lblAddedTimeSuggestions.setText("");
                timeSuggestions.clear();
            }
        });

        // All users-list selection events.
        lstAllUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedUserFromAllUsers = lstAllUsers.getSelectedValue();
            }
        });

        // Added users-list selection event.
        lstAddedUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedUserFromAddedUsers = lstAddedUsers.getSelectedValue();
            }
        });

        // Add user event.
        btnAddUser.addActionListener(new ActionListener() {
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
        btnRemoveUser.addActionListener(new ActionListener() {
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
        txtfTimeSuggestion.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtfTimeSuggestion.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        // Live validation and constraints for time suggestion input.
        txtfTimeSuggestion.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                // Only allow digits and colon as input, no longer than 5 chars.
                if (!((input >= '0') && (input <= '9') || input == ':') || txtfTimeSuggestion.getText().length() > 4) {
                    e.consume();
                }
            }
        });

        // Add time suggestion event.
        btnAddTimeSuggestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeSuggestion = new TimeSuggestion();
                // Validate time input then add it to the meeting.
                if (!Validation.is24HourFormat(txtfTimeSuggestion.getText())) {
                    JOptionPane.showMessageDialog(parentFrame, "Ange tiden enligt 24-timmarsformatet HH:mm, e.g.: 19:15, 8:30.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                } else {
                     try {
                         timeSuggestion.setMeetingid(meeting.getId());
                         timeSuggestion.setTime(new Time(dateFormat.parse(txtfTimeSuggestion.getText()).getTime()));
                     } catch (ParseException ex) {
                        Logger.getLogger(CreateMeeting.class.getName()).log(Level.SEVERE, null, ex);
                    }

                     if (timeSuggestions.size() > 4) {
                         JOptionPane.showMessageDialog(parentFrame, "Högst 5 tidsförslag får anges.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                     } else {
                        if (timeSuggestions.size() == 0)
                            lblAddedTimeSuggestions.setText(timeSuggestion.getTime().toString());
                        else
                            lblAddedTimeSuggestions.setText(lblAddedTimeSuggestions.getText() + ", " + timeSuggestion.getTime());

                        timeSuggestions.add(timeSuggestion);
                     }
                }
            }
        });
    }

    // Fetches users from the database and populates the all users-list with them.
    private void refreshAllUsersList() {
        users = userRepo.getAll();
        lstMdlAllUsers.removeAllElements();

        for (User user : users)
            // Don't add the logged in user to the list.
            if (user.getId() != parentFrame.loggedInUser.getId())
                lstMdlAllUsers.addElement(user);
    }

    private void clearFields() {
        txtfTitle.setText("");
        txtaDescription.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMeetingContainer = new javax.swing.JPanel();
        lblCreateMeetingHeading = new javax.swing.JLabel();
        scrLstAllUsers = new javax.swing.JScrollPane();
        lstAllUsers = new javax.swing.JList<>();
        lblChooseUsers = new javax.swing.JLabel();
        scrLstAddedUsers = new javax.swing.JScrollPane();
        lstAddedUsers = new javax.swing.JList<>();
        lblAddedUsers = new javax.swing.JLabel();
        btnAddUser = new javax.swing.JButton();
        btnRemoveUser = new javax.swing.JButton();
        lblMeetingDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDescription = new javax.swing.JTextArea();
        btnCreate = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        txtfTitle = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        clnDatePicker = new com.toedter.calendar.JCalendar();
        lblTimeSuggestion = new javax.swing.JLabel();
        txtfTimeSuggestion = new javax.swing.JTextField();
        btnAddTimeSuggestion = new javax.swing.JButton();
        lblAddedTimeSuggestions = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        pnlMeetingContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 193, 193)));
        pnlMeetingContainer.setPreferredSize(new java.awt.Dimension(919, 768));

        lblCreateMeetingHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblCreateMeetingHeading.setText("Skapa nytt möte");

        scrLstAllUsers.setViewportView(lstAllUsers);

        lblChooseUsers.setText("Välj mötesdeltagare");

        scrLstAddedUsers.setViewportView(lstAddedUsers);

        lblAddedUsers.setText("Tillagda mötesdeltagare");

        btnAddUser.setText("Välj");

        btnRemoveUser.setText("Ta bort");

        lblMeetingDescription.setText("Mötesbeskrivning");

        txtaDescription.setColumns(20);
        txtaDescription.setLineWrap(true);
        txtaDescription.setRows(5);
        jScrollPane1.setViewportView(txtaDescription);

        btnCreate.setText("Skapa möte");

        lblTitle.setText("Mötestitel");

        lblDate.setText("Datum för möte");

        lblTimeSuggestion.setText("Tidsförslag");

        txtfTimeSuggestion.setText("HH:mm");

        btnAddTimeSuggestion.setText("Lägg till");

        javax.swing.GroupLayout pnlMeetingContainerLayout = new javax.swing.GroupLayout(pnlMeetingContainer);
        pnlMeetingContainer.setLayout(pnlMeetingContainerLayout);
        pnlMeetingContainerLayout.setHorizontalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addComponent(lblMeetingDescription)
                        .addGap(808, 808, 808))
                    .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMeetingContainerLayout.createSequentialGroup()
                            .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(btnCreate))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMeetingContainerLayout.createSequentialGroup()
                                    .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblChooseUsers)
                                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                                .addGap(224, 224, 224)
                                                .addComponent(btnAddUser))
                                            .addComponent(scrLstAllUsers)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblAddedUsers, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnRemoveUser, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(scrLstAddedUsers, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(25, 25, 25)
                            .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(clnDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDate)
                                .addComponent(lblTimeSuggestion)
                                .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                    .addComponent(txtfTimeSuggestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAddTimeSuggestion))
                                .addComponent(lblAddedTimeSuggestions))
                            .addGap(23, 23, 23))
                        .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                            .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitle)
                                .addComponent(lblCreateMeetingHeading)
                                .addComponent(txtfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        pnlMeetingContainerLayout.setVerticalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCreateMeetingHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addComponent(lblChooseUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrLstAllUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddUser))
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(lblAddedUsers)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrLstAddedUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addComponent(lblDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clnDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoveUser)))
                .addGap(18, 18, 18)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMeetingDescription)
                    .addComponent(lblTimeSuggestion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfTimeSuggestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddTimeSuggestion))
                        .addGap(18, 18, 18)
                        .addComponent(lblAddedTimeSuggestions)))
                .addGap(18, 18, 18)
                .addComponent(btnCreate)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTimeSuggestion;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnRemoveUser;
    private com.toedter.calendar.JCalendar clnDatePicker;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddedTimeSuggestions;
    private javax.swing.JLabel lblAddedUsers;
    private javax.swing.JLabel lblChooseUsers;
    private javax.swing.JLabel lblCreateMeetingHeading;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblMeetingDescription;
    private javax.swing.JLabel lblTimeSuggestion;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList<User> lstAddedUsers;
    private javax.swing.JList<User> lstAllUsers;
    private javax.swing.JPanel pnlMeetingContainer;
    private javax.swing.JScrollPane scrLstAddedUsers;
    private javax.swing.JScrollPane scrLstAllUsers;
    private javax.swing.JTextArea txtaDescription;
    private javax.swing.JTextField txtfTimeSuggestion;
    private javax.swing.JTextField txtfTitle;
    // End of variables declaration//GEN-END:variables
}
