/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.modelo;

import java.util.Date;

/**
 *
 * @author JUAN
 */
public class organizacion {
    private int id_organizacion;
    private String ruc;
    private String razon_social;
    private String direccion;
    private String telefono1;
    private String telefono2;
    private String id_forma_pago;
    private String responsable_pago;
    private String telefono_responsable_pago;
    private String email_responsalbe_pago;
    private String anexo_responsable_pago;
    private String id_estado;
    private String anexo;
    private Date fecha_aniversario;
    private String categoria;
    private String nombre_comercial;
    private String grupo_comercial;
    private String ciiu;
    private int id_usuario_administrador;
    private int id_usuario_sectorista;
    private String cargo_responsalbe_pago;
    private String area_responsable_pago;
    private String celular_responsable_pago;
    private String id_usuario_crea;
    private String id_usuario_actualiza;
    private Date fecha_creacion;
    private Date fecha_actualizacion;

    public organizacion() {
    }
    
    public organizacion(int id_organizacion, String ruc, String razon_social, String direccion, String telefono1, String telefono2, String id_forma_pago, String responsable_pago, String telefono_responsable_pago, String email_responsalbe_pago, String anexo_responsable_pago, String id_estado, String anexo, Date fecha_aniversario, String categoria, String nombre_comercial, String grupo_comercial, String ciiu, int id_usuario_administrador, int id_usuario_sectorista, String cargo_responsalbe_pago, String area_responsable_pago, String celular_responsable_pago, String id_usuario_crea, String id_usuario_actualiza, Date fecha_creacion, Date fecha_actualizacion) {
        this.id_organizacion = id_organizacion;
        this.ruc = ruc;
        this.razon_social = razon_social;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.id_forma_pago = id_forma_pago;
        this.responsable_pago = responsable_pago;
        this.telefono_responsable_pago = telefono_responsable_pago;
        this.email_responsalbe_pago = email_responsalbe_pago;
        this.anexo_responsable_pago = anexo_responsable_pago;
        this.id_estado = id_estado;
        this.anexo = anexo;
        this.fecha_aniversario = fecha_aniversario;
        this.categoria = categoria;
        this.nombre_comercial = nombre_comercial;
        this.grupo_comercial = grupo_comercial;
        this.ciiu = ciiu;
        this.id_usuario_administrador = id_usuario_administrador;
        this.id_usuario_sectorista = id_usuario_sectorista;
        this.cargo_responsalbe_pago = cargo_responsalbe_pago;
        this.area_responsable_pago = area_responsable_pago;
        this.celular_responsable_pago = celular_responsable_pago;
        this.id_usuario_crea = id_usuario_crea;
        this.id_usuario_actualiza = id_usuario_actualiza;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public int getId_organizacion() {
        return id_organizacion;
    }

    public void setId_organizacion(int id_organizacion) {
        this.id_organizacion = id_organizacion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getId_forma_pago() {
        return id_forma_pago;
    }

    public void setId_forma_pago(String id_forma_pago) {
        this.id_forma_pago = id_forma_pago;
    }

    public String getResponsable_pago() {
        return responsable_pago;
    }

    public void setResponsable_pago(String responsable_pago) {
        this.responsable_pago = responsable_pago;
    }

    public String getTelefono_responsable_pago() {
        return telefono_responsable_pago;
    }

    public void setTelefono_responsable_pago(String telefono_responsable_pago) {
        this.telefono_responsable_pago = telefono_responsable_pago;
    }

    public String getEmail_responsalbe_pago() {
        return email_responsalbe_pago;
    }

    public void setEmail_responsalbe_pago(String email_responsalbe_pago) {
        this.email_responsalbe_pago = email_responsalbe_pago;
    }

    public String getAnexo_responsable_pago() {
        return anexo_responsable_pago;
    }

    public void setAnexo_responsable_pago(String anexo_responsable_pago) {
        this.anexo_responsable_pago = anexo_responsable_pago;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public Date getFecha_aniversario() {
        return fecha_aniversario;
    }

    public void setFecha_aniversario(Date fecha_aniversario) {
        this.fecha_aniversario = fecha_aniversario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public String getGrupo_comercial() {
        return grupo_comercial;
    }

    public void setGrupo_comercial(String grupo_comercial) {
        this.grupo_comercial = grupo_comercial;
    }

    public String getCiiu() {
        return ciiu;
    }

    public void setCiiu(String ciiu) {
        this.ciiu = ciiu;
    }

    public int getId_usuario_administrador() {
        return id_usuario_administrador;
    }

    public void setId_usuario_administrador(int id_usuario_administrador) {
        this.id_usuario_administrador = id_usuario_administrador;
    }

    public int getId_usuario_sectorista() {
        return id_usuario_sectorista;
    }

    public void setId_usuario_sectorista(int id_usuario_sectorista) {
        this.id_usuario_sectorista = id_usuario_sectorista;
    }

    public String getCargo_responsalbe_pago() {
        return cargo_responsalbe_pago;
    }

    public void setCargo_responsalbe_pago(String cargo_responsalbe_pago) {
        this.cargo_responsalbe_pago = cargo_responsalbe_pago;
    }

    public String getArea_responsable_pago() {
        return area_responsable_pago;
    }

    public void setArea_responsable_pago(String area_responsable_pago) {
        this.area_responsable_pago = area_responsable_pago;
    }

    public String getCelular_responsable_pago() {
        return celular_responsable_pago;
    }

    public void setCelular_responsable_pago(String celular_responsable_pago) {
        this.celular_responsable_pago = celular_responsable_pago;
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
    
    
    
}
