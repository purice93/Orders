package com.lero.model;

public class Instructor {
	private int instructorId;
	private String instructorName;
	private String instructorEmail;
		
	public Instructor() {
	}

	public Instructor(String instructorName, String instructorEmail) {
		this.instructorName = instructorName;
		this.instructorEmail = instructorEmail;
	}

	public int getInstructorId() {
		return instructorId;
	}
	
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public String getInstructorName() {
		return instructorName;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getInstructorEmail() {
		return instructorEmail;
	}
	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}
	
		
}
