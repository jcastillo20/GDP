/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.vista;

import net.proteanit.sql.DbUtils;
import com.pe.controlador.UsuarioDAO;
import com.pe.extras.Mensajes;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author JuanCR
 */
public class IFrmListaUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form IFrmListaUsuario
     */
    public IFrmListaUsuario() {
        initComponents();
        
        txtBusqueda.setEditable(false);
        Botones(true, false, false, false, false);

    }

    private void FormatoTabla() {

        tblListaUsu.getColumnModel().getColumn(0).setMaxWidth(0);
        tblListaUsu.getColumnModel().getColumn(0).setMinWidth(0);
        tblListaUsu.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblListaUsu.getColumnModel().getColumn(1).setPreferredWidth(75);
        tblListaUsu.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblListaUsu.getColumnModel().getColumn(3).setPreferredWidth(200);
        tblListaUsu.getColumnModel().getColumn(4).setPreferredWidth(190);
        tblListaUsu.getColumnModel().getColumn(5).setPreferredWidth(80);
        tblListaUsu.getColumnModel().getColumn(6).setPreferredWidth(80);
        tblListaUsu.getColumnModel().getColumn(7).setPreferredWidth(80);
        tblListaUsu.getColumnModel().getColumn(8).setPreferredWidth(200);
        tblListaUsu.getColumnModel().getColumn(9).setPreferredWidth(70);
        tblListaUsu.getColumnModel().getColumn(10).setPreferredWidth(70);
        tblListaUsu.getColumnModel().getColumn(11).setMaxWidth(0);
        tblListaUsu.getColumnModel().getColumn(11).setMinWidth(0);
        tblListaUsu.getColumnModel().getColumn(11).setPreferredWidth(0);
        tblListaUsu.getColumnModel().getColumn(12).setMaxWidth(0);
        tblListaUsu.getColumnModel().getColumn(12).setMinWidth(0);
        tblListaUsu.getColumnModel().getColumn(12).setPreferredWidth(0);
        tblListaUsu.getColumnModel().getColumn(13).setMaxWidth(0);
        tblListaUsu.getColumnModel().getColumn(13).setMinWidth(0);
        tblListaUsu.getColumnModel().getColumn(13).setPreferredWidth(0);
        tblListaUsu.getColumnModel().getColumn(14).setMaxWidth(0);
        tblListaUsu.getColumnModel().getColumn(14).setMinWidth(0);
        tblListaUsu.getColumnModel().getColumn(14).setPreferredWidth(0);
        tblListaUsu.getColumnModel().getColumn(15).setMaxWidth(0);
        tblListaUsu.getColumnModel().getColumn(15).setMinWidth(0);
        tblListaUsu.getColumnModel().getColumn(15).setPreferredWidth(0);
        tblListaUsu.getColumnModel().getColumn(16).setMaxWidth(0);
        tblListaUsu.getColumnModel().getColumn(16).setMinWidth(0);
        tblListaUsu.getColumnModel().getColumn(16).setPreferredWidth(0);
        tblListaUsu.getColumnModel().getColumn(17).setMaxWidth(0);
        tblListaUsu.getColumnModel().getColumn(17).setMinWidth(0);
        tblListaUsu.getColumnModel().getColumn(17).setPreferredWidth(0);
        tblListaUsu.getColumnModel().getColumn(18).setMaxWidth(0);
        tblListaUsu.getColumnModel().getColumn(18).setMinWidth(0);
        tblListaUsu.getColumnModel().getColumn(18).setPreferredWidth(0);
        tblListaUsu.setSelectionBackground(Color.GRAY);
        tblListaUsu.setIntercellSpacing(new Dimension(1, 1));
        tblListaUsu.setRowMargin(1);
        tblListaUsu.setOpaque(false);

    }

    private void ListarUsuario() {
        try {
            tblListaUsu.setModel(DbUtils.resultSetToTableModel(UsuarioDAO.getUsuario()));
            FormatoTabla();
        } catch (Exception e) {
        }
    }

    private void ListarXUsuario() {
        try {
            String usuario = txtBusqueda.getText().trim().toString();
            tblListaUsu.setModel(DbUtils.resultSetToTableModel(UsuarioDAO.getxUsuario(usuario)));
            FormatoTabla();
        } catch (Exception e) {
        }
    }

    private void ListarXDocumento() {
        try {
            String documento = txtBusqueda.getText().trim().toString();
            tblListaUsu.setModel(DbUtils.resultSetToTableModel(UsuarioDAO.getXDocumento(documento)));
            FormatoTabla();
        } catch (Exception e) {
        }
    }

    private void ListarXNombres() {
        try {
            String nombre = txtBusqueda.getText().trim().toString();
            tblListaUsu.setModel(DbUtils.resultSetToTableModel(UsuarioDAO.getXNombres(nombre)));
            FormatoTabla();
        } catch (Exception e) {
        }
    }

    private void ListarXEmpresa() {
        try {
            String empresa = txtBusqueda.getText().trim().toString();
            tblListaUsu.setModel(DbUtils.resultSetToTableModel(UsuarioDAO.getXEmpresa(empresa)));
            FormatoTabla();
        } catch (Exception e) {
        }
    }

    private void Botones(boolean nuevo, boolean editar, boolean eliminar, boolean cambiar_password, boolean perfil) {
        btnNuevo.setVisible(nuevo);
        btnEditar.setVisible(editar);
        btnEliminar.setVisible(eliminar);
        btnCambiarPassword.setVisible(cambiar_password);
        btnPerfil.setVisible(perfil);
    }

    private void pasarDatosFila() {
        int filaselecionada = tblListaUsu.getSelectedRow();
        if (filaselecionada >= 0) {
            int codigo = (int) tblListaUsu.getValueAt(filaselecionada, 0);
            Mensajes.msjMuestra(codigo + "");
            Botones(true, true, true, true, true);
        } else {
            Mensajes.msjMuestra("nada");
            Botones(true, false, false, false, false);
        }

    }

    public void mousePressed(MouseEvent Mouse_evt) {
        JTable table = (JTable) Mouse_evt.getSource();

        Point point = Mouse_evt.getPoint();
        int row = table.rowAtPoint(point);
        if (Mouse_evt.getClickCount() == 1) {
//            Mensajes.msjmuestra(""+tblusu.getValueAt(tblusu.getSelectedRow(), 1));
            pasarDatosFila();
        }

    }

    private void Editar() {

        JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
        FrmDialogEditarUsuario editar = new FrmDialogEditarUsuario(f, true);
        int filaselecionada = tblListaUsu.getSelectedRow();
        Date date = null;

        editar.txtNDocumento.setText((String) tblListaUsu.getValueAt(filaselecionada, 1));
        editar.txtNombres.setText((String) tblListaUsu.getValueAt(filaselecionada, 2));
        editar.txtEmpresa.setText((String) tblListaUsu.getValueAt(filaselecionada, 3));
        editar.txtCargo.setText((String) tblListaUsu.getValueAt(filaselecionada, 4));
        if (tblListaUsu.getValueAt(filaselecionada, 6) != "") {

            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse((String) tblListaUsu.getValueAt(filaselecionada, 6));
            } catch (ParseException ex) {
                Logger.getLogger(IFrmListaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            editar.DateFechaNaci.setDate(null);
        } else {
            
editar.DateFechaNaci.setDate(date);
        }
        editar.txtusername.setText((String) tblListaUsu.getValueAt(filaselecionada, 7));
        editar.txtEmail.setText((String) tblListaUsu.getValueAt(filaselecionada, 8));
        editar.txtFijo.setText((String) tblListaUsu.getValueAt(filaselecionada, 9));
        editar.txtCelular.setText((String) tblListaUsu.getValueAt(filaselecionada, 10));
        editar.CargarTipoDocumentoEditar(Integer.parseInt((String) tblListaUsu.getValueAt(filaselecionada, 11)));
        editar.CargarTipoGeneroEditar(Integer.parseInt((String) tblListaUsu.getValueAt(filaselecionada, 12)));
        editar.CargarTipoFlagEditar(Integer.parseInt((String) tblListaUsu.getValueAt(filaselecionada, 13)));
        editar.CargarTipoUsuarioEditar(Integer.parseInt((String) tblListaUsu.getValueAt(filaselecionada, 14)));
        if (tblListaUsu.getValueAt(filaselecionada, 16) != null) {
            editar.chboxModerador.setSelected(true);
        } else {
            editar.chboxModerador.setSelected(false);
        }
        editar.CargarTipoPerfilEditar(((Integer) tblListaUsu.getValueAt(filaselecionada,17)));
        editar.CargarTipoEstadoEditar(Integer.parseInt((String) tblListaUsu.getValueAt(filaselecionada, 18)));
        editar.show();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JScrollPane = new javax.swing.JScrollPane();
        tblListaUsu = new JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        cboBusqueda = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnCambiarPassword = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnPerfil = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Usuarios");
        setToolTipText("");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(0, 0));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameIconified(evt);
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        JScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        JScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane.setAutoscrolls(true);
        JScrollPane.setFocusCycleRoot(true);
        JScrollPane.setHorizontalScrollBar(null);

        tblListaUsu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblListaUsu.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblListaUsu.getTableHeader().setReorderingAllowed(false);
        tblListaUsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaUsuMouseClicked(evt);
            }
        });
        JScrollPane.setViewportView(tblListaUsu);

        jSeparator1.setAutoscrolls(true);

        jLabel1.setText("Busqueda :");

        cboBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Selecione-", "Usuario", "Num. Documento", "Nombres y Apellidos", "Empresa" }));
        cboBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBusquedaActionPerformed(evt);
            }
        });

        txtBusqueda.setToolTipText("");
        txtBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBusquedaFocusGained(evt);
            }
        });
        txtBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBusquedaMouseClicked(evt);
            }
        });
        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnCambiarPassword.setText("Cambiar Passwod");

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        btnPerfil.setText("Perfil");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPane)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnCambiarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(333, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        ListarUsuario();

    }//GEN-LAST:event_formInternalFrameOpened

    private void cboBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBusquedaActionPerformed
        if (cboBusqueda.getSelectedIndex() == 0) {
            txtBusqueda.setEditable(false);
        } else if (cboBusqueda.getSelectedIndex() == 1) {
            txtBusqueda.setEditable(true);
            Mensajes.Hint(txtBusqueda, "Usuario", txtBusqueda.getText().trim().length());
        } else if (cboBusqueda.getSelectedIndex() == 2) {
            Mensajes.Hint(txtBusqueda, "Numero Documento", txtBusqueda.getText().trim().length());
            txtBusqueda.setEditable(true);
        } else if (cboBusqueda.getSelectedIndex() == 3) {
            Mensajes.Hint(txtBusqueda, "Nombres y Apellidos", txtBusqueda.getText().trim().length());
            txtBusqueda.setEditable(true);
        } else {
            Mensajes.Hint(txtBusqueda, "Empresa", txtBusqueda.getText().trim().length());
            txtBusqueda.setEditable(true);
        }
    }//GEN-LAST:event_cboBusquedaActionPerformed

    private void txtBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBusquedaMouseClicked
        Mensajes.sinHit(txtBusqueda);
    }//GEN-LAST:event_txtBusquedaMouseClicked

    private void txtBusquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBusquedaFocusGained
        Mensajes.sinHit(txtBusqueda);
    }//GEN-LAST:event_txtBusquedaFocusGained

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        if (cboBusqueda.getSelectedIndex() == 0) {
            ListarUsuario();
            Mensajes.msjMuestra("SEleccionar al menos un filtro");
        } else if (cboBusqueda.getSelectedIndex() == 1) {
            ListarXUsuario();
        } else if (cboBusqueda.getSelectedIndex() == 2) {
            ListarXDocumento();
        } else if (cboBusqueda.getSelectedIndex() == 3) {
            ListarXNombres();
        } else {
            ListarXEmpresa();
        }
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void tblListaUsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaUsuMouseClicked
        mousePressed(evt);
    }//GEN-LAST:event_tblListaUsuMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.Editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);

        FrmDialogEditarUsuario dialog = new FrmDialogEditarUsuario(f, true);
        dialog.show();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void formInternalFrameIconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameIconified
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameIconified


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JButton btnCambiarPassword;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JComboBox<String> cboBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblListaUsu;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
