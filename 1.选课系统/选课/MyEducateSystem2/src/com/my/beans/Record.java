package com.my.beans;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable{
	private User user;
	private Date inTime;
	private Date outTime;
	
	
	public Record(User user, Date inTime, Date outTime) {
		super();
		this.user = user;
		this.inTime = inTime;
		this.outTime = outTime;
	}


	@Override
	public String toString() {
		return "Record [user=" + user + ", inTime=" + inTime + ", outTime=" + outTime + "]";
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getInTime() {
		return inTime;
	}


	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}


	public Date getOutTime() {
		return outTime;
	}


	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}


	public Record(){}
}
