package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDAO;
import dao.UserDAO;
import entity.User;

public class UserDAOImpl extends BaseDAO implements UserDAO {

	public List<User> findAll() throws Exception {
		Connection conn = null;
		List<User> users = new ArrayList<User>();
		try {
			conn = getConnection();
			PreparedStatement prep = conn
					.prepareStatement("select * from f_user");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String phone = rs.getString("phone");
				String ask = rs.getString("ask");
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPwd(pwd);
				user.setName(name);
				user.setAge(age);
				user.setGender(gender);
				user.setPhone(phone);
				user.setAsk(ask);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}
		return users;
	}

//	public void delete(int id) throws Exception {
//		Connection conn = null;
//		try {
//			conn = getConnection();
//			PreparedStatement prep = conn
//					.prepareStatement("delete from f_user where id=?");
//			prep.setInt(1, id);
//			prep.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			closeConnection();
//		}
//
//	}

	public int save(User user) throws Exception {
		Connection conn = null;
		int id=-1;
		try {
			conn = getConnection();
			PreparedStatement prep=conn.prepareStatement("insert into f_user(username,pwd,name,age,gender,phone,ask) values (?,?,?,?,?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, user.getUsername());
			prep.setString(2, user.getPwd());
			prep.setString(3, user.getName());
			prep.setInt(4,user.getAge());
			prep.setString(5, user.getGender());
			prep.setString(6, user.getPhone());
			prep.setString(7, user.getAsk());
			prep.executeUpdate();
			ResultSet rs=prep.getGeneratedKeys();
			if(rs.next()){
				id=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}
		return id;
	}

	public User findById(int id) throws Exception {
		Connection conn = null;
		User user = null;
		try {
			conn = getConnection();
			PreparedStatement prep = conn
					.prepareStatement("select * from f_user where id=?");
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String username = rs.getString("username");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String phone = rs.getString("phone");
				String ask = rs.getString("ask");
				user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPwd(pwd);
				user.setName(name);
				user.setAge(age);
				user.setGender(gender);
				user.setPhone(phone);
				user.setAsk(ask);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}
		return user;
	}
	public User findByUsername(String username) throws Exception{
		Connection conn=null;
		User user=null;
		try {
			conn=getConnection();
			PreparedStatement prep=conn.prepareStatement("select * from f_user where username=?");
			prep.setString(1, username);
			ResultSet rs=prep.executeQuery();
			if(rs.next()){
				user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(username);
				user.setPwd(rs.getString("pwd"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setAge(rs.getInt("age"));
				user.setPhone(rs.getString("phone"));
				user.setAsk(rs.getString("ask"));	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			closeConnection();
		}
		
		return user;
	}


	public void modify(User user) throws Exception {
		Connection conn=null;
		try {
			conn=getConnection();
			PreparedStatement prep=conn.prepareStatement("update f_user set pwd=?,phone=?,ask=? where id=?");
			prep.setString(1,user.getPwd());
			prep.setString(2, user.getPhone());
			prep.setString(3, user.getAsk());
			prep.setInt(4, user.getId());
			prep.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			closeConnection();
		}
	}

}
