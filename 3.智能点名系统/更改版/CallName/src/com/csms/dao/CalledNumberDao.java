package com.csms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csms.DBLink;
import com.csms.entity.CalledNumber;

public class CalledNumberDao {
	private static CalledNumber calledNumber = null;

	public int getNumber() {
		Connection conn = DBLink.getConn();
		String sql = "SELECT calledNumber FROM callednumber WHERE class = '1'";

		Statement stm = null;
		ResultSet rs = null;
		CalledNumberDao cnum = null;

		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				calledNumber = new CalledNumber();
				calledNumber.setCalledNumber(Integer.valueOf(rs.getString("calledNumber")) + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return calledNumber.getCalledNumber();
	}

	public void addOneNum() {
		Connection conn = DBLink.getConn();
		String sql = "UPDATE callednumber set calledNumber = ?";

		PreparedStatement ptm = null;
		ResultSet rs = null;
		CalledNumberDao cnum = new CalledNumberDao();
		

		try {
			ptm = conn.prepareStatement(sql);
            ptm.setString(1, String.valueOf(calledNumber.getCalledNumber()));
			ptm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}
}
