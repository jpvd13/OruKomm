/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.gui.panels;

import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import orukomm.data.FileStorage;
import orukomm.data.entities.Post;
import orukomm.data.repositories.PostRepository;
import orukomm.gui.MainWindow;

/**
 *
 * @author Ludvig
 */
public class InformalFeed extends javax.swing.JPanel {

    /**
     * Creates new form FormalFeed
     */
    private ArrayList<Post> posts;
    private FileStorage fs = new FileStorage();
    private String title;
    private String description;
    private int post_id;
    DisplayPostInformal dsv;
    private ImageIcon imageIcon;
    private ImageIcon resizedImage;
    

    

    public InformalFeed(MainWindow parentFrame) {
        try {
            this.dsv = new DisplayPostInformal((this), description, title);
            initComponents();
            PostRepository pr = new PostRepository();
            this.posts = pr.fillListInformal();
            initPanels();

        } catch (IOException ex) {
            Logger.getLogger(InformalFeed.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initPanels() {
        pnlInfoFeed.setVisible(true);
    }

    public void switchPanel(JPanel panel) {
        pnlInfoFeed.removeAll();
        pnlInfoFeed.repaint();
        pnlInfoFeed.revalidate();
        pnlInfoFeed.add(panel);
        pnlInfoFeed.repaint();
        pnlInfoFeed.revalidate();
    }

    public void fillTable() {

        DefaultTableModel model = (DefaultTableModel) tblFormalFeed.getModel();  //Typecastar JTablemodellen till en DefaultTableModel
        Object[] row = new Object[3];    // Använder Object klassen så att Arrayn kan ta in vilka object som helst
        for (int i = 0; i < posts.size(); i++) {
            row[0] = posts.get(i).getTitle();
            row[1] = posts.get(i).getUsername();
            row[2] = posts.get(i).getDate();

            model.addRow(row);
        }

    }

    public int getPostId() {
        return post_id;
    }
    
    private void selectPost(){
        int columnTitle = 0;
        int columnPoster = 1;

        int row = tblFormalFeed.getSelectedRow();
        title = tblFormalFeed.getModel().getValueAt(row, columnTitle).toString();
        String poster = tblFormalFeed.getModel().getValueAt(row, columnPoster).toString();
        description = "";

        for (Post post : posts) {
            if (post.getUsername().equals(poster) && post.getTitle().equals(title)) {
                description = post.getDescription();
                post_id = post.getId();
                
                
            }
        }
        try {
            switchPanel(new DisplayPostInformal((this), description, title));
        } catch (IOException ex) {
            Logger.getLogger(InformalFeed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
            
    public void chooseDirectory() {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose destination");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            fs.selectFile(getPostId(), chooser.getSelectedFile().toString() + "\\");
        } else {
            System.out.println("No Selection ");
        }
    }

    public ArrayList<String> getFileName() {
        FileStorage fs = new FileStorage();

        ArrayList<String> fileNames = new ArrayList<String>();

        String selectSQL = ("SELECT name FROM attachments WHERE post_id = ?");
        try (Connection conn = fs.connect();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, getPostId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                fileNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return fileNames;
    }

    public ImageIcon selectImage() throws IOException {
        resizedImage = new ImageIcon();
        imageIcon = new ImageIcon();
        String selectSQL = ("SELECT file FROM attachments WHERE post_id = ?");
        try (Connection conn = fs.connect();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, getPostId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                
                byte[] bytes = rs.getBytes("file");
                imageIcon = new ImageIcon(bytes);
                Image img = imageIcon.getImage();
                Image newImg = img.getScaledInstance(607, 388, Image.SCALE_SMOOTH);
                resizedImage = new ImageIcon(newImg);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormalFeed.class.getName()).log(Level.SEVERE, null, ex);

        }
       
        return resizedImage;
    } 

   /* public ImageIcon getImage() {
        return ii;

    }*/

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormalFeed = new javax.swing.JTable();
        pnlInfoFeed = new javax.swing.JPanel();

        tblFormalFeed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titel", "Författare", "Datum"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFormalFeed.getTableHeader().setReorderingAllowed(false);
        tblFormalFeed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFormalFeedMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFormalFeed);
        if (tblFormalFeed.getColumnModel().getColumnCount() > 0) {
            tblFormalFeed.getColumnModel().getColumn(0).setResizable(false);
            tblFormalFeed.getColumnModel().getColumn(1).setResizable(false);
            tblFormalFeed.getColumnModel().getColumn(2).setResizable(false);
        }

        pnlInfoFeed.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlInfoFeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlInfoFeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(198, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblFormalFeedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormalFeedMouseClicked
       selectPost();
       
            

    }//GEN-LAST:event_tblFormalFeedMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlInfoFeed;
    public javax.swing.JTable tblFormalFeed;
    // End of variables declaration//GEN-END:variables
}
