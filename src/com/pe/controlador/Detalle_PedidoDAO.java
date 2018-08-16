/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.controlador;

import com.pe.extras.Mensajes;
import com.pe.modelo.detalle_pedido;
import com.pe.modelo.organizacion;
import com.pe.modelo.usuario;
import com.pe.util.conexion;
import com.pe.vista.FrmDialogDetallePaquete;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author JUAN
 */
// probando
public class Detalle_PedidoDAO {

    public static ResultSet ConsolidadoxEmpresa_Riesgos(String id) {
        ResultSet rs = null;
        try {
            String consulta = "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA ,ifnull(REPLACE(REPLACE(dp.flag_antecedentes,'1','Sin Antecedentes'),'2','Con Antecedentes'),'Falta Responder') as ANTECEDENTES from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join detalle_catalogo dc2 inner join pedido p \n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA ,ifnull(REPLACE(REPLACE(dp.flag_antecedentes,'1','Sin Antecedentes'),'2','Con Antecedentes'),'Falta Responder') as ANTECEDENTES from detalle_pedido dp inner join detalle_catalogo dc inner join detalle_catalogo dc2 inner join pedido p\n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido  and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, id);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle paquete" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet DetalleConsolidado_Riesgos(String id) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT \n"
                    + "CONCAT(MONTHNAME(dp.fecha_creacion),\" - \",YEAR(dp.fecha_creacion))AS MES,dc.descripcion_larga as SERVICIO, COUNT(1) AS 'NRO DE REPORTES',\n"
                    + "po.precio_unitario AS 'COSTO UNITARIO',po.precio_unitario*COUNT(1) AS TOTAL,\n"
                    + "po.total_consultas-pos.consultas_disponible as 'CONSUMIDO',pos.consultas_disponible as 'DISPONIBLE'\n"
                    + "FROM gdp.detalle_pedido dp  inner join detalle_catalogo dc INNER JOIN paquete_organizacion_solicitud pos ON dp.id_paquete_organizacion_solicitud = pos.id_paquete_organizacion_solicitud\n"
                    + "INNER JOIN paquete_organizacion po inner join pedido p ON po.Id_paquete_organizacion = pos.id_paquete_organizacion\n"
                    + "WHERE dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and  dc.id_catalogo=6  and dc.codigo=dp.id_tipo_servicio and dp.id_paquete_organizacion_solicitud =?  GROUP BY YEAR(dp.fecha_creacion),MONTHNAME(dp.fecha_creacion),dp.id_tipo_servicio ORDER BY dp.fecha_creacion";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            rs = pst.executeQuery();
            //conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet Detalle_Usuario_Paquete_Riesgos(String id) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.USUARIOS,SUM(T.total_consulta) AS 'TOTAL CONSULTAS',SUM(T.positivos) AS POSITIVOS,SUM(T.negativos) AS NEGATIVOS from(\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join pedido p\n"
                    + "inner join detalle_catalogo dc2  where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea and dp.flag_antecedentes=1\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",0 as positivos,count(*) as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea and dp.flag_antecedentes=2\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea and dp.flag_antecedentes is null\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select'Pedido Automatico' AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos from detalle_pedido dp inner join detalle_catalogo dc  inner join pedido p\n"
                    + "inner join detalle_catalogo dc2  where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and dp.flag_antecedentes=1 and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",0 as positivos,count(*) as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and dp.flag_antecedentes=2 and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad and dp.flag_antecedentes is null and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes)T\n"
                    + "GROUP BY (T.USUARIOS)";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, id);
            pst.setString(4, id);
            pst.setString(5, id);
            pst.setString(6, id);
            rs = pst.executeQuery();
            // conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Usuario Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ConsolidadoxEmpresa_Laboral(String id) {
        ResultSet rs = null;
        try {
            String consulta = "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dc3.descripcion_corta as ESTADO,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join detalle_catalogo dc2 inner join pedido p inner join detalle_catalogo dc3\n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea\n"
                    + "and dc3.id_catalogo=16 and dc3.codigo=dp.id_estado_reporte\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dc3.descripcion_corta as ESTADO,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join detalle_catalogo dc2 inner join pedido p inner join detalle_catalogo dc3\n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido  and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and dp.id_usuario_crea=0\n"
                    + "and dc3.id_catalogo=16 and dc3.codigo=dp.id_estado_reporte\n"
                    + "and dp.id_paquete_organizacion_solicitud=?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, id);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle paquete" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet DetalleConsolidado_Laboral(String id) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT \n"
                    + "CONCAT(MONTHNAME(dp.fecha_creacion),\" - \",YEAR(dp.fecha_creacion))AS MES,dc.descripcion_larga as SERVICIO, COUNT(1) AS 'NRO DE REPORTES',\n"
                    + "po.precio_unitario AS 'COSTO UNITARIO',po.precio_unitario*COUNT(1) AS TOTAL,\n"
                    + "po.total_consultas-pos.consultas_disponible as 'CONSUMIDO',pos.consultas_disponible as 'DISPONIBLE'\n"
                    + "FROM gdp.detalle_pedido dp  inner join detalle_catalogo dc INNER JOIN paquete_organizacion_solicitud pos ON dp.id_paquete_organizacion_solicitud = pos.id_paquete_organizacion_solicitud\n"
                    + "INNER JOIN paquete_organizacion po inner join pedido p ON po.Id_paquete_organizacion = pos.id_paquete_organizacion\n"
                    + "WHERE dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and  dc.id_catalogo=6  and dc.codigo=dp.id_tipo_servicio and \n"
                    + "dp.id_paquete_organizacion_solicitud =?  GROUP BY YEAR(dp.fecha_creacion),MONTHNAME(dp.fecha_creacion),\n"
                    + "dp.id_tipo_servicio ORDER BY dp.fecha_creacion";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            rs = pst.executeQuery();
            //conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet Detalle_Usuario_Paquete_Laboral(String id) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.USUARIOS,SUM(T.total_consulta) AS 'TOTAL CONSULTAS' from(\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp inner join usuario u inner join pedido p\n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and u.id_usuario=dp.id_usuario_crea \n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea)\n"
                    + "UNION ALL\n"
                    + "select'Pedido Automatico' AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp inner join pedido p\n"
                    + "where dp.id_estado in (1,2)  and dp.id_pedido=p.id_pedido\n"
                    + "and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea))T\n"
                    + "GROUP BY (T.USUARIOS)";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, id);
            rs = pst.executeQuery();
            // conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Usuario Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ConsolidadoxEmpresa_Otros_Servicios(String id) {
        ResultSet rs = null;
        try {
            String consulta = "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join detalle_catalogo dc2 inner join pedido p \n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join detalle_catalogo dc2 inner join pedido p \n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido  and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, id);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle paquete" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet DetalleConsolidado_Otros_Servicios(String id) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT \n"
                    + "CONCAT(MONTHNAME(dp.fecha_creacion),\" - \",YEAR(dp.fecha_creacion))AS MES,dc.descripcion_larga as SERVICIO, COUNT(1) AS 'NRO DE REPORTES',\n"
                    + "po.precio_unitario AS 'COSTO UNITARIO',po.precio_unitario*COUNT(1) AS TOTAL,\n"
                    + "po.total_consultas-pos.consultas_disponible as 'CONSUMIDO',pos.consultas_disponible as 'DISPONIBLE'\n"
                    + "FROM gdp.detalle_pedido dp  inner join detalle_catalogo dc INNER JOIN paquete_organizacion_solicitud pos ON dp.id_paquete_organizacion_solicitud = pos.id_paquete_organizacion_solicitud\n"
                    + "INNER JOIN paquete_organizacion po inner join pedido p ON po.Id_paquete_organizacion = pos.id_paquete_organizacion\n"
                    + "WHERE dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and  dc.id_catalogo=6  and dc.codigo=dp.id_tipo_servicio and dp.id_paquete_organizacion_solicitud =?  GROUP BY YEAR(dp.fecha_creacion),MONTHNAME(dp.fecha_creacion),dp.id_tipo_servicio ORDER BY dp.fecha_creacion";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            rs = pst.executeQuery();
            //conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet Detalle_Usuario_Paquete_Otros_Servicios(String id) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.USUARIOS,SUM(T.total_consulta) AS 'TOTAL CONSULTAS' from(\n"
                    + "SELECT u.nombres as USUARIOS,count(*) AS total_consulta\n"
                    + "from detalle_pedido dp inner join usuario u\n"
                    + "on dp.id_usuario_crea=u.id_usuario\n"
                    + "where dp.id_estado in (1,2) and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea)\n"
                    + "UNION ALL\n"
                    + "select'Pedido Automatico' AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp \n"
                    + "where dp.id_estado in (1,2) and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea))T\n"
                    + "GROUP BY (T.USUARIOS)";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, id);
            rs = pst.executeQuery();
            // conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Usuario Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ConsolidadoxEmpresa_SinPaquete_Riesgos(String id, String desde, String hasta, String Servicio) {
        ResultSet rs = null;
        try {

            String consulta = "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA ,ifnull(REPLACE(REPLACE(dp.flag_antecedentes,'1','Sin Antecedentes'),'2','Con Antecedentes'),'Falta Responder') as ANTECEDENTES\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA ,ifnull(REPLACE(REPLACE(dp.flag_antecedentes,'1','Sin Antecedentes'),'2','Con Antecedentes'),'Falta Responder') as ANTECEDENTES\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?";

            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, Servicio);
            pst.setString(3, desde);
            pst.setString(4, hasta);
            pst.setString(5, id);
            pst.setString(6, Servicio);
            pst.setString(7, desde);
            pst.setString(8, hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
            if (!rs.next()) {
                Mensajes.msjMuestra("Sin datos");
            }

        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle de Empresa" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet DetalleConsolidado_SinPaquete_Riesgos(String id, String desde, String hasta, String Servicio) {
        ResultSet rs = null;
        try {
            String consulta = "select \n"
                    + "CONCAT(MONTHNAME(dp.fecha_creacion),\" - \",YEAR(dp.fecha_creacion))AS MES,dc.descripcion_larga as SERVICIO,count(*)as 'NRO DE REPORTES' ,\n"
                    + "dp.costo_servicio AS 'COSTO UNITARIO',dp.costo_servicio*count(*) AS TOTAL\n"
                    + "from detalle_pedido dp inner join pedido p inner join detalle_catalogo dc \n"
                    + "WHERE dp.id_estado in (1,2) and p.id_tipo_pedido='RR' and dp.id_pedido=p.id_pedido and p.id_organizacion=?\n"
                    + "and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and p.id_tipo_pedido=?\n"
                    + "and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY YEAR(dp.fecha_creacion),MONTHNAME(dp.fecha_creacion)  ORDER BY dp.fecha_creacion";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, Servicio);
            pst.setString(3, desde);
            pst.setString(4, hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet Detalle_Usuario_SinPaquete_Riesgos(String id, String desde, String hasta, String Servicio) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.USUARIOS,SUM(T.total_consulta) AS 'TOTAL CONSULTAS',SUM(T.positivos) AS POSITIVOS,SUM(T.negativos) AS NEGATIVOS from(\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join pedido p\n"
                    + "inner join detalle_catalogo dc2  where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea and dp.flag_antecedentes=1\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",0 as positivos,count(*) as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea and dp.flag_antecedentes=2\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea and dp.flag_antecedentes is null\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select'Pedido Automatico' AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos from detalle_pedido dp inner join detalle_catalogo dc  inner join pedido p\n"
                    + "inner join detalle_catalogo dc2  where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and dp.flag_antecedentes=1 and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",0 as positivos,count(*) as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad  and dp.flag_antecedentes=2 and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos from detalle_pedido dp inner join detalle_catalogo dc inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + "AND dc2.id_catalogo=8  and p.id_tipo_pedido='RR' and dc2.codigo=dp.id_localidad and dp.flag_antecedentes is null and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes)T\n"
                    + "GROUP BY (T.USUARIOS)";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, Servicio);
            pst.setString(3, desde);
            pst.setString(4, hasta);
            pst.setString(5, id);
            pst.setString(6, Servicio);
            pst.setString(7, desde);
            pst.setString(8, hasta);
            pst.setString(9, id);
            pst.setString(10, Servicio);
            pst.setString(11, desde);
            pst.setString(12, hasta);
            pst.setString(13, id);
            pst.setString(14, Servicio);
            pst.setString(15, desde);
            pst.setString(16, hasta);
            pst.setString(17, id);
            pst.setString(18, Servicio);
            pst.setString(19, desde);
            pst.setString(20, hasta);
            pst.setString(21, id);
            pst.setString(22, Servicio);
            pst.setString(23, desde);
            pst.setString(24, hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Usuario Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ConsolidadoxEmpresa_SinPaquete_Laboral(String id, String desde, String hasta, String Servicio) {
        ResultSet rs = null;

        try {

            String consulta = "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dc3.descripcion_corta as ESTADO,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join detalle_catalogo dc2 inner join pedido p inner join detalle_catalogo dc3\n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea\n"
                    + "and dc3.id_catalogo=16 and dc3.codigo=dp.id_estado_reporte\n"
                    + "and  p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dc3.descripcion_corta as ESTADO,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join detalle_catalogo dc2 inner join pedido p inner join detalle_catalogo dc3\n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido  and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and dp.id_usuario_crea=0\n"
                    + "and dc3.id_catalogo=16 and dc3.codigo=dp.id_estado_reporte\n"
                    + "and  p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,'En Proceso' as ESTADO,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join detalle_catalogo dc2 inner join pedido p \n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea\n"
                    + "and dp.id_estado_reporte is null\n"
                    + "and  p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?";

            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, Servicio);
            pst.setString(3, desde);
            pst.setString(4, hasta);
            pst.setString(5, id);
            pst.setString(6, Servicio);
            pst.setString(7, desde);
            pst.setString(8, hasta);
            pst.setString(9, id);
            pst.setString(10, Servicio);
            pst.setString(11, desde);
            pst.setString(12, hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());

        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle de Empresa" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet DetalleConsolidado_SinPaquete_Laboral(String id, String desde, String hasta, String Servicio) {
        ResultSet rs = null;
        try {
            String consulta = "select \n"
                    + "CONCAT(MONTHNAME(dp.fecha_creacion),\" - \",YEAR(dp.fecha_creacion))AS MES,dc.descripcion_larga as SERVICIO,count(*)as 'NRO DE REPORTES' ,\n"
                    + "dp.costo_servicio AS 'COSTO UNITARIO',dp.costo_servicio*count(*) AS TOTAL\n"
                    + "from detalle_pedido dp inner join pedido p inner join detalle_catalogo dc \n"
                    + "WHERE dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and p.id_organizacion=?\n"
                    + "and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and p.id_tipo_pedido= ?\n"
                    + "and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY YEAR(dp.fecha_creacion),MONTHNAME(dp.fecha_creacion)  ORDER BY dp.fecha_creacion";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, Servicio);
            pst.setString(3, desde);
            pst.setString(4, hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet Detalle_Usuario_SinPaquete_Laboral(String id, String desde, String hasta, String Servicio) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.USUARIOS,SUM(T.total_consulta) AS 'TOTAL CONSULTAS' from(\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp inner join usuario u inner join pedido p\n"
                    + "where dp.id_estado in (1,2)  and dp.id_pedido=p.id_pedido\n"
                    + "and u.id_usuario=dp.id_usuario_crea \n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea)\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + " and dc2.codigo=dp.id_localidad  and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea))T\n"
                    + "GROUP BY (T.USUARIOS)";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, Servicio);
            pst.setString(3, desde);
            pst.setString(4, hasta);
            pst.setString(5, id);
            pst.setString(6, Servicio);
            pst.setString(7, desde);
            pst.setString(8, hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Usuario Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ConsolidadoxEmpresa_SinPaquete_Otros_Servicios(String id, String desde, String hasta, String Servicio) {
        ResultSet rs = null;

        try {

            String consulta = "SELECT T.DNI,T.NOMBRES,T.SERVICIO,T.LOCALIDAD,T.MODALIDAD,T.FECHA,T.USUARIO,T.IMPORTE,T.TIEMPO_TRANSCURRIDO,T.RESPUESTA from(\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO_TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join detalle_catalogo dc2 inner join pedido p \n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 \n"
                    + "and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea and dp.apellido_paterno is not null\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO_TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join detalle_catalogo dc2 inner join pedido p \n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido  and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 and dc2.codigo=dp.id_localidad  and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,dp.razon_social_proveedor as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO_TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join usuario u inner join detalle_catalogo dc2 inner join pedido p \n"
                    + "where dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio AND dc2.id_catalogo=8 \n"
                    + "and dc2.codigo=dp.id_localidad  and u.id_usuario=dp.id_usuario_crea and dp.razon_social_proveedor is not null\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + ")T\n"
                    + "ORDER BY T.FECHA";

            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, Servicio);
            pst.setString(3, desde);
            pst.setString(4, hasta);
            pst.setString(5, id);
            pst.setString(6, Servicio);
            pst.setString(7, desde);
            pst.setString(8, hasta);
            pst.setString(9, id);
            pst.setString(10, Servicio);
            pst.setString(11, desde);
            pst.setString(12, hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());

        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle de Empresa" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet DetalleConsolidado_SinPaquete_Otros_Servicios(String id, String desde, String hasta, String Servicio) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT \n"
                    + "CONCAT(MONTHNAME(dp.fecha_creacion),\" - \",YEAR(dp.fecha_creacion))AS MES,dc.descripcion_larga as SERVICIO, COUNT(1) AS 'NRO DE REPORTES',\n"
                    + "dp.costo_servicio AS 'COSTO UNITARIO',dp.costo_servicio*COUNT(1) AS TOTAL\n"
                    + "FROM gdp.detalle_pedido dp  inner join detalle_catalogo dc  inner join pedido p\n"
                    + "WHERE dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido and  dc.id_catalogo=6  and dc.codigo=dp.id_tipo_servicio  \n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY YEAR(dp.fecha_creacion),MONTHNAME(dp.fecha_creacion),dp.id_tipo_servicio ORDER BY dp.fecha_creacion";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, Servicio);
            pst.setString(3, desde);
            pst.setString(4, hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet Detalle_Usuario_SinPaquete_Otros_Servicios(String id, String desde, String hasta, String Servicio) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.USUARIOS,SUM(T.total_consulta) AS 'TOTAL CONSULTAS' from(\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp inner join usuario u inner join pedido p\n"
                    + "where dp.id_estado in (1,2)  and dp.id_pedido=p.id_pedido\n"
                    + "and u.id_usuario=dp.id_usuario_crea \n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea)\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp inner join detalle_catalogo dc inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_pedido=p.id_pedido\n"
                    + " and dc2.codigo=dp.id_localidad  and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea))T\n"
                    + "GROUP BY (T.USUARIOS)";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, Servicio);
            pst.setString(3, desde);
            pst.setString(4, hasta);
            pst.setString(5, id);
            pst.setString(6, Servicio);
            pst.setString(7, desde);
            pst.setString(8, hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Usuario Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ArrayList<detalle_pedido> Busqueda_DNI(String dni, String servicio) {
        ArrayList<detalle_pedido> list = new ArrayList<>();
        String sql = "SELECT T.ID,T.ESTADO_DETALLE,T.ID_PEDIDO,T.ESTADO_PEDIDO,ifnull(T.PAQUETE,'') AS PAQUETE,T.EMPRESA,T.FECHA,T.NUMERO_DOCUMENTO,T.POSTULANTE,T.SERVICIO,T.MODALIDAD,\n"
                + "T.USUARIO FROM(\n"
                + "select dp.id_detalle_pedido AS ID,dp.id_estado as \"ESTADO_DETALLE\",p.id_pedido AS ID_PEDIDO,p.id_estado as \"ESTADO_PEDIDO\",dp.id_paquete_organizacion_solicitud as PAQUETE,\n"
                + "o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",CONCAT(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres)as POSTULANTE\n"
                + ",dc.descripcion_larga as SERVICIO,dp.modalidad_express AS MODALIDAD,u.nombres AS USUARIO\n"
                + "from detalle_pedido dp \n"
                + "inner join pedido p on dp.id_pedido=p.id_pedido inner join organizacion o  ON p.id_organizacion=o.id_organizacion inner join usuario u \n"
                + "ON dp.id_usuario_crea=u.id_usuario inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.id_estado in (1,2)\n"
                + "where dp.numero_documento=?  and p.id_tipo_pedido=?\n"
                + "UNION ALL\n"
                + "select dp.id_detalle_pedido AS ID,dp.id_estado as \"ESTADO_DETALLE\",p.id_pedido AS ID_PEDIDO,p.id_estado as \"ESTADO_PEDIDO\",dp.id_paquete_organizacion_solicitud as PAQUETE\n"
                + ",o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",CONCAT(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres)as POSTULANTE\n"
                + ",dc.descripcion_larga as SERVICIO,'NO' AS MODALIDAD,'Pedido Automatico' AS USUARIO\n"
                + "from detalle_pedido dp\n"
                + "inner join pedido p on dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido inner join organizacion o on p.id_organizacion=o.id_organizacion \n"
                + "inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio \n"
                + "where dp.id_usuario_crea=0 and dp.numero_documento=?  and p.id_tipo_pedido=?\n"
                + ")T";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, servicio);
            ps.setString(3, dni);
            ps.setString(4, servicio);
            rs = ps.executeQuery();
            while (rs.next()) {
                detalle_pedido vo = new detalle_pedido();
                vo.setId_detalle_pedido(rs.getInt("ID"));
                vo.setId_localidad(rs.getString("ESTADO_DETALLE"));
                vo.setId_pedido(rs.getInt("ID_PEDIDO"));
                vo.setId_tipo_servicio(rs.getString("ESTADO_PEDIDO"));
                vo.setNombre_archivo(rs.getString("PAQUETE"));
                vo.setApellido_paterno(rs.getString("EMPRESA"));
                vo.setFecha_creacion(rs.getTimestamp("FECHA"));
                System.out.println(rs.getString("FECHA"));
                vo.setNumero_documento(rs.getString("NUMERO_DOCUMENTO"));
                vo.setApellido_materno(rs.getString("POSTULANTE"));
                vo.setNombres(rs.getString("SERVICIO"));
                vo.setModalidad_express(rs.getString("MODALIDAD"));
                vo.setRegion(rs.getString("USUARIO"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public static ArrayList<detalle_pedido> Busqueda_DNI_2(String dni, String servicio) {
        ArrayList<detalle_pedido> list = new ArrayList<>();
        String sql = "SELECT T.ID,T.ESTADO_DETALLE,T.ID_PEDIDO,T.ESTADO_PEDIDO,ifnull(T.PAQUETE,'') AS PAQUETE,T.EMPRESA,T.FECHA,T.NUMERO_DOCUMENTO,T.POSTULANTE,T.SERVICIO,T.MODALIDAD,\n"
                + "T.USUARIO FROM(\n"
                + "select dp.id_detalle_pedido AS ID,dp.id_estado as \"ESTADO_DETALLE\",p.id_pedido AS ID_PEDIDO,p.id_estado as \"ESTADO_PEDIDO\",dp.id_paquete_organizacion_solicitud as PAQUETE\n"
                + ",o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",dp.razon_social_proveedor POSTULANTE\n"
                + ",dc.descripcion_larga as SERVICIO,'NO' AS MODALIDAD,u.nombres  AS USUARIO\n"
                + "from detalle_pedido dp \n"
                + "inner join pedido p on dp.id_estado in (1,2) AND dp.id_pedido=p.id_pedido inner join organizacion o \n"
                + "on p.id_organizacion=o.id_organizacion  inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                + "inner join detalle_catalogo dc on\n"
                + "dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio and dp.apellido_paterno is null\n"
                + "where dp.numero_documento=? and p.id_tipo_pedido=?\n"
                + "UNION ALL\n"
                + "select dp.id_detalle_pedido AS ID,dp.id_estado as \"ESTADO_DETALLE\",p.id_pedido AS ID_PEDIDO,p.id_estado as \"ESTADO_PEDIDO\",dp.id_paquete_organizacion_solicitud as PAQUETE,\n"
                + "o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",CONCAT(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres)as POSTULANTE\n"
                + ",dc.descripcion_larga as SERVICIO,dp.modalidad_express AS MODALIDAD,u.nombres AS USUARIO\n"
                + "from detalle_pedido dp \n"
                + "inner join pedido p on dp.id_estado in (1,2) AND dp.id_pedido=p.id_pedido inner join organizacion o \n"
                + "on p.id_organizacion=o.id_organizacion  inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                + "inner join detalle_catalogo dc on\n"
                + "dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio \n"
                + "where dp.numero_documento=? and p.id_tipo_pedido=?\n"
                + ")T\n"
                + "GROUP BY T.ID";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, servicio);
            ps.setString(3, dni);
            ps.setString(4, servicio);
            rs = ps.executeQuery();
            while (rs.next()) {
                detalle_pedido vo = new detalle_pedido();
                vo.setId_detalle_pedido(rs.getInt("ID"));
                vo.setId_localidad(rs.getString("ESTADO_DETALLE"));
                vo.setId_pedido(rs.getInt("ID_PEDIDO"));
                vo.setId_tipo_servicio(rs.getString("ESTADO_PEDIDO"));
                vo.setNombre_archivo(rs.getString("PAQUETE"));
                vo.setApellido_paterno(rs.getString("EMPRESA"));
                vo.setFecha_creacion(rs.getTimestamp("FECHA"));
                System.out.println(rs.getString("FECHA"));
                vo.setNumero_documento(rs.getString("NUMERO_DOCUMENTO"));
                vo.setApellido_materno(rs.getString("POSTULANTE"));
                vo.setNombres(rs.getString("SERVICIO"));
                vo.setModalidad_express(rs.getString("MODALIDAD"));
                vo.setRegion(rs.getString("USUARIO"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public void actualizarPedido(String idDetallePedido, String idPedido) {
        String sql = "update detalle_pedido dp inner join pedido p\n"
                + "on(dp.id_pedido=p.id_pedido)\n"
                + "set dp.id_estado='1' , p.id_estado='1'\n"
                + "where dp.id_detalle_pedido=?  and p.id_pedido=?";
        PreparedStatement ps = null;
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, idDetallePedido);
            ps.setString(2, idPedido);
            int actualizar = ps.executeUpdate();
            if (actualizar > 0) {
                Mensajes.msjMuestra("Pedido actualizado");
            } else {
                Mensajes.msjError("Error al actualizar Pedido");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void eliminarPedido(String idDetallePedido, String idPedido, String paquete) {
        
         ArrayList<detalle_pedido> list = new ArrayList<>();
        String sql = "SELECT SUM(T.PENDIENTE) AS PENDIENTE,SUM(T.ENTREGADOS) AS ENTREGADOS,SUM(T.TOTAL) AS TOTAL \n"
                + "FROM(\n"
                + "SELECT COUNT(*)as PENDIENTE,0 AS ENTREGADOS,COUNT(*) AS TOTAL FROM detalle_pedido dp where dp.id_pedido=?\n"
                + "AND dp.id_estado=1\n"
                + "UNION ALL\n"
                + "SELECT 0 as PENDIENTE,COUNT(*) AS ENTREGADOS,COUNT(*) AS TOTAL FROM detalle_pedido dp where dp.id_pedido=?\n"
                + "AND dp.id_estado=2)T";
        ResultSet rs = null;
        PreparedStatement ps = null;
        detalle_pedido vo = new detalle_pedido();
        
        try {
            ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, idPedido);
            rs = ps.executeQuery();
            while (rs.next()) {                
                vo.setId_detalle_pedido(rs.getInt("PENDIENTE"));
                vo.setId_pedido(rs.getInt("ENTREGADOS"));
                vo.setId_usuario_asignado(rs.getInt("TOTAL"));
                list.add(vo);
            }
            
            if (vo.getId_detalle_pedido() >= 1) {
                // SI HAY MAS DE UN PENDIENTE SE VERA SI HAY PAQUETE O NO
                // lo primero se cambia el estado a 3 al detalle_pedido
                Mensajes.msjMuestra("aqui se cambia de estado al detalle_pedido");                
            }
            if (paquete != null) {
                    // si hay numero de paquete se agrega 1 mas y se pone como aprobado
                    Mensajes.msjMuestra("Aqui ses aumenta 1 al paquete");
                }
            if(vo.getId_pedido()+1==vo.getId_usuario_asignado()){
                // se valida que todos los pedidos ya fueron entregados o que es el unico pedido pendiente
                // por tal caso se elimina el detalle_pedido y el pedido
                Mensajes.msjMuestra("Aqui se elimina el detalle pedido y el pedido");
            }
           
        } catch (Exception e) {
        }

    }
}
