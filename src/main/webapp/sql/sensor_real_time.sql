/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : grassdatabase

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2016-12-13 09:04:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sensor_real_time
-- ----------------------------
DROP TABLE IF EXISTS `sensor_real_time`;
CREATE TABLE `sensor_real_time` (
  `device_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collect_time` datetime NOT NULL,
  `depth` double NOT NULL,
  `temp` double DEFAULT NULL,
  `ECe` float DEFAULT NULL,
  `water` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `device_id` (`device_id`,`collect_time`,`depth`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sensor_real_time
-- ----------------------------
INSERT INTO `sensor_real_time` VALUES ('1', '1', '2016-08-20 11:00:00', '10', '20', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '2', '2016-08-20 11:00:00', '20', '30', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '3', '2016-08-20 11:00:00', '30', '40', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '4', '2016-08-20 11:00:00', '40', '50', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '5', '2016-08-20 11:00:00', '50', '60', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '6', '2016-08-20 11:00:00', '60', '70', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '7', '2016-08-20 11:00:00', '70', '80', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '8', '2016-08-20 11:00:00', '80', '90', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '9', '2016-08-20 11:00:00', '90', '100', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '10', '2016-08-20 11:00:00', '100', '110', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '11', '2016-08-20 11:00:00', '1000', '85', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '12', '2016-08-21 11:00:00', '10', '20', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '13', '2016-08-21 11:00:00', '20', '30', '10', '20');
INSERT INTO `sensor_real_time` VALUES ('1', '14', '2016-08-21 11:00:00', '30', '40', '10', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '15', '2016-08-21 11:00:00', '40', '50', '10', '12');
INSERT INTO `sensor_real_time` VALUES ('1', '16', '2016-08-21 11:00:00', '50', '60', '10', '38');
INSERT INTO `sensor_real_time` VALUES ('1', '17', '2016-08-21 11:00:00', '60', '70', '10', '21');
INSERT INTO `sensor_real_time` VALUES ('1', '18', '2016-08-21 11:00:00', '80', '90', '10', '25');
INSERT INTO `sensor_real_time` VALUES ('1', '19', '2016-08-21 11:00:00', '70', '80', '10', '22');
INSERT INTO `sensor_real_time` VALUES ('1', '20', '2016-08-21 11:00:00', '90', '100', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '21', '2016-08-21 11:00:00', '100', '110', '10', '80');
INSERT INTO `sensor_real_time` VALUES ('1', '22', '2016-08-21 11:00:00', '1000', '85', '10', '100');
INSERT INTO `sensor_real_time` VALUES ('1', '23', '2000-01-02 11:00:00', '10', '20', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '24', '2000-01-02 11:00:00', '20', '30', '10', '90');
INSERT INTO `sensor_real_time` VALUES ('1', '25', '2000-01-02 11:00:00', '30', '40', '10', '13');
INSERT INTO `sensor_real_time` VALUES ('1', '26', '2000-01-02 11:00:00', '40', '50', '10', '22');
INSERT INTO `sensor_real_time` VALUES ('1', '27', '2000-01-02 11:00:00', '50', '60', '10', '48');
INSERT INTO `sensor_real_time` VALUES ('1', '28', '2000-01-02 11:00:00', '60', '70', '10', '11');
INSERT INTO `sensor_real_time` VALUES ('1', '29', '2000-01-02 11:00:00', '70', '80', '10', '42');
INSERT INTO `sensor_real_time` VALUES ('1', '30', '2000-01-02 11:00:00', '80', '90', '10', '55');
INSERT INTO `sensor_real_time` VALUES ('1', '31', '2000-01-02 11:00:00', '90', '100', '10', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '32', '2000-01-02 11:00:00', '100', '110', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '33', '2000-01-02 11:00:00', '1000', '85', '10', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '34', '2016-08-22 11:00:00', '10', '20', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '35', '2016-08-22 11:00:00', '20', '30', '10', '90');
INSERT INTO `sensor_real_time` VALUES ('1', '36', '2016-08-22 11:00:00', '30', '40', '10', '13');
INSERT INTO `sensor_real_time` VALUES ('1', '37', '2016-08-22 11:00:00', '40', '50', '10', '22');
INSERT INTO `sensor_real_time` VALUES ('1', '38', '2016-08-22 11:00:00', '50', '60', '10', '48');
INSERT INTO `sensor_real_time` VALUES ('1', '39', '2016-08-22 11:00:00', '60', '70', '10', '11');
INSERT INTO `sensor_real_time` VALUES ('1', '40', '2016-08-22 11:00:00', '70', '80', '10', '42');
INSERT INTO `sensor_real_time` VALUES ('1', '41', '2016-08-22 11:00:00', '80', '90', '10', '55');
INSERT INTO `sensor_real_time` VALUES ('1', '42', '2016-08-22 11:00:00', '90', '100', '10', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '43', '2016-08-22 11:00:00', '100', '110', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '44', '2016-08-22 11:00:00', '1000', '85', '10', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '45', '2016-08-23 11:00:00', '10', '20', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '46', '2016-08-23 11:00:00', '20', '30', '10', '90');
INSERT INTO `sensor_real_time` VALUES ('1', '47', '2016-08-23 11:00:00', '30', '40', '10', '13');
INSERT INTO `sensor_real_time` VALUES ('1', '48', '2016-08-23 11:00:00', '40', '50', '10', '22');
INSERT INTO `sensor_real_time` VALUES ('1', '49', '2016-08-23 11:00:00', '50', '60', '10', '48');
INSERT INTO `sensor_real_time` VALUES ('1', '50', '2016-08-23 11:00:00', '60', '70', '10', '11');
INSERT INTO `sensor_real_time` VALUES ('1', '51', '2016-08-23 11:00:00', '70', '80', '10', '42');
INSERT INTO `sensor_real_time` VALUES ('1', '52', '2016-08-23 11:00:00', '80', '90', '10', '55');
INSERT INTO `sensor_real_time` VALUES ('1', '53', '2016-08-23 11:00:00', '90', '100', '10', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '54', '2016-08-23 11:00:00', '100', '110', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '55', '2016-08-23 11:00:00', '1000', '85', '10', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '56', '2016-08-24 11:00:00', '10', '20', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '57', '2016-08-24 11:00:00', '20', '30', '10', '90');
INSERT INTO `sensor_real_time` VALUES ('1', '58', '2016-08-24 11:00:00', '30', '40', '10', '13');
INSERT INTO `sensor_real_time` VALUES ('1', '59', '2016-08-24 11:00:00', '40', '50', '10', '22');
INSERT INTO `sensor_real_time` VALUES ('1', '60', '2016-08-24 11:00:00', '50', '60', '10', '48');
INSERT INTO `sensor_real_time` VALUES ('1', '61', '2016-08-24 11:00:00', '60', '70', '10', '11');
INSERT INTO `sensor_real_time` VALUES ('1', '62', '2016-08-24 11:00:00', '70', '80', '10', '42');
INSERT INTO `sensor_real_time` VALUES ('1', '63', '2016-08-24 11:00:00', '80', '90', '10', '55');
INSERT INTO `sensor_real_time` VALUES ('1', '64', '2016-08-24 11:00:00', '90', '100', '10', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '65', '2016-08-24 11:00:00', '100', '110', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '66', '2016-08-24 11:00:00', '1000', '85', '10', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '67', '2016-08-24 10:00:00', '10', '20', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '68', '2016-08-24 10:00:00', '20', '30', '10', '90');
INSERT INTO `sensor_real_time` VALUES ('1', '69', '2016-08-24 10:00:00', '30', '40', '10', '13');
INSERT INTO `sensor_real_time` VALUES ('1', '70', '2016-08-24 10:00:00', '40', '50', '10', '22');
INSERT INTO `sensor_real_time` VALUES ('1', '71', '2016-08-24 10:00:00', '50', '60', '10', '48');
INSERT INTO `sensor_real_time` VALUES ('1', '72', '2016-08-24 10:00:00', '60', '70', '10', '11');
INSERT INTO `sensor_real_time` VALUES ('1', '73', '2016-08-24 10:00:00', '70', '80', '10', '42');
INSERT INTO `sensor_real_time` VALUES ('1', '74', '2016-08-24 10:00:00', '80', '90', '10', '55');
INSERT INTO `sensor_real_time` VALUES ('1', '75', '2016-08-24 10:00:00', '90', '100', '10', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '76', '2016-08-24 10:00:00', '100', '110', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '77', '2016-08-24 10:00:00', '1000', '85', '10', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '78', '2016-10-14 11:00:00', '10', '44', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '79', '2016-10-14 11:00:00', '20', '37', '10', '20');
INSERT INTO `sensor_real_time` VALUES ('1', '80', '2016-10-14 11:00:00', '30', '29', '10', '30');
INSERT INTO `sensor_real_time` VALUES ('1', '81', '2016-10-14 11:00:00', '40', '25', '10', '40');
INSERT INTO `sensor_real_time` VALUES ('1', '82', '2016-10-14 11:00:00', '50', '21', '10', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '83', '2016-10-14 11:00:00', '60', '15', '10', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '84', '2016-10-14 11:00:00', '70', '12', '10', '70');
INSERT INTO `sensor_real_time` VALUES ('1', '85', '2016-10-14 11:00:00', '80', '7', '10', '80');
INSERT INTO `sensor_real_time` VALUES ('1', '86', '2016-10-14 11:00:00', '90', '4', '10', '90');
INSERT INTO `sensor_real_time` VALUES ('1', '87', '2016-10-14 11:00:00', '100', '1', '10', '100');
INSERT INTO `sensor_real_time` VALUES ('1', '88', '2016-10-14 11:00:00', '1000', '31', '10', '68');
INSERT INTO `sensor_real_time` VALUES ('1', '89', '2016-10-14 12:00:00', '10', '43', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '90', '2016-10-14 12:00:00', '20', '38', '10', '20');
INSERT INTO `sensor_real_time` VALUES ('1', '91', '2016-10-14 12:00:00', '30', '31', '10', '30');
INSERT INTO `sensor_real_time` VALUES ('1', '92', '2016-10-14 12:00:00', '40', '27', '10', '40');
INSERT INTO `sensor_real_time` VALUES ('1', '93', '2016-10-14 12:00:00', '50', '22', '10', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '94', '2016-10-14 12:00:00', '60', '16', '10', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '95', '2016-10-14 12:00:00', '70', '11', '10', '70');
INSERT INTO `sensor_real_time` VALUES ('1', '96', '2016-10-14 12:00:00', '80', '8', '10', '80');
INSERT INTO `sensor_real_time` VALUES ('1', '97', '2016-10-14 12:00:00', '90', '5', '10', '90');
INSERT INTO `sensor_real_time` VALUES ('1', '98', '2016-10-14 12:00:00', '100', '1', '10', '100');
INSERT INTO `sensor_real_time` VALUES ('1', '99', '2016-10-14 12:00:00', '1000', '30', '10', '78');
INSERT INTO `sensor_real_time` VALUES ('1', '100', '2016-10-15 12:00:00', '10', '45', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '101', '2016-10-15 12:00:00', '20', '38', '18', '20');
INSERT INTO `sensor_real_time` VALUES ('1', '102', '2016-10-15 12:00:00', '30', '30', '18', '30');
INSERT INTO `sensor_real_time` VALUES ('1', '103', '2016-10-15 12:00:00', '40', '25', '10', '40');
INSERT INTO `sensor_real_time` VALUES ('1', '104', '2016-10-15 12:00:00', '50', '20', '18', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '105', '2016-10-15 12:00:00', '60', '15', '18', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '106', '2016-10-15 12:00:00', '70', '10', '10', '70');
INSERT INTO `sensor_real_time` VALUES ('1', '107', '2016-10-15 12:00:00', '80', '8', '18', '80');
INSERT INTO `sensor_real_time` VALUES ('1', '108', '2016-10-15 12:00:00', '90', '6', '18', '90');
INSERT INTO `sensor_real_time` VALUES ('1', '109', '2016-10-15 12:00:00', '100', '2', '10', '100');
INSERT INTO `sensor_real_time` VALUES ('1', '110', '2016-10-15 12:00:00', '1000', '28', '18', '76');
INSERT INTO `sensor_real_time` VALUES ('1', '111', '2016-10-15 13:00:00', '10', '42', '10', '12');
INSERT INTO `sensor_real_time` VALUES ('1', '112', '2016-10-15 13:00:00', '20', '36', '18', '21');
INSERT INTO `sensor_real_time` VALUES ('1', '113', '2016-10-15 13:00:00', '30', '31', '18', '28');
INSERT INTO `sensor_real_time` VALUES ('1', '114', '2016-10-15 13:00:00', '40', '26', '10', '41');
INSERT INTO `sensor_real_time` VALUES ('1', '115', '2016-10-15 13:00:00', '50', '22', '18', '53');
INSERT INTO `sensor_real_time` VALUES ('1', '116', '2016-10-15 13:00:00', '60', '18', '18', '59');
INSERT INTO `sensor_real_time` VALUES ('1', '117', '2016-10-15 13:00:00', '70', '12', '10', '72');
INSERT INTO `sensor_real_time` VALUES ('1', '118', '2016-10-15 13:00:00', '80', '7', '18', '83');
INSERT INTO `sensor_real_time` VALUES ('1', '119', '2016-10-15 13:00:00', '90', '5', '18', '96');
INSERT INTO `sensor_real_time` VALUES ('1', '120', '2016-10-15 13:00:00', '100', '2', '10', '101');
INSERT INTO `sensor_real_time` VALUES ('1', '121', '2016-10-15 13:00:00', '1000', '34', '18', '70');
INSERT INTO `sensor_real_time` VALUES ('1', '122', '2016-10-15 14:00:00', '10', '43', '10', '13');
INSERT INTO `sensor_real_time` VALUES ('1', '123', '2016-10-15 14:00:00', '20', '37', '18', '22');
INSERT INTO `sensor_real_time` VALUES ('1', '124', '2016-10-15 14:00:00', '30', '32', '18', '29');
INSERT INTO `sensor_real_time` VALUES ('1', '125', '2016-10-15 14:00:00', '40', '27', '10', '42');
INSERT INTO `sensor_real_time` VALUES ('1', '126', '2016-10-15 14:00:00', '50', '23', '18', '54');
INSERT INTO `sensor_real_time` VALUES ('1', '127', '2016-10-15 14:00:00', '60', '19', '18', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '128', '2016-10-15 14:00:00', '70', '13', '10', '73');
INSERT INTO `sensor_real_time` VALUES ('1', '129', '2016-10-15 14:00:00', '80', '8', '18', '84');
INSERT INTO `sensor_real_time` VALUES ('1', '130', '2016-10-15 14:00:00', '90', '6', '18', '97');
INSERT INTO `sensor_real_time` VALUES ('1', '131', '2016-10-15 14:00:00', '100', '3', '10', '102');
INSERT INTO `sensor_real_time` VALUES ('1', '132', '2016-10-15 14:00:00', '1000', '35', '18', '71');
INSERT INTO `sensor_real_time` VALUES ('1', '133', '2016-10-16 14:00:00', '10', '48', '10', '18');
INSERT INTO `sensor_real_time` VALUES ('1', '134', '2016-10-16 14:00:00', '20', '42', '18', '27');
INSERT INTO `sensor_real_time` VALUES ('1', '135', '2016-10-16 14:00:00', '30', '37', '18', '34');
INSERT INTO `sensor_real_time` VALUES ('1', '136', '2016-10-16 14:00:00', '40', '32', '10', '47');
INSERT INTO `sensor_real_time` VALUES ('1', '137', '2016-10-16 14:00:00', '50', '28', '18', '59');
INSERT INTO `sensor_real_time` VALUES ('1', '138', '2016-10-16 14:00:00', '60', '24', '18', '65');
INSERT INTO `sensor_real_time` VALUES ('1', '139', '2016-10-16 14:00:00', '70', '18', '10', '78');
INSERT INTO `sensor_real_time` VALUES ('1', '140', '2016-10-16 14:00:00', '80', '13', '18', '89');
INSERT INTO `sensor_real_time` VALUES ('1', '141', '2016-10-16 14:00:00', '90', '11', '18', '102');
INSERT INTO `sensor_real_time` VALUES ('1', '142', '2016-10-16 14:00:00', '100', '8', '10', '108');
INSERT INTO `sensor_real_time` VALUES ('1', '143', '2016-10-16 14:00:00', '1000', '40', '18', '76');
INSERT INTO `sensor_real_time` VALUES ('1', '144', '2016-10-16 15:00:00', '10', '51', '10', '21');
INSERT INTO `sensor_real_time` VALUES ('1', '145', '2016-10-16 15:00:00', '20', '45', '18', '30');
INSERT INTO `sensor_real_time` VALUES ('1', '146', '2016-10-16 15:00:00', '30', '40', '18', '37');
INSERT INTO `sensor_real_time` VALUES ('1', '147', '2016-10-16 15:00:00', '40', '35', '10', '50');
INSERT INTO `sensor_real_time` VALUES ('1', '148', '2016-10-16 15:00:00', '50', '31', '18', '62');
INSERT INTO `sensor_real_time` VALUES ('1', '149', '2016-10-16 15:00:00', '60', '27', '18', '68');
INSERT INTO `sensor_real_time` VALUES ('1', '150', '2016-10-16 15:00:00', '70', '21', '10', '81');
INSERT INTO `sensor_real_time` VALUES ('1', '151', '2016-10-16 15:00:00', '80', '18', '18', '92');
INSERT INTO `sensor_real_time` VALUES ('1', '152', '2016-10-16 15:00:00', '90', '14', '18', '105');
INSERT INTO `sensor_real_time` VALUES ('1', '153', '2016-10-16 15:00:00', '100', '11', '10', '111');
INSERT INTO `sensor_real_time` VALUES ('1', '154', '2016-10-16 15:00:00', '1000', '43', '18', '79');
INSERT INTO `sensor_real_time` VALUES ('1', '155', '2016-10-16 16:00:00', '10', '47', '10', '17');
INSERT INTO `sensor_real_time` VALUES ('1', '156', '2016-10-16 16:00:00', '20', '41', '18', '26');
INSERT INTO `sensor_real_time` VALUES ('1', '157', '2016-10-16 16:00:00', '30', '36', '18', '34');
INSERT INTO `sensor_real_time` VALUES ('1', '158', '2016-10-16 16:00:00', '40', '31', '10', '46');
INSERT INTO `sensor_real_time` VALUES ('1', '159', '2016-10-16 16:00:00', '50', '27', '18', '58');
INSERT INTO `sensor_real_time` VALUES ('1', '160', '2016-10-16 16:00:00', '60', '23', '18', '64');
INSERT INTO `sensor_real_time` VALUES ('1', '161', '2016-10-16 16:00:00', '70', '17', '10', '77');
INSERT INTO `sensor_real_time` VALUES ('1', '162', '2016-10-16 16:00:00', '80', '14', '18', '88');
INSERT INTO `sensor_real_time` VALUES ('1', '163', '2016-10-16 16:00:00', '90', '10', '18', '101');
INSERT INTO `sensor_real_time` VALUES ('1', '164', '2016-10-16 16:00:00', '100', '7', '10', '107');
INSERT INTO `sensor_real_time` VALUES ('1', '165', '2016-10-16 16:00:00', '1000', '39', '18', '75');
INSERT INTO `sensor_real_time` VALUES ('1', '166', '2016-10-16 17:00:00', '10', '43', '10', '13');
INSERT INTO `sensor_real_time` VALUES ('1', '167', '2016-10-16 17:00:00', '20', '37', '18', '22');
INSERT INTO `sensor_real_time` VALUES ('1', '168', '2016-10-16 17:00:00', '30', '42', '18', '29');
INSERT INTO `sensor_real_time` VALUES ('1', '169', '2016-10-16 17:00:00', '40', '27', '10', '42');
INSERT INTO `sensor_real_time` VALUES ('1', '170', '2016-10-16 17:00:00', '50', '23', '18', '54');
INSERT INTO `sensor_real_time` VALUES ('1', '171', '2016-10-16 17:00:00', '60', '19', '18', '60');
INSERT INTO `sensor_real_time` VALUES ('1', '172', '2016-10-16 17:00:00', '70', '13', '10', '73');
INSERT INTO `sensor_real_time` VALUES ('1', '173', '2016-10-16 17:00:00', '80', '10', '18', '84');
INSERT INTO `sensor_real_time` VALUES ('1', '174', '2016-10-16 17:00:00', '90', '6', '18', '97');
INSERT INTO `sensor_real_time` VALUES ('1', '175', '2016-10-16 17:00:00', '100', '3', '10', '103');
INSERT INTO `sensor_real_time` VALUES ('1', '176', '2016-10-16 17:00:00', '1000', '35', '18', '71');
INSERT INTO `sensor_real_time` VALUES ('1', '177', '2016-10-17 11:00:00', '10', '40', '10', '10');
INSERT INTO `sensor_real_time` VALUES ('1', '178', '2016-10-17 11:00:00', '20', '34', '18', '19');
INSERT INTO `sensor_real_time` VALUES ('1', '179', '2016-10-17 11:00:00', '30', '39', '18', '26');
INSERT INTO `sensor_real_time` VALUES ('1', '180', '2016-10-17 11:00:00', '40', '24', '10', '39');
INSERT INTO `sensor_real_time` VALUES ('1', '181', '2016-10-17 11:00:00', '50', '20', '18', '51');
INSERT INTO `sensor_real_time` VALUES ('1', '182', '2016-10-17 11:00:00', '60', '16', '18', '57');
INSERT INTO `sensor_real_time` VALUES ('1', '183', '2016-10-17 11:00:00', '70', '10', '10', '70');
INSERT INTO `sensor_real_time` VALUES ('1', '184', '2016-10-17 11:00:00', '80', '7', '18', '81');
INSERT INTO `sensor_real_time` VALUES ('1', '185', '2016-10-17 11:00:00', '90', '3', '18', '94');
INSERT INTO `sensor_real_time` VALUES ('1', '186', '2016-10-17 11:00:00', '100', '1', '10', '100');
INSERT INTO `sensor_real_time` VALUES ('1', '187', '2016-10-17 11:00:00', '1000', '35', '18', '68');
INSERT INTO `sensor_real_time` VALUES ('1', '188', '2016-10-17 12:00:00', '10', '47', '10', '17');
INSERT INTO `sensor_real_time` VALUES ('1', '189', '2016-10-17 12:00:00', '20', '41', '18', '26');
INSERT INTO `sensor_real_time` VALUES ('1', '190', '2016-10-17 12:00:00', '30', '46', '18', '33');
INSERT INTO `sensor_real_time` VALUES ('1', '191', '2016-10-17 12:00:00', '40', '33', '10', '46');
INSERT INTO `sensor_real_time` VALUES ('1', '192', '2016-10-17 12:00:00', '50', '27', '18', '58');
INSERT INTO `sensor_real_time` VALUES ('1', '193', '2016-10-17 12:00:00', '60', '23', '18', '64');
INSERT INTO `sensor_real_time` VALUES ('1', '194', '2016-10-17 12:00:00', '70', '27', '10', '77');
INSERT INTO `sensor_real_time` VALUES ('1', '195', '2016-10-17 12:00:00', '80', '14', '18', '88');
INSERT INTO `sensor_real_time` VALUES ('1', '196', '2016-10-17 12:00:00', '90', '10', '18', '101');
INSERT INTO `sensor_real_time` VALUES ('1', '197', '2016-10-17 12:00:00', '100', '7', '10', '107');
INSERT INTO `sensor_real_time` VALUES ('1', '198', '2016-10-17 12:00:00', '1000', '35', '18', '75');
