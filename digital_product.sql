/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.59 : Database - digital_product
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`digital_product` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `digital_product`;

/*Table structure for table `s_admin` */

DROP TABLE IF EXISTS `s_admin`;

CREATE TABLE `s_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `passWord` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `s_admin` */

LOCK TABLES `s_admin` WRITE;

insert  into `s_admin`(`id`,`userName`,`passWord`,`name`,`lastLoginTime`) values (2,'admin','123456','admin','2022-01-05 14:16:47');

UNLOCK TABLES;

/*Table structure for table `s_catalog` */

DROP TABLE IF EXISTS `s_catalog`;

CREATE TABLE `s_catalog` (
  `catalogId` int(11) NOT NULL AUTO_INCREMENT,
  `catalogName` varchar(20) NOT NULL,
  PRIMARY KEY (`catalogId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `s_catalog` */

LOCK TABLES `s_catalog` WRITE;

insert  into `s_catalog`(`catalogId`,`catalogName`) values (1,'摄影摄像'),(2,'智能设备'),(3,'娱乐影音'),(4,'游戏装备');

UNLOCK TABLES;

/*Table structure for table `s_digital` */

DROP TABLE IF EXISTS `s_digital`;

CREATE TABLE `s_digital` (
  `digitalID` int(11) NOT NULL AUTO_INCREMENT,
  `catalogId` int(11) NOT NULL,
  `digitalName` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(10,2) NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `imgId` int(11) NOT NULL,
  `addTime` datetime DEFAULT NULL,
  PRIMARY KEY (`digitalID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `s_digital` */

LOCK TABLES `s_digital` WRITE;

insert  into `s_digital`(`digitalID`,`catalogId`,`digitalName`,`price`,`description`,`imgId`,`addTime`) values (1,1,'索尼（SONY）Alpha 7',146900.00,'【微单相机TOP明星机型，A7M3系列京东销量5万+】性价比28-70mm镜头套装。实时眼部对焦搭配10张/秒高速连拍，支持4K-HDR专业视频格式录制！\r\n',1,'2022-01-05 00:00:00'),(2,1,'富士（FUJIFILM）X100V 数码',9790.00,NULL,2,'2022-01-05 00:00:00'),(3,1,'徕卡（Leica）D-LUX7',9900.00,'【百年徕卡光影传奇】【相机/镜头限时官方延保1年】【年度断货王·高贵典雅】【小巧机身·理想品质】【咨询客服享礼】',3,'2022-01-05 00:00:00'),(4,1,'索尼（SONY）ZV-1 Vlog数码相',5199.00,'【注册送官方1年延保】读懂你心的极速对焦切换，高颜值VLOG神器，高效助力变身博主&达人。',4,'2022-01-05 00:00:00'),(5,1,'佳能（Canon）EOS 200D II',5299.00,'【迷你单反】轻量设计，打破传统颜色，拥有黑白外观，5轴防抖，流畅自动对焦，轻松视频拍摄。',5,'2022-01-05 00:00:00'),(6,1,'智云（zhi yun）Smooth',699.00,'【跟焦手轮，你的焦点由你掌控；“疯狗模式”，酷炫转场】【新品上市】',6,'2022-01-05 00:00:00'),(7,1,'伟峰（WEIFENG）WF-717',538.00,NULL,7,'2022-01-05 00:00:00'),(8,1,'富士instax立拍立得 一次成像相机 ',1188.00,NULL,8,'2022-01-05 00:00:00'),(9,2,'荣耀手环5 陨石黑',149.00,'【荣耀手表GS3，定金100抵200】,1月5日-13日每日10点预定前100名再送50元优惠券，购买享12期免息点击查看',9,'2022-01-05 00:00:00'),(10,2,'荣耀手表 熔岩黑',1120.00,NULL,10,'2022-01-05 00:00:00'),(11,2,'华为手环4 Pro',2600.00,NULL,11,'2022-01-05 00:00:00'),(12,3,'索尼（SONY）WI-1000X Hi-',1390.00,NULL,12,'2022-01-05 00:00:00'),(13,3,'Bose QuietComfort 35',1999.00,'【降噪经典】大牌耳机，钜惠来袭！主动消噪，静如人意；佩戴舒适，20小时续航。火速抢购',13,'2022-01-05 00:00:00'),(14,3,'飞利浦 PHILIPS DLM3006U',3000.00,NULL,14,'2022-01-05 00:00:00'),(15,3,'漫步者（EDIFIER）K800 头戴式',65.00,'【限时秒杀，1.5日到手价65元】 【JD自营】漫步者百元内高性价比明星耳麦，音质清晰，累计好评远超30W!更多福利',15,'2022-01-05 00:00:00'),(16,4,'微软Surface Laptop Go ',4758.00,'【下单限量随赠好礼,晒单5000京豆限量送】1110g轻薄便携,达13小时长续航,快速充电,3:2高色域全面屏触屏,预装Office',16,'2022-01-05 00:00:00'),(17,4,' 雷神(ThundeRobot)黑武士4',7999.00,'黑武士4+新品震撼来袭,首发到手仅7299元!12代新品异构处理器!6期免息!【晒单赠雷神鼠标套装,下单送机械键盘,三年上门】》12代新品',17,'2022-01-05 00:00:00');

UNLOCK TABLES;

/*Table structure for table `s_order` */

DROP TABLE IF EXISTS `s_order`;

CREATE TABLE `s_order` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `orderDate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `money` double(10,2) NOT NULL,
  `orderStatus` int(2) NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `s_order` */

LOCK TABLES `s_order` WRITE;

insert  into `s_order`(`orderId`,`orderNum`,`userId`,`orderDate`,`money`,`orderStatus`) values (40,'202201051325237561193321829',1,'2022-01-05 13:25:23',156019.00,1),(41,'20220105133305249200768757',1,'2022-01-05 13:33:05',4758.00,1),(42,'202201051414244391451205899',1,'2022-01-05 14:14:24',293800.00,1);

UNLOCK TABLES;

/*Table structure for table `s_orderitem` */

DROP TABLE IF EXISTS `s_orderitem`;

CREATE TABLE `s_orderitem` (
  `itemId` int(11) NOT NULL AUTO_INCREMENT,
  `digitalId` int(11) NOT NULL,
  `orderId` int(11) NOT NULL DEFAULT '0',
  `quantity` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `s_orderitem` */

LOCK TABLES `s_orderitem` WRITE;

insert  into `s_orderitem`(`itemId`,`digitalId`,`orderId`,`quantity`) values (27,1,40,1),(28,17,40,1),(29,10,40,1),(30,16,41,1),(31,1,42,2);

UNLOCK TABLES;

/*Table structure for table `s_uploadimg` */

DROP TABLE IF EXISTS `s_uploadimg`;

CREATE TABLE `s_uploadimg` (
  `imgId` int(11) NOT NULL AUTO_INCREMENT,
  `imgName` varchar(50) NOT NULL,
  `imgSrc` varchar(255) NOT NULL,
  `imgType` varchar(20) NOT NULL,
  PRIMARY KEY (`imgId`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `s_uploadimg` */

LOCK TABLES `s_uploadimg` WRITE;

insert  into `s_uploadimg`(`imgId`,`imgName`,`imgSrc`,`imgType`) values (1,'0f8d63c48579de81.jpg','images/digital/digitalimg/0f8d63c48579de81.jpg','image/jpeg'),(2,'52c4cb17403594ad.jpg','images/digital/digitalimg/52c4cb17403594ad.jpg','image/jpeg'),(3,'889afa646d98993d.jpg','images/digital/digitalimg/889afa646d98993d.jpg','image/jpeg'),(4,'0d411a0112bb959b.jpg','images/digital/digitalimg/0d411a0112bb959b.jpg','image/jpeg'),(5,'82bd2ce75e8bc579.jpg','images/digital/digitalimg/82bd2ce75e8bc579.jpg','image/jpeg'),(6,'0b67d6451dc01858.jpg','images/digital/digitalimg/0b67d6451dc01858.jpg','image/jpeg'),(7,'56695132Nb96cf24b.jpg','images/digital/digitalimg/56695132Nb96cf24b.jpg','image/jpeg'),(8,'5bed36f4N8d7a6a54.jpg','images/digital/digitalimg/5bed36f4N8d7a6a54.jpg','image/jpeg'),(9,'9cb4ff597e04b1d3.jpg','images/digital/digitalimg/9cb4ff597e04b1d3.jpg','image/jpeg'),(10,'50ff030bc81bdf8a.jpg','images/digital/digitalimg/50ff030bc81bdf8a.jpg','image/jpeg'),(11,'635e2d33821a9529.jpg','images/digital/digitalimg/635e2d33821a9529.jpg','image/jpeg'),(12,'5b03af68Ne4180a01.jpg','images/digital/digitalimg/5b03af68Ne4180a01.jpg','image/jpeg'),(13,'59b7482eNd2c4bc51.jpg','images/digital/digitalimg/59b7482eNd2c4bc51.jpg','image/jpeg'),(14,'c368d801db59e18c.jpg','images/digital/digitalimg/c368d801db59e18c.jpg','image/jpeg'),(15,'f7379c63e59d5fcc.jpg','images/digital/digitalimg/f7379c63e59d5fcc.jpg','image/jpeg'),(16,'100d562e88c8d0e3.jpg','images/digital/digitalimg/100d562e88c8d0e3.jpg','image/jpeg'),(17,'d033b3e9a4c3cca7.jpg','images/digital/digitalimg/d033b3e9a4c3cca7.jpg','image/jpeg'),(18,'粉红乐园18.jpg','images/digital/digitalimg/100d562e88c8d0e3.jpg','image/jpeg'),(19,'梦幻星球19.jpg','images/cake/cakeimg/梦幻星球19.jpg','image/jpeg'),(20,'独角天使20.jpg','images/cake/cakeimg/独角天使20.jpg','image/jpeg'),(21,'玫红精灵21.jpg','images/cake/cakeimg/玫红精灵21.jpg','image/jpeg'),(22,'至爱22.jpg','images/cake/cakeimg/至爱22.jpg','image/jpeg'),(23,'偏爱23.jpg','images/cake/cakeimg/偏爱23.jpg','image/jpeg'),(24,'宠爱24.jpg','images/cake/cakeimg/宠爱24.jpg','image/jpeg'),(25,'唯爱25.jpg','images/cake/cakeimg/唯爱25.jpg','image/jpeg'),(26,'蜜雪儿26.jpg','images/cake/cakeimg/蜜雪儿26.jpg','image/jpeg'),(27,'糖果儿27.jpg','images/cake/cakeimg/糖果儿27.jpg','image/jpeg'),(28,'甜心儿28.jpg','images/cake/cakeimg/甜心儿28.jpg','image/jpeg'),(29,'糖果盒子29.jpg','images/cake/cakeimg/糖果盒子29.jpg','image/jpeg'),(30,'蜜糖宝贝30.jpg','images/cake/cakeimg/蜜糖宝贝30.jpg','image/jpeg'),(31,'小乖乖31.jpg','images/cake/cakeimg/小乖乖31.jpg','image/jpeg'),(32,'新年之歌32.jpg','images/cake/cakeimg/新年之歌32.jpg','image/jpeg'),(33,'温暖的家33.jpg','images/cake/cakeimg/温暖的家33.jpg','image/jpeg'),(34,'丘比特的祝福34.jpg','images/cake/cakeimg/丘比特的祝福34.jpg','image/jpeg'),(35,'旋转木马35.jpg','images/cake/cakeimg/旋转木马35.jpg','image/jpeg'),(36,'真爱36.jpg','images/cake/cakeimg/真爱36.jpg','image/jpeg'),(37,'小精灵37.jpg','images/cake/cakeimg/小精灵37.jpg','image/jpeg'),(38,'小公主38.jpg','images/cake/cakeimg/小公主38.jpg','image/jpeg'),(39,'女神的花冠39.jpg','images/cake/cakeimg/女神的花冠39.jpg','image/jpeg'),(40,'桃李春风40.jpg','images/cake/cakeimg/桃李春风40.jpg','image/jpeg'),(41,'生如夏花41.jpg','images/cake/cakeimg/生如夏花41.jpg','image/jpeg'),(42,'e65d3fc2f0e347c68c8e1c36c2b2dca4.jpg','images/cake/cakeimg/e65d3fc2f0e347c68c8e1c36c2b2dca4.jpg','image/jpeg'),(43,'9e4fd1d28c7f44b8a6d2e390eaa9a736.jpg','images/cake/cakeimg/9e4fd1d28c7f44b8a6d2e390eaa9a736.jpg','image/jpeg'),(44,'9a15f9c683d34609b679ec8f06a4cdc4.jpg','images/cake/cakeimg/9a15f9c683d34609b679ec8f06a4cdc4.jpg','image/jpeg'),(45,'21681df76dc64a3584876a0f4f1a111e.jpg','images/cake/cakeimg/21681df76dc64a3584876a0f4f1a111e.jpg','image/jpeg'),(46,'28fce675af9d4c9599671abd56ca267c.jpg','images/digital/digitalimg/28fce675af9d4c9599671abd56ca267c.jpg','image/jpeg'),(47,'b705e5d544f0416e87e7c01a53362bdd.jpg','images/digital/digitalimg/b705e5d544f0416e87e7c01a53362bdd.jpg','image/jpeg');

UNLOCK TABLES;

/*Table structure for table `s_user` */

DROP TABLE IF EXISTS `s_user`;

CREATE TABLE `s_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `userPassWord` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(5) NOT NULL,
  `tell` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `s_user` */

LOCK TABLES `s_user` WRITE;

insert  into `s_user`(`userId`,`userName`,`userPassWord`,`name`,`sex`,`age`,`tell`,`address`,`enabled`) values (1,'user','1234','xzy','男',22,'18000000000','湖北武汉','y'),(2,'dsfsd','1234','fsdaf','男',32,'18000000000','ghsf','y'),(3,'user1234','123456','11','女',22,'15072186406','aa','y'),(4,'useraa','123456','11','女',20,'15072186406','11','y'),(5,'user8','123456','xx','男',22,'15072186406','xx','y'),(7,'user23','123456','xx','男',20,'18000000000','xx','y');

UNLOCK TABLES;

/*Table structure for table `view_digital` */

DROP TABLE IF EXISTS `view_digital`;

/*!50001 DROP VIEW IF EXISTS `view_digital` */;
/*!50001 DROP TABLE IF EXISTS `view_digital` */;

/*!50001 CREATE TABLE  `view_digital`(
 `catalogName` varchar(20) NOT NULL ,
 `digitalId` int(11) NOT NULL  default '0' ,
 `catalogId` int(11) NOT NULL ,
 `digitalName` varchar(20) NOT NULL ,
 `price` double(10,2) NOT NULL ,
 `description` text NULL ,
 `imgId` int(11) NOT NULL ,
 `imgName` varchar(50) NOT NULL ,
 `imgSrc` varchar(255) NOT NULL ,
 `imgType` varchar(20) NOT NULL ,
 `addTime` datetime NULL 
)*/;

/*Table structure for table `view_order` */

DROP TABLE IF EXISTS `view_order`;

/*!50001 DROP VIEW IF EXISTS `view_order` */;
/*!50001 DROP TABLE IF EXISTS `view_order` */;

/*!50001 CREATE TABLE  `view_order`(
 `itemId` int(11) NOT NULL  default '0' ,
 `digitalId` int(11) NOT NULL ,
 `quantity` int(11) NOT NULL  default '0' ,
 `orderId` int(11) NOT NULL  default '0' ,
 `orderNum` varchar(50) NOT NULL ,
 `userId` int(11) NOT NULL ,
 `orderDate` varchar(20) NOT NULL ,
 `money` double(10,2) NOT NULL ,
 `orderStatus` int(2) NOT NULL 
)*/;

/*View structure for view view_digital */

/*!50001 DROP TABLE IF EXISTS `view_digital` */;
/*!50001 DROP VIEW IF EXISTS `view_digital` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_digital` AS select `s_catalog`.`catalogName` AS `catalogName`,`s_digital`.`digitalID` AS `digitalId`,`s_digital`.`catalogId` AS `catalogId`,`s_digital`.`digitalName` AS `digitalName`,`s_digital`.`price` AS `price`,`s_digital`.`description` AS `description`,`s_digital`.`imgId` AS `imgId`,`s_uploadimg`.`imgName` AS `imgName`,`s_uploadimg`.`imgSrc` AS `imgSrc`,`s_uploadimg`.`imgType` AS `imgType`,`s_digital`.`addTime` AS `addTime` from ((`s_digital` join `s_catalog` on((`s_digital`.`catalogId` = `s_catalog`.`catalogId`))) join `s_uploadimg` on((`s_digital`.`imgId` = `s_uploadimg`.`imgId`))) */;

/*View structure for view view_order */

/*!50001 DROP TABLE IF EXISTS `view_order` */;
/*!50001 DROP VIEW IF EXISTS `view_order` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_order` AS select `s_orderitem`.`itemId` AS `itemId`,`s_orderitem`.`digitalId` AS `digitalId`,`s_orderitem`.`quantity` AS `quantity`,`s_order`.`orderId` AS `orderId`,`s_order`.`orderNum` AS `orderNum`,`s_order`.`userId` AS `userId`,`s_order`.`orderDate` AS `orderDate`,`s_order`.`money` AS `money`,`s_order`.`orderStatus` AS `orderStatus` from (`s_order` join `s_orderitem` on((`s_orderitem`.`orderId` = `s_order`.`orderId`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
