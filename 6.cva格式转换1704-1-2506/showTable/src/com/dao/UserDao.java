package com.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DBLink;
import com.entity.User;

/**
 * ���ݿ������
 *
 */
public class UserDao {
	
	// �����ݿ��user�в�������
	public void insertUser(User user) {
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO user (username,udate,utime,tcode) VALUES (?,?,?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, user.getUsername());
            ptm.setString(2, user.getuDate());
            ptm.setString(3, user.getuTime());
            ptm.setString(4, user.getTcode());
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
	}
	
	// �����ݿ��grade�в�������
	public void insertGrade(User user) {
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO grade (username,grade) VALUES (?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, user.getUsername());
            ptm.setInt(2, user.getGrade());
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
	}

	// �����ݿ��grade��ͨ���û������Ҷ�Ӧ�ķ���
	public String selectScoreByUsername(String username) throws NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = DBLink.getConn();
        String sql = "SELECT grade FROM grade WHERE username = (?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String grade = null;
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, username);
            rs = ptm.executeQuery();
            while (rs.next()) {
            	grade = rs.getString("grade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
        return grade;
	}

}