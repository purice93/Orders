/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.36 : Database - manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`manage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `manage`;

/*Table structure for table `adminitrator` */

DROP TABLE IF EXISTS `adminitrator`;

CREATE TABLE `adminitrator` (
  `Username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `trueName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `adminitrator` */

insert  into `adminitrator`(`Username`,`password`,`trueName`) values ('root','root','111');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `Cno` varchar(20) NOT NULL,
  `Cname` varchar(20) NOT NULL,
  `Csum` int(20) NOT NULL DEFAULT '100',
  `Cselected` int(20) NOT NULL DEFAULT '0',
  `Cattri` varchar(20) NOT NULL DEFAULT '必修'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`Cno`,`Cname`,`Csum`,`Cselected`,`Cattri`) values ('1101','线性代数',20,20,'必修'),('1102','组成原理',20,10,'必修'),('1103','操作系统',20,3,'必修');

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `Sno` varchar(20) NOT NULL,
  `Cno` varchar(20) NOT NULL,
  `sco` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `Sno` varchar(20) NOT NULL,
  `Spassword` varchar(20) NOT NULL,
  `Sname` varchar(20) DEFAULT NULL,
  `Sx` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`Sno`,`Spassword`,`Sname`,`Sx`) values ('111','111','张三','计算机系'),('112','112','李四','电子系'),('113','113','王五','通信系'),('12','12','12','12'),('1305','1305','1305','1305'),('1306','1306','1306','1306'),('31','31','213','31'),('3213','3213','1213','4124312');

/*Table structure for table `studentcourse` */

DROP TABLE IF EXISTS `studentcourse`;

CREATE TABLE `studentcourse` (
  `Sno` varchar(20) NOT NULL,
  `Cno` varchar(20) NOT NULL,
  `Sco` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `studentcourse` */

insert  into `studentcourse`(`Sno`,`Cno`,`Sco`) values ('111','1103',99),('111','1102',0),('111','123',111);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `Tno` varchar(20) NOT NULL,
  `Tpassword` varchar(20) NOT NULL,
  `Cno` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`Tno`,`Tpassword`,`Cno`) values ('1','1','1101'),('2','2','1102'),('3','3','1103');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
