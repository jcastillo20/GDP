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
 * @author JUAN
 */
public class Detalle_CatalogoDAO {
    
   public static ArrayList CargarTipoEstado() {
        ArrayList tipo_estado = new ArrayList();
        ResultSet rs = null;

        try {

            String consulta = "select descripcion_corta from detalle_catalogo where id_catalogo=2 and id_estado=1";
            PreparedStatement pst = conexion.Conexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                tipo_estado.add(rs.getString("descripcion_corta"));
            }
            conexion.CerrarBD(conexion.Conexion());

        } catch (Exception e) {
            Mensajes.msjMuestra(e.getMessage());
        }

        return tipo_estado;
    }
}
