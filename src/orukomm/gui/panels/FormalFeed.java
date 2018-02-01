/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orukomm.gui.panels;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import orukomm.data.entities.Post;
import orukomm.data.repositories.PostRepository;

/**
 *
 * @author Ludvig
 */
public class FormalFeed extends javax.swing.JPanel {

    /**
     * Creates new form FormalFeed
     */
    public FormalFeed() {
        initComponents();

    }

    public void fillTable() {
        PostRepository pr = new PostRepository();

        ArrayList<Post> postList = pr.fillList();

        DefaultTableModel model = (DefaultTableModel) tblFormalFeed.getModel();  //Typecastar JTablemodellen till en DefaultTableModel
        Object[] row = new Object[3];    // Använder Object klassen så att Arrayn kan ta in vilka object som helst
        for (int i = 0; i < postList.size(); i++) {
            row[0] = postList.get(i).getTitle();
            row[1] = postList.get(i).getUsername();
            row[2] = postList.get(i).getDate();
            model.addRow(row);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormalFeed = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

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
        tblFormalFeed.setColumnSelectionAllowed(true);
        tblFormalFeed.getTableHeader().setReorderingAllowed(false);
        tblFormalFeed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFormalFeedMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFormalFeed);
        tblFormalFeed.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblFormalFeed.getColumnModel().getColumnCount() > 0) {
            tblFormalFeed.getColumnModel().getColumn(0).setResizable(false);
            tblFormalFeed.getColumnModel().getColumn(1).setResizable(false);
            tblFormalFeed.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblFormalFeedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFormalFeedMouseClicked
        //ändra
    }//GEN-LAST:event_tblFormalFeedMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblFormalFeed;
    // End of variables declaration//GEN-END:variables
}
