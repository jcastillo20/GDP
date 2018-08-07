/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.vista;

import com.pe.controlador.Detalle_PedidoDAO;
import com.pe.extras.Mensajes;
import com.pe.extras.export_excel;
import com.pe.modelo.detalle_pedido;
import com.pe.util.conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author JUAN
 */
public class FrmDialogDetallePaquete extends javax.swing.JDialog {

    /**
     * Creates new form FrmDialogDetallePaquete
     */
    String idPaquete;
    String idEmpresa;
    String Empresa, Servicio;
    Date date = new Date();
    String Desde;
    String Hasta;

    public FrmDialogDetallePaquete(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/image/ipartner.png")).getImage());
        Mensajes.centerComponent(this);

    }

    public void Recibedatos(String id, String empresa, String servicio) {

        System.out.println(id + "recibo el id");
        idPaquete = id;
        Empresa = empresa;
        Servicio = servicio.trim();
        if (Servicio.matches("(.*)Riesgos(.*)")) {
            ListarDetallePaqueteRiesgos();
            lblNombreEmpresa.setText(Empresa);
            lblServicio.setText(Servicio);
        } else if (Servicio.matches("(.*)Laboral(.*)")) {
            ListarDetallePaqueteLaboral();
            lblNombreEmpresa.setText(Empresa);
            lblServicio.setText(Servicio);
        }else{
            ListarDetallePaqueteOtrosServicios();
            lblNombreEmpresa.setText(Empresa);
            lblServicio.setText(Servicio);
        }

    }

    private void ListarDetallePaqueteRiesgos() {
        try {
            tblDetalle_pedido.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.ConsolidadoxEmpresa_Riesgos(idPaquete)));
            FormatoDetallepaqueteRiesgos();
            ListarDetalleConsolidadoRiesgos();
            ListarUsuarioPaqueteRiesgos();
        } catch (Exception e) {
        }
    }

    private void ListarDetalleConsolidadoRiesgos() {
        try {
            tblDetalleConsolidado.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.DetalleConsolidado_Riesgos(idPaquete)));
            FormatoDetalleconsolidadoRiesgos();
            if (tblDetalleConsolidado.getRowCount() > 0) {
                txtReportesAFavor.setVisible(true);
                txtTotalReportesSolicitados.setText("" + tblDetalleConsolidado.getValueAt(0, 5));
                txtReportesAFavor.setText("" + tblDetalleConsolidado.getValueAt(0, 6));
            } else {
                Mensajes.msjMuestra("No hay Datos del paquete seleccionado");
            }

        } catch (Exception e) {
        }
    }

    private void ListarUsuarioPaqueteRiesgos() {
        try {
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.Detalle_Usuario_Paquete_Riesgos(idPaquete)));
            FormatoUsuarioRiesgos();

        } catch (Exception e) {
        }
    }

    private void ListarDetallePaqueteLaboral() {
        try {
            tblDetalle_pedido.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.ConsolidadoxEmpresa_Laboral(idPaquete)));
            FormatoDetallepaqueteLaboral();
            ListarDetalleConsolidadoLaboral();
            ListarUsuarioPaqueteLaboral();
        } catch (Exception e) {
        }
    }

    private void ListarDetalleConsolidadoLaboral() {
        try {
            tblDetalleConsolidado.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.DetalleConsolidado_Laboral(idPaquete)));
            FormatoDetalleconsolidadoLaboral();
            if (tblDetalleConsolidado.getRowCount() > 0) {
                txtReportesAFavor.setVisible(true);
                txtTotalReportesSolicitados.setText("" + tblDetalleConsolidado.getValueAt(0, 5));
                txtReportesAFavor.setText("" + tblDetalleConsolidado.getValueAt(0, 6));
            } else {
                Mensajes.msjMuestra("No hay Datos del paquete seleccionado");
            }

        } catch (Exception e) {
        }
    }

    private void ListarUsuarioPaqueteLaboral() {
        try {
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.Detalle_Usuario_Paquete_Laboral(idPaquete)));
            FormatoUsuarioLaboral();

        } catch (Exception e) {
        }
    }
    
        private void ListarDetallePaqueteOtrosServicios() {
        try {
            tblDetalle_pedido.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.ConsolidadoxEmpresa_Otros_Servicios(idPaquete)));
            FormatoDetallepaqueteLaboral();
            ListarDetalleConsolidadoOtrosServicios();
            ListarUsuarioPaqueteOtrosServicios();
        } catch (Exception e) {
        }
    }

    private void ListarDetalleConsolidadoOtrosServicios() {
        try {
            tblDetalleConsolidado.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.DetalleConsolidado_Otros_Servicios(idPaquete)));
            FormatoDetalleconsolidadoLaboral();
            if (tblDetalleConsolidado.getRowCount() > 0) {
                txtReportesAFavor.setVisible(true);
                txtTotalReportesSolicitados.setText("" + tblDetalleConsolidado.getValueAt(0, 5));
                txtReportesAFavor.setText("" + tblDetalleConsolidado.getValueAt(0, 6));
            } else {
                Mensajes.msjMuestra("No hay Datos del paquete seleccionado");
            }

        } catch (Exception e) {
        }
    }

    private void ListarUsuarioPaqueteOtrosServicios() {
        try {
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.Detalle_Usuario_Paquete_Otros_Servicios(idPaquete)));
            FormatoUsuarioLaboral();

        } catch (Exception e) {
        }
    }

    private void FormatoDetallepaqueteRiesgos() {
        tblDetalle_pedido.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblDetalle_pedido.getColumnModel().getColumn(1).setPreferredWidth(280);
        tblDetalle_pedido.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblDetalle_pedido.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblDetalle_pedido.getColumnModel().getColumn(4).setPreferredWidth(110);
        tblDetalle_pedido.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblDetalle_pedido.getColumnModel().getColumn(6).setPreferredWidth(180);
        tblDetalle_pedido.getColumnModel().getColumn(7).setPreferredWidth(250);
        tblDetalle_pedido.getColumnModel().getColumn(8).setPreferredWidth(100);
        tblDetalle_pedido.getColumnModel().getColumn(9).setPreferredWidth(250);
        tblDetalle_pedido.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDetalle_pedido.setSelectionBackground(Color.GRAY);
        tblDetalle_pedido.setIntercellSpacing(new Dimension(1, 1));
        tblDetalle_pedido.setRowMargin(1);
        tblDetalle_pedido.setOpaque(false);
    }

    private void FormatoDetalleconsolidadoRiesgos() {
        tblDetalleConsolidado.getColumnModel().getColumn(5).setPreferredWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(6).setPreferredWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(5).setMaxWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(5).setMinWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(6).setMaxWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(6).setMinWidth(0);
        tblDetalleConsolidado.setSelectionBackground(Color.GRAY);
        tblDetalleConsolidado.setIntercellSpacing(new Dimension(1, 1));
        tblDetalleConsolidado.setRowMargin(1);
        tblDetalleConsolidado.setOpaque(false);

    }

    private void FormatoUsuarioRiesgos() {
        tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(350);
        tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblUsuarios.setSelectionBackground(Color.GRAY);
        tblUsuarios.setIntercellSpacing(new Dimension(1, 1));
        tblUsuarios.setRowMargin(1);
        tblUsuarios.setOpaque(false);
    }

    private void FormatoDetallepaqueteLaboral() {
        tblDetalle_pedido.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblDetalle_pedido.getColumnModel().getColumn(1).setPreferredWidth(280);
        tblDetalle_pedido.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblDetalle_pedido.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblDetalle_pedido.getColumnModel().getColumn(4).setPreferredWidth(110);
        tblDetalle_pedido.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblDetalle_pedido.getColumnModel().getColumn(6).setPreferredWidth(180);
        tblDetalle_pedido.getColumnModel().getColumn(7).setPreferredWidth(250);
        tblDetalle_pedido.getColumnModel().getColumn(8).setPreferredWidth(100);
        tblDetalle_pedido.getColumnModel().getColumn(9).setPreferredWidth(250);
        tblDetalle_pedido.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDetalle_pedido.setSelectionBackground(Color.GRAY);
        tblDetalle_pedido.setIntercellSpacing(new Dimension(1, 1));
        tblDetalle_pedido.setRowMargin(1);
        tblDetalle_pedido.setOpaque(false);
    }

    private void FormatoDetalleconsolidadoLaboral() {
        tblDetalleConsolidado.getColumnModel().getColumn(5).setPreferredWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(6).setPreferredWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(5).setMaxWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(5).setMinWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(6).setMaxWidth(0);
        tblDetalleConsolidado.getColumnModel().getColumn(6).setMinWidth(0);
        tblDetalleConsolidado.setSelectionBackground(Color.GRAY);
        tblDetalleConsolidado.setIntercellSpacing(new Dimension(1, 1));
        tblDetalleConsolidado.setRowMargin(1);
        tblDetalleConsolidado.setOpaque(false);
    }

    private void FormatoUsuarioLaboral() {
        tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(350);
        tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblUsuarios.setSelectionBackground(Color.GRAY);
        tblUsuarios.setIntercellSpacing(new Dimension(1, 1));
        tblUsuarios.setRowMargin(1);
        tblUsuarios.setOpaque(false);
    }

    public void RecibeDatosSinPaquete(String id, String empresa, String desde, String hasta,String servicio) {
        Empresa = empresa;
        idEmpresa = id;
        Desde = desde;
        Hasta = hasta;
        lblNombreEmpresa.setText(Empresa);
        Servicio=servicio;
        if(Servicio=="RR"){
            lblServicio.setText("Riesgos");
            ListarDetalleSinPaqueteRiesgos();
        }else if(Servicio=="RL"){
            lblServicio.setText("Laboral");
            ListarDetalleSinPaqueteLaboral();
        }else if(Servicio=="VD"){
            lblServicio.setText("Domiciliaria");
        }else if(Servicio=="VP"){
            lblServicio.setText("Proveedor");
        }else if(Servicio=="VA"){
            lblServicio.setText("Academicas");
        }else if(Servicio=="CT"){
            lblServicio.setText("Academicas");
        }
       
    }

    private void ListarDetalleSinPaqueteRiesgos() {
        try {
            tblDetalle_pedido.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.ConsolidadoxEmpresa_SinPaquete_Riesgos(idEmpresa, Desde, Hasta,Servicio)));
            FormatoDetallepaqueteRiesgos();;
            int cantidad = tblDetalle_pedido.getRowCount();
            txtTotalReportesSolicitados.setText("" + cantidad);
            ListarDetalleConsolidadoSinPaqueteRiesgos();
            ListarUsuarioSinPaqueteRiesgos();
        } catch (Exception e) {
        }
    }

    private void ListarDetalleConsolidadoSinPaqueteRiesgos() {
        try {
            tblDetalleConsolidado.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.DetalleConsolidado_SinPaquete_Riesgos(idEmpresa, Desde, Hasta,Servicio)));
            FormatoDetalleconsolidadoRiesgos();
            if (tblDetalleConsolidado.getRowCount() > 0) {
                txtTotalReportesSolicitados.setText("" + tblDetalleConsolidado.getValueAt(0, 5));
                txtReportesAFavor.setVisible(false);
                tblDetalle_pedido.setVisible(true);

            } else {
                Mensajes.msjMuestra("No hay Datos del paquete seleccionado");
            }

        } catch (Exception e) {
        }
    }

    private void ListarUsuarioSinPaqueteRiesgos() {
        try {
            System.out.println("paso hasta aqui Listar Usuario");
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.Detalle_Usuario_SinPaquete_Riesgos(idEmpresa, Desde, Hasta,Servicio)));
            System.out.println("Ya paso el Lsitado");
            FormatoUsuarioRiesgos();

        } catch (Exception e) {
        }
    }
   
    
    private void ListarDetalleSinPaqueteLaboral() {
        try {
            tblDetalle_pedido.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.ConsolidadoxEmpresa_SinPaquete_Laboral(idEmpresa, Desde, Hasta,Servicio)));
            FormatoDetallepaqueteRiesgos();;
            int cantidad = tblDetalle_pedido.getRowCount();
            txtTotalReportesSolicitados.setText("" + cantidad);
            ListarDetalleConsolidadoSinPaqueteRiesgos();
            ListarUsuarioSinPaqueteRiesgos();
        } catch (Exception e) {
        }
    }

    private void ListarDetalleConsolidadoSinPaqueteLaboral() {
        try {
            tblDetalleConsolidado.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.DetalleConsolidado_SinPaquete_Laboral(idEmpresa, Desde, Hasta,Servicio)));
            FormatoDetalleconsolidadoRiesgos();
            if (tblDetalleConsolidado.getRowCount() > 0) {
                txtTotalReportesSolicitados.setText("" + tblDetalleConsolidado.getValueAt(0, 5));
                txtReportesAFavor.setVisible(false);
                tblDetalle_pedido.setVisible(true);

            } else {
                Mensajes.msjMuestra("No hay Datos del paquete seleccionado");
            }

        } catch (Exception e) {
        }
    }

    private void ListarUsuarioSinPaqueteLaboral() {
        try {
            System.out.println("paso hasta aqui Listar Usuario");
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(Detalle_PedidoDAO.Detalle_Usuario_SinPaquete_Laboral(idEmpresa, Desde, Hasta,Servicio)));
            System.out.println("Ya paso el Lsitado");
            FormatoUsuarioRiesgos();

        } catch (Exception e) {
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle_pedido = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtTotalReportesSolicitados = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleConsolidado = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        lblaFavor = new javax.swing.JLabel();
        txtReportesAFavor = new javax.swing.JTextField();
        lblNombreEmpresa = new javax.swing.JLabel();
        btnGenerarReporte = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        lblServicio = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(null);
        setMinimumSize(new java.awt.Dimension(1061, 738));
        setPreferredSize(new java.awt.Dimension(1185, 712));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("CONSOLIDADO DE PEDIDOS - ");

        tblDetalle_pedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDetalle_pedido);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("CUADRO DETALLE");

        jLabel3.setText("Nro. de Reportes Solicitados:");

        txtTotalReportesSolicitados.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalReportesSolicitados.setForeground(new java.awt.Color(255, 0, 0));
        txtTotalReportesSolicitados.setEnabled(false);

        tblDetalleConsolidado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblDetalleConsolidado);

        lblaFavor.setText("NRO. DE REPORTES A SU FAVOR");

        txtReportesAFavor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtReportesAFavor.setForeground(new java.awt.Color(255, 0, 0));
        txtReportesAFavor.setEnabled(false);

        lblNombreEmpresa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombreEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnGenerarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ic_excel2.png"))); // NOI18N
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("DETALLE POR USUARIO");

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblUsuarios);

        lblServicio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblServicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblServicio.setName("lblServicio"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("SERVICIO :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(734, 1032, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane1)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblaFavor)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtReportesAFavor, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotalReportesSolicitados, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(433, 433, 433))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblServicio)
                        .addComponent(jLabel6))
                    .addComponent(lblNombreEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarReporte)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTotalReportesSolicitados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblaFavor)
                            .addComponent(txtReportesAFavor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        // TODO add your handling code here:
        JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(FrmDialogDetallePaquete.this);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            File dir = dialog.getSelectedFile();

            try {
                List<JTable> tb = new ArrayList<JTable>();
                List<JTable> tb1 = new ArrayList<JTable>();
                List<JTable> tb2 = new ArrayList<JTable>();
                tb.add(tblDetalle_pedido);
                tb1.add(tblDetalleConsolidado);
                tb2.add(tblUsuarios);
                //-------------------
                export_excel excelExporter = new export_excel(tb, tb1, tb2, new File(dir.getAbsolutePath() + ".xls"));
                if (excelExporter.export()) {
                    JOptionPane.showMessageDialog(null, "TABLAS EXPORTADOS CON EXITOS!");

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnGenerarReporteActionPerformed

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
            java.util.logging.Logger.getLogger(FrmDialogDetallePaquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDialogDetallePaquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDialogDetallePaquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDialogDetallePaquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmDialogDetallePaquete dialog = new FrmDialogDetallePaquete(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblNombreEmpresa;
    private javax.swing.JLabel lblServicio;
    public javax.swing.JLabel lblaFavor;
    private javax.swing.JTable tblDetalleConsolidado;
    public javax.swing.JTable tblDetalle_pedido;
    public javax.swing.JTable tblUsuarios;
    public javax.swing.JTextField txtReportesAFavor;
    public javax.swing.JTextField txtTotalReportesSolicitados;
    // End of variables declaration//GEN-END:variables
}
