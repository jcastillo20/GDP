/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.modelo;

import java.sql.Date;

/**
 *
 * @author JuanCR
 */
public class usuario {
    
private int id_usuario;
private String id_tipo_documento;
private String numero_documento;
private String nombres;
private String apellido_paterno;
private String apellido_materno;
private String id_tipo_genero;
private Date fecha_nacimiento;
private String password;
private Date fecha_expira_password;
private String flag_cambio_password;
public String username;
private String codigo_empleado;
private String email;
private String telefono_fijo;
private String telefono_celular;
private String telefono_anexo;
private Date fecha_inicio_vigencia;
private Date fecha_fin_vigencia;
private String id_estado;
private String id_tipo_usuario;
private Date fecha_creacion;
private Date fecha_actualizacion;
private String id_usuario_crea;
private String id_usuario_actualiza;
private int codigo_color;

    public usuario() {
    }

    public usuario(int id_usuario, String id_tipo_documento, String numero_documento, String nombres, String apellido_paterno, String apellido_materno, String id_tipo_genero, Date fecha_nacimiento, String password, Date fecha_expira_password, String flag_cambio_password, String username, String codigo_empleado, String email, String telefono_fijo, String telefono_celular, String telefono_anexo, Date fecha_inicio_vigencia, Date fecha_fin_vigencia, String id_estado, String id_tipo_usuario, Date fecha_creacion, Date fecha_actualizacion, String id_usuario_crea, String id_usuario_actualiza, int codigo_color) {
        this.id_usuario = id_usuario;
        this.id_tipo_documento = id_tipo_documento;
        this.numero_documento = numero_documento;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.id_tipo_genero = id_tipo_genero;
        this.fecha_nacimiento = fecha_nacimiento;
        this.password = password;
        this.fecha_expira_password = fecha_expira_password;
        this.flag_cambio_password = flag_cambio_password;
        this.username = username;
        this.codigo_empleado = codigo_empleado;
        this.email = email;
        this.telefono_fijo = telefono_fijo;
        this.telefono_celular = telefono_celular;
        this.telefono_anexo = telefono_anexo;
        this.fecha_inicio_vigencia = fecha_inicio_vigencia;
        this.fecha_fin_vigencia = fecha_fin_vigencia;
        this.id_estado = id_estado;
        this.id_tipo_usuario = id_tipo_usuario;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.id_usuario_crea = id_usuario_crea;
        this.id_usuario_actualiza = id_usuario_actualiza;
        this.codigo_color = codigo_color;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_tipo_documento() {
        return id_tipo_documento;
    }

    public void setId_tipo_documento(String id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getId_tipo_genero() {
        return id_tipo_genero;
    }

    public void setId_tipo_genero(String id_tipo_genero) {
        this.id_tipo_genero = id_tipo_genero;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFecha_expira_password() {
        return fecha_expira_password;
    }

    public void setFecha_expira_password(Date fecha_expira_password) {
        this.fecha_expira_password = fecha_expira_password;
    }

    public String getFlag_cambio_password() {
        return flag_cambio_password;
    }

    public void setFlag_cambio_password(String flag_cambio_password) {
        this.flag_cambio_password = flag_cambio_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCodigo_empleado() {
        return codigo_empleado;
    }

    public void setCodigo_empleado(String codigo_empleado) {
        this.codigo_empleado = codigo_empleado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono_fijo() {
        return telefono_fijo;
    }

    public void setTelefono_fijo(String telefono_fijo) {
        this.telefono_fijo = telefono_fijo;
    }

    public String getTelefono_celular() {
        return telefono_celular;
    }

    public void setTelefono_celular(String telefono_celular) {
        this.telefono_celular = telefono_celular;
    }

    public String getTelefono_anexo() {
        return telefono_anexo;
    }

    public void setTelefono_anexo(String telefono_anexo) {
        this.telefono_anexo = telefono_anexo;
    }

    public Date getFecha_inicio_vigencia() {
        return fecha_inicio_vigencia;
    }

    public void setFecha_inicio_vigencia(Date fecha_inicio_vigencia) {
        this.fecha_inicio_vigencia = fecha_inicio_vigencia;
    }

    public Date getFecha_fin_vigencia() {
        return fecha_fin_vigencia;
    }

    public void setFecha_fin_vigencia(Date fecha_fin_vigencia) {
        this.fecha_fin_vigencia = fecha_fin_vigencia;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public String getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public void setId_tipo_usuario(String id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public String getId_usuario_crea() {
        return id_usuario_crea;
    }

    public void setId_usuario_crea(String id_usuario_crea) {
        this.id_usuario_crea = id_usuario_crea;
    }

    public String getId_usuario_actualiza() {
        return id_usuario_actualiza;
    }

    public void setId_usuario_actualiza(String id_usuario_actualiza) {
        this.id_usuario_actualiza = id_usuario_actualiza;
    }

    public int getCodigo_color() {
        return codigo_color;
    }

    public void setCodigo_color(int codigo_color) {
        this.codigo_color = codigo_color;
    }

    
    
}
