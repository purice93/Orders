package com.csms;
 
/**
 * @program
 *      ������Ҫ�ǽ��������ݿ�Ĳ���д�ڸ���֮�У�����dao����
 *
 */
 
import java.sql.*;
 
 
public class DBLink {
    // 1. �������ݿ����ӵ�ַ
    private static final String URL = "jdbc:mysql://localhost:3306/manage";
     
    // 2. ���ݿ��¼�û���
    private static final String NAME = "root";
     
    // 3. ���ݿ��¼����
    private static final String PASSWORD = "root";
     
    // 4. �������Ӷ���
    private static Connection conn = null;
     
    static{
        try {
            // ����jdbc��������
            Class.forName("com.mysql.jdbc.Driver");
            // ���ӵ�ָ�������ݿ�,ȡ�����ݿ�Ķ���
            conn = (Connection) DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            // �׳��ļ��Ҳ������쳣
            e.printStackTrace();
        } catch (SQLException e) {
            // �׳����ݿ�������Ӳ������쳣
            e.printStackTrace();
        }
    }// �õ����ݿ����Ӷ���
    public static Connection getConn() {
        return conn;
    }
}