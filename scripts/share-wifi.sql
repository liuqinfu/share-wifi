/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100411
 Source Host           : localhost:3306
 Source Schema         : share-wifi

 Target Server Type    : MySQL
 Target Server Version : 100411
 File Encoding         : 65001

 Date: 13/08/2020 23:48:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_device_info
-- ----------------------------
DROP TABLE IF EXISTS `t_device_info`;
CREATE TABLE `t_device_info` (
  `id` char(32) NOT NULL,
  `device_id` varchar(100) NOT NULL COMMENT '设备唯一标识',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `is_block` int(1) NOT NULL DEFAULT 0 COMMENT '是否冻结，0：未冻结  1：已冻结',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_wifi_info
-- ----------------------------
DROP TABLE IF EXISTS `t_wifi_info`;
CREATE TABLE `t_wifi_info` (
  `id` char(32) NOT NULL,
  `device_rowId` char(32) NOT NULL COMMENT '设备行记录id',
  `bssid` varchar(50) NOT NULL COMMENT '热点唯一标识',
  `ssid` varchar(50) NOT NULL COMMENT '热点名称',
  `encry_type` varchar(20) DEFAULT NULL COMMENT '热点加密方式',
  `pwd` varchar(100) DEFAULT NULL COMMENT '热点密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `device_rowId` (`device_rowId`),
  CONSTRAINT `foreign_device_wifi` FOREIGN KEY (`device_rowId`) REFERENCES `t_device_info` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
