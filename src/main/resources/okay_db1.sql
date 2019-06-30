/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : okay_db

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-06-30 15:45:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for entity_env
-- ----------------------------
DROP TABLE IF EXISTS `entity_env`;
CREATE TABLE `entity_env` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `zk` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_env
-- ----------------------------
INSERT INTO `entity_env` VALUES ('1', 'dev', '10.10.6.3:2181');
INSERT INTO `entity_env` VALUES ('2', 'docker-dev', '172.18.4..48:2181');
INSERT INTO `entity_env` VALUES ('3', 'docker-hotfix', '10.10.1.7:2181');
INSERT INTO `entity_env` VALUES ('4', 'stress', null);

-- ----------------------------
-- Table structure for entity_history
-- ----------------------------
DROP TABLE IF EXISTS `entity_history`;
CREATE TABLE `entity_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NOT NULL,
  `md5` varchar(64) COLLATE utf8_bin NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL,
  `script_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `entity_history_script_id_77a9580d_fk_entity_script_id` (`script_id`),
  KEY `entity_history_user_id_ca5b1d79_fk_entity_user_id` (`user_id`),
  CONSTRAINT `entity_history_script_id_77a9580d_fk_entity_script_id` FOREIGN KEY (`script_id`) REFERENCES `entity_script` (`id`),
  CONSTRAINT `entity_history_user_id_ca5b1d79_fk_entity_user_id` FOREIGN KEY (`user_id`) REFERENCES `entity_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of entity_history
-- ----------------------------

-- ----------------------------
-- Table structure for entity_project
-- ----------------------------
DROP TABLE IF EXISTS `entity_project`;
CREATE TABLE `entity_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `env` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `descption` varchar(200) COLLATE utf8_bin NOT NULL,
  `ctime` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `entity_project_user_id_db91b8ba_fk_entity_user_id` (`user_id`),
  KEY `env` (`env`),
  KEY `type` (`type`),
  CONSTRAINT `entity_project_ibfk_1` FOREIGN KEY (`env`) REFERENCES `entity_env` (`id`),
  CONSTRAINT `entity_project_ibfk_2` FOREIGN KEY (`type`) REFERENCES `entity_type` (`id`),
  CONSTRAINT `entity_project_user_id_db91b8ba_fk_entity_user_id` FOREIGN KEY (`user_id`) REFERENCES `entity_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of entity_project
-- ----------------------------
INSERT INTO `entity_project` VALUES ('9', '商城测试项目', '3', '4', '商城h5', '2019-06-30 06:44:39.664000', '2019-06-30 06:44:39.664000', '1');

-- ----------------------------
-- Table structure for entity_protocol
-- ----------------------------
DROP TABLE IF EXISTS `entity_protocol`;
CREATE TABLE `entity_protocol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_protocol
-- ----------------------------
INSERT INTO `entity_protocol` VALUES ('1', 'HTTP');
INSERT INTO `entity_protocol` VALUES ('2', 'DUBBO');
INSERT INTO `entity_protocol` VALUES ('3', 'WEBSOCKET');

-- ----------------------------
-- Table structure for entity_request_type
-- ----------------------------
DROP TABLE IF EXISTS `entity_request_type`;
CREATE TABLE `entity_request_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_request_type
-- ----------------------------
INSERT INTO `entity_request_type` VALUES ('1', 'GET');
INSERT INTO `entity_request_type` VALUES ('2', 'POST');
INSERT INTO `entity_request_type` VALUES ('3', 'DELETE');
INSERT INTO `entity_request_type` VALUES ('4', 'PUT');

-- ----------------------------
-- Table structure for entity_role
-- ----------------------------
DROP TABLE IF EXISTS `entity_role`;
CREATE TABLE `entity_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_role
-- ----------------------------
INSERT INTO `entity_role` VALUES ('1', '普通用户');
INSERT INTO `entity_role` VALUES ('2', 'VIP');
INSERT INTO `entity_role` VALUES ('3', '管理员');

-- ----------------------------
-- Table structure for entity_script
-- ----------------------------
DROP TABLE IF EXISTS `entity_script`;
CREATE TABLE `entity_script` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  `pre_number` int(11) NOT NULL,
  `pre_time` int(11) NOT NULL,
  `url` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `time_out` int(11) NOT NULL,
  `request_type_id` int(11) NOT NULL,
  `protocol_id` int(11) NOT NULL,
  `ins` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `assert_text` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `method` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `params` varchar(600) COLLATE utf8_bin DEFAULT NULL,
  `param_type` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `cookie` varchar(800) COLLATE utf8_bin DEFAULT NULL,
  `header` varchar(800) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `version` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ip` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `entity_script_project_id_d2d23c58_fk_entity_project_id` (`project_id`),
  KEY `entity_script_user_id_29555dde_fk_entity_user_id` (`user_id`),
  KEY `entity_script_ibfk_1` (`protocol_id`),
  KEY `request_type_id` (`request_type_id`),
  CONSTRAINT `entity_script_ibfk_1` FOREIGN KEY (`protocol_id`) REFERENCES `entity_protocol` (`id`),
  CONSTRAINT `entity_script_ibfk_2` FOREIGN KEY (`request_type_id`) REFERENCES `entity_request_type` (`id`),
  CONSTRAINT `entity_script_project_id_d2d23c58_fk_entity_project_id` FOREIGN KEY (`project_id`) REFERENCES `entity_project` (`id`),
  CONSTRAINT `entity_script_user_id_29555dde_fk_entity_user_id` FOREIGN KEY (`user_id`) REFERENCES `entity_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of entity_script
-- ----------------------------
INSERT INTO `entity_script` VALUES ('23', '首页课件列表测试111', '200', '100', 'https://jiaoshi.okjiaoyu.cn/myindex/index_get_by_indextag?_=1561858643766&id=87448&is_waste=0&show_question=', '1000', '2', '1', null, '\"code\":0', null, '[]', null, '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"d1568c1998974c0e891d91b1832a9853\"},{\"cookieKey\":\"org_id\",\"cookieValue\":\"80\"}]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"1231324_qa\"}]', '2019-06-30 10:00:03.000000', '2019-06-30 10:00:06.000000', null, '9', '1', '', null);
INSERT INTO `entity_script` VALUES ('24', '新的开启', '200', '100', 'https://jiaoshi.okjiaoyu.cn/myindex/index_get_by_indextag?_=1561858643766&id=87448&is_waste=0&show_question=', '2000', '1', '1', '', '\"code\":0', '', '', '', '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"d1568c1998974c0e891d91b1832a9853\"}]', '[{\"headerKey\":\"reuqestid\",\"headerValue\":\"qa_ooo22202\"}]', '2019-06-30 10:04:29.145000', '2019-06-30 10:04:29.145000', '', '9', '1', '', null);
INSERT INTO `entity_script` VALUES ('25', '获取数据库信息', '100', '200', 'http://jiaoyu.qa-dev.xk12.cn/ksu/list/', '4000', '3', '1', '', '300', '', '[{\"paramskey\":\"content\",\"paramsvalue\":\"1231\"},{\"paramskey\":\"id\",\"paramsvalue\":\"2\"}]', '', '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"2310981390831\"}]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"qa_000101\"}]', '2019-06-30 13:41:21.329000', '2019-06-30 13:41:21.329000', '', '9', '1', '', null);

-- ----------------------------
-- Table structure for entity_token
-- ----------------------------
DROP TABLE IF EXISTS `entity_token`;
CREATE TABLE `entity_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(200) COLLATE utf8_bin NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `entity_token_user_id_e53f18b6_fk_entity_user_id` FOREIGN KEY (`user_id`) REFERENCES `entity_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of entity_token
-- ----------------------------
INSERT INTO `entity_token` VALUES ('3', '7570d31da3fe1cfea40f384749f55d9c', '2019-06-30 15:19:55.143000', '1');
INSERT INTO `entity_token` VALUES ('4', '526c7711442799372ff0b6c259f11d35', '2019-06-30 14:01:59.205000', '8');

-- ----------------------------
-- Table structure for entity_type
-- ----------------------------
DROP TABLE IF EXISTS `entity_type`;
CREATE TABLE `entity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_type
-- ----------------------------
INSERT INTO `entity_type` VALUES ('1', '教师空间');
INSERT INTO `entity_type` VALUES ('2', '教师pad');
INSERT INTO `entity_type` VALUES ('3', '学生pad');
INSERT INTO `entity_type` VALUES ('4', '商城');

-- ----------------------------
-- Table structure for entity_user
-- ----------------------------
DROP TABLE IF EXISTS `entity_user`;
CREATE TABLE `entity_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(100) COLLATE utf8_bin NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `email` (`email`),
  KEY `user_type` (`role_id`),
  CONSTRAINT `entity_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `entity_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of entity_user
-- ----------------------------
INSERT INTO `entity_user` VALUES ('1', '张亚丹', 'zhangyadan@okay.cn', '123456', '2', '2019-06-29 13:28:31.743000');
INSERT INTO `entity_user` VALUES ('8', '刘占会', 'liuzhanhui@okay.cn', '123456', '1', '2019-06-30 06:48:30.000000');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('DemoScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) NOT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) NOT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) NOT NULL COMMENT 'cron表达式',
  `status` int(11) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(50) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `script_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `script_id` (`script_id`),
  CONSTRAINT `schedule_job_ibfk_1` FOREIGN KEY (`script_id`) REFERENCES `entity_script` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务表';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` int(11) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) NOT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) NOT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` int(11) NOT NULL COMMENT '任务状态  0：成功  1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `crt_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志表';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
