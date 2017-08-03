package com.jakey.model;

public class Student {
private int Sno=-1;
private String Spassword;

public Student() {
	super();
	// TODO Auto-generated constructor stub
}

public Student(int sno) {
	super();
	Sno = sno;
}

public Student(int sno, String spassword) {
	super();
	Sno = sno;
	Spassword = spassword;
}
public int getSno() {
	return Sno;
}
public void setSno(int sno) {
	Sno = sno;
}
public String getSpassword() {
	return Spassword;
}
public void setSpassword(String spassword) {
	Spassword = spassword;
}


}
