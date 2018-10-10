/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.controlador;

import com.pe.extras.Mensajes;
import com.pe.modelo.organizacion;
import com.pe.util.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author JUAN
 */
public class OrganizacionDAO {

    public ArrayList<organizacion> Listar_info_Botones_ConPaquete() {
        ArrayList<organizacion> list = new ArrayList<>();
        String sql = "SELECT pos.id_paquete_organizacion_solicitud as ID,o.razon_social AS 'EMPRESA',pos.fecha_creacion AS 'FECHA',po.total_consultas AS 'CONTRATADAS',\n"
                + "pos.consultas_disponible AS 'DISPONIBLES',po.precio_unitario AS 'PRECIO',dp.descripcion_corta  AS 'ESTADO',IFNULL(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(\n"
                + "po.referencia_servicios,\n"
                + "'Rep. Premium Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Internacional ','Riesgos'),\n"
                + "'Rep. Basico Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Internacional ','Riesgos'),\n"
                + "'Rep. VIP Nivel Nacional ','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'REF. BÁSICO ( 1 JEFE DIRECTO ) Nivel Nacional','Laboral'),\n"
                + "'REF. REGULAR ( 2 JEFES DIRECTOS ) Nivel Nacional ','Laboral'),\n"
                + "'REF. ESTANDAR ( 1 JEFE DIRECTO + 1 RRHH ) Nivel Nacional  ','Laboral'),\n"
                + "'REF. PREMIUM ( 2 JEFES DIRECTOS + 1 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. VIP ( 2 JEFES DIRECTOS + 2 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. SUPER VIP ( 3 JEFES DIRECTOS + 3 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'1 RRHH Nivel Nacional','Laboral'),\n"
                + "'2 RRHH Nivel Nacional','Laboral'),\n"
                + "'3 RRHH Nivel Nacional','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Verificacion Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Dom + Verif. Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria BASC Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria Comercial Nivel Nacional ','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Ver. Academica Nivel Nacional ','Academicas'),\n"
                + "'Normal Lima ','Domiciliarias'),\n"
                + "'Normal Provincia ','Domiciliarias'),\n"
                + "'Zona Roja Normal Lima ','Domiciliarias'),\n"
                + "'Zona Roja Normal Provincia ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias','Domiciliarias'),\n"
                + "'Validacion Cert. Trab. Nivel Nacional ','Cert. Trabajo')\n"
                + ",'Sin paquete')\n"
                + "as SERVICIO\n"
                + "FROM paquete_organizacion_solicitud pos \n"
                + "INNER JOIN paquete_organizacion po ON po.Id_paquete_organizacion=pos.id_paquete_organizacion\n"
                + "INNER JOIN organizacion o ON o.id_organizacion=po.Id_Organizacion\n"
                + "INNER JOIN detalle_catalogo dp ON dp.codigo=pos.id_estado\n"
                + "WHERE pos.id_estado in (3,5) AND dp.id_catalogo=21 \n"
                + "ORDER BY SERVICIO,dp.descripcion_corta,pos.consultas_disponible ASC";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                organizacion vo = new organizacion();
                vo.setId_estado(rs.getString("ID"));
                vo.setRazon_social(rs.getString("EMPRESA"));
                vo.setFecha_creacion(rs.getDate("FECHA"));
                vo.setId_organizacion(rs.getInt("CONTRATADAS"));
                vo.setId_usuario_actualiza(rs.getString("DISPONIBLES"));
                vo.setResponsable_pago(rs.getString("PRECIO"));
                vo.setRuc(rs.getString("ESTADO"));
                vo.setTelefono1(rs.getString("SERVICIO"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<organizacion> Listar_info_Botones_X_EMPRESA_ConPaquete(String empresa) {
        ArrayList<organizacion> list = new ArrayList<>();
        String sql = "SELECT pos.id_paquete_organizacion_solicitud as ID,o.razon_social AS 'EMPRESA',pos.fecha_creacion AS 'FECHA',po.total_consultas AS 'CONTRATADAS',\n"
                + "pos.consultas_disponible AS 'DISPONIBLES',po.precio_unitario AS 'PRECIO',dp.descripcion_corta  AS 'ESTADO',IFNULL(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(\n"
                + "po.referencia_servicios,\n"
                + "'Rep. Premium Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Internacional ','Riesgos'),\n"
                + "'Rep. Basico Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Internacional ','Riesgos'),\n"
                + "'Rep. VIP Nivel Nacional ','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'REF. BÁSICO ( 1 JEFE DIRECTO ) Nivel Nacional','Laboral'),\n"
                + "'REF. REGULAR ( 2 JEFES DIRECTOS ) Nivel Nacional ','Laboral'),\n"
                + "'REF. ESTANDAR ( 1 JEFE DIRECTO + 1 RRHH ) Nivel Nacional  ','Laboral'),\n"
                + "'REF. PREMIUM ( 2 JEFES DIRECTOS + 1 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. VIP ( 2 JEFES DIRECTOS + 2 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. SUPER VIP ( 3 JEFES DIRECTOS + 3 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'1 RRHH Nivel Nacional','Laboral'),\n"
                + "'2 RRHH Nivel Nacional','Laboral'),\n"
                + "'3 RRHH Nivel Nacional','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Verificacion Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Dom + Verif. Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria BASC Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria Comercial Nivel Nacional ','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Ver. Academica Nivel Nacional ','Academicas'),\n"
                + "'Normal Lima ','Domiciliarias'),\n"
                + "'Normal Provincia ','Domiciliarias'),\n"
                + "'Zona Roja Normal Lima ','Domiciliarias'),\n"
                + "'Zona Roja Normal Provincia ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias','Domiciliarias'),\n"
                + "'Validacion Cert. Trab. Nivel Nacional ','Cert. Trabajo')\n"
                + ",'Sin paquete')\n"
                + "as SERVICIO\n"
                + "FROM paquete_organizacion_solicitud pos \n"
                + "INNER JOIN paquete_organizacion po ON po.Id_paquete_organizacion=pos.id_paquete_organizacion\n"
                + "INNER JOIN organizacion o ON o.id_organizacion=po.Id_Organizacion\n"
                + "INNER JOIN detalle_catalogo dp ON dp.codigo=pos.id_estado\n"
                + "WHERE pos.id_estado in (3,5) AND dp.id_catalogo=21 AND o.razon_social LIKE ? ORDER BY SERVICIO,dp.descripcion_corta,pos.consultas_disponible ASC";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, "%" + empresa + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                organizacion vo = new organizacion();
                vo.setId_estado(rs.getString("ID"));
                vo.setRazon_social(rs.getString("EMPRESA"));
                vo.setFecha_creacion(rs.getDate("FECHA"));
                vo.setId_organizacion(rs.getInt("CONTRATADAS"));
                vo.setId_usuario_actualiza(rs.getString("DISPONIBLES"));
                vo.setResponsable_pago(rs.getString("PRECIO"));
                vo.setRuc(rs.getString("ESTADO"));
                vo.setTelefono1(rs.getString("SERVICIO"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<organizacion> Listar_info_Botones_X_DISPONIBLES_ConPaquete(String desde, String hasta) {
        ArrayList<organizacion> list = new ArrayList<>();
        String sql = "SELECT pos.id_paquete_organizacion_solicitud as ID,o.razon_social AS 'EMPRESA',pos.fecha_creacion AS 'FECHA',po.total_consultas AS 'CONTRATADAS',\n"
                + "pos.consultas_disponible AS 'DISPONIBLES',po.precio_unitario AS 'PRECIO',dp.descripcion_corta  AS 'ESTADO',IFNULL(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(\n"
                + "po.referencia_servicios,\n"
                + "'Rep. Premium Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Internacional ','Riesgos'),\n"
                + "'Rep. Basico Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Internacional ','Riesgos'),\n"
                + "'Rep. VIP Nivel Nacional ','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'REF. BÁSICO ( 1 JEFE DIRECTO ) Nivel Nacional','Laboral'),\n"
                + "'REF. REGULAR ( 2 JEFES DIRECTOS ) Nivel Nacional ','Laboral'),\n"
                + "'REF. ESTANDAR ( 1 JEFE DIRECTO + 1 RRHH ) Nivel Nacional  ','Laboral'),\n"
                + "'REF. PREMIUM ( 2 JEFES DIRECTOS + 1 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. VIP ( 2 JEFES DIRECTOS + 2 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. SUPER VIP ( 3 JEFES DIRECTOS + 3 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'1 RRHH Nivel Nacional','Laboral'),\n"
                + "'2 RRHH Nivel Nacional','Laboral'),\n"
                + "'3 RRHH Nivel Nacional','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Verificacion Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Dom + Verif. Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria BASC Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria Comercial Nivel Nacional ','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Ver. Academica Nivel Nacional ','Academicas'),\n"
                + "'Normal Lima ','Domiciliarias'),\n"
                + "'Normal Provincia ','Domiciliarias'),\n"
                + "'Zona Roja Normal Lima ','Domiciliarias'),\n"
                + "'Zona Roja Normal Provincia ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias','Domiciliarias'),\n"
                + "'Validacion Cert. Trab. Nivel Nacional ','Cert. Trabajo')\n"
                + ",'Sin paquete')\n"
                + "as SERVICIO\n"
                + "FROM paquete_organizacion_solicitud pos \n"
                + "INNER JOIN paquete_organizacion po ON po.Id_paquete_organizacion=pos.id_paquete_organizacion\n"
                + "INNER JOIN organizacion o ON o.id_organizacion=po.Id_Organizacion\n"
                + "INNER JOIN detalle_catalogo dp ON dp.codigo=pos.id_estado\n"
                + "WHERE  dp.id_catalogo=21 AND\n"
                + "pos.id_estado in (3,5) AND pos.consultas_disponible between ? and ?  ORDER BY SERVICIO,dp.descripcion_corta,pos.consultas_disponible ASC";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, desde);
            ps.setString(2, hasta);
            rs = ps.executeQuery();
            while (rs.next()) {
                organizacion vo = new organizacion();
                vo.setId_estado(rs.getString("ID"));
                vo.setRazon_social(rs.getString("EMPRESA"));
                vo.setFecha_creacion(rs.getDate("FECHA"));
                vo.setId_organizacion(rs.getInt("CONTRATADAS"));
                vo.setId_usuario_actualiza(rs.getString("DISPONIBLES"));
                vo.setResponsable_pago(rs.getString("PRECIO"));
                vo.setRuc(rs.getString("ESTADO"));
                vo.setTelefono1(rs.getString("SERVICIO"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<organizacion> Listar_info_Botones_X_CONTRATADAS_ConPaquete(String desde, String hasta) {
        ArrayList<organizacion> list = new ArrayList<>();
        String sql = "SELECT pos.id_paquete_organizacion_solicitud as ID,o.razon_social AS 'EMPRESA',pos.fecha_creacion AS 'FECHA',po.total_consultas AS 'CONTRATADAS',\n"
                + "pos.consultas_disponible AS 'DISPONIBLES',po.precio_unitario AS 'PRECIO',dp.descripcion_corta  AS 'ESTADO',IFNULL(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(\n"
                + "po.referencia_servicios,\n"
                + "'Rep. Premium Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Internacional ','Riesgos'),\n"
                + "'Rep. Basico Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Internacional ','Riesgos'),\n"
                + "'Rep. VIP Nivel Nacional ','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'REF. BÁSICO ( 1 JEFE DIRECTO ) Nivel Nacional','Laboral'),\n"
                + "'REF. REGULAR ( 2 JEFES DIRECTOS ) Nivel Nacional ','Laboral'),\n"
                + "'REF. ESTANDAR ( 1 JEFE DIRECTO + 1 RRHH ) Nivel Nacional  ','Laboral'),\n"
                + "'REF. PREMIUM ( 2 JEFES DIRECTOS + 1 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. VIP ( 2 JEFES DIRECTOS + 2 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. SUPER VIP ( 3 JEFES DIRECTOS + 3 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'1 RRHH Nivel Nacional','Laboral'),\n"
                + "'2 RRHH Nivel Nacional','Laboral'),\n"
                + "'3 RRHH Nivel Nacional','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Verificacion Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Dom + Verif. Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria BASC Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria Comercial Nivel Nacional ','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Ver. Academica Nivel Nacional ','Academicas'),\n"
                + "'Normal Lima ','Domiciliarias'),\n"
                + "'Normal Provincia ','Domiciliarias'),\n"
                + "'Zona Roja Normal Lima ','Domiciliarias'),\n"
                + "'Zona Roja Normal Provincia ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias','Domiciliarias'),\n"
                + "'Validacion Cert. Trab. Nivel Nacional ','Cert. Trabajo')\n"
                + ",'Sin paquete')\n"
                + "as SERVICIO\n"
                + "FROM paquete_organizacion_solicitud pos \n"
                + "INNER JOIN paquete_organizacion po ON po.Id_paquete_organizacion=pos.id_paquete_organizacion\n"
                + "INNER JOIN organizacion o ON o.id_organizacion=po.Id_Organizacion\n"
                + "INNER JOIN detalle_catalogo dp ON dp.codigo=pos.id_estado\n"
                + "WHERE  dp.id_catalogo=21 AND\n"
                + "pos.id_estado in (3,5) AND po.total_consultas between ? and ?  ORDER BY SERVICIO,dp.descripcion_corta,pos.consultas_disponible ASC";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, desde);
            ps.setString(2, hasta);
            rs = ps.executeQuery();
            while (rs.next()) {
                organizacion vo = new organizacion();
                vo.setId_estado(rs.getString("ID"));
                vo.setRazon_social(rs.getString("EMPRESA"));
                vo.setFecha_creacion(rs.getDate("FECHA"));
                vo.setId_organizacion(rs.getInt("CONTRATADAS"));
                vo.setId_usuario_actualiza(rs.getString("DISPONIBLES"));
                vo.setResponsable_pago(rs.getString("PRECIO"));
                vo.setRuc(rs.getString("ESTADO"));
                vo.setTelefono1(rs.getString("SERVICIO"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<organizacion> Listar_info_Botones_X_ESTADO_ConPaquete(String estado) {
        ArrayList<organizacion> list = new ArrayList<>();
        String sql = "SELECT T.ID,T.EMPRESA,T.FECHA,T.CONTRATADAS,T.DISPONIBLES,T.PRECIO,T.ESTADO,T.SERVICIO FROM (\n"
                + "SELECT pos.id_paquete_organizacion_solicitud as ID,o.razon_social AS 'EMPRESA',pos.fecha_creacion AS 'FECHA',po.total_consultas AS 'CONTRATADAS',\n"
                + "pos.consultas_disponible AS 'DISPONIBLES',po.precio_unitario AS 'PRECIO',dp.descripcion_corta  AS 'ESTADO',IFNULL(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(\n"
                + "po.referencia_servicios,\n"
                + "'Rep. Premium Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Internacional ','Riesgos'),\n"
                + "'Rep. Basico Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Internacional ','Riesgos'),\n"
                + "'Rep. VIP Nivel Nacional ','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'REF. BÁSICO ( 1 JEFE DIRECTO ) Nivel Nacional','Laboral'),\n"
                + "'REF. REGULAR ( 2 JEFES DIRECTOS ) Nivel Nacional ','Laboral'),\n"
                + "'REF. ESTANDAR ( 1 JEFE DIRECTO + 1 RRHH ) Nivel Nacional  ','Laboral'),\n"
                + "'REF. PREMIUM ( 2 JEFES DIRECTOS + 1 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. VIP ( 2 JEFES DIRECTOS + 2 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. SUPER VIP ( 3 JEFES DIRECTOS + 3 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'1 RRHH Nivel Nacional','Laboral'),\n"
                + "'2 RRHH Nivel Nacional','Laboral'),\n"
                + "'3 RRHH Nivel Nacional','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Verificacion Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Dom + Verif. Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria BASC Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria Comercial Nivel Nacional ','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Ver. Academica Nivel Nacional ','Academicas'),\n"
                + "'Normal Lima ','Domiciliarias'),\n"
                + "'Normal Provincia ','Domiciliarias'),\n"
                + "'Zona Roja Normal Lima ','Domiciliarias'),\n"
                + "'Zona Roja Normal Provincia ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias','Domiciliarias'),\n"
                + "'Validacion Cert. Trab. Nivel Nacional ','Cert. Trabajo')\n"
                + ",'Sin paquete')\n"
                + "as SERVICIO\n"
                + "FROM paquete_organizacion_solicitud pos \n"
                + "INNER JOIN paquete_organizacion po ON po.Id_paquete_organizacion=pos.id_paquete_organizacion\n"
                + "INNER JOIN organizacion o ON o.id_organizacion=po.Id_Organizacion\n"
                + "INNER JOIN detalle_catalogo dp ON dp.codigo=pos.id_estado\n"
                + "WHERE dp.id_catalogo=21 AND pos.id_estado in (3,5)   \n"
                + ")T WHERE T.ESTADO=?  ORDER BY T.SERVICIO,T.ESTADO,T.DISPONIBLES ASC";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();
            while (rs.next()) {
                organizacion vo = new organizacion();
                vo.setId_estado(rs.getString("ID"));
                vo.setRazon_social(rs.getString("EMPRESA"));
                vo.setFecha_creacion(rs.getDate("FECHA"));
                vo.setId_organizacion(rs.getInt("CONTRATADAS"));
                vo.setId_usuario_actualiza(rs.getString("DISPONIBLES"));
                vo.setResponsable_pago(rs.getString("PRECIO"));
                vo.setRuc(rs.getString("ESTADO"));
                vo.setTelefono1(rs.getString("SERVICIO"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<organizacion> Listar_info_Botones_X_SERVICIO_ConPaquete(String servicio) {
        ArrayList<organizacion> list = new ArrayList<>();
        String sql = "SELECT T.ID,T.EMPRESA,T.FECHA,T.CONTRATADAS,T.DISPONIBLES,T.PRECIO,T.ESTADO,T.SERVICIO FROM (\n"
                + "SELECT pos.id_paquete_organizacion_solicitud as ID,o.razon_social AS 'EMPRESA',pos.fecha_creacion AS 'FECHA',po.total_consultas AS 'CONTRATADAS',\n"
                + "pos.consultas_disponible AS 'DISPONIBLES',po.precio_unitario AS 'PRECIO',dp.descripcion_corta  AS 'ESTADO',IFNULL(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(\n"
                + "REPLACE(\n"
                + "po.referencia_servicios,\n"
                + "'Rep. Premium Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Nivel Nacional ','Riesgos'),\n"
                + "'Lavado de activos Internacional ','Riesgos'),\n"
                + "'Rep. Basico Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Nivel Nacional ','Riesgos'),\n"
                + "'Reporte Internacional Internacional ','Riesgos'),\n"
                + "'Rep. VIP Nivel Nacional ','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'Riesgos, Riesgos','Riesgos'),\n"
                + "'REF. BÁSICO ( 1 JEFE DIRECTO ) Nivel Nacional','Laboral'),\n"
                + "'REF. REGULAR ( 2 JEFES DIRECTOS ) Nivel Nacional ','Laboral'),\n"
                + "'REF. ESTANDAR ( 1 JEFE DIRECTO + 1 RRHH ) Nivel Nacional  ','Laboral'),\n"
                + "'REF. PREMIUM ( 2 JEFES DIRECTOS + 1 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. VIP ( 2 JEFES DIRECTOS + 2 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'REF. SUPER VIP ( 3 JEFES DIRECTOS + 3 RRHH ) Nivel Nacional ','Laboral'),\n"
                + "'1 RRHH Nivel Nacional','Laboral'),\n"
                + "'2 RRHH Nivel Nacional','Laboral'),\n"
                + "'3 RRHH Nivel Nacional','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Laboral, Laboral','Laboral'),\n"
                + "'Verificacion Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Dom + Verif. Empresarial Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria BASC Nivel Nacional ','Proveedor'),\n"
                + "'Auditoria Comercial Nivel Nacional ','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Proveedor, Proveedor','Proveedor'),\n"
                + "'Ver. Academica Nivel Nacional ','Academicas'),\n"
                + "'Normal Lima ','Domiciliarias'),\n"
                + "'Normal Provincia ','Domiciliarias'),\n"
                + "'Zona Roja Normal Lima ','Domiciliarias'),\n"
                + "'Zona Roja Normal Provincia ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias ','Domiciliarias'),\n"
                + "'Domiciliarias, Domiciliarias','Domiciliarias'),\n"
                + "'Validacion Cert. Trab. Nivel Nacional ','Cert. Trabajo')\n"
                + ",'Sin paquete')\n"
                + "as SERVICIO\n"
                + "FROM paquete_organizacion_solicitud pos \n"
                + "INNER JOIN paquete_organizacion po ON po.Id_paquete_organizacion=pos.id_paquete_organizacion\n"
                + "INNER JOIN organizacion o ON o.id_organizacion=po.Id_Organizacion\n"
                + "INNER JOIN detalle_catalogo dp ON dp.codigo=pos.id_estado\n"
                + "WHERE dp.id_catalogo=21 AND pos.id_estado in (3,5)   \n"
                + ")T WHERE T.SERVICIO like ?  ORDER BY T.SERVICIO,T.ESTADO,T.DISPONIBLES ASC";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, "%" + servicio + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                organizacion vo = new organizacion();
                vo.setId_estado(rs.getString("ID"));
                vo.setRazon_social(rs.getString("EMPRESA"));
                vo.setFecha_creacion(rs.getDate("FECHA"));
                vo.setId_organizacion(rs.getInt("CONTRATADAS"));
                vo.setId_usuario_actualiza(rs.getString("DISPONIBLES"));
                vo.setResponsable_pago(rs.getString("PRECIO"));
                vo.setRuc(rs.getString("ESTADO"));
                vo.setTelefono1(rs.getString("SERVICIO"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<organizacion> Listar_info_Botones_SinPaquetes() {
        ArrayList<organizacion> list = new ArrayList<>();
        String sql = "select o.id_organizacion AS ID,o.ruc AS RUC,o.razon_social AS EMPRESA,u.nombres AS SECTORISTA,\n"
                + "dc.descripcion_corta AS CATEGORIA \n"
                + "from organizacion o \n"
                + "inner join detalle_catalogo dc ON dc.codigo=o.categoria\n"
                + "inner join usuario u ON u.id_usuario=o.id_usuario_sectorista\n"
                + "where  dc.id_catalogo=12 and o.id_estado in (1,2)";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                organizacion vo = new organizacion();
                vo.setId_estado(rs.getString("ID"));
                vo.setRuc(rs.getString("RUC"));
                vo.setRazon_social(rs.getString("EMPRESA"));
                vo.setId_usuario_actualiza(rs.getString("SECTORISTA"));
                vo.setResponsable_pago(rs.getString("CATEGORIA"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<organizacion> Listar_info_Botones_X_EMPRESA_SinPaquete(String empresa) {
        ArrayList<organizacion> list = new ArrayList<>();
        String sql = "select o.id_organizacion AS ID,o.ruc AS RUC,o.razon_social AS EMPRESA,u.nombres AS SECTORISTA,\n"
                + "dc.descripcion_corta AS CATEGORIA \n"
                + "from organizacion o \n"
                + "inner join detalle_catalogo dc ON dc.codigo=o.categoria\n"
                + "inner join usuario u ON u.id_usuario=o.id_usuario_sectorista\n"
                + "where  dc.id_catalogo=12 and o.id_estado in (1,2) AND  o.razon_social LIKE ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, "%" + empresa + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                organizacion vo = new organizacion();
                vo.setId_estado(rs.getString("ID"));
                vo.setRuc(rs.getString("RUC"));
                vo.setRazon_social(rs.getString("EMPRESA"));
                vo.setId_usuario_actualiza(rs.getString("SECTORISTA"));
                vo.setResponsable_pago(rs.getString("CATEGORIA"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return list;
    }

}
