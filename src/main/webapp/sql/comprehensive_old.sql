/*
Navicat MySQL Data Transfer

Source Server         : mysql-localhost
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : comprehensive_old

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-01-22 11:49:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(50) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  `isparent` tinyint(1) DEFAULT '1',
  `permission` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '用户管理', '/author/user', '5', '', '1', '0', 'author:*');
INSERT INTO `menu` VALUES ('10', '功能练习', null, '0', null, '1', '1', 'practice:*');
INSERT INTO `menu` VALUES ('11', '文件上传', '/practice/upload', '10', null, '1', '1', 'practice:*');
INSERT INTO `menu` VALUES ('12', 'POI操作Excel', '/practice/excel', '10', null, '1', '1', 'practice:*');
INSERT INTO `menu` VALUES ('2', '订单模块', '/module/order', '3', null, '1', '0', 'module:*');
INSERT INTO `menu` VALUES ('3', '模块管理', null, '0', null, '1', '1', 'module:*');
INSERT INTO `menu` VALUES ('4', '活动模块', '/module/activity', '3', null, '1', '0', 'module:*');
INSERT INTO `menu` VALUES ('5', 'Authority管理', null, '0', null, '1', '1', 'author:*');
INSERT INTO `menu` VALUES ('6', '角色管理', '/author/role', '5', null, '1', '0', 'author:*');
INSERT INTO `menu` VALUES ('7', '权限管理', '/author/permission', '5', null, '1', '0', 'author:*');
INSERT INTO `menu` VALUES ('8', '系统管理', '', '0', null, '1', '1', 'sysytem:*');
INSERT INTO `menu` VALUES ('9', '菜单管理', '/system/menu', '8', null, '1', '0', 'system:*');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(50) NOT NULL,
  `url` varchar(30) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` varchar(120) NOT NULL DEFAULT '1',
  `pid` varchar(50) DEFAULT NULL,
  `field1` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '', 'module:*', null, '模块管理', '1', '3', null);
INSERT INTO `permission` VALUES ('2', '', 'author:*', null, '权限管理', '1', '5', null);
INSERT INTO `permission` VALUES ('3', '', 'system:*', null, '系统管理', '1', '8', null);
INSERT INTO `permission` VALUES ('4', ' ', 'practice:*', null, '功能练习', '1', '10', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL,
  `createtime` varchar(120) NOT NULL,
  `updatetime` varchar(120) DEFAULT NULL,
  `field1` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '2018-06-25 11:20', '', null);
INSERT INTO `role` VALUES ('2', '普通用户', '2018-06-25 11:20', '', null);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(50) NOT NULL,
  `roleid` varchar(50) NOT NULL,
  `permissionid` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1f35804b0c704eaba5c720ab17163c0a', '2', '4');
INSERT INTO `role_permission` VALUES ('7060169bb1ea4ad3b4d9bd08f28cb89d', '1', '1');
INSERT INTO `role_permission` VALUES ('8270375fb25e435288c9c354de50843b', '1', '4');
INSERT INTO `role_permission` VALUES ('9766cdaa87ee43c48b897211a1b22714', '1', '3');
INSERT INTO `role_permission` VALUES ('ab41a4ce293246b58c1d17e5c3cb0671', '1', '2');
INSERT INTO `role_permission` VALUES ('c0d31e30d4db4795adec9f884c929446', '2', '1');

-- ----------------------------
-- Table structure for upload
-- ----------------------------
DROP TABLE IF EXISTS `upload`;
CREATE TABLE `upload` (
  `id` varchar(50) NOT NULL,
  `path` varchar(255) NOT NULL,
  `uploadtime` varchar(100) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `field1` varchar(200) DEFAULT NULL,
  `field2` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upload
-- ----------------------------
INSERT INTO `upload` VALUES ('89ceef0abdff4175a058080f53df2cc0', 'G:\\learn\\java\\practice\\target\\practice-1.0-SNAPSHOT\\/image/1530793181691.jpg', '2018-07-05 08:19:41', 'wdd', null, null);
INSERT INTO `upload` VALUES ('c1002c801c634b5084fe7e1389cf3f84', 'G:\\learn\\java\\practice\\target\\practice-1.0-SNAPSHOT\\/image/1530793104763.jpg', '2018-07-05 08:18:24', 'wang', null, null);
INSERT INTO `upload` VALUES ('c81e0586f7f74d4b89e1118e064394a9', 'G:\\learn\\java\\practice\\target\\practice-1.0-SNAPSHOT\\/image/1530793116582.jpg', '2018-07-05 08:18:36', 'wang', null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `createtime` varchar(40) NOT NULL,
  `field1` varchar(255) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('185ac77c434d44ca9b3f16c5b8fc2221', 'ooo', 'd754203d6bf22a4fd91ca0288afe6b92', 'f7e0a846f816f9f2c79b4b21b0c9b010', '2018-07-04 04:56:59', null, '1');
INSERT INTO `user` VALUES ('2be87cb39cba478ab5fe89c43c258284', 'xia', '91f124752f9a87dcaeaf22075c786bc6', 'f34f53d29ea04a72e79cc49944b8c984', '2018-07-04 04:44:08', null, '1');
INSERT INTO `user` VALUES ('49e4ea11d8074e0bae6409cd1369590f', 'ming', '70b865e6562b44e81e58fa9478aadae9', '70186bd44b5bb07521f806ddcd3cc4d8', '2018-07-09 08:47:50', null, '0');
INSERT INTO `user` VALUES ('63a333ea83a84f0eb58513efce4d3f84', 'asd', '2844a359ffc85ab565293e8c3251c08d', 'a99dcff1eb8108725a153de97fced1bd', '2018-07-05 01:37:21', null, '0');
INSERT INTO `user` VALUES ('6bc9f7b4ece34faeb9849bd06ee4cdb3', 'qwe', 'f935d6dd27e6670f455447b3bf725948', '2792064085ae761957504f48b33875c2', '2018-07-05 01:40:40', null, '0');
INSERT INTO `user` VALUES ('749ebaa901924a6f91ce23a4fdfc20db', 'ling', 'ce627abf3e5a739645360d5319f9cb34', '2c78fbb4ae863a94fb8e6e747d152e71', '2019-01-22 11:26:36', null, '0');
INSERT INTO `user` VALUES ('a99b033a7a1d412296f255191b619a72', 'qqq', '770846f1aca6d92b72e96c8006c063d0', '3fc29ab4d3159d7286704ed39ea50d13', '2018-07-05 01:40:28', null, '0');
INSERT INTO `user` VALUES ('b509535de9f840419c5a71556e168831', 'yun', '672b2cd72bc93010776881cc468c87c2', '1f4a40b154dc2d4ba9a27df6520b3403', '2018-07-10 02:08:41', null, '0');
INSERT INTO `user` VALUES ('b678366dd812466f8acf974327d31d5e', 'hhh', '7a836209d5ef130ee6c90ebe3ee2d246', '72be8499f447ec908aa73d2b1954a25a', '2018-07-13 02:50:07', null, '0');
INSERT INTO `user` VALUES ('cba72b0bd5f94ff8a4374d3bd33ac0ee', 'dsa', 'a773da7b8bc0d0d7d486804f255440ff', 'fa8f9bd902b2402829fe08904d57aa4c', '2018-07-05 01:39:20', null, '0');
INSERT INTO `user` VALUES ('e536909981cf471b9e5257dd9ff3f0d8', 'wang', 'e39fd15d1fdd475fff437e12ce253dfd', 'e7e382a10963f248fe1f7be688b85060', '2018-07-04 04:39:51', null, '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(50) NOT NULL,
  `userid` varchar(50) NOT NULL,
  `roleid` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1919905cdea44e97b96ff9c61a8228f5', '2be87cb39cba478ab5fe89c43c258284', '1');
INSERT INTO `user_role` VALUES ('1cfbaaa22afa4424a1243bcb149c797c', 'b678366dd812466f8acf974327d31d5e', '2');
INSERT INTO `user_role` VALUES ('2057d391ff404dcfb085d09c46e11dc9', '63a333ea83a84f0eb58513efce4d3f84', '2');
INSERT INTO `user_role` VALUES ('2b20048ffdc84a0c85b09f6bce25193f', 'a99b033a7a1d412296f255191b619a72', '2');
INSERT INTO `user_role` VALUES ('509a663bd88f42848f7b8a6bf3435e30', 'b509535de9f840419c5a71556e168831', '1');
INSERT INTO `user_role` VALUES ('5d4bc31777954b408692f1e52783b013', 'e536909981cf471b9e5257dd9ff3f0d8', '1');
INSERT INTO `user_role` VALUES ('7582f2bae603414088c031c1ff3e8aca', '749ebaa901924a6f91ce23a4fdfc20db', '2');
INSERT INTO `user_role` VALUES ('761d9954027149e8b5f646550048dc76', '185ac77c434d44ca9b3f16c5b8fc2221', '2');
INSERT INTO `user_role` VALUES ('989afc050cb14210b31b0e8c1e829b41', '6bc9f7b4ece34faeb9849bd06ee4cdb3', '2');
INSERT INTO `user_role` VALUES ('9d53c11387bb46458c9b30c08aea139b', '49e4ea11d8074e0bae6409cd1369590f', '2');
INSERT INTO `user_role` VALUES ('e8d61f91eca74c358f09e8c8bdf73b2f', 'cba72b0bd5f94ff8a4374d3bd33ac0ee', '2');
