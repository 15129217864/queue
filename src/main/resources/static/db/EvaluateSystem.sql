DROP DATABASE IF EXISTS evaluate_sys;

CREATE DATABASE evaluate_sys;

USE evaluate_sys;

DROP TABLE IF EXISTS base_biz;
CREATE TABLE `base_biz` (
  `base_biz_id` int(7) NOT NULL AUTO_INCREMENT COMMENT '业务主键',
  `base_biz_name` varchar(10) DEFAULT NULL COMMENT '业务名称',
  `base_biz_num` varchar(10) DEFAULT NULL COMMENT '业务窗口编号',
  PRIMARY KEY (`base_biz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务信息表';

DROP TABLE IF EXISTS evaluate;
CREATE TABLE `evaluate` (
  `evaluate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价主键',
  `handle_biz_id` int(11) DEFAULT NULL COMMENT '处理业务外键',
  `evaluate_type` char(1) DEFAULT '1' COMMENT '评价类型 1好/2中/3差',
  `evaluate_date` varchar(25) DEFAULT NULL COMMENT '评价日期',
  PRIMARY KEY (`evaluate_id`),
  KEY `idx_handle_biz_id` (`handle_biz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务评价表';

DROP TABLE IF EXISTS handle_biz_record;
CREATE TABLE `handle_biz_record` (
  `job_user_id` int(11) DEFAULT NULL COMMENT '工作人员外键',
  `base_biz_id` int(11) DEFAULT NULL COMMENT '业务外键',
  `transact_biz_id` int(11) DEFAULT NULL COMMENT '办理业务外键',
  `handle_start_date` varchar(25) DEFAULT NULL COMMENT '处理开始时间',
  `handle_end_date` varchar(25) DEFAULT NULL COMMENT '处理结束时间',
  `handle_biz_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '处理业务主键',
  PRIMARY KEY (`handle_biz_id`),
  KEY `idx_job_user_id` (`job_user_id`),
  KEY `idx_base_biz_id` (`base_biz_id`),
  KEY `idx_transact_biz_id` (`transact_biz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工处理业务记录';

DROP TABLE IF EXISTS job_user;
CREATE TABLE `job_user` (
  `job_user_name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `job_user_sex` char(1) DEFAULT '0' COMMENT '0-男，1-女',
  `job_user_num` varchar(50) DEFAULT NULL COMMENT '编号',
  `job_user_photo` varchar(100) DEFAULT NULL COMMENT '工作人员头像图片名称',
  `job_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工作人员表主键',
  PRIMARY KEY (`job_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作人员信息表';

DROP TABLE IF EXISTS set_biz_screen_info;
CREATE TABLE `set_biz_screen_info` (
  `biz_screen_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '业务屏主键',
  `base_biz_id` int(11) DEFAULT NULL COMMENT '基础业务外键',
  `job_user_id` int(11) DEFAULT NULL COMMENT '业务员外键',
  `biz_screen_mark` char(1) DEFAULT '0' COMMENT '0-办理业务、1-业务员',
  `biz_screen_ip` varchar(15) DEFAULT NULL COMMENT '业务屏对应的安卓ip',
  PRIMARY KEY (`biz_screen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务屏设置表';

DROP TABLE IF EXISTS set_screen_info;
CREATE TABLE `set_screen_info` (
  `screen_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '条屏主键',
  `base_biz_id` int(11) DEFAULT NULL COMMENT '业务外键',
  `screen_mark` char(1) DEFAULT '0' COMMENT '0-单、1-双',
  `screen_type` char(1) DEFAULT '0' COMMENT '0-左、1-右、2-中',
  `screen_num` varchar(10) DEFAULT NULL COMMENT '条屏编号',
  `screen_ip` varchar(15) DEFAULT NULL COMMENT '条屏对应的安卓ip',
  `screen_barckground` VARCHAR(200) null COMMENT '背景图',
  PRIMARY KEY (`screen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='条屏编号表';

DROP TABLE IF EXISTS transact_biz_record;
CREATE TABLE `transact_biz_record` (
  `transact_biz_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '业务办理记录主键',
  `id_card` varchar(25) DEFAULT NULL COMMENT '身份证号',
  `use_name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `use_sex` char(1) DEFAULT NULL COMMENT '用户性别',
  `use_photo` longtext DEFAULT NULL COMMENT '用户头像 保存图片名称',
  `use_birty` varchar(25) DEFAULT NULL COMMENT '用户出生日期',
  `use_addr` varchar(200) DEFAULT NULL COMMENT '用户住址',
  `screen_num` varchar(50) DEFAULT NULL COMMENT '屏号',
  `biz_name` varchar(50) DEFAULT NULL COMMENT '业务名称',
  `queue_num` varchar(20) DEFAULT NULL COMMENT '排队号',
  `use_current_photo` longtext DEFAULT NULL COMMENT '用户当前头像 保存图片名称',
  `transact_biz_date` varchar(25) DEFAULT NULL COMMENT '办理时间',
  PRIMARY KEY (`transact_biz_id`),
  KEY `idx_queue_no` (`queue_num`),
  KEY `idx_use_name` (`use_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务办理记录表';
