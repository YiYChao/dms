/*
 Navicat Premium Data Transfer

 Source Server         : QLY
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 59.110.237.77:3306
 Source Schema         : dms

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 22/03/2019 11:23:28
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
-- Table structure for matched_logrec
-- ----------------------------
DROP TABLE IF EXISTS `matched_logrec`;
CREATE TABLE `matched_logrec`  (
  `login_id` int(11) NOT NULL,
  `logout_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`login_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

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

SET FOREIGN_KEY_CHECKS = 1;
