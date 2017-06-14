package com.jakey.model;

public class Course {
	private int courseId=-1;
	private String courseName;
	private String courseTime;
	private String  courseTeacher;
	private int capacity;
	private int numSelected;
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String courseName, String courseTime, String courseTeacher) {
		super();
		this.courseName = courseName;
		this.courseTime = courseTime;
		this.courseTeacher = courseTeacher;
	}

	public Course(int courseId, String courseName, String courseTime,
			String courseTeacher, int capacity) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseTime = courseTime;
		this.courseTeacher = courseTeacher;
		this.capacity = capacity;
	}

	public Course(String courseName, String courseTime, String courseTeacher,
			int capacity) {
		super();
		this.courseName = courseName;
		this.courseTime = courseTime;
		this.courseTeacher = courseTeacher;
		this.capacity = capacity;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public String getCourseTeacher() {
		return courseTeacher;
	}
	public void setCourseTeacher(String courseTeacher) {
		this.courseTeacher = courseTeacher;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getNumSelected() {
		return numSelected;
	}
	public void setNumSelected(int numSelected) {
		this.numSelected = numSelected;
	}
	
}
