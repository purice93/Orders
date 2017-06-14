package com;
 
/**
 * 数据库连接操作函数
 *
 */
 
import java.sql.*;
 
 
public class DBLink { 
    // 
    private static Connection conn = null;
    
    
    // 加载驱动
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/showtable", "root", "root");
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