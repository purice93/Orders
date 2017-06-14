package com.lero.model;
/*
 * 管理员实体类，model包中的其他类与此类似；分别是公寓、公寓管理员、辅导员、页码、学生就寝记录、学生。
 */
public class Admin {
	
	private int adminId;
	private String userName;
	private String password;
	private String name;
	private String sex;
	private String tel;
	
	public Admin() {

	}
	
	
	public Admin(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
