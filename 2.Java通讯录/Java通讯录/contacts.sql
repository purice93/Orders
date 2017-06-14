/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.36 : Database - contact
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`contact` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `contact`;

/*Table structure for table `persion` */

DROP TABLE IF EXISTS `persion`;

CREATE TABLE `persion` (
  `name` varchar(20) NOT NULL,
  `phoneNumber` varchar(40) NOT NULL,
  `address` varchar(40) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`phoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `persion` */

insert  into `persion`(`name`,`phoneNumber`,`address`,`email`) values 
('大郎','13200001111','武汉市','10010@qq.com'),
('王二','13211110000','长沙市','10086@gmail.com'),
('李四','13050420009','上海市','77228@qq.com'),
('张三','13288889999','北京市','23654@qq.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
