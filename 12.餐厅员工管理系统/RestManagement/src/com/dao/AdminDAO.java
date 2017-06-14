package com.dao;
/*
 * 管理员数据库操作类
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.windows.DBLink;

public class AdminDAO {

	// 登录确认
	public Boolean loginConfirm(String name, String password) {
		boolean flag = false;
		Connection conn = DBLink.getConn();
		String sql = "SELECT * FROM admin WHERE id = (?) AND password = (?)";

		PreparedStatement ptm = null;
		ResultSet rs = null;

		try {
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, name);
			ptm.setString(2, password);
			rs = ptm.executeQuery();
			while (rs.next()) {
				if (rs.getString("id").equals(name) && rs.getString("password").equals(password)) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return flag;
	}
}