/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.vista;

import com.pe.controlador.UsuarioDAO;
import com.pe.extras.Mensajes;
import com.pe.modelo.usuario;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author JuanCR
 */
public class FrmLogin extends javax.swing.JFrame {

    /**
     * Creates new form FrmLogin
     */
    String USUARIO;
    FrmPrincipal op = new FrmPrincipal();

    public FrmLogin() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/image/ipartner.png")).getImage());
        setLocationRelativeTo(null);
        setResizable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtusu = new javax.swing.JTextField();
        txtpwd = new javax.swing.JPasswordField();
        btnlog = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(318, 153));
        setMinimumSize(new java.awt.Dimension(318, 153));

        jLabel1.setText("Usuario:");

        jLabel2.setText("Password:");

        txtpwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpwdActionPerformed(evt);
            }
        });

        btnlog.setText("Login");
        btnlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtpwd, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(txtusu)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnlog, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtpwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnlog)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogActionPerformed
       Ingresar();

    }//GEN-LAST:event_btnlogActionPerformed

    private void txtpwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpwdActionPerformed
        // TODO add your handling code here:
        Ingresar();
    }//GEN-LAST:event_txtpwdActionPerformed

    public void Ingresar(){
         String textoSinEncriptar = txtpwd.getText();
        try {
            String password = DigestUtils.md5Hex(textoSinEncriptar);
            usuario ou = new usuario();
            ou.setUsername(txtusu.getText());
            ou.setPassword(password);
            if (UsuarioDAO.inLogin(ou) > 0) {
                Mensajes.msjMuestra("Todo bien");
                USUARIO = txtusu.getText();
                obTener();
                op.show();
                op.setExtendedState(MAXIMIZED_BOTH);
                this.dispose();
            } else {
                Mensajes.msjMuestra("Horrorrrr");
            }
        } catch (Exception e) {
            Mensajes.msjMuestra(e.getMessage());
        }
    }
    public void obTener() {

        String datos_usuario = UsuarioDAO.BuscarID(USUARIO);
        String[] datos = datos_usuario.split(";");
        String id = datos[0];
        Mensajes.GuardarIDUsuario(id);
        String nombres = datos[1];
        String empresa = datos[2];
        String cargo = datos[3];
        op.lblIdUsuario.setText(id);
        op.lblNombre_usuario.setText(nombres + "; " + empresa + "; " + cargo);

    }

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
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtpwd;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables
}
