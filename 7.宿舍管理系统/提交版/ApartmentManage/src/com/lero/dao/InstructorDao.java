package com.lero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lero.model.Instructor;
import com.lero.model.DormManager;
import com.lero.model.PageBean;
import com.lero.util.StringUtil;

public class InstructorDao {

	public List<Instructor> instructorList(Connection con, PageBean pageBean, Instructor s_instructor)throws Exception {
		List<Instructor> instructorList = new ArrayList<Instructor>();
		StringBuffer sb = new StringBuffer("select * from t_instructor t1");
		if(StringUtil.isNotEmpty(s_instructor.getInstructorName())) {
			sb.append(" where t1.instructorName like '%"+s_instructor.getInstructorName()+"%'");
		}
		if(pageBean != null) {
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Instructor instructor=new Instructor();
			instructor.setInstructorId(rs.getInt("instructorId"));
			instructor.setInstructorName(rs.getString("instructorName"));
			instructor.setInstructorEmail(rs.getString("instructorEmail"));
			instructorList.add(instructor);
		}
		return instructorList;
	}
	
	public static String instructorName(Connection con, int instructorId)throws Exception {
		String sql = "select * from t_instructor where instructorId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, instructorId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getString("instructorName");
		}
		return null;
	}
	
	public int instructorCount(Connection con, Instructor s_instructor)throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_instructor t1");
		if(StringUtil.isNotEmpty(s_instructor.getInstructorName())) {
			sb.append(" where t1.instructorName like '%"+s_instructor.getInstructorName()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	public Instructor instructorShow(Connection con, String instructorId)throws Exception {
		String sql = "select * from t_instructor t1 where t1.instructorId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, instructorId);
		ResultSet rs=pstmt.executeQuery();
		Instructor instructor = new Instructor();
		if(rs.next()) {
			instructor.setInstructorId(rs.getInt("instructorId"));
			instructor.setInstructorName(rs.getString("instructorName"));
			instructor.setInstructorEmail(rs.getString("instructorEmail"));
		}
		return instructor;
	}
	
	public int instructorAdd(Connection con, Instructor instructor)throws Exception {
		String sql = "insert into t_instructor values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, instructor.getInstructorName());
		pstmt.setString(2, instructor.getInstructorEmail());
		return pstmt.executeUpdate();
	}
	
	public int instructorDelete(Connection con, String instructorId)throws Exception {
		String sql = "delete from t_instructor where instructorId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, instructorId);
		return pstmt.executeUpdate();
	}
	
	public int instructorUpdate(Connection con, Instructor instructor)throws Exception {
		String sql = "update t_instructor set instructorName=?,instructorEmail=? where instructorId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, instructor.getInstructorName());
		pstmt.setString(2, instructor.getInstructorEmail());
		pstmt.setInt(3, instructor.getInstructorId());
		return pstmt.executeUpdate();
	}
	
	public boolean existManOrDormWithId(Connection con, String instructorId)throws Exception {
		boolean isExist = false;
		String sql = "select *from t_dormManager where instructorId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, instructorId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			isExist = true;
		} else {
			isExist = false;
		}
		String sql1="select * from t_instructor t1,t_dorm t2 where t1.instructorId=t2.instructorId and t1.instructorId=?";
		PreparedStatement p=con.prepareStatement(sql1);
		p.setString(1, instructorId);
		ResultSet r = pstmt.executeQuery();
		if(r.next()) {
			return isExist;
		} else {
			return false;
		}
	}
	
	public List<DormManager> dormManWithoutBuild(Connection con)throws Exception {
		List<DormManager> dormManagerList = new ArrayList<DormManager>();
		String sql = "SELECT * FROM t_dormManager WHERE instructorId IS NULL OR instructorId=0";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DormManager dormManager=new DormManager();
			dormManager.setDormManagerId(rs.getInt("dormManId"));
			dormManager.setName(rs.getString("name"));
			dormManager.setUserName(rs.getString("userName"));
			dormManager.setSex(rs.getString("sex"));
			dormManager.setTel(rs.getString("tel"));
			dormManagerList.add(dormManager);
		}
		return dormManagerList;
	}
	
	public List<DormManager> dormManWithBuildId(Connection con, String instructorId)throws Exception {
		List<DormManager> dormManagerList = new ArrayList<DormManager>();
		String sql = "select *from t_dormManager where instructorId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, instructorId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DormManager dormManager=new DormManager();
			dormManager.setDormBuildId(rs.getInt("dormBuildId"));
			dormManager.setDormManagerId(rs.getInt("dormManId"));
			dormManager.setName(rs.getString("name"));
			dormManager.setUserName(rs.getString("userName"));
			dormManager.setSex(rs.getString("sex"));
			dormManager.setTel(rs.getString("tel"));
			dormManagerList.add(dormManager);
		}
		return dormManagerList;
	}
	
	public int managerUpdateWithId (Connection con, String dormManagerId, String instructorId)throws Exception {
		String sql = "update t_dormManager set instructorId=? where dormManId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, instructorId);
		pstmt.setString(2, dormManagerId);
		return pstmt.executeUpdate();
	}

	public Instructor getNameById(Connection con, String instructorId) throws SQLException {
		String sql = "select * from t_instructor t1 where t1.instructorId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, instructorId);
		ResultSet rs=pstmt.executeQuery();
		Instructor instructor = new Instructor();
		if(rs.next()) {
			instructor.setInstructorId(rs.getInt("instructorId"));
			instructor.setInstructorName(rs.getString("instructorName"));
			instructor.setInstructorEmail(rs.getString("instructorEmail"));
		}
		return instructor;
	}
}
