CREATE DATABASE manage;
USE manage;

CREATE TABLE adminitrator(
	Username VARCHAR(20) PRIMARY KEY ,
	passsword VARCHAR(20),
	userNumber VARCHAR(20));  
	
INSERT INTO adminitrator(Username,passsword,userNumber) VALUES('root','root','111');  



CREATE TABLE student(
	Sno VARCHAR(20) PRIMARY KEY,
	Spassword VARCHAR(20),
	Sname VARCHAR(20),
	Sx VARCHAR(20));  
	

INSERT INTO student(Sno,Sname,Sx) VALUES('150','150','张三','计算机系');  

INSERT INTO student(Sno,Sname,Sx) VALUES('151','150','李四','电子系');

INSERT INTO student(Sno,Sname,Sx) VALUES('152','150','王五','通信系');


CREATE TABLE course(
	Cno VARCHAR(20) PRIMARY KEY,
	Cname VARCHAR(20),
	Csum  INT(20) NOT NULL AUTO_INCREMENT,
	Cselected  INT(20) NOT NULL DEFAULT 0 AUTO_INCREMENT);  


INSERT INTO course (Cno,Cname) VALUES ('1101','计算机网络');

INSERT INTO course (Cno,Cname) VALUES ('1102','组成原理');

INSERT INTO course (Cno,Cname) VALUES ('1103','操作系统');



CREATE TABLE studentcourse(
	Sno VARCHAR(20) PRIMARY KEY,
	Cno VARCHAR(20),
	FOREIGN KEY (Cno) REFERENCES course (Cno),
	FOREIGN KEY (Sno) REFERENCES student (Sno)); 
	




INSERT INTO student(stuName,stuID,stuAge,stuSdept,stuClass,stuGender,loginPsd) VALUES('222','222',222,'222','222','222','222');

INSERT INTO course (courseID,courseName,courseCridits,courseTime,courseType,courseTeacher,courseNumber,teacherID) VALUES (?,?,?,?,?,?,?,?)

INSERT INTO selectcourse (courseID,studentID,courseGrade) VALUES (?,?,0)

INSERT INTO teacher(loginName,loginPsd,teacID,teacName,teacAge,teacGender) VALUES(?,?,?,?,?,?)


