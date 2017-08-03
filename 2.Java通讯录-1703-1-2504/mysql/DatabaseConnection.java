package mysql;

/*
 * mysql数据库连接
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	//数据库连接信息
	private Connection connection = null;
	
	public DatabaseConnection(){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test"
					,"root","root");
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		catch (ClassNotFoundException ex) 
		{
			ex.printStackTrace();
		}
	}
		
		public Connection getConnection(){
			return this.connection;
		}
		
		public void close(){
			if(this.connection != null)
			{
				try
				{
					this.connection.close();
				}
				catch(SQLException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		
}
