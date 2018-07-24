/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.controlador;

import com.pe.extras.Mensajes;
import com.pe.modelo.OnpeFBI;
import com.pe.util.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author JUAN
 */
public class OnpeFBIDAO {

    ResultSet rs = null;

 
    public ArrayList<OnpeFBI> BusquedaXDNI(String dni) {
        ArrayList<OnpeFBI> list = new ArrayList<>();
        String sql = "select o.NUMERO_DOCUMENTO,concat(o.APELLIDO_PATERNO,' ',o.APELLIDO_MATERNO,' ',o.NOMBRES)AS NOMBRES,\n"
                + "YEAR(CURDATE())-YEAR(o.FECHA_NACIMIENTO) + \n"
                + "IF(DATE_FORMAT(CURDATE(),'%m-%d') > DATE_FORMAT(o.FECHA_NACIMIENTO,'%m-%d'), 0 , -1 ) AS EDAD\n"
                + "from onpe o where o.NUMERO_DOCUMENTO=? group by o.NUMERO_DOCUMENTO";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conexion.ConexionFBI().prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            OnpeFBI vo = new OnpeFBI();
            //while (rs.next()) {
            if (rs != null && rs.next()) {
                vo.setNUMERO_DOCUMENTO(rs.getString("NUMERO_DOCUMENTO"));
                vo.setAPELLIDO_PATERNO(rs.getString("NOMBRES"));
                vo.setNUMERO_MESA(rs.getString("EDAD"));
            } else {
                vo.setNUMERO_DOCUMENTO("No se encontro");
                vo.setAPELLIDO_PATERNO(" ");
                vo.setNUMERO_MESA(" ");
            }
            list.add(vo);
            //        }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conexion.CerrarBDFBI(conexion.ConexionFBI());
            } catch (Exception ex) {
            }
        }
        return list;
    }
}
