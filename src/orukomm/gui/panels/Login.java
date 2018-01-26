package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import orukomm.data.entities.User;
import orukomm.data.repositories.UserRepository;
import orukomm.gui.MainWindow;

public class Login extends javax.swing.JPanel {

	private UserRepository userRepo;
	private MainWindow parentFrame;
	
	public Login(MainWindow parentFrame) {
		this.parentFrame = parentFrame;
		initComponents();
		addEventListeners();
		userRepo = new UserRepository();

	}

	private void addEventListeners() {
		// Login submit.
		btnLogin.addActionListener((ActionEvent e) -> {
			// TODO run txtfPassword.getTetx() and salt through enryption method.
			User user = userRepo.login(txtfUsername.getText(), pswPassword.getText());
			
			if (user.getId() == 0) {
				lblLoginError.setText("Fel användarnamn eller lösenord");
			} else {
				// Login success: 
				parentFrame.loggedInUser = user;
				String windowTitle = String.format("Orukomm [inloggad som %s %s]",
				parentFrame.loggedInUser.getSurname(),
				parentFrame.loggedInUser.getSurname());
				parentFrame.setTitle(windowTitle);
				
				parentFrame.switchPanel(parentFrame.pnlRegister);
			}
		});
		
		pswPassword.addActionListener((ActionEvent e) -> {
			btnLogin.doClick();
		});
	}
	
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {
                java.awt.GridBagConstraints gridBagConstraints;

                pnlLoginContainer = new javax.swing.JPanel();
                lblUsername = new javax.swing.JLabel();
                lblPassword = new javax.swing.JLabel();
                txtfUsername = new javax.swing.JTextField();
                pswPassword = new javax.swing.JPasswordField();
                btnLogin = new javax.swing.JButton();
                lblLoginError = new javax.swing.JLabel();
                lblLoginHeading = new javax.swing.JLabel();

                setLayout(new java.awt.GridBagLayout());

                lblUsername.setText("Användarnamn");

                lblPassword.setText("Lösenord");

                btnLogin.setText("Logga in");

                lblLoginError.setForeground(new java.awt.Color(255, 0, 0));
                lblLoginError.setText(" ");
                lblLoginError.setToolTipText("");

                lblLoginHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
                lblLoginHeading.setText("Logga in");

                javax.swing.GroupLayout pnlLoginContainerLayout = new javax.swing.GroupLayout(pnlLoginContainer);
                pnlLoginContainer.setLayout(pnlLoginContainerLayout);
                pnlLoginContainerLayout.setHorizontalGroup(
                        pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginContainerLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblLoginError, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlLoginContainerLayout.createSequentialGroup()
                                                .addGroup(pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlLoginContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(pswPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                                .addGap(49, 49, 49)
                                .addComponent(lblLoginError)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.ipadx = -73;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                gridBagConstraints.insets = new java.awt.Insets(245, 388, 247, 316);
                add(pnlLoginContainer, gridBagConstraints);
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnLogin;
        private javax.swing.JLabel lblLoginError;
        private javax.swing.JLabel lblLoginHeading;
        private javax.swing.JLabel lblPassword;
        private javax.swing.JLabel lblUsername;
        private javax.swing.JPanel pnlLoginContainer;
        private javax.swing.JPasswordField pswPassword;
        private javax.swing.JTextField txtfUsername;
        // End of variables declaration//GEN-END:variables
}
