drop database  if exists `interface`;
CREATE SCHEMA `interface` DEFAULT CHARACTER SET utf8 ;

USE `interface`;

drop TABLE  if exists `interface`.`interDoc`;
CREATE TABLE `interdoc` (
  `interid` varchar(256) NOT NULL COMMENT '文档唯一标识',
  `intername` varchar(128) NOT NULL COMMENT '文档名称',
  `intertag` varchar(128) NOT NULL COMMENT '涉及关键词',
  `dixtag` varchar(128) NOT NULL COMMENT 'dix版本',
  `interdesc` varchar(256) NOT NULL COMMENT '文档描述',
  `interimage` varchar(256) COMMENT '文档照片',
  `intercode` json COMMENT '代码',
  `intereditor` varchar(128) COMMENT '作者',
  `intercreatetime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `interupdatetime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后更新时间',
  PRIMARY KEY (`interid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop TABLE  if exists `interface`.`intertag`;
CREATE TABLE `intertag` (
  `tagid` varchar(256) NOT NULL COMMENT '关键词标识',
  `tagname` varchar(256) NOT NULL COMMENT '关键词名称',
  `tagcreatetime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `tagupdatetime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后更新时间',
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop TABLE  if exists `interface`.`interimage`;
CREATE TABLE `interimage` (
  `interid` varchar(256) NOT NULL COMMENT '文档唯一关键词',
  `interimage` json NOT NULL COMMENT '照片',
  `imagecreatetime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `imageupdatetime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后更新时间',
  PRIMARY KEY (`interid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
