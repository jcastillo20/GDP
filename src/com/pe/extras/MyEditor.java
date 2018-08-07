/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.extras;

import com.pe.controlador.OrganizacionDAO;
import com.pe.modelo.Render;
import com.pe.modelo.Render2;
import com.pe.modelo.organizacion;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
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

    OrganizacionDAO dao = null;

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

        dao = new OrganizacionDAO();
        organizacion vo = new organizacion();

        ArrayList<organizacion> list = dao.Listar_info_Botones_ConPaquete();

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

        dao = new OrganizacionDAO();
        organizacion vo = new organizacion();

        ArrayList<organizacion> list = dao.Listar_info_Botones_X_EMPRESA_ConPaquete(empresa);

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

        dao = new OrganizacionDAO();
        organizacion vo = new organizacion();

        ArrayList<organizacion> list = dao.Listar_info_Botones_SinPaquetes();

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

        dao = new OrganizacionDAO();
        organizacion vo = new organizacion();

        ArrayList<organizacion> list = dao.Listar_info_Botones_X_EMPRESA_SinPaquete(empresa);

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
}
