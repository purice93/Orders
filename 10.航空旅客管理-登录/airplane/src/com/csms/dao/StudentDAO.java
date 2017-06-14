package com.csms.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csms.DBLink;
 
public class StudentDAO {
    public Boolean loginConfirm(String name, String password) {
 
        Connection conn = DBLink.getConn();
 
        String sql = "SELECT passagerID,password FROM passager";
 
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                if (name.equals(rs.getString("passagerID")) && password.equals(rs.getString("password"))) {
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
                e.printStackTrace();
            }
        }
        return false;
    }

	public void register(String loginUserName, String loginPassword, String identityId, String passargerId) {
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO passager (passagerID,realName,identityID,password) VALUES (?,?,?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, loginUserName);
            ptm.setString(2, loginPassword);
            ptm.setString(3, identityId);
            ptm.setString(4, passargerId);
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        } 
	}
}