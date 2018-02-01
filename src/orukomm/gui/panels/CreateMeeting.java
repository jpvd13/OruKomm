package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import orukomm.data.entities.User;
import orukomm.data.repositories.UserRepository;
import orukomm.gui.MainWindow;

public class CreateMeeting extends javax.swing.JPanel {

    private MainWindow parentFrame;

    private ArrayList<User> users;
    private UserRepository userRepo;
    private DefaultListModel<User> lstMdlAllUsers;
    private DefaultListModel<User> lstMdlAddedUsers;
    
    private User selectedUserFromAllUsers;
    private User selectedUserFromAddedUsers;

    public CreateMeeting(MainWindow parentFrame) {
        initComponents();
        this.parentFrame = parentFrame;

        userRepo = new UserRepository();
        
        lstMdlAddedUsers = new DefaultListModel<>();
        lstAddedUsers.setModel(lstMdlAddedUsers);

        refreshAllUsersList();
        lstAllUsers.setSelectedIndex(0);

        selectedUserFromAllUsers = lstAllUsers.getSelectedValue();

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

        lblCreateMeetingHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblCreateMeetingHeading.setText("Skapa nytt möte");

        scrLstAllUsers.setViewportView(lstAllUsers);

        lblChooseUsers.setText("Välj mötesdeltagare");

        scrLstAddedUsers.setViewportView(lstAddedUsers);

        lblAddedUsers.setText("Tillagda mötesdeltagare");

        btnAddUser.setText("Lägg till");

        btnRemoveUser.setText("Ta bort");

        lblMeetingDescription.setText("Mötesbeskrivning");

        txtaDescription.setColumns(20);
        txtaDescription.setLineWrap(true);
        txtaDescription.setRows(5);
        jScrollPane1.setViewportView(txtaDescription);

        btnCreate.setText("Skapa möte");

        javax.swing.GroupLayout pnlMeetingContainerLayout = new javax.swing.GroupLayout(pnlMeetingContainer);
        pnlMeetingContainer.setLayout(pnlMeetingContainerLayout);
        pnlMeetingContainerLayout.setHorizontalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCreateMeetingHeading)
                            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAddUser)
                                    .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrLstAllUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblChooseUsers)))
                                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblAddedUsers)
                                            .addComponent(scrLstAddedUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMeetingContainerLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRemoveUser))))
                            .addComponent(lblMeetingDescription))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMeetingContainerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCreate)))
                .addContainerGap())
        );
        pnlMeetingContainerLayout.setVerticalGroup(
            pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeetingContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCreateMeetingHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChooseUsers)
                    .addComponent(lblAddedUsers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMeetingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrLstAddedUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(scrLstAllUsers))
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
                .addContainerGap(75, Short.MAX_VALUE))
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
                .addContainerGap(36, Short.MAX_VALUE))
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
    private javax.swing.JList<User> lstAddedUsers;
    private javax.swing.JList<User> lstAllUsers;
    private javax.swing.JPanel pnlMeetingContainer;
    private javax.swing.JScrollPane scrLstAddedUsers;
    private javax.swing.JScrollPane scrLstAllUsers;
    private javax.swing.JTextArea txtaDescription;
    // End of variables declaration//GEN-END:variables
}
