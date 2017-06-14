package mysql;

import java.util.*;

public class Student {
	private int sno;
	private String name;
	private String sex;
	private int age;
	private Date birthday;
	private String phoneNum;

	public Student(){
		super();
	}
	
	public Student(int sno,String name,String sex,int age,Date birthday,String phoneNum){
		super();
		this.sno = sno;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.birthday = birthday;
		this.phoneNum = phoneNum;
	}
	
	public int getSno(){
		return sno;
	}
	public void setSno(int sno){
		this.sno = sno;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getSex(){
		return sex;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age = age;
	}
	
	public Date getBirthday(){
		return birthday;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	
	public String getPhoneNum(){
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}
	
	
	
	
}
