package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import orukomm.Settings;
import orukomm.data.entities.User;
import orukomm.data.entities.User.PermissionFlag;
import orukomm.data.repositories.UserRepository;
import orukomm.gui.MainWindow;
import orukomm.logic.security.Encryption;

public class Login extends javax.swing.JPanel {

	private MainWindow parentFrame;
	private UserRepository userRepo;

	public Login(MainWindow parentFrame) {
		this.parentFrame = parentFrame;
		initComponents();
		addEventListeners();
		userRepo = new UserRepository();
	}

	private void addEventListeners() {
		// Login event.
		btnLogin.addActionListener((ActionEvent e) -> {
			User user;
			String salt = "";
			String passwordHash = "";
			
			// Run submitted password and username's salt through SHA-256 hashing method if the submitted
			// username exists in the data context.
			if (userRepo.userExists(txtfUsername.getText())) {
				User tmpUser = userRepo.getByUsername(txtfUsername.getText());
				salt = tmpUser.getSalt();
				passwordHash = Encryption.generatePasswordHash(pswPassword.getText(), salt);
			}
			
			user = userRepo.login(txtfUsername.getText(), passwordHash);

			if (user.getId() == 0) {
				JOptionPane.showMessageDialog(parentFrame, "Fel användarnamn eller lösenord.", "Inloggningen misslyckades", JOptionPane.ERROR_MESSAGE);
			} else {
				// Login success.
				parentFrame.loggedInUser = user;
				String windowTitle = String.format("%s [inloggad som %s %s]", Settings.WINDOW_TITLE, parentFrame.loggedInUser.getFirstName(), parentFrame.loggedInUser.getSurname());
				parentFrame.setTitle(windowTitle);
				parentFrame.enableLoggedInInterface(PermissionFlag.NONE.getPermissionFlags(user.getRole()));
				parentFrame.switchPanel(new Index(parentFrame));
				parentFrame.remove(this);
			}
		});

		pswPassword.addActionListener((ActionEvent e) -> {
			btnLogin.doClick();
		});
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLoginContainer = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtfUsername = new javax.swing.JTextField();
        pswPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblLoginHeading = new javax.swing.JLabel();

        pnlLoginContainer.setBorder(null);

        lblUsername.setText("Användarnamn");

        lblPassword.setText("Lösenord");

        btnLogin.setText("Logga in");

        lblLoginHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblLoginHeading.setText("Logga in");

        javax.swing.GroupLayout pnlLoginContainerLayout = new javax.swing.GroupLayout(pnlLoginContainer);
        pnlLoginContainer.setLayout(pnlLoginContainerLayout);
        pnlLoginContainerLayout.setHorizontalGroup(
            pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pswPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(87, 87, 87))
            .addGroup(pnlLoginContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLoginHeading)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLoginContainerLayout.setVerticalGroup(
            pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginContainerLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblLoginHeading)
                .addGap(18, 18, 18)
                .addGroup(pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername))
                .addGap(18, 18, 18)
                .addGroup(pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(358, Short.MAX_VALUE)
                .addComponent(pnlLoginContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(346, 346, 346))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(pnlLoginContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblLoginHeading;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlLoginContainer;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JTextField txtfUsername;
    // End of variables declaration//GEN-END:variables
}
