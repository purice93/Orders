/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.36 : Database - restaurant
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`restaurant` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `restaurant`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` varchar(5) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`password`) values ('00000','123456'),('00001','123457');

/*Table structure for table `employees` */

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
  `id` varchar(5) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `position` varchar(10) DEFAULT NULL,
  `salary` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employees` */

insert  into `employees`(`id`,`name`,`gender`,`age`,`birthday`,`position`,`salary`) values ('00000','张三','男',20,'1997/1/1','经理',12000),('00001','李四','男',22,'1995/1/2','副经理',10000),('00110','王五','男',46,'1985/2/3','厨师长',8000),('00111','赵四','男',30,'1987/2/3','厨师',5000),('00112','赵五','男',20,'1997/1/20','厨师',5000),('00201','小丽','女',18,'1999/1/30','服务员',3000),('00202','小芳','女',18,'1999/2/5','服务员',3000),('00203','李娜','女',18,'1999/4/6','服务员',3000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
