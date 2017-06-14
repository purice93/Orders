package com.csms;
 
/**
 * 数据库连接
 *
 */
 
import java.sql.*;
 
 
public class DBLink { 
    // 数据库连接
    private static Connection conn = null;
     
    static{
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", "root", "root");
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