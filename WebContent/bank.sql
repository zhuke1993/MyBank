/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.20 : Database - bank
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bank` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bank`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PWD` varchar(255) NOT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `SEX` varchar(255) DEFAULT NULL,
   balance decimal(10,2) DEFAULT NULL,
  `groupId` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*=========================================*/
/*Table: BankAccount                       */
/*=========================================*/
drop table if exists BankAccount;

create table BankAccount (
	ba_id int(255) NOT NULL AUTO_INCREMENT,
	ba_name varchar(255) NOT NULL,
	ba_password varchar(255) NOT NULL,
	ba_sex  varchar(255) DEFAULT NULL,
	ba_tel varchar(255) DEFAULT NULL,
	ba_email varchar(255) DEFAULT NULL,
	ba_balance decimal(10,2) DEFAULT NULL,
	ba_groupId int(11) NOT NULL DEFAULT '1',
	primary key(id)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*=========================================*/
/*Table: BankRecord                        */
/*=========================================*/
drop table if exists BankRecord;

create table BankRecord(
	id int(255) NOT NULL AUTO_INCREMENT,
	br_action varchar(255) DEFAULT NULL,
	br_dateTime varchar(255) DEFAULT NULL,
	br_fromId int(255) DEFAULT NULL ,
	br_toId int(255) DEFAULT NULL,
	br_money decimal(10,2) DEFAULT NULL,
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*添加外键*/
alter table BankRecord add CONSTRAINT FK_Reference_1 foreign key(br_fromId) REFERENCES
	user(id) on DELETE CASCADE on UPDATE CASCADE;

alter table BankAccount add CONSTRAINT FK_Reference_2 foreign key(ba_id) REFERENCES
	user(id) on DELETE CASCADE on UPDATE CASCADE;
/*删除外键约束*/
alter table BankRecord drop foreign key FK_Reference_1;
alter table BankAccount drop foreign key FK_Reference_2;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;