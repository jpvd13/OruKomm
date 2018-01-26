package orukomm.gui;

import javax.swing.JPanel;
import orukomm.data.entities.User;
import orukomm.data.entities.User.Role;
import orukomm.gui.panels.Index;
import orukomm.gui.panels.Login;
import orukomm.gui.panels.Register;

public class MainWindow extends javax.swing.JFrame {

	// Global user object of the logged in user.
	public User loggedInUser = new User();

	// Panels.
	public Login pnlLogin;
	public Index pnlIndex;
	public Register pnlReg;

	public MainWindow() {
		setTitle("OruKomm intranät");
		initComponents();
		initPanels();
		setLocationRelativeTo(null);
		setVisible(true);
		switchPanel(pnlLogin);
	}

	private void initPanels() {
		pnlContainer.setVisible(true);
		pnlLogin = new Login(this);
		pnlIndex = new Index(this);
		pnlReg = new Register(this);
	}

	/**
	 * Enables and disables the relevant components for a logged in user depending on the user role.
	 */
	public void enableLoggedInInterface(Role role) {
		
//		mnuArchiveLogout.setEnabled(loggedIn);
//		mnuArchiveLogout.setVisible(loggedIn);
//		mnuArchiveLogin.setEnabled(!loggedIn);
//		mnuArchiveLogin.setVisible(!loggedIn);
//		mnuAdministration.setEnabled(loggedIn);
//		mnuAdministration.setVisible(loggedIn);
//		mnuAdministrationRegisterTeacher.setVisible(isAdmin);
//		mnuAdministrationAlterHead.setVisible(isAdmin);
//		mnuAdministrationAlterPrefect.setVisible(isAdmin);
//		mnuAdministrationAlterTeacher.setVisible(isAdmin);
//		mnuSettings.setVisible(loggedIn);
//		mnuSettings.setEnabled(loggedIn);
	}

	/**
	 * Removes all panels from the panel container and adds the specified panel to it.
	 */
	public void switchPanel(JPanel panel) {
		// Remove the current panel.
		pnlContainer.removeAll();
		pnlContainer.repaint();
		pnlContainer.revalidate();

		// Add the specified panel to the wrapping panel.
		pnlContainer.add(panel);
		pnlContainer.repaint();
		pnlContainer.revalidate();
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                pnlContainer = new javax.swing.JPanel();
                jMenuBar1 = new javax.swing.JMenuBar();
                jMenu1 = new javax.swing.JMenu();
                jMenuItem1 = new javax.swing.JMenuItem();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                pnlContainer.setPreferredSize(new java.awt.Dimension(1024, 768));
                pnlContainer.setLayout(new java.awt.CardLayout());

                jMenu1.setText("Arkiv");

                jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, 0));
                jMenuItem1.setText("Avsluta");
                jMenu1.add(jMenuItem1);

                jMenuBar1.add(jMenu1);

                setJMenuBar(jMenuBar1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JMenu jMenu1;
        private javax.swing.JMenuBar jMenuBar1;
        private javax.swing.JMenuItem jMenuItem1;
        private javax.swing.JPanel pnlContainer;
        // End of variables declaration//GEN-END:variables
}
