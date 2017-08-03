package com.my.beans;

public class Course {
	private String cname;
	private String caca;
	private String cid;
	private Teacher tea;
	private Student stu;
	
	
	public Course(String cname, String caca, String cid, Teacher tea, Student stu) {
		super();
		this.cname = cname;
		this.caca = caca;
		this.cid = cid;
		this.tea = tea;
		this.stu = stu;
	}


	@Override
	public String toString() {
		return "Course [cname=" + cname + ", caca=" + caca + ", cid=" + cid + ", tea=" + tea + ", stu=" + stu + "]";
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getCaca() {
		return caca;
	}


	public void setCaca(String caca) {
		this.caca = caca;
	}


	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}


	public Teacher getTea() {
		return tea;
	}


	public void setTea(Teacher tea) {
		this.tea = tea;
	}


	public Student getStu() {
		return stu;
	}


	public void setStu(Student stu) {
		this.stu = stu;
	}


	public Course(){}
}
