package mysql;
import java.sql.*;
import java.util.*;

public class StudentDAOImpl implements StudentDAO{
	private Connection connection = null;
	
	public StudentDAOImpl(Connection connection){
		this.connection = connection;
	}
	
	public boolean doCreate(Student student) throws Exception{
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO student(sno,name,sex,age,birthday,phoneNum) VALUES (?,?,?,?,?,?)";
		try
		{
			pstmt = this.connection.prepareStatement(sql);
			pstmt.setInt(1,student.getSno());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getSex());
			pstmt.setInt(4, student.getAge());
			pstmt.setDate(5,new java.sql.Date(student.getBirthday().getTime()));
			pstmt.setString(6, student.getPhoneNum());
			
			int len = pstmt.executeUpdate();
			if(len > 0)
			{
				flag = true;
			}
		}catch(Exception ex){
			throw ex;
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception ex)
			{
				throw ex;
			}
		}
		return flag;
	}

	public boolean doDelete(int sno) throws Exception{
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM student WHERE sno = ?";
		try
		{
			pstmt = this.connection.prepareStatement(sql);
			pstmt.setInt(1, sno);
			
			int len = pstmt.executeUpdate();
			if(len > 0)
			{
				flag = true;
			}
		}catch(Exception ex){
			throw ex;
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception ex)
			{
				throw ex;
			}
		}
		return flag;
	}
	
	public boolean doUpdate(Student student) throws Exception{
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE student SET name=?,sex=?,age=?,birthday=?,phoneNum=? WHERE sno=?";
		try
		{
			pstmt = this.connection.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getSex());
			pstmt.setInt(3, student.getAge());
			pstmt.setDate(4,new java.sql.Date(student.getBirthday().getTime()));
			pstmt.setString(5, student.getPhoneNum());
			pstmt.setInt(6, student.getSno());
			
			int len = pstmt.executeUpdate();
			if(len > 0)
			{
				flag = true;
			}
		}catch(Exception ex){
			throw ex;
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception ex)
			{
				throw ex;
			}
		}
		return flag;
	}
	
	public List<Student> findAll(String keyWord) throws Exception{
		List<Student> all = new ArrayList<Student>();
		PreparedStatement pstmt = null;
		String sql = "SELECT sno,name,sex,age,birthday,phoneNum FROM student " +
				"WHERE name LIKE ? OR sex LIKE ? OR age LIKE ? OR birthday LIKE ? OR phoneNum LIKE ?";
		try
		{
			pstmt = this.connection.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			pstmt.setString(2, "%" + keyWord + "%");
			pstmt.setString(3, "%" + keyWord + "%");
			pstmt.setString(4,"%" + keyWord + "%");
			pstmt.setString(5, "%" + keyWord + "%");
			ResultSet rs = pstmt.executeQuery();
			Student stu = null;
			while(rs.next())
			{
				stu = new Student();
				stu.setSno(rs.getInt(1));
				stu.setName(rs.getString(2));
				stu.setSex(rs.getString(3));
				stu.setAge(rs.getInt(4));
				stu.setBirthday(rs.getDate(5));
				stu.setPhoneNum(rs.getString(6));
				all.add(stu);
			}
			
		}catch(Exception ex){
			throw ex;
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception ex)
			{
				throw ex;
			}
		}
		return all;
	}
	
	public Student findById(int sno) throws Exception{
		Student stu = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT sno,name,sex,age,birthday,phoneNum FROM student WHERE sno=?";
		try
		{
			pstmt = this.connection.prepareStatement(sql);
			pstmt.setInt(1, sno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				stu = new Student();
				stu.setSno(rs.getInt(1));
				stu.setName(rs.getString(2));
				stu.setSex(rs.getString(3));
				stu.setAge(rs.getInt(4));
				stu.setBirthday(rs.getDate(5));
				stu.setPhoneNum(rs.getString(6));
			}
			
		}catch(Exception ex){
			throw ex;
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception ex)
			{
				throw ex;
			}
		}
		return stu;
	}
	
	
}
