package com.jakey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jakey.model.Course;
import com.jakey.model.Sinfo;
import com.jakey.model.Student;
import com.jakey.util.StringUtil;

public class StudentDao {
	public ResultSet StudentList(Connection con,Sinfo sinfo) throws SQLException{
		StringBuffer sb=new StringBuffer("select * from t_sinfo ");
if(sinfo.getSno()!=-1){
	sb.append(" and Sno="+sinfo.getSno());
}
if(StringUtil.isNotEmpty(sinfo.getSname())){
	sb.append(" and Sname like '%"+sinfo.getSname()+"%'");
}
PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
return pstmt.executeQuery();
		}
	
public ResultSet PasswordList(Connection con,Student student)throws SQLException{
	StringBuffer sb=new StringBuffer("select * from t_slogon ");
	if(student.getSno()!=-1){
		sb.append("where Sno="+student.getSno());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	
}
public int PasswordModify(Connection con,Student student)throws Exception{
	String sql="update t_slogon set Spassword=? where Sno=? ";
	PreparedStatement pstmt=con.prepareStatement(sql);
	pstmt.setString(1, student.getSpassword());
	pstmt.setInt(2, student.getSno());
	return pstmt.executeUpdate();
	
} 

}

