/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : okay_db

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 07/18/2019 00:56:48 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `entity_article`
-- ----------------------------
DROP TABLE IF EXISTS `entity_article`;
CREATE TABLE `entity_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` text NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `entity_article_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `entity_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_article_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `entity_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_article`
-- ----------------------------
BEGIN;
INSERT INTO `entity_article` VALUES ('1', '测试文章读写功能', '**测试title 1**\n不论在生活中还是网络里，人人都会有朋友。如果没有朋友情，生活就不会有悦耳的和音，就如死水一滩；友情无处不在，它伴随你左右，萦绕在你身边，和你共渡一生。\n\n　　友情，是雨季中的一把小伞，它撑起了一个晴朗的天空；友情，是风雪之夜的一杯淡茶，它能将寒意驱走，带来温暖；友情，是迷途中的一盏灯，它在你迷失时给你方向……人生漫漫，若能拥有一段地久天长的相知相伴的友情，生命亦无憾。\n**测试title 2**\n　　 大千世界，红尘滚滚，一年又一年的风风雨雨，几许微笑，几丝忧伤，随着时间小河的流淌，许多人和事都付之东流去。但有一种人却随着时间的推移，你与ta的交往，如陈年酒香，沁人心肺。你与ta的友情是世上最珍贵的情感。这种友情是一种最纯洁、最高尚、最朴素、最平凡的感情。也是最浪漫、最动人、最坚实、最永恒的情感。\n\n　　^小时候，^友情是简单的一个玩伴，是一份哭哭笑笑的天真无邪。儿时的友情是真切的我和你，可以肆无忌惮的吵架，互相赌气撅起的嘴，片刻之后又携手言欢。那时的友情是那一块小小的糖，甜甜的味道，永远留在了儿时的记忆里，无论何时回味起，都是美滋滋的。\n\n　　长大后，友情是一樽透明的酒杯，举杯邀明月时，对影成三人。失败或落寞时的酒话连篇，ta不会觉得你烦。友情，是伤心不必躲在一个角落悄悄地哭泣，ta默默地陪你，告诉你在哪里跌倒的就在哪里爬起来。于是，成长里有我们友情地久天长的足迹。\n[测试url](http://www.duwenzhang.com/wenzhang/youqingwenzhang/20190511/402752.html)\n　　烦恼时，友情如醇绵的酒；痛苦时，友情如清香的茶；快乐时，友情如轻快的歌；孤寂时，友情如对饮的月……\n\n　　友情是一汪温泉，是共同烦恼和喜悦的点点滴滴，最后汇聚成一条友情的河，在生命里潺潺不息。\n\n　　友情是一缕轻柔的风，是懊恼时送来的缕缕畅意，是烦闷时真诚互吐的心曲，是节日时互赠的声声祝语……那时的友情，是一卷明朗的画，无论何时想起都清爽亮丽；是生命里郁郁葱葱的树木，一年四季常青。\n![4.png](http://127.0.0.1:7003/9c464dd5-5f77-46b7-a1a6-f70e68056e05.png)\n　　 一个人的天空是狭小的、单调的。友情织成的天空，是广阔的，也是灿烂的。友情能给你的生活增添情趣，让你更多的洞悉外面的世界。\n\n　　友情是一股互助的动力，是互相欣赏的知己。是成功时的互相致意，是失败时永恒的鼓舞，是一曲豪迈的歌，何时唱起都激昂如昔。\n\n　　友情，又像是一杯浓浓的咖啡，是成熟后的淡淡的香醇与苦涩，是愚人节一个善意的玩笑，是生日时的那一句：生日快乐！\n\n　　你是天上的云，我就是吹动你的风，我们彼此依赖，互相信任，互相关心。\n\n　　友情，有时是一种无声的陪伴，是桌子前的两两对坐，无言亦是懂得。友情是一处温暖的海港，静静地接待疲惫的你靠岸。\n![6.png](http://127.0.0.1:7003/f931871b-4e6b-4dd2-a3f2-0aac10017958.png)\n　　 在顺境中，朋友结识了我们；在逆境中，我们了解了朋友，结下了友谊之情。友情犹如夏日的雨露，冬日的太阳，心田靠它滋润，冰雪靠它融化，万物赖以生长。无论身处何地，忘不了的是友情，让我们共同度着浓情岁月。友情，让我们共同携手一生，地久天长。', '1', '2019-07-17 19:57:18', '2019-07-17 19:57:18', '1');
COMMIT;

-- ----------------------------
--  Table structure for `entity_category`
-- ----------------------------
DROP TABLE IF EXISTS `entity_category`;
CREATE TABLE `entity_category` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_category`
-- ----------------------------
BEGIN;
INSERT INTO `entity_category` VALUES ('1', '性能'), ('2', '自动化'), ('3', '工具');
COMMIT;

-- ----------------------------
--  Table structure for `entity_env`
-- ----------------------------
DROP TABLE IF EXISTS `entity_env`;
CREATE TABLE `entity_env` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `zk` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_env`
-- ----------------------------
BEGIN;
INSERT INTO `entity_env` VALUES ('1', 'dev', '10.10.6.3:2181'), ('2', 'docker-dev', '172.18.4..48:2181'), ('3', 'docker-hotfix', '10.10.1.7:2181'), ('4', 'stress', null);
COMMIT;

-- ----------------------------
--  Table structure for `entity_history`
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
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `entity_history`
-- ----------------------------
BEGIN;
INSERT INTO `entity_history` VALUES ('87', '2019-07-04 09:49:05.675000', '9425a240499adfd32cf722cd604312ae', 'success', '30', '1'), ('88', '2019-07-04 09:52:02.994000', 'ca1a4050ed5f715f21f717e7ff39bc5c', 'success', '30', '1'), ('89', '2019-07-04 09:57:31.623000', 'e3eb91788af2a7874041fd026d973672', 'success', '30', '1'), ('90', '2019-07-04 10:07:38.043000', 'f2f3bcaab0bab6013e052a899ad1819e', 'success', '30', '1'), ('91', '2019-07-04 10:22:44.514000', '7f585fa7bce9402cbb531fadb13bf910', 'success', '31', '1'), ('92', '2019-07-04 12:36:56.580000', '93da2160251cf60abc5fb24d9b4d233e', 'success', '31', '1'), ('93', '2019-07-04 12:40:43.987000', '98fd6013da2e571aeaaf8d1735d0d5c6', 'success', '31', '1'), ('94', '2019-07-04 13:12:46.362000', 'adc55973b87f237d09a5db2792339d48', 'success', '31', '1'), ('95', '2019-07-04 20:07:50.586000', '46fa7b38feed7215f039e192e1d6de36', 'success', '30', '1'), ('96', '2019-07-04 20:08:00.037000', '84a77d919fe555a35c168a8b7c9bc1cf', 'success', '30', '1'), ('97', '2019-07-04 20:08:50.022000', '40b98b06deff3af8f97cee129d2bd589', 'success', '30', '1'), ('98', '2019-07-04 20:09:00.051000', '5ad0568186045f62f37f24bfb6789790', 'success', '30', '1'), ('99', '2019-07-04 20:13:28.633000', '0ce1519c1e1cea8ece89b7dcf296247d', 'success', '30', '1'), ('100', '2019-07-04 20:14:50.516000', '242135a2c00f6132f1756dbea9a93535', 'success', '30', '1'), ('101', '2019-07-04 20:15:00.038000', '86dffefa5e907429182d056ec4d27205', 'success', '30', '1'), ('102', '2019-07-04 20:15:50.032000', '4631fb4929c01ce09155ffd0112bd1a6', 'success', '30', '1'), ('103', '2019-07-04 20:16:00.036000', '2a4d1e10bce211dbdf28d2864d1616e3', 'success', '30', '1'), ('104', '2019-07-04 20:16:50.547000', 'e5927ed9905ff91a6d130bc6590fbe38', 'success', '30', '1'), ('105', '2019-07-04 20:17:00.042000', '0302e4f8a41663ff49890d7473083cc5', 'success', '30', '1'), ('106', '2019-07-04 20:17:50.054000', 'fd4ee42bbac58c15841d46e13bbf4b16', 'success', '30', '1'), ('107', '2019-07-04 20:17:50.072000', 'c82b2eafd231b329d26f209065047be7', 'success', '30', '1'), ('108', '2019-07-04 20:18:00.060000', 'd3819e878b92be7a1d9315222efef3a4', 'success', '30', '1'), ('109', '2019-07-04 20:18:50.032000', '14951d7b60b69eb14f7b24b5934b2fa8', 'success', '30', '1'), ('110', '2019-07-04 20:19:00.035000', 'd6d23d91942a2ece6b1158a712dcf520', 'success', '30', '1'), ('111', '2019-07-04 20:19:50.028000', '0314ec8a15f4c29aef5e51be3c8b336b', 'success', '30', '1'), ('112', '2019-07-04 20:20:00.160000', '690499d349e8c959721cdafed3c14e85', 'success', '30', '1'), ('113', '2019-07-05 10:33:51.833000', '2f5d418d5b4f9301130b488a01a9b7ac', 'success', '30', '1'), ('114', '2019-07-05 10:34:00.059000', '504d4eeabc02695ab0eafdf4b8e37306', 'success', '30', '1'), ('115', '2019-07-05 10:34:50.038000', '4e7ee61cdad84a64f7b370064398b667', 'success', '30', '1'), ('116', '2019-07-05 10:35:00.097000', 'bb0a592731127206c037422098d0fb2f', 'success', '30', '1'), ('117', '2019-07-05 10:35:50.040000', '4db058ce4bf49c2f6b294ad9f8415355', 'success', '30', '1'), ('118', '2019-07-05 10:36:00.036000', '521dc7b778ca7ae827097406c813a87f', 'success', '30', '1'), ('119', '2019-07-05 10:36:50.036000', '5dbdac68c38e8b2918b4997aa13f6f1a', 'success', '30', '1'), ('120', '2019-07-05 10:37:00.035000', '7e111bed42bf63c5e729ae4c0bc508ab', 'success', '30', '1'), ('121', '2019-07-05 10:37:50.031000', '12b1c284436a4bacd5e9e808b11f1c61', 'success', '30', '1'), ('122', '2019-07-05 10:38:00.032000', 'd9bb17ba7643815c69143298a856167d', 'success', '30', '1'), ('123', '2019-07-05 10:38:50.062000', '7a919b6f22352d1d7b45a7cdf000c160', 'success', '30', '1'), ('124', '2019-07-05 10:39:00.038000', '54d5743c32a213f46ff6db0f4041c09e', 'success', '30', '1'), ('125', '2019-07-05 10:39:50.040000', 'cf3dd2e01679685f0e46bff816dbd706', 'success', '30', '1'), ('126', '2019-07-05 10:40:00.032000', '6343b65e2f1c9cef41c6bb2b33b208a3', 'success', '30', '1'), ('127', '2019-07-05 10:40:50.039000', '268a6600f368e08378e49f4b1735bca3', 'success', '30', '1'), ('128', '2019-07-05 10:41:00.040000', 'ef6fdaedac27ab5df39a4a344770806e', 'success', '30', '1'), ('129', '2019-07-05 10:41:50.038000', '813ca9434517c8541f13f625b8e422de', 'success', '30', '1'), ('130', '2019-07-05 10:42:00.035000', 'ee42c3c722ad2ae7b6cbc16b5225ddb8', 'success', '30', '1'), ('131', '2019-07-05 10:42:50.038000', '4e8367a3ffaefeeb461499f755e50698', 'success', '30', '1'), ('132', '2019-07-05 10:43:00.034000', '7fd00ed1585ae15e5b6920c184f901d3', 'success', '30', '1'), ('133', '2019-07-05 10:43:50.035000', 'bf094ba6621b55ac810b881db6b64222', 'success', '30', '1'), ('134', '2019-07-05 10:44:00.037000', '88bc93b5945721e63925c0e40b994194', 'success', '30', '1'), ('135', '2019-07-05 10:44:50.034000', '9360f145e4865086825c8d9bda1c4410', 'success', '30', '1'), ('136', '2019-07-05 10:45:00.032000', 'd0526df8b5ee3167437a7c91a649c31e', 'success', '30', '1'), ('137', '2019-07-05 10:47:05.485000', '5cd40ea251b36bbb1f69ec25a6c553b7', 'success', '30', '1'), ('138', '2019-07-05 10:47:50.048000', '773a490bc79bcdb554e4ef995b249903', 'success', '30', '1'), ('139', '2019-07-05 10:48:00.033000', '2ea2cc6a4e571de3ec615bf87baa1697', 'success', '30', '1'), ('140', '2019-07-05 10:48:50.047000', 'd24a86c3175f539d53a442d4cb33cf57', 'success', '30', '1'), ('141', '2019-07-05 10:49:00.030000', '6f1a99d3433c1a8c7bbd4ce6ef6abf52', 'success', '30', '1'), ('142', '2019-07-05 10:49:50.035000', '77a22549e83efaab2a0bffda15d58a58', 'success', '30', '1'), ('143', '2019-07-05 10:50:00.030000', '062b817f986a56e0c2020389bc1b2feb', 'success', '30', '1'), ('144', '2019-07-05 10:50:50.044000', 'fddfe8cd6da7d54b75d1ec98d44ab791', 'success', '30', '1'), ('145', '2019-07-05 10:51:00.032000', 'd1f27a2bbee706a27a6fa97fe911af91', 'success', '30', '1'), ('146', '2019-07-05 10:51:50.036000', '8b6060153ba0f0835df8e396d0afa2a7', 'success', '30', '1'), ('147', '2019-07-05 10:52:00.028000', 'fe05c57c3401ebc102428133563bb935', 'success', '30', '1'), ('148', '2019-07-05 10:52:50.049000', 'eb7cc4d563a3616e231e18ca7e5900fe', 'success', '30', '1'), ('149', '2019-07-05 10:53:00.039000', '41bb01140d8edf43b7ab46aa493e86ee', 'success', '30', '1'), ('150', '2019-07-05 10:53:50.093000', '48839ad8c4d5537e5eaa18f158fb5015', 'success', '30', '1'), ('151', '2019-07-05 10:54:00.031000', 'f9fe364b9841c740f7993e68f870824a', 'success', '30', '1'), ('152', '2019-07-05 10:54:50.041000', '6da01370a5f2dcff0844e76292a2a34f', 'success', '30', '1'), ('153', '2019-07-05 10:55:00.032000', 'c7523328c274231030d59dff5a837fc1', 'success', '30', '1'), ('154', '2019-07-05 10:55:50.066000', '3a55e00c2f4fe568c1c0f5f0c342e586', 'success', '30', '1'), ('155', '2019-07-05 10:56:00.030000', '72d56be5b915979b954d2b7534edebfb', 'success', '30', '1'), ('156', '2019-07-05 10:56:50.034000', '268c86b90c0d9598b94d5acc1e2c3887', 'success', '30', '1'), ('157', '2019-07-05 10:57:00.030000', '1718137ba3e51b2b47ed6b70ba6eb2db', 'success', '30', '1'), ('158', '2019-07-05 10:57:50.045000', '641da1d2272c9593fa5efca9bb1ebd7c', 'success', '30', '1'), ('159', '2019-07-05 10:58:00.038000', 'ef1056332968bd7b2cbe78a67e6ac616', 'success', '30', '1'), ('160', '2019-07-05 10:58:50.032000', '5a061df261547fc54b5ef3f136da5751', 'success', '30', '1'), ('161', '2019-07-05 10:59:00.034000', 'a1ffc08ba5c4d20e1d456daa857d40cd', 'success', '30', '1'), ('162', '2019-07-05 10:59:50.042000', 'f47360f5f523963af9fd64516139d33a', 'success', '30', '1'), ('163', '2019-07-05 11:00:00.065000', '1ccbb6de6c74237ae33b744d50790a9a', 'success', '30', '1'), ('164', '2019-07-05 11:00:50.059000', 'd226a02c2e4e3c3d430188ed34a36d04', 'success', '30', '1'), ('165', '2019-07-05 11:01:00.031000', 'fa2a2c5a9e96628cf418fa38582d45b3', 'success', '30', '1'), ('166', '2019-07-05 11:01:50.104000', 'eebdc178e8ad14951513595ca28f8bfb', 'success', '30', '1'), ('167', '2019-07-05 11:02:00.202000', '4045c22a5588db2583955909391b70f0', 'success', '30', '1'), ('168', '2019-07-05 11:02:00.242000', '548e44ee7818bdef2e24ed2bd0de47be', 'success', '30', '1'), ('169', '2019-07-05 11:02:50.137000', '3ea6b6f120e0ab1a6ab33b5a8be94640', 'success', '30', '1'), ('170', '2019-07-05 11:03:00.047000', '1993bb6d5f527272d00c7d2efd456dea', 'success', '30', '1'), ('171', '2019-07-05 11:14:51.733000', 'e97f07c4b875596a728130f9c0a21174', 'success', '30', '1'), ('172', '2019-07-05 11:15:00.030000', '58442e0953d4cb5c5d796ade74c1f804', 'success', '30', '1'), ('173', '2019-07-05 11:15:50.052000', '953e3669a2a9c43ca8a83762df467f43', 'success', '30', '1'), ('174', '2019-07-05 11:16:00.034000', '6e9d4b61820739eb6e6b7526bd293592', 'success', '30', '1'), ('175', '2019-07-05 11:16:50.042000', '0f59597c4e2ffa0e563c83a6762fc3f2', 'success', '30', '1'), ('176', '2019-07-05 11:17:00.036000', '7e4e0edd4bf40b296af238a5e3b70774', 'success', '30', '1'), ('177', '2019-07-05 11:17:50.041000', 'a9b4aba39200c9b2d76b0485606411d5', 'success', '30', '1'), ('178', '2019-07-05 11:18:00.030000', 'be3430204b87e91c2356381910aca795', 'success', '30', '1'), ('179', '2019-07-05 11:18:50.034000', '621942e0d63b6f13ec9c8f01e059e855', 'success', '30', '1'), ('180', '2019-07-05 11:19:00.035000', '14f044a2517dabee4103df50e9036fa2', 'success', '30', '1'), ('181', '2019-07-05 11:19:50.045000', '322e42668a53745901aeaded49627b7c', 'success', '30', '1'), ('182', '2019-07-05 11:20:00.033000', '7e44856c4ee1b1f0a603e9f8a9d02b93', 'success', '30', '1'), ('183', '2019-07-05 11:20:50.028000', '560321e8ac7fadf61d7783a64a54ba34', 'success', '30', '1'), ('184', '2019-07-05 11:21:00.029000', '044ee13d25eb04d2ba9355d12970f8fa', 'success', '30', '1'), ('185', '2019-07-05 11:40:00.975000', '98e406e94da336a87f0fae95eba71c68', 'success', '30', '1'), ('186', '2019-07-05 12:10:00.063000', '1d80eba7151587b8025e093bd19b7e6b', 'success', '30', '1'), ('187', '2019-07-05 12:20:00.375000', 'b89ca2af168dc76f02fe4a28e9576356', 'success', '30', '1'), ('188', '2019-07-05 12:30:00.509000', '490e7fe35e4c7498d6c94254c106c80e', 'success', '30', '1'), ('189', '2019-07-05 12:40:00.069000', '6354fa3ace5e8802d2ae898d3a77b94c', 'success', '30', '1'), ('190', '2019-07-05 12:50:00.048000', '8aa65cad14816a60c5ba09aa2db1491b', 'success', '30', '1'), ('191', '2019-07-05 13:00:00.046000', '2a4525a5b3124c1c082fc4fd299c3033', 'success', '30', '1'), ('192', '2019-07-05 13:10:00.377000', 'b95e79d8e9264c68451323c91e8efeec', 'success', '30', '1'), ('193', '2019-07-05 13:20:00.687000', '833e43d045a08b1539ec4f334abc877d', 'success', '30', '1'), ('194', '2019-07-05 13:30:00.050000', '50f1902b76c41866cf17b3c6b8892a8f', 'success', '30', '1'), ('195', '2019-07-05 13:40:00.055000', '22ecd3b673492303fa4713aaec66cec9', 'success', '30', '1'), ('196', '2019-07-05 14:00:00.465000', '4bb281f185c7d278254683c19fe7f076', 'success', '30', '1'), ('197', '2019-07-05 14:10:00.636000', '2ec118a559ddc383089ef0b1950a8139', 'success', '30', '1'), ('198', '2019-07-05 14:20:00.546000', '2e3f0160c89dbc96be4c4ec113f6b2b7', 'success', '30', '1'), ('199', '2019-07-05 14:30:00.054000', '5fe966f32bda8b1a6d5c2807d095bf33', 'success', '30', '1'), ('200', '2019-07-05 14:40:00.052000', '7bd7548b0de73121f5174c8b88131bc1', 'success', '30', '1'), ('201', '2019-07-05 14:50:00.491000', 'f192c9153842d9539df842d103aec804', 'success', '30', '1'), ('202', '2019-07-05 15:00:00.065000', 'b7665f65fecb8cd169bdb7b101732049', 'success', '30', '1'), ('203', '2019-07-05 15:10:00.066000', '8650a32a251fd208b49905c608e2611d', 'success', '30', '1'), ('204', '2019-07-05 15:20:00.051000', '8918c57f5cdb168c9aff591a8fe0ec68', 'success', '30', '1'), ('205', '2019-07-05 15:30:00.054000', '704c9bb82eac04b3166c0d7bd089f764', 'success', '30', '1'), ('206', '2019-07-05 15:40:00.224000', '3b33a637113b61ad9489ca3dbf66daab', 'success', '30', '1'), ('207', '2019-07-05 16:43:42.163000', 'f8f59445fc21dfde288473448defab74', 'success', '34', '1'), ('208', '2019-07-05 16:47:28.813000', '602d1038b7a419e44725f8c25815002a', 'success', '34', '1'), ('209', '2019-07-05 17:00:48.174000', '6784a699767a91dfa20e7d4b71f8a3ac', 'success', '34', '1'), ('210', '2019-07-05 17:03:11.609000', '2d4924321e4d4c9ea6792af8c607b2e0', 'success', '34', '1'), ('211', '2019-07-05 17:10:35.194000', 'd1a4f79162d13ccedd7f6b1e687e29f1', 'success', '34', '1'), ('212', '2019-07-10 17:29:46.002000', '7ab72ea946ac3f75f49cedcd7442b0fa', 'success', '33', '1'), ('213', '2019-07-10 17:35:18.366000', '76e28c06b80ca361fad42e95f9caa0f0', 'success', '33', '1'), ('214', '2019-07-10 18:05:31.477000', '3c3308c0c3ca388077d12521faf8bfea', 'success', '33', '1'), ('215', '2019-07-10 18:28:17.023000', 'c49885bcf7f5ed290850e8c47b4affba', 'success', '33', '1'), ('216', '2019-07-11 11:37:18.458000', '0ded63e82df9e08ecc0879e0050b93bd', 'success', '34', '1'), ('217', '2019-07-11 15:49:48.259000', 'f4bcc3405cfddfcf1ebd893799742a4d', 'success', '33', '1'), ('218', '2019-07-12 12:20:22.597000', 'a8af07fb744831656860519ee60378a4', 'success', '34', '1'), ('219', '2019-07-12 12:25:37.143000', '1ec71621f928a26aa663fd5466a0f0f8', 'success', '34', '1'), ('220', '2019-07-16 16:37:04.603000', 'fd01056cef203d00a0ce78f25d3c6946', 'success', '35', '1'), ('221', '2019-07-16 17:36:27.774000', '536006a5e220503ca87e5fe261d1c354', 'fail', '35', '1'), ('222', '2019-07-16 17:36:48.791000', '5ce7221f5df564894e5ee316b0206a83', 'fail', '35', '1'), ('223', '2019-07-16 17:38:11.601000', '2a6f7272ca8f6cc906f11a4b7909b7fa', 'fail', '35', '1'), ('224', '2019-07-16 17:43:48.528000', 'd83682843ab8ccd4bea3f2dd544f8bfc', 'success', '33', '1'), ('225', '2019-07-16 17:55:49.488000', '1ab0d7813148008378acdb5e57464d09', 'fail', '35', '1'), ('226', '2019-07-16 17:57:32.960000', 'e78e52be727d382ad4fcc719797b1a45', 'fail', '35', '1'), ('227', '2019-07-16 17:59:40.121000', '6760d8253f837deb50969092bb09f877', 'success', '35', '1'), ('228', '2019-07-16 18:15:14.062000', '8f333c136c81e6005aa177e059e51d7f', 'success', '31', '1'), ('229', '2019-07-16 19:13:23.859000', '1d373d1fed5993c93f601e6b34f26358', 'success', '35', '1'), ('230', '2019-07-16 19:26:15.983000', '88b54da9326f63409ed54c3365b522db', 'success', '36', '1');
COMMIT;

-- ----------------------------
--  Table structure for `entity_project`
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `entity_project`
-- ----------------------------
BEGIN;
INSERT INTO `entity_project` VALUES ('9', '商城测试项目', '1', '4', '商城h5', '2019-06-30 06:44:39.664000', '2019-07-01 15:20:15.118000', '1'), ('10', '教师性能本地户', '1', '2', '测试性能', '2019-07-05 15:56:41.102000', '2019-07-16 19:21:57.359000', '1'), ('11', '教师机日志打印', '1', '2', '测试环境教师pad', '2019-07-16 19:22:29.044000', '2019-07-16 19:22:29.044000', '1');
COMMIT;

-- ----------------------------
--  Table structure for `entity_protocol`
-- ----------------------------
DROP TABLE IF EXISTS `entity_protocol`;
CREATE TABLE `entity_protocol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_protocol`
-- ----------------------------
BEGIN;
INSERT INTO `entity_protocol` VALUES ('1', 'HTTP'), ('2', 'DUBBO'), ('3', 'WEBSOCKET');
COMMIT;

-- ----------------------------
--  Table structure for `entity_request_type`
-- ----------------------------
DROP TABLE IF EXISTS `entity_request_type`;
CREATE TABLE `entity_request_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_request_type`
-- ----------------------------
BEGIN;
INSERT INTO `entity_request_type` VALUES ('1', 'GET'), ('2', 'POST'), ('3', 'DELETE'), ('4', 'PUT');
COMMIT;

-- ----------------------------
--  Table structure for `entity_role`
-- ----------------------------
DROP TABLE IF EXISTS `entity_role`;
CREATE TABLE `entity_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_role`
-- ----------------------------
BEGIN;
INSERT INTO `entity_role` VALUES ('1', '普通用户'), ('2', 'VIP'), ('3', '管理员');
COMMIT;

-- ----------------------------
--  Table structure for `entity_script`
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
  `params` varchar(4096) COLLATE utf8_bin DEFAULT NULL,
  `param_type` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `cookie` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `entity_script`
-- ----------------------------
BEGIN;
INSERT INTO `entity_script` VALUES ('23', '首页课件列表测试111', '200', '100', 'https://jiaoshi.okjiaoyu.cn/myindex/index_get_by_indextag?_=1561858643766&id=87448&is_waste=0&show_question=', '1000', '2', '1', null, '\"code\":0', null, '[]', null, '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"d1568c1998974c0e891d91b1832a9853\"},{\"cookieKey\":\"org_id\",\"cookieValue\":\"80\"}]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"1231324_qa\"}]', '2019-06-30 10:00:03.000000', '2019-06-30 10:00:06.000000', null, '9', '1', '', null), ('24', '新的开启', '200', '100', 'https://jiaoshi.okjiaoyu.cn/myindex/index_get_by_indextag?_=1561858643766&id=87448&is_waste=0&show_question=', '2000', '1', '1', '', '\"code\":0', '', '[]', '', '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"d1568c1998974c0e891d91b1832a9853\"}]', '[{\"headerKey\":\"reuqestid\",\"headerValue\":\"qa_ooo22202\"}]', '2019-06-30 10:04:29.145000', '2019-06-30 10:04:29.145000', '', '9', '1', '', null), ('25', '获取数据库信息', '100', '200', 'http://jiaoyu.qa-dev.xk12.cn/ksu/list/', '4000', '3', '1', '', '300', '', '[{\"paramskey\":\"content\",\"paramsvalue\":\"1231\"},{\"paramskey\":\"id\",\"paramsvalue\":\"2\"}]', '', '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"2310981390831\"}]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"qa_000101\"}]', '2019-06-30 13:41:21.329000', '2019-06-30 13:41:21.329000', '', '9', '1', '', null), ('28', '测试http', '100', '200', 'https://jiaoshi.qa-dev.xk12.cn/home/course_list?_=1562046981044&custom_directory_id=2677814&origin=0&page=1&page_size=10', '2000', '1', '1', '', '300', '', '[]', '', '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"b4f7795b04f9489691c8ef9e462c68f8\"}]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"1232109013_qa\"}]', '2019-07-02 14:06:27.779000', '2019-07-02 14:06:27.779000', '', '9', '1', '', null), ('29', '添加选择题', '300', '200', 'https://jiaoshi.qa-dev.xk12.cn/quiz/quiz_add', '3000', '2', '1', '', '\"code\":0', '', '[{\"paramskey\":\"statue\",\"paramsvalue\":\"0\"},{\"paramskey\":\"html\",\"paramsvalue\":\"{\\\"body\\\":\\\"<p>测试接口测试<input type=\\\\\\\"text\\\\\\\" readonly=\\\\\\\"true\\\\\\\" class=\\\\\\\"questions-blank\\\\\\\" value=\\\\\\\"1\\\\\\\" contenteditable=\\\\\\\"true\\\\\\\"/>&nbsp;</p>\\\",\\\"analysis\\\":\\\"<p>测试接口数据</p>\\\",\\\"answer\\\":[\\\"<p>1112</p>\\\"],\\\"questions\\\":[]}\"},{\"paramskey\":\"id\",\"paramsvalue\":\"\"},{\"paramskey\":\"level\",\"paramsvalue\":\"2\"},{\"paramskey\":\"source\",\"paramsvalue\":\"测试来源\"},{\"paramskey\":\"ref\",\"paramsvalue\":\"0\"},{\"paramskey\":\"type\",\"paramsvalue\":\"2\"},{\"paramskey\":\"topic\",\"paramsvalue\":\"15809\"},{\"paramskey\":\"special\",\"paramsvalue\":\"4\"},{\"paramskey\":\"chapter\",\"paramsvalue\":\"9709\"},{\"paramskey\":\"versionId\",\"paramsvalue\":\"21\"},{\"paramskey\":\"directoryId\",\"paramsvalue\":\"194\"},{\"paramskey\":\"customDirectoryId\",\"paramsvalue\":\"2677820\"},{\"paramskey\":\"groupId\",\"paramsvalue\":\"45673\"},{\"paramskey\":\"rules\",\"paramsvalue\":\"[]\"}]', '', '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"b4f7795b04f9489691c8ef9e462c68f8\"}]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"123949_qa\"}]', '2019-07-02 14:28:00.353000', '2019-07-02 14:28:00.353000', '', '9', '1', '', null), ('30', '获取推荐知识点', '300', '200', 'http://stupad-hotfix.xk12.cn/api/pad/xiaoyun/knowledge', '2000', '2', '1', '', '\"ecode\": 0', '', '{\n    \"ktype\": 1,\n    \"klevel\": 2\n}', '', '[]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"2132133121\"},{\"headerKey\":\"token\",\"headerValue\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMzA3MjciLCJzeXN0ZW1JZCI6IjgxOTUxMDk2NjA5Iiwib3JnSWQiOiI4MCIsInRpbWVzdGFtcCI6IjE1NjA5MzAyMDY4NDUifQ.bNoV9ARuwTsHr9Z7uQlE6-7CN7XIfwwoM3LaEBhFMtQ\"},{\"headerKey\":\"Content-Type\",\"headerValue\":\"application/json\"}]', '2019-07-02 14:41:39.143000', '2019-07-02 14:41:39.143000', '', '9', '1', '', null), ('31', '测试dubbo接口', '1', '10', '', '1000', '1', '2', 'com.noriental.productsvr.product.service.ProductService', '\"code\":0', 'findProductListForBs', '{\"pageNo\":1,\"pageSize\":10,\"addSource\":1}', 'com.noriental.productsvr.product.bean.request.FindProductListRequest', '[]', '[]', '2019-07-02 16:24:05.484000', '2019-07-02 16:24:05.484000', '1.0.0', '9', '1', '', null), ('32', '教师空间创建题目', '100', '150', 'https://jiaoshi.qa-dev.xk12.cn/quiz/quiz_add', '3000', '2', '1', '', '\"code\"', '', '[{\"paramskey\":\"statue\",\"paramsvalue\":\"0\"},{\"paramskey\":\"html\",\"paramsvalue\":\"{\\\"body\\\":\\\"<p>判断这道题对还是错</p>\\\",\\\"answer\\\":[\\\"1\\\"],\\\"analysis\\\":\\\"<p>错</p>\\\",\\\"questions\\\":[]}\"},{\"paramskey\":\"level\",\"paramsvalue\":\"2\"},{\"paramskey\":\"source\",\"paramsvalue\":\"测试编辑题\"},{\"paramskey\":\"ref\",\"paramsvalue\":\"0\"},{\"paramskey\":\"type\",\"paramsvalue\":\"3\"},{\"paramskey\":\"topic\",\"paramsvalue\":\"15809\"},{\"paramskey\":\"special\",\"paramsvalue\":\"8\"},{\"paramskey\":\"chapter\",\"paramsvalue\":\"9217\"},{\"paramskey\":\"versionId\",\"paramsvalue\":\"19\"},{\"paramskey\":\"directoryId\",\"paramsvalue\":\"180\"},{\"paramskey\":\"customDirectoryId\",\"paramsvalue\":\"2677820\"},{\"paramskey\":\"groupId\",\"paramsvalue\":\"45673\"},{\"paramskey\":\"rules\",\"paramsvalue\":\" []\"}]', '', '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"3c2ccbdd39c1417fbcc8bd237d53bf82\"}]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"qa_2009201091\"}]', '2019-07-05 16:09:37.998000', '2019-07-05 16:09:37.998000', '', '10', '1', '', null), ('33', 'product测试', '10', '20', '', '1000', '1', '2', 'com.noriental.productsvr.product.service.ProductService', '\"code\":0', 'findProductListForBs', '{\"pageNo\":1,\"pageSize\":10,\"addSource\":1}', 'com.noriental.productsvr.product.bean.request.FindProductListRequest', '[]', '[]', '2019-07-05 16:15:52.108000', '2019-07-05 16:15:52.108000', '1.0.0', '10', '1', '10.10.6.1', null), ('34', '测试题集数据', '10', '60', 'https://jiaoshi.qa-dev.xk12.cn/quizcenter/directory_list?_=1562313839379&version_id=19&filter_empty=1', '1000', '1', '1', '', '\"code\":0', '', '[]', '', '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"a4042a3e0c964834afc1f6387ff0ccaf\"}]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"qa_00901901\"}]', '2019-07-05 16:33:41.947000', '2019-07-05 16:33:41.947000', '', '10', '1', '10.10.6.1', null), ('35', '首页课程包', '10', '30', 'https://jiaoshi.qa-dev.xk12.cn/home/course_pkg_list?_=1563260846235&custom_directory_id=2677820&origin=0&page=1&page_size=10&custom_system_id=45673', '1000', '1', '1', '', '\"code\":0', '', '[]', '', '[{\"cookieKey\":\"teacher_id\",\"cookieValue\":\"300f326023514268994a7ee9a503aad7\"}]', '[{\"headerKey\":\"requestid\",\"headerValue\":\"qa_${requestid}\"}]', '2019-07-16 15:11:38.332000', '2019-07-16 15:11:38.332000', '', '10', '1', '10.10.6.2', null), ('36', '教师dubbo接口', '10', '60', '', '2000', '1', '2', 'com.noriental.productsvr.product.service.ProductService', '\"code\":0', 'findProductListForBs', '{\"pageNo\":1,\"pageSize\":10,\"addSource\":1}', 'com.noriental.productsvr.product.bean.request.FindProductListRequest', '[]', '[]', '2019-07-16 19:25:33.171000', '2019-07-16 19:25:33.171000', '1.0.0', '11', '1', '10.10.6.1', null);
COMMIT;

-- ----------------------------
--  Table structure for `entity_token`
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
--  Records of `entity_token`
-- ----------------------------
BEGIN;
INSERT INTO `entity_token` VALUES ('3', '6e350cbed9295bb84e5d79f1459769ee', '2019-07-17 20:35:36.005000', '1'), ('4', '526c7711442799372ff0b6c259f11d35', '2019-06-30 14:01:59.205000', '8');
COMMIT;

-- ----------------------------
--  Table structure for `entity_type`
-- ----------------------------
DROP TABLE IF EXISTS `entity_type`;
CREATE TABLE `entity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_type`
-- ----------------------------
BEGIN;
INSERT INTO `entity_type` VALUES ('1', '教师空间'), ('2', '教师pad'), ('3', '学生pad'), ('4', '商城');
COMMIT;

-- ----------------------------
--  Table structure for `entity_user`
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
--  Records of `entity_user`
-- ----------------------------
BEGIN;
INSERT INTO `entity_user` VALUES ('1', '张亚丹', 'zhangyadan@okay.cn', '123456', '2', '2019-06-29 13:28:31.743000'), ('8', '刘占会', 'liuzhanhui@okay.cn', '123456', '1', '2019-06-30 06:48:30.000000');
COMMIT;

-- ----------------------------
--  Table structure for `qrtz_blob_triggers`
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
--  Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `qrtz_cron_triggers`
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
--  Records of `qrtz_cron_triggers`
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_cron_triggers` VALUES ('DemoScheduler', 'TASK_9', 'DEFAULT', '0 0/50 * * * ?', 'Asia/Shanghai');
COMMIT;

-- ----------------------------
--  Table structure for `qrtz_fired_triggers`
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
--  Table structure for `qrtz_job_details`
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
--  Records of `qrtz_job_details`
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_job_details` VALUES ('DemoScheduler', 'TASK_9', 'DEFAULT', null, 'com.dubbo.api.common.util.ScheduleJobUtil', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000d4a4f425f504152414d5f4b45597400d27b226265616e4e616d65223a22657865634a6d65746572536372697074222c2263726f6e45787072657373696f6e223a223020302f3130202a202a202a203f222c2263727454696d65223a313536323330353835353234382c226964223a392c226d6574686f644e616d65223a22657865634a6d65746572536372697074222c22706172616d73223a22312c3330222c2272656d61726b223a22e6b58be8af95e5ae9ae697b6e4bbbbe58aa1222c227363726970744964223a33302c22737461747573223a302c22757365724964223a317d7800);
COMMIT;

-- ----------------------------
--  Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `qrtz_locks`
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_locks` VALUES ('DemoScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
--  Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `qrtz_scheduler_state`
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
--  Table structure for `qrtz_simple_triggers`
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
--  Table structure for `qrtz_simprop_triggers`
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
--  Table structure for `qrtz_triggers`
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
--  Records of `qrtz_triggers`
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_triggers` VALUES ('DemoScheduler', 'TASK_9', 'DEFAULT', 'TASK_9', 'DEFAULT', null, '1562306400000', '-1', '5', 'PAUSED', 'CRON', '1562305855000', '0', null, '2', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000d4a4f425f504152414d5f4b45597400d27b226265616e4e616d65223a22657865634a6d65746572536372697074222c2263726f6e45787072657373696f6e223a223020302f3530202a202a202a203f222c2263727454696d65223a313536323330353835353030302c226964223a392c226d6574686f644e616d65223a22657865634a6d65746572536372697074222c22706172616d73223a22312c3334222c2272656d61726b223a22e9a298e99b86e5ae9ae697b6e4bbbbe58aa1222c227363726970744964223a33342c22737461747573223a312c22757365724964223a317d7800);
COMMIT;

-- ----------------------------
--  Table structure for `schedule_job`
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
  `script_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `script_id` (`script_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `schedule_job_ibfk_1` FOREIGN KEY (`script_id`) REFERENCES `entity_script` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `schedule_job_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `entity_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='定时任务表';

-- ----------------------------
--  Records of `schedule_job`
-- ----------------------------
BEGIN;
INSERT INTO `schedule_job` VALUES ('9', 'execJmeterScript', 'execJmeterScript', '1,34', '0 0/50 * * * ?', '1', '题集定时任务', '2019-07-05 13:50:55', null, null, '34', '1');
COMMIT;

-- ----------------------------
--  Table structure for `schedule_job_log`
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
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COMMENT='定时任务日志表';

-- ----------------------------
--  Records of `schedule_job_log`
-- ----------------------------
BEGIN;
INSERT INTO `schedule_job_log` VALUES ('92', '2', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '926', '2019-07-05 11:40:00'), ('93', '2', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '45', '2019-07-05 12:10:00'), ('94', '2', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '347', '2019-07-05 12:20:00'), ('95', '2', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '481', '2019-07-05 12:30:00'), ('96', '2', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '47', '2019-07-05 12:40:00'), ('97', '2', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '29', '2019-07-05 12:50:00'), ('98', '5', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '31', '2019-07-05 13:00:00'), ('99', '5', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '348', '2019-07-05 13:10:00'), ('100', '5', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '623', '2019-07-05 13:20:00'), ('101', '5', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '34', '2019-07-05 13:30:00'), ('102', '5', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '37', '2019-07-05 13:40:00'), ('103', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '426', '2019-07-05 14:00:00'), ('104', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '590', '2019-07-05 14:10:00'), ('105', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '509', '2019-07-05 14:20:00'), ('106', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '44', '2019-07-05 14:30:00'), ('107', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '31', '2019-07-05 14:40:00'), ('108', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '440', '2019-07-05 14:50:00'), ('109', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '44', '2019-07-05 15:00:00'), ('110', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '50', '2019-07-05 15:10:00'), ('111', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '31', '2019-07-05 15:20:00'), ('112', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '35', '2019-07-05 15:30:00'), ('113', '9', 'execJmeterScript', 'execJmeterScript', '1,30', '0', null, '208', '2019-07-05 15:40:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
