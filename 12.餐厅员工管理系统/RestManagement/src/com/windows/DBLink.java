package com.windows;
 
/**
 * 数据库连接操作类
 * 这个类主要用于Java和mysql数据库的连接
 */
 
import java.sql.*;
 
 
public class DBLink { 
    // 
    private static Connection conn = null;
    
    
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载数据库驱动
            // 进行数据库连接：设置编码格式为utf-8、以及数据库登录名和密码
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?useUnicode=true&characterEncoding=utf-8", "root", "root");
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