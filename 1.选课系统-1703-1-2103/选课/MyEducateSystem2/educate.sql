           ----create database educateSystem
           --use educateSystem
CREATE TABLE user1 (
	uname VARCHAR(20) PRIMARY KEY  NOT NULL,
	upwd VARCHAR(20),
	uright INTEGER );              

CREATE TABLE student(
	sid VARCHAR(20) PRIMARY KEY ,
	sname VARCHAR(20),
	ssex VARCHAR(20),
	saca VARCHAR(20) );           

CREATE TABLE teacher(
	tid VARCHAR(20) PRIMARY KEY ,
	tname VARCHAR(20),
	tsex VARCHAR(20),taca VARCHAR(20));           

CREATE TABLE course(
	cno INTEGER  PRIMARY KEY,
	cid VARCHAR(20) ,
	cname VARCHAR(20),
	caca VARCHAR(20),
	teacher VARCHAR(20) ,
	tid VARCHAR(20) ,
	student VARCHAR(20) ,
	sid VARCHAR(20) ); 

INSERT INTO user1(uname,upwd,uright) VALUES('a1','111',1);               
           
		
		

