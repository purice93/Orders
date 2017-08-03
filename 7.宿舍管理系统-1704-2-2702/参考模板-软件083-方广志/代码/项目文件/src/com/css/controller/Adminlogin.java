package com.css.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.css.hib.Admin;
import com.css.hib.AdminDAO;
import com.css.hib.BaseHibernateDAO;
import com.css.hib.RoomDAO;
import com.css.service.AdminloginService;
import com.css.service.impl.AdminloginSerivceImpl;


public class Adminlogin {
	private String id;
	
	private String adminid;
	
	private String adminname;
	private String adminpassword;
	
	private String radminname;
	private String radminpassword;
	
	private String fadminname;
	private String fadminpassword;
	
	private List list = new ArrayList();

	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getFadminname() {
		return fadminname;
	}
	public void setFadminname(String fadminname) {
		this.fadminname = fadminname;
	}
	public String getFadminpassword() {
		return fadminpassword;
	}
	public void setFadminpassword(String fadminpassword) {
		this.fadminpassword = fadminpassword;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRadminname() {
		return radminname;
	}
	public void setRadminname(String radminname) {
		this.radminname = radminname;
	}
	public String getRadminpassword() {
		return radminpassword;
	}
	public void setRadminpassword(String radminpassword) {
		this.radminpassword = radminpassword;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	
	public String adminlogin(){
		AdminloginService ls=new AdminloginSerivceImpl();		
		Admin user=new Admin();		
		user.setAdminname(this.getAdminname());
		user.setAdminpassword(this.getAdminpassword());

		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("loginuser", user);
		
		if(ls.checkLogin(user)){
			return "okok";
		}
		else{
			return "nono";
		}
	}

	public String execute() {

		AdminDAO dao=new AdminDAO();
		//查询，查数据
		list=dao.findAll();
		
		return "toTrue";
	}
	
	public String updateAdmin(){

		// 得到请求参数id的值
		id = ServletActionContext.getRequest().getParameter("id");
		
		int num = Integer.parseInt(id);
		AdminDAO dao = new AdminDAO();
		Admin a = dao.findById(num);
		
		radminname = a.getAdminname();
		radminpassword = a.getAdminpassword();

		return "toTrue";
	}

	public String finishUpdateAdmin() {
		int num = Integer.parseInt(adminid);
		AdminDAO dao = new AdminDAO();
		Admin r = dao.findById(num);

		r.setAdminname(fadminname);
		r.setAdminpassword(fadminpassword);

		dao.merge(r);

		list = dao.findAll();

		return "toTrue";
	}

}
