package orukomm.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import javax.swing.JPanel;
import orukomm.Settings;
import orukomm.data.entities.User;
import orukomm.data.entities.User.PermissionFlag;
import orukomm.gui.panels.Login;
import orukomm.gui.panels.Register;

public class MainWindow extends javax.swing.JFrame implements ActionListener {

	// Global user object of the logged in user.
	public User loggedInUser = new User();
	
	public MainWindow() {
		setTitle(Settings.WINDOW_TITLE);
		setResizable(false);
		initComponents();
		initPanels();
		setLocationRelativeTo(null);
		setVisible(true);
		switchPanel(new Login(this));
		addActionListeners();
		enableLoggedInInterface(Settings.LOGGED_OUT_ROLE);
		
	}
	
	private void initPanels() {
		pnlContainer.setVisible(true);
	}

	/*
	 * Enables and disables GUI components for a user role, depending on its permissions.
	 */
	public void enableLoggedInInterface(EnumSet<PermissionFlag> userRole) {
		boolean hasUserPermission = userRole.contains(PermissionFlag.USER);
		boolean hasAdminPermission = userRole.contains(PermissionFlag.ADMIN);
		boolean hasSuperadminPermission = userRole.contains(PermissionFlag.SUPERADMIN);
		
		mnuAccount.setVisible(hasUserPermission);
		mnuAdministration.setVisible(hasSuperadminPermission);
	}

	/*
	 * Removes current panel attached to the panel container and adds the specified panel to it.
	 */
	public void switchPanel(JPanel panel) {
		pnlContainer.removeAll();
		pnlContainer.repaint();
		pnlContainer.revalidate();
		pnlContainer.add(panel);
		pnlContainer.repaint();
		pnlContainer.revalidate();
	}
	
	private void addActionListeners() {
		mnuArchiveExit.setActionCommand("mnuArchiveExit");
		mnuArchiveExit.addActionListener(this);
		
		mnuAdministrationRegister.setActionCommand("mnuAdministrationRegister");
		mnuAdministrationRegister.addActionListener(this);
		
		mnuAccountLogout.setActionCommand("mnuAccountLogout");
		mnuAccountLogout.addActionListener(this);
	}

	/*
	 * Handle menu events.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "mnuArchiveExit":
				this.dispatchEvent(new java.awt.event.WindowEvent(this, java.awt.event.WindowEvent.WINDOW_CLOSING));
				break;
			
			case "mnuAdministrationRegister":
				switchPanel(new Register((this)));
				break;
				
			case "mnuAccountLogout":
				logout();
				break;
		}
	}
	
	/*
	 * Reset logged in user object, wipe out the old one, and set the appropriate GUI.
	 */
	private void logout() {
		loggedInUser = new User();
		System.gc();
		enableLoggedInInterface(Settings.LOGGED_OUT_ROLE);
		setTitle(Settings.WINDOW_TITLE);
		switchPanel(new Login((this)));
	}
	
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                pnlContainer = new javax.swing.JPanel();
                mnubMain = new javax.swing.JMenuBar();
                mnuArchive = new javax.swing.JMenu();
                mnuArchiveExit = new javax.swing.JMenuItem();
                mnuAdministration = new javax.swing.JMenu();
                mnuAdministrationRegister = new javax.swing.JMenuItem();
                mnuAccount = new javax.swing.JMenu();
                mnuAccountLogout = new javax.swing.JMenuItem();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                pnlContainer.setPreferredSize(new java.awt.Dimension(1024, 768));
                pnlContainer.setLayout(new java.awt.CardLayout());

                mnuArchive.setText("Arkiv");

                mnuArchiveExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
                mnuArchiveExit.setText("Avsluta");
                mnuArchive.add(mnuArchiveExit);

                mnubMain.add(mnuArchive);

                mnuAdministration.setText("Administration");

                mnuAdministrationRegister.setText("Registrera användare");
                mnuAdministration.add(mnuAdministrationRegister);

                mnubMain.add(mnuAdministration);

                mnuAccount.setText("Konto");

                mnuAccountLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
                mnuAccountLogout.setText("Logga ut");
                mnuAccount.add(mnuAccountLogout);

                mnubMain.add(mnuAccount);

                setJMenuBar(mnubMain);

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
        private javax.swing.JMenu mnuAccount;
        private javax.swing.JMenuItem mnuAccountLogout;
        private javax.swing.JMenu mnuAdministration;
        private javax.swing.JMenuItem mnuAdministrationRegister;
        private javax.swing.JMenu mnuArchive;
        private javax.swing.JMenuItem mnuArchiveExit;
        private javax.swing.JMenuBar mnubMain;
        private javax.swing.JPanel pnlContainer;
        // End of variables declaration//GEN-END:variables

}
