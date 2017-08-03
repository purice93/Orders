package com.lero.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

import com.lero.model.DormBuild;
import com.lero.model.Instructor;
import com.lero.model.Record;
import com.lero.model.Student;
import com.lero.util.StringUtil;

public class RecordDao {
	public List<Record> recordList(Connection con, Record s_record)throws Exception {
		List<Record> recordList = new ArrayList<Record>();
		StringBuffer sb = new StringBuffer("select * from t_record t1");
		if(StringUtil.isNotEmpty(s_record.getStudentNumber())) {
			sb.append(" and t1.studentNumber like '%"+s_record.getStudentNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_record.getStudentName())) {
			sb.append(" and t1.studentName like '%"+s_record.getStudentName()+"%'");
		}
		if(s_record.getDormBuildId()!=0) {
			sb.append(" and t1.dormBuildId="+s_record.getDormBuildId());
		}
		if(StringUtil.isNotEmpty(s_record.getDate())) {
			sb.append(" and t1.date="+s_record.getDate());
		}
		if(StringUtil.isNotEmpty(s_record.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_record.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_record.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_record.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Record record=new Record();
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<Record> recordListWithBuild(Connection con, Record s_record, int buildId)throws Exception {
		List<Record> recordList = new ArrayList<Record>();
		StringBuffer sb = new StringBuffer("select * from t_record t1");
		if(StringUtil.isNotEmpty(s_record.getStudentNumber())) {
			sb.append(" and t1.studentNumber like '%"+s_record.getStudentNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_record.getStudentName())) {
			sb.append(" and t1.studentName like '%"+s_record.getStudentName()+"%'");
		}
		sb.append(" and t1.dormBuildId="+buildId);
		if(StringUtil.isNotEmpty(s_record.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_record.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_record.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_record.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Record record=new Record();
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<Record> recordListWithNumber(Connection con, Record s_record, String studentNumber)throws Exception {
		List<Record> recordList = new ArrayList<Record>();
		StringBuffer sb = new StringBuffer("select * from t_record t1");
		if(StringUtil.isNotEmpty(studentNumber)) {
			sb.append(" and t1.studentNumber ="+studentNumber);
		} 
		if(StringUtil.isNotEmpty(s_record.getStartDate())){
			sb.append(" and TO_DAYS(t1.date)>=TO_DAYS('"+s_record.getStartDate()+"')");
		}
		if(StringUtil.isNotEmpty(s_record.getEndDate())){
			sb.append(" and TO_DAYS(t1.date)<=TO_DAYS('"+s_record.getEndDate()+"')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Record record=new Record();
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
			recordList.add(record);
		}
		return recordList;
	}
	
	public List<DormBuild> dormBuildList(Connection con)throws Exception {
		List<DormBuild> dormBuildList = new ArrayList<DormBuild>();
		String sql = "select * from t_dormBuild";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DormBuild dormBuild=new DormBuild();
			dormBuild.setDormBuildId(rs.getInt("dormBuildId"));
			dormBuild.setDormBuildName(rs.getString("dormBuildName"));
			dormBuild.setDetail(rs.getString("dormBuildDetail"));
			dormBuildList.add(dormBuild);
		}
		return dormBuildList;
	}
//	
//	public int studentCount(Connection con, Student s_student)throws Exception {
//		StringBuffer sb = new StringBuffer("select count(*) as total from t_student t1");
//		if(StringUtil.isNotEmpty(s_student.getName())) {
//			sb.append(" and t1.name like '%"+s_student.getName()+"%'");
//		} else if(StringUtil.isNotEmpty(s_student.getStuNumber())) {
//			sb.append(" and t1.stuNum like '%"+s_student.getStuNumber()+"%'");
//		} else if(StringUtil.isNotEmpty(s_student.getDormName())) {
//			sb.append(" and t1.dormName like '%"+s_student.getDormName()+"%'");
//		}
//		if(s_student.getDormBuildId()!=0) {
//			sb.append(" and t1.dormBuildId="+s_student.getDormBuildId());
//		}
//		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
//		ResultSet rs = pstmt.executeQuery();
//		if(rs.next()) {
//			return rs.getInt("total");
//		} else {
//			return 0;
//		}
//	}
	
	public Record recordShow(Connection con, String recordId)throws Exception {
		String sql = "select * from t_record t1 where t1.recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, recordId);
		ResultSet rs=pstmt.executeQuery();
		Record record = new Record();
		if(rs.next()) {
			record.setRecordId(rs.getInt("recordId"));
			record.setStudentNumber(rs.getString("studentNumber"));
			record.setStudentName(rs.getString("studentName"));
			int dormBuildId = rs.getInt("dormBuildId");
			record.setDormBuildId(dormBuildId);
			record.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			record.setDormName(rs.getString("dormName"));
			record.setDate(rs.getString("date"));
			record.setDetail(rs.getString("detail"));
		}
		return record;
	}
	
	public int recordAdd(Connection con, Record record)throws Exception {
		String sql = "insert into t_record values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, record.getStudentNumber());
		pstmt.setString(2, record.getStudentName());
		pstmt.setInt(3, record.getDormBuildId());
		pstmt.setString(4, record.getDormName());
		pstmt.setString(5, record.getDate());
		pstmt.setString(6, record.getDetail());
		return pstmt.executeUpdate();
	}
	
	public int recordDelete(Connection con, String recordId)throws Exception {
		String sql = "delete from t_record where recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, recordId);
		return pstmt.executeUpdate();
	}
	
	public int recordUpdate(Connection con, Record record)throws Exception {
		String sql = "update t_record set studentNumber=?,studentName=?,dormBuildId=?,dormName=?,detail=? where recordId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, record.getStudentNumber());
		pstmt.setString(2, record.getStudentName());
		pstmt.setInt(3, record.getDormBuildId());
		pstmt.setString(4, record.getDormName());
		pstmt.setString(5, record.getDetail());
		pstmt.setInt(6, record.getRecordId());
		return pstmt.executeUpdate();
	}

	/**
	 * 发邮件
	 * @param user
	 * @throws Exception 
	 */
	public void sendEmail(Connection con, String studentNumber) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * 查找学生姓名及其所属导员
		 * 
		 */
		StudentDao studentDao = new StudentDao();
		Student student = studentDao.getStudentByNumber(con, studentNumber);
		InstructorDao instructorDao =  new InstructorDao();
		Instructor instructor = instructorDao.getNameById(con, student.getInstructorId());
		/*
		 * 发邮件
		 */
		/*
		 * 把配置文件内容加载到prop中
		 */
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		/*
		 * 登录邮件服务器，得到session
		 */
		String host = prop.getProperty("host");//服务器主机名
		String name = prop.getProperty("username");//登录名
		String pass = prop.getProperty("password");//登录密码
		Session session = MailUtils.createSession(host, name, pass);
		
		/*
		 * 创建Mail对象
		 */
		String from = prop.getProperty("from");
		String to = instructor.getInstructorEmail();
		String subject = prop.getProperty("subject");
		// MessageForm.format方法会把第一个参数中的{0},使用第二个参数来替换。
		// 例如MessageFormat.format("你好{0}, 你{1}!", "张三", "去死吧"); 返回“你好张三，你去死吧！”
		String content = MessageFormat.format(prop.getProperty("content"), instructor.getInstructorName(),student.getName());
		Mail mail = new Mail(from, to, subject, content);
		/*
		 * 发送邮件
		 */
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
