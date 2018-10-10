/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.controlador;

import com.pe.extras.Mensajes;
import com.pe.modelo.usuario;
import com.pe.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author JuanCR
 */
public class UsuarioDAO {
    
    public static int inLogin(usuario ou) {
        int valor = 0;
        try {
            String consulta = "Select id_usuario from usuario where username=? and password=?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, ou.getUsername());
            pst.setString(2, ou.getPassword());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                valor = 1;
            }
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception ex) {
            Mensajes.msjMuestra(ex.getMessage());
        }
        return valor;
    }

    public static String BuscarID(String usuario) {
        String id_usuario = null;
        try {
            String consulta = "Select CONCAT(id_usuario , ';' ,nombres,';',apellido_paterno,';',apellido_materno) as DATOS_PERSONA from usuario where username=?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, usuario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id_usuario = String.valueOf(rs.getString("DATOS_PERSONA"));
            }
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjMuestra(e.getMessage());
        }
        return id_usuario;
    }

    public static ArrayList<usuario> ObtenerDatosUsuario(String usuario) {
        ArrayList lst = new ArrayList();
        try {
            String consulta = "Select id_usuario,nombres,apellido_paterno,apellido_materno from usuario where username=?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, usuario);
            ResultSet rs = pst.executeQuery();
            usuario usu;

            while (rs.next()) {
                usu = new usuario();
                usu.setId_usuario(rs.getInt("id_usuario"));
                usu.setNombres(rs.getString("nombres"));
                usu.setApellido_paterno(rs.getString("apellido_paterno"));
                usu.setApellido_materno(rs.getString("apellido_materno"));
                lst.add(usu);
            }
        } catch (Exception e) {

        } finally {
            try {
                conexion.CerrarBD(conexion.Conexion());
            } catch (Exception ex) {
            }
        }
        return lst;
    }

    public static ResultSet getUsuario() {
        ResultSet rs = null;

        try {
            String consulta = "select usu.id_usuario AS ID,usu.numero_documento AS DNI,usu.nombres ,usu.apellido_paterno AS EMPRESA,\n"
                    + "usu.apellido_materno AS CARGO,dc.descripcion_corta AS SEXO,IFNULL(usu.fecha_nacimiento,'') AS 'F. NACIMIENTO',\n"
                    + "usu.username ,usu.email ,usu.telefono_fijo ,usu.telefono_celular,usu.id_tipo_documento,usu.id_tipo_genero,\n"
                    + "usu.flag_cambio_password,usu.id_tipo_usuario,usu.codigo_color,usu.es_moderador_chat,up.id_perfil,up.id_estado  from usuario usu inner join detalle_catalogo dc\n"
                    + "inner join usuario_perfil up where usu.id_estado='1' AND up.id_usuario=usu.id_usuario AND dc.id_catalogo='3' AND usu.id_tipo_genero=dc.codigo";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            rs = pst.executeQuery();

            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjMuestra("Error al Listar Usuario" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet getxUsuario(String usuario) {
        usuario u = new usuario();
        ResultSet rs = null;
        try {
            String consulta = "select usu.id_usuario AS ID,usu.numero_documento AS DNI,usu.nombres ,usu.apellido_paterno AS EMPRESA,\n"
                    + "usu.apellido_materno AS CARGO,dc.descripcion_corta AS SEXO,IFNULL(usu.fecha_nacimiento,'') AS 'F. NACIMIENTO',\n"
                    + "usu.username ,usu.email ,usu.telefono_fijo ,usu.telefono_celular,usu.id_tipo_documento,usu.id_tipo_genero,\n"
                    + "usu.flag_cambio_password,usu.id_tipo_usuario,usu.codigo_color,usu.es_moderador_chat,up.id_perfil,up.id_estado  from usuario usu inner join detalle_catalogo dc\n"
                    + "inner join usuario_perfil up where usu.id_estado='1' AND up.id_usuario=usu.id_usuario AND dc.id_catalogo='3' AND usu.id_tipo_genero=dc.codigo AND usu.username LIKE ?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, "%" + usuario + "%");
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjMuestra("Error al Listar por Usuario" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet getXDocumento(String documento) {
        ResultSet rs = null;
        try {
            String consulta = "select usu.id_usuario AS ID,usu.numero_documento AS DNI,usu.nombres ,usu.apellido_paterno AS EMPRESA,\n"
                    + "usu.apellido_materno AS CARGO,dc.descripcion_corta AS SEXO,IFNULL(usu.fecha_nacimiento,'') AS 'F. NACIMIENTO',\n"
                    + "usu.username ,usu.email ,usu.telefono_fijo ,usu.telefono_celular,usu.id_tipo_documento,usu.id_tipo_genero,\n"
                    + "usu.flag_cambio_password,usu.id_tipo_usuario,usu.codigo_color,usu.es_moderador_chat,up.id_perfil,up.id_estado  from usuario usu inner join detalle_catalogo dc\n"
                    + "inner join usuario_perfil up where usu.id_estado='1' AND up.id_usuario=usu.id_usuario AND dc.id_catalogo='3' AND usu.id_tipo_genero=dc.codigo AND usu.numero_documento LIKE ?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, "%" + documento + "%");
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjMuestra("Error al Listar por Nro° Documento" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet getXNombres(String nombres) {
        ResultSet rs = null;
        try {
            String consulta = "select usu.id_usuario AS ID,usu.numero_documento AS DNI,usu.nombres ,usu.apellido_paterno AS EMPRESA,\n"
                    + "usu.apellido_materno AS CARGO,dc.descripcion_corta AS SEXO,IFNULL(usu.fecha_nacimiento,'') AS 'F. NACIMIENTO',\n"
                    + "usu.username ,usu.email ,usu.telefono_fijo ,usu.telefono_celular,usu.id_tipo_documento,usu.id_tipo_genero,\n"
                    + "usu.flag_cambio_password,usu.id_tipo_usuario,usu.codigo_color,usu.es_moderador_chat,up.id_perfil,up.id_estado  from usuario usu inner join detalle_catalogo dc\n"
                    + "inner join usuario_perfil up where usu.id_estado='1' AND up.id_usuario=usu.id_usuario AND dc.id_catalogo='3' AND usu.id_tipo_genero=dc.codigo AND usu.nombres LIKE ?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, "%" + nombres + "%");
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjMuestra("Error al Listar por Nombres" + e.getMessage());
        }
        return rs;
    }

    public static ResultSet getXEmpresa(String empresa) {
        ResultSet rs = null;
        try {
            String consulta = "select usu.id_usuario AS ID,usu.numero_documento AS DNI,usu.nombres ,usu.apellido_paterno AS EMPRESA,\n"
                    + "usu.apellido_materno AS CARGO,dc.descripcion_corta AS SEXO,IFNULL(usu.fecha_nacimiento,'') AS 'F. NACIMIENTO',\n"
                    + "usu.username ,usu.email ,usu.telefono_fijo ,usu.telefono_celular,usu.id_tipo_documento,usu.id_tipo_genero,\n"
                    + "usu.flag_cambio_password,usu.id_tipo_usuario,usu.codigo_color,usu.es_moderador_chat,up.id_perfil,up.id_estado  from usuario usu inner join detalle_catalogo dc\n"
                    + "inner join usuario_perfil up where usu.id_estado='1' AND up.id_usuario=usu.id_usuario AND dc.id_catalogo='3' AND usu.id_tipo_genero=dc.codigo AND usu.apellido_paterno LIKE ?";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            pst.setString(1, "%" + empresa + "%");
            rs = pst.executeQuery();
            conexion.CerrarBD(conexion.Conexion());
        } catch (Exception e) {
            Mensajes.msjMuestra("Error al Listar por Empresa" + e.getMessage());
        }
        return rs;
    }

    public static ArrayList CargarTipoDocumento() {
        ArrayList tipo_documento = new ArrayList();
        ResultSet rs = null;

        try {

            String consulta = "select descripcion_corta from detalle_catalogo where id_catalogo=1 and id_estado=1";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                tipo_documento.add(rs.getString("descripcion_corta"));
            }
            conexion.CerrarBD(conexion.Conexion());

        } catch (Exception e) {
            Mensajes.msjMuestra(e.getMessage());
        }

        return tipo_documento;
    }

    public static ArrayList CargarTipoGenero() {
        ArrayList tipo_genero = new ArrayList();
        ResultSet rs = null;

        try {

            String consulta = "select descripcion_corta from detalle_catalogo where id_catalogo=3 and id_estado=1";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                tipo_genero.add(rs.getString("descripcion_corta"));
            }
            conexion.CerrarBD(conexion.Conexion());

        } catch (Exception e) {
            Mensajes.msjMuestra(e.getMessage());
        }

        return tipo_genero;
    }

    public static ArrayList CargarTipoFlag() {
        ArrayList tipo_flag = new ArrayList();
        ResultSet rs = null;

        try {

            String consulta = "select descripcion_corta from detalle_catalogo where id_catalogo=4 and id_estado=1";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                tipo_flag.add(rs.getString("descripcion_corta"));
            }
            conexion.CerrarBD(conexion.Conexion());

        } catch (Exception e) {
            Mensajes.msjMuestra(e.getMessage());
        }

        return tipo_flag;
    }

    public static ArrayList CargarTipoUsuario() {
        ArrayList tipo_usuario = new ArrayList();
        ResultSet rs = null;

        try {

            String consulta = "select descripcion_corta from detalle_catalogo where id_catalogo=5 and id_estado=1";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                tipo_usuario.add(rs.getString("descripcion_corta"));
            }
            conexion.CerrarBD(conexion.Conexion());

        } catch (Exception e) {
            Mensajes.msjMuestra(e.getMessage());
        }

        return tipo_usuario;
    }

}
