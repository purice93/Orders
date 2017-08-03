package com.my.beans;

public class Teacher extends User{
	private String tid;
	private String tsex;
	private String taca;
	private String tname;
	
	public Teacher(String uname, String pwd, int uright, String tid, String tsex, String taca, String tname) {
		super(uname, pwd, uright);
		this.tid = tid;
		this.tsex = tsex;
		this.taca = taca;
		this.tname = tname;
	}

	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tsex=" + tsex + ", taca=" + taca + ", tname=" + tname + "]";
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public String getTaca() {
		return taca;
	}

	public void setTaca(String taca) {
		this.taca = taca;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Teacher(){}
}
