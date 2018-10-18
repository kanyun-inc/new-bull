DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `id`          int(11)      NOT NULL AUTO_INCREMENT,
  `resource`    varchar(255) NOT NULL COMMENT '资源',
  `action`      varchar(255) NOT NULL COMMENT '操作',
  `methodName`  varchar(255) NOT NULL COMMENT '执行的方法名',
  `className`   varchar(255) NOT NULL COMMENT '执行的类名',
  `createdTime` bigint(20)   NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
);