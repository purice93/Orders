/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.36 : Database - db_dorm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_dorm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_dorm`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_admin` */

insert  into `t_admin`(`adminId`,`userName`,`password`,`name`,`sex`,`tel`) values (1,'admin','admin','管理员','男','123');

/*Table structure for table `t_dorm` */

DROP TABLE IF EXISTS `t_dorm`;

CREATE TABLE `t_dorm` (
  `dormId` int(11) NOT NULL AUTO_INCREMENT,
  `dormBuildId` int(11) DEFAULT NULL,
  `dormName` varchar(20) DEFAULT NULL,
  `dormType` varchar(20) DEFAULT NULL,
  `dormNumber` int(11) DEFAULT NULL,
  `dormTel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`dormId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_dorm` */

insert  into `t_dorm`(`dormId`,`dormBuildId`,`dormName`,`dormType`,`dormNumber`,`dormTel`) values (1,1,'dormName','男',6,'dormTel');

/*Table structure for table `t_dormbuild` */

DROP TABLE IF EXISTS `t_dormbuild`;

CREATE TABLE `t_dormbuild` (
  `dormBuildId` int(11) NOT NULL AUTO_INCREMENT,
  `dormBuildName` varchar(20) DEFAULT NULL,
  `dormBuildDetail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dormBuildId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `t_dormbuild` */

insert  into `t_dormbuild`(`dormBuildId`,`dormBuildName`,`dormBuildDetail`) values (1,'1栋','大一住宿'),(4,'2栋','大二住宿'),(5,'3栋','大三住宿'),(6,'4栋','无备注'),(7,'5栋','无备注'),(8,'6栋','无备注'),(9,'7栋','混合住宿');

/*Table structure for table `t_dormmanager` */

DROP TABLE IF EXISTS `t_dormmanager`;

CREATE TABLE `t_dormmanager` (
  `dormManId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `dormBuildId` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`dormManId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `t_dormmanager` */

insert  into `t_dormmanager`(`dormManId`,`userName`,`password`,`dormBuildId`,`name`,`sex`,`tel`) values (2,'manager2','123',4,'张三','男','188'),(3,'manager3','123',1,'李四','女','189'),(4,'manager4','123',5,'王五','男','190'),(5,'manager5','123',NULL,'小六','男','183'),(7,'manager6','123',NULL,'阿七','女','178'),(8,'manager1','123',6,'小白','男','176'),(9,'manager7','123',7,'九斤','女','155');

/*Table structure for table `t_instructor` */

DROP TABLE IF EXISTS `t_instructor`;

CREATE TABLE `t_instructor` (
  `instructorId` int(11) NOT NULL AUTO_INCREMENT,
  `instructorName` varchar(20) DEFAULT NULL,
  `instructorEmail` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`instructorId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_instructor` */

insert  into `t_instructor`(`instructorId`,`instructorName`,`instructorEmail`) values (9,'王辅导员','530337704@qq.com'),(11,'马云','5300@qq.com'),(12,'丁磊','163@qq.com'),(13,'李彦宏','101010@qq.com'),(14,'马化腾','10001@qq.com');

/*Table structure for table `t_record` */

DROP TABLE IF EXISTS `t_record`;

CREATE TABLE `t_record` (
  `recordId` int(11) NOT NULL AUTO_INCREMENT,
  `studentNumber` varchar(20) DEFAULT NULL,
  `studentName` varchar(30) DEFAULT NULL,
  `dormBuildId` int(11) DEFAULT NULL,
  `dormName` varchar(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `detail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_record` */

insert  into `t_record`(`recordId`,`studentNumber`,`studentName`,`dormBuildId`,`dormName`,`date`,`detail`) values (1,'002','白起',4,'120','2014-01-01','123'),(3,'007','刘备',1,'221','2014-08-11','123'),(4,'005','赵云',4,'220','2014-08-12','...'),(5,'006','关羽',4,'111','2014-08-12','00'),(6,'004','李世明',6,'220','2014-08-12','....'),(7,'004','李世明',6,'220','2014-08-12','22'),(8,'002','白起',4,'120','2017-05-02','未到');

/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `stuNum` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `dormBuildId` int(11) DEFAULT NULL,
  `dormName` varchar(11) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `instructorId` varchar(20) NOT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `t_student` */

insert  into `t_student`(`studentId`,`stuNum`,`password`,`name`,`dormBuildId`,`dormName`,`sex`,`tel`,`instructorId`) values (2,'002','123','白起',4,'120','男','32','9'),(3,'003','123','王翦',5,'201','男','2','9'),(4,'004','123','李世明',6,'220','女','1','9'),(5,'005','123','赵云',4,'220','女','123','9'),(6,'006','123','关羽',4,'111','女','111','14'),(9,'007','123','刘备',1,'221','男','123','11'),(28,'001','123','诸葛亮',1,'111','男','123','11'),(29,'008','123','曹操',6,'123','男','123','11'),(30,'009','123','孙权',5,'123','男','123','12'),(31,'010','123','司马懿',4,'222','男','111','13');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
