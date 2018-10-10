package com.pe.util;

import com.mysql.jdbc.Driver;
import com.pe.extras.Mensajes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author JuanCR
 */
public class conexion {

    public static Connection Conexion() {

        final String usuario = "root";
        final String pasword = "a2bceYUxS5";
        Connection conexion = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://68.169.51.63:3306/gdp?zeroDateTimeBehavior=convertToNull";
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, pasword);
          
        } catch (Exception e) {
            Mensajes.msjMuestra( e.getMessage());
            System.out.println(e);

        }
        return conexion;
    }


    public static void CerrarBD(Connection conexion) {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception ex) {
            Mensajes.msjMuestra(ex.getMessage());
        }
    }

    public static Connection ConexionFBI() {

        final String usuario = "bis";
        final String pasword = "2adpkNFB3UNkYRnU";
        Connection conexion = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://68.169.60.121:3306/bis";
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, pasword);
        } catch (Exception ex) {
            Mensajes.msjMuestra(ex.getMessage());

        }
        return conexion;
    }

    public static void CerrarBDFBI(Connection conexion) {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception ex) {
            Mensajes.msjMuestra(ex.getMessage());
        }
    }

}
