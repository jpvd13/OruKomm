/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.gui.panels;

import java.awt.Cursor;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import orukomm.data.FileStorage;
import orukomm.data.repositories.PostRepository;
import orukomm.gui.panels.FormalFeed;

/**
 *
 * @author Pontu
 */
public class ChangePostFormal1 extends javax.swing.JPanel {

    private static String textPost;
    private static String title;
    private static ImageIcon image;
    private FileStorage fs = new FileStorage();
    private ArrayList<String> fileNames = new ArrayList<String>();
    private FormalFeed ff;
    private ChangePost chp;
    private int pictureID;
    private static ImageIcon attachedImage;
    private static String bildURL;
    private int post_id;

    /**
     * Creates new form CreatedPost
     */
    public ChangePostFormal1() {
    }

    public ChangePostFormal1(ChangePost chp, String textPost, String title, int post_id) throws IOException {
        initComponents();
        //bildURL = CreatePostPanel.getBildURL();
        // fileName = CreatePost.getFileURL();
        // fileName2 = CreatePost.getFileURL2();
        // fileURL3 = CreatePost.getFileURL3();  

        //paintPicture(lblDisplay);
        
        this.chp = chp;
        this.textPost = textPost;
        this.post_id = post_id;
        
        fileNames = chp.getFileName();
        this.title = title;
        this.image = chp.selectImage();
        
        
        btnFile1.setVisible(false);
        btnFile2.setVisible(false);
        btnFile3.setVisible(false);
        
        
        
        paintPicture();
        PostRepository pr = new PostRepository();
        if (pr.hasPictureFile(post_id))
        {
        btnRemove.setVisible(true);
        btnAdd.setVisible(false);
        }
        else{
            btnRemove.setVisible(false);
        btnAdd.setVisible(true);
        }
        
        System.out.println(image);
        
        setTxtHeadingPost();
        setAttachedFilesTxt();
        setTxtCreatedPost();

        txtUserOutput.setEditable(true);
        txtUserOutput.setLineWrap(true);
        txtUserOutput.setWrapStyleWord(true);

        
    }
    

    private void setTxtCreatedPost() {
        txtUserOutput.setText(textPost);

    }

    public void setTxtHeadingPost() {
        txtHeading.setText(title);

    }

    public void paintPicture() throws IOException {
        lblDisplay.setIcon(image);
        
    }

    private void setAttachedFilesTxt() {

        if (fileNames.size() == 1) {
            btnFile1.setVisible(true);
            btnAddFile1.setVisible(false);
            
            lblURL1.setText(fileNames.get(0));
            lblBifogad1.setText("Bifogad fil1: ");

            lblURL1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if (fileNames.size() == 2) {
            btnFile1.setVisible(true);
            btnFile2.setVisible(true);
            btnAddFile1.setVisible(false);
            btnAddFile2.setVisible(false);
            lblURL1.setText(fileNames.get(0));
            lblURL2.setText(fileNames.get(1));

            lblBifogad1.setText("Bifogad fil1: ");
            lblBifogad2.setText("Bifogad fil2: ");

            lblURL1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblURL2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if (fileNames.size() == 3) {
            btnFile1.setVisible(true);
            btnFile2.setVisible(true);
            btnFile3.setVisible(true);
            btnAddFile1.setVisible(false);
            btnAddFile2.setVisible(false);
            btnAddFile3.setVisible(false);
            lblURL1.setText(fileNames.get(0));
            lblURL2.setText(fileNames.get(1));
            lblURL3.setText(fileNames.get(2));

            lblBifogad1.setText("Bifogad fil1: ");
            lblBifogad2.setText("Bifogad fil2: ");
            lblBifogad3.setText("Bifogad fil3: ");

            lblURL1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblURL2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblURL3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        
         lblURL1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         lblURL2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         lblURL3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    public void removePicture(int id)
    {
        PostRepository pr = new PostRepository();
        pr.deletePicture(id);
        lblDisplay.setIcon(null);
    }
    
    private ImageIcon resizeImage(JLabel label1) {
        ImageIcon MyImage = new ImageIcon(bildURL);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label1.getWidth(), label1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        this.image = image;
        
        return image;
    }
    
    public void paintPicture2() {
        resizeImage(lblDisplay);
        lblDisplay.setIcon(image);
    }
    
     public void insertAttachedPicture() {
            fs.insertPictureFile(bildURL, post_id);
    }
     
     public String fileDirectory()
     {
                    JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        String fileURL = "";
        
        
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            fileURL = path;
            
            
                    

            

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        }
        
        return fileURL;

     }
    
     
   
    /**
     * /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDisplay = new javax.swing.JLabel();
        lblBifogad1 = new javax.swing.JLabel();
        lblBifogad3 = new javax.swing.JLabel();
        lblURL1 = new javax.swing.JLabel();
        lblURL2 = new javax.swing.JLabel();
        lblURL3 = new javax.swing.JLabel();
        lblBifogad2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtUserOutput = new javax.swing.JTextArea();
        btnChange = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnFile1 = new javax.swing.JButton();
        btnFile2 = new javax.swing.JButton();
        btnFile3 = new javax.swing.JButton();
        btnAddFile2 = new javax.swing.JButton();
        btnAddFile3 = new javax.swing.JButton();
        btnAddFile1 = new javax.swing.JButton();
        txtHeading = new javax.swing.JTextField();

        lblURL1.setForeground(new java.awt.Color(0, 0, 255));
        lblURL1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblURL1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblURL1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblURL1MouseEntered(evt);
            }
        });

        lblURL2.setForeground(new java.awt.Color(0, 0, 255));
        lblURL2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblURL2.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblURL2ComponentAdded(evt);
            }
        });

        lblURL3.setForeground(new java.awt.Color(0, 0, 255));
        lblURL3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblURL3.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblURL3ComponentAdded(evt);
            }
        });

        lblBifogad2.setText(" ");

        txtUserOutput.setColumns(20);
        txtUserOutput.setRows(5);
        jScrollPane1.setViewportView(txtUserOutput);

        btnChange.setText("Spara");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        btnRemove.setText("Ta bort");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnAdd.setText("Lägg till");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnFile1.setText("X");
        btnFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFile1ActionPerformed(evt);
            }
        });

        btnFile2.setText("X");
        btnFile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFile2ActionPerformed(evt);
            }
        });

        btnFile3.setText("X");
        btnFile3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFile3ActionPerformed(evt);
            }
        });

        btnAddFile2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnAddFile2.setText("+");
        btnAddFile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFile2ActionPerformed(evt);
            }
        });

        btnAddFile3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnAddFile3.setText("+");
        btnAddFile3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFile3ActionPerformed(evt);
            }
        });

        btnAddFile1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnAddFile1.setText("+");
        btnAddFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFile1ActionPerformed(evt);
            }
        });

        txtHeading.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBifogad3)
                                .addGap(18, 18, 18)
                                .addComponent(lblURL3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBifogad2)
                                .addGap(18, 18, 18)
                                .addComponent(lblURL2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBifogad1)
                                .addGap(18, 18, 18)
                                .addComponent(lblURL1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFile3)
                            .addComponent(btnFile2)
                            .addComponent(btnFile1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddFile3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addComponent(btnChange))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAddFile2)
                                    .addComponent(btnAddFile1))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(btnRemove)
                        .addGap(52, 52, 52)
                        .addComponent(btnAdd)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHeading, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove)
                    .addComponent(btnAdd))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblBifogad1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblURL1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnFile1)
                                .addComponent(btnAddFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblBifogad2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblURL2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnFile2, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(btnAddFile2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblBifogad3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblURL3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnFile3)
                            .addComponent(btnAddFile3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(8, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChange)
                        .addContainerGap())))
        );

        btnAdd.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    private void lblURL1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblURL1MouseClicked
        ff.chooseDirectory();
    }//GEN-LAST:event_lblURL1MouseClicked

    private void lblURL2ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblURL2ComponentAdded
        lblBifogad2.setText("Bifogad fil2: ");
    }//GEN-LAST:event_lblURL2ComponentAdded

    private void lblURL3ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblURL3ComponentAdded
        lblBifogad3.setText("Bifogad fil3: ");
    }//GEN-LAST:event_lblURL3ComponentAdded


    private void lblURL1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblURL1MouseEntered
      
    }//GEN-LAST:event_lblURL1MouseEntered

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        
        removePicture(post_id);
        btnRemove.setVisible(false);
        btnAdd.setVisible(true);
        System.out.println(post_id);
        JOptionPane.showMessageDialog(null, "Bilden har tagits bort");
                    
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

                JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            this.bildURL = path;
            paintPicture2();
            insertAttachedPicture();
            btnAdd.setVisible(false);
            btnRemove.setVisible(true);
            
            JOptionPane.showMessageDialog(null, "Bilden har bifogats");
                    

            

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFile1ActionPerformed
        String name = lblURL1.getText();
        
        PostRepository pr = new PostRepository();
        pr.deleteSingleFile(post_id, name);
        
        lblURL1.setText("");
        lblBifogad1.setText("");
        btnFile1.setVisible(false);
        btnAddFile1.setVisible(true);
        JOptionPane.showMessageDialog(null, "Filen har tagits bort");
        
    }//GEN-LAST:event_btnFile1ActionPerformed

    private void btnFile2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFile2ActionPerformed
        String name = lblURL2.getText();
        
        PostRepository pr = new PostRepository();
        pr.deleteSingleFile(post_id, name);
        
        lblURL2.setText("");
        lblBifogad2.setText("");
        btnFile2.setVisible(false);
        btnAddFile2.setVisible(true);
        JOptionPane.showMessageDialog(null, "Filen har tagits bort");
    }//GEN-LAST:event_btnFile2ActionPerformed

    private void btnFile3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFile3ActionPerformed
       String name = lblURL3.getText();
        
        PostRepository pr = new PostRepository();
        pr.deleteSingleFile(post_id, name);
        
        lblURL3.setText("");
        lblBifogad3.setText("");
        btnFile3.setVisible(false);
        btnAddFile3.setVisible(true);
        
        JOptionPane.showMessageDialog(null, "Filen har tagits bort");
    }//GEN-LAST:event_btnFile3ActionPerformed

    private void btnAddFile2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFile2ActionPerformed
        
         {
                    JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        String fileURL = "";
        
        
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            fileURL = path;
            
            btnFile2.setVisible(true);
            btnAddFile2.setVisible(false);
            String fileName1 = fileURL.substring(fileURL.lastIndexOf("\\") + 1);
            lblURL2.setText(fileName1);
            lblBifogad2.setVisible(true);
            lblBifogad2.setText("Bifogad fil 2: ");
            
            
          JOptionPane.showMessageDialog(null, "Filen har bifogats");          

            

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        
        
      
        

     }
       }
    }//GEN-LAST:event_btnAddFile2ActionPerformed

    private void btnAddFile3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFile3ActionPerformed
       {
                    JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        String fileURL = "";
        
        
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            fileURL = path;
            fs.changeFile(fileURL, post_id);
            
            btnFile3.setVisible(true);
            btnAddFile3.setVisible(false);
            String fileName1 = fileURL.substring(fileURL.lastIndexOf("\\") + 1);
            lblURL3.setText(fileName1);
            lblBifogad3.setVisible(true);
            lblBifogad3.setText("Bifogad fil 3: ");
            
            
            
             JOptionPane.showMessageDialog(null, "Filen har bifogats");       

            

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
            
        
        
        

     }
       }
    }//GEN-LAST:event_btnAddFile3ActionPerformed

    private void btnAddFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFile1ActionPerformed
        
       {
                    JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        String fileURL = "";
        
        
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            fileURL = path;
            fs.changeFile(fileURL, post_id);
            btnFile1.setVisible(true);
            btnAddFile1.setVisible(false);
            String fileName1 = fileURL.substring(fileURL.lastIndexOf("\\") + 1);
            lblURL1.setText(fileName1);
            lblBifogad1.setVisible(true);
            lblBifogad1.setText("Bifogad fil 1: ");
            
            
             JOptionPane.showMessageDialog(null, "Filen har bifogats");
                    

            

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        
        
        
        

     }
       }
        
    }//GEN-LAST:event_btnAddFile1ActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        
        PostRepository pr = new PostRepository();
        pr.updatePost(post_id, txtHeading.getText(), txtUserOutput.getText());
        JOptionPane.showMessageDialog(null, "Titel och text har ändrats!");
    }//GEN-LAST:event_btnChangeActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddFile1;
    private javax.swing.JButton btnAddFile2;
    private javax.swing.JButton btnAddFile3;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnFile1;
    private javax.swing.JButton btnFile2;
    private javax.swing.JButton btnFile3;
    private javax.swing.JButton btnRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBifogad1;
    private javax.swing.JLabel lblBifogad2;
    private javax.swing.JLabel lblBifogad3;
    private javax.swing.JLabel lblDisplay;
    private javax.swing.JLabel lblURL1;
    private javax.swing.JLabel lblURL2;
    private javax.swing.JLabel lblURL3;
    private javax.swing.JTextField txtHeading;
    private javax.swing.JTextArea txtUserOutput;
    // End of variables declaration//GEN-END:variables
}
