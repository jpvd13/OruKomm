package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import orukomm.Settings;
import orukomm.data.entities.User;
import orukomm.data.repositories.UserRepository;
import orukomm.gui.MainWindow;
import orukomm.logic.security.Validation;
import orukomm.logic.security.Encryption;

public class UpdateAccount extends javax.swing.JPanel {

    private MainWindow parentFrame;
    private UserRepository userRepo;

    public UpdateAccount(MainWindow parentFrame) {
        this.parentFrame = parentFrame;
        initComponents();
        userRepo = new UserRepository();
        fillTxtfields();

        // Update submit event.
        btnUpdate.addActionListener((ActionEvent e) -> {
            User editUser = new User();

            // Validate user's properties.
            if (Validation.isEmptyOrNull(txtfFirstName.getText())
                    || Validation.isEmptyOrNull(txtfSurname.getText())
                    || Validation.isEmptyOrNull(txtfEmail.getText())) {
                JOptionPane.showMessageDialog(parentFrame, "Inga fält får lämnas tomma.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);

                return;
            }

            if (!pswPassword.getText().equals(pswPasswordConfirmation.getText())) {
                JOptionPane.showMessageDialog(parentFrame, "Lösenorden du angav matchar inte varandra.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Set new user object and write it to the data context.
            editUser.setId(parentFrame.loggedInUser.getId());
            editUser.setFirstName(txtfFirstName.getText());
            editUser.setSurname(txtfSurname.getText());
            editUser.setUsername(this.parentFrame.loggedInUser.getUsername());
            editUser.setPassword(parentFrame.loggedInUser.getPassword());
            editUser.setSalt(parentFrame.loggedInUser.getSalt());
            editUser.setEmail(txtfEmail.getText());
            editUser.setRole(parentFrame.loggedInUser.getRole());

            // Check if password should be updated.
            if (!Validation.isEmptyOrNull(pswPassword.getText()) || !Validation.isEmptyOrNull(pswPasswordConfirmation.getText())) {
                String salt = Encryption.generateSalt();
                String passwordHash = Encryption.generatePasswordHash(pswPassword.getText(), salt);
                editUser.setPassword(passwordHash);
                editUser.setSalt(salt);
            }

            // Account update survived the validation: Write it to the data context.
            parentFrame.loggedInUser = editUser;
            userRepo.update(editUser);
            String windowTitle = String.format("%s [inloggad som %s %s]", Settings.WINDOW_TITLE, parentFrame.loggedInUser.getFirstName(), parentFrame.loggedInUser.getSurname());
            parentFrame.setTitle(windowTitle);
            parentFrame.switchPanel(new Index(parentFrame));
            JOptionPane.showMessageDialog(parentFrame, "Användaruppgifterna har ändrats.", "Användaruppgifter uppdaterade", JOptionPane.INFORMATION_MESSAGE);
        });

        pswPasswordConfirmation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.doClick();
            }
        });
    }

    private void fillTxtfields() {
        txtfFirstName.setText(parentFrame.loggedInUser.getFirstName());
        txtfSurname.setText(parentFrame.loggedInUser.getSurname());
        txtfEmail.setText(parentFrame.loggedInUser.getEmail());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegisterContainer = new javax.swing.JPanel();
        lblFirstName = new javax.swing.JLabel();
        lblSurname = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtfFirstName = new javax.swing.JTextField();
        txtfSurname = new javax.swing.JTextField();
        pswPassword = new javax.swing.JPasswordField();
        btnUpdate = new javax.swing.JButton();
        lblRegisterHeading = new javax.swing.JLabel();
        pswPasswordConfirmation = new javax.swing.JPasswordField();
        lblPasswordConfirmation = new javax.swing.JLabel();
        txtfEmail = new javax.swing.JTextField();
        lblEpost = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        lblFirstName.setText("Förnamn");

        lblSurname.setText("Efternamn");

        lblPassword.setText("Nytt lösenord");

        btnUpdate.setText("Ändra");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lblRegisterHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblRegisterHeading.setText("Ändra uppgifter");

        lblPasswordConfirmation.setText("Bekräfta nytt lösenord");

        lblEpost.setText("Epost");

        javax.swing.GroupLayout pnlRegisterContainerLayout = new javax.swing.GroupLayout(pnlRegisterContainer);
        pnlRegisterContainer.setLayout(pnlRegisterContainerLayout);
        pnlRegisterContainerLayout.setHorizontalGroup(
            pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegisterContainerLayout.createSequentialGroup()
                        .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegisterContainerLayout.createSequentialGroup()
                                .addComponent(lblRegisterHeading)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegisterContainerLayout.createSequentialGroup()
                                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                                        .addComponent(lblFirstName)
                                        .addGap(120, 120, 120))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRegisterContainerLayout.createSequentialGroup()
                                        .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblEpost, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSurname, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtfSurname)
                                    .addComponent(txtfFirstName)
                                    .addComponent(txtfEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegisterContainerLayout.createSequentialGroup()
                                .addComponent(lblPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegisterContainerLayout.createSequentialGroup()
                                .addComponent(lblPasswordConfirmation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pswPasswordConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        pnlRegisterContainerLayout.setVerticalGroup(
            pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegisterHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFirstName))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSurname))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEpost))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswPasswordConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPasswordConfirmation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(pnlRegisterContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(pnlRegisterContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
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
    private javax.swing.JLabel lblRegisterHeading;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JPanel pnlRegisterContainer;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JPasswordField pswPasswordConfirmation;
    private javax.swing.JTextField txtfEmail;
    private javax.swing.JTextField txtfFirstName;
    private javax.swing.JTextField txtfSurname;
    // End of variables declaration//GEN-END:variables
}
