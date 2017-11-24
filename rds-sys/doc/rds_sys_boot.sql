/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.6.26 : Database - rds_sys_boot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rds_sys_boot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rds_sys_boot`;

/*Table structure for table `sys_icon` */

DROP TABLE IF EXISTS `sys_icon`;

CREATE TABLE `sys_icon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='图标';

/*Data for the table `sys_icon` */

insert  into `sys_icon`(`id`,`name`,`url`,`type`) values (1,'1-1.png','/images/32x32/1-1.png','32x32'),(2,'1.png','/images/32x32/1.png','32x32'),(3,'address.png','/images/32x32/address.png','32x32'),(4,'administrative-docs.png','/images/32x32/administrative-docs.png','32x32'),(5,'advertising.png','/images/32x32/advertising.png','32x32'),(6,'archives.png','/images/32x32/archives.png','32x32'),(7,'attibutes.png','/images/32x32/attibutes.png','32x32'),(8,'bank.png','/images/32x32/bank.png','32x32'),(9,'basket.png','/images/32x32/basket.png','32x32'),(10,'bestseller.png','/images/32x32/bestseller.png','32x32'),(11,'billing.png','/images/32x32/billing.png','32x32'),(12,'bookmark.png','/images/32x32/bookmark.png','32x32'),(13,'brainstorming.png','/images/32x32/brainstorming.png','32x32'),(14,'business-contact.png','/images/32x32/business-contact.png','32x32'),(15,'busy.png','/images/32x32/busy.png','32x32'),(16,'calendar.png','/images/32x32/calendar.png','32x32'),(17,'category.png','/images/32x32/category.png','32x32'),(18,'check.png','/images/32x32/check.png','32x32'),(19,'collaboration.png','/images/32x32/collaboration.png','32x32'),(20,'comment.png','/images/32x32/comment.png','32x32'),(21,'communication.png','/images/32x32/communication.png','32x32'),(22,'config.png','/images/32x32/config.png','32x32'),(23,'consulting.png','/images/32x32/consulting.png','32x32'),(24,'contact.png','/images/32x32/contact.png','32x32'),(25,'cost.png','/images/32x32/cost.png','32x32'),(26,'credit-card.png','/images/32x32/credit-card.png','32x32'),(27,'credit.png','/images/32x32/credit.png','32x32'),(28,'current-work.png','/images/32x32/current-work.png','32x32'),(29,'customers.png','/images/32x32/customers.png','32x32'),(30,'cv.png','/images/32x32/cv.png','32x32'),(31,'database.png','/images/32x32/database.png','32x32'),(32,'date.png','/images/32x32/date.png','32x32'),(33,'delicious.png','/images/32x32/delicious.png','32x32'),(34,'document-library.png','/images/32x32/document-library.png','32x32'),(35,'donate.png','/images/32x32/donate.png','32x32'),(36,'drawings.png','/images/32x32/drawings.png','32x32'),(37,'edit.png','/images/32x32/edit.png','32x32'),(38,'email.png','/images/32x32/email.png','32x32'),(39,'featured.png','/images/32x32/featured.png','32x32'),(40,'feed.png','/images/32x32/feed.png','32x32'),(41,'finished-work.png','/images/32x32/finished-work.png','32x32'),(42,'flag.png','/images/32x32/flag.png','32x32'),(43,'folder.png','/images/32x32/folder.png','32x32'),(44,'free-for-job.png','/images/32x32/free-for-job.png','32x32'),(45,'freelance.png','/images/32x32/freelance.png','32x32'),(46,'full-time.png','/images/32x32/full-time.png','32x32'),(47,'future-projects.png','/images/32x32/future-projects.png','32x32'),(48,'graphic-design.png','/images/32x32/graphic-design.png','32x32'),(49,'heart.png','/images/32x32/heart.png','32x32'),(50,'hire-me.png','/images/32x32/hire-me.png','32x32'),(51,'home.png','/images/32x32/home.png','32x32'),(52,'illustration.png','/images/32x32/illustration.png','32x32'),(53,'invoice.png','/images/32x32/invoice.png','32x32'),(54,'issue.png','/images/32x32/issue.png','32x32'),(55,'library.png','/images/32x32/library.png','32x32'),(56,'lightbulb.png','/images/32x32/lightbulb.png','32x32'),(57,'limited-edition.png','/images/32x32/limited-edition.png','32x32'),(58,'link.png','/images/32x32/link.png','32x32'),(59,'lock.png','/images/32x32/lock.png','32x32'),(60,'login.png','/images/32x32/login.png','32x32'),(61,'logout.png','/images/32x32/logout.png','32x32'),(62,'milestone.png','/images/32x32/milestone.png','32x32'),(63,'my-account.png','/images/32x32/my-account.png','32x32'),(64,'networking.png','/images/32x32/networking.png','32x32'),(65,'old-versions.png','/images/32x32/old-versions.png','32x32'),(66,'order-1.png','/images/32x32/order-1.png','32x32'),(67,'order.png','/images/32x32/order.png','32x32'),(68,'payment-card.png','/images/32x32/payment-card.png','32x32'),(69,'paypal.png','/images/32x32/paypal.png','32x32'),(70,'pen.png','/images/32x32/pen.png','32x32'),(71,'pencil.png','/images/32x32/pencil.png','32x32'),(72,'phone.png','/images/32x32/phone.png','32x32'),(73,'photography.png','/images/32x32/photography.png','32x32'),(74,'plus.png','/images/32x32/plus.png','32x32'),(75,'premium.png','/images/32x32/premium.png','32x32'),(76,'print.png','/images/32x32/print.png','32x32'),(77,'process.png','/images/32x32/process.png','32x32'),(78,'product-1.png','/images/32x32/product-1.png','32x32'),(79,'product-design.png','/images/32x32/product-design.png','32x32'),(80,'product.png','/images/32x32/product.png','32x32'),(81,'project.png','/images/32x32/project.png','32x32'),(82,'publish.png','/images/32x32/publish.png','32x32'),(83,'refresh.png','/images/32x32/refresh.png','32x32'),(84,'search.png','/images/32x32/search.png','32x32'),(85,'settings.png','/images/32x32/settings.png','32x32'),(86,'shipping.png','/images/32x32/shipping.png','32x32'),(87,'showreel.png','/images/32x32/showreel.png','32x32'),(88,'sign-in.png','/images/32x32/sign-in.png','32x32'),(89,'sign-out.png','/images/32x32/sign-out.png','32x32'),(90,'sign-up.png','/images/32x32/sign-up.png','32x32'),(91,'sitemap.png','/images/32x32/sitemap.png','32x32'),(92,'special-offer.png','/images/32x32/special-offer.png','32x32'),(93,'star.png','/images/32x32/star.png','32x32'),(94,'statistics.png','/images/32x32/statistics.png','32x32'),(95,'suppliers.png','/images/32x32/suppliers.png','32x32'),(96,'tag.png','/images/32x32/tag.png','32x32'),(97,'ticket.png','/images/32x32/ticket.png','32x32'),(98,'twitter.png','/images/32x32/twitter.png','32x32'),(99,'upcoming-work.png','/images/32x32/upcoming-work.png','32x32'),(100,'user.png','/images/32x32/user.png','32x32'),(101,'world.png','/images/32x32/world.png','32x32'),(102,'zoom.png','/images/32x32/zoom.png','32x32');

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP地址',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `url` varchar(200) DEFAULT NULL COMMENT '访问链接',
  `clazz` varchar(100) DEFAULT NULL COMMENT '访问的类',
  `method` varchar(50) DEFAULT NULL COMMENT '访问的方法',
  `moudle` varchar(30) DEFAULT NULL COMMENT '模块',
  `function` varchar(30) DEFAULT NULL COMMENT '功能',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1787 DEFAULT CHARSET=utf8 COMMENT='日志';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`time`,`ip`,`login_name`,`url`,`clazz`,`method`,`moudle`,`function`) values (1721,'2017-06-01 19:55:16','127.0.0.1','admin','http://127.0.0.1:8081/sys/logs/delete','me.jinkun.rds.sys.web.SysLogController','delete','日志','删除日志'),(1722,'2017-06-01 19:55:16','127.0.0.1','admin','http://127.0.0.1:8081/sys/logs/','me.jinkun.rds.sys.web.SysLogController','list','日志','获取日志列表'),(1723,'2017-06-01 19:55:18','127.0.0.1','admin','http://127.0.0.1:8081/','me.jinkun.rds.IndexController','index','首页','首页'),(1724,'2017-06-01 19:55:19','127.0.0.1','admin','http://127.0.0.1:8081/menu/-1/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1725,'2017-06-01 19:55:19','127.0.0.1','admin','http://127.0.0.1:8081/sys/logs/pv/','me.jinkun.rds.sys.web.SysLogController','pv','日志','PV 统计'),(1726,'2017-06-01 19:55:19','127.0.0.1','admin','http://127.0.0.1:8081/menu/250/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1727,'2017-06-01 19:55:21','127.0.0.1','admin','http://127.0.0.1:8081/menu/236/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1728,'2017-06-01 19:55:23','127.0.0.1','admin','http://127.0.0.1:8081/menu/238/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1729,'2017-06-01 19:55:24','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/list.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1730,'2017-06-01 19:55:24','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/','me.jinkun.rds.sys.web.SysResourceController','list','资源','获取资源列表'),(1731,'2017-06-01 19:55:27','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/input.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1732,'2017-06-01 19:55:27','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/258','me.jinkun.rds.sys.web.SysResourceController','get','资源','获取资源'),(1733,'2017-06-01 19:55:27','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/tree','me.jinkun.rds.sys.web.SysResourceController','tree','资源','获取资源树'),(1734,'2017-06-01 19:55:28','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/icon.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1735,'2017-06-01 19:55:28','127.0.0.1','admin','http://127.0.0.1:8081/sys/icons','me.jinkun.rds.sys.web.SysIconController','list','图标','获取图标列表'),(1736,'2017-06-01 19:55:31','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/save','me.jinkun.rds.sys.web.SysResourceController','save','资源','新增或更新资源'),(1737,'2017-06-01 19:55:31','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/','me.jinkun.rds.sys.web.SysResourceController','list','资源','获取资源列表'),(1738,'2017-06-01 19:55:36','127.0.0.1','admin','http://127.0.0.1:8081/menu/258/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1739,'2017-06-01 19:55:44','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/input.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1740,'2017-06-01 19:55:44','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/258','me.jinkun.rds.sys.web.SysResourceController','get','资源','获取资源'),(1741,'2017-06-01 19:55:44','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/tree','me.jinkun.rds.sys.web.SysResourceController','tree','资源','获取资源树'),(1742,'2017-06-01 19:55:45','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/icon.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1743,'2017-06-01 19:55:45','127.0.0.1','admin','http://127.0.0.1:8081/sys/icons','me.jinkun.rds.sys.web.SysIconController','list','图标','获取图标列表'),(1744,'2017-06-01 19:55:47','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/save','me.jinkun.rds.sys.web.SysResourceController','save','资源','新增或更新资源'),(1745,'2017-06-01 19:55:47','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/','me.jinkun.rds.sys.web.SysResourceController','list','资源','获取资源列表'),(1746,'2017-06-01 19:55:50','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/','me.jinkun.rds.sys.web.SysResourceController','list','资源','获取资源列表'),(1747,'2017-06-01 19:55:54','127.0.0.1','admin','http://127.0.0.1:8081/menu/237/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1748,'2017-06-01 19:55:58','127.0.0.1','admin','http://127.0.0.1:8081/sys/icons/ui/list.html','me.jinkun.rds.sys.web.SysIconController','ui','图标','页面跳转'),(1749,'2017-06-01 19:55:58','127.0.0.1','admin','http://127.0.0.1:8081/sys/icons/','me.jinkun.rds.sys.web.SysIconController','list','图标','获取图标列表'),(1750,'2017-06-01 19:56:00','127.0.0.1','admin','http://127.0.0.1:8081/sys/icons/','me.jinkun.rds.sys.web.SysIconController','list','图标','获取图标列表'),(1751,'2017-06-01 19:56:01','127.0.0.1','admin','http://127.0.0.1:8081/sys/logs/ui/list.html','me.jinkun.rds.sys.web.SysLogController','ui','日志','页面跳转'),(1752,'2017-06-01 19:56:01','127.0.0.1','admin','http://127.0.0.1:8081/sys/logs/','me.jinkun.rds.sys.web.SysLogController','list','日志','获取日志列表'),(1753,'2017-06-01 19:58:00','127.0.0.1','admin','http://127.0.0.1:8081/login.html','me.jinkun.rds.IndexController','loginui','首页','登录页面'),(1754,'2017-06-01 19:58:01','127.0.0.1','admin','http://127.0.0.1:8081/login','me.jinkun.rds.IndexController','login','首页','登录'),(1755,'2017-06-01 19:58:02','127.0.0.1','admin','http://127.0.0.1:8081/','me.jinkun.rds.IndexController','index','首页','首页'),(1756,'2017-06-01 19:58:02','127.0.0.1','admin','http://127.0.0.1:8081/menu/-1/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1757,'2017-06-01 19:58:02','127.0.0.1','admin','http://127.0.0.1:8081/sys/logs/pv/','me.jinkun.rds.sys.web.SysLogController','pv','日志','PV 统计'),(1758,'2017-06-01 19:58:02','127.0.0.1','admin','http://127.0.0.1:8081/menu/250/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1759,'2017-06-01 19:58:04','127.0.0.1','admin','http://127.0.0.1:8081/menu/236/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1760,'2017-06-01 19:58:06','127.0.0.1','admin','http://127.0.0.1:8081/menu/258/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1761,'2017-06-01 19:58:06','127.0.0.1','admin','http://127.0.0.1:8081/sys/icons/ui/list.html','me.jinkun.rds.sys.web.SysIconController','ui','图标','页面跳转'),(1762,'2017-06-01 19:58:06','127.0.0.1','admin','http://127.0.0.1:8081/sys/icons/','me.jinkun.rds.sys.web.SysIconController','list','图标','获取图标列表'),(1763,'2017-06-01 19:58:08','127.0.0.1','admin','http://127.0.0.1:8081/menu/238/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1764,'2017-06-01 19:58:09','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/list.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1765,'2017-06-01 19:58:09','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/','me.jinkun.rds.sys.web.SysResourceController','list','资源','获取资源列表'),(1766,'2017-06-01 19:58:11','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/input.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1767,'2017-06-01 19:58:11','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/251','me.jinkun.rds.sys.web.SysResourceController','get','资源','获取资源'),(1768,'2017-06-01 19:58:11','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/tree','me.jinkun.rds.sys.web.SysResourceController','tree','资源','获取资源树'),(1769,'2017-06-01 19:58:12','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/icon.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1770,'2017-06-01 19:58:12','127.0.0.1','admin','http://127.0.0.1:8081/sys/icons','me.jinkun.rds.sys.web.SysIconController','list','图标','获取图标列表'),(1771,'2017-06-01 20:07:52','127.0.0.1','admin','http://127.0.0.1:8081/login.html','me.jinkun.rds.IndexController','loginui','首页','登录页面'),(1772,'2017-06-01 20:07:54','127.0.0.1','admin','http://127.0.0.1:8081/login','me.jinkun.rds.IndexController','login','首页','登录'),(1773,'2017-06-01 20:07:54','127.0.0.1','admin','http://127.0.0.1:8081/','me.jinkun.rds.IndexController','index','首页','首页'),(1774,'2017-06-01 20:07:55','127.0.0.1','admin','http://127.0.0.1:8081/menu/-1/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1775,'2017-06-01 20:07:55','127.0.0.1','admin','http://127.0.0.1:8081/sys/logs/pv/','me.jinkun.rds.sys.web.SysLogController','pv','日志','PV 统计'),(1776,'2017-06-01 20:07:55','127.0.0.1','admin','http://127.0.0.1:8081/menu/250/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1777,'2017-06-01 20:07:57','127.0.0.1','admin','http://127.0.0.1:8081/menu/236/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1778,'2017-06-01 20:07:58','127.0.0.1','admin','http://127.0.0.1:8081/menu/238/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1779,'2017-06-01 20:07:59','127.0.0.1','admin','http://127.0.0.1:8081/menu/237/children','me.jinkun.rds.IndexController','topMenu','首页','菜单'),(1780,'2017-06-01 20:08:02','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/list.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1781,'2017-06-01 20:08:02','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/','me.jinkun.rds.sys.web.SysResourceController','list','资源','获取资源列表'),(1782,'2017-06-01 20:08:05','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/input.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1783,'2017-06-01 20:08:05','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/257','me.jinkun.rds.sys.web.SysResourceController','get','资源','获取资源'),(1784,'2017-06-01 20:08:05','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/tree','me.jinkun.rds.sys.web.SysResourceController','tree','资源','获取资源树'),(1785,'2017-06-01 20:08:06','127.0.0.1','admin','http://127.0.0.1:8081/sys/resources/ui/icon.html','me.jinkun.rds.sys.web.SysResourceController','ui','资源','页面跳转'),(1786,'2017-06-01 20:08:06','127.0.0.1','admin','http://127.0.0.1:8081/sys/icons','me.jinkun.rds.sys.web.SysIconController','list','图标','获取图标列表');

/*Table structure for table `sys_org` */

DROP TABLE IF EXISTS `sys_org`;

CREATE TABLE `sys_org` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '组织名',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `code` varchar(64) NOT NULL COMMENT '编号',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级主键',
  `is_leaf` tinyint(1) DEFAULT '0' COMMENT '叶子节点',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='组织机构';

/*Data for the table `sys_org` */

insert  into `sys_org`(`id`,`name`,`address`,`code`,`icon`,`pid`,`is_leaf`,`seq`,`del_flag`,`update_time`,`create_time`) values (26,'百度','','','',NULL,1,1,0,'2017-04-21 15:32:14','2017-01-06 12:37:12'),(27,'事业部','','','',26,1,0,0,'2017-01-06 12:38:56','2017-01-06 12:37:26'),(28,'开发部','','','',27,0,0,0,'2017-01-06 12:37:37','2017-01-06 12:37:37'),(34,'系统开发','','','',NULL,1,0,0,'2017-05-25 15:22:36','2017-05-25 15:22:36'),(42,'软件开发','','','',34,0,0,0,'2017-05-25 15:39:32','2017-05-25 15:39:32');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `open_mode` varchar(32) DEFAULT NULL COMMENT '打开方式 ajax,iframe',
  `description` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(100) DEFAULT NULL COMMENT '资源图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级资源id',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `resource_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类别',
  `is_leaf` tinyint(1) DEFAULT '0' COMMENT '是否是叶子',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`name`,`url`,`open_mode`,`description`,`icon`,`pid`,`seq`,`status`,`resource_type`,`is_leaf`,`del_flag`,`update_time`,`create_time`) values (236,'系统管理','','0','','/images/32x32/settings.png',NULL,1,0,0,1,0,'2017-05-22 22:51:03','2017-01-08 03:33:16'),(237,'机构管理','','0','','/images/32x32/1-1.png',236,0,0,0,1,0,'2017-05-22 22:39:11','2017-01-08 03:33:32'),(238,'权限管理','','0','','/images/32x32/1-1.png',236,1,0,0,1,0,'2017-05-22 22:39:18','2017-01-08 03:38:43'),(239,'机构列表','/sys/orgs/ui/list.html','0','','/images/32x32/user.png',237,0,0,0,1,0,'2017-05-23 08:12:16','2017-01-08 03:38:56'),(240,'用户列表','/sys/users/ui/list.html','0','','/images/32x32/1-1.png',237,1,0,0,0,0,'2017-05-26 15:38:30','2017-01-08 03:39:08'),(245,'资源列表','/sys/resources/ui/list.html','0','','/images/32x32/graphic-design.png',238,0,0,0,0,0,'2017-05-23 08:19:42','2017-01-10 21:15:22'),(246,'角色列表','/sys/roles/ui/list.html','0','','/images/32x32/1-1.png',238,1,0,0,0,0,'2017-05-26 15:39:02','2017-01-10 21:16:15'),(250,'一级菜单','','0','','/images/32x32/billing.png',NULL,3,0,0,1,0,'2017-05-26 15:20:16','2017-05-26 08:40:33'),(251,'二级菜单1','','0','','/images/32x32/feed.png',250,0,1,0,1,0,'2017-05-26 15:28:49','2017-05-29 08:52:18'),(254,'3级菜单','','0','','/images/32x32/collaboration.png',251,0,0,0,0,0,'2017-05-26 14:39:59','2017-05-26 14:35:39'),(255,'3级菜单2','','0','','/images/32x32/delicious.png',251,1,0,0,0,0,'2017-05-26 15:26:36','2017-05-26 15:26:36'),(256,'二级菜单2','','0','','/images/32x32/plus.png',250,1,0,0,1,0,'2017-05-26 15:28:36','2017-05-26 15:28:06'),(257,'3级菜单1','','0','','/images/32x32/cv.png',256,0,0,0,0,0,'2017-05-26 16:39:23','2017-05-26 16:39:23'),(258,'其它管理','','0','','/images/32x32/issue.png',236,2,0,0,1,0,'2017-06-01 19:55:48','2017-05-27 16:40:18'),(259,'日志管理','/sys/logs/ui/list.html','0','','/images/32x32/date.png',258,0,0,0,0,0,'2017-05-27 16:56:49','2017-05-27 16:43:38'),(260,'图标管理','/sys/icons/ui/list.html','0','','/images/32x32/product.png',258,1,0,0,0,0,'2017-06-01 17:15:06','2017-06-01 17:14:59');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序号',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`seq`,`description`,`status`,`del_flag`,`update_time`,`create_time`) values (11,'系统管理员',0,'',0,0,'2017-01-08 17:30:37','2017-01-08 17:30:37');

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=762 DEFAULT CHARSET=utf8 COMMENT='角色-资源';

/*Data for the table `sys_role_resource` */

insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`) values (680,14,247),(681,14,248),(682,14,249),(683,14,250),(684,14,251),(685,14,254),(746,11,236),(747,11,237),(748,11,239),(749,11,240),(750,11,238),(751,11,245),(752,11,246),(753,11,258),(754,11,259),(755,11,260),(756,11,250),(757,11,251),(758,11,254),(759,11,255),(760,11,256),(761,11,257);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) NOT NULL COMMENT '登录名',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `sex` tinyint(2) DEFAULT '0' COMMENT '性别',
  `age` tinyint(2) DEFAULT '0' COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_type` tinyint(2) DEFAULT '0' COMMENT '用户类别',
  `status` tinyint(2) DEFAULT '0' COMMENT '用户状态',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`login_name`,`name`,`password`,`sex`,`age`,`phone`,`user_type`,`status`,`del_flag`,`update_time`,`create_time`) values (38,'admin','系统管理员','123456',0,NULL,'',0,0,0,'2017-05-26 15:54:37','2017-01-09 10:52:59'),(47,'test','test','123456',0,0,'',0,0,0,'2017-05-26 16:05:13','2017-05-26 14:47:09');

/*Table structure for table `sys_user_org` */

DROP TABLE IF EXISTS `sys_user_org`;

CREATE TABLE `sys_user_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户-机构';

/*Data for the table `sys_user_org` */

insert  into `sys_user_org`(`id`,`user_id`,`org_id`) values (22,38,42),(31,47,28);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='用户-角色';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (20,38,11),(23,47,14);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
