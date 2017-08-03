	create table f_user(
			id int primary key auto_increment,
			username varchar(20) unique,
			pwd varchar(10),
			name varchar(20),
			age int(3),
			gender char(1),
			phone varchar(20),
			ask text);
			create table f_pic(
			id int primary key auto_increment,
			picName varchar(100),
			userId int);