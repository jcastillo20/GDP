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
import com.pe.vista.FrmDialogFiltroPaquete;
import java.sql.Connection;
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
            Pool metodosPool = new Pool();
            Connection cn = null;
            String consulta = "SELECT T.DNI,T.NOMBRES,T.SERVICIO,T.LOCALIDAD,T.MODALIDAD,T.CENTRO_DE_COSTO,T.FECHA,\n"
                    + "T.USUARIO,T.IMPORTE,T.TIEMPO_TRANSCURRIDO,T.RESPUESTA,T.ANTECEDENTES FROM(\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,ifnull(dp.centro_costo,'') AS CENTRO_DE_COSTO,dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS TIEMPO_TRANSCURRIDO,dp.fecha_actualizacion AS RESPUESTA ,ifnull(REPLACE(REPLACE(dp.flag_antecedentes,'1','Sin Antecedentes'),'2','Con Antecedentes'),'Falta Responder') as ANTECEDENTES \n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc  on  dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "where dp.id_estado in (1,2)  and dc.id_catalogo=6  AND dc2.id_catalogo=8  \n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,ifnull(dp.centro_costo,'') AS CENTRO_DE_COSTO,dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS TIEMPO_TRANSCURRIDO,dp.fecha_actualizacion AS RESPUESTA ,ifnull(REPLACE(REPLACE(dp.flag_antecedentes,'1','Sin Antecedentes'),'2','Con Antecedentes'),'Falta Responder') as ANTECEDENTES \n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "where dp.id_estado in (1,2)  and dc.id_catalogo=6  AND dc2.id_catalogo=8  and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + ")T\n"
                    + "ORDER BY T.FECHA ASC;";
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
                    + "FROM gdp.detalle_pedido dp\n"
                    + "inner join pedido p ON dp.id_pedido=p.id_pedido  \n"
                    + "inner join detalle_catalogo dc ON  dp.id_tipo_servicio=dc.codigo\n"
                    + "INNER JOIN paquete_organizacion_solicitud pos ON dp.id_paquete_organizacion_solicitud = pos.id_paquete_organizacion_solicitud\n"
                    + "INNER JOIN paquete_organizacion po ON po.Id_paquete_organizacion = pos.id_paquete_organizacion\n"
                    + "WHERE dp.id_estado in (1,2) and  dc.id_catalogo=6 and dp.id_paquete_organizacion_solicitud =?\n"
                    + "GROUP BY YEAR(dp.fecha_creacion),MONTHNAME(dp.fecha_creacion),dp.id_tipo_servicio ORDER BY dp.fecha_creacion;";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
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
                    + ",count(*) as positivos,0 as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido and p.id_tipo_pedido='RR'\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join usuario u on dp.id_usuario_crea\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6\n"
                    + "and u.id_usuario=dp.id_usuario_crea and dp.flag_antecedentes=1\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",0 as positivos,count(*) as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido and p.id_tipo_pedido='RR'\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join usuario u on dp.id_usuario_crea\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6\n"
                    + "and u.id_usuario=dp.id_usuario_crea and dp.flag_antecedentes=2\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido and p.id_tipo_pedido='RR'\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join usuario u on dp.id_usuario_crea\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and u.id_usuario=dp.id_usuario_crea and dp.flag_antecedentes is null\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select'Pedido Automatico' AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido and p.id_tipo_pedido='RR'\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.flag_antecedentes=1 and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",0 as positivos,count(*) as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido and p.id_tipo_pedido='RR'\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.flag_antecedentes=2 and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc \n"
                    + "inner join pedido p\n"
                    + "inner join detalle_catalogo dc2 \n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.flag_antecedentes is null and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes)T\n"
                    + "GROUP BY (T.USUARIOS);";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, id);
            pst.setString(4, id);
            pst.setString(5, id);
            pst.setString(6, id);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Usuario Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ConsolidadoxEmpresa_Laboral(String id) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.DNI,T.NOMBRES,T.SERVICIO,T.LOCALIDAD,T.MODALIDAD,T.ESTADO,T.CENTRO_DE_COSTO,\n"
                    + "T.FECHA,T.USUARIO,T.IMPORTE,T.TIEMPO_TRANSCURRIDO,T.RESPUESTA FROM(\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dc3.descripcion_corta as ESTADO,ifnull(dp.centro_costo,'') AS CENTRO_DE_COSTO,dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS TIEMPO_TRANSCURRIDO,dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp  \n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "inner join detalle_catalogo dc3 on dp.id_estado_reporte=dc3.codigo\n"
                    + "where dp.id_estado in (1,2) and dc.id_catalogo=6 AND dc2.id_catalogo=8\n"
                    + "and dc3.id_catalogo=16 and dc3.codigo=dp.id_estado_reporte\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dc3.descripcion_corta as ESTADO,ifnull(dp.centro_costo,'') AS CENTRO_DE_COSTO,dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS TIEMPO_TRANSCURRIDO,dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "inner join detalle_catalogo dc3 on dp.id_estado_reporte=dc3.codigo\n"
                    + "where dp.id_estado in (1,2)  and dc.id_catalogo=6 AND dc2.id_catalogo=8 and dp.id_usuario_crea=0\n"
                    + "and dc3.id_catalogo=16\n"
                    + "and dp.id_paquete_organizacion_solicitud=?)T ORDER BY T.FECHA ASC";
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
                    + "FROM detalle_pedido dp  \n"
                    + "inner join detalle_catalogo dc on dc.codigo=dp.id_tipo_servicio\n"
                    + "INNER JOIN paquete_organizacion_solicitud pos ON dp.id_paquete_organizacion_solicitud = pos.id_paquete_organizacion_solicitud\n"
                    + "INNER JOIN paquete_organizacion po on po.Id_paquete_organizacion = pos.id_paquete_organizacion\n"
                    + "inner join pedido p ON dp.id_pedido=p.id_pedido \n"
                    + "WHERE dp.id_estado in (1,2) and  dc.id_catalogo=6 and \n"
                    + "dp.id_paquete_organizacion_solicitud =133  GROUP BY YEAR(dp.fecha_creacion),MONTHNAME(dp.fecha_creacion),\n"
                    + "dp.id_tipo_servicio ORDER BY dp.fecha_creacion";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
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
                    + "from detalle_pedido dp \n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "where dp.id_estado in (1,2) \n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea)\n"
                    + "UNION ALL\n"
                    + "select'Pedido Automatico' AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp \n"
                    + "where dp.id_estado in (1,2) \n"
                    + "and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "GROUP BY (dp.id_usuario_crea))T\n"
                    + "GROUP BY (T.USUARIOS)";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, id);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Usuario Consolidado" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ConsolidadoxEmpresa_Otros_Servicios(String id) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.DNI,T.NOMBRES,T.SERVICIO,T.LOCALIDAD,T.MODALIDAD,T.FECHA,T.FECHA,T.USUARIO,T.IMPORTE,T.TIEMPO_TRANSCURRIDO,T.RESPUESTA FROM(\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS TIEMPO_TRANSCURRIDO,dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "where dp.id_estado in (1,2) and dc.id_catalogo=6 AND dc2.id_catalogo=8\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS TIEMPO_TRANSCURRIDO,dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "where dp.id_estado in (1,2)  and dc.id_catalogo=6 AND dc2.id_catalogo=8 and dp.id_usuario_crea=0\n"
                    + "and dp.id_paquete_organizacion_solicitud=?\n"
                    + ")T ORDER BY T.FECHA ASC";
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
                    + "FROM gdp.detalle_pedido dp  \n"
                    + "inner join detalle_catalogo dc on  dp.id_tipo_servicio=dc.codigo\n"
                    + "INNER JOIN paquete_organizacion_solicitud pos ON dp.id_paquete_organizacion_solicitud = pos.id_paquete_organizacion_solicitud\n"
                    + "INNER JOIN paquete_organizacion po ON pos.id_paquete_organizacion=po.Id_paquete_organizacion\n"
                    + "WHERE dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.id_paquete_organizacion_solicitud =?\n"
                    + " GROUP BY YEAR(dp.fecha_creacion),MONTHNAME(dp.fecha_creacion),dp.id_tipo_servicio ORDER BY dp.fecha_creacion";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, id);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
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
            conexion.CerrarBD(conexion.Conexion());
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
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido \n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6\n"
                    + "AND dc2.id_catalogo=8 and p.id_organizacion=? and \n"
                    + "p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA ,ifnull(REPLACE(REPLACE(dp.flag_antecedentes,'1','Sin Antecedentes'),'2','Con Antecedentes'),'Falta Responder') as ANTECEDENTES\n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido \n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "AND dc2.id_catalogo=8  and dp.id_usuario_crea=0\n"
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
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "WHERE dp.id_estado in (1,2) and p.id_organizacion=?\n"
                    + "and dc.id_catalogo=6 and p.id_tipo_pedido=?\n"
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
                    + ",count(*) as positivos,0 as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo \n"
                    + "inner join usuario u on dp.id_usuario_crea\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.flag_antecedentes=1\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",0 as positivos,count(*) as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo \n"
                    + "inner join usuario u on dp.id_usuario_crea\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.flag_antecedentes=2\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select u.nombres AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo \n"
                    + "inner join usuario u on dp.id_usuario_crea\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.flag_antecedentes is null\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select'Pedido Automatico' AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.flag_antecedentes=1 and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",0 as positivos,count(*) as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.flag_antecedentes=2 and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea),dp.flag_antecedentes\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + ",count(*) as positivos,0 as negativos \n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and dp.flag_antecedentes is null and dp.id_usuario_crea=0\n"
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
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc3 on dp.id_estado_reporte=dc3.codigo\n"
                    + "where dp.id_estado in (1,2) and dc.id_catalogo=6 AND dc2.id_catalogo=8 and dc3.id_catalogo=16\n"
                    + "and  p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dc3.descripcion_corta as ESTADO,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc3 on dp.id_estado_reporte=dc3.codigo\n"
                    + "where dp.id_estado in (1,2) and dc.id_catalogo=6  AND dc2.id_catalogo=8 and dp.id_usuario_crea=0\n"
                    + "and dc3.id_catalogo=16 \n"
                    + "and  p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,'En Proceso' as ESTADO,ifnull(dp.centro_costo,'') AS 'CENTRO DE COSTO',dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo \n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2) and dc.id_catalogo=6 AND dc2.id_catalogo=8 and dp.id_estado_reporte is null\n"
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
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "WHERE dp.id_estado in (1,2) and p.id_organizacion=?\n"
                    + "and dc.id_catalogo=6 and p.id_tipo_pedido= ?\n"
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
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 \n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea)\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "inner join detalle_catalogo dc2 on dp.id_localidad=dc2.codigo\n"
                    + "where dp.id_estado in (1,2) and  dc.id_catalogo=6 and dp.id_usuario_crea=0\n"
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
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc ON dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join usuario u ON dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join detalle_catalogo dc2 ON dp.id_localidad=dc2.codigo\n"
                    + "inner join pedido p ON dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2)  and dc.id_catalogo=6 AND dc2.id_catalogo=8 \n"
                    + "and dp.apellido_paterno is not null\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,CONCAT(dp.apellido_paterno,\" \",dp.apellido_materno,\" \",dp.nombres) as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dp.fecha_creacion AS FECHA,'Pedido Automatico' AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO_TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc ON dp.id_tipo_servicio=dc.codigo \n"
                    + "inner join detalle_catalogo dc2  ON dp.id_localidad=dc2.codigo\n"
                    + "inner join pedido p ON dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2) and dc.id_catalogo=6 AND dc2.id_catalogo=8 and dp.id_usuario_crea=0\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "UNION ALL\n"
                    + "select dp.numero_documento as DNI ,dp.razon_social_proveedor as NOMBRES,ifnull(dc.descripcion_larga,'') AS SERVICIO,\n"
                    + "ifnull(dc2.descripcion_larga,'') AS LOCALIDAD,IFNULL(dp.modalidad_express,'') AS MODALIDAD,dp.fecha_creacion AS FECHA  ,ifnull(u.nombres,'') AS 'USUARIO',ifnull(dp.costo_servicio,'')\n"
                    + "AS IMPORTE,IFNULL(TIMEDIFF(dp.fecha_actualizacion,dp.fecha_creacion),'') AS 'TIEMPO_TRANSCURRIDO',dp.fecha_actualizacion AS RESPUESTA\n"
                    + "from detalle_pedido dp \n"
                    + "inner join detalle_catalogo dc ON dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join usuario u ON dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join detalle_catalogo dc2 ON dp.id_localidad=dc2.codigo\n"
                    + "inner join pedido ON dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2) and dc.id_catalogo=6 AND dc2.id_catalogo=8 \n"
                    + "and dp.razon_social_proveedor is not null\n"
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
                    + "FROM gdp.detalle_pedido dp  \n"
                    + "inner join detalle_catalogo dc  on dp.id_tipo_servicio=dc.codigo\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "WHERE dp.id_estado in (1,2)  and  dc.id_catalogo=6 \n"
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
                    + "from detalle_pedido dp \n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2)\n"
                    + "and p.id_organizacion=? and p.id_tipo_pedido=? and dp.fecha_creacion BETWEEN ? and ?\n"
                    + "GROUP BY (dp.id_usuario_crea)\n"
                    + "UNION ALL\n"
                    + "select 'Pedido Automatico'  AS USUARIOS,COUNT(*) AS total_consulta\n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on  dp.id_pedido=p.id_pedido\n"
                    + "where dp.id_estado in (1,2)  and dp.id_usuario_crea=0\n"
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
        String sql = "SELECT T.ID,T.ESTADO_DETALLE,T.ID_PEDIDO,T.ESTADO_PEDIDO,ifnull(T.PAQUETE,0) AS PAQUETE,T.EMPRESA,T.FECHA,T.NUMERO_DOCUMENTO,T.POSTULANTE,T.SERVICIO,T.MODALIDAD,\n"
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
                + ")T ORDER BY T.FECHA ASC";
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
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public static ArrayList<detalle_pedido> Busqueda_DNI_2(String dni, String servicio) {
        ArrayList<detalle_pedido> list = new ArrayList<>();
        String sql = "SELECT T.ID,T.ESTADO_DETALLE,T.ID_PEDIDO,T.ESTADO_PEDIDO,ifnull(T.PAQUETE,0) AS PAQUETE,T.EMPRESA,T.FECHA,T.NUMERO_DOCUMENTO,T.POSTULANTE,T.SERVICIO,T.MODALIDAD,\n"
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
                + "GROUP BY T.ID ORDER BY T.FECHA ASC";
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
                conexion.CerrarBD(conexion.Conexion());
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
            conexion.CerrarBD(conexion.Conexion());
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
        ResultSet rs = null;;
        detalle_pedido vo = new detalle_pedido();

        try {
            PreparedStatement ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, idPedido);
            ps.setString(2, idPedido);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Paso 1");

                if (!rs.getObject(1).equals(1)) {
                    // SI HAY MAS DE UN PENDIENTE SE VERA SI HAY PAQUETE O NO
                    // lo primero se cambia el estado a 3 al detalle_pedido
                    System.out.println("aqui se cambia de estado al detalle_pedido" + rs.getObject(1));
                    this.EliminarDetallePedido(idDetallePedido);
                }
                if (paquete.equals("0") || paquete.equals("")) {

                } else {
                    // si hay numero de paquete se agrega 1 mas y se pone como aprobado
                    System.out.println("Aqui ses aumenta 1 al paquete" + paquete);
                    this.AumentarPaquete(paquete);
                }

                if (rs.getInt(2) + 1 == rs.getInt(3)) {
                    // se valida que todos los pedidos ya fueron entregados o que es el unico pedido pendiente
                    // por tal caso se elimina el detalle_pedido y el pedido
                    System.out.println("Se cambia el pedido a entregado" + idPedido);
                    this.PedidoEntregado(idPedido);
                }
                if (rs.getInt(1) == rs.getInt(3)) {
                    // se valida que todos los pedidos ya fueron entregados o que es el unico pedido pendiente
                    // por tal caso se elimina el detalle_pedido y el pedido
                    System.out.println("Se cambia el pedido a cancelado" + idPedido);
                    this.CancelarPedido(idPedido);
                }
                Mensajes.msjMuestra("Pedido Eliminado");
            }
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
        }

    }

    public void EliminarDetallePedido(String iddetallePedido) {
        String sql = "update detalle_pedido dp set dp.id_estado='3',dp.id_paquete_organizacion_solicitud=null,\n"
                + "dp.id_usuario_asignado=null\n"
                + "where dp.id_detalle_pedido=?";
        try {
            PreparedStatement ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, iddetallePedido);
            ps.executeUpdate();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
        }
    }

    public void AumentarPaquete(String idpaquete) {
        String sql = "update paquete_organizacion_solicitud pos\n"
                + "set pos.consultas_disponible=pos.consultas_disponible+1\n"
                + "where pos.id_paquete_organizacion_solicitud=?";
        try {
            PreparedStatement ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, idpaquete);
            ps.executeUpdate();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {

        }
    }

    public void PedidoEntregado(String idpedido) {
        String sql = "update pedido p\n"
                + "set p.id_estado='2'\n"
                + "where p.id_pedido=?";
        try {
            PreparedStatement ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, idpedido);
            ps.executeUpdate();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {

        }
    }

    public void CancelarPedido(String idpedido) {
        String sql = "update pedido p\n"
                + "set p.id_estado='5'\n"
                + "where p.id_pedido=?";
        try {
            PreparedStatement ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, idpedido);
            ps.executeUpdate();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {

        }
    }

    public void InsertarComentario(String id, String fecha, String comentario, String pedido) {
        String sql = "update detalle_pedido dp\n"
                + "set dp.id_usuario_actualiza=? , dp.fecha_actualizacion=?,dp.comentario=?\n"
                + "where dp.id_detalle_pedido=?";

        try {
            PreparedStatement ps = conexion.Conexion().prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, fecha);
            ps.setString(3, comentario);
            ps.setString(4, pedido);
            System.out.println("" + id);
            System.out.println("" + fecha);
            System.out.println("" + comentario);
            System.out.println("" + pedido);
            ps.executeUpdate();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {

        }
    }

    public static ResultSet ReportesActualizados(String tipo, String desde, String Hasta) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.EMPRESA,T.FECHA,T.NUMERO_DOCUMENTO,T.POSTULANTE,T.USUARIO,\n"
                    + "T.COMENTARIO,T.USUARIO_ACTUALIZA FROM(\n"
                    + "select dp.id_detalle_pedido AS ID,o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                    + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",CONCAT(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres)as POSTULANTE\n"
                    + ",u.nombres AS USUARIO,dp.comentario as COMENTARIO, u1.nombres as \"USUARIO_ACTUALIZA\"\n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido \n"
                    + "inner join organizacion o  ON p.id_organizacion=o.id_organizacion \n"
                    + "inner join usuario u ON dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join usuario u1 ON dp.id_usuario_actualiza=u1.id_usuario \n"
                    + "inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio\n"
                    + "where dp.comentario is not null and dp.id_estado in (1,2) and p.id_tipo_pedido=?\n"
                    + "and dp.fecha_creacion BETWEEN ? AND ?\n"
                    + "UNION ALL\n"
                    + "select dp.id_detalle_pedido AS ID,o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                    + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",CONCAT(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres)as POSTULANTE\n"
                    + ",'Pedido Automatico' AS USUARIO,dp.comentario as COMENTARIO, u.nombres as \"USUARIO_ACTUALIZA\"\n"
                    + "from detalle_pedido dp\n"
                    + "inner join pedido p on dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido \n"
                    + "inner join organizacion o on p.id_organizacion=o.id_organizacion \n"
                    + "inner join usuario u ON dp.id_usuario_actualiza=u.id_usuario \n"
                    + "inner join usuario u1 ON dp.id_usuario_actualiza=u1.id_usuario \n"
                    + "inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio \n"
                    + "where dp.id_usuario_crea=0 and dp.comentario is not null and dp.id_estado in (1,2)  and p.id_tipo_pedido=?\n"
                    + "and dp.fecha_creacion BETWEEN ? AND ?\n"
                    + ")T ORDER BY T.FECHA ASC";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, tipo);
            pst.setString(2, desde);
            pst.setString(3, Hasta);
            pst.setString(4, tipo);
            pst.setString(5, desde);
            pst.setString(6, Hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle de Reportes Actualizados" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ReportesActualizados_2(String tipo, String desde, String Hasta) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.EMPRESA,T.FECHA,T.NUMERO_DOCUMENTO,T.POSTULANTE,T.USUARIO,\n"
                    + "    T.COMENTARIO,T.USUARIO_ACTUALIZA FROM(\n"
                    + "    select dp.id_detalle_pedido AS ID,o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                    + "    dp.numero_documento AS \"NUMERO_DOCUMENTO\",dp.razon_social_proveedor POSTULANTE\n"
                    + "    ,u.nombres  AS USUARIO,dp.comentario as COMENTARIO, u1.nombres as \"USUARIO_ACTUALIZA\"\n"
                    + "    from detalle_pedido dp \n"
                    + "    inner join pedido p on dp.id_estado in (1,2) AND dp.id_pedido=p.id_pedido \n"
                    + "    inner join organizacion o on p.id_organizacion=o.id_organizacion  \n"
                    + "    inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "    inner join usuario u1 ON dp.id_usuario_actualiza=u1.id_usuario \n"
                    + "    inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio \n"
                    + "    where dp.comentario is not null and dp.id_estado in (1,2) and dp.apellido_paterno is null and p.id_tipo_pedido=?\n"
                    + "    and dp.fecha_creacion BETWEEN ? AND ?\n"
                    + "    UNION ALL\n"
                    + "    select dp.id_detalle_pedido AS ID,o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                    + "    dp.numero_documento AS \"NUMERO_DOCUMENTO\",CONCAT(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres)as POSTULANTE\n"
                    + "    ,u.nombres AS USUARIO,dp.comentario as COMENTARIO, u1.nombres as \"USUARIO_ACTUALIZA\"\n"
                    + "    from detalle_pedido dp \n"
                    + "    inner join pedido p on dp.id_estado in (1,2) AND dp.id_pedido=p.id_pedido \n"
                    + "    inner join organizacion o on p.id_organizacion=o.id_organizacion  \n"
                    + "    inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "    inner join usuario u1 ON dp.id_usuario_actualiza=u1.id_usuario \n"
                    + "    inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio \n"
                    + "    where dp.comentario is not null and dp.id_estado in (1,2) and p.id_tipo_pedido=?\n"
                    + "    and dp.fecha_creacion BETWEEN ? AND ?\n"
                    + "    )T\n"
                    + "    GROUP BY T.ID ORDER BY T.FECHA ASC";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, tipo);
            pst.setString(2, desde);
            pst.setString(3, Hasta);
            pst.setString(4, tipo);
            pst.setString(5, desde);
            pst.setString(6, Hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle de Reportes Actualizados" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ReportesEliminados(String tipo, String desde, String Hasta) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.EMPRESA,T.FECHA,T.NUMERO_DOCUMENTO,T.POSTULANTE,T.USUARIO,\n"
                    + "T.COMENTARIO,T.USUARIO_ACTUALIZA FROM(\n"
                    + "select dp.id_detalle_pedido AS ID,o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                    + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",CONCAT(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres)as POSTULANTE\n"
                    + ",u.nombres AS USUARIO,dp.comentario as COMENTARIO, u1.nombres as \"USUARIO_ACTUALIZA\"\n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido \n"
                    + "inner join organizacion o  ON p.id_organizacion=o.id_organizacion \n"
                    + "inner join usuario u ON dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join usuario u1 ON dp.id_usuario_actualiza=u1.id_usuario \n"
                    + "inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio\n"
                    + "where dp.comentario is not null and dp.id_estado in (3) and p.id_tipo_pedido=?\n"
                    + "and dp.fecha_creacion BETWEEN ? AND ?\n"
                    + "UNION ALL\n"
                    + "select dp.id_detalle_pedido AS ID,o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                    + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",CONCAT(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres)as POSTULANTE\n"
                    + ",'Pedido Automatico' AS USUARIO,dp.comentario as COMENTARIO, u.nombres as \"USUARIO_ACTUALIZA\"\n"
                    + "from detalle_pedido dp\n"
                    + "inner join pedido p on dp.id_estado in (1,2) and dp.id_pedido=p.id_pedido \n"
                    + "inner join organizacion o on p.id_organizacion=o.id_organizacion \n"
                    + "inner join usuario u ON dp.id_usuario_actualiza=u.id_usuario \n"
                    + "inner join usuario u1 ON dp.id_usuario_actualiza=u1.id_usuario \n"
                    + "inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio \n"
                    + "where dp.id_usuario_crea=0 and dp.comentario is not null and dp.id_estado in (3)  and p.id_tipo_pedido=?\n"
                    + "and dp.fecha_creacion BETWEEN ? AND ?\n"
                    + ")T ORDER BY T.FECHA ASC";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, tipo);
            pst.setString(2, desde);
            pst.setString(3, Hasta);
            pst.setString(4, tipo);
            pst.setString(5, desde);
            pst.setString(6, Hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle de Reportes Eliminados" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet ReportesEliminados_2(String tipo, String desde, String Hasta) {
        ResultSet rs = null;
        try {
            String consulta = "SELECT T.EMPRESA,T.FECHA,T.NUMERO_DOCUMENTO,T.POSTULANTE,T.USUARIO,\n"
                    + "T.COMENTARIO,T.USUARIO_ACTUALIZA FROM(\n"
                    + "select dp.id_detalle_pedido AS ID,o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                    + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",dp.razon_social_proveedor POSTULANTE\n"
                    + ",u.nombres  AS USUARIO,dp.comentario as COMENTARIO, u1.nombres as \"USUARIO_ACTUALIZA\"\n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on  dp.id_pedido=p.id_pedido \n"
                    + "inner join organizacion o on p.id_organizacion=o.id_organizacion  \n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join usuario u1 ON dp.id_usuario_actualiza=u1.id_usuario \n"
                    + "inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio \n"
                    + "where dp.comentario is not null and dp.id_estado in (3) and dp.apellido_paterno is null and p.id_tipo_pedido=?\n"
                    + "and dp.fecha_creacion BETWEEN ? AND ?\n"
                    + "UNION ALL\n"
                    + "select dp.id_detalle_pedido AS ID,o.razon_social as EMPRESA,dp.fecha_creacion AS FECHA,\n"
                    + "dp.numero_documento AS \"NUMERO_DOCUMENTO\",CONCAT(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres)as POSTULANTE\n"
                    + ",u.nombres AS USUARIO,dp.comentario as COMENTARIO, u1.nombres as \"USUARIO_ACTUALIZA\"\n"
                    + "from detalle_pedido dp \n"
                    + "inner join pedido p on  dp.id_pedido=p.id_pedido \n"
                    + "inner join organizacion o on p.id_organizacion=o.id_organizacion  \n"
                    + "inner join usuario u on dp.id_usuario_crea=u.id_usuario\n"
                    + "inner join usuario u1 ON dp.id_usuario_actualiza=u1.id_usuario \n"
                    + "inner join detalle_catalogo dc on dc.id_catalogo=6 and dc.codigo=dp.id_tipo_servicio \n"
                    + "where dp.comentario is not null and dp.id_estado in (3) and p.id_tipo_pedido=?\n"
                    + "and dp.fecha_creacion BETWEEN ? AND ?\n"
                    + ")T\n"
                    + "GROUP BY T.ID ORDER BY T.FECHA ASC";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, tipo);
            pst.setString(2, desde);
            pst.setString(3, Hasta);
            pst.setString(4, tipo);
            pst.setString(5, desde);
            pst.setString(6, Hasta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("Error al Listar Detalle de Reportes Eliminados" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet REPETIDOS() {
        ResultSet rs = null;
        try {
            String consulta = "SELECT dp.apellido_paterno,dp.apellido_materno,dp.nombres,ifnull(dp.cargo_postula,\"\")as '2do NOMBRE',dp.numero_documento,\n"
                    + "concat(dp.apellido_paterno,' ',dp.apellido_materno,' ',dp.nombres) as NOMBRES_COMPLETOS,\n"
                    + "date_format(dp.fecha_creacion,'%d/%m/%y')as FECHA,o.razon_social,ifnull(REPLACE(REPLACE(dp.flag_antecedentes,'1','Sin Antecedentes'),'2','Con Antecedentes'),'Falta Responder') as ANTECEDENTES\n"
                    + "FROM detalle_pedido dp\n"
                    + "inner join pedido p on dp.id_pedido=p.id_pedido and p.id_tipo_pedido='RR'\n"
                    + "inner join organizacion o on p.id_organizacion=o.id_organizacion\n"
                    + "where dp.id_estado in (1,2)\n"
                    + "ORDER BY dp.fecha_creacion ASC";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjError("REPETIDOS" + e.getMessage());
        }
        return rs;
    }

}
