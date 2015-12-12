/*
SQLyog Ultimate v9.30 
MySQL - 5.5.43-0ubuntu0.12.04.1 : Database - homework
*********************************************************************
*/


CREATE DATABASE /*!32312 IF NOT EXISTS*/`homework` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `homework`;

/*Table structure for table `tb_bookmark` */

DROP TABLE IF EXISTS `tb_bookmark`;

CREATE TABLE `tb_bookmark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `url` varchar(200) NOT NULL,
  `created` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

