/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : angle-dist-trans-order

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-07-30 14:05:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `order_no` varchar(64) NOT NULL,
  `user_id` int(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_num` int(10) DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('123456799999', '1', '2019-07-29 15:57:13', '1', '2');
INSERT INTO `order_info` VALUES ('657898655', '1', '2019-07-29 21:07:39', '1', '2');
INSERT INTO `order_info` VALUES ('987654321', '1', '2019-07-29 16:01:06', '1', '2');
INSERT INTO `order_info` VALUES ('9876543210', '1', '2019-07-29 16:23:40', '1', '2');
INSERT INTO `order_info` VALUES ('98765432101', '1', '2019-07-29 16:24:39', '1', '2');
