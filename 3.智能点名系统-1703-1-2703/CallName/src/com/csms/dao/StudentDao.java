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
import com.csms.entity.Student;
 

 
public class StudentDao {
	public void insertStudent(Student st) {
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO student (Sid,Sname,Spic,selectNumber,lateNumber) VALUES (?,?,?,?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, st.getStudentId());
            ptm.setString(2, st.getStudentName());
            ptm.setString(3, st.getStudentPic());
            ptm.setInt(4, st.getCalledNumber());
            ptm.setInt(5, st.getLateNumber());
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
	}

}