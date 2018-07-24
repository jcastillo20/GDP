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
public class perfil {
    
    private int id_perfil;
    private String descripcion_corta;
    private String descripcion_larga;
    private Date fecha_inicio_vigencia;
    private Date fecha_fin_vigencia;
    private int id_estado;
    private Date fecha_creacion;
    private Date fecha_actualizacion;
    private int id_usuario_crea;
    private int id_usuario_actualiza;

    public perfil() {
    }

    public perfil(int id_perfil, String descripcion_corta, String descripcion_larga, Date fecha_inicio_vigencia, Date fecha_fin_vigencia, int id_estado, Date fecha_creacion, Date fecha_actualizacion, int id_usuario_crea, int id_usuario_actualiza) {
        this.id_perfil = id_perfil;
        this.descripcion_corta = descripcion_corta;
        this.descripcion_larga = descripcion_larga;
        this.fecha_inicio_vigencia = fecha_inicio_vigencia;
        this.fecha_fin_vigencia = fecha_fin_vigencia;
        this.id_estado = id_estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.id_usuario_crea = id_usuario_crea;
        this.id_usuario_actualiza = id_usuario_actualiza;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getDescripcion_corta() {
        return descripcion_corta;
    }

    public void setDescripcion_corta(String descripcion_corta) {
        this.descripcion_corta = descripcion_corta;
    }

    public String getDescripcion_larga() {
        return descripcion_larga;
    }

    public void setDescripcion_larga(String descripcion_larga) {
        this.descripcion_larga = descripcion_larga;
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

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
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

    public int getId_usuario_crea() {
        return id_usuario_crea;
    }

    public void setId_usuario_crea(int id_usuario_crea) {
        this.id_usuario_crea = id_usuario_crea;
    }

    public int getId_usuario_actualiza() {
        return id_usuario_actualiza;
    }

    public void setId_usuario_actualiza(int id_usuario_actualiza) {
        this.id_usuario_actualiza = id_usuario_actualiza;
    }
    
    
    
}
