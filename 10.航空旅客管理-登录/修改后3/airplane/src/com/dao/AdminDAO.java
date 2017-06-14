package com.dao;

import java.util.List;

import com.entity.Admin;

public class AdminDAO {
 
    public Boolean loginConfirm(String name, String password, List<Admin> adminList) {
        for(Admin admin : adminList) {
        	if(admin.getUserName().equals(name)&&admin.getPassword().equals(password)) {
        		return true;
        	}
        }
        return false;
    }
}