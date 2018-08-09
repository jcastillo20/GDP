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
public class detalle_pedido {

    private int id_detalle_pedido;
    private int id_pedido;
    private String id_tipo_documento;
    private String numero_documento;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;
    private String id_localidad;
    private String id_tipo_servicio;
    private String archivo;
    private String nombre_archivo;
    private String centro_costo;
    private double costo_servicio;
    private String id_estado;
    private Date fecha_creacion;
    private Date fecha_actualizacion;
    private String id_usuario_crea;
    private String id_usuario_actualiza;
    private double file_size;
    private String flag_antecedentes;
    private int stage_id;
    private String ruta_archivo;
    private String telefonos_contacto;
    private String cargo_postula;
    private String id_permiso_comunicarse;
    private String id_tiempo_validar;
    private String jefe_directo_1;
    private String telefonos_jefe_directo_1;
    private String empresa_jefe_directo_1;
    private String telefonos_rrhh_jefe_directo_1;
    private String jefe_directo_2;
    private String telefonos_jefe_directo_2;
    private String empresa_jefe_directo_2;
    private String telefonos_rrhh_jefe_directo_2;
    private String jefe_directo_3;
    private String telefonos_jefe_directo_3;
    private String empresa_jefe_directo_3;
    private String telefonos_rrhh_jefe_directo_3;
    private String id_estado_postulante_empresa_1;
    private String observaciones_postulante;
    private String ruta_archivo_adjunto;
    private String id_estado_reporte;
    private int cantidad_documentos_validados;
    private int id_usuario_asignado;
    private String id_catalogo_resumen_investigacion;
    private String ruta_archivo_word;
    private String nombre_archivo_word;
    private int id_usuario_enviado;
    private String comentario;
    private int id_paquete_organizacion_solicitud;
    private String id_ubigeo;
    private String urbanizacion;
    private String direccion;
    private String referencia;
    private String telefonos_verificacion_domiciliaria;
    private String id_proveedor_empresa_solicitante;
    private String razon_social_proveedor;
    private String id_tipo_proveedor;
    private String unidad_investigacion;
    private String modalidad_express;
    private String region;
    private String tienda;
    private String puesto;

    public String getId_proveedor_empresa_solicitante() {
        return id_proveedor_empresa_solicitante;
    }

    public void setId_proveedor_empresa_solicitante(String id_proveedor_empresa_solicitante) {
        this.id_proveedor_empresa_solicitante = id_proveedor_empresa_solicitante;
    }

    public String getRazon_social_proveedor() {
        return razon_social_proveedor;
    }

    public void setRazon_social_proveedor(String razon_social_proveedor) {
        this.razon_social_proveedor = razon_social_proveedor;
    }

    public String getId_tipo_proveedor() {
        return id_tipo_proveedor;
    }

    public void setId_tipo_proveedor(String id_tipo_proveedor) {
        this.id_tipo_proveedor = id_tipo_proveedor;
    }

    public String getUnidad_investigacion() {
        return unidad_investigacion;
    }

    public void setUnidad_investigacion(String unidad_investigacion) {
        this.unidad_investigacion = unidad_investigacion;
    }

    public String getModalidad_express() {
        return modalidad_express;
    }

    public void setModalidad_express(String modalidad_express) {
        this.modalidad_express = modalidad_express;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public detalle_pedido() {
    }

    public detalle_pedido(String dni, String nombres, String servicio, String localidad, String centro_costo, String fecha, String usuario, String costo, String antecedens) {
        this.ruta_archivo = dni;
        this.telefonos_contacto = nombres;
        this.cargo_postula = servicio;
        this.id_permiso_comunicarse = localidad;
        this.id_tiempo_validar = centro_costo;
        this.jefe_directo_1 = fecha;
        this.telefonos_jefe_directo_1 = usuario;
        this.empresa_jefe_directo_1 = costo;
        this.telefonos_rrhh_jefe_directo_1 = antecedens;
    }

    public detalle_pedido(int id_detalle_pedido, int id_pedido, String id_tipo_documento, String numero_documento, String apellido_paterno, String apellido_materno, String nombres, String id_localidad, String id_tipo_servicio, String archivo, String nombre_archivo, String centro_costo, double costo_servicio, String id_estado, Date fecha_creacion, Date fecha_actualizacion, String id_usuario_crea, String id_usuario_actualiza, double file_size, String flag_antecedentes, int stage_id, String ruta_archivo, String telefonos_contacto, String cargo_postula, String id_permiso_comunicarse, String id_tiempo_validar, String jefe_directo_1, String telefonos_jefe_directo_1, String empresa_jefe_directo_1, String telefonos_rrhh_jefe_directo_1, String jefe_directo_2, String telefonos_jefe_directo_2, String empresa_jefe_directo_2, String telefonos_rrhh_jefe_directo_2, String jefe_directo_3, String telefonos_jefe_directo_3, String empresa_jefe_directo_3, String telefonos_rrhh_jefe_directo_3, String id_estado_postulante_empresa_1, String observaciones_postulante, String ruta_archivo_adjunto, String id_estado_reporte, int cantidad_documentos_validados, int id_usuario_asignado, String id_catalogo_resumen_investigacion, String ruta_archivo_word, String nombre_archivo_word, int id_usuario_enviado, String comentario, int id_paquete_organizacion_solicitud, String id_ubigeo, String urbanizacion, String direccion, String referencia, String telefonos_verificacion_domiciliaria, String id_proveedor_empresa_solicitante, String razon_social_proveedor, String id_tipo_proveedor, String unidad_investigacion, String modalidad_express, String region, String tienda, String puesto) {
        this.id_detalle_pedido = id_detalle_pedido;
        this.id_pedido = id_pedido;
        this.id_tipo_documento = id_tipo_documento;
        this.numero_documento = numero_documento;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombres = nombres;
        this.id_localidad = id_localidad;
        this.id_tipo_servicio = id_tipo_servicio;
        this.archivo = archivo;
        this.nombre_archivo = nombre_archivo;
        this.centro_costo = centro_costo;
        this.costo_servicio = costo_servicio;
        this.id_estado = id_estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.id_usuario_crea = id_usuario_crea;
        this.id_usuario_actualiza = id_usuario_actualiza;
        this.file_size = file_size;
        this.flag_antecedentes = flag_antecedentes;
        this.stage_id = stage_id;
        this.ruta_archivo = ruta_archivo;
        this.telefonos_contacto = telefonos_contacto;
        this.cargo_postula = cargo_postula;
        this.id_permiso_comunicarse = id_permiso_comunicarse;
        this.id_tiempo_validar = id_tiempo_validar;
        this.jefe_directo_1 = jefe_directo_1;
        this.telefonos_jefe_directo_1 = telefonos_jefe_directo_1;
        this.empresa_jefe_directo_1 = empresa_jefe_directo_1;
        this.telefonos_rrhh_jefe_directo_1 = telefonos_rrhh_jefe_directo_1;
        this.jefe_directo_2 = jefe_directo_2;
        this.telefonos_jefe_directo_2 = telefonos_jefe_directo_2;
        this.empresa_jefe_directo_2 = empresa_jefe_directo_2;
        this.telefonos_rrhh_jefe_directo_2 = telefonos_rrhh_jefe_directo_2;
        this.jefe_directo_3 = jefe_directo_3;
        this.telefonos_jefe_directo_3 = telefonos_jefe_directo_3;
        this.empresa_jefe_directo_3 = empresa_jefe_directo_3;
        this.telefonos_rrhh_jefe_directo_3 = telefonos_rrhh_jefe_directo_3;
        this.id_estado_postulante_empresa_1 = id_estado_postulante_empresa_1;
        this.observaciones_postulante = observaciones_postulante;
        this.ruta_archivo_adjunto = ruta_archivo_adjunto;
        this.id_estado_reporte = id_estado_reporte;
        this.cantidad_documentos_validados = cantidad_documentos_validados;
        this.id_usuario_asignado = id_usuario_asignado;
        this.id_catalogo_resumen_investigacion = id_catalogo_resumen_investigacion;
        this.ruta_archivo_word = ruta_archivo_word;
        this.nombre_archivo_word = nombre_archivo_word;
        this.id_usuario_enviado = id_usuario_enviado;
        this.comentario = comentario;
        this.id_paquete_organizacion_solicitud = id_paquete_organizacion_solicitud;
        this.id_ubigeo = id_ubigeo;
        this.urbanizacion = urbanizacion;
        this.direccion = direccion;
        this.referencia = referencia;
        this.telefonos_verificacion_domiciliaria = telefonos_verificacion_domiciliaria;
        this.id_proveedor_empresa_solicitante = id_proveedor_empresa_solicitante;
        this.razon_social_proveedor = razon_social_proveedor;
        this.id_tipo_proveedor = id_tipo_proveedor;
        this.unidad_investigacion = unidad_investigacion;
        this.modalidad_express = modalidad_express;
        this.region = region;
        this.tienda = tienda;
        this.puesto = puesto;
    }


    public int getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(int id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(String id_localidad) {
        this.id_localidad = id_localidad;
    }

    public String getId_tipo_servicio() {
        return id_tipo_servicio;
    }

    public void setId_tipo_servicio(String id_tipo_servicio) {
        this.id_tipo_servicio = id_tipo_servicio;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getCentro_costo() {
        return centro_costo;
    }

    public void setCentro_costo(String centro_costo) {
        this.centro_costo = centro_costo;
    }

    public double getCosto_servicio() {
        return costo_servicio;
    }

    public void setCosto_servicio(double costo_servicio) {
        this.costo_servicio = costo_servicio;
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

    public double getFile_size() {
        return file_size;
    }

    public void setFile_size(double file_size) {
        this.file_size = file_size;
    }

    public String getFlag_antecedentes() {
        return flag_antecedentes;
    }

    public void setFlag_antecedentes(String flag_antecedentes) {
        this.flag_antecedentes = flag_antecedentes;
    }

    public int getStage_id() {
        return stage_id;
    }

    public void setStage_id(int stage_id) {
        this.stage_id = stage_id;
    }

    public String getRuta_archivo() {
        return ruta_archivo;
    }

    public void setRuta_archivo(String ruta_archivo) {
        this.ruta_archivo = ruta_archivo;
    }

    public String getTelefonos_contacto() {
        return telefonos_contacto;
    }

    public void setTelefonos_contacto(String telefonos_contacto) {
        this.telefonos_contacto = telefonos_contacto;
    }

    public String getCargo_postula() {
        return cargo_postula;
    }

    public void setCargo_postula(String cargo_postula) {
        this.cargo_postula = cargo_postula;
    }

    public String getId_permiso_comunicarse() {
        return id_permiso_comunicarse;
    }

    public void setId_permiso_comunicarse(String id_permiso_comunicarse) {
        this.id_permiso_comunicarse = id_permiso_comunicarse;
    }

    public String getId_tiempo_validar() {
        return id_tiempo_validar;
    }

    public void setId_tiempo_validar(String id_tiempo_validar) {
        this.id_tiempo_validar = id_tiempo_validar;
    }

    public String getJefe_directo_1() {
        return jefe_directo_1;
    }

    public void setJefe_directo_1(String jefe_directo_1) {
        this.jefe_directo_1 = jefe_directo_1;
    }

    public String getTelefonos_jefe_directo_1() {
        return telefonos_jefe_directo_1;
    }

    public void setTelefonos_jefe_directo_1(String telefonos_jefe_directo_1) {
        this.telefonos_jefe_directo_1 = telefonos_jefe_directo_1;
    }

    public String getEmpresa_jefe_directo_1() {
        return empresa_jefe_directo_1;
    }

    public void setEmpresa_jefe_directo_1(String empresa_jefe_directo_1) {
        this.empresa_jefe_directo_1 = empresa_jefe_directo_1;
    }

    public String getTelefonos_rrhh_jefe_directo_1() {
        return telefonos_rrhh_jefe_directo_1;
    }

    public void setTelefonos_rrhh_jefe_directo_1(String telefonos_rrhh_jefe_directo_1) {
        this.telefonos_rrhh_jefe_directo_1 = telefonos_rrhh_jefe_directo_1;
    }

    public String getJefe_directo_2() {
        return jefe_directo_2;
    }

    public void setJefe_directo_2(String jefe_directo_2) {
        this.jefe_directo_2 = jefe_directo_2;
    }

    public String getTelefonos_jefe_directo_2() {
        return telefonos_jefe_directo_2;
    }

    public void setTelefonos_jefe_directo_2(String telefonos_jefe_directo_2) {
        this.telefonos_jefe_directo_2 = telefonos_jefe_directo_2;
    }

    public String getEmpresa_jefe_directo_2() {
        return empresa_jefe_directo_2;
    }

    public void setEmpresa_jefe_directo_2(String empresa_jefe_directo_2) {
        this.empresa_jefe_directo_2 = empresa_jefe_directo_2;
    }

    public String getTelefonos_rrhh_jefe_directo_2() {
        return telefonos_rrhh_jefe_directo_2;
    }

    public void setTelefonos_rrhh_jefe_directo_2(String telefonos_rrhh_jefe_directo_2) {
        this.telefonos_rrhh_jefe_directo_2 = telefonos_rrhh_jefe_directo_2;
    }

    public String getJefe_directo_3() {
        return jefe_directo_3;
    }

    public void setJefe_directo_3(String jefe_directo_3) {
        this.jefe_directo_3 = jefe_directo_3;
    }

    public String getTelefonos_jefe_directo_3() {
        return telefonos_jefe_directo_3;
    }

    public void setTelefonos_jefe_directo_3(String telefonos_jefe_directo_3) {
        this.telefonos_jefe_directo_3 = telefonos_jefe_directo_3;
    }

    public String getEmpresa_jefe_directo_3() {
        return empresa_jefe_directo_3;
    }

    public void setEmpresa_jefe_directo_3(String empresa_jefe_directo_3) {
        this.empresa_jefe_directo_3 = empresa_jefe_directo_3;
    }

    public String getTelefonos_rrhh_jefe_directo_3() {
        return telefonos_rrhh_jefe_directo_3;
    }

    public void setTelefonos_rrhh_jefe_directo_3(String telefonos_rrhh_jefe_directo_3) {
        this.telefonos_rrhh_jefe_directo_3 = telefonos_rrhh_jefe_directo_3;
    }

    public String getId_estado_postulante_empresa_1() {
        return id_estado_postulante_empresa_1;
    }

    public void setId_estado_postulante_empresa_1(String id_estado_postulante_empresa_1) {
        this.id_estado_postulante_empresa_1 = id_estado_postulante_empresa_1;
    }

    public String getObservaciones_postulante() {
        return observaciones_postulante;
    }

    public void setObservaciones_postulante(String observaciones_postulante) {
        this.observaciones_postulante = observaciones_postulante;
    }

    public String getRuta_archivo_adjunto() {
        return ruta_archivo_adjunto;
    }

    public void setRuta_archivo_adjunto(String ruta_archivo_adjunto) {
        this.ruta_archivo_adjunto = ruta_archivo_adjunto;
    }

    public String getId_estado_reporte() {
        return id_estado_reporte;
    }

    public void setId_estado_reporte(String id_estado_reporte) {
        this.id_estado_reporte = id_estado_reporte;
    }

    public int getCantidad_documentos_validados() {
        return cantidad_documentos_validados;
    }

    public void setCantidad_documentos_validados(int cantidad_documentos_validados) {
        this.cantidad_documentos_validados = cantidad_documentos_validados;
    }

    public int getId_usuario_asignado() {
        return id_usuario_asignado;
    }

    public void setId_usuario_asignado(int id_usuario_asignado) {
        this.id_usuario_asignado = id_usuario_asignado;
    }

    public String getId_catalogo_resumen_investigacion() {
        return id_catalogo_resumen_investigacion;
    }

    public void setId_catalogo_resumen_investigacion(String id_catalogo_resumen_investigacion) {
        this.id_catalogo_resumen_investigacion = id_catalogo_resumen_investigacion;
    }

    public String getRuta_archivo_word() {
        return ruta_archivo_word;
    }

    public void setRuta_archivo_word(String ruta_archivo_word) {
        this.ruta_archivo_word = ruta_archivo_word;
    }

    public String getNombre_archivo_word() {
        return nombre_archivo_word;
    }

    public void setNombre_archivo_word(String nombre_archivo_word) {
        this.nombre_archivo_word = nombre_archivo_word;
    }

    public int getId_usuario_enviado() {
        return id_usuario_enviado;
    }

    public void setId_usuario_enviado(int id_usuario_enviado) {
        this.id_usuario_enviado = id_usuario_enviado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getId_paquete_organizacion_solicitud() {
        return id_paquete_organizacion_solicitud;
    }

    public void setId_paquete_organizacion_solicitud(int id_paquete_organizacion_solicitud) {
        this.id_paquete_organizacion_solicitud = id_paquete_organizacion_solicitud;
    }

    public String getId_ubigeo() {
        return id_ubigeo;
    }

    public void setId_ubigeo(String id_ubigeo) {
        this.id_ubigeo = id_ubigeo;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTelefonos_verificacion_domiciliaria() {
        return telefonos_verificacion_domiciliaria;
    }

    public void setTelefonos_verificacion_domiciliaria(String telefonos_verificacion_domiciliaria) {
        this.telefonos_verificacion_domiciliaria = telefonos_verificacion_domiciliaria;
    }

}
