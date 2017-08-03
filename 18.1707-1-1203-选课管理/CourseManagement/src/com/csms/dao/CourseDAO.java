package com.csms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.csms.DBLink;
import com.csms.entity.Course;
import com.csms.entity.SelectCourse;
import com.csms.entity.Student;

/**
 * @program 课程信息与数据库进行操作的类
 * 
 */
public class CourseDAO {
	// 查询课程信息
	public List<Course> searchCourseInformation() {
		Course course = null;
		Connection conn = DBLink.getConn();
		String sql = "SELECT sc.Cno,c.Cname FROM studentcourse sc,course c WHERE sc.Cno = c.Cno";
		List<Course> list = new ArrayList<Course>();
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				course = new Course();
				course.setCourID(rs.getString("Cno"));
				course.setCourName(rs.getString("Cname"));
				list.add(course);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null)
					stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 通过课程ID搜索课程信息
	public List<Course> findAllCourse() {
		Connection conn = DBLink.getConn();
		String sql = "SELECT Cno,Cname FROM course";
		List<Course> list = new ArrayList<Course>();

		PreparedStatement ptm = null;
		ResultSet rs = null;
		Course course = null;
		try {
			ptm = conn.prepareStatement(sql);
			rs = ptm.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setCourID(rs.getString("Cno"));
				course.setCourName(rs.getString("Cname"));
				list.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptm != null)
					ptm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static String getNameById(String courseID) {
		Connection conn = DBLink.getConn();
		String sql = "SELECT Cno FROM course where Cno=?";
		List<Course> list = new ArrayList<Course>();
		String courseName = null;
		PreparedStatement ptm = null;
		ResultSet rs = null;
		Course course = null;
		Student student = null;
		SelectCourse sc = null;

		try {
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, courseID);
			rs = ptm.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setCourID(rs.getString("Cname"));
				return course.getCourID();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptm != null)
					ptm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return courseName;
	}

}