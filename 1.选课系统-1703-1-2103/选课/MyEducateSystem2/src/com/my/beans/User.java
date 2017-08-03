package com.my.beans;

import java.io.Serializable;

public class User implements Serializable{
	private String uname;
	private String pwd;
	private int uright;
	
	
	public User(String uname, String pwd, int uright) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.uright = uright;
	}


	@Override
	public String toString() {
		return "User [uname=" + uname + ", pwd=" + pwd + ", uright=" + uright + "]";
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public int getUright() {
		return uright;
	}


	public void setUright(int uright) {
		this.uright = uright;
	}


	public User(){}
	
}
