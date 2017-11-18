/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.36 : Database - studentmanagement
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentmanagement` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `studentmanagement`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `cid` int(9) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  `credit` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=100000007 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`cid`,`cname`,`credit`) values (100000000,'高等数学','5'),(100000001,'大学英语','2'),(100000002,'线性代数','2'),(100000003,'大学计算机基础','1'),(100000004,'线性代数','1'),(100000005,'数据结构','2'),(100000006,'分布式计算','2');

/*Table structure for table `optcou` */

DROP TABLE IF EXISTS `optcou`;

CREATE TABLE `optcou` (
  `sid` int(9) NOT NULL,
  `cid` int(9) NOT NULL,
  `score` decimal(5,2) DEFAULT '-1.00',
  PRIMARY KEY (`sid`,`cid`),
  KEY `cid` (`cid`),
  CONSTRAINT `optcou_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `optcou_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `optcou` */

insert  into `optcou`(`sid`,`cid`,`score`) values (1,100000000,100.00),(1,100000001,75.00),(1,100000002,80.00),(1,100000003,-1.00),(1,100000006,-1.00),(2,100000000,95.00);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `sid` int(9) NOT NULL AUTO_INCREMENT,
  `spwd` varchar(20) DEFAULT NULL,
  `sname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=100000003 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`sid`,`spwd`,`sname`) values (1,'001','小一'),(2,'002','小二'),(100000002,'mayun','马云');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
