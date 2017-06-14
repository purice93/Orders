package com.entity;

import java.sql.Date;
import java.sql.Time;

import javax.xml.crypto.Data;

/**
 * User实体类
 *
 */
public class User {
	
	private String username; //用户名
	private String uDate; //登陆时间
	private String uTime; //登录时长
	private String tcode; //操作类型
	private int grade; //成绩
	public String getUsername() {
		return username;
	}
	public String getuDate() {
		return uDate;
	}
	public void setuDate(String uDate) {
		this.uDate = uDate;
	}
	public String getuTime() {
		return uTime;
	}
	public void setuTime(String uTime) {
		this.uTime = uTime;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}