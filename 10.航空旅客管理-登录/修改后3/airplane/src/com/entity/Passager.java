package com.entity;

public class Passager {
	
	private String passagerID;
	private String realName;
	private String identityID;
	private String password;
	private String orderID;
	public String getPassagerID() {
		return passagerID;
	}
	public void setPassagerID(String passagerID) {
		this.passagerID = passagerID;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentityID() {
		return identityID;
	}
	public void setIdentityID(String identityID) {
		this.identityID = identityID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String toString() {
		return passagerID+" "+realName+" "+identityID+" "+password+orderID;
	}
	
	
}