/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.12 : Database - db_patent
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `bs_patent_authority_t` */

DROP TABLE IF EXISTS `bs_patent_authority_t`;

CREATE TABLE `bs_patent_authority_t` (
  `id` int(3) NOT NULL COMMENT '目录ID',
  `pId` int(3) DEFAULT NULL COMMENT '父目录ID',
  `name` varchar(20) DEFAULT NULL COMMENT '目录名称',
  `path` varchar(50) DEFAULT NULL COMMENT '目录相对路径',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '目录功能介绍',
  `open` int(1) DEFAULT NULL COMMENT '是否展开标识：0标识不展开，1标识展开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面权限表';

/*Data for the table `bs_patent_authority_t` */

insert  into `bs_patent_authority_t`(`id`,`pId`,`name`,`path`,`description`,`open`) values (1,0,'客户管理','path','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',1),(2,0,'name2','path','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(3,0,'合同管理','path','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',1),(4,0,'name4','path','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(5,0,'name5','path','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(6,0,'系统管理','path','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(11,1,'客户信息管理','path-11','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(12,1,'客户主体信息','path-12','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(13,1,'name-13','path-13','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(14,1,'name-14','path-14','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(21,2,'name-21','path-21','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(22,2,'name-22','path-22','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(23,2,'name-23','path-23','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(24,2,'name-24','path-24','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(31,3,'正式合同管理','path-31','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(32,3,'合同审核','path-32','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(33,3,'开票记录','path-33','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',1),(34,3,'合同完结','path-34','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(41,4,'name-41','path-41','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(42,4,'name-42','path-42','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(43,4,'name-43','path-43','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(44,4,'name-44','path-44','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(51,5,'name-51','path-51','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(52,5,'name-52','path-52','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(53,5,'name-53','path-53','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(54,5,'name-54','path-54','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(61,6,'用户管理','views/system/service.html','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(62,6,'角色管理','path-62','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(63,6,'专利代理人','path-63','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(64,6,'服务报价管理','views/system/service.html','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(311,31,'任务分配','path-331','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(312,31,'***','path-332','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0),(313,31,'统计报表','path-333','这个指令可以隐藏未编译的 Mustache 标签直到实例准备完毕',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
