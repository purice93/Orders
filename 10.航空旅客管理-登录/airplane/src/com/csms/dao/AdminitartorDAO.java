package com.csms.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csms.DBLink;
 
/**
 * @program 管理员信息与数据库进行操作的类
 *
 */
 
public class AdminitartorDAO {
 
    public Boolean loginConfirm(String name, String password) {
        Connection conn = DBLink.getConn();
        String sql = "SELECT userName,password FROM admin";
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                if (name.equals(rs.getString("userName")) && password.equals(rs.getString("password"))) {
                    return true;
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null)
                    stm.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }
     
	public void register(String loginUserName, String loginPassword) {
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO admin (userName,password) VALUES (?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, loginUserName);
            ptm.setString(2, loginPassword);
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        } 
	}
}