package dao;

import java.util.List;

import entity.User;

public interface UserDAO {
	public List<User> findAll() throws Exception;
	//public void delete(int id) throws Exception;
	public int save(User user) throws Exception;
	public User findById(int id) throws Exception;
	public User findByUsername(String username) throws Exception;
	public void modify(User user) throws Exception;
}
