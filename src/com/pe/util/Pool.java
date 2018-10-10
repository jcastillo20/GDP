/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author JUAN
 */
public class Pool {

    public DataSource datasource;
    String usuario = "root";
    String pasword = "a2bceYUxS5";
    String url = "jdbc:mysql://68.169.51.63:3306/gdp?zeroDateTimeBehavior=convertToNull";
    Connection connection = null;

    public Pool() {
        inicializarDataSource();
    }

    public void inicializarDataSource() {

        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername(usuario);
        basicDataSource.setPassword(pasword);
        basicDataSource.setUrl(url);
        basicDataSource.setMaxActive(50);
        basicDataSource.setRemoveAbandoned(true);
        basicDataSource.setRemoveAbandonedTimeout(300000);

        datasource = basicDataSource;

//            connection = datasource.getConnection();
    }

    public void devolverconexionPool() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
