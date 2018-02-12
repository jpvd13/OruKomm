package orukomm.gui.panels;

import java.util.ArrayList;
import orukomm.data.entities.User;
import orukomm.gui.MainWindow;
import orukomm.logic.scheduler.jobs.Email;

/*
 * First panel to display after successful login.
 */
public class Index extends javax.swing.JPanel {

    public Index(MainWindow parentFrame) {
        initComponents();
    }

    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                pnlIndexContainer = new javax.swing.JPanel();
                lblWelcome = new javax.swing.JLabel();

                setLayout(new java.awt.GridBagLayout());

                pnlIndexContainer.setPreferredSize(new java.awt.Dimension(500, 350));

                javax.swing.GroupLayout pnlIndexContainerLayout = new javax.swing.GroupLayout(pnlIndexContainer);
                pnlIndexContainer.setLayout(pnlIndexContainerLayout);
                pnlIndexContainerLayout.setHorizontalGroup(
                        pnlIndexContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIndexContainerLayout.createSequentialGroup()
                                .addContainerGap(103, Short.MAX_VALUE)
                                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80))
                );
                pnlIndexContainerLayout.setVerticalGroup(
                        pnlIndexContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlIndexContainerLayout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(lblWelcome)
                                .addContainerGap(258, Short.MAX_VALUE))
                );

                add(pnlIndexContainer, new java.awt.GridBagConstraints());
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel lblWelcome;
        private javax.swing.JPanel pnlIndexContainer;
        // End of variables declaration//GEN-END:variables
}
