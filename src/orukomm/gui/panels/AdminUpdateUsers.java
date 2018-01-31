package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import orukomm.Settings;
import orukomm.data.entities.User;
import orukomm.data.repositories.UserRepository;
import orukomm.gui.MainWindow;
import orukomm.logic.Validation;
import orukomm.logic.security.Encryption;

public class AdminUpdateUsers extends javax.swing.JPanel {
    
    private MainWindow parentFrame;
    private ArrayList<User> users;
    private UserRepository userRepo;
    private DefaultListModel<User> lstMdlUsers;
    
    private String newPassword = "";
    private String newPasswordConfirmation = "";
    
    public AdminUpdateUsers(MainWindow parentFrame) {
        initComponents();
        this.parentFrame = parentFrame;
        userRepo = new UserRepository();
        
        refreshUsersList();
        lstUsers.setSelectedIndex(0);
        
        User selectedUser = lstUsers.getSelectedValue();
        fillTextFields(selectedUser);

        // List selection events.
        lstUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                User selectedUser = lstUsers.getSelectedValue();
                if (selectedUser != null) {
                    fillTextFields(lstUsers.getSelectedValue());
                }
            }
        });

        // Update user submit event.
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User editUser = lstUsers.getSelectedValue();

                // Validate user's properties.
                if (Validation.isEmptyOrNull(txtfFirstName.getText())
                        || Validation.isEmptyOrNull(txtfSurname.getText())
                        || Validation.isEmptyOrNull(txtfEmail.getText())
                        || Validation.isEmptyOrNull(txtfUsername.getText())) {
                    JOptionPane.showMessageDialog(parentFrame, "Inga fält får lämnas tomma.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }
                
                if (!pswPassword.getText().equals(pswPasswordConfirmation.getText())) {
                    JOptionPane.showMessageDialog(parentFrame, "Lösenorden du angav matchar inte varandra.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }

                // Set new user object and write it to the data context.
                editUser.setId(editUser.getId());
                editUser.setFirstName(txtfFirstName.getText());
                editUser.setSurname(txtfSurname.getText());
                editUser.setUsername(txtfUsername.getText());
                editUser.setPassword(editUser.getPassword());
                editUser.setSalt(editUser.getSalt());
                editUser.setEmail(txtfEmail.getText());
                editUser.setRole(editUser.getRole());

                // Check if password should be updated.
                if (!Validation.isEmptyOrNull(pswPassword.getText()) || !Validation.isEmptyOrNull(pswPasswordConfirmation.getText())) {
                    String salt = Encryption.generateSalt();
                    String passwordHash = Encryption.generatePasswordHash(pswPassword.getText(), salt);
                    editUser.setPassword(passwordHash);
                    editUser.setSalt(salt);

                    // Validate the new password.
                    if (!newPassword.equals(newPasswordConfirmation)) {
                        JOptionPane.showMessageDialog(parentFrame, "Lösenorden du angav matchar inte varandra.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                        
                        return;
                    }
                }

                // Account update survived the validation: Write it to the data context and refresh user list with updated user.
                userRepo.update(editUser);
                refreshUsersList();
                lstUsers.setSelectedIndex(0);
                JOptionPane.showMessageDialog(parentFrame, "Användaruppgifterna har ändrats.", "Användaruppgifter uppdaterade", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        pswPasswordConfirmation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.doClick();
            }
        });
    }

    // Fetches users from the database and populates the user list with them.
    private void refreshUsersList() {
        lstMdlUsers = new DefaultListModel<>();
        lstUsers.setModel(lstMdlUsers);
        
        users = userRepo.getAll();
        lstMdlUsers.removeAllElements();
        
        for (User user : users) {
            lstMdlUsers.addElement(user);
        }
    }
    
    private void fillTextFields(User user) {
        txtfFirstName.setText(user.getFirstName());
        txtfSurname.setText(user.getSurname());
        txtfUsername.setText(user.getUsername());
        txtfEmail.setText(user.getEmail());
        pswPassword.setText("");
        pswPasswordConfirmation.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEditUsersContainer = new javax.swing.JPanel();
        scrLstUsers = new javax.swing.JScrollPane();
        lstUsers = new javax.swing.JList<>();
        lblUpdateUserHeading = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        pswPasswordConfirmation = new javax.swing.JPasswordField();
        pswPassword = new javax.swing.JPasswordField();
        txtfEmail = new javax.swing.JTextField();
        txtfSurname = new javax.swing.JTextField();
        txtfFirstName = new javax.swing.JTextField();
        lblFirstName = new javax.swing.JLabel();
        lblSurname = new javax.swing.JLabel();
        lblEpost = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblPasswordConfirmation = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtfUsername = new javax.swing.JTextField();

        scrLstUsers.setViewportView(lstUsers);

        lblUpdateUserHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblUpdateUserHeading.setText("Redigera användare");

        btnUpdate.setText("Ändra");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lblFirstName.setText("Förnamn");

        lblSurname.setText("Efternamn");

        lblEpost.setText("Epost");

        lblPassword.setText("Nytt lösenord");

        lblPasswordConfirmation.setText("Bekräfta nytt lösenord");

        lblUsername.setText("Användarnamn");

        javax.swing.GroupLayout pnlEditUsersContainerLayout = new javax.swing.GroupLayout(pnlEditUsersContainer);
        pnlEditUsersContainer.setLayout(pnlEditUsersContainerLayout);
        pnlEditUsersContainerLayout.setHorizontalGroup(
            pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditUsersContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditUsersContainerLayout.createSequentialGroup()
                        .addComponent(scrLstUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassword)
                            .addComponent(lblPasswordConfirmation)
                            .addComponent(lblEpost)
                            .addComponent(lblUsername)
                            .addComponent(lblSurname)
                            .addComponent(lblFirstName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtfEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pswPasswordConfirmation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pswPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtfSurname)
                                    .addComponent(txtfFirstName)
                                    .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(31, 31, 31))
                    .addGroup(pnlEditUsersContainerLayout.createSequentialGroup()
                        .addComponent(lblUpdateUserHeading)
                        .addContainerGap())))
        );
        pnlEditUsersContainerLayout.setVerticalGroup(
            pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditUsersContainerLayout.createSequentialGroup()
                .addComponent(lblUpdateUserHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditUsersContainerLayout.createSequentialGroup()
                        .addComponent(scrLstUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                        .addGap(36, 36, 36))
                    .addGroup(pnlEditUsersContainerLayout.createSequentialGroup()
                        .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFirstName))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSurname))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsername))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEpost))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEditUsersContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pswPasswordConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPasswordConfirmation))
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(pnlEditUsersContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(pnlEditUsersContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel lblEpost;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordConfirmation;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblUpdateUserHeading;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList<User> lstUsers;
    private javax.swing.JPanel pnlEditUsersContainer;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JPasswordField pswPasswordConfirmation;
    private javax.swing.JScrollPane scrLstUsers;
    private javax.swing.JTextField txtfEmail;
    private javax.swing.JTextField txtfFirstName;
    private javax.swing.JTextField txtfSurname;
    private javax.swing.JTextField txtfUsername;
    // End of variables declaration//GEN-END:variables
}
