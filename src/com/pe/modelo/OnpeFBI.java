/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.modelo;

/**
 *
 * @author JUAN
 */
public class OnpeFBI {

    private int ID;
    private String NUMERO_DOCUMENTO;
    private String DIGITO_VERIFICADOR;
    private String NUMERO_MESA;
    private String UBIGEO;
    private String APELLIDO_PATERNO;
    private String APELLIDO_MATERNO;
    private String NOMBRES;
    private String FECHA_NACIMIENTO;
    private String SEXO;
    private String GRADO_INSTRUCCION;
    private String RESTRICCION;
    private String TIPO_DOCUMENTO;
    private String DISCAPACIDAD;

    public OnpeFBI(int ID, String NUMERO_DOCUMENTO, String DIGITO_VERIFICADOR, String NUMERO_MESA, String UBIGEO, String APELLIDO_PATERNO, String APELLIDO_MATERNO, String NOMBRES, String FECHA_NACIMIENTO, String SEXO, String GRADO_INSTRUCCION, String RESTRICCION, String TIPO_DOCUMENTO, String DISCAPACIDAD) {
        this.ID = ID;
        this.NUMERO_DOCUMENTO = NUMERO_DOCUMENTO;
        this.DIGITO_VERIFICADOR = DIGITO_VERIFICADOR;
        this.NUMERO_MESA = NUMERO_MESA;
        this.UBIGEO = UBIGEO;
        this.APELLIDO_PATERNO = APELLIDO_PATERNO;
        this.APELLIDO_MATERNO = APELLIDO_MATERNO;
        this.NOMBRES = NOMBRES;
        this.FECHA_NACIMIENTO = FECHA_NACIMIENTO;
        this.SEXO = SEXO;
        this.GRADO_INSTRUCCION = GRADO_INSTRUCCION;
        this.RESTRICCION = RESTRICCION;
        this.TIPO_DOCUMENTO = TIPO_DOCUMENTO;
        this.DISCAPACIDAD = DISCAPACIDAD;
    }

    public OnpeFBI() {
       
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNUMERO_DOCUMENTO() {
        return NUMERO_DOCUMENTO;
    }

    public void setNUMERO_DOCUMENTO(String NUMERO_DOCUMENTO) {
        this.NUMERO_DOCUMENTO = NUMERO_DOCUMENTO;
    }

    public String getDIGITO_VERIFICADOR() {
        return DIGITO_VERIFICADOR;
    }

    public void setDIGITO_VERIFICADOR(String DIGITO_VERIFICADOR) {
        this.DIGITO_VERIFICADOR = DIGITO_VERIFICADOR;
    }

    public String getNUMERO_MESA() {
        return NUMERO_MESA;
    }

    public void setNUMERO_MESA(String NUMERO_MESA) {
        this.NUMERO_MESA = NUMERO_MESA;
    }

    public String getUBIGEO() {
        return UBIGEO;
    }

    public void setUBIGEO(String UBIGEO) {
        this.UBIGEO = UBIGEO;
    }

    public String getAPELLIDO_PATERNO() {
        return APELLIDO_PATERNO;
    }

    public void setAPELLIDO_PATERNO(String APELLIDO_PATERNO) {
        this.APELLIDO_PATERNO = APELLIDO_PATERNO;
    }

    public String getAPELLIDO_MATERNO() {
        return APELLIDO_MATERNO;
    }

    public void setAPELLIDO_MATERNO(String APELLIDO_MATERNO) {
        this.APELLIDO_MATERNO = APELLIDO_MATERNO;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getFECHA_NACIMIENTO() {
        return FECHA_NACIMIENTO;
    }

    public void setFECHA_NACIMIENTO(String FECHA_NACIMIENTO) {
        this.FECHA_NACIMIENTO = FECHA_NACIMIENTO;
    }

    public String getSEXO() {
        return SEXO;
    }

    public void setSEXO(String SEXO) {
        this.SEXO = SEXO;
    }

    public String getGRADO_INSTRUCCION() {
        return GRADO_INSTRUCCION;
    }

    public void setGRADO_INSTRUCCION(String GRADO_INSTRUCCION) {
        this.GRADO_INSTRUCCION = GRADO_INSTRUCCION;
    }

    public String getRESTRICCION() {
        return RESTRICCION;
    }

    public void setRESTRICCION(String RESTRICCION) {
        this.RESTRICCION = RESTRICCION;
    }

    public String getTIPO_DOCUMENTO() {
        return TIPO_DOCUMENTO;
    }

    public void setTIPO_DOCUMENTO(String TIPO_DOCUMENTO) {
        this.TIPO_DOCUMENTO = TIPO_DOCUMENTO;
    }

    public String getDISCAPACIDAD() {
        return DISCAPACIDAD;
    }

    public void setDISCAPACIDAD(String DISCAPACIDAD) {
        this.DISCAPACIDAD = DISCAPACIDAD;
    }
    
    
}
