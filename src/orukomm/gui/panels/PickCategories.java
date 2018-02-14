/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.gui.panels;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import orukomm.data.entities.Category;
import orukomm.data.entities.User;
import orukomm.data.repositories.CategoryRepository;
import orukomm.gui.MainWindow;
import orukomm.gui.panels.FormalFeed;
import orukomm.gui.panels.FormalFeed;
import orukomm.gui.panels.FormalFeed;

/**
 *
 * @author admin
 */
public class PickCategories extends javax.swing.JPanel {

 
    private MainWindow parentFrame;
    private CategoryRepository cr;
    private ArrayList<Category> categoryList;
    private ArrayList<Category> disabledFromStart;
    private ArrayList<Category> toRemove;
    private ArrayList<Category> toAdd;
    
    
    private DefaultListModel<Category> listMdlAvailableCategories;
    private DefaultListModel<Category> listMdlChoosenCategories;
    
    private Category choosenCategory;
    
    public PickCategories(MainWindow parentFrame) {
        initComponents();
        
        this.parentFrame = parentFrame;
        cr = new CategoryRepository();        
               
        listMdlAvailableCategories = new DefaultListModel<>();
        listAvailableCategories.setModel(listMdlAvailableCategories); 
        listMdlChoosenCategories = new DefaultListModel<>();
        listChoosenCategories.setModel(listMdlChoosenCategories);
        
        disabledFromStart = new ArrayList<>();
        toRemove = new ArrayList<>();
        toAdd = new ArrayList<>();

        refreshAvailableCategoriesList();
        refreshDisabledCategoriesList();
    }
    
    private void refreshAvailableCategoriesList(){
         listMdlAvailableCategories.removeAllElements();
        categoryList = cr.getAllNotDisabled(parentFrame.loggedInUser.getId());

        for (Category cats : categoryList) {
            listMdlAvailableCategories.addElement(cats);
            
            }        
    }
    
    private void refreshDisabledCategoriesList(){
        listMdlChoosenCategories.removeAllElements();
        disabledFromStart = cr.getAllDisabled(parentFrame.loggedInUser.getId());
        
        for(Category cats : disabledFromStart){
            listMdlChoosenCategories.addElement(cats);
        }
    }
              
     private void removeFromDB(){
         
         for (int i = 0; i < toAdd.size(); i ++){
             Category cat = toAdd.get(i);
                 cr.removeUserDisabledCategory(cat.getId(), parentFrame.loggedInUser.getId());
         }
     }
     
     private void insertToDB(){

         for(int i = 0; i < toRemove.size(); i++){
             Category cat = toRemove.get(i);
             cr.addUserDisabledCategory(cat.getId(), parentFrame.loggedInUser.getId());    
     }
     }
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAddCategory = new javax.swing.JButton();
        btnRemoveCategory = new javax.swing.JButton();
        btnSaveCategories = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listAvailableCategories = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listChoosenCategories = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        btnAddCategory.setText(">>>");
        btnAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoryActionPerformed(evt);
            }
        });

        btnRemoveCategory.setText("<<<");
        btnRemoveCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveCategoryActionPerformed(evt);
            }
        });

        btnSaveCategories.setText("Spara");
        btnSaveCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveCategoriesActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(listAvailableCategories);

        jScrollPane4.setViewportView(listChoosenCategories);

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel1.setText("Bloggkategorier du valt att se");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setText("Bloggkategorier du valt att inte se");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRemoveCategory)
                                    .addComponent(btnAddCategory, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(btnSaveCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(btnSaveCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btnAddCategory)
                        .addGap(79, 79, 79)
                        .addComponent(btnRemoveCategory)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCategoriesActionPerformed
       insertToDB();
       removeFromDB();

       JOptionPane.showMessageDialog(null, "Inställningar för kategorier sparade.");
    }//GEN-LAST:event_btnSaveCategoriesActionPerformed

    private void btnAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCategoryActionPerformed
          choosenCategory = listAvailableCategories.getSelectedValue();       
        if (choosenCategory != null) {
            toRemove.add(choosenCategory);

             listMdlChoosenCategories.addElement(choosenCategory);
             listMdlAvailableCategories.removeElement(choosenCategory);
                }
    }//GEN-LAST:event_btnAddCategoryActionPerformed

    private void btnRemoveCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveCategoryActionPerformed
        choosenCategory = listChoosenCategories.getSelectedValue();
        if (choosenCategory != null) {
            toAdd.add(choosenCategory);
            
            listMdlAvailableCategories.addElement(choosenCategory);
            listMdlChoosenCategories.removeElement(choosenCategory);
        }
    }//GEN-LAST:event_btnRemoveCategoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCategory;
    private javax.swing.JButton btnRemoveCategory;
    private javax.swing.JButton btnSaveCategories;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<Category> listAvailableCategories;
    private javax.swing.JList<Category> listChoosenCategories;
    // End of variables declaration//GEN-END:variables
}
