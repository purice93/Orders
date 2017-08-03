/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.49-community : Database - db_courseselect
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_courseselect` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_courseselect`;

/*Table structure for table `t_adminlogon` */

DROP TABLE IF EXISTS `t_adminlogon`;

CREATE TABLE `t_adminlogon` (
  `adminId` int(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_adminlogon` */

insert  into `t_adminlogon`(`adminId`,`password`) values (11,'11'),(123,'123');

/*Table structure for table `t_course` */

DROP TABLE IF EXISTS `t_course`;

CREATE TABLE `t_course` (
  `courseId` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(20) NOT NULL,
  `courseTime` varchar(20) NOT NULL,
  `courseTeacher` varchar(10) DEFAULT NULL,
  `capacity` int(11) NOT NULL,
  `numSelected` int(10) DEFAULT '0',
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_course` */

insert  into `t_course`(`courseId`,`courseName`,`courseTime`,`courseTeacher`,`capacity`,`numSelected`) values (1,'彩票营销','周四5.6节','蔡',50,1),(2,'岭南文化','周一7.8节','吴',40,2),(3,'网球','周五3.4节','王',45,2),(4,'很快','很快','很快',70,0);

/*Table structure for table `t_selection` */

DROP TABLE IF EXISTS `t_selection`;

CREATE TABLE `t_selection` (
  `selectId` int(11) NOT NULL AUTO_INCREMENT,
  `courseId` int(11) NOT NULL,
  `Sno` int(11) NOT NULL,
  PRIMARY KEY (`courseId`,`Sno`),
  UNIQUE KEY `selectId` (`selectId`),
  KEY `FK2_t_selection` (`Sno`),
  CONSTRAINT `FK2_t_selection` FOREIGN KEY (`Sno`) REFERENCES `t_sinfo` (`Sno`),
  CONSTRAINT `FK_t_selection` FOREIGN KEY (`courseId`) REFERENCES `t_course` (`courseId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `t_selection` */

insert  into `t_selection`(`selectId`,`courseId`,`Sno`) values (10,3,2),(12,2,2),(26,1,1),(29,3,1),(37,2,1);

/*Table structure for table `t_sinfo` */

DROP TABLE IF EXISTS `t_sinfo`;

CREATE TABLE `t_sinfo` (
  `Sno` int(11) NOT NULL,
  `Sname` varchar(10) NOT NULL,
  `Ssex` varchar(5) NOT NULL,
  `Smajor` varchar(20) NOT NULL,
  `Stele` varchar(20) NOT NULL,
  PRIMARY KEY (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sinfo` */

insert  into `t_sinfo`(`Sno`,`Sname`,`Ssex`,`Smajor`,`Stele`) values (1,'张三','男','计算机','12345'),(2,'李四','男','机电','120'),(3,'王一','女','英语','88888');

/*Table structure for table `t_slogon` */

DROP TABLE IF EXISTS `t_slogon`;

CREATE TABLE `t_slogon` (
  `Sno` int(11) NOT NULL,
  `Spassword` varchar(20) NOT NULL,
  PRIMARY KEY (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_slogon` */

insert  into `t_slogon`(`Sno`,`Spassword`) values (1,'3'),(2,'2'),(3,'3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
