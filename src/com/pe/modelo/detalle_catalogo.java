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
public class detalle_catalogo {

    private int id_detalle_catalogo;
	private int id_catalogo;
	private String codigo;
	private String descripcion_corta;
	private String descripcion_larga;
	private String valor_auxiliar;
	private Date fecha_inicio_vigencia;
	private Date fecha_fin_vigencia;
	private String id_estado;
	private Date fecha_creacion;
	private Date fecha_actualizacion;
	private String id_usuario_crea;
	private String id_usuario_actualiza;

    public detalle_catalogo() {
    }

    public detalle_catalogo(int id_detalle_catalogo, int id_catalogo, String codigo, String descripcion_corta, String descripcion_larga, String valor_auxiliar, Date fecha_inicio_vigencia, Date fecha_fin_vigencia, String id_estado, Date fecha_creacion, Date fecha_actualizacion, String id_usuario_crea, String id_usuario_actualiza) {
        this.id_detalle_catalogo = id_detalle_catalogo;
        this.id_catalogo = id_catalogo;
        this.codigo = codigo;
        this.descripcion_corta = descripcion_corta;
        this.descripcion_larga = descripcion_larga;
        this.valor_auxiliar = valor_auxiliar;
        this.fecha_inicio_vigencia = fecha_inicio_vigencia;
        this.fecha_fin_vigencia = fecha_fin_vigencia;
        this.id_estado = id_estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.id_usuario_crea = id_usuario_crea;
        this.id_usuario_actualiza = id_usuario_actualiza;
    }

    public int getId_detalle_catalogo() {
        return id_detalle_catalogo;
    }

    public void setId_detalle_catalogo(int id_detalle_catalogo) {
        this.id_detalle_catalogo = id_detalle_catalogo;
    }

    public int getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(int id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getValor_auxiliar() {
        return valor_auxiliar;
    }

    public void setValor_auxiliar(String valor_auxiliar) {
        this.valor_auxiliar = valor_auxiliar;
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
        
        
    
}
