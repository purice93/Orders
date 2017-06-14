package com.csms;
 
/**
 * @program
 *      该类主要是将连接数据库的操作写在该类之中，方便dao链接
 *
 */
 
import java.sql.*;
 
 
public class DBLink {
    // 1. 创建数据库连接地址
    private static final String URL = "jdbc:mysql://localhost:3306/manage";
     
    // 2. 数据库登录用户名
    private static final String NAME = "root";
     
    // 3. 数据库登录密码
    private static final String PASSWORD = "root";
     
    // 4. 设置连接对象
    private static Connection conn = null;
     
    static{
        try {
            // 加载jdbc程序驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接到指定的数据库,取得数据库的对象
            conn = (Connection) DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            // 抛出文件找不到的异常
            e.printStackTrace();
        } catch (SQLException e) {
            // 抛出数据库对象链接不到的异常
            e.printStackTrace();
        }
    }// 得到数据库连接对象
    public static Connection getConn() {
        return conn;
    }
}