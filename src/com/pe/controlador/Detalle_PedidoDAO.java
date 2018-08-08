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

}
