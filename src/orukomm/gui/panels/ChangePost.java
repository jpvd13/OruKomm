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
public class ChangePost extends javax.swing.JPanel {

    /**
     * Creates new form FormalFeed
     */
    private ArrayList<Post> posts;
    
    private static String bildURL;
    private static String fileURL;
    private static String fileURL2;
    private static String fileURL3;
    private String title;
    private String description;
    private String username;
    private int role;
    private ArrayList<Post> userPosts;
    private ImageIcon imageIcon;
    private ImageIcon resizedImage;
    private int pictureID;
    
           
    private int post_id;
    DisplayPostV2 dsv;
    ChangePostFormal1 formal;
    DisplayPostInformal informal;
    FileStorage fs = new FileStorage();
    
    public ChangePost(MainWindow parentFrame) {
        try {
            //this.dsv = new DisplayPostV2(pnlPost, description, title, post_id);
            this.formal = new ChangePostFormal1((this), description, title, post_id);
            this.informal = new DisplayPostInformal((this), description, title);
            this.username = parentFrame.loggedInUser.getUsername();
            this.role = parentFrame.loggedInUser.getRole();
            initComponents();
            initPanels();
        } catch (IOException ex) {
            Logger.getLogger(ChangePost.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void initPanels() {
		pnlPost.setVisible(true);
	}
    
    public void switchPanel(JPanel panel) {
		pnlPost.removeAll();
		pnlPost.repaint();
		pnlPost.revalidate();
		pnlPost.add(panel);
		pnlPost.repaint();
		pnlPost.revalidate();
	}
    
  

    public void fillTable(ArrayList<Post> list) {
        

        DefaultTableModel model = (DefaultTableModel) tblFormalFeed.getModel();  //Typecastar JTablemodellen till en DefaultTableModel
        Object[] row = new Object[3];    // Använder Object klassen så att Arrayn kan ta in vilka object som helst
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getTitle();
            row[1] = list.get(i).getUsername();
            row[2] = list.get(i).getDate();
            //row[3] = posts.get(i).getId(); //Ska tas bort när vi hittar lösning på hur vi hämtar ut post ID till attachments
            model.addRow(row);
        }

    }
   
        
    
    
    public void clearTable()
    {
     DefaultTableModel model = (DefaultTableModel) tblFormalFeed.getModel();
     model.setRowCount(0);
    }
    
    public int getPostId(){
        
        return post_id;
    }
    
private void selectPost(){
        int columnTitle = 0;
        int columnPoster = 1;
        pnlPost.setVisible(true);
      
        
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
            switchPanel(new ChangePostFormal1((this), description, title, post_id));
        } catch (IOException ex) {
            Logger.getLogger(FormalFeed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public ArrayList<Post> userPosts() 
   {
       ArrayList<Post> userPosts = new ArrayList<>(); 
       for(Post post : posts){
           if (post.getUsername().equals(username))
           {
               Post ponta = post;
               userPosts.add(ponta);
           }
       }
       return userPosts;
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
            fs.selectFile(getPostId(), chooser.getSelectedFile().toString()+"\\");
        } else {
            System.out.println("No Selection ");
        }
    }

    public ArrayList<String> getFileName() {
        FileStorage fs = new FileStorage();               

        ArrayList<String> fileNames = new ArrayList<String>();

        String selectSQL = ("SELECT name FROM attachments WHERE post_id = ? AND type = 0");
        try (Connection conn = fs.connect();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, getPostId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){

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
        String selectSQL = ("SELECT file, id FROM attachments WHERE post_id = ? AND type = 1");
        try (Connection conn = fs.connect();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, getPostId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                
                byte[] bytes = rs.getBytes("file");
                rs.getInt("id");
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
     

        
       

    
        
        
    

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupFeed = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormalFeed = new javax.swing.JTable();
        pnlPost = new javax.swing.JPanel();
        rbtnFormal = new javax.swing.JRadioButton();
        rbtnInformal = new javax.swing.JRadioButton();
        btnDelete = new javax.swing.JButton();

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

        pnlPost.setLayout(new java.awt.CardLayout());

        btnGroupFeed.add(rbtnFormal);
        rbtnFormal.setText("Formella inlägg");
        rbtnFormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFormalActionPerformed(evt);
            }
        });

        btnGroupFeed.add(rbtnInformal);
        rbtnInformal.setText("Informella inlägg");
        rbtnInformal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnInformalActionPerformed(evt);
            }
        });

        btnDelete.setText("Ta bort inlägg");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rbtnFormal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnInformal))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(445, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnFormal)
                    .addComponent(rbtnInformal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnDelete)
                .addContainerGap(130, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblFormalFeedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormalFeedMouseClicked
        selectPost();     
      
    }//GEN-LAST:event_tblFormalFeedMouseClicked

    private void rbtnFormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFormalActionPerformed
        clearTable();
            PostRepository pr = new PostRepository();
            
           
        if(role == 2)
        {
            this.posts = pr.fillListFormal();
            userPosts = userPosts();
            fillTable(userPosts);
        }
        else
        {
            this.posts = pr.fillListFormal();
        fillTable(posts);
        }
    }//GEN-LAST:event_rbtnFormalActionPerformed

    private void rbtnInformalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnInformalActionPerformed
        clearTable();
          PostRepository pr = new PostRepository();
          
          if (role == 2)
          {
          this.posts = pr.fillListInformal();
          userPosts = userPosts();
          fillTable(userPosts);
          }
          
          else {
          this.posts = pr.fillListInformal();
          fillTable(posts);
          }
    }//GEN-LAST:event_rbtnInformalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.ButtonGroup btnGroupFeed;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlPost;
    private javax.swing.JRadioButton rbtnFormal;
    private javax.swing.JRadioButton rbtnInformal;
    public javax.swing.JTable tblFormalFeed;
    // End of variables declaration//GEN-END:variables
}
