package orukomm.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import javax.swing.JPanel;
import orukomm.Settings;
import orukomm.data.entities.User;
import orukomm.data.entities.User.PermissionFlag;
import orukomm.gui.dialogs.AddCategory;
import orukomm.gui.panels.AdminUpdateUsers;
import orukomm.gui.panels.ChangePost;
import orukomm.gui.panels.CreateMeeting;
import orukomm.gui.panels.CreatePostPanel;
import orukomm.gui.panels.CreateResearchGroup;
import orukomm.gui.panels.EditAndDeleteMeetings;
import orukomm.gui.panels.FormalFeed;
import orukomm.gui.panels.Login;
import orukomm.gui.panels.Meetings;
import orukomm.gui.panels.Register;
import orukomm.gui.panels.UpdateAccount;
import orukomm.gui.panels.InformalFeed;
import orukomm.gui.panels.PickCategories;
//import orukomm.gui.panels.ChangePost;

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
        mnuAccountEdit.setVisible(hasUserPermission);
        mnuFeed.setVisible(hasUserPermission);
        mnuPost.setVisible(hasUserPermission);
        mnuMeetings.setVisible(hasUserPermission);
        

        mnuAdministrationEditUser.setVisible(hasAdminPermission);
        mnuEditMeetings.setVisible(hasAdminPermission);
        
        mnuAdministration.setVisible(hasSuperadminPermission);
        mnuAdministrationAddCategory.setVisible(hasSuperadminPermission);
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

        mnuAdministrationAddCategory.setActionCommand("mnuAdministrationAddCategory");
        mnuAdministrationAddCategory.addActionListener(this);

        mnuAdministrationEditUser.setActionCommand("mnuAdministrationEditUser");
        mnuAdministrationEditUser.addActionListener(this);

        mnuAccountLogout.setActionCommand("mnuAccountLogout");
        mnuAccountLogout.addActionListener(this);

        mnuAccountEdit.setActionCommand("mnuAccountEdit");
        mnuAccountEdit.addActionListener(this);

        mnuFormalFeed.setActionCommand("mnuFormalFeed");
        mnuFormalFeed.addActionListener(this);
        
        mnuInformalFeed.setActionCommand("mnuInformalFeed");
        mnuInformalFeed.addActionListener(this);

        mnuNewPost.setActionCommand("mnuNewPost");
        mnuNewPost.addActionListener(this);
        
        mnuChangePost.setActionCommand("mnuChangePost");
        mnuChangePost.addActionListener(this);
        
        mnuMeetingCreate.setActionCommand("mnuMeetingCreate");
        mnuMeetingCreate.addActionListener(this);
        
        mnuMyMeetings.setActionCommand("mnuMyMeetings");
        mnuMyMeetings.addActionListener(this);
        
        mnuFiltratePosts.setActionCommand("mnuFiltratePosts");
        mnuFiltratePosts.addActionListener(this);
        
        mnuFeedResearch.setActionCommand("mnuFeedResearch");
        mnuFeedResearch.addActionListener(this);
        
        mnuEditMeetings.setActionCommand("mnuEditMeetings");
        mnuEditMeetings.addActionListener(this);
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

            case "mnuAdministrationAddCategory":
                new AddCategory(this, true).setVisible(true);
                break;

            case "mnuAdministrationEditUser":
                switchPanel(new AdminUpdateUsers(this));
                break;

            case "mnuAccountLogout":
                logout();
                break;

            case "mnuAccountEdit":
                switchPanel(new UpdateAccount((this)));
                break;

            case "mnuFormalFeed":
                FormalFeed formalFeed = new FormalFeed(this);
                formalFeed.fillTable();
                switchPanel(formalFeed);
                break;
                
            case "mnuInformalFeed":
                InformalFeed informalFeed = new InformalFeed(this);
                informalFeed.fillTable();
                switchPanel(informalFeed);
                break;
                
           case "mnuChangePost":
               ChangePost changePost = new ChangePost(this);
               switchPanel(changePost);
               break;

            case "mnuNewPost":
                switchPanel(new CreatePostPanel((this)));
                break;
                
            case "mnuMeetingCreate":
                switchPanel(new CreateMeeting(this));
                break;
                
            case "mnuMyMeetings":
                switchPanel(new Meetings(this));
                break;
                
            case "mnuFiltratePosts":
                switchPanel(new PickCategories(this));
                break;
                
            case "mnuFeedResearch":
                switchPanel(new CreateResearchGroup(this));
                break;
                
            case "mnuEditMeetings":
                switchPanel(new EditAndDeleteMeetings(this));
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
        mnuAdministrationUsers = new javax.swing.JMenu();
        mnuAdministrationRegister = new javax.swing.JMenuItem();
        mnuAdministrationEditUser = new javax.swing.JMenuItem();
        mnuAdministrationAddCategory = new javax.swing.JMenuItem();
        mnuAccount = new javax.swing.JMenu();
        mnuAccountEdit = new javax.swing.JMenuItem();
        mnuAccountLogout = new javax.swing.JMenuItem();
        mnuFeed = new javax.swing.JMenu();
        mnuFormalFeed = new javax.swing.JMenuItem();
        mnuInformalFeed = new javax.swing.JMenuItem();
        mnuFiltratePosts = new javax.swing.JMenuItem();
        mnuFeedResearch = new javax.swing.JMenuItem();
        mnuPost = new javax.swing.JMenu();
        mnuNewPost = new javax.swing.JMenuItem();
        mnuChangePost = new javax.swing.JMenuItem();
        mnuMeetings = new javax.swing.JMenu();
        mnuMeetingCreate = new javax.swing.JMenuItem();
        mnuMyMeetings = new javax.swing.JMenuItem();
        mnuEditMeetings = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlContainer.setPreferredSize(new java.awt.Dimension(1024, 768));
        pnlContainer.setLayout(new java.awt.CardLayout());

        mnuArchive.setText("Arkiv");

        mnuArchiveExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuArchiveExit.setText("Avsluta");
        mnuArchive.add(mnuArchiveExit);

        mnubMain.add(mnuArchive);

        mnuAdministration.setText("Administration");

        mnuAdministrationUsers.setText("Användare");

        mnuAdministrationRegister.setText("Registrera");
        mnuAdministrationUsers.add(mnuAdministrationRegister);

        mnuAdministrationEditUser.setText("Redigera");
        mnuAdministrationUsers.add(mnuAdministrationEditUser);

        mnuAdministration.add(mnuAdministrationUsers);

        mnuAdministrationAddCategory.setText("Lägg till ny bloggkategori");
        mnuAdministration.add(mnuAdministrationAddCategory);

        mnubMain.add(mnuAdministration);

        mnuAccount.setText("Konto");

        mnuAccountEdit.setText("Ändra kontouppgifter");
        mnuAccount.add(mnuAccountEdit);

        mnuAccountLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuAccountLogout.setText("Logga ut");
        mnuAccount.add(mnuAccountLogout);

        mnubMain.add(mnuAccount);

        mnuFeed.setText("Flöde");

        mnuFormalFeed.setText("Formellt flöde");
        mnuFeed.add(mnuFormalFeed);

        mnuInformalFeed.setText("Informellt flöde");
        mnuInformalFeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInformalFeedActionPerformed(evt);
            }
        });
        mnuFeed.add(mnuInformalFeed);

        mnuFiltratePosts.setText("Filtrera inlägg");
        mnuFeed.add(mnuFiltratePosts);

        mnuFeedResearch.setText("Forskning");
        mnuFeed.add(mnuFeedResearch);

        mnubMain.add(mnuFeed);

        mnuPost.setText("Inlägg");

        mnuNewPost.setText("Skriv Inlägg");
        mnuNewPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewPostActionPerformed(evt);
            }
        });
        mnuPost.add(mnuNewPost);

        mnuChangePost.setText("Redigera inlägg");
        mnuPost.add(mnuChangePost);

        mnubMain.add(mnuPost);

        mnuMeetings.setText("Möten");

        mnuMeetingCreate.setText("Skapa nytt möte");
        mnuMeetings.add(mnuMeetingCreate);

        mnuMyMeetings.setText("Mina möten");
        mnuMeetings.add(mnuMyMeetings);

        mnuEditMeetings.setText("Redigera möten");
        mnuMeetings.add(mnuEditMeetings);

        mnubMain.add(mnuMeetings);

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

    private void mnuNewPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewPostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuNewPostActionPerformed

    private void mnuInformalFeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInformalFeedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuInformalFeedActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu mnuAccount;
    private javax.swing.JMenuItem mnuAccountEdit;
    private javax.swing.JMenuItem mnuAccountLogout;
    private javax.swing.JMenu mnuAdministration;
    private javax.swing.JMenuItem mnuAdministrationAddCategory;
    private javax.swing.JMenuItem mnuAdministrationEditUser;
    private javax.swing.JMenuItem mnuAdministrationRegister;
    private javax.swing.JMenu mnuAdministrationUsers;
    private javax.swing.JMenu mnuArchive;
    private javax.swing.JMenuItem mnuArchiveExit;
    private javax.swing.JMenuItem mnuChangePost;
    private javax.swing.JMenuItem mnuEditMeetings;
    private javax.swing.JMenu mnuFeed;
    private javax.swing.JMenuItem mnuFeedResearch;
    private javax.swing.JMenuItem mnuFiltratePosts;
    private javax.swing.JMenuItem mnuFormalFeed;
    private javax.swing.JMenuItem mnuInformalFeed;
    private javax.swing.JMenuItem mnuMeetingCreate;
    private javax.swing.JMenu mnuMeetings;
    private javax.swing.JMenuItem mnuMyMeetings;
    private javax.swing.JMenuItem mnuNewPost;
    private javax.swing.JMenu mnuPost;
    private javax.swing.JMenuBar mnubMain;
    private javax.swing.JPanel pnlContainer;
    // End of variables declaration//GEN-END:variables

}
