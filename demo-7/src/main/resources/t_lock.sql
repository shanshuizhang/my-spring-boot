/*
Navicat MySQL Data Transfer

Source Server         : 本地环境
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : zsscode

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-09-04 20:57:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_lock
-- ----------------------------
DROP TABLE IF EXISTS `t_lock`;
CREATE TABLE `t_lock` (
  `lock_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '锁唯一标志',
  `lock_count` int unsigned NOT NULL DEFAULT '0' COMMENT '持有锁的次数，实现可重入锁',
  `version` int unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
  `hold_time` bigint unsigned NOT NULL DEFAULT '0' COMMENT '持有锁的时间，超过则默认释放',
  `request_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '请求id',
  PRIMARY KEY (`lock_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_lock
-- ----------------------------
