package com.jakey.model;

public class Sinfo {
private int sno=-1;
private String sname;
private String ssex;
private String smajor;
private String stele;

public Sinfo() {
	super();
	// TODO Auto-generated constructor stub
}

public Sinfo(int sno) {
	super();
	this.sno = sno;
}

public Sinfo(int sno, String sname) {
	super();
	this.sno = sno;
	this.sname = sname;
}

public int getSno() {
	return sno;
}

public void setSno(int sno) {
	this.sno = sno;
}

public String getSname() {
	return sname;
}

public void setSname(String sname) {
	this.sname = sname;
}

public String getSsex() {
	return ssex;
}

public void setSsex(String ssex) {
	this.ssex = ssex;
}

public String getSmajor() {
	return smajor;
}

public void setSmajor(String smajor) {
	this.smajor = smajor;
}

public String getStele() {
	return stele;
}

public void setStele(String stele) {
	this.stele = stele;
}



}
