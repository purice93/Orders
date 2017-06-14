/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.36 : Database - airplane
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`airplane` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `airplane`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`userName`,`password`) values ('root','root');

/*Table structure for table `passager` */

DROP TABLE IF EXISTS `passager`;

CREATE TABLE `passager` (
  `passagerID` int(20) DEFAULT NULL,
  `realName` varchar(20) DEFAULT NULL,
  `identityID` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `orderID` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `passager` */

insert  into `passager`(`passagerID`,`realName`,`identityID`,`password`,`orderID`) values (123,'张三','421302','123','10086'),(111,'312','3','111',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
