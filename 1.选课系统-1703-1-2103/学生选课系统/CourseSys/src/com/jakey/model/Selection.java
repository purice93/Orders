package com.jakey.model;

public class Selection {
int selectId;
int courseId=-1;
int Sno;

public Selection() {
	super();
	// TODO Auto-generated constructor stub
}
public Selection(int courseId, int sno) {
	super();
	this.courseId = courseId;
	Sno = sno;
}
public int getSelectId() {
	return selectId;
}
public void setSelectId(int selectId) {
	this.selectId = selectId;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public int getSno() {
	return Sno;
}
public void setSno(int sno) {
	Sno = sno;
}


}
