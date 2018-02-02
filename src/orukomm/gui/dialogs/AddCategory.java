package orukomm.gui.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import orukomm.data.entities.Category;
import orukomm.data.repositories.CategoryRepository;
import orukomm.gui.panels.CreatePostPanel;
import orukomm.logic.Validation;

public class AddCategory extends javax.swing.JDialog {

    private CategoryRepository categoryRepo;
    private Category category;

    public AddCategory(java.awt.Frame parentFrame, boolean modal) {
        super(parentFrame, modal);
        categoryRepo = new CategoryRepository();
        category = new Category();
        initComponents();
        setLocationRelativeTo(null);

        // Submit event.
        btnAddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Validation.isEmptyOrNull(txtfCategory.getText())) {
                    JOptionPane.showMessageDialog(parentFrame, "Fältet får inte vara tomt.", "Valideringsfel", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                // Create category object.
                category.setCategory(txtfCategory.getText());
                categoryRepo.add(category);
                JOptionPane.showMessageDialog(parentFrame, "Kategorin lades till.", "Kategori tillagd", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        txtfCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddCategory.doClick();
            }
        }); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAddCategoryHeading = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        txtfCategory = new javax.swing.JTextField();
        btnAddCategory = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblAddCategoryHeading.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblAddCategoryHeading.setText("Lägg til ny kategori");

        lblCategory.setText("Kategori");

        btnAddCategory.setText("Lägg till");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddCategoryHeading)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnAddCategory)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblCategory)
                            .addGap(18, 18, 18)
                            .addComponent(txtfCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddCategoryHeading)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategory)
                    .addComponent(txtfCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAddCategory)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AddCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddCategory dialog = new AddCategory(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCategory;
    private javax.swing.JLabel lblAddCategoryHeading;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JTextField txtfCategory;
    // End of variables declaration//GEN-END:variables
}
