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

public class Register extends javax.swing.JPanel {

	private MainWindow parentFrame;
	private UserRepository userRepo;

	public Register(MainWindow parentFrame) {
		this.parentFrame = parentFrame;
		initComponents();
		userRepo = new UserRepository();

		// Registration submit event.
		btnRegister.addActionListener((ActionEvent e) -> {
			User newUser = new User();

			switch (cmbRoles.getSelectedIndex()) {
				case 0:
					newUser.setRole(User.PermissionFlag.USER.getPermissionValue(Settings.USER_ROLE));
					break;

				case 1:
					newUser.setRole(User.PermissionFlag.ADMIN.getPermissionValue(Settings.ADMIN_ROLE));
					break;

				case 2:
					newUser.setRole(User.PermissionFlag.SUPERADMIN.getPermissionValue(Settings.SUPERADMIN_ROLE));
					break;
			}

			// Validate submitted user's properties.
                       
			if (Validation.isEmptyOrNull(txtfFirstName.getText())
					|| Validation.isEmptyOrNull(txtfSurname.getText())
					|| Validation.isEmptyOrNull(txtfUsername.getText())
					|| Validation.isEmptyOrNull(pswPassword.getText())
					|| Validation.isEmptyOrNull(pswPasswordConfirmation.getText())
					|| Validation.isEmptyOrNull(txtfEmail.getText())) {
				JOptionPane.showMessageDialog(parentFrame, "Inga fält får lämnas tomma.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);

				return;
			}

                        
			if (Validation.wordLength(txtfFirstName.getText(), User.MAX_LENGTH_FIRST_NAME))
                        {
                        JOptionPane.showMessageDialog(parentFrame, "Förnamnet får inte vara längre än 32 tecken!", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                        return;
                        }
                        
                        
                        if (Validation.wordLength(txtfSurname.getText(), User.MAX_LENGTH_SURNAME))
                        {
                        JOptionPane.showMessageDialog(parentFrame, "Efternamnet får inte vara längre än 32 tecken!", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                        return;
                        }
                        
                        if(Validation.wordLength(txtfUsername.getText(), User.MAX_LENGTH_USERNAME))
                        {
                        JOptionPane.showMessageDialog(parentFrame, "Användarnamnet får inte vara längre än 64 tecken!", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                        return;
                        }
                        
                        if(Validation.wordLength(txtfEmail.getText(), User.MAX_LENGTH_EMAIL))
                        {
                        JOptionPane.showMessageDialog(parentFrame, "Email-adressen får inte vara längre än 128 tecken!", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                        return;
                        }
                        
                        if(Validation.wordLength(pswPassword.getText(), User.MAX_LENGTH_PASSWORDHASH))
                        {
                        JOptionPane.showMessageDialog(parentFrame, "Lösenordet får inte vara längre än 128 tecken!", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
                        return;
                        }
                        
                            


			if (!pswPassword.getText().equals(pswPasswordConfirmation.getText())) {
				JOptionPane.showMessageDialog(parentFrame, "Lösenorden du angav matchar inte varandra.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);

				return;
			}

			// Check if username exists in the data context.
			if (userRepo.userExists(txtfUsername.getText())) {
				JOptionPane.showMessageDialog(parentFrame, "Användarnamnet du angav existerar redan.", "Användarnamn upptaget", JOptionPane.ERROR_MESSAGE);

				return;
			}
                        
                        // Check if email exists in the data context.
                        if(userRepo.emailExists(txtfEmail.getText()))
                        {
                        JOptionPane.showMessageDialog(parentFrame, "Email-adressen du angav existerar redan.", "Email upptagen", JOptionPane.ERROR_MESSAGE);
                                return;
                        }

			// Generate salt and password hash.
			String salt = Encryption.generateSalt();
			String passwordHash = Encryption.generatePasswordHash(pswPassword.getText(), salt);

			// Set new user object and write it to the data context.
			newUser.setFirstName(txtfFirstName.getText());
			newUser.setSurname(txtfSurname.getText());
			newUser.setUsername(txtfUsername.getText());
			newUser.setPassword(passwordHash);
			newUser.setSalt(salt);
			newUser.setEmail(txtfEmail.getText());

			// User registration survived the validation: Write it to the data context.
			userRepo.add(newUser);
			parentFrame.switchPanel(new Index(parentFrame));
			JOptionPane.showMessageDialog(parentFrame, "Den nya användaren har registrerats.", "Användare registrerad", JOptionPane.INFORMATION_MESSAGE);
		});

		pswPasswordConfirmation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnRegister.doClick();
			}
		});
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegisterContainer = new javax.swing.JPanel();
        lblFirstName = new javax.swing.JLabel();
        lblSurname = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtfFirstName = new javax.swing.JTextField();
        txtfSurname = new javax.swing.JTextField();
        txtfUsername = new javax.swing.JTextField();
        pswPassword = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        lblRegisterHeading = new javax.swing.JLabel();
        pswPasswordConfirmation = new javax.swing.JPasswordField();
        lblPasswordConfirmation = new javax.swing.JLabel();
        cmbRoles = new javax.swing.JComboBox<>();
        lblUserRole = new javax.swing.JLabel();
        txtfEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        pnlRegisterContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(193, 193, 193), 1, true));

        lblFirstName.setText("Förnamn");

        lblSurname.setText("Efternamn");

        lblUsername.setText("Användarnamn");

        lblPassword.setText("Lösenord");

        btnRegister.setText("Registrera");

        lblRegisterHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblRegisterHeading.setText("Registrera ny användare");

        lblPasswordConfirmation.setText("Bekräfta lösenord");

        cmbRoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Användare", "Admin", "Superadmin" }));

        lblUserRole.setText("Användarroll");

        lblEmail.setText("E-post adress");

        javax.swing.GroupLayout pnlRegisterContainerLayout = new javax.swing.GroupLayout(pnlRegisterContainer);
        pnlRegisterContainer.setLayout(pnlRegisterContainerLayout);
        pnlRegisterContainerLayout.setHorizontalGroup(
            pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegisterContainerLayout.createSequentialGroup()
                        .addComponent(lblRegisterHeading)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegisterContainerLayout.createSequentialGroup()
                        .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPasswordConfirmation)
                                    .addComponent(lblPassword)
                                    .addComponent(lblUserRole))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cmbRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pswPasswordConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnRegister))))
                            .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFirstName)
                                    .addComponent(lblSurname)
                                    .addComponent(lblUsername))
                                .addGap(51, 51, 51)
                                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtfUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(txtfSurname)
                                    .addComponent(txtfFirstName))))
                        .addGap(39, 39, 39))))
        );
        pnlRegisterContainerLayout.setVerticalGroup(
            pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegisterHeading)
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(txtfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSurname)
                    .addComponent(txtfSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswPasswordConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPasswordConfirmation))
                .addGap(18, 18, 18)
                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserRole))
                .addGap(18, 18, 18)
                .addComponent(btnRegister)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(309, Short.MAX_VALUE)
                .addComponent(pnlRegisterContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRegisterContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(332, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JComboBox<String> cmbRoles;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordConfirmation;
    private javax.swing.JLabel lblRegisterHeading;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblUserRole;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlRegisterContainer;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JPasswordField pswPasswordConfirmation;
    private javax.swing.JTextField txtfEmail;
    private javax.swing.JTextField txtfFirstName;
    private javax.swing.JTextField txtfSurname;
    private javax.swing.JTextField txtfUsername;
    // End of variables declaration//GEN-END:variables
}
