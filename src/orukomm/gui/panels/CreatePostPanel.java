package orukomm.gui.panels;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import orukomm.data.FileStorage;
import orukomm.data.entities.Category;
import orukomm.data.entities.User;
import orukomm.data.repositories.CategoryRepository;
import orukomm.gui.MainWindow;
import orukomm.logic.security.Validation;

public class CreatePostPanel extends javax.swing.JPanel {

    private MainWindow parentFrame;
    private ArrayList<Category> categories;
    private CategoryRepository categoryRepo;
    private DefaultListModel<Category> lstMdlCategories;
    private FileStorage fs;
    private String date;

    private static String bildURL;
    private static String fileURL;
    private static String fileURL2;
    private static String fileURL3;
    private static String textPost;
    private static String textHeading;

    /**
     * Creates new form CreatePost
     */
    public CreatePostPanel(MainWindow parentFrame) {

        initComponents();
        this.parentFrame = parentFrame;
        lstMdlCategories = new DefaultListModel<>();
        lstCategory.setModel(lstMdlCategories);
        fs = new FileStorage();
        categoryRepo = new CategoryRepository();
        getDate();
        refreshCategoryList();
        lstCategory.setSelectedIndex(0);

        hideButtons();
        lstCategory.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Category selectedCategory = lstCategory.getSelectedValue();
                if (selectedCategory != null) {

                }
            }
        });
    }

    public void clearFields() {
        TxtHeading.setText(null);
        TxtInput.setText(null);
        hideButtons();

        lblURL1.setText("");
        lblBifogad.setText(null);
        fileURL = "";
        btnClearURL1.setVisible(false);

        lblURL2.setText("");
        lblBifogad2.setText(null);
        fileURL2 = "";
        btnClearURL2.setVisible(false);

        lblURL3.setText("");
        lblBifogad3.setText(null);
        fileURL3 = "";
        btnClearURL3.setVisible(false);

        lblImageURL.setText("");
        lblBifogadBild.setText(null);
        bildURL = "";
        btnClearImage.setVisible(false);

    }

    public void refreshCategoryList() {
        lstMdlCategories.removeAllElements();
        categories = categoryRepo.getAll();

        for (Category cats : categories) {
            lstMdlCategories.addElement(cats);
        }
    }

    private void hideButtons() {
        btnClearImage.setVisible(false);
        btnClearURL1.setVisible(false);
        btnClearURL2.setVisible(false);
        btnClearURL3.setVisible(false);
    }

    public CreatePostPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getDate() {

        LocalDate localDate = LocalDate.now();
        date = localDate.toString();

    }

    private void setTextPost() {
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

    /**
     * private JFrame buildFrame() throws IOException { JFrame frame = new CreatedPost(textPost, textHeading);
     * frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); frame.setSize(605, 720); frame.setVisible(true);
     * return frame; }
     */
    public void insertAttachedFiles() {

        if (!lblURL1.getText().isEmpty() && !lblURL2.getText().isEmpty() && !lblURL3.getText().isEmpty()) {
            fs.insertFile(fileURL);
            fs.insertFile(fileURL2);
            fs.insertFile(fileURL3);
        } else if (!lblURL1.getText().isEmpty() && !lblURL2.getText().isEmpty()) {
            fs.insertFile(fileURL);
            fs.insertFile(fileURL2);
        } else if (!lblURL1.getText().isEmpty()) {
            fs.insertFile(fileURL);
        } else {

        }
    }

    public void insertAttachedPicture() {

        if (!lblImageURL.getText().isEmpty()) {
            fs.insertFile(bildURL);

        }
    }

    public void insertAttachedText() {
        User user = new User();
        int id = user.getId();

        if (!TxtInput.getText().isEmpty()) {

        } else {

        }
    }

    public boolean buttonSelected() {
        boolean selected = false;
        if (rbtn1.isSelected() || rbtn2.isSelected()) {
            selected = true;
        } else {
            JOptionPane.showMessageDialog(parentFrame, "Vänligen välj flöde.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);
        }

        return selected;
    }

    public int getButton() {
        if (rbtn1.isSelected()) {
            return 0;
        } else {
            return 1;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblURL3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnClearURL3 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnClearURL2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtInput = new javax.swing.JTextArea();
        btnClearImage = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblBifogad = new javax.swing.JLabel();
        lblURL2 = new javax.swing.JLabel();
        lblBifogad2 = new javax.swing.JLabel();
        lblBifogad3 = new javax.swing.JLabel();
        btnClearURL1 = new javax.swing.JButton();
        TxtHeading = new javax.swing.JTextField();
        lblURL1 = new javax.swing.JLabel();
        lblImageURL = new javax.swing.JLabel();
        lblBifogadBild = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstCategory = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        rbtn1 = new javax.swing.JRadioButton();
        rbtn2 = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(1024, 768));
        setLayout(new java.awt.CardLayout());

        jButton2.setText("Bifoga bild");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnClearURL3.setText("X");
        btnClearURL3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearURL3ActionPerformed(evt);
            }
        });

        jButton3.setText("Bifoga fil");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnClearURL2.setText("X");
        btnClearURL2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearURL2ActionPerformed(evt);
            }
        });

        TxtInput.setColumns(20);
        TxtInput.setRows(5);
        jScrollPane1.setViewportView(TxtInput);

        btnClearImage.setText("X");
        btnClearImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearImageActionPerformed(evt);
            }
        });

        jButton1.setText("Skapa inlägg");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnClearURL1.setText("X");
        btnClearURL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearURL1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Rubrik");

        lstCategory.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lstCategoryComponentAdded(evt);
            }
        });
        jScrollPane2.setViewportView(lstCategory);

        jLabel1.setText("Kategori");

        buttonGroup1.add(rbtn1);
        rbtn1.setText("Formella flödet");

        buttonGroup1.add(rbtn2);
        rbtn2.setText("Informella flödet");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton1)
                        .addGap(45, 45, 45)
                        .addComponent(rbtn1)
                        .addGap(34, 34, 34)
                        .addComponent(rbtn2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnClearURL1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblURL1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBifogad))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBifogad2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBifogad3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnClearURL3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblURL3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblBifogadBild))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnClearImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImageURL, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnClearURL2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblURL2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TxtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(178, 178, 178)
                                .addComponent(jButton3)
                                .addGap(9, 9, 9)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap(382, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(TxtHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jLabel1)))
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(rbtn1)
                    .addComponent(rbtn2))
                .addGap(32, 32, 32)
                .addComponent(lblBifogad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblURL1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearURL1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBifogad2)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblURL2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearURL2))
                .addGap(8, 8, 8)
                .addComponent(lblBifogad3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblURL3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearURL3))
                .addGap(11, 11, 11)
                .addComponent(lblBifogadBild)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImageURL, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearImage))
                .addContainerGap())
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if (buttonSelected()) {
            setTextPost();

            setHeadingPost();
            fs.insertPost(parentFrame.loggedInUser.getId(),
                    textHeading,
                    textPost,
                    lstCategory.getSelectedValue().getId(),
                    getButton(),
                    date);

            insertAttachedPicture();
            insertAttachedFiles();
          if (lblURL1.getText().isEmpty() && lblURL2.getText().isEmpty() && lblURL3.getText().isEmpty() && lblImageURL.getText().isEmpty()) {
              JOptionPane.showMessageDialog(null, "Inlägg skapat!");
              
            clearFields();
          }
                       
        }
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

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

            if (lblURL1.getText().isEmpty()) {
                lblURL1.setText(path);
                lblBifogad.setText("Bifogad fil 1:");
                fileURL = path;
                btnClearURL1.setVisible(true);

            } else if (lblURL2.getText().isEmpty()) {
                lblURL2.setText(path);
                lblBifogad2.setText("Bifogad fil 2:");
                fileURL2 = path;
                btnClearURL2.setVisible(true);

            } else if (lblURL3.getText().isEmpty()) {
                lblURL3.setText(path);
                lblBifogad3.setText("Bifogad fil 3:");
                fileURL3 = path;
                btnClearURL3.setVisible(true);
            }

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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

            lblBifogadBild.setText("Bifogad bild:");
            btnClearImage.setVisible(true);
            lblImageURL.setText(path);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnClearURL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearURL1ActionPerformed
        lblURL1.setText("");
        lblBifogad.setText(null);
        fileURL = "";
        btnClearURL1.setVisible(false);
    }//GEN-LAST:event_btnClearURL1ActionPerformed

    private void btnClearURL2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearURL2ActionPerformed
        lblURL2.setText("");
        lblBifogad2.setText(null);
        fileURL2 = "";
        btnClearURL2.setVisible(false);
    }//GEN-LAST:event_btnClearURL2ActionPerformed

    private void btnClearURL3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearURL3ActionPerformed
        lblURL3.setText("");
        lblBifogad3.setText(null);
        fileURL3 = "";
        btnClearURL3.setVisible(false);
    }//GEN-LAST:event_btnClearURL3ActionPerformed

    private void btnClearImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearImageActionPerformed
        lblImageURL.setText("");
        lblBifogadBild.setText(null);
        bildURL = "";
        btnClearImage.setVisible(false);
    }//GEN-LAST:event_btnClearImageActionPerformed

    private void lstCategoryComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lstCategoryComponentAdded
//            refreshCategoryList(); 
    }//GEN-LAST:event_lstCategoryComponentAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtHeading;
    private javax.swing.JTextArea TxtInput;
    private javax.swing.JButton btnClearImage;
    private javax.swing.JButton btnClearURL1;
    private javax.swing.JButton btnClearURL2;
    private javax.swing.JButton btnClearURL3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JList<Category> lstCategory;
    private javax.swing.JRadioButton rbtn1;
    private javax.swing.JRadioButton rbtn2;
    // End of variables declaration//GEN-END:variables
}
