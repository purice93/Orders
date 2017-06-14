package com.my.beans;

public class Student extends User{
	private String sname;
	private String sid;
	private String sex;
	private String saca;
	
	public Student(String uname, String pwd, int uright, String sname, String sid, String sex, String saca) {
		super(uname, pwd, uright);
		this.sname = sname;
		this.sid = sid;
		this.sex = sex;
		this.saca = saca;
	}

	@Override
	public String toString() {
		return "Student [sname=" + sname + ", sid=" + sid + ", sex=" + sex + ", saca=" + saca + "]";
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSaca() {
		return saca;
	}

	public void setSaca(String saca) {
		this.saca = saca;
	}

	public Student(){}
}
