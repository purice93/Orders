package com.csms.entity;
 
/**
 * 实体�?
 *
 */
public class Student {
	
	private String studentId;//学号
	private String studentName;//学生姓名
	private String studentPic;//学生图片
	private int calledNumber;//被叫次数
	private int lateNumber;//迟到次数
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentPic() {
		return studentPic;
	}
	public void setStudentPic(String studentPic) {
		this.studentPic = studentPic;
	}
	public int getCalledNumber() {
		return calledNumber;
	}
	public void setCalledNumber(int calledNumber) {
		this.calledNumber = calledNumber;
	}
	public int getLateNumber() {
		return lateNumber;
	}
	public void setLateNumber(int lateNumber) {
		this.lateNumber = lateNumber;
	}
	
	
}