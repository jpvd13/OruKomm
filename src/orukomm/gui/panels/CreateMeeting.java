package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import orukomm.data.entities.Meeting;
import orukomm.data.entities.User;
import orukomm.data.repositories.MeetingRepository;
import orukomm.data.repositories.UserRepository;
import orukomm.gui.MainWindow;
import orukomm.logic.Validation;

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

    public CreateMeeting(MainWindow parentFrame) {
        initComponents();
        this.parentFrame = parentFrame;
        meetingRepo = new MeetingRepository();
        meeting = new Meeting();
        invitedUsers = new ArrayList<>();

        userRepo = new UserRepository();
        
        lstMdlAddedUsers = new DefaultListModel<>();
        lstAddedUsers.setModel(lstMdlAddedUsers);

        refreshAllUsersList();
        lstAllUsers.setSelectedIndex(0);

        selectedUserFromAllUsers = lstAllUsers.getSelectedValue();

        // Add meeting submit event.
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate fields and invited users.
                if (Validation.isEmptyOrNull(txtfTitle.getText()) || Validation.isEmptyOrNull(txtaDescription.getText())) {
                    JOptionPane.showMessageDialog(parentFrame, "Inga fält får lämnas tomma.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }
                
                // Add all invited users to array list.
                for (int i = 0; i < lstMdlAddedUsers.getSize(); i++)
                    invitedUsers.add(lstMdlAddedUsers.getElementAt(i));
                
                if (invitedUsers.size() < 1) {
                    JOptionPane.showMessageDialog(parentFrame, "Du måste bjuda in användare till mötet.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                
                    return;
                }
                
                // Survived validation: Create meeting and add it to database.
                meeting.setMeetingCaller(parentFrame.loggedInUser.getId());
                meeting.setTitle(txtfTitle.getText());
                meeting.setDescription(txtaDescription.getText());
                meeting.setInvitedUsers(invitedUsers);
                
                meetingRepo.add(meeting);
                JOptionPane.showMessageDialog(parentFrame, "Mötet har skapats.", "Möte skapat", JOptionPane.INFORMATION_MESSAGE);
                lstMdlAddedUsers.removeAllElements();
                invitedUsers.removeAll(invitedUsers);
                refreshAllUsersList();
                clearFields();
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
    }

    // Fetches users from the database and populates the all users-list with them.
    private void refreshAllUsersList() {
        lstMdlAllUsers = new DefaultListModel<>();
        lstAllUsers.setModel(lstMdlAllUsers);

        users = userRepo.getAll();
        lstMdlAllUsers.removeAllElements();

        for (User user : users) {
            lstMdlAllUsers.addElement(user);
        }
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

        javax.swing.GroupLayout pnlMeetingContainerLayout = new javax.swing.GroupLayout(pnlMeetingContainer);
        pnlMeetingContainer.setLayout(pnlMeetingContainerLayout);
        pnlMeetingContainerLayout.setHorizontalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMeetingContainerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCreate))
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCreateMeetingHeading)
                            .addComponent(lblMeetingDescription)
                            .addComponent(lblTitle)
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtfTitle, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAddUser)
                                    .addComponent(scrLstAllUsers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                    .addComponent(lblChooseUsers, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblAddedUsers)
                                            .addComponent(scrLstAddedUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMeetingContainerLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRemoveUser)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChooseUsers)
                    .addComponent(lblAddedUsers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrLstAddedUsers)
                    .addComponent(scrLstAllUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddUser)
                    .addComponent(btnRemoveUser))
                .addGap(18, 18, 18)
                .addComponent(lblMeetingDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCreate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(pnlMeetingContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnRemoveUser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddedUsers;
    private javax.swing.JLabel lblChooseUsers;
    private javax.swing.JLabel lblCreateMeetingHeading;
    private javax.swing.JLabel lblMeetingDescription;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList<User> lstAddedUsers;
    private javax.swing.JList<User> lstAllUsers;
    private javax.swing.JPanel pnlMeetingContainer;
    private javax.swing.JScrollPane scrLstAddedUsers;
    private javax.swing.JScrollPane scrLstAllUsers;
    private javax.swing.JTextArea txtaDescription;
    private javax.swing.JTextField txtfTitle;
    // End of variables declaration//GEN-END:variables
}
