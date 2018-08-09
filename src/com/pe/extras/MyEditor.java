/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.extras;

import com.pe.controlador.Detalle_PedidoDAO;
import com.pe.controlador.OrganizacionDAO;
import com.pe.modelo.Render;
import com.pe.modelo.Render2;
import com.pe.modelo.detalle_pedido;
import com.pe.modelo.organizacion;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author JUAN
 */
public class MyEditor {

    OrganizacionDAO daoOrganizacion = null;
    Detalle_PedidoDAO daoDetallePedido = null;

    public void VisualizarData_ConPaquete(JTable tabla) {
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("EMPRESA");
        dt.addColumn("FECHA");
        dt.addColumn("CONTRATADAS");
        dt.addColumn("DISPONIBLES");
        dt.addColumn("PRECIO");
        dt.addColumn("ESTADO");
        dt.addColumn("SERVICIO");
        dt.addColumn("Accion");
        JButton btn_info = new JButton("Informacion");
        btn_info.setName("i");

        daoOrganizacion = new OrganizacionDAO();
        organizacion vo = new organizacion();

        ArrayList<organizacion> list = daoOrganizacion.Listar_info_Botones_ConPaquete();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[9];
                vo = list.get(i);
                fila[0] = vo.getId_estado();
                fila[1] = vo.getRazon_social();
                fila[2] = vo.getFecha_creacion();
                fila[3] = vo.getId_organizacion();
                fila[4] = vo.getId_usuario_actualiza();
                fila[5] = vo.getResponsable_pago();
                fila[6] = vo.getRuc();
                fila[7] = vo.getTelefono1();
                fila[8] = btn_info;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);

        }
    }

    public void VisualizarDataXEmpresa_ConPaquete(JTable tabla, String empresa) {
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("EMPRESA");
        dt.addColumn("FECHA");
        dt.addColumn("CONTRATADAS");
        dt.addColumn("DISPONIBLES");
        dt.addColumn("PRECIO");
        dt.addColumn("ESTADO");
        dt.addColumn("SERVICIO");
        dt.addColumn("Accion");
        JButton btn_info = new JButton("Informacion");
        btn_info.setName("i");

        daoOrganizacion = new OrganizacionDAO();
        organizacion vo = new organizacion();

        ArrayList<organizacion> list = daoOrganizacion.Listar_info_Botones_X_EMPRESA_ConPaquete(empresa);

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[9];
                vo = list.get(i);
                fila[0] = vo.getId_estado();
                fila[1] = vo.getRazon_social();
                fila[2] = vo.getFecha_creacion();
                fila[3] = vo.getId_organizacion();
                fila[4] = vo.getId_usuario_actualiza();
                fila[5] = vo.getResponsable_pago();
                fila[6] = vo.getRuc();
                fila[7] = vo.getTelefono1();
                fila[8] = btn_info;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);

        }

    }

    public void VisualizarData_SinPaquete(JTable tabla) {
        tabla.setDefaultRenderer(Object.class, new Render2());
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("RUC");
        dt.addColumn("EMPRESA");
        dt.addColumn("SECTORISTA");
        dt.addColumn("CATEGORIA");
        dt.addColumn("Accion");
        JButton btn_info = new JButton("Informacion");
        btn_info.setName("i");

        daoOrganizacion = new OrganizacionDAO();
        organizacion vo = new organizacion();

        ArrayList<organizacion> list = daoOrganizacion.Listar_info_Botones_SinPaquetes();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[6];
                vo = list.get(i);
                fila[0] = vo.getId_estado();
                fila[1] = vo.getRuc();
                fila[2] = vo.getRazon_social();
                fila[3] = vo.getId_usuario_actualiza();
                fila[4] = vo.getResponsable_pago();
                fila[5] = btn_info;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);

        }
    }

    public void VisualizarDataXEmpresa_SinPaquete(JTable tabla, String empresa) {
        tabla.setDefaultRenderer(Object.class, new Render2());
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("RUC");
        dt.addColumn("EMPRESA");
        dt.addColumn("SECTORISTA");
        dt.addColumn("CATEGORIA");
        dt.addColumn("Accion");
        JButton btn_info = new JButton("Informacion");
        btn_info.setName("i");

        daoOrganizacion = new OrganizacionDAO();
        organizacion vo = new organizacion();

        ArrayList<organizacion> list = daoOrganizacion.Listar_info_Botones_X_EMPRESA_SinPaquete(empresa);

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[6];
                vo = list.get(i);
                fila[0] = vo.getId_estado();
                fila[1] = vo.getRuc();
                fila[2] = vo.getRazon_social();
                fila[3] = vo.getId_usuario_actualiza();
                fila[4] = vo.getResponsable_pago();
                fila[5] = btn_info;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);

        }

    }

    public void VisualizarData_porDNI(JTable tabla, String dni) {
        tabla.setDefaultRenderer(Object.class, new Render2());
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("ID");
        dt.addColumn("ESTADO DETALLE");
        dt.addColumn("ESTADO PEDIDO");
        dt.addColumn("PAQUETE");
        dt.addColumn("EMPRESA");
        dt.addColumn("FECHA");
        dt.addColumn("NUMERO DOCUMENTO");
        dt.addColumn("POSTULANTE");
        dt.addColumn("SERVICIO");
        dt.addColumn("MODALIDAD");
        dt.addColumn("USUARIO");
        dt.addColumn("ACTUALIZAR");
        ImageIcon icono_actualizar = new ImageIcon(getClass().getResource("/image/update.png"));
        JButton btn_actualizar = new JButton();
        btn_actualizar.setIcon(icono_actualizar);
        btn_actualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_actualizar.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_actualizar.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        dt.addColumn("ELIMINAR");
        ImageIcon icono_eliminar = new ImageIcon(getClass().getResource("/image/delete.png"));
        JButton btn_eliminar = new JButton();
        btn_eliminar.setIcon(icono_eliminar);
        btn_actualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_actualizar.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_actualizar.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

        daoDetallePedido = new Detalle_PedidoDAO();
        detalle_pedido vo = new detalle_pedido();

        ArrayList<detalle_pedido> list = daoDetallePedido.Busqueda_DNI(dni);

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[13];
                vo = list.get(i);
                SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy HH:mm:S");
                fila[0] = vo.getId_detalle_pedido();
                fila[1] = vo.getId_localidad();
                fila[2] = vo.getId_tipo_servicio();
                fila[3] = vo.getNombre_archivo();
                fila[4] = vo.getApellido_paterno();
                fila[5] = formateador.format(vo.getFecha_creacion());
                fila[6] = vo.getNumero_documento();
                fila[7] = vo.getApellido_materno();
                fila[8] = vo.getNombres();
                fila[9] = vo.getModalidad_express();
                fila[10] = vo.getRegion();
                fila[11] = btn_actualizar;
                fila[12] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);

        }
    }
}
