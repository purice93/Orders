package mysql;

import java.util.List;

public class StudentDAOProxy implements StudentDAO{
	
	private DatabaseConnection dbc = null;
	private StudentDAO dao = null;
	
	public StudentDAOProxy(){
		this.dbc = new DatabaseConnection();
		this.dao = new StudentDAOImpl(this.dbc.getConnection());			
	}
	
	public boolean doCreate(Student student) throws Exception{
		boolean flag = false;
		try
		{
			flag = this.dao.doCreate(student);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			this.dbc.close();
		}
		return flag;
	}
	
	public boolean doDelete(int sno) throws Exception{
		boolean flag = false;
		try
		{
			flag = this.dao.doDelete(sno);
		}
		catch(Exception ex)
		{
			throw ex;
		}
		finally
		{
			this.dbc.close();
		}
		return flag;
	}
	
	public boolean doUpdate(Student student) throws Exception{
		boolean flag = false;
		try
		{
			flag = this.dao.doUpdate(student);
		}
		catch(Exception ex)
		{
			throw ex;
		}
		finally
		{
			this.dbc.close();
		}
		return flag;
	} 
	
	public Student findById(int sno) throws Exception{
		Student stu = null;
		try
		{
			stu = this.dao.findById(sno);
		}
		catch(Exception ex)
		{
			throw ex;
		}
		finally
		{
			this.dbc.close();
		}
		return stu;
	}
	
	public List<Student> findAll(String keyWord) throws Exception{
		List<Student> all = null;
		try
		{
			all = this.dao.findAll(keyWord);
		}
		catch(Exception ex)
		{
			throw ex;
		}
		finally
		{
			this.dbc.close();
		}
		return all;
	}
	
	
}
