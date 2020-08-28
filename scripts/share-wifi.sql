/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : share-wifi

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-08-28 17:27:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_device_flux
-- ----------------------------
DROP TABLE IF EXISTS `t_device_flux`;
CREATE TABLE `t_device_flux` (
  `device_id` varchar(100) NOT NULL COMMENT 'STA角色设备的device_id',
  `bssid` varchar(50) DEFAULT NULL COMMENT '热点唯一标识',
  `ssid` varchar(50) NOT NULL COMMENT '热点名称',
  `flux` double NOT NULL DEFAULT 0 COMMENT '流量，单位Kb',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '上报时间',
  KEY `foreign_device_flux` (`device_id`),
  CONSTRAINT `foreign_device_flux` FOREIGN KEY (`device_id`) REFERENCES `t_device_info` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='终端设备流量使用情况，只记录STA设备';

-- ----------------------------
-- Table structure for t_device_info
-- ----------------------------
DROP TABLE IF EXISTS `t_device_info`;
CREATE TABLE `t_device_info` (
  `device_id` varchar(100) NOT NULL COMMENT '设备唯一标识',
  `band` varchar(20) NOT NULL COMMENT '手机品牌',
  `sys_v` varchar(50) DEFAULT NULL COMMENT '系统版本',
  `ui_v` varchar(50) DEFAULT NULL COMMENT 'ui版本',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '是否冻结，0：未冻结  1：已冻结',
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='终端设备信息';

-- ----------------------------
-- Table structure for t_flux_meal
-- ----------------------------
DROP TABLE IF EXISTS `t_flux_meal`;
CREATE TABLE `t_flux_meal` (
  `id` char(32) NOT NULL,
  `device_id` char(32) NOT NULL COMMENT '设备唯一标识',
  `order_id` char(32) NOT NULL COMMENT '订单id',
  `used_flux` double NOT NULL DEFAULT 0 COMMENT '已使用流量 Kb',
  `left_flux` double NOT NULL COMMENT '剩余流量  Kb',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '套餐状态  1：使用中、2：使用完、3：已失效',
  `start_time` datetime NOT NULL COMMENT '生效时间',
  `invild_time` datetime NOT NULL COMMENT '失效时间',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `foreign_device_order2` (`device_id`),
  KEY `foreign_order_order2` (`order_id`),
  CONSTRAINT `foreign_device_order2` FOREIGN KEY (`device_id`) REFERENCES `t_device_info` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `foreign_order_order2` FOREIGN KEY (`order_id`) REFERENCES `t_order_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_gps_his
-- ----------------------------
DROP TABLE IF EXISTS `t_gps_his`;
CREATE TABLE `t_gps_his` (
  `device_id` varchar(100) NOT NULL COMMENT '设备唯一标识',
  `latitude` decimal(11,0) NOT NULL COMMENT '纬度',
  `longitude` decimal(11,0) NOT NULL COMMENT '经度',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '上报时间',
  KEY `foreign_device_gps` (`device_id`),
  CONSTRAINT `foreign_device_gps` FOREIGN KEY (`device_id`) REFERENCES `t_device_info` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='终端设备位置表';

-- ----------------------------
-- Table structure for t_order_info
-- ----------------------------
DROP TABLE IF EXISTS `t_order_info`;
CREATE TABLE `t_order_info` (
  `id` char(32) NOT NULL,
  `device_id` char(50) NOT NULL COMMENT '设备id',
  `meal_id` char(32) NOT NULL COMMENT '套餐id',
  `pay_price` decimal(10,0) NOT NULL COMMENT '支付价格',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '订单状态  1、已创建、待支付；2、已创建、已支付；3、已创建、已取消',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `foreign_device_order` (`device_id`),
  KEY `foreign_meal_order` (`meal_id`),
  CONSTRAINT `foreign_device_order` FOREIGN KEY (`device_id`) REFERENCES `t_device_info` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `foreign_meal_order` FOREIGN KEY (`meal_id`) REFERENCES `t_setmeal_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_remotecmd_info
-- ----------------------------
DROP TABLE IF EXISTS `t_remotecmd_info`;
CREATE TABLE `t_remotecmd_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_band` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '手机品牌',
  `sys_version` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '操作系统版本',
  `ui_version` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'UI版本',
  `remote_cmds` varchar(1000) CHARACTER SET utf8mb4 NOT NULL COMMENT '远程指令需要按执行顺序存储，结构为json',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for t_setmeal_info
-- ----------------------------
DROP TABLE IF EXISTS `t_setmeal_info`;
CREATE TABLE `t_setmeal_info` (
  `id` char(32) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '套餐名称',
  `flux` decimal(10,0) NOT NULL COMMENT '套餐容量；Kb',
  `price` decimal(10,0) NOT NULL COMMENT '套餐价格、分',
  `indate` bigint(20) NOT NULL COMMENT '有效期，天',
  `description` varchar(500) DEFAULT NULL COMMENT '套餐描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_share_limit
-- ----------------------------
DROP TABLE IF EXISTS `t_share_limit`;
CREATE TABLE `t_share_limit` (
  `device_id` char(32) NOT NULL COMMENT '设备唯一标识',
  `limits` double DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `update_time` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`device_id`),
  CONSTRAINT `t_device_limit` FOREIGN KEY (`device_id`) REFERENCES `t_device_info` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_wifi_event
-- ----------------------------
DROP TABLE IF EXISTS `t_wifi_event`;
CREATE TABLE `t_wifi_event` (
  `id` char(32) NOT NULL,
  `device_id` varchar(100) NOT NULL COMMENT '设备唯一标识',
  `bssid` varchar(50) DEFAULT NULL COMMENT '热点唯一标识',
  `ssid` varchar(50) NOT NULL COMMENT '热点名称',
  `event` varchar(10) NOT NULL COMMENT '事件类型  con:连接  discon:断开',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '事件发生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='终端设备连接与断开热点的事件记录';

-- ----------------------------
-- Table structure for t_wifi_info
-- ----------------------------
DROP TABLE IF EXISTS `t_wifi_info`;
CREATE TABLE `t_wifi_info` (
  `device_id` varchar(100) NOT NULL COMMENT '设备行记录id',
  `bssid` varchar(50) NOT NULL COMMENT '热点唯一标识',
  `ssid` varchar(50) NOT NULL COMMENT '热点名称',
  `encry_type` varchar(20) DEFAULT NULL COMMENT '热点加密方式',
  `pwd` varchar(100) DEFAULT NULL COMMENT '热点密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`device_id`),
  KEY `device_rowId` (`device_id`),
  CONSTRAINT `foreign_device_wifi` FOREIGN KEY (`device_id`) REFERENCES `t_device_info` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='热点信息';
