/**
 * @program  ��ѡ�γ���Ϣ�����ݿ���в�������
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
    // �����ݿ������һ��ѧ��ѡ�ε���Ϣ
    public int addStudentSelectCourseInformation(String studentID, String courseID) {
        // ȡ�����ݿ��ĸ�ѧ��ѡ�ε�����
        int count = 0;
 
        // 1.�������ݿ����Ӷ���
        Connection conn = DBLink.getConn();
 
        // 2.д��������ݵ�sql���
        String sql = "INSERT INTO studentcourse (Cno,Sno) VALUES (?,?)";
        String sql2 = "update course set Cselected = Cselected+1 where Cno = ?";
        // �����������ݿ�ı���
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
 
    // ɾ����ѡ�γ�
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
		// ȡ�����ݿ��ĸ�ѧ��ѡ�ε�����
        int count = 0;
 
        // 1.�������ݿ����Ӷ���
        Connection conn = DBLink.getConn();
 
        // 2.д��������ݵ�sql���
        String sql = "SELECT Sno FROM studentcourse WHERE Sno = ? AND Cno = ?";
        // �����������ݿ�ı���
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