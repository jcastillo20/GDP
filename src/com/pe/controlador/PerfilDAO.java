/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.controlador;

import com.pe.extras.Mensajes;
import com.pe.util.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JuanCR
 */
public class PerfilDAO {
    
     public static ArrayList CargarTipoPerfil(){
        ArrayList  tipo_perfil=new ArrayList();
        ResultSet rs=null;
        
        try {
            
            String consulta="select descripcion_corta from perfil where id_estado=1";
            PreparedStatement pst=conexion.Conexion().prepareStatement(consulta);
            rs=pst.executeQuery();
            while(rs.next()){
                tipo_perfil.add(rs.getString("descripcion_corta"));
            }
            conexion.CerrarBD(conexion.Conexion());
            
        } catch (Exception e) {
            Mensajes.msjMuestra(e.getMessage());
        }
        
        return tipo_perfil;
    }
    
}
