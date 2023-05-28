package view;

import java.awt.BorderLayout;

public class NewJFrame extends javax.swing.JFrame {
    /* Create variables*/
    private QuanLyThucDonJPanel quanLyThucDon = new QuanLyThucDonJPanel();
    /*Constructor*/
    public NewJFrame() {
        initComponents();
        this.add(quanLyThucDon, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 600));

        kGradientPanel1.setLayout(new java.awt.GridLayout(6, 1));

        jButton1.setText("jhbhjkdsafjk");
        kGradientPanel1.add(jButton1);

        jButton2.setText("jButton2");
        kGradientPanel1.add(jButton2);

        getContentPane().add(kGradientPanel1, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
