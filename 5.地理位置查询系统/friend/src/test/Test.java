package test;

import java.sql.SQLException;

import util.DAOFactory;
import dao.UserDAO;
import entity.User;

public class Test {
	public static void main(String[] args) throws Exception {
		// Connection conn=UserDAOImpl.getConnection();
		// System.out.println(conn);
		UserDAO userDAO = DAOFactory.getUserDAO();
		User user=userDAO.findById(1);
		System.out.println(user.getName());

	}
}
