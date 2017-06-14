package com.css.service.impl;

import com.css.hib.Admin;
import com.css.hib.AdminDAO;
import com.css.service.AdminloginService;

public class AdminloginSerivceImpl implements AdminloginService {

	private AdminDAO dao;

	public boolean checkLogin(Admin user) {
		dao = new AdminDAO();
		dao.findByExample(user);
		if (dao.findByExample(user).size() >= 1) {
			return true;
		} else {
			return false;
		}
	}

}
