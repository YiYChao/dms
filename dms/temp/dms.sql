/*
 Navicat Premium Data Transfer

 Source Server         : Alibaba
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 59.110.237.77:3306
 Source Schema         : dms

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 25/03/2019 23:06:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gather_logrec
-- ----------------------------
DROP TABLE IF EXISTS `gather_logrec`;
CREATE TABLE `gather_logrec`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `time` datetime(0) NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `logtype` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gather_logrec
-- ----------------------------
INSERT INTO `gather_logrec` VALUES (1001, '2019-03-23 12:14:06', '太原', 1, 'zhangsan', '192.168.25.123', 1);
INSERT INTO `gather_logrec` VALUES (1002, '2019-03-23 12:14:06', '太原', 1, 'zhangsan', '192.168.25.123', 0);
INSERT INTO `gather_logrec` VALUES (1003, '2019-03-23 12:14:06', '北京', 1, 'lisi', '192.168.25.165', 1);
INSERT INTO `gather_logrec` VALUES (1004, '2019-03-23 12:14:06', '北京', 1, 'lisi', '192.168.25.165', 0);
INSERT INTO `gather_logrec` VALUES (1005, '2019-03-23 12:14:06', '青岛', 1, 'wangwu', '192.168.25.159', 1);
INSERT INTO `gather_logrec` VALUES (1006, '2019-03-23 12:14:06', '青岛', 1, 'wangwu', '192.168.25.159', 0);

-- ----------------------------
-- Table structure for gather_transport
-- ----------------------------
DROP TABLE IF EXISTS `gather_transport`;
CREATE TABLE `gather_transport`  (
  `id` int(11) NOT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `handler` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `receiver` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `transport_type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gather_transport
-- ----------------------------
INSERT INTO `gather_transport` VALUES (2001, '2019-03-23 12:14:08', '太原', 1, 'zhangsan', 'chao1', 1);
INSERT INTO `gather_transport` VALUES (2002, '2019-03-23 12:14:08', '北京', 1, 'lisi', 'chao1', 2);
INSERT INTO `gather_transport` VALUES (2003, '2019-03-23 12:14:08', '青岛', 1, 'wangwu', 'chao1', 3);
INSERT INTO `gather_transport` VALUES (2004, '2019-03-23 12:14:08', '太原', 1, 'zhangsan', 'chao1', 1);
INSERT INTO `gather_transport` VALUES (2005, '2019-03-23 12:14:08', '北京', 1, 'lisi', 'chao1', 2);
INSERT INTO `gather_transport` VALUES (2006, '2019-03-23 12:14:08', '青岛', 1, 'wangwu', 'chao1', 3);

-- ----------------------------
-- Table structure for matched_logrec
-- ----------------------------
DROP TABLE IF EXISTS `matched_logrec`;
CREATE TABLE `matched_logrec`  (
  `login_id` int(11) NOT NULL,
  `logout_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`login_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of matched_logrec
-- ----------------------------
INSERT INTO `matched_logrec` VALUES (1001, 1002);
INSERT INTO `matched_logrec` VALUES (1003, 1004);
INSERT INTO `matched_logrec` VALUES (1005, 1006);

-- ----------------------------
-- Table structure for matched_transport
-- ----------------------------
DROP TABLE IF EXISTS `matched_transport`;
CREATE TABLE `matched_transport`  (
  `send_id` int(11) NOT NULL,
  `trans_id` int(11) NULL DEFAULT NULL,
  `received_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`send_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of matched_transport
-- ----------------------------
INSERT INTO `matched_transport` VALUES (2001, 2002, 2003);
INSERT INTO `matched_transport` VALUES (2004, 2005, 2006);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `hobby` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `degree` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Chao', '123456', 1, '阅读,游泳,旅游', '山西省太原市', '本科');

SET FOREIGN_KEY_CHECKS = 1;
