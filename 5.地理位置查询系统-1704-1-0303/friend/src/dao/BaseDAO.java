package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class BaseDAO {
	private static BasicDataSource ds;
	private static ThreadLocal<Connection> tl;
	static {
		Properties props = new Properties();
		try {
			props.load(BaseDAO.class.getClassLoader().getResourceAsStream(
					"dao/db.properties"));
			String driver = props.getProperty("jdbc.driver");
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");

			ds = new BasicDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			ds.setInitialSize(5);
			ds.setMaxActive(100);
			ds.setMaxIdle(20);
			tl = new ThreadLocal<Connection>();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	public static Connection openConnection() throws SQLException{
		return ds.getConnection();
	}
	public static Connection getConnection()throws SQLException{
		Connection conn=tl.get();
		if(conn==null){
			conn=openConnection();
			tl.set(conn);
		}
		return conn;
	}
	public static void closeConnection() throws SQLException{
		Connection conn=tl.get();
		if(conn!=null){
			conn.close();
		}
		tl.set(null);
	}
	public static void begin()throws SQLException{
		Connection conn=tl.get();
		conn.setAutoCommit(false);
	}
	public static void commit()throws SQLException{
		Connection conn=tl.get();
		conn.commit();
	}
	public static void rollback()throws SQLException{
		Connection conn=tl.get();
		conn.rollback();
	}
}
