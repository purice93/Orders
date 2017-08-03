package com.csms;
 
/**
 * ���ݿ����
 *
 */
 
import java.sql.*;
 
 
public class DBLink { 
    // 
    private static Connection conn = null;
     
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }
}