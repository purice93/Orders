/**
 * @program  已选课程信息与数据库进行操作的类
 *
 */
 
package com.csms.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.csms.DBLink;
import com.csms.entity.Course;
import com.csms.entity.SelectCourse;
import com.csms.entity.Student;
 
public class SelectCourseDAO {
    // 向数据库中添加一条学生选课的信息
    public int addStudentSelectCourseInformation(String studentID, String courseID) {
        // 取得数据库表的该学生选课的门数
        int count = 0;
 
        // 1.创建数据库连接对象
        Connection conn = DBLink.getConn();
 
        // 2.写出添加数据的sql语句
        String sql = "INSERT INTO studentcourse (Cno,Sno) VALUES (?,?)";
        String sql2 = "update course set Cselected = Cselected+1 where Cno = ?";
        // 设置连接数据库的变量
        PreparedStatement ptm = null;
        PreparedStatement ptm2 = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, courseID);
            ptm.setString(2, studentID);


            ptm2 = conn.prepareStatement(sql2);
            ptm2.setString(1, courseID);

            ptm2.execute();
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
 
        return count;
    }
 
    // 删除已选课程
    public void removeCourse(String courseID) {
        Connection conn = DBLink.getConn();
        String sql = "DELETE FROM selectcourse WHERE courseID=?";
        PreparedStatement ptm = null;
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, courseID);
            ptm.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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


	public int getById(String studentID, String courseID) {
		// 取得数据库表的该学生选课的门数
        int count = 0;
 
        // 1.创建数据库连接对象
        Connection conn = DBLink.getConn();
 
        // 2.写出添加数据的sql语句
        String sql = "SELECT Sno FROM studentcourse WHERE Sno = ? AND Cno = ?";
        // 设置连接数据库的变量
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, studentID);
            ptm.setString(2, courseID);
            rs = ptm.executeQuery();
            while (rs.next()) {
            	count++;
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
        
        return count;
	}

	public List<Course> getNumById(String courseID) {
		// TODO Auto-generated method stub
        Connection conn = DBLink.getConn();
        String sql = "SELECT * FROM course WHERE Cno =?";
        List<Course> list = new ArrayList<Course>();
 
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
                course.setCsum(Integer.parseInt(rs.getString("Csum")));
                course.setCselected(Integer.parseInt(rs.getString("Cselected")));
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

}