package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    final static String driverClass = "com.mysql.cj.jdbc.Driver";
    final static String user = "aysh";
    final static String password = "password";
    final static String url = "jdbc:mysql://localhost/test";


    public static volatile Connection conn;

    public static Connection getConnection() {
        if( conn == null ){
            try{
                Class.forName(driverClass);
                conn = DriverManager.getConnection(url, user, password);

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return conn;
    }

}
