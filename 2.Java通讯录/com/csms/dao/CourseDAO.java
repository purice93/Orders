package com.csms.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.JTextField;
 
import com.csms.DBLink;
import com.csms.entity.Course;
import com.csms.entity.SelectCourse;
import com.csms.entity.Student;
 
/**
 * @program  �γ���Ϣ�����ݿ���в�������
 *
 */
 
public class CourseDAO {
    // ��ѯ�γ���Ϣ
    public List<Course> searchCourseInformation() {
        Course course = null;
        Connection conn = DBLink.getConn();
        String sql = "SELECT Cno,Cname,Csum,Cselected FROM course";
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
                course.setCsum(Integer.parseInt(rs.getString("Csum")));
                course.setCselected(Integer.parseInt(rs.getString("Cselected")));
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
 
    // ɾ���γ���Ϣ
    public void deleteCourseInformation(String id) {
        Connection conn = DBLink.getConn();
        String sql = "DELETE FROM course WHERE Cno=?";
        System.out.println(id);
        PreparedStatement ptm = null;
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, id);
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

    // ��ӿγ�
	public void insertCourse(Course ca) {
		// TODO Auto-generated method stub
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO course (Cno,Cname,Csum) VALUES (?,?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, ca.getCourID());
            ptm.setString(2, ca.getCourName());
            ptm.setInt(3, ca.getCsum());
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
	}
	
	// �������еĿγ�
	public List<Course> searchAllCourse() {
		List<Course> list = new ArrayList<Course>();
		Course ca = null;
        Connection conn = DBLink.getConn();
        String sql = "SELECT Cno,Cname,Csum,Cselected FROM course";
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            ptm = conn.prepareStatement(sql);
            rs = ptm.executeQuery();
            while(rs.next()){
                ca = new Course();
                ca.setCourID(rs.getString("Cno"));
                ca.setCourName(rs.getString("Cname"));
                ca.setCsum(Integer.parseInt(rs.getString("Csum")));
                ca.setCselected(Integer.parseInt(rs.getString("Cselected")));
                list.add(ca);
            }
            rs.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(ptm!=null){
                    ptm.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return list;
	}
	
	// ���ݿγ�IDɾ���ſ���Ϣ
	public void deleteArrageCourse(String arrangeId) {
		Connection conn = DBLink.getConn();
        String sql = "DELETE FROM course WHERE Cno=?";
        PreparedStatement ptm = null;
        try{
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, arrangeId);
            ptm.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(ptm != null){
                    ptm.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
	}

	// ͨ���γ�ID�����γ���Ϣ
	public List<Course> searchCourseInformationById(String courseID) {
        Connection conn = DBLink.getConn();
        String sql = "SELECT Cno,Cname,Csum,Cselected FROM course where Cno=?";
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
                course.setCourID(rs.getString("Cno"));
                course.setCourName(rs.getString("Cname"));
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