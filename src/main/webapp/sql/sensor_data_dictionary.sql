/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : grassdatabase

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2016-12-12 15:59:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sensor_data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sensor_data_dictionary`;
CREATE TABLE `sensor_data_dictionary` (
  `param_id` int(11) NOT NULL COMMENT '检测的参数的id，name一起使用',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`param_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 7168 kB; InnoDB free: 7168 kB';

-- ----------------------------
-- Records of sensor_data_dictionary
-- ----------------------------
INSERT INTO `sensor_data_dictionary` VALUES ('34', '土壤温度', '℃', '记录土壤或地表的温度');
INSERT INTO `sensor_data_dictionary` VALUES ('35', '空气温度', '℃', '空气温度');
INSERT INTO `sensor_data_dictionary` VALUES ('36', '相对湿度', '%', '相对湿度');
INSERT INTO `sensor_data_dictionary` VALUES ('37', '露点', '℃', '记录露点温度');
INSERT INTO `sensor_data_dictionary` VALUES ('38', '大气压力', 'hPa', '大气压力');
INSERT INTO `sensor_data_dictionary` VALUES ('39', '大气温度', '℃', '大气压力校准温度');
INSERT INTO `sensor_data_dictionary` VALUES ('40', '最大风速', 'm/s', '记录一段时间内的最大风速');
INSERT INTO `sensor_data_dictionary` VALUES ('41', '风速', 'm/s', '记录一段时间内的平均风速');
INSERT INTO `sensor_data_dictionary` VALUES ('42', '风向', '°', '记录风向数据(0-360°)');
