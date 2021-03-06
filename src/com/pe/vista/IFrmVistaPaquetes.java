/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.vista;

import com.pe.controlador.Detalle_CatalogoDAO;
import com.pe.controlador.Detalle_PedidoDAO;
import com.pe.controlador.OrganizacionDAO;
import com.pe.controlador.UsuarioDAO;
import com.pe.extras.Mensajes;
import com.pe.extras.MyEditor;
import com.pe.modelo.Render;
import com.pe.modelo.detalle_pedido;
import com.pe.modelo.organizacion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author JUAN
 */
public class IFrmVistaPaquetes extends javax.swing.JInternalFrame {

    MyEditor myEdit = new MyEditor();
    int clic_tabla = 0;
    Font font = new Font("Agency FB", Font.BOLD, 14);

    /**
     * Creates new form IFrmVistaPaquetes
     */
    OrganizacionDAO daoOrga = new OrganizacionDAO();

    public IFrmVistaPaquetes() {
        initComponents();
        HabilitarBusqueda();
        //Cargar_Combo_Dependente();
        txtbusqueda.setVisible(false);
        Mensajes.centerComponent(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Parametros = new javax.swing.ButtonGroup();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        btnActualizar = new javax.swing.JButton();
        lblBusqueda = new javax.swing.JLabel();
        txtbusqueda = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInfoPaquetes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        rbtnConPaquete = new javax.swing.JRadioButton();
        rbtnSinPaquete = new javax.swing.JRadioButton();
        cboFiltroPaquete = new javax.swing.JComboBox<>();
        cboRango = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("INFO - PAQUETES");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        lblBusqueda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBusqueda.setText("Busqueda :");

        txtbusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtbusquedaFocusGained(evt);
            }
        });
        txtbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusquedaActionPerformed(evt);
            }
        });

        tblInfoPaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblInfoPaquetes.setFocusable(false);
        tblInfoPaquetes.setRequestFocusEnabled(false);
        tblInfoPaquetes.getTableHeader().setReorderingAllowed(false);
        tblInfoPaquetes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInfoPaquetesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblInfoPaquetes);

        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("PARAMETROS");

        Parametros.add(rbtnConPaquete);
        rbtnConPaquete.setText("Con Paquete");
        rbtnConPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnConPaqueteActionPerformed(evt);
            }
        });

        Parametros.add(rbtnSinPaquete);
        rbtnSinPaquete.setText("Sin Paquete");
        rbtnSinPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnSinPaqueteActionPerformed(evt);
            }
        });

        cboFiltroPaquete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EMPRESA", "DISPONIBLES", "CONTRATADAS", "ESTADO", "SERVICIO" }));
        cboFiltroPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFiltroPaqueteActionPerformed(evt);
            }
        });

        cboRango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRangoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(rbtnConPaquete)
                                .addGap(97, 97, 97)
                                .addComponent(rbtnSinPaquete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnActualizar)
                                .addGap(13, 13, 13))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBusqueda)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cboFiltroPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtnConPaquete)
                            .addComponent(rbtnSinPaquete))
                        .addGap(10, 10, 10)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBusqueda)
                    .addComponent(cboFiltroPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void HabilitarBusqueda() {
        if (rbtnConPaquete.isSelected() == true) {
            lblBusqueda.setVisible(true);
            txtbusqueda.setVisible(true);
            btnActualizar.setVisible(true);
            cboFiltroPaquete.setVisible(true);
            Cargar_Combo_Dependente();
        } else if (rbtnSinPaquete.isSelected() == true) {
            lblBusqueda.setVisible(true);
            txtbusqueda.setVisible(true);
            btnActualizar.setVisible(true);
            cboFiltroPaquete.setVisible(false);
            cboRango.setVisible(false);
        } else {
            lblBusqueda.setVisible(false);
            txtbusqueda.setVisible(false);
            btnActualizar.setVisible(false);
            cboFiltroPaquete.setVisible(false);
            cboRango.setVisible(false);
        }
    }


    private void txtbusquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbusquedaFocusGained
        Mensajes.sinHit(txtbusqueda);
    }//GEN-LAST:event_txtbusquedaFocusGained

    private void txtbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusquedaActionPerformed
        if (rbtnConPaquete.isSelected() == true) {
            if (cboFiltroPaquete.getSelectedIndex() == 0) {
                visualizar_XEmpresa_ConPaquete();
            }
        } else {
            visualizar_XEmpresa_SinPaquete();
        }


    }//GEN-LAST:event_txtbusquedaActionPerformed

    private void tblInfoPaquetesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInfoPaquetesMouseClicked
        if (rbtnConPaquete.isSelected() == true) {
            clic_tabla = this.tblInfoPaquetes.rowAtPoint(evt.getPoint());
            int filaseleccionada = tblInfoPaquetes.getSelectedRow();
            int column = tblInfoPaquetes.getColumnModel().getColumnIndexAtX(evt.getX());
            int row = evt.getY() / tblInfoPaquetes.getRowHeight();
            String contratadas = "" + tblInfoPaquetes.getValueAt(filaseleccionada, 3);
            String disponibles = "" + tblInfoPaquetes.getValueAt(filaseleccionada, 4);

//            if (row < tblInfoPaquetes.getRowCount() && row >= 0 && column < tblInfoPaquetes.getColumnCount() && column >= 0) {
            Object value = tblInfoPaquetes.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (contratadas.equals(disponibles)) {
                    Mensajes.msjMuestra("No hay Detalle del Paquete Seleccionado");
                } else {
                    if (boton.getName().equals("i")) {
                        System.out.println("Click en el boton modificar");
                        ObtenerID();
                    }
                }
//                }

            }
        } else if (rbtnSinPaquete.isSelected() == true) {
            clic_tabla = this.tblInfoPaquetes.rowAtPoint(evt.getPoint());
            int column = tblInfoPaquetes.getColumnModel().getColumnIndexAtX(evt.getX());
            int row = evt.getY() / tblInfoPaquetes.getRowHeight();

//            if (row < tblInfoPaquetes.getRowCount() && row >= 0 && column < tblInfoPaquetes.getColumnCount() && column >= 0) {
            Object value = tblInfoPaquetes.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("i")) {
                    System.out.println("Click en el boton modificar");
                    ObtenerID();
                }

//                }
            }
        }
    }//GEN-LAST:event_tblInfoPaquetesMouseClicked

    private void rbtnConPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnConPaqueteActionPerformed
        // TODO add your handling code here:        

        visualizar_Paquetes();
        HabilitarBusqueda();

    }//GEN-LAST:event_rbtnConPaqueteActionPerformed

    private void rbtnSinPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnSinPaqueteActionPerformed
        // TODO add your handling code here:
        visualizar_SinPaquete();
        HabilitarBusqueda();
    }//GEN-LAST:event_rbtnSinPaqueteActionPerformed

    private void cboFiltroPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFiltroPaqueteActionPerformed
        // TODO add your handling code here:
        this.Cargar_Combo_Dependente();
    }//GEN-LAST:event_cboFiltroPaqueteActionPerformed

    private void cboRangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRangoActionPerformed
        // TODO add your handling code here:
        if (cboFiltroPaquete.getSelectedIndex() == 1) {
            visualizar_XDisponibles_ConPaquete();
        } else if (cboFiltroPaquete.getSelectedIndex() == 2) {
            visualizar_XContratadas_ConPaquete();
        }else if (cboFiltroPaquete.getSelectedIndex() == 3) {
            visualizar_XEstado_ConPaquete();
        } else if (cboFiltroPaquete.getSelectedIndex() == 4) {
            visualizar_XServicio_ConPaquete();
        }
    }//GEN-LAST:event_cboRangoActionPerformed

    private void ObtenerID() {
        if (rbtnConPaquete.isSelected() == true) {
            JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
            FrmDialogDetallePaquete detalle = new FrmDialogDetallePaquete(f, true);
            int filaseleccionada = tblInfoPaquetes.getSelectedRow();
            String id = (String) tblInfoPaquetes.getValueAt(filaseleccionada, 0);
            String empresa = (String) tblInfoPaquetes.getValueAt(filaseleccionada, 1);
            String servicio = (String) tblInfoPaquetes.getValueAt(filaseleccionada, 7);
            System.out.println(id + "mando el id");
            detalle.Recibedatos(id, empresa, servicio);
            detalle.show();
        } else if (rbtnSinPaquete.isSelected() == true) {
            JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
            FrmDialogFiltroPaquete detalle = new FrmDialogFiltroPaquete(f, true);
            int filaseleccionada = tblInfoPaquetes.getSelectedRow();
            String id = (String) tblInfoPaquetes.getValueAt(filaseleccionada, 0);
            String empresa = (String) tblInfoPaquetes.getValueAt(filaseleccionada, 2);
            System.out.println(id + "mando el id");
            detalle.RecibeID(id, empresa);
            detalle.show();
            detalle.setUndecorated(true);
        }
    }

    private void Actualizar() {
        if (rbtnConPaquete.isSelected() == true) {
            visualizar_Paquetes();
        } else if (rbtnSinPaquete.isSelected() == true) {
            visualizar_SinPaquete();
        }

    }

    private void Formato_conPaquete() {
        tblInfoPaquetes.getColumnModel().getColumn(0).setMaxWidth(0);
        tblInfoPaquetes.getColumnModel().getColumn(0).setMinWidth(0);
        tblInfoPaquetes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblInfoPaquetes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblInfoPaquetes.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblInfoPaquetes.getColumnModel().getColumn(2).setPreferredWidth(95);
        tblInfoPaquetes.getColumnModel().getColumn(3).setPreferredWidth(110);
        tblInfoPaquetes.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblInfoPaquetes.getColumnModel().getColumn(5).setPreferredWidth(80);
        tblInfoPaquetes.getColumnModel().getColumn(6).setPreferredWidth(80);
        tblInfoPaquetes.getColumnModel().getColumn(7).setPreferredWidth(160);
        tblInfoPaquetes.getColumnModel().getColumn(8).setPreferredWidth(95);
        tblInfoPaquetes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblInfoPaquetes.setSelectionBackground(Color.yellow);
        tblInfoPaquetes.setSelectionForeground(Color.black);
        tblInfoPaquetes.setIntercellSpacing(new Dimension(1, 1));
        tblInfoPaquetes.setRowMargin(1);
        tblInfoPaquetes.setOpaque(false);

    }

    private void Formato_SinPaquete() {
        tblInfoPaquetes.getColumnModel().getColumn(0).setMaxWidth(0);
        tblInfoPaquetes.getColumnModel().getColumn(0).setMinWidth(0);
        tblInfoPaquetes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblInfoPaquetes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblInfoPaquetes.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblInfoPaquetes.getColumnModel().getColumn(2).setPreferredWidth(310);
        tblInfoPaquetes.getColumnModel().getColumn(3).setPreferredWidth(300);
        tblInfoPaquetes.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblInfoPaquetes.getColumnModel().getColumn(5).setPreferredWidth(250);
        tblInfoPaquetes.setIntercellSpacing(new Dimension(1, 1));
        tblInfoPaquetes.setRowMargin(1);
        tblInfoPaquetes.setOpaque(false);

    }

    private void visualizar_Paquetes() {

        myEdit.VisualizarData_ConPaquete(tblInfoPaquetes);
        Formato_conPaquete();

    }

    private void visualizar_SinPaquete() {

        myEdit.VisualizarData_SinPaquete(tblInfoPaquetes);
        Formato_SinPaquete();

    }

    private void visualizar_XEmpresa_ConPaquete() {
        String empresa = txtbusqueda.getText().trim().toString();
        myEdit.VisualizarDataXEmpresa_ConPaquete(tblInfoPaquetes, empresa);
        Formato_conPaquete();
    }

    private void visualizar_XDisponibles_ConPaquete() {
        String desde = null;
        String hasta = null;
        if (cboRango.getSelectedIndex() == 0) {
            desde = "1";
            hasta = "20";
            myEdit.VisualizarDataXDisponibles_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 1) {
            desde = "21";
            hasta = "40";
            myEdit.VisualizarDataXDisponibles_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 2) {
            desde = "41";
            hasta = "60";
            myEdit.VisualizarDataXDisponibles_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 3) {
            desde = "61";
            hasta = "100";
            myEdit.VisualizarDataXDisponibles_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 4) {
            desde = "101";
            hasta = "150";
            myEdit.VisualizarDataXDisponibles_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 5) {
            desde = "151";
            hasta = "200";
            myEdit.VisualizarDataXDisponibles_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        }
    }
    private void visualizar_XContratadas_ConPaquete() {
        String desde = null;
        String hasta = null;
        if (cboRango.getSelectedIndex() == 0) {
            desde = "0";
            hasta = "50";
            myEdit.VisualizarDataXContratadas_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 1) {
            desde = "51";
            hasta = "100";
            myEdit.VisualizarDataXContratadas_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 2) {
            desde = "101";
            hasta = "200";
            myEdit.VisualizarDataXContratadas_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 3) {
            desde = "201";
            hasta = "300";
            myEdit.VisualizarDataXContratadas_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 4) {
            desde = "301";
            hasta = "400";
            myEdit.VisualizarDataXContratadas_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 5) {
            desde = "401";
            hasta = "500";
            myEdit.VisualizarDataXContratadas_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        }else if (cboRango.getSelectedIndex() == 6) {
            desde = "501";
            hasta = "1000";
            myEdit.VisualizarDataXContratadas_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 7) {
            desde = "1001";
            hasta = "2000";
            myEdit.VisualizarDataXContratadas_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        } else if (cboRango.getSelectedIndex() == 8) {
            desde = "2001";
            hasta = "5000";
            myEdit.VisualizarDataXContratadas_ConPaquete(tblInfoPaquetes, desde, hasta);
            Formato_conPaquete();
        }
    }

    private void visualizar_XEstado_ConPaquete() {
        String estado = null;
        if (cboRango.getSelectedItem() == null) {

        } else {
            estado = cboRango.getSelectedItem().toString().trim();
            System.out.println(estado);
            myEdit.VisualizarDataXEstado_ConPaquete(tblInfoPaquetes, estado);
            Formato_conPaquete();
        }

    }

    private void visualizar_XServicio_ConPaquete() {
        String servicio = null;
        if (cboRango.getSelectedItem() == null) {

        } else {
            servicio = cboRango.getSelectedItem().toString().trim();
            myEdit.VisualizarDataXServicio_ConPaquete(tblInfoPaquetes, servicio);
            Formato_conPaquete();
        }

    }

    private void visualizar_XEmpresa_SinPaquete() {
        String empresa = txtbusqueda.getText().trim().toString();
        myEdit.VisualizarDataXEmpresa_SinPaquete(tblInfoPaquetes, empresa);
        Formato_SinPaquete();
    }

    private void Cargar_Combo_Dependente() {
        if (cboFiltroPaquete.getSelectedIndex() == 0) {
            cboRango.setVisible(false);
            txtbusqueda.setVisible(true);
            visualizar_XEmpresa_ConPaquete();
        } else if (cboFiltroPaquete.getSelectedIndex() == 1) {
            cboRango.removeAllItems();
            cboRango.setVisible(true);
            cboRango.addItem("1 - 20");
            cboRango.addItem("21 - 40");
            cboRango.addItem("41 - 60");
            cboRango.addItem("61 - 100");
            cboRango.addItem("101 - 150");
            cboRango.addItem("151 - 200");
            txtbusqueda.setVisible(false);
        }else if (cboFiltroPaquete.getSelectedIndex() == 2) {
            cboRango.removeAllItems();
            cboRango.setVisible(true);
            cboRango.addItem("0 - 50");
            cboRango.addItem("51 - 100");
            cboRango.addItem("101 - 200");
            cboRango.addItem("201 - 300");
            cboRango.addItem("301 - 400");
            cboRango.addItem("401 - 500");
            cboRango.addItem("501 - 1000");
            cboRango.addItem("1001 - 2000");
            cboRango.addItem("2001 - 5000");
            txtbusqueda.setVisible(false);
        } else if (cboFiltroPaquete.getSelectedIndex() == 3) {
            cboRango.removeAllItems();
            cboRango.setVisible(true);
            cboRango.addItem("Aprobado");
            cboRango.addItem("Caducado");
            txtbusqueda.setVisible(false);
        } else if (cboFiltroPaquete.getSelectedIndex() == 4) {
            cboRango.removeAllItems();
            cboRango.setVisible(true);
            cboRango.addItem("Riesgos");
            cboRango.addItem("Laboral");
            cboRango.addItem("Domiciliarias");
            cboRango.addItem("Sin Paquete");
            txtbusqueda.setVisible(false);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Parametros;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JComboBox<String> cboFiltroPaquete;
    private javax.swing.JComboBox<String> cboRango;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBusqueda;
    private javax.swing.JRadioButton rbtnConPaquete;
    private javax.swing.JRadioButton rbtnSinPaquete;
    private javax.swing.JTable tblInfoPaquetes;
    private javax.swing.JTextField txtbusqueda;
    // End of variables declaration//GEN-END:variables
}
