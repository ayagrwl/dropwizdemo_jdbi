package com.flipkart.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {

    public static void main(final String[] args){
        try {
            Connection conn = DBUtils.getConnection();

            String sql = "select * from auth";

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while(res.next()){
                System.out.printf("%5s %10s %15s\n", res.getString(1), res.getString(2), res.getString(3));
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
