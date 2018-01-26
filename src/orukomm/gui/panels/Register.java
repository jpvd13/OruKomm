package orukomm.gui.panels;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import orukomm.data.entities.User;
import orukomm.data.repositories.UserRepository;

public class Register extends javax.swing.JPanel {
	
	UserRepository userRepo;

    public Register(JFrame parentFrame) {
        initComponents();
	userRepo = new UserRepository();
	
	btnRegister.addActionListener((ActionEvent e) -> {
		User newUser = new User();
		newUser.setFirstName(txtfFirstName.getText());
		newUser.setSurname(txtfSurname.getText());
		newUser.setUsername(txtfUsername.getText());
		newUser.setPassword(pswPassword.getText());
		newUser.setRole(1); // Should be retrieved from combobox.
		
		// Validate submitted user.
		
		
		// Register new user to data context.
		userRepo.add(newUser);
		System.out.println("new user registered.");
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

                setPreferredSize(new java.awt.Dimension(1024, 768));
                setLayout(new java.awt.GridBagLayout());

                lblFirstName.setText("Förnamn");

                lblSurname.setText("Efternamn");

                lblUsername.setText("Användarnamn");

                lblPassword.setText("Lösenord");

                btnRegister.setText("Registrera");

                lblRegisterHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
                lblRegisterHeading.setText("Registrera ny användare");

                javax.swing.GroupLayout pnlRegisterContainerLayout = new javax.swing.GroupLayout(pnlRegisterContainer);
                pnlRegisterContainer.setLayout(pnlRegisterContainerLayout);
                pnlRegisterContainerLayout.setHorizontalGroup(
                        pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblRegisterHeading)
                                        .addGroup(pnlRegisterContainerLayout.createSequentialGroup()
                                                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblFirstName)
                                                        .addComponent(lblSurname)
                                                        .addComponent(lblUsername)
                                                        .addComponent(lblPassword))
                                                .addGap(27, 27, 27)
                                                .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btnRegister)
                                                        .addGroup(pnlRegisterContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txtfUsername)
                                                                .addComponent(txtfSurname)
                                                                .addComponent(txtfFirstName)
                                                                .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(20, Short.MAX_VALUE))
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
                                        .addComponent(lblPassword)
                                        .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnRegister)
                                .addContainerGap(23, Short.MAX_VALUE))
                );

                add(pnlRegisterContainer, new java.awt.GridBagConstraints());
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnRegister;
        private javax.swing.JLabel lblFirstName;
        private javax.swing.JLabel lblPassword;
        private javax.swing.JLabel lblRegisterHeading;
        private javax.swing.JLabel lblSurname;
        private javax.swing.JLabel lblUsername;
        private javax.swing.JPanel pnlRegisterContainer;
        private javax.swing.JPasswordField pswPassword;
        private javax.swing.JTextField txtfFirstName;
        private javax.swing.JTextField txtfSurname;
        private javax.swing.JTextField txtfUsername;
        // End of variables declaration//GEN-END:variables
}
