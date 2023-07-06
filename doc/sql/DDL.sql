-- melon.ACT_EVT_LOG definition

CREATE TABLE `ACT_EVT_LOG`
(
    `LOG_NR_`       bigint(20)   NOT NULL AUTO_INCREMENT,
    `TYPE_`         varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `PROC_DEF_ID_`  varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `EXECUTION_ID_` varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `TASK_ID_`      varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `TIME_STAMP_`   timestamp(3) NOT NULL         DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `USER_ID_`      varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `DATA_`         longblob,
    `LOCK_OWNER_`   varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `LOCK_TIME_`    timestamp(3) NULL             DEFAULT NULL,
    `IS_PROCESSED_` tinyint(4)                    DEFAULT '0',
    PRIMARY KEY (`LOG_NR_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_GE_PROPERTY definition

CREATE TABLE `ACT_GE_PROPERTY`
(
    `NAME_`  varchar(64) COLLATE utf8_bin NOT NULL,
    `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
    `REV_`   int(11)                       DEFAULT NULL,
    PRIMARY KEY (`NAME_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_HI_ACTINST definition

CREATE TABLE `ACT_HI_ACTINST`
(
    `ID_`                varchar(64) COLLATE utf8_bin  NOT NULL,
    `PROC_DEF_ID_`       varchar(64) COLLATE utf8_bin  NOT NULL,
    `PROC_INST_ID_`      varchar(64) COLLATE utf8_bin  NOT NULL,
    `EXECUTION_ID_`      varchar(64) COLLATE utf8_bin  NOT NULL,
    `ACT_ID_`            varchar(255) COLLATE utf8_bin NOT NULL,
    `TASK_ID_`           varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `ACT_NAME_`          varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `ACT_TYPE_`          varchar(255) COLLATE utf8_bin NOT NULL,
    `ASSIGNEE_`          varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `START_TIME_`        datetime(3)                   NOT NULL,
    `END_TIME_`          datetime(3)                    DEFAULT NULL,
    `DURATION_`          bigint(20)                     DEFAULT NULL,
    `DELETE_REASON_`     varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `TENANT_ID_`         varchar(255) COLLATE utf8_bin  DEFAULT '',
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
    KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
    KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`, `ACT_ID_`),
    KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`, `ACT_ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_HI_ATTACHMENT definition

CREATE TABLE `ACT_HI_ATTACHMENT`
(
    `ID_`           varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`          int(11)                        DEFAULT NULL,
    `USER_ID_`      varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `NAME_`         varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `DESCRIPTION_`  varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `TYPE_`         varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `TASK_ID_`      varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `URL_`          varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `CONTENT_ID_`   varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `TIME_`         datetime(3)                    DEFAULT NULL,
    PRIMARY KEY (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_HI_COMMENT definition

CREATE TABLE `ACT_HI_COMMENT`
(
    `ID_`           varchar(64) COLLATE utf8_bin NOT NULL,
    `TYPE_`         varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `TIME_`         datetime(3)                  NOT NULL,
    `USER_ID_`      varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `TASK_ID_`      varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `ACTION_`       varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `MESSAGE_`      varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `FULL_MSG_`     longblob,
    PRIMARY KEY (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_HI_DETAIL definition

CREATE TABLE `ACT_HI_DETAIL`
(
    `ID_`           varchar(64) COLLATE utf8_bin  NOT NULL,
    `TYPE_`         varchar(255) COLLATE utf8_bin NOT NULL,
    `PROC_INST_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `EXECUTION_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `TASK_ID_`      varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `ACT_INST_ID_`  varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `NAME_`         varchar(255) COLLATE utf8_bin NOT NULL,
    `VAR_TYPE_`     varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `REV_`          int(11)                        DEFAULT NULL,
    `TIME_`         datetime(3)                   NOT NULL,
    `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `DOUBLE_`       double                         DEFAULT NULL,
    `LONG_`         bigint(20)                     DEFAULT NULL,
    `TEXT_`         varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `TEXT2_`        varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
    KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
    KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
    KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
    KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_HI_IDENTITYLINK definition

CREATE TABLE `ACT_HI_IDENTITYLINK`
(
    `ID_`           varchar(64) COLLATE utf8_bin NOT NULL,
    `GROUP_ID_`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `TYPE_`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `USER_ID_`      varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `TASK_ID_`      varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
    KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
    KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_HI_PROCINST definition

CREATE TABLE `ACT_HI_PROCINST`
(
    `ID_`                        varchar(64) COLLATE utf8_bin NOT NULL,
    `PROC_INST_ID_`              varchar(64) COLLATE utf8_bin NOT NULL,
    `BUSINESS_KEY_`              varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `PROC_DEF_ID_`               varchar(64) COLLATE utf8_bin NOT NULL,
    `START_TIME_`                datetime(3)                  NOT NULL,
    `END_TIME_`                  datetime(3)                    DEFAULT NULL,
    `DURATION_`                  bigint(20)                     DEFAULT NULL,
    `START_USER_ID_`             varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `START_ACT_ID_`              varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `END_ACT_ID_`                varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `DELETE_REASON_`             varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `TENANT_ID_`                 varchar(255) COLLATE utf8_bin  DEFAULT '',
    `NAME_`                      varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
    KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
    KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_HI_TASKINST definition

CREATE TABLE `ACT_HI_TASKINST`
(
    `ID_`             varchar(64) COLLATE utf8_bin NOT NULL,
    `PROC_DEF_ID_`    varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `TASK_DEF_KEY_`   varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `PROC_INST_ID_`   varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `EXECUTION_ID_`   varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `NAME_`           varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `DESCRIPTION_`    varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `OWNER_`          varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `ASSIGNEE_`       varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `START_TIME_`     datetime(3)                  NOT NULL,
    `CLAIM_TIME_`     datetime(3)                    DEFAULT NULL,
    `END_TIME_`       datetime(3)                    DEFAULT NULL,
    `DURATION_`       bigint(20)                     DEFAULT NULL,
    `DELETE_REASON_`  varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `PRIORITY_`       int(11)                        DEFAULT NULL,
    `DUE_DATE_`       datetime(3)                    DEFAULT NULL,
    `FORM_KEY_`       varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `CATEGORY_`       varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `TENANT_ID_`      varchar(255) COLLATE utf8_bin  DEFAULT '',
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_HI_VARINST definition

CREATE TABLE `ACT_HI_VARINST`
(
    `ID_`                varchar(64) COLLATE utf8_bin  NOT NULL,
    `PROC_INST_ID_`      varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `EXECUTION_ID_`      varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `TASK_ID_`           varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `NAME_`              varchar(255) COLLATE utf8_bin NOT NULL,
    `VAR_TYPE_`          varchar(100) COLLATE utf8_bin  DEFAULT NULL,
    `REV_`               int(11)                        DEFAULT NULL,
    `BYTEARRAY_ID_`      varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `DOUBLE_`            double                         DEFAULT NULL,
    `LONG_`              bigint(20)                     DEFAULT NULL,
    `TEXT_`              varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `TEXT2_`             varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `CREATE_TIME_`       datetime(3)                    DEFAULT NULL,
    `LAST_UPDATED_TIME_` datetime(3)                    DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
    KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`, `VAR_TYPE_`),
    KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_ID_GROUP definition

CREATE TABLE `ACT_ID_GROUP`
(
    `ID_`   varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`  int(11)                       DEFAULT NULL,
    `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_ID_INFO definition

CREATE TABLE `ACT_ID_INFO`
(
    `ID_`        varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`       int(11)                       DEFAULT NULL,
    `USER_ID_`   varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `TYPE_`      varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `KEY_`       varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `VALUE_`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `PASSWORD_`  longblob,
    `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_ID_USER definition

CREATE TABLE `ACT_ID_USER`
(
    `ID_`         varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`        int(11)                       DEFAULT NULL,
    `FIRST_`      varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `LAST_`       varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `EMAIL_`      varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `PWD_`        varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `PICTURE_ID_` varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    PRIMARY KEY (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RE_DEPLOYMENT definition

CREATE TABLE `ACT_RE_DEPLOYMENT`
(
    `ID_`             varchar(64) COLLATE utf8_bin NOT NULL,
    `NAME_`           varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `CATEGORY_`       varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `KEY_`            varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `TENANT_ID_`      varchar(255) COLLATE utf8_bin     DEFAULT '',
    `DEPLOY_TIME_`    timestamp(3)                 NULL DEFAULT NULL,
    `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    PRIMARY KEY (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RE_PROCDEF definition

CREATE TABLE `ACT_RE_PROCDEF`
(
    `ID_`                     varchar(64) COLLATE utf8_bin  NOT NULL,
    `REV_`                    int(11)                        DEFAULT NULL,
    `CATEGORY_`               varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `NAME_`                   varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    `KEY_`                    varchar(255) COLLATE utf8_bin NOT NULL,
    `VERSION_`                int(11)                       NOT NULL,
    `DEPLOYMENT_ID_`          varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `RESOURCE_NAME_`          varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `DGRM_RESOURCE_NAME_`     varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `DESCRIPTION_`            varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `HAS_START_FORM_KEY_`     tinyint(4)                     DEFAULT NULL,
    `HAS_GRAPHICAL_NOTATION_` tinyint(4)                     DEFAULT NULL,
    `SUSPENSION_STATE_`       int(11)                        DEFAULT NULL,
    `TENANT_ID_`              varchar(255) COLLATE utf8_bin  DEFAULT '',
    `ENGINE_VERSION_`         varchar(255) COLLATE utf8_bin  DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`, `VERSION_`, `TENANT_ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.gen_table definition

CREATE TABLE `gen_table`
(
    `table_id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_name`        varchar(200) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '表名称',
    `table_comment`     varchar(500) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '表描述',
    `sub_table_name`    varchar(64) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '关联子表的表名',
    `sub_table_fk_name` varchar(64) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '子表关联的外键名',
    `class_name`        varchar(100) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '实体类名称',
    `tpl_category`      varchar(200) COLLATE utf8mb4_bin  DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
    `package_name`      varchar(100) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '生成包路径',
    `module_name`       varchar(30) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '生成模块名',
    `business_name`     varchar(30) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '生成业务名',
    `function_name`     varchar(50) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '生成功能名',
    `function_author`   varchar(50) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '生成功能作者',
    `gen_type`          char(1) COLLATE utf8mb4_bin       DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
    `gen_path`          varchar(200) COLLATE utf8mb4_bin  DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
    `options`           varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '其它生成选项',
    `create_by`         varchar(64) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '创建者',
    `create_time`       datetime                          DEFAULT NULL COMMENT '创建时间',
    `update_by`         varchar(64) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '更新者',
    `update_time`       datetime                          DEFAULT NULL COMMENT '更新时间',
    `remark`            varchar(500) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`table_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='代码生成业务表';


-- melon.gen_table_column definition

CREATE TABLE `gen_table_column`
(
    `column_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_id`       varchar(64) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '归属表编号',
    `column_name`    varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '列名称',
    `column_comment` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '列描述',
    `column_type`    varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '列类型',
    `java_type`      varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'JAVA类型',
    `java_field`     varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'JAVA字段名',
    `is_pk`          char(1) COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '是否主键（1是）',
    `is_increment`   char(1) COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '是否自增（1是）',
    `is_required`    char(1) COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '是否必填（1是）',
    `is_insert`      char(1) COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '是否为插入字段（1是）',
    `is_edit`        char(1) COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '是否编辑字段（1是）',
    `is_list`        char(1) COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '是否列表字段（1是）',
    `is_query`       char(1) COLLATE utf8mb4_bin      DEFAULT NULL COMMENT '是否查询字段（1是）',
    `query_type`     varchar(200) COLLATE utf8mb4_bin DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
    `html_type`      varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    `dict_type`      varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典类型',
    `sort`           int(11)                          DEFAULT NULL COMMENT '排序',
    `create_by`      varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '创建者',
    `create_time`    datetime                         DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '更新者',
    `update_time`    datetime                         DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`column_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='代码生成业务表字段';


-- melon.qrtz_calendars definition

CREATE TABLE `qrtz_calendars`
(
    `sched_name`    varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `calendar_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '日历名称',
    `calendar`      blob                             NOT NULL COMMENT '存放持久化calendar对象',
    PRIMARY KEY (`sched_name`, `calendar_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='日历信息表';


-- melon.qrtz_fired_triggers definition

CREATE TABLE `qrtz_fired_triggers`
(
    `sched_name`        varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `entry_id`          varchar(95) COLLATE utf8mb4_bin  NOT NULL COMMENT '调度器实例id',
    `trigger_name`      varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group`     varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `instance_name`     varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '调度器实例名',
    `fired_time`        bigint(20)                       NOT NULL COMMENT '触发的时间',
    `sched_time`        bigint(20)                       NOT NULL COMMENT '定时器制定的时间',
    `priority`          int(11)                          NOT NULL COMMENT '优先级',
    `state`             varchar(16) COLLATE utf8mb4_bin  NOT NULL COMMENT '状态',
    `job_name`          varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名称',
    `job_group`         varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务组名',
    `is_nonconcurrent`  varchar(1) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '是否并发',
    `requests_recovery` varchar(1) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT '是否接受恢复执行',
    PRIMARY KEY (`sched_name`, `entry_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='已触发的触发器表';


-- melon.qrtz_job_details definition

CREATE TABLE `qrtz_job_details`
(
    `sched_name`        varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `job_name`          varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '任务名称',
    `job_group`         varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '任务组名',
    `description`       varchar(250) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '相关介绍',
    `job_class_name`    varchar(250) COLLATE utf8mb4_bin NOT NULL COMMENT '执行任务类名称',
    `is_durable`        varchar(1) COLLATE utf8mb4_bin   NOT NULL COMMENT '是否持久化',
    `is_nonconcurrent`  varchar(1) COLLATE utf8mb4_bin   NOT NULL COMMENT '是否并发',
    `is_update_data`    varchar(1) COLLATE utf8mb4_bin   NOT NULL COMMENT '是否更新数据',
    `requests_recovery` varchar(1) COLLATE utf8mb4_bin   NOT NULL COMMENT '是否接受恢复执行',
    `job_data`          blob COMMENT '存放持久化job对象',
    PRIMARY KEY (`sched_name`, `job_name`, `job_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='任务详细信息表';


-- melon.qrtz_locks definition

CREATE TABLE `qrtz_locks`
(
    `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `lock_name`  varchar(40) COLLATE utf8mb4_bin  NOT NULL COMMENT '悲观锁名称',
    PRIMARY KEY (`sched_name`, `lock_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='存储的悲观锁信息表';


-- melon.qrtz_paused_trigger_grps definition

CREATE TABLE `qrtz_paused_trigger_grps`
(
    `sched_name`    varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    PRIMARY KEY (`sched_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='暂停的触发器表';


-- melon.qrtz_scheduler_state definition

CREATE TABLE `qrtz_scheduler_state`
(
    `sched_name`        varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `instance_name`     varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '实例名称',
    `last_checkin_time` bigint(20)                       NOT NULL COMMENT '上次检查时间',
    `checkin_interval`  bigint(20)                       NOT NULL COMMENT '检查间隔时间',
    PRIMARY KEY (`sched_name`, `instance_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='调度器状态表';


-- melon.sys_config definition

CREATE TABLE `sys_config`
(
    `config_id`    int(11) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
    `config_name`  varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '参数键值',
    `config_type`  char(1) COLLATE utf8mb4_bin      DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
    `create_by`    varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '创建者',
    `create_time`  datetime                         DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '更新者',
    `update_time`  datetime                         DEFAULT NULL COMMENT '更新时间',
    `remark`       varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`config_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='参数配置表';


-- melon.sys_dept definition

CREATE TABLE `sys_dept`
(
    `dept_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `parent_id`   bigint(20)                      DEFAULT '0' COMMENT '父部门id',
    `ancestors`   varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '祖级列表',
    `dept_name`   varchar(30) COLLATE utf8mb4_bin DEFAULT '' COMMENT '部门名称',
    `order_num`   int(11)                         DEFAULT '0' COMMENT '显示顺序',
    `leader`      varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '负责人',
    `phone`       varchar(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
    `email`       varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
    `status`      char(1) COLLATE utf8mb4_bin     DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
    `del_flag`    char(1) COLLATE utf8mb4_bin     DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
    `create_time` datetime                        DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
    `update_time` datetime                        DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`dept_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 200
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='部门表';


-- melon.sys_dict_data definition

CREATE TABLE `sys_dict_data`
(
    `dict_code`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `dict_sort`   int(11)                          DEFAULT '0' COMMENT '字典排序',
    `dict_label`  varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典标签',
    `dict_value`  varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典类型',
    `css_class`   varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class`  varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表格回显样式',
    `is_default`  char(1) COLLATE utf8mb4_bin      DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `status`      char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '创建者',
    `create_time` datetime                         DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '更新者',
    `update_time` datetime                         DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='字典数据表';


-- melon.sys_dict_type definition

CREATE TABLE `sys_dict_type`
(
    `dict_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name`   varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典类型',
    `status`      char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '创建者',
    `create_time` datetime                         DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '更新者',
    `update_time` datetime                         DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_id`),
    UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='字典类型表';


-- melon.sys_job definition

CREATE TABLE `sys_job`
(
    `job_id`          bigint(20)                       NOT NULL AUTO_INCREMENT COMMENT '任务ID',
    `job_name`        varchar(64) COLLATE utf8mb4_bin  NOT NULL DEFAULT '' COMMENT '任务名称',
    `job_group`       varchar(64) COLLATE utf8mb4_bin  NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
    `invoke_target`   varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '调用目标字符串',
    `cron_expression` varchar(255) COLLATE utf8mb4_bin          DEFAULT '' COMMENT 'cron执行表达式',
    `misfire_policy`  varchar(20) COLLATE utf8mb4_bin           DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    `concurrent`      char(1) COLLATE utf8mb4_bin               DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
    `status`          char(1) COLLATE utf8mb4_bin               DEFAULT '0' COMMENT '状态（0正常 1暂停）',
    `create_by`       varchar(64) COLLATE utf8mb4_bin           DEFAULT '' COMMENT '创建者',
    `create_time`     datetime                                  DEFAULT NULL COMMENT '创建时间',
    `update_by`       varchar(64) COLLATE utf8mb4_bin           DEFAULT '' COMMENT '更新者',
    `update_time`     datetime                                  DEFAULT NULL COMMENT '更新时间',
    `remark`          varchar(500) COLLATE utf8mb4_bin          DEFAULT '' COMMENT '备注信息',
    PRIMARY KEY (`job_id`, `job_name`, `job_group`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='定时任务调度表';


-- melon.sys_job_log definition

CREATE TABLE `sys_job_log`
(
    `job_log_id`     bigint(20)                       NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
    `job_name`       varchar(64) COLLATE utf8mb4_bin  NOT NULL COMMENT '任务名称',
    `job_group`      varchar(64) COLLATE utf8mb4_bin  NOT NULL COMMENT '任务组名',
    `invoke_target`  varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '调用目标字符串',
    `job_message`    varchar(500) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '日志信息',
    `status`         char(1) COLLATE utf8mb4_bin       DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
    `exception_info` varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '异常信息',
    `create_time`    datetime                          DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`job_log_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='定时任务调度日志表';


-- melon.sys_logininfor definition

CREATE TABLE `sys_logininfor`
(
    `info_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `user_name`      varchar(50) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '用户账号',
    `ipaddr`         varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '操作系统',
    `status`         char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg`            varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime                         DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`info_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 226
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='系统访问记录';


-- melon.sys_menu definition

CREATE TABLE `sys_menu`
(
    `menu_id`     bigint(20)                      NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name`   varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
    `parent_id`   bigint(20)                       DEFAULT '0' COMMENT '父菜单ID',
    `order_num`   int(11)                          DEFAULT '0' COMMENT '显示顺序',
    `path`        varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '路由地址',
    `component`   varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件路径',
    `query`       varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '路由参数',
    `is_frame`    int(11)                          DEFAULT '1' COMMENT '是否为外链（0是 1否）',
    `is_cache`    int(11)                          DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type`   char(1) COLLATE utf8mb4_bin      DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible`     char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `status`      char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `perms`       varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100) COLLATE utf8mb4_bin DEFAULT '#' COMMENT '菜单图标',
    `create_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '创建者',
    `create_time` datetime                         DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '更新者',
    `update_time` datetime                         DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`menu_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2018
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='菜单权限表';


-- melon.sys_notice definition

CREATE TABLE `sys_notice`
(
    `notice_id`      int(11)                         NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `notice_title`   varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '公告标题',
    `notice_type`    char(1) COLLATE utf8mb4_bin     NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` longblob COMMENT '公告内容',
    `status`         char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `create_by`      varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '创建者',
    `create_time`    datetime                         DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '更新者',
    `update_time`    datetime                         DEFAULT NULL COMMENT '更新时间',
    `remark`         varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`notice_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='通知公告表';


-- melon.sys_oper_log definition

CREATE TABLE `sys_oper_log`
(
    `oper_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`          varchar(50) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '模块标题',
    `business_type`  int(11)                           DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '请求方式',
    `operator_type`  int(11)                           DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '返回参数',
    `status`         int(11)                           DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime                          DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`oper_id`),
    KEY `sys_oper_log_oper_time_IDX` (`oper_time`) USING BTREE,
    KEY `sys_oper_log_status_IDX` (`status`) USING BTREE,
    KEY `sys_oper_log_business_type_IDX` (`business_type`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 143
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='操作日志记录';


-- melon.sys_post definition

CREATE TABLE `sys_post`
(
    `post_id`     bigint(20)                      NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `post_code`   varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '岗位编码',
    `post_name`   varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '岗位名称',
    `post_sort`   int(11)                         NOT NULL COMMENT '显示顺序',
    `status`      char(1) COLLATE utf8mb4_bin     NOT NULL COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '创建者',
    `create_time` datetime                         DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '更新者',
    `update_time` datetime                         DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`post_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='岗位信息表';


-- melon.sys_role definition

CREATE TABLE `sys_role`
(
    `role_id`             bigint(20)                       NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name`           varchar(30) COLLATE utf8mb4_bin  NOT NULL COMMENT '角色名称',
    `role_key`            varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '角色权限字符串',
    `role_sort`           int(11)                          NOT NULL COMMENT '显示顺序',
    `data_scope`          char(1) COLLATE utf8mb4_bin      DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `menu_check_strictly` tinyint(1)                       DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
    `dept_check_strictly` tinyint(1)                       DEFAULT '1' COMMENT '部门树选择项是否关联显示',
    `status`              char(1) COLLATE utf8mb4_bin      NOT NULL COMMENT '角色状态（0正常 1停用）',
    `del_flag`            char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`           varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '创建者',
    `create_time`         datetime                         DEFAULT NULL COMMENT '创建时间',
    `update_by`           varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '更新者',
    `update_time`         datetime                         DEFAULT NULL COMMENT '更新时间',
    `remark`              varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='角色信息表';


-- melon.sys_role_dept definition

CREATE TABLE `sys_role_dept`
(
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
    PRIMARY KEY (`role_id`, `dept_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='角色和部门关联表';


-- melon.sys_role_menu definition

CREATE TABLE `sys_role_menu`
(
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='角色和菜单关联表';


-- melon.sys_user definition

CREATE TABLE `sys_user`
(
    `user_id`     bigint(20)                      NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `dept_id`     bigint(20)                       DEFAULT NULL COMMENT '部门ID',
    `user_name`   varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '用户账号',
    `nick_name`   varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
    `user_type`   varchar(2) COLLATE utf8mb4_bin   DEFAULT '00' COMMENT '用户类型（00系统用户）',
    `email`       varchar(50) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '用户邮箱',
    `phonenumber` varchar(11) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '手机号码',
    `sex`         char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar`      varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '头像地址',
    `password`    varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '密码',
    `status`      char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `del_flag`    char(1) COLLATE utf8mb4_bin      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip`    varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime                         DEFAULT NULL COMMENT '最后登录时间',
    `create_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '创建者',
    `create_time` datetime                         DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin  DEFAULT '' COMMENT '更新者',
    `update_time` datetime                         DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1553797
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='用户信息表';


-- melon.sys_user_post definition

CREATE TABLE `sys_user_post`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
    PRIMARY KEY (`user_id`, `post_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='用户与岗位关联表';


-- melon.sys_user_role definition

CREATE TABLE `sys_user_role`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='用户和角色关联表';


-- melon.xxl_conf_env definition

CREATE TABLE `xxl_conf_env`
(
    `env`         varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT 'Env',
    `title`       varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '环境名称',
    `order`       tinyint(4)                       NOT NULL DEFAULT '0' COMMENT '显示排序',
    `create_by`   varchar(64) COLLATE utf8mb4_bin           DEFAULT '' COMMENT '创建者',
    `create_time` datetime                                  DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin           DEFAULT '' COMMENT '更新者',
    `update_time` datetime                                  DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`env`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='config配置环境';


-- melon.xxl_conf_node definition

CREATE TABLE `xxl_conf_node`
(
    `env`         varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT 'Env',
    `key`         varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '配置Key',
    `appname`     varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '所属项目AppName',
    `title`       varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '配置描述',
    `value`       varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置Value',
    `create_by`   varchar(64) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '创建者',
    `create_time` datetime                          DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '更新者',
    `update_time` datetime                          DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`env`, `key`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='config配置节点';


-- melon.xxl_conf_node_log definition

CREATE TABLE `xxl_conf_node_log`
(
    `env`         varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT 'Env',
    `key`         varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '配置Key',
    `title`       varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '配置描述',
    `value`       varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置Value',
    `create_by`   varchar(64) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '创建者',
    `create_time` datetime                          DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '更新者',
    `update_time` datetime                          DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='config配置日志';


-- melon.xxl_conf_node_msg definition

CREATE TABLE `xxl_conf_node_msg`
(
    `id`          int(11)                          NOT NULL AUTO_INCREMENT,
    `env`         varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT 'Env',
    `key`         varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '配置Key',
    `value`       varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置Value',
    `create_by`   varchar(64) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '创建者',
    `create_time` datetime                          DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin   DEFAULT '' COMMENT '更新者',
    `update_time` datetime                          DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 34
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='config配置项消息';


-- melon.xxl_conf_project definition

CREATE TABLE `xxl_conf_project`
(
    `appname`     varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT 'AppName',
    `title`       varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '项目名称',
    `create_by`   varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
    `create_time` datetime                        DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
    `update_time` datetime                        DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`appname`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='config配置项';


-- melon.ACT_GE_BYTEARRAY definition

CREATE TABLE `ACT_GE_BYTEARRAY`
(
    `ID_`            varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`           int(11)                       DEFAULT NULL,
    `NAME_`          varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `BYTES_`         longblob,
    `GENERATED_`     tinyint(4)                    DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
    CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_ID_MEMBERSHIP definition

CREATE TABLE `ACT_ID_MEMBERSHIP`
(
    `USER_ID_`  varchar(64) COLLATE utf8_bin NOT NULL,
    `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
    PRIMARY KEY (`USER_ID_`, `GROUP_ID_`),
    KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
    CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `ACT_ID_GROUP` (`ID_`),
    CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `ACT_ID_USER` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_PROCDEF_INFO definition

CREATE TABLE `ACT_PROCDEF_INFO`
(
    `ID_`           varchar(64) COLLATE utf8_bin NOT NULL,
    `PROC_DEF_ID_`  varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`          int(11)                      DEFAULT NULL,
    `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
    KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
    KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
    CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
    CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RE_MODEL definition

CREATE TABLE `ACT_RE_MODEL`
(
    `ID_`                           varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`                          int(11)                           DEFAULT NULL,
    `NAME_`                         varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `KEY_`                          varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `CATEGORY_`                     varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `CREATE_TIME_`                  timestamp(3)                 NULL DEFAULT NULL,
    `LAST_UPDATE_TIME_`             timestamp(3)                 NULL DEFAULT NULL,
    `VERSION_`                      int(11)                           DEFAULT NULL,
    `META_INFO_`                    varchar(4000) COLLATE utf8_bin    DEFAULT NULL,
    `DEPLOYMENT_ID_`                varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `EDITOR_SOURCE_VALUE_ID_`       varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `TENANT_ID_`                    varchar(255) COLLATE utf8_bin     DEFAULT '',
    PRIMARY KEY (`ID_`),
    KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
    KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
    KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
    CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`),
    CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
    CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RU_EXECUTION definition

CREATE TABLE `ACT_RU_EXECUTION`
(
    `ID_`                   varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`                  int(11)                           DEFAULT NULL,
    `PROC_INST_ID_`         varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `BUSINESS_KEY_`         varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `PARENT_ID_`            varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `PROC_DEF_ID_`          varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `SUPER_EXEC_`           varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `ROOT_PROC_INST_ID_`    varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `ACT_ID_`               varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `IS_ACTIVE_`            tinyint(4)                        DEFAULT NULL,
    `IS_CONCURRENT_`        tinyint(4)                        DEFAULT NULL,
    `IS_SCOPE_`             tinyint(4)                        DEFAULT NULL,
    `IS_EVENT_SCOPE_`       tinyint(4)                        DEFAULT NULL,
    `IS_MI_ROOT_`           tinyint(4)                        DEFAULT NULL,
    `SUSPENSION_STATE_`     int(11)                           DEFAULT NULL,
    `CACHED_ENT_STATE_`     int(11)                           DEFAULT NULL,
    `TENANT_ID_`            varchar(255) COLLATE utf8_bin     DEFAULT '',
    `NAME_`                 varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `START_TIME_`           datetime(3)                       DEFAULT NULL,
    `START_USER_ID_`        varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `LOCK_TIME_`            timestamp(3)                 NULL DEFAULT NULL,
    `IS_COUNT_ENABLED_`     tinyint(4)                        DEFAULT NULL,
    `EVT_SUBSCR_COUNT_`     int(11)                           DEFAULT NULL,
    `TASK_COUNT_`           int(11)                           DEFAULT NULL,
    `JOB_COUNT_`            int(11)                           DEFAULT NULL,
    `TIMER_JOB_COUNT_`      int(11)                           DEFAULT NULL,
    `SUSP_JOB_COUNT_`       int(11)                           DEFAULT NULL,
    `DEADLETTER_JOB_COUNT_` int(11)                           DEFAULT NULL,
    `VAR_COUNT_`            int(11)                           DEFAULT NULL,
    `ID_LINK_COUNT_`        int(11)                           DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
    KEY `ACT_IDC_EXEC_ROOT` (`ROOT_PROC_INST_ID_`),
    KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
    KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
    KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
    KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
    CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE,
    CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
    CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RU_JOB definition

CREATE TABLE `ACT_RU_JOB`
(
    `ID_`                  varchar(64) COLLATE utf8_bin  NOT NULL,
    `REV_`                 int(11)                            DEFAULT NULL,
    `TYPE_`                varchar(255) COLLATE utf8_bin NOT NULL,
    `LOCK_EXP_TIME_`       timestamp(3)                  NULL DEFAULT NULL,
    `LOCK_OWNER_`          varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `EXCLUSIVE_`           tinyint(1)                         DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `RETRIES_`             int(11)                            DEFAULT NULL,
    `EXCEPTION_STACK_ID_`  varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `EXCEPTION_MSG_`       varchar(4000) COLLATE utf8_bin     DEFAULT NULL,
    `DUEDATE_`             timestamp(3)                  NULL DEFAULT NULL,
    `REPEAT_`              varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `HANDLER_TYPE_`        varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `HANDLER_CFG_`         varchar(4000) COLLATE utf8_bin     DEFAULT NULL,
    `TENANT_ID_`           varchar(255) COLLATE utf8_bin      DEFAULT '',
    PRIMARY KEY (`ID_`),
    KEY `ACT_FK_JOB_EXECUTION` (`EXECUTION_ID_`),
    KEY `ACT_FK_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
    KEY `ACT_FK_JOB_PROC_DEF` (`PROC_DEF_ID_`),
    KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
    CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
    CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RU_SUSPENDED_JOB definition

CREATE TABLE `ACT_RU_SUSPENDED_JOB`
(
    `ID_`                  varchar(64) COLLATE utf8_bin  NOT NULL,
    `REV_`                 int(11)                            DEFAULT NULL,
    `TYPE_`                varchar(255) COLLATE utf8_bin NOT NULL,
    `EXCLUSIVE_`           tinyint(1)                         DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `RETRIES_`             int(11)                            DEFAULT NULL,
    `EXCEPTION_STACK_ID_`  varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `EXCEPTION_MSG_`       varchar(4000) COLLATE utf8_bin     DEFAULT NULL,
    `DUEDATE_`             timestamp(3)                  NULL DEFAULT NULL,
    `REPEAT_`              varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `HANDLER_TYPE_`        varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `HANDLER_CFG_`         varchar(4000) COLLATE utf8_bin     DEFAULT NULL,
    `TENANT_ID_`           varchar(255) COLLATE utf8_bin      DEFAULT '',
    PRIMARY KEY (`ID_`),
    KEY `ACT_FK_SUSPENDED_JOB_EXECUTION` (`EXECUTION_ID_`),
    KEY `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
    KEY `ACT_FK_SUSPENDED_JOB_PROC_DEF` (`PROC_DEF_ID_`),
    KEY `ACT_FK_SUSPENDED_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
    CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
    CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RU_TASK definition

CREATE TABLE `ACT_RU_TASK`
(
    `ID_`               varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`              int(11)                           DEFAULT NULL,
    `EXECUTION_ID_`     varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `PROC_INST_ID_`     varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `PROC_DEF_ID_`      varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `NAME_`             varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `PARENT_TASK_ID_`   varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `DESCRIPTION_`      varchar(4000) COLLATE utf8_bin    DEFAULT NULL,
    `TASK_DEF_KEY_`     varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `OWNER_`            varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `ASSIGNEE_`         varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `DELEGATION_`       varchar(64) COLLATE utf8_bin      DEFAULT NULL,
    `PRIORITY_`         int(11)                           DEFAULT NULL,
    `CREATE_TIME_`      timestamp(3)                 NULL DEFAULT NULL,
    `DUE_DATE_`         datetime(3)                       DEFAULT NULL,
    `CATEGORY_`         varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `SUSPENSION_STATE_` int(11)                           DEFAULT NULL,
    `TENANT_ID_`        varchar(255) COLLATE utf8_bin     DEFAULT '',
    `FORM_KEY_`         varchar(255) COLLATE utf8_bin     DEFAULT NULL,
    `CLAIM_TIME_`       datetime(3)                       DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
    KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
    KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
    KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
    CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
    CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RU_TIMER_JOB definition

CREATE TABLE `ACT_RU_TIMER_JOB`
(
    `ID_`                  varchar(64) COLLATE utf8_bin  NOT NULL,
    `REV_`                 int(11)                            DEFAULT NULL,
    `TYPE_`                varchar(255) COLLATE utf8_bin NOT NULL,
    `LOCK_EXP_TIME_`       timestamp(3)                  NULL DEFAULT NULL,
    `LOCK_OWNER_`          varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `EXCLUSIVE_`           tinyint(1)                         DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `RETRIES_`             int(11)                            DEFAULT NULL,
    `EXCEPTION_STACK_ID_`  varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `EXCEPTION_MSG_`       varchar(4000) COLLATE utf8_bin     DEFAULT NULL,
    `DUEDATE_`             timestamp(3)                  NULL DEFAULT NULL,
    `REPEAT_`              varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `HANDLER_TYPE_`        varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `HANDLER_CFG_`         varchar(4000) COLLATE utf8_bin     DEFAULT NULL,
    `TENANT_ID_`           varchar(255) COLLATE utf8_bin      DEFAULT '',
    PRIMARY KEY (`ID_`),
    KEY `ACT_FK_TIMER_JOB_EXECUTION` (`EXECUTION_ID_`),
    KEY `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
    KEY `ACT_FK_TIMER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
    KEY `ACT_FK_TIMER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
    CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
    CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RU_VARIABLE definition

CREATE TABLE `ACT_RU_VARIABLE`
(
    `ID_`           varchar(64) COLLATE utf8_bin  NOT NULL,
    `REV_`          int(11)                        DEFAULT NULL,
    `TYPE_`         varchar(255) COLLATE utf8_bin NOT NULL,
    `NAME_`         varchar(255) COLLATE utf8_bin NOT NULL,
    `EXECUTION_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `TASK_ID_`      varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin   DEFAULT NULL,
    `DOUBLE_`       double                         DEFAULT NULL,
    `LONG_`         bigint(20)                     DEFAULT NULL,
    `TEXT_`         varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    `TEXT2_`        varchar(4000) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
    KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
    KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
    KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
    CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
    CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.qrtz_triggers definition

CREATE TABLE `qrtz_triggers`
(
    `sched_name`     varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `trigger_name`   varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '触发器的名字',
    `trigger_group`  varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '触发器所属组的名字',
    `job_name`       varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
    `job_group`      varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
    `description`    varchar(250) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '相关介绍',
    `next_fire_time` bigint(20)                       DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
    `prev_fire_time` bigint(20)                       DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
    `priority`       int(11)                          DEFAULT NULL COMMENT '优先级',
    `trigger_state`  varchar(16) COLLATE utf8mb4_bin  NOT NULL COMMENT '触发器状态',
    `trigger_type`   varchar(8) COLLATE utf8mb4_bin   NOT NULL COMMENT '触发器的类型',
    `start_time`     bigint(20)                       NOT NULL COMMENT '开始时间',
    `end_time`       bigint(20)                       DEFAULT NULL COMMENT '结束时间',
    `calendar_name`  varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日程表名称',
    `misfire_instr`  smallint(6)                      DEFAULT NULL COMMENT '补偿执行的策略',
    `job_data`       blob COMMENT '存放持久化job对象',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    KEY `sched_name` (`sched_name`, `job_name`, `job_group`),
    CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='触发器详细信息表';


-- melon.ACT_RU_DEADLETTER_JOB definition

CREATE TABLE `ACT_RU_DEADLETTER_JOB`
(
    `ID_`                  varchar(64) COLLATE utf8_bin  NOT NULL,
    `REV_`                 int(11)                            DEFAULT NULL,
    `TYPE_`                varchar(255) COLLATE utf8_bin NOT NULL,
    `EXCLUSIVE_`           tinyint(1)                         DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `EXCEPTION_STACK_ID_`  varchar(64) COLLATE utf8_bin       DEFAULT NULL,
    `EXCEPTION_MSG_`       varchar(4000) COLLATE utf8_bin     DEFAULT NULL,
    `DUEDATE_`             timestamp(3)                  NULL DEFAULT NULL,
    `REPEAT_`              varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `HANDLER_TYPE_`        varchar(255) COLLATE utf8_bin      DEFAULT NULL,
    `HANDLER_CFG_`         varchar(4000) COLLATE utf8_bin     DEFAULT NULL,
    `TENANT_ID_`           varchar(255) COLLATE utf8_bin      DEFAULT '',
    PRIMARY KEY (`ID_`),
    KEY `ACT_FK_DEADLETTER_JOB_EXECUTION` (`EXECUTION_ID_`),
    KEY `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
    KEY `ACT_FK_DEADLETTER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
    KEY `ACT_FK_DEADLETTER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
    CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
    CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RU_EVENT_SUBSCR definition

CREATE TABLE `ACT_RU_EVENT_SUBSCR`
(
    `ID_`            varchar(64) COLLATE utf8_bin  NOT NULL,
    `REV_`           int(11)                                DEFAULT NULL,
    `EVENT_TYPE_`    varchar(255) COLLATE utf8_bin NOT NULL,
    `EVENT_NAME_`    varchar(255) COLLATE utf8_bin          DEFAULT NULL,
    `EXECUTION_ID_`  varchar(64) COLLATE utf8_bin           DEFAULT NULL,
    `PROC_INST_ID_`  varchar(64) COLLATE utf8_bin           DEFAULT NULL,
    `ACTIVITY_ID_`   varchar(64) COLLATE utf8_bin           DEFAULT NULL,
    `CONFIGURATION_` varchar(255) COLLATE utf8_bin          DEFAULT NULL,
    `CREATED_`       timestamp(3)                  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `PROC_DEF_ID_`   varchar(64) COLLATE utf8_bin           DEFAULT NULL,
    `TENANT_ID_`     varchar(255) COLLATE utf8_bin          DEFAULT '',
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
    KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
    CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.ACT_RU_IDENTITYLINK definition

CREATE TABLE `ACT_RU_IDENTITYLINK`
(
    `ID_`           varchar(64) COLLATE utf8_bin NOT NULL,
    `REV_`          int(11)                       DEFAULT NULL,
    `GROUP_ID_`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `TYPE_`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `USER_ID_`      varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `TASK_ID_`      varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    `PROC_DEF_ID_`  varchar(64) COLLATE utf8_bin  DEFAULT NULL,
    PRIMARY KEY (`ID_`),
    KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
    KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
    KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
    KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
    KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
    CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
    CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
    CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `ACT_RU_TASK` (`ID_`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


-- melon.qrtz_blob_triggers definition

CREATE TABLE `qrtz_blob_triggers`
(
    `sched_name`    varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `trigger_name`  varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `blob_data`     blob COMMENT '存放持久化Trigger对象',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='Blob类型的触发器表';


-- melon.qrtz_cron_triggers definition

CREATE TABLE `qrtz_cron_triggers`
(
    `sched_name`      varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `trigger_name`    varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group`   varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `cron_expression` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'cron表达式',
    `time_zone_id`    varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时区',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='Cron类型的触发器表';


-- melon.qrtz_simple_triggers definition

CREATE TABLE `qrtz_simple_triggers`
(
    `sched_name`      varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `trigger_name`    varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group`   varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `repeat_count`    bigint(20)                       NOT NULL COMMENT '重复的次数统计',
    `repeat_interval` bigint(20)                       NOT NULL COMMENT '重复的间隔时间',
    `times_triggered` bigint(20)                       NOT NULL COMMENT '已经触发的次数',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='简单触发器的信息表';


-- melon.qrtz_simprop_triggers definition

CREATE TABLE `qrtz_simprop_triggers`
(
    `sched_name`    varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
    `trigger_name`  varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    `str_prop_1`    varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
    `str_prop_2`    varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
    `str_prop_3`    varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
    `int_prop_1`    int(11)                          DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
    `int_prop_2`    int(11)                          DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
    `long_prop_1`   bigint(20)                       DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
    `long_prop_2`   bigint(20)                       DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
    `dec_prop_1`    decimal(13, 4)                   DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
    `dec_prop_2`    decimal(13, 4)                   DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
    `bool_prop_1`   varchar(1) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
    `bool_prop_2`   varchar(1) COLLATE utf8mb4_bin   DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
    PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`),
    CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='同步机制的行锁表';