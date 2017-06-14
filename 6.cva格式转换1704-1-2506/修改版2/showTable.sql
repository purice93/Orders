/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.36 : Database - showtable
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`showtable` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `showtable`;

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `username` varchar(20) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `grade` */

/*Table structure for table `havegrade` */

DROP TABLE IF EXISTS `havegrade`;

CREATE TABLE `havegrade` (
  `username` varchar(20) NOT NULL,
  `stepnum` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `havegrade` */

/*Table structure for table `nograde` */

DROP TABLE IF EXISTS `nograde`;

CREATE TABLE `nograde` (
  `username` varchar(20) NOT NULL,
  `stepnum` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `nograde` */

/*Table structure for table `steptable` */

DROP TABLE IF EXISTS `steptable`;

CREATE TABLE `steptable` (
  `username` varchar(20) DEFAULT NULL,
  `XD01` varchar(20) DEFAULT NULL,
  `XK01` varchar(20) DEFAULT NULL,
  `FK01` varchar(20) DEFAULT NULL,
  `VD02` varchar(20) DEFAULT NULL,
  `XD02` varchar(20) DEFAULT NULL,
  `XD03` varchar(20) DEFAULT NULL,
  `ME41` varchar(20) DEFAULT NULL,
  `ME21N` varchar(20) DEFAULT NULL,
  `SU01` varchar(20) DEFAULT NULL,
  `VAP1` varchar(20) DEFAULT NULL,
  `VAP2` varchar(20) DEFAULT NULL,
  `ME52N` varchar(20) DEFAULT NULL,
  `MMH1` varchar(20) DEFAULT NULL,
  `ME47` varchar(20) DEFAULT NULL,
  `ME22N` varchar(20) DEFAULT NULL,
  `ME23N` varchar(20) DEFAULT NULL,
  `ME42` varchar(20) DEFAULT NULL,
  `KS01` varchar(20) DEFAULT NULL,
  `XK02` varchar(20) DEFAULT NULL,
  `KA01` varchar(20) DEFAULT NULL,
  `KA06` varchar(20) DEFAULT NULL,
  `VL02N` varchar(20) DEFAULT NULL,
  `KL01` varchar(20) DEFAULT NULL,
  `MM02` varchar(20) DEFAULT NULL,
  `MMF1` varchar(20) DEFAULT NULL,
  `MMR1` varchar(20) DEFAULT NULL,
  `SU10` varchar(20) DEFAULT NULL,
  `CA02` varchar(20) DEFAULT NULL,
  `CA01` varchar(20) DEFAULT NULL,
  `FS00` varchar(20) DEFAULT NULL,
  `CS01` varchar(20) DEFAULT NULL,
  `VA02` varchar(20) DEFAULT NULL,
  `VA12` varchar(20) DEFAULT NULL,
  `VA22` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `steptable` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `udate` varchar(20) DEFAULT NULL,
  `utime` varchar(20) DEFAULT NULL,
  `tcode` varchar(20) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
