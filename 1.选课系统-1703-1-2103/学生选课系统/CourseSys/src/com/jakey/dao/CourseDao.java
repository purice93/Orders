package com.jakey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jakey.model.Course;
import com.jakey.util.StringUtil;



public class CourseDao {
	public int courseAdd(Connection con,Course course)throws Exception{
		String sql="insert into t_course value(null,?,?,?,?,0)";
		PreparedStatement  pstmt=con.prepareStatement(sql);
		pstmt.setString(1,course.getCourseName());
		pstmt.setString(2, course.getCourseTime());
		pstmt.setString(3, course.getCourseTeacher());
		pstmt.setInt(4, course.getCapacity());
		return pstmt.executeUpdate();

	}
	public ResultSet courseList(Connection con,Course course) throws SQLException{
		StringBuffer sb=new StringBuffer("select * from t_course");
		if(StringUtil.isNotEmpty(course.getCourseName())){
			sb.append(" and courseName like '%"+course.getCourseName()+"%'");
		}
		if(StringUtil.isNotEmpty(course.getCourseTime())){
			sb.append(" and courseTime like '%"+course.getCourseTime()+"%'");
		}
		if(StringUtil.isNotEmpty(course.getCourseTeacher())){
			sb.append(" and courseTeacher like '%"+course.getCourseTeacher()+"%'");
		}
PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
return pstmt.executeQuery();
		}
	public int courseDelete(Connection con,String courseId)throws Exception{
		String sql="delete from t_course where courseId=?";
		PreparedStatement  pstmt=con.prepareStatement(sql);
		pstmt.setString(1, courseId);
		return pstmt.executeUpdate();

	}
	public int courseModify(Connection con,Course course)throws Exception{
		String sql="update t_course set courseName=?,courseTime=?,courseTeacher=?,capacity=? where courseId=? ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, course.getCourseName());
		pstmt.setString(2, course.getCourseTime());
		pstmt.setString(3, course.getCourseTeacher());
		pstmt.setInt(4,course.getCapacity() );
		pstmt.setInt(5, course.getCourseId());
		return pstmt.executeUpdate();
		
	}
	public ResultSet UnderFullList(Connection con,Course course) throws SQLException{
		String sql="select * from t_course where capacity>numSelected";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	}


