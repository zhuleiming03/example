-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` VARCHAR(32) DEFAULT NULL COMMENT '用户名',
  `pass_word` VARCHAR(32) DEFAULT NULL COMMENT '密码',
  `user_sex` VARCHAR(32) DEFAULT NULL COMMENT '性别（male、female）',
  `is_valid` TINYINT UNSIGNED DEFAULT 0 COMMENT '是否有效（1：是；0：否）',
	`gmt_create` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
	`gmt_modified` DATETIME(0) NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;


INSERT INTO users(user_name,pass_word,user_sex,is_valid) 
VALUES('漩涡鸣人','123','MALE',1),('小樱','321','FEMALE',1)

INSERT INTO users(user_name,pass_word,user_sex,is_valid)
VALUES('桐人','frdgd','MALE',1),('亚丝娜','15613f','FEMALE',1)



