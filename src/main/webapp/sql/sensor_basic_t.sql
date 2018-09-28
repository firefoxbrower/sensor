/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : grassdatabase

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2016-12-12 16:06:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sensor_basic_t
-- ----------------------------
DROP TABLE IF EXISTS `sensor_basic_t`;
CREATE TABLE `sensor_basic_t` (
  `device_ip` varchar(20) NOT NULL,
  `device_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `longitude` varchar(20) DEFAULT NULL,
  `latitude` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`device_ip`,`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sensor_basic_t
-- ----------------------------
INSERT INTO `sensor_basic_t` VALUES ('000.000.000', '1', '1', '120.43047222', '43.55444444');
INSERT INTO `sensor_basic_t` VALUES ('000.000.001', '2', '1', '120.43225000', '43.56080556');
INSERT INTO `sensor_basic_t` VALUES ('000.000.002', '3', '1', '120.45841667', '43.55800000');
INSERT INTO `sensor_basic_t` VALUES ('16032500028688', '3925', '1', '116.35678', '39.972633');
INSERT INTO `sensor_basic_t` VALUES ('16032500028696', '3927', '1', '116.35639', '39.972973');
