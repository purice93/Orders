/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.36 : Database - student
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`student` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `student`;

/*Table structure for table `callednumber` */

DROP TABLE IF EXISTS `callednumber`;

CREATE TABLE `callednumber` (
  `calledNumber` int(11) NOT NULL DEFAULT '0',
  `lateNumber` int(11) DEFAULT '0',
  `class` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`calledNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `callednumber` */

insert  into `callednumber`(`calledNumber`,`lateNumber`,`class`) values (2,0,'1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
