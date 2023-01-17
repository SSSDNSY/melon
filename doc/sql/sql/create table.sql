create table `t_user`
(
    `id`             varchar(32) not null COMMENT '主键',
    `username`       varchar(50) not null COMMENT '名称',
    `password`       varchar(50) not null COMMENT '密码',
    `salt`           tinyint(2) not null COMMENT '盐',
    `email`          varchar(50) not null default '' COMMENT '邮箱',
    `mobile`         varchar(20) not null default '' COMMENT '手机号码',
    `status`         tinyint(2) not null default 1 COMMENT '状态',
    `create_user_id` varchar(35) not null default '' COMMENT '创建人id',
    `create_time`    datetime    not null COMMENT '创建时间',
    primary key (`id`)
) ENGINE = InnoDB default CHARSET = utf8 COMMENT = '测试';


