package util;

import dao.PicDAO;
import dao.UserDAO;
import dao.impl.PicDAOImpl;
import dao.impl.UserDAOImpl;

public class DAOFactory {
	private static PicDAO picDAO = new PicDAOImpl();
	private static UserDAO userDAO = new UserDAOImpl();

	public static PicDAO getPicDAO() {
		return picDAO;
	}

	public static UserDAO getUserDAO() {
		return userDAO;
	}
}
