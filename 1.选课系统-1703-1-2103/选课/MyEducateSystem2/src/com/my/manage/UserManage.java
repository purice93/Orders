package com.my.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import com.my.beans.DataBase;
import com.my.beans.Record;
import com.my.beans.Student;
import com.my.beans.Teacher;
import com.my.beans.User;
import com.my.util.Tools;

public class UserManage {
	private static DataBase dataBase=null;
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private User user=null;
	//调用tools类解析配置文件////////////////////////////////////////////
	static {
		dataBase=Tools.getProperties();
	}
	public Connection getConn() {
		return conn;
	}
	public PreparedStatement getPs() {
		return ps;
	}
	
	public User getUser() {
		return user;
	}
	public void closeConnection()
	{if(this.conn!=null)
		this.conn=null;}
//	public void setUserNull(){if(this.user!=null)this.user=null;}
	public User login(String uname,String upwd){
		//调用tools类连接数据库 ，读取User表//////////////////////////
		//Class.forName("org.sqlite.JDBC")  d:/newJavaChengXu/MyEducateSystem/
		//DriverManager.getConnection("jdbc:sqlite:educateSystem.db")
		/*<database name="SQL Server">
			<driver>com.microsoft.sqlserver.jdbc.SQLServerDriver</driver>
			<protocal>jdbc:sqlserver://localhost:1433;</protocal>
			<dbUrl>educateSystem.db</dbUrl>
		</database>*/
		 
		try {
			Class.forName(dataBase.getDriver());
			conn=Tools.connectDataBase(dataBase.getProtocal()+dataBase.getDbUrl());
			
			/*Class.forName("org.sqlite.JDBC");
			conn=Tools.connectDataBase("jdbc:sqlite:educateSystem.db");*/
			
			
			//发送sql语句
			Object params[]={uname,upwd};
			//判断用户名和密码，
			sqlBuilde("select *from user1 where uname=? and upwd=?",params);
			
			try {
				 rs=ps.executeQuery();
				 
				if(rs.next())//若存在 //返回user
				{  
					int rr=rs.getInt(3);
					if(rr==2)
					{
						Teacher tea= new Teacher();
						Object params2[]={uname};
						//判断用户名和密码，
						sqlBuilde("select *from teacher where tid=?",params2);
						ResultSet rs2=ps.executeQuery();
						if(rs2.next()){
							tea.setTid(rs2.getString(1));
							tea.setTname(rs2.getString(2));
							tea.setTaca(rs2.getString(4));
							tea.setTsex(rs2.getString(3));
						}
						user=tea;//上转型  在主界面里 下转型
					}
					else
						if(rr==3)
						{
							Student stu=new Student();
							Object params3[]={uname};
							//判断用户名和密码，
							sqlBuilde("select *from student where sid=?",params3);
							ResultSet rs3=ps.executeQuery();
							if(rs3.next()){
								stu.setSid(rs3.getString(1));
								stu.setSname(rs3.getString(2));
								stu.setSaca(rs3.getString(4));
								stu.setSex(rs3.getString(3));
							}
							user=stu;
						}
						else
						{
							user=new User();
						}
					user.setUname(rs.getString(1));
					user.setPwd(rs.getString(2));
					user.setUright(rs.getInt(3));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	public  void sqlBuilde(String sql,Object params[])
	{
		
		try {
			ps=conn.prepareStatement(sql);
//			ps.setQueryTimeout(30);
			if(params!=null){
				for(int i=0;i<params.length;i++)
				{
				ps.setObject(i+1, params[i]);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
