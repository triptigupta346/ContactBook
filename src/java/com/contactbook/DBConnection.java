/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contactbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Tripti
 */
public class DBConnection {

    public Connection getDBConnection() {
        Connection con = null;
        try {
            //DB connection
            String driverName = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://node188290-contactbookapi.jelastic.servint.net/contact";
            String dbName = "contact";
            String userName = "root";
            String password = "MSDbna22534";

            Class.forName(driverName);
            con = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

}
