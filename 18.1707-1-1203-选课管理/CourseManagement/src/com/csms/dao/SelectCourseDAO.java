/**
 * @program  已选课程信息与数据库进行操作的类
 *
 */

package com.csms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.csms.DBLink;
import com.csms.entity.SelectCourse;
import com.csms.entity.Student;

public class SelectCourseDAO {
	public static String getStudentIdByCourse(String courID) {
		// 设置学生对象
		Student student = null;

		// 1.取得数据库连接的对象conn
		Connection conn = DBLink.getConn();

		// 2. 查询全体学生的sql语句
		String sql = "SELECT Sno FROM studentcourse WHERE Cno=?";

		PreparedStatement ptm = null;
		ResultSet rs = null;
		try {
			// 3.将查询语句发送给数据库,执行查询操作
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, courID);
			rs = ptm.executeQuery();

			student = new Student();
			// 逐条记录进行查询
			while (rs.next()) {
				student.setLoginName(rs.getString("Sno"));
			}

			// 关闭查询指针链接
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4. 操作完成，关闭连接
			try {
				if (ptm != null)
					ptm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return student.getLoginName();
	}

	public static void insert(SelectCourse sc) {
		// 取得数据库表的该学生选课的门数
		int count = 0;

		// 1.创建数据库连接对象
		Connection conn = DBLink.getConn();

		// 2.写出添加数据的sql语句
		String sql = "INSERT INTO studentcourse (Sno,Cno) VALUES (?,?)";
		// 设置连接数据库的变量
		PreparedStatement ptm = null;

		try {
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, sc.getStudengID());
			ptm.setString(2, sc.getCourseID());
			ptm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptm != null)
					ptm.close();
				count++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void delete(String courseID) {
		Connection conn = DBLink.getConn();
		String sql = "DELETE FROM studentcourse WHERE Cno=?";
		PreparedStatement ptm = null;
		try {
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, courseID);
			ptm.execute();
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
	}

	public static int modify(String courseID,String mcourseID) {
		int i=0;  
	    String sql="UPDATE studentcourse SET  Cno=? WHERE Cno=?";
		Connection conn = DBLink.getConn(); 
	    try{  
	        PreparedStatement preStmt =conn.prepareStatement(sql);  
	        preStmt.setString(1,mcourseID);  
	        preStmt.setString(2,courseID);
	        i=preStmt.executeUpdate();  
	    }  
	    catch (SQLException e)  
	    {  
	        e.printStackTrace();  
	    }  
	    return i;
	}

	public static String find(String courseID) {
		String courseName = null;
		// 1.取得数据库连接的对象conn
		Connection conn = DBLink.getConn();

		// 2. 查询全体学生的sql语句
		String sql = "SELECT Cname FROM course WHERE Cno=?";

		PreparedStatement ptm = null;
		ResultSet rs = null;
		try {
			// 3.将查询语句发送给数据库,执行查询操作
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, courseID);
			rs = ptm.executeQuery();
			// 逐条记录进行查询
			while (rs.next()) {
				courseName = rs.getString("Cname");
				return courseName;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4. 操作完成，关闭连接
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