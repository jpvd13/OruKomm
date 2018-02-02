/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame; 
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import orukomm.data.FileStorage;
import orukomm.data.entities.Category;
import orukomm.data.entities.User;
import orukomm.data.repositories.CategoryRepository;
import orukomm.gui.MainWindow;

/**
 *
 * @author Pontu
 */
public class CreatePost extends javax.swing.JFrame {
    private static String bildURL;
    private static String fileURL;
    private static String fileURL2;
    private static String fileURL3;
    private static String textPost;
    private static String textHeading;
    private FileStorage fs;
    private String date;
    

    public CreatePost() {
        initComponents();
        fs = new FileStorage();
        getDate();
        
        lstCategory.setSelectedIndex(0);

        String selectedCategory = lstCategory.getSelectedValue();
        //fillTextFields(selectedCategory);
        
         lstCategory.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selectedCategory = lstCategory.getSelectedValue();
                if (selectedCategory != null) {
                    
                }
            }
        });
    }
    
    
    
    private void getDate(){        
        
        LocalDate localDate = LocalDate.now();
        date = localDate.toString();       
        
    }
    
    private void setTextPost(){
        textPost = TxtInput.getText();
    }
    
    private void setHeadingPost() { 
        textHeading = TxtHeading.getText();
    }
   
    public static String getBildURL() {
        return bildURL;
    }

    public static String getFileURL() {
        return fileURL;
    }

    public static String getFileURL2() {
        return fileURL2;
    }

    public static String getFileURL3() {
        return fileURL3;
    }
      
     
    private JFrame buildFrame() throws IOException {
        JFrame frame = new CreatedPost(textPost, textHeading);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(605, 720);
        frame.setVisible(true);
        return frame;
    }
    

    public void insertAttachedFiles(){
                
        if(!lblURL1.getText().isEmpty() && !lblURL2.getText().isEmpty() && !lblURL3.getText().isEmpty())
        {
        fs.insertFile(fileURL);
        fs.insertFile(fileURL2);
        fs.insertFile(fileURL3);
    }
        else if (!lblURL1.getText().isEmpty() && !lblURL2.getText().isEmpty())
                {
        fs.insertFile(fileURL);
        fs.insertFile(fileURL2); 
                }
        else if (!lblURL1.getText().isEmpty())
        {
        fs.insertFile(fileURL);
        }
        else {
           
        }
    }
    public void insertAttachedPicture()
    {
            
    
            if(!lblImageURL.getText().isEmpty())
            {
                fs.insertFile(bildURL);
                
            }
    } 

    
    public void insertAttachedText()
    {
        User user = new User();
        int id = user.getId();
        
        if(!TxtInput.getText().isEmpty())
        {
            
        }
        else {
                
                }
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtInput = new javax.swing.JTextArea();
        lblBifogad = new javax.swing.JLabel();
        lblBifogad2 = new javax.swing.JLabel();
        lblBifogad3 = new javax.swing.JLabel();
        TxtHeading = new javax.swing.JTextField();
        lblURL1 = new javax.swing.JLabel();
        lblURL2 = new javax.swing.JLabel();
        lblURL3 = new javax.swing.JLabel();
        btnClearURL3 = new javax.swing.JButton();
        btnClearURL2 = new javax.swing.JButton();
        btnClearImage = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblBifogadBild = new javax.swing.JLabel();
        lblImageURL = new javax.swing.JLabel();
        btnClearURL4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstCategory = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Skapa inlägg");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Bifoga bild");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Bifoga fil");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        TxtInput.setColumns(20);
        TxtInput.setRows(5);
        jScrollPane1.setViewportView(TxtInput);

        lblBifogad.setText("Bifogad fil 1:");

        lblBifogad2.setText("Bifogad fil 2:");

        lblBifogad3.setText("Bifogad fil 3:");

        btnClearURL3.setText("X");
        btnClearURL3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearURL3ActionPerformed(evt);
            }
        });

        btnClearURL2.setText("X");
        btnClearURL2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearURL2ActionPerformed(evt);
            }
        });

        btnClearImage.setText("X");
        btnClearImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearImageActionPerformed(evt);
            }
        });

        jLabel1.setText("Rensa");

        lblBifogadBild.setText("Bifogad bild:");

        btnClearURL4.setText("X");
        btnClearURL4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearURL4ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(lstCategory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblBifogad, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBifogad3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBifogadBild, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBifogad2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lblURL1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblImageURL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblURL3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnClearImage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnClearURL4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lblURL2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnClearURL3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnClearURL2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(69, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jButton2)
                                .addGap(133, 133, 133)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(39, 39, 39)
                        .addComponent(TxtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBifogad)
                            .addComponent(lblURL1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnClearURL4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblURL2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblBifogad2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBifogad3)
                        .addGap(1, 1, 1)
                        .addComponent(lblURL3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(lblImageURL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClearURL2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClearURL3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClearImage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBifogadBild))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButton1)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
     
        setTextPost();
        setHeadingPost();
        
       
        
        try {
            buildFrame();
            
        } catch (IOException ex) {
            Logger.getLogger(CreatePost.class.getName()).log(Level.SEVERE, null, ex);
        }
//        insertAttachedPicture();
//        insertAttachedFiles();
        
       
       
        
       
     
         
     
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
           JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              String path = selectedFile.getAbsolutePath();
              this.bildURL = path;
              
              lblImageURL.setText(path);
          }
           
          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              String path = selectedFile.getAbsolutePath();
              
              
              if(lblURL1.getText().isEmpty()) {
              lblURL1.setText(path);
              fileURL = path;
             }
              
              else if(lblURL2.getText().isEmpty()) {
                  lblURL2.setText(path);
                  fileURL2 = path;
                  
              } else if(lblURL3.getText().isEmpty()){
                  lblURL3.setText(path);
                  fileURL3 = path;
              }
            
              
          }
      
          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
             
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnClearImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearImageActionPerformed
        lblImageURL.setText("");
              bildURL = "";
    }//GEN-LAST:event_btnClearImageActionPerformed

    private void btnClearURL2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearURL2ActionPerformed
        lblURL2.setText("");
              fileURL2 = "";
    }//GEN-LAST:event_btnClearURL2ActionPerformed

    private void btnClearURL3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearURL3ActionPerformed
        lblURL3.setText("");
              fileURL3 = "";
    }//GEN-LAST:event_btnClearURL3ActionPerformed

    private void btnClearURL4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearURL4ActionPerformed
        lblURL1.setText("");
        fileURL = "";
    }//GEN-LAST:event_btnClearURL4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreatePost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreatePost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreatePost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreatePost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreatePost().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtHeading;
    private javax.swing.JTextArea TxtInput;
    private javax.swing.JButton btnClearImage;
    private javax.swing.JButton btnClearURL2;
    private javax.swing.JButton btnClearURL3;
    private javax.swing.JButton btnClearURL4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBifogad;
    private javax.swing.JLabel lblBifogad2;
    private javax.swing.JLabel lblBifogad3;
    private javax.swing.JLabel lblBifogadBild;
    private javax.swing.JLabel lblImageURL;
    private javax.swing.JLabel lblURL1;
    private javax.swing.JLabel lblURL2;
    private javax.swing.JLabel lblURL3;
    private javax.swing.JList<String> lstCategory;
    // End of variables declaration//GEN-END:variables
}
