-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: melon
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table` (
                             `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
                             `table_name` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '表名称',
                             `table_comment` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '表描述',
                             `sub_table_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关联子表的表名',
                             `sub_table_fk_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '子表关联的外键名',
                             `class_name` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '实体类名称',
                             `tpl_category` varchar(200) COLLATE utf8mb4_bin DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
                             `package_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成包路径',
                             `module_name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成模块名',
                             `business_name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成业务名',
                             `function_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成功能名',
                             `function_author` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成功能作者',
                             `gen_type` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
                             `gen_path` varchar(200) COLLATE utf8mb4_bin DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
                             `options` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '其它生成选项',
                             `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                             PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table`
--

LOCK TABLES `gen_table` WRITE;
/*!40000 ALTER TABLE `gen_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS `gen_table_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table_column` (
                                    `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
                                    `table_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '归属表编号',
                                    `column_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '列名称',
                                    `column_comment` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '列描述',
                                    `column_type` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '列类型',
                                    `java_type` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'JAVA类型',
                                    `java_field` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'JAVA字段名',
                                    `is_pk` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否主键（1是）',
                                    `is_increment` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否自增（1是）',
                                    `is_required` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否必填（1是）',
                                    `is_insert` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否为插入字段（1是）',
                                    `is_edit` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否编辑字段（1是）',
                                    `is_list` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否列表字段（1是）',
                                    `is_query` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否查询字段（1是）',
                                    `query_type` varchar(200) COLLATE utf8mb4_bin DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
                                    `html_type` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
                                    `dict_type` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典类型',
                                    `sort` int DEFAULT NULL COMMENT '排序',
                                    `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                                    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                    PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_column`
--

LOCK TABLES `gen_table_column` WRITE;
/*!40000 ALTER TABLE `gen_table_column` DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_blob_triggers`
--

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_blob_triggers` (
                                      `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                      `trigger_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                      `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                      `blob_data` blob COMMENT '存放持久化Trigger对象',
                                      PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                      CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Blob类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_calendars` (
                                  `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                  `calendar_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '日历名称',
                                  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
                                  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='日历信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_cron_triggers` (
                                      `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                      `trigger_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                      `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                      `cron_expression` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'cron表达式',
                                      `time_zone_id` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时区',
                                      PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                      CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Cron类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_fired_triggers` (
                                       `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                       `entry_id` varchar(95) COLLATE utf8mb4_bin NOT NULL COMMENT '调度器实例id',
                                       `trigger_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                       `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                       `instance_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '调度器实例名',
                                       `fired_time` bigint NOT NULL COMMENT '触发的时间',
                                       `sched_time` bigint NOT NULL COMMENT '定时器制定的时间',
                                       `priority` int NOT NULL COMMENT '优先级',
                                       `state` varchar(16) COLLATE utf8mb4_bin NOT NULL COMMENT '状态',
                                       `job_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名称',
                                       `job_group` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务组名',
                                       `is_nonconcurrent` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否并发',
                                       `requests_recovery` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否接受恢复执行',
                                       PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='已触发的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_job_details` (
                                    `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                    `job_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '任务名称',
                                    `job_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '任务组名',
                                    `description` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '相关介绍',
                                    `job_class_name` varchar(250) COLLATE utf8mb4_bin NOT NULL COMMENT '执行任务类名称',
                                    `is_durable` varchar(1) COLLATE utf8mb4_bin NOT NULL COMMENT '是否持久化',
                                    `is_nonconcurrent` varchar(1) COLLATE utf8mb4_bin NOT NULL COMMENT '是否并发',
                                    `is_update_data` varchar(1) COLLATE utf8mb4_bin NOT NULL COMMENT '是否更新数据',
                                    `requests_recovery` varchar(1) COLLATE utf8mb4_bin NOT NULL COMMENT '是否接受恢复执行',
                                    `job_data` blob COMMENT '存放持久化job对象',
                                    PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='任务详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_locks` (
                              `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                              `lock_name` varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT '悲观锁名称',
                              PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='存储的悲观锁信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_paused_trigger_grps` (
                                            `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                            `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                            PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='暂停的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_scheduler_state` (
                                        `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                        `instance_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '实例名称',
                                        `last_checkin_time` bigint NOT NULL COMMENT '上次检查时间',
                                        `checkin_interval` bigint NOT NULL COMMENT '检查间隔时间',
                                        PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='调度器状态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simple_triggers` (
                                        `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                        `trigger_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                        `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                        `repeat_count` bigint NOT NULL COMMENT '重复的次数统计',
                                        `repeat_interval` bigint NOT NULL COMMENT '重复的间隔时间',
                                        `times_triggered` bigint NOT NULL COMMENT '已经触发的次数',
                                        PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                        CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='简单触发器的信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simprop_triggers`
--

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_simprop_triggers` (
                                         `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                         `trigger_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                         `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                         `str_prop_1` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
                                         `str_prop_2` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
                                         `str_prop_3` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
                                         `int_prop_1` int DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
                                         `int_prop_2` int DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
                                         `long_prop_1` bigint DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
                                         `long_prop_2` bigint DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
                                         `dec_prop_1` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
                                         `dec_prop_2` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
                                         `bool_prop_1` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
                                         `bool_prop_2` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
                                         PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                         CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='同步机制的行锁表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simprop_triggers`
--

LOCK TABLES `qrtz_simprop_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qrtz_triggers` (
                                 `sched_name` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '调度名称',
                                 `trigger_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '触发器的名字',
                                 `trigger_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '触发器所属组的名字',
                                 `job_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
                                 `job_group` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
                                 `description` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '相关介绍',
                                 `next_fire_time` bigint DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
                                 `prev_fire_time` bigint DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
                                 `priority` int DEFAULT NULL COMMENT '优先级',
                                 `trigger_state` varchar(16) COLLATE utf8mb4_bin NOT NULL COMMENT '触发器状态',
                                 `trigger_type` varchar(8) COLLATE utf8mb4_bin NOT NULL COMMENT '触发器的类型',
                                 `start_time` bigint NOT NULL COMMENT '开始时间',
                                 `end_time` bigint DEFAULT NULL COMMENT '结束时间',
                                 `calendar_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日程表名称',
                                 `misfire_instr` smallint DEFAULT NULL COMMENT '补偿执行的策略',
                                 `job_data` blob COMMENT '存放持久化job对象',
                                 PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                 KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
                                 CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='触发器详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config` (
                              `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
                              `config_name` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '参数名称',
                              `config_key` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '参数键名',
                              `config_value` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '参数键值',
                              `config_type` char(1) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
                              `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                              PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2023-01-30 13:46:10','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2023-01-30 13:46:10','',NULL,'初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2023-01-30 13:46:10','',NULL,'深色主题theme-dark，浅色主题theme-light'),(4,'账号自助-验证码开关','sys.account.captchaEnabled','false','Y','admin','2023-01-30 13:46:10','',NULL,'是否开启验证码功能（true开启，false关闭）'),(5,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2023-01-30 13:46:10','',NULL,'是否开启注册用户功能（true开启，false关闭）');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
                            `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
                            `parent_id` bigint DEFAULT '0' COMMENT '父部门id',
                            `ancestors` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '祖级列表',
                            `dept_name` varchar(30) COLLATE utf8mb4_bin DEFAULT '' COMMENT '部门名称',
                            `order_num` int DEFAULT '0' COMMENT '显示顺序',
                            `leader` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '负责人',
                            `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
                            `email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
                            `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
                            `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                            `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,0,'0','若依科技',0,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL),(101,100,'0,100','深圳总公司',1,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL),(102,100,'0,100','长沙分公司',2,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL),(103,101,'0,100,101','研发部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL),(104,101,'0,100,101','市场部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL),(105,101,'0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL),(106,101,'0,100,101','财务部门',4,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL),(107,101,'0,100,101','运维部门',5,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL),(108,102,'0,100,102','市场部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL),(109,102,'0,100,102','财务部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2023-01-30 13:46:09','',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_data` (
                                 `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
                                 `dict_sort` int DEFAULT '0' COMMENT '字典排序',
                                 `dict_label` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典标签',
                                 `dict_value` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典键值',
                                 `dict_type` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典类型',
                                 `css_class` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
                                 `list_class` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表格回显样式',
                                 `is_default` char(1) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
                                 `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,1,'男','0','sys_user_sex','','','Y','0','admin','2023-01-30 13:46:10','',NULL,'性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2023-01-30 13:46:10','',NULL,'性别女'),(3,3,'未知','2','sys_user_sex','','','N','0','admin','2023-01-30 13:46:10','',NULL,'性别未知'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2023-01-30 13:46:10','',NULL,'显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2023-01-30 13:46:10','',NULL,'隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2023-01-30 13:46:10','',NULL,'正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2023-01-30 13:46:10','',NULL,'停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2023-01-30 13:46:10','',NULL,'正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2023-01-30 13:46:10','',NULL,'停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2023-01-30 13:46:10','',NULL,'默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2023-01-30 13:46:10','',NULL,'系统分组'),(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2023-01-30 13:46:10','',NULL,'系统默认是'),(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2023-01-30 13:46:10','',NULL,'系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2023-01-30 13:46:10','',NULL,'通知'),(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2023-01-30 13:46:10','',NULL,'公告'),(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2023-01-30 13:46:10','',NULL,'正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2023-01-30 13:46:10','',NULL,'关闭状态'),(18,99,'其他','0','sys_oper_type','','info','N','0','admin','2023-01-30 13:46:10','',NULL,'其他操作'),(19,1,'新增','1','sys_oper_type','','info','N','0','admin','2023-01-30 13:46:10','',NULL,'新增操作'),(20,2,'修改','2','sys_oper_type','','info','N','0','admin','2023-01-30 13:46:10','',NULL,'修改操作'),(21,3,'删除','3','sys_oper_type','','danger','N','0','admin','2023-01-30 13:46:10','',NULL,'删除操作'),(22,4,'授权','4','sys_oper_type','','primary','N','0','admin','2023-01-30 13:46:10','',NULL,'授权操作'),(23,5,'导出','5','sys_oper_type','','warning','N','0','admin','2023-01-30 13:46:10','',NULL,'导出操作'),(24,6,'导入','6','sys_oper_type','','warning','N','0','admin','2023-01-30 13:46:10','',NULL,'导入操作'),(25,7,'强退','7','sys_oper_type','','danger','N','0','admin','2023-01-30 13:46:10','',NULL,'强退操作'),(26,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2023-01-30 13:46:10','',NULL,'生成操作'),(27,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2023-01-30 13:46:10','',NULL,'清空操作'),(28,1,'成功','0','sys_common_status','','primary','N','0','admin','2023-01-30 13:46:10','',NULL,'正常状态'),(29,2,'失败','1','sys_common_status','','danger','N','0','admin','2023-01-30 13:46:10','',NULL,'停用状态');
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_type` (
                                 `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
                                 `dict_name` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典名称',
                                 `dict_type` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '字典类型',
                                 `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`dict_id`),
                                 UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'用户性别','sys_user_sex','0','admin','2023-01-30 13:46:10','',NULL,'用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2023-01-30 13:46:10','',NULL,'菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2023-01-30 13:46:10','',NULL,'系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2023-01-30 13:46:10','',NULL,'任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2023-01-30 13:46:10','',NULL,'任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2023-01-30 13:46:10','',NULL,'系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2023-01-30 13:46:10','',NULL,'通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2023-01-30 13:46:10','',NULL,'通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2023-01-30 13:46:10','',NULL,'操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2023-01-30 13:46:10','',NULL,'登录状态列表');
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job` (
                           `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
                           `job_name` varchar(64) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '任务名称',
                           `job_group` varchar(64) COLLATE utf8mb4_bin NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
                           `invoke_target` varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '调用目标字符串',
                           `cron_expression` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'cron执行表达式',
                           `misfire_policy` varchar(20) COLLATE utf8mb4_bin DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
                           `concurrent` char(1) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
                           `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态（0正常 1暂停）',
                           `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                           `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                           `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                           `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注信息',
                           PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2023-01-30 13:46:10','',NULL,''),(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2023-01-30 13:46:10','',NULL,''),(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2023-01-30 13:46:10','',NULL,'');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job_log` (
                               `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
                               `job_name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '任务名称',
                               `job_group` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '任务组名',
                               `invoke_target` varchar(500) COLLATE utf8mb4_bin NOT NULL COMMENT '调用目标字符串',
                               `job_message` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日志信息',
                               `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
                               `exception_info` varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '异常信息',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_logininfor` (
                                  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
                                  `user_name` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户账号',
                                  `ipaddr` varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT '登录IP地址',
                                  `login_location` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '登录地点',
                                  `browser` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '浏览器类型',
                                  `os` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '操作系统',
                                  `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
                                  `msg` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '提示消息',
                                  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
                                  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` VALUES (100,'admin','127.0.0.1','内网IP','Chrome 10','Windows 10','0','登录成功','2023-01-30 15:13:35'),(101,'admin','127.0.0.1','内网IP','Chrome 10','Windows 10','0','登录成功','2023-02-13 14:59:33'),(102,'admin','127.0.0.1','内网IP','Chrome 10','Windows 10','0','登录成功','2023-02-13 15:43:34'),(103,'','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户： 不存在','2023-03-16 14:45:58'),(104,'','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户： 不存在','2023-03-16 14:52:26'),(105,'','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户： 不存在','2023-03-16 14:55:09'),(106,'','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户： 不存在','2023-03-16 14:55:12'),(107,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-16 15:00:17'),(108,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-16 15:35:52'),(109,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-16 15:38:36'),(110,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-16 16:33:11'),(111,'','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户： 不存在','2023-03-17 11:09:43'),(112,'','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户： 不存在','2023-03-17 11:10:41'),(113,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-17 11:10:49'),(114,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-17 11:10:55'),(115,'','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户： 不存在','2023-03-17 11:10:58'),(116,'','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户： 不存在','2023-03-17 11:12:58'),(117,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户：SSSDNSY 不存在','2023-03-17 11:13:10'),(118,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户：SSSDNSY 不存在','2023-03-17 11:14:41'),(119,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','登录用户：SSSDNSY 不存在','2023-03-17 11:22:09'),(120,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','注册成功','2023-03-17 13:42:27'),(121,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','密码输入错误1次','2023-03-17 13:42:27'),(122,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','用户不存在/密码错误','2023-03-17 13:42:27'),(123,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','密码输入错误2次','2023-03-17 13:42:54'),(124,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','用户不存在/密码错误','2023-03-17 13:42:54'),(125,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','用户不存在/密码错误','2023-03-17 13:48:26'),(126,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','密码输入错误3次','2023-03-17 13:48:26'),(127,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','密码输入错误4次','2023-03-17 13:49:38'),(128,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','用户不存在/密码错误','2023-03-17 13:49:38'),(129,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','注册成功','2023-03-17 13:51:15'),(130,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','用户不存在/密码错误','2023-03-17 13:51:15'),(131,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','密码输入错误5次','2023-03-17 13:51:15'),(132,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','注册成功','2023-03-17 13:57:59'),(133,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','密码输入错误5次，帐户锁定10分钟','2023-03-17 13:57:59'),(134,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','1','密码输入错误5次，帐户锁定10分钟','2023-03-17 13:57:59'),(135,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-17 14:01:41'),(136,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-17 14:03:34'),(137,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-17 14:04:20'),(138,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-17 14:22:45'),(139,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-17 14:25:55'),(140,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-17 14:34:08'),(141,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 08:38:49'),(142,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-20 09:04:09'),(143,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:09:33'),(144,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:11:14'),(145,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:14:33'),(146,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:23:39'),(147,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-20 09:23:51'),(148,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:27:09'),(149,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:28:02'),(150,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:28:10'),(151,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:30:07'),(152,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-20 09:30:13'),(153,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:30:33'),(154,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:37:01'),(155,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 09:37:38'),(156,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 10:04:28'),(157,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 10:11:53'),(158,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 10:14:22'),(159,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 10:14:34'),(160,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 10:33:23'),(161,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-20 10:33:27'),(162,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 10:36:52'),(163,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 10:37:36'),(164,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 17:09:05'),(165,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 17:11:39'),(166,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-20 17:32:44'),(167,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:06:12'),(168,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:09:52'),(169,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:10:21'),(170,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:13:15'),(171,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:22:00'),(172,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:25:10'),(173,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:28:19'),(174,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:30:04'),(175,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-21 19:31:36'),(176,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:31:46'),(177,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-21 19:32:04'),(178,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-21 19:32:32'),(179,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-22 09:11:11'),(180,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-22 09:54:51'),(181,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-24 09:27:17'),(182,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-24 09:29:21'),(183,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-24 09:29:25'),(184,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-24 09:41:45'),(185,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-24 09:41:48'),(186,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-27 09:13:36'),(187,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-27 11:07:03'),(188,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-27 15:50:58'),(189,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-27 16:17:45'),(190,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-27 16:17:53'),(191,'admin','127.0.0.1','内网IP','Chrome 11','Windows 10','0','退出成功','2023-03-27 16:24:26'),(192,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-27 16:24:34'),(193,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-30 15:15:38'),(194,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-30 17:07:41'),(195,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-31 16:19:03'),(196,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-31 16:19:16'),(197,'SSSDNSY','127.0.0.1','内网IP','Chrome 11','Windows 10','0','登录成功','2023-03-31 16:21:17');
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
                            `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                            `menu_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
                            `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
                            `order_num` int DEFAULT '0' COMMENT '显示顺序',
                            `path` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '路由地址',
                            `component` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件路径',
                            `query` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '路由参数',
                            `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
                            `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
                            `menu_type` char(1) COLLATE utf8mb4_bin DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
                            `visible` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
                            `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
                            `perms` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限标识',
                            `icon` varchar(100) COLLATE utf8mb4_bin DEFAULT '#' COMMENT '菜单图标',
                            `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
                            PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2015 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,'',1,0,'M','0','0','','system','admin','2023-01-30 13:46:09','',NULL,'系统管理目录'),(2,'系统监控',0,2,'monitor',NULL,'',1,0,'M','0','0','','monitor','admin','2023-01-30 13:46:09','',NULL,'系统监控目录'),(3,'系统工具',0,3,'tool',NULL,'',1,0,'M','0','0','','tool','admin','2023-01-30 13:46:09','',NULL,'系统工具目录'),(4,'若依官网',0,0,'http://ruoyi.vip',NULL,'',0,0,'M','0','0','','guide','admin','2023-01-30 13:46:09','SSSDNSY','2023-03-24 09:28:59','若依官网地址'),(100,'用户管理',1,1,'user','system/user/index','',1,0,'C','0','0','system:user:list','user','admin','2023-01-30 13:46:09','',NULL,'用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index','',1,0,'C','0','0','system:role:list','peoples','admin','2023-01-30 13:46:09','',NULL,'角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index','',1,0,'C','0','0','system:menu:list','tree-table','admin','2023-01-30 13:46:09','',NULL,'菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index','',1,0,'C','0','0','system:dept:list','tree','admin','2023-01-30 13:46:09','',NULL,'部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index','',1,0,'C','0','0','system:post:list','post','admin','2023-01-30 13:46:09','',NULL,'岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index','',1,0,'C','0','0','system:dict:list','dict','admin','2023-01-30 13:46:09','',NULL,'字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index','',1,0,'C','0','0','system:config:list','edit','admin','2023-01-30 13:46:09','',NULL,'参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index','',1,0,'C','0','0','system:notice:list','message','admin','2023-01-30 13:46:09','',NULL,'通知公告菜单'),(108,'日志管理',1,9,'log','','',1,0,'M','0','0','','log','admin','2023-01-30 13:46:09','',NULL,'日志管理菜单'),(109,'在线用户',2,1,'online','monitor/online/index','',1,0,'C','0','0','monitor:online:list','online','admin','2023-01-30 13:46:09','',NULL,'在线用户菜单'),(110,'定时任务',2,2,'job','monitor/job/index','',1,0,'C','0','0','monitor:job:list','job','admin','2023-01-30 13:46:09','',NULL,'定时任务菜单'),(111,'数据监控',2,3,'druid','monitor/druid/index','',1,0,'C','0','0','monitor:druid:list','druid','admin','2023-01-30 13:46:09','',NULL,'数据监控菜单'),(112,'服务监控',2,4,'server','monitor/server/index','',1,0,'C','0','0','monitor:server:list','server','admin','2023-01-30 13:46:09','',NULL,'服务监控菜单'),(113,'缓存监控',2,5,'cache','monitor/cache/index','',1,0,'C','0','0','monitor:cache:list','redis','admin','2023-01-30 13:46:09','',NULL,'缓存监控菜单'),(114,'缓存列表',2,6,'cacheList','monitor/cache/list','',1,0,'C','0','0','monitor:cache:list','redis-list','admin','2023-01-30 13:46:09','',NULL,'缓存列表菜单'),(115,'表单构建',3,1,'build','tool/build/index','',1,0,'C','0','0','tool:build:list','build','admin','2023-01-30 13:46:09','',NULL,'表单构建菜单'),(116,'代码生成',3,2,'gen','tool/gen/index','',1,0,'C','0','0','tool:gen:list','code','admin','2023-01-30 13:46:09','',NULL,'代码生成菜单'),(117,'系统接口',3,3,'swagger','tool/swagger/index','',1,0,'C','0','0','tool:swagger:list','swagger','admin','2023-01-30 13:46:09','',NULL,'系统接口菜单'),(500,'操作日志',108,1,'operlog','monitor/operlog/index','',1,0,'C','0','0','monitor:operlog:list','form','admin','2023-01-30 13:46:09','',NULL,'操作日志菜单'),(501,'登录日志',108,2,'logininfor','monitor/logininfor/index','',1,0,'C','0','0','monitor:logininfor:list','logininfor','admin','2023-01-30 13:46:09','',NULL,'登录日志菜单'),(1000,'用户查询',100,1,'','','',1,0,'F','0','0','system:user:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1001,'用户新增',100,2,'','','',1,0,'F','0','0','system:user:add','#','admin','2023-01-30 13:46:09','',NULL,''),(1002,'用户修改',100,3,'','','',1,0,'F','0','0','system:user:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1003,'用户删除',100,4,'','','',1,0,'F','0','0','system:user:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1004,'用户导出',100,5,'','','',1,0,'F','0','0','system:user:export','#','admin','2023-01-30 13:46:09','',NULL,''),(1005,'用户导入',100,6,'','','',1,0,'F','0','0','system:user:import','#','admin','2023-01-30 13:46:09','',NULL,''),(1006,'重置密码',100,7,'','','',1,0,'F','0','0','system:user:resetPwd','#','admin','2023-01-30 13:46:09','',NULL,''),(1007,'角色查询',101,1,'','','',1,0,'F','0','0','system:role:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1008,'角色新增',101,2,'','','',1,0,'F','0','0','system:role:add','#','admin','2023-01-30 13:46:09','',NULL,''),(1009,'角色修改',101,3,'','','',1,0,'F','0','0','system:role:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1010,'角色删除',101,4,'','','',1,0,'F','0','0','system:role:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1011,'角色导出',101,5,'','','',1,0,'F','0','0','system:role:export','#','admin','2023-01-30 13:46:09','',NULL,''),(1012,'菜单查询',102,1,'','','',1,0,'F','0','0','system:menu:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1013,'菜单新增',102,2,'','','',1,0,'F','0','0','system:menu:add','#','admin','2023-01-30 13:46:09','',NULL,''),(1014,'菜单修改',102,3,'','','',1,0,'F','0','0','system:menu:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1015,'菜单删除',102,4,'','','',1,0,'F','0','0','system:menu:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1016,'部门查询',103,1,'','','',1,0,'F','0','0','system:dept:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1017,'部门新增',103,2,'','','',1,0,'F','0','0','system:dept:add','#','admin','2023-01-30 13:46:09','',NULL,''),(1018,'部门修改',103,3,'','','',1,0,'F','0','0','system:dept:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1019,'部门删除',103,4,'','','',1,0,'F','0','0','system:dept:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1020,'岗位查询',104,1,'','','',1,0,'F','0','0','system:post:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1021,'岗位新增',104,2,'','','',1,0,'F','0','0','system:post:add','#','admin','2023-01-30 13:46:09','',NULL,''),(1022,'岗位修改',104,3,'','','',1,0,'F','0','0','system:post:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1023,'岗位删除',104,4,'','','',1,0,'F','0','0','system:post:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1024,'岗位导出',104,5,'','','',1,0,'F','0','0','system:post:export','#','admin','2023-01-30 13:46:09','',NULL,''),(1025,'字典查询',105,1,'#','','',1,0,'F','0','0','system:dict:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1026,'字典新增',105,2,'#','','',1,0,'F','0','0','system:dict:add','#','admin','2023-01-30 13:46:09','',NULL,''),(1027,'字典修改',105,3,'#','','',1,0,'F','0','0','system:dict:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1028,'字典删除',105,4,'#','','',1,0,'F','0','0','system:dict:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1029,'字典导出',105,5,'#','','',1,0,'F','0','0','system:dict:export','#','admin','2023-01-30 13:46:09','',NULL,''),(1030,'参数查询',106,1,'#','','',1,0,'F','0','0','system:config:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1031,'参数新增',106,2,'#','','',1,0,'F','0','0','system:config:add','#','admin','2023-01-30 13:46:09','',NULL,''),(1032,'参数修改',106,3,'#','','',1,0,'F','0','0','system:config:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1033,'参数删除',106,4,'#','','',1,0,'F','0','0','system:config:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1034,'参数导出',106,5,'#','','',1,0,'F','0','0','system:config:export','#','admin','2023-01-30 13:46:09','',NULL,''),(1035,'公告查询',107,1,'#','','',1,0,'F','0','0','system:notice:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1036,'公告新增',107,2,'#','','',1,0,'F','0','0','system:notice:add','#','admin','2023-01-30 13:46:09','',NULL,''),(1037,'公告修改',107,3,'#','','',1,0,'F','0','0','system:notice:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1038,'公告删除',107,4,'#','','',1,0,'F','0','0','system:notice:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1039,'操作查询',500,1,'#','','',1,0,'F','0','0','monitor:operlog:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1040,'操作删除',500,2,'#','','',1,0,'F','0','0','monitor:operlog:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1041,'日志导出',500,3,'#','','',1,0,'F','0','0','monitor:operlog:export','#','admin','2023-01-30 13:46:09','',NULL,''),(1042,'登录查询',501,1,'#','','',1,0,'F','0','0','monitor:logininfor:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1043,'登录删除',501,2,'#','','',1,0,'F','0','0','monitor:logininfor:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1044,'日志导出',501,3,'#','','',1,0,'F','0','0','monitor:logininfor:export','#','admin','2023-01-30 13:46:09','',NULL,''),(1045,'账户解锁',501,4,'#','','',1,0,'F','0','0','monitor:logininfor:unlock','#','admin','2023-01-30 13:46:09','',NULL,''),(1046,'在线查询',109,1,'#','','',1,0,'F','0','0','monitor:online:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1047,'批量强退',109,2,'#','','',1,0,'F','0','0','monitor:online:batchLogout','#','admin','2023-01-30 13:46:09','',NULL,''),(1048,'单条强退',109,3,'#','','',1,0,'F','0','0','monitor:online:forceLogout','#','admin','2023-01-30 13:46:09','',NULL,''),(1049,'任务查询',110,1,'#','','',1,0,'F','0','0','monitor:job:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1050,'任务新增',110,2,'#','','',1,0,'F','0','0','monitor:job:add','#','admin','2023-01-30 13:46:09','',NULL,''),(1051,'任务修改',110,3,'#','','',1,0,'F','0','0','monitor:job:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1052,'任务删除',110,4,'#','','',1,0,'F','0','0','monitor:job:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1053,'状态修改',110,5,'#','','',1,0,'F','0','0','monitor:job:changeStatus','#','admin','2023-01-30 13:46:09','',NULL,''),(1054,'任务导出',110,6,'#','','',1,0,'F','0','0','monitor:job:export','#','admin','2023-01-30 13:46:09','',NULL,''),(1055,'生成查询',116,1,'#','','',1,0,'F','0','0','tool:gen:query','#','admin','2023-01-30 13:46:09','',NULL,''),(1056,'生成修改',116,2,'#','','',1,0,'F','0','0','tool:gen:edit','#','admin','2023-01-30 13:46:09','',NULL,''),(1057,'生成删除',116,3,'#','','',1,0,'F','0','0','tool:gen:remove','#','admin','2023-01-30 13:46:09','',NULL,''),(1058,'导入代码',116,4,'#','','',1,0,'F','0','0','tool:gen:import','#','admin','2023-01-30 13:46:09','',NULL,''),(1059,'预览代码',116,5,'#','','',1,0,'F','0','0','tool:gen:preview','#','admin','2023-01-30 13:46:09','',NULL,''),(1060,'生成代码',116,6,'#','','',1,0,'F','0','0','tool:gen:code','#','admin','2023-01-30 13:46:09','',NULL,''),(2000,'配置管理',0,4,'config','','',1,0,'M','0','0','','component','admin','2023-01-25 12:00:23','admin','2023-01-25 13:18:04',''),(2001,'配置列表',2000,1,'config','config/conf/index','',1,0,'C','0','0','config:project:list','code','admin','2023-01-25 12:02:03','SSSDNSY','2023-03-27 16:09:34',''),(2002,'环境管理',2000,2,'env','config/env/index','',1,0,'C','0','0','','swagger','admin','2023-01-25 13:13:34','admin','2023-01-25 13:14:35',''),(2003,'项目管理',2000,3,'project','config/project/index','',1,0,'C','0','0','','example','admin','2023-01-25 13:14:09','',NULL,''),(2004,'编辑权限',2001,1,'',NULL,NULL,1,0,'F','0','0','config:conf:edit','#','SSSDNSY','2023-03-27 16:08:02','',NULL,''),(2005,'导出权限',2001,2,'',NULL,NULL,1,0,'F','0','0','config:conf:export','#','SSSDNSY','2023-03-27 16:08:48','',NULL,''),(2008,'查询权限',2001,3,'',NULL,NULL,1,0,'F','0','0','config:conf:list','#','admin','2023-03-27 16:18:27','',NULL,''),(2009,'编辑权限',2002,1,'',NULL,NULL,1,0,'F','0','0','config:env:edit','#','SSSDNSY','2023-03-27 16:08:02','',NULL,''),(2010,'导出权限',2002,2,'',NULL,NULL,1,0,'F','0','0','config:env:export','#','SSSDNSY','2023-03-27 16:08:48','',NULL,''),(2011,'查询权限',2002,3,'',NULL,NULL,1,0,'F','0','0','config:env:list','#','admin','2023-03-27 16:18:27','',NULL,''),(2012,'编辑权限',2003,1,'',NULL,NULL,1,0,'F','0','0','config:project:edit','#','SSSDNSY','2023-03-27 16:08:02','',NULL,''),(2013,'导出权限',2003,2,'',NULL,NULL,1,0,'F','0','0','config:project:export','#','SSSDNSY','2023-03-27 16:08:48','',NULL,''),(2014,'查询权限',2003,3,'',NULL,NULL,1,0,'F','0','0','config:project:list','#','admin','2023-03-27 16:18:27','',NULL,'');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice` (
                              `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
                              `notice_title` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '公告标题',
                              `notice_type` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '公告类型（1通知 2公告）',
                              `notice_content` longblob COMMENT '公告内容',
                              `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
                              `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                              PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'温馨提醒：2018-07-01 若依新版本发布啦','2',_binary '新版本内容','0','admin','2023-01-30 13:46:10','',NULL,'管理员'),(2,'维护通知：2018-07-01 若依系统凌晨维护','1',_binary '维护内容','0','admin','2023-01-30 13:46:10','',NULL,'管理员'),(10,'阿斯蒂芬','1',_binary '<p>阿斯顿发斯蒂芬</p>','0','admin','2023-03-24 09:35:14','',NULL,NULL),(11,'阿斯蒂芬阿斯蒂芬','2',_binary '<p>阿斯顿发斯蒂芬</p>','0','admin','2023-03-24 09:35:39','',NULL,NULL);
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oper_log` (
                                `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
                                `title` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '模块标题',
                                `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
                                `method` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '方法名称',
                                `request_method` varchar(10) COLLATE utf8mb4_bin DEFAULT '' COMMENT '请求方式',
                                `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
                                `oper_name` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '操作人员',
                                `dept_name` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '部门名称',
                                `oper_url` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '请求URL',
                                `oper_ip` varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT '主机地址',
                                `oper_location` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '操作地点',
                                `oper_param` varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '请求参数',
                                `json_result` varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '返回参数',
                                `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
                                `error_msg` varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '错误消息',
                                `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
                                PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` VALUES (100,'菜单管理',3,'fun.sssdnsy.web.controller.system.SysMenuController.edit()','PUT',1,'SSSDNSY',NULL,'/melon/system/menu','127.0.0.1','内网IP','{\"children\":[],\"createTime\":\"2023-01-30 13:46:09\",\"icon\":\"guide\",\"isCache\":\"0\",\"isFrame\":\"0\",\"menuId\":4,\"menuName\":\"若依官网\",\"menuType\":\"M\",\"orderNum\":0,\"params\":{},\"parentId\":0,\"path\":\"http://ruoyi.vip\",\"perms\":\"\",\"query\":\"\",\"status\":\"0\",\"updateBy\":\"SSSDNSY\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-24 09:28:59'),(101,'通知公告',2,'fun.sssdnsy.web.controller.system.SysNoticeController.add()','POST',1,'admin',NULL,'/melon/system/notice','127.0.0.1','内网IP','{\"createBy\":\"admin\",\"noticeContent\":\"<p>阿斯顿发斯蒂芬</p>\",\"noticeTitle\":\"阿斯蒂芬\",\"noticeType\":\"1\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-24 09:35:14'),(102,'通知公告',2,'fun.sssdnsy.web.controller.system.SysNoticeController.add()','POST',1,'admin',NULL,'/melon/system/notice','127.0.0.1','内网IP','{\"createBy\":\"admin\",\"noticeContent\":\"<p>阿斯顿发斯蒂芬</p>\",\"noticeTitle\":\"阿斯蒂芬阿斯蒂芬\",\"noticeType\":\"2\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-24 09:35:39'),(103,'角色管理',3,'fun.sssdnsy.web.controller.system.SysRoleController.edit()','PUT',1,'admin',NULL,'/melon/system/role','127.0.0.1','内网IP','{\"admin\":false,\"createTime\":\"2023-01-30 13:46:09\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"deptCheckStrictly\":true,\"flag\":false,\"menuCheckStrictly\":true,\"menuIds\":[4,1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,501,1042,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,114,3,115,116,1055,1056,1057,1058,1059,1060,117,2000,2001,2002,2003],\"params\":{},\"remark\":\"普通角色\",\"roleId\":2,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"roleSort\":2,\"status\":\"0\",\"updateBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-24 09:41:32'),(104,'参数管理',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"1\",\"env\":\"1\",\"key\":\"1\",\"params\":{},\"title\":\"1\",\"value\":\"1\"}',NULL,1,'','2023-03-27 10:00:39'),(105,'用户管理',3,'fun.sssdnsy.web.controller.system.SysUserController.edit()','PUT',1,'SSSDNSY',NULL,'/melon/system/user','127.0.0.1','内网IP','{\"admin\":false,\"avatar\":\"https://foruda.gitee.com/avatar/1676975810888864595/1553796_sssdnsy_1658297241.png\",\"createBy\":\"sys\",\"createTime\":\"2023-03-17 13:57:59\",\"delFlag\":\"0\",\"email\":\"sssdnsy@163.com\",\"loginDate\":\"2023-03-27 15:50:59\",\"loginIp\":\"127.0.0.1\",\"nickName\":\"SSSDNSY\",\"params\":{},\"phonenumber\":\"\",\"postIds\":[4],\"remark\":\"1\",\"roleIds\":[],\"roles\":[{\"admin\":true,\"dataScope\":\"1\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"roleId\":1,\"roleKey\":\"admin\",\"roleName\":\"超级管理员\",\"roleSort\":1,\"status\":\"0\"},{\"admin\":false,\"dataScope\":\"2\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"roleId\":2,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"roleSort\":2,\"status\":\"0\"}],\"sex\":\"0\",\"status\":\"0\",\"updateBy\":\"SSSDNSY\",\"userId\":1553796,\"userName\":\"SSSDNSY\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 15:51:52'),(106,'角色管理',3,'fun.sssdnsy.web.controller.system.SysRoleController.edit()','PUT',1,'SSSDNSY',NULL,'/melon/system/role','127.0.0.1','内网IP','{\"admin\":false,\"createTime\":\"2023-01-30 13:46:09\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"deptCheckStrictly\":true,\"flag\":false,\"menuCheckStrictly\":true,\"menuIds\":[4,1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,501,1042,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,114,3,115,116,1055,1056,1057,1058,1059,1060,117,2000,2001,2002,2003],\"params\":{},\"remark\":\"普通角色\",\"roleId\":2,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"roleSort\":2,\"status\":\"0\",\"updateBy\":\"SSSDNSY\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 15:59:14'),(107,'菜单管理',2,'fun.sssdnsy.web.controller.system.SysMenuController.add()','POST',1,'SSSDNSY',NULL,'/melon/system/menu','127.0.0.1','内网IP','{\"children\":[],\"createBy\":\"SSSDNSY\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"编辑权限\",\"menuType\":\"F\",\"orderNum\":1,\"params\":{},\"parentId\":2001,\"perms\":\"config:project:edit\",\"status\":\"0\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:08:02'),(108,'菜单管理',2,'fun.sssdnsy.web.controller.system.SysMenuController.add()','POST',1,'SSSDNSY',NULL,'/melon/system/menu','127.0.0.1','内网IP','{\"children\":[],\"createBy\":\"SSSDNSY\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"导出权限\",\"menuType\":\"F\",\"orderNum\":2,\"params\":{},\"parentId\":2001,\"perms\":\"config:project:export\",\"status\":\"0\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:08:48'),(109,'菜单管理',3,'fun.sssdnsy.web.controller.system.SysMenuController.edit()','PUT',1,'SSSDNSY',NULL,'/melon/system/menu','127.0.0.1','内网IP','{\"children\":[],\"component\":\"config/conf/index\",\"createTime\":\"2023-01-25 12:02:03\",\"icon\":\"code\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2001,\"menuName\":\"配置列表\",\"menuType\":\"C\",\"orderNum\":1,\"params\":{},\"parentId\":2000,\"path\":\"config\",\"perms\":\"config:project:list\",\"query\":\"\",\"status\":\"0\",\"updateBy\":\"SSSDNSY\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:09:34'),(110,'菜单管理',2,'fun.sssdnsy.web.controller.system.SysMenuController.add()','POST',1,'SSSDNSY',NULL,'/melon/system/menu','127.0.0.1','内网IP','{\"children\":[],\"createBy\":\"SSSDNSY\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"导出\",\"menuType\":\"F\",\"orderNum\":1,\"params\":{},\"parentId\":2001,\"perms\":\"config:project:export\",\"status\":\"0\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:10:22'),(111,'菜单管理',2,'fun.sssdnsy.web.controller.system.SysMenuController.add()','POST',1,'SSSDNSY',NULL,'/melon/system/menu','127.0.0.1','内网IP','{\"children\":[],\"createBy\":\"SSSDNSY\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"1\",\"menuType\":\"F\",\"orderNum\":1,\"params\":{},\"parentId\":117,\"perms\":\"1\",\"status\":\"0\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:16:01'),(112,'菜单管理',2,'fun.sssdnsy.web.controller.system.SysMenuController.add()','POST',1,'admin',NULL,'/melon/system/menu','127.0.0.1','内网IP','{\"children\":[],\"createBy\":\"admin\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"查询权限\",\"menuType\":\"F\",\"orderNum\":3,\"params\":{},\"parentId\":2001,\"perms\":\"config:project:list\",\"status\":\"0\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:18:27'),(113,'菜单管理',4,'fun.sssdnsy.web.controller.system.SysMenuController.remove()','DELETE',1,'admin',NULL,'/melon/system/menu/2007','127.0.0.1','内网IP','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:18:35'),(114,'角色管理',3,'fun.sssdnsy.web.controller.system.SysRoleController.edit()','PUT',1,'admin',NULL,'/melon/system/role','127.0.0.1','内网IP','{\"admin\":false,\"createTime\":\"2023-01-30 13:46:09\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"deptCheckStrictly\":true,\"flag\":false,\"menuCheckStrictly\":true,\"menuIds\":[4,1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,501,1042,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,114,3,115,116,1055,1056,1057,1058,1059,1060,117,2000,2001,2004,2005,2008,2002,2003],\"params\":{},\"remark\":\"普通角色\",\"roleId\":2,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"roleSort\":2,\"status\":\"0\",\"updateBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:20:33'),(115,'角色管理',3,'fun.sssdnsy.web.controller.system.SysRoleController.edit()','PUT',1,'admin',NULL,'/melon/system/role','127.0.0.1','内网IP','{\"admin\":false,\"createTime\":\"2023-01-30 13:46:09\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"deptCheckStrictly\":true,\"flag\":false,\"menuCheckStrictly\":true,\"menuIds\":[4,1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,501,1042,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,114,3,115,116,1055,1056,1057,1058,1059,1060,117,2000,2001,2004,2005,2008,2002,2009,2010,2011,2003,2012,2013,2014],\"params\":{},\"remark\":\"普通角色\",\"roleId\":2,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"roleSort\":2,\"status\":\"0\",\"updateBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:24:22'),(116,'删除配置项',4,'fun.sssdnsy.web.controller.config.ConfController.remove()','DELETE',1,'SSSDNSY',NULL,'/melon/config/conf/test/default.key01','127.0.0.1','内网IP','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-27 16:50:18'),(117,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"1\",\"env\":\"1\",\"key\":\"21\",\"params\":{},\"title\":\"1\",\"value\":\"2\"}',NULL,1,'','2023-03-27 17:00:23'),(118,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"1\",\"env\":\"1\",\"key\":\"21\",\"params\":{},\"title\":\"1\",\"value\":\"2\"}',NULL,1,'','2023-03-27 17:01:00'),(119,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"1\",\"env\":\"1\",\"key\":\"21\",\"params\":{},\"title\":\"1\",\"value\":\"2\"}',NULL,1,'','2023-03-27 17:01:13'),(120,'保存环境配置',1,'fun.sssdnsy.web.controller.config.EnvController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/env','127.0.0.1','内网IP','{\"env\":\"dev\",\"order\":4,\"params\":{},\"title\":\"开发环境\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-30 15:16:18'),(121,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"env\":\"dev\",\"key\":\"a\",\"params\":{},\"title\":\"水电费\",\"value\":\"1\"}',NULL,1,'','2023-03-30 16:37:51'),(122,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"env\":\"dev\",\"key\":\"a\",\"params\":{},\"title\":\"水电费\",\"value\":\"1\"}',NULL,1,'','2023-03-30 16:38:23'),(123,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"env\":\"dev\",\"key\":\"a\",\"params\":{},\"title\":\"水电费\",\"value\":\"1\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-30 16:38:37'),(124,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"env\":\"dev\",\"key\":\"b\",\"params\":{},\"title\":\"1\",\"value\":\"2\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-30 17:08:12'),(125,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"createTime\":\"2023-03-30 16:38:37\",\"env\":\"dev\",\"key\":\"a\",\"logList\":[{\"createTime\":\"2023-03-30 16:38:37\",\"env\":\"dev\",\"key\":\"a\",\"params\":{},\"title\":\"水电费(配置新增)\",\"updateBy\":\"\",\"value\":\"1\"}],\"params\":{},\"title\":\"水电费\",\"updateBy\":\"\",\"value\":\"1\"}',NULL,1,'\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'WHERE `env` = \'dev\' AND `key` = \'a\'\' at line 6\r\n### The error may exist in file [E:\\source\\melon\\melon-system\\target\\classes\\mapper\\config\\XxlConfNodeMapper.xml]\r\n### The error may involve fun.sssdnsy.mapper.XxlConfNodeDao.update-Inline\r\n### The error occurred while setting parameters\r\n### SQL: UPDATE xxl_conf_node         SET `title`      = ?,             `value`      = ?,             `update_time`=now(),             `update_by`=?,             WHERE `env` = ? AND `key` = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'WHERE `env` = \'dev\' AND `key` = \'a\'\' at line 6\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'WHERE `env` = \'dev\' AND `key` = \'a\'\' at line 6','2023-03-30 17:11:39'),(126,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"createTime\":\"2023-03-30 16:38:37\",\"env\":\"dev\",\"key\":\"a\",\"logList\":[{\"createTime\":\"2023-03-30 16:38:37\",\"env\":\"dev\",\"key\":\"a\",\"params\":{},\"title\":\"水电费(配置新增)\",\"updateBy\":\"\",\"value\":\"1\"}],\"params\":{},\"title\":\"水电费\",\"updateBy\":\"\",\"value\":\"1\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-30 17:13:32'),(127,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"createTime\":\"2023-03-30 16:38:37\",\"env\":\"dev\",\"key\":\"a\",\"logList\":[],\"params\":{},\"title\":\"水电费\",\"updateBy\":\"\",\"updateTime\":\"2023-03-30 17:13:32\",\"value\":\"22\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-30 17:13:51'),(128,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"createTime\":\"2023-03-30 17:08:12\",\"env\":\"dev\",\"key\":\"b\",\"logList\":[{\"createTime\":\"2023-03-30 17:08:12\",\"env\":\"dev\",\"key\":\"b\",\"params\":{},\"title\":\"1(配置新增)\",\"updateBy\":\"\",\"value\":\"2\"}],\"params\":{},\"title\":\"1\",\"updateBy\":\"\",\"value\":\"333\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-30 17:14:39'),(129,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"createBy\":\"SSSDNSY\",\"createTime\":\"2023-03-30 17:35:52\",\"env\":\"dev\",\"key\":\"f\",\"logList\":[],\"params\":{},\"title\":\"fffffff\",\"updateBy\":\"SSSDNSY\",\"updateTime\":\"2023-03-31 11:20:44\",\"value\":\"f\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-31 11:23:49'),(130,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"createBy\":\"SSSDNSY\",\"createTime\":\"2023-03-30 17:35:52\",\"env\":\"dev\",\"key\":\"f\",\"logList\":[],\"params\":{},\"title\":\"f\",\"updateBy\":\"SSSDNSY\",\"updateTime\":\"2023-03-31 11:23:47\",\"value\":\"ffffffff\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-31 11:24:16'),(131,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"createBy\":\"SSSDNSY\",\"createTime\":\"2023-03-31 16:22:07.875\",\"env\":\"dev\",\"key\":\"default.key01\",\"params\":{},\"title\":\"default.key02\",\"value\":\"xxxxxxxxxxxxxxxxxxxxxxx\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-31 16:22:07'),(132,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"createBy\":\"SSSDNSY\",\"createTime\":\"2023-03-31 16:22:08\",\"env\":\"dev\",\"key\":\"default.key01\",\"logList\":[{\"createBy\":\"SSSDNSY\",\"createTime\":\"2023-03-31 16:22:08\",\"env\":\"dev\",\"key\":\"default.key01\",\"params\":{},\"title\":\"default.key02(配置新增)\",\"updateBy\":\"\",\"value\":\"xxxxxxxxxxxxxxxxxxxxxxx\"}],\"params\":{},\"title\":\"default.key02\",\"updateBy\":\"\",\"value\":\"2\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-31 16:43:16'),(133,'保存配置项',1,'fun.sssdnsy.web.controller.config.ConfController.save()','POST',1,'SSSDNSY',NULL,'/melon/config/conf','127.0.0.1','内网IP','{\"appname\":\"default\",\"createBy\":\"SSSDNSY\",\"createTime\":\"2023-03-30 17:35:52\",\"env\":\"dev\",\"key\":\"f\",\"logList\":[],\"params\":{},\"title\":\"f\",\"updateBy\":\"SSSDNSY\",\"updateTime\":\"2023-03-31 11:24:16\",\"value\":\"2\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2023-03-31 16:43:23');
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_post` (
                            `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
                            `post_code` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '岗位编码',
                            `post_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '岗位名称',
                            `post_sort` int NOT NULL COMMENT '显示顺序',
                            `status` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '状态（0正常 1停用）',
                            `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'ceo','董事长',1,'0','admin','2023-01-30 13:46:09','',NULL,''),(2,'se','项目经理',2,'0','admin','2023-01-30 13:46:09','',NULL,''),(3,'hr','人力资源',3,'0','admin','2023-01-30 13:46:09','',NULL,''),(4,'user','普通员工',4,'0','admin','2023-01-30 13:46:09','',NULL,'');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
                            `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                            `role_name` varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
                            `role_key` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '角色权限字符串',
                            `role_sort` int NOT NULL COMMENT '显示顺序',
                            `data_scope` char(1) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
                            `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
                            `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
                            `status` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '角色状态（0正常 1停用）',
                            `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                            `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'1',1,1,'0','0','admin','2023-01-30 13:46:09','',NULL,'超级管理员'),(2,'普通角色','common',2,'2',1,1,'0','0','admin','2023-01-30 13:46:09','admin','2023-03-27 16:24:22','普通角色');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept` (
                                 `role_id` bigint NOT NULL COMMENT '角色ID',
                                 `dept_id` bigint NOT NULL COMMENT '部门ID',
                                 PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (2,100),(2,101),(2,105);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
                                 `role_id` bigint NOT NULL COMMENT '角色ID',
                                 `menu_id` bigint NOT NULL COMMENT '菜单ID',
                                 PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (2,1),(2,2),(2,3),(2,4),(2,100),(2,101),(2,102),(2,103),(2,104),(2,105),(2,106),(2,107),(2,108),(2,109),(2,110),(2,111),(2,112),(2,113),(2,114),(2,115),(2,116),(2,117),(2,500),(2,501),(2,1000),(2,1001),(2,1002),(2,1003),(2,1004),(2,1005),(2,1006),(2,1007),(2,1008),(2,1009),(2,1010),(2,1011),(2,1012),(2,1013),(2,1014),(2,1015),(2,1016),(2,1017),(2,1018),(2,1019),(2,1020),(2,1021),(2,1022),(2,1023),(2,1024),(2,1025),(2,1026),(2,1027),(2,1028),(2,1029),(2,1030),(2,1031),(2,1032),(2,1033),(2,1034),(2,1035),(2,1036),(2,1037),(2,1038),(2,1039),(2,1040),(2,1041),(2,1042),(2,1043),(2,1044),(2,1045),(2,1046),(2,1047),(2,1048),(2,1049),(2,1050),(2,1051),(2,1052),(2,1053),(2,1054),(2,1055),(2,1056),(2,1057),(2,1058),(2,1059),(2,1060),(2,2000),(2,2001),(2,2002),(2,2003),(2,2004),(2,2005),(2,2008),(2,2009),(2,2010),(2,2011),(2,2012),(2,2013),(2,2014);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
                            `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
                            `user_name` varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '用户账号',
                            `nick_name` varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
                            `user_type` varchar(2) COLLATE utf8mb4_bin DEFAULT '00' COMMENT '用户类型（00系统用户）',
                            `email` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户邮箱',
                            `phonenumber` varchar(11) COLLATE utf8mb4_bin DEFAULT '' COMMENT '手机号码',
                            `sex` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
                            `avatar` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '头像地址',
                            `password` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '密码',
                            `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
                            `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                            `login_ip` varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT '最后登录IP',
                            `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
                            `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1553797 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,103,'admin','若依','00','ry@163.com','15888888888','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2023-03-27 16:17:53','admin','2023-01-30 13:46:09','','2023-03-27 16:17:53','管理员'),(2,105,'ry','若依','00','ry@qq.com','15666666666','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2023-01-30 13:46:09','admin','2023-01-30 13:46:09','',NULL,'测试员'),(1553796,NULL,'SSSDNSY','SSSDNSY','00','sssdnsy@163.com','','0','https://foruda.gitee.com/avatar/1676975810888864595/1553796_sssdnsy_1658297241.png','$2a$10$L5RWvHoX0WR0XFIsPkVn.OI3Vv3/W8ZqpyF7FQgCIdU4mZleom2Ky','0','0','127.0.0.1','2023-03-31 16:21:17','sys','2023-03-17 13:57:59','SSSDNSY','2023-03-31 16:21:17','1');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_post` (
                                 `user_id` bigint NOT NULL COMMENT '用户ID',
                                 `post_id` bigint NOT NULL COMMENT '岗位ID',
                                 PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1),(2,2),(1553796,4);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
                                 `user_id` bigint NOT NULL COMMENT '用户ID',
                                 `role_id` bigint NOT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,2),(1553796,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_conf_env`
--

DROP TABLE IF EXISTS `xxl_conf_env`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_conf_env` (
                                `env` varchar(100) NOT NULL COMMENT 'Env',
                                `title` varchar(100) NOT NULL COMMENT '环境名称',
                                `order` tinyint NOT NULL DEFAULT '0' COMMENT '显示排序',
                                `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                                `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                PRIMARY KEY (`env`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='config配置环境';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_conf_env`
--

LOCK TABLES `xxl_conf_env` WRITE;
/*!40000 ALTER TABLE `xxl_conf_env` DISABLE KEYS */;
INSERT INTO `xxl_conf_env` VALUES ('dev','开发环境',4,NULL,NULL,'',NULL),('ppe','预发布环境',2,'',NULL,'',NULL),('product','生产环境',3,'',NULL,'',NULL),('test','测试环境',1,'',NULL,'',NULL);
/*!40000 ALTER TABLE `xxl_conf_env` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_conf_node`
--

DROP TABLE IF EXISTS `xxl_conf_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_conf_node` (
                                 `env` varchar(100) NOT NULL COMMENT 'Env',
                                 `key` varchar(200) NOT NULL COMMENT '配置Key',
                                 `appname` varchar(100) NOT NULL COMMENT '所属项目AppName',
                                 `title` varchar(100) NOT NULL COMMENT '配置描述',
                                 `value` varchar(2000) DEFAULT NULL COMMENT '配置Value',
                                 `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`env`,`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='config配置节点';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_conf_node`
--

LOCK TABLES `xxl_conf_node` WRITE;
/*!40000 ALTER TABLE `xxl_conf_node` DISABLE KEYS */;
INSERT INTO `xxl_conf_node` VALUES ('dev','default.key01','default','default.key02','2','SSSDNSY','2023-03-31 16:22:08','SSSDNSY','2023-03-31 16:43:17'),('dev','f','default','f','2','SSSDNSY','2023-03-30 17:35:52','SSSDNSY','2023-03-31 16:43:23'),('test','default.key02','default','测试配置02','2','',NULL,'',NULL),('test','default.key03','default','测试配置03','3','',NULL,'',NULL);
/*!40000 ALTER TABLE `xxl_conf_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_conf_node_log`
--

DROP TABLE IF EXISTS `xxl_conf_node_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_conf_node_log` (
                                     `env` varchar(255) NOT NULL COMMENT 'Env',
                                     `key` varchar(200) NOT NULL COMMENT '配置Key',
                                     `title` varchar(100) NOT NULL COMMENT '配置描述',
                                     `value` varchar(2000) DEFAULT NULL COMMENT '配置Value',
                                     `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                                     `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='config配置日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_conf_node_log`
--

LOCK TABLES `xxl_conf_node_log` WRITE;
/*!40000 ALTER TABLE `xxl_conf_node_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `xxl_conf_node_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_conf_node_msg`
--

DROP TABLE IF EXISTS `xxl_conf_node_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_conf_node_msg` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `env` varchar(100) NOT NULL COMMENT 'Env',
                                     `key` varchar(200) NOT NULL COMMENT '配置Key',
                                     `value` varchar(2000) DEFAULT NULL COMMENT '配置Value',
                                     `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                                     `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='config配置项消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_conf_node_msg`
--

LOCK TABLES `xxl_conf_node_msg` WRITE;
/*!40000 ALTER TABLE `xxl_conf_node_msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `xxl_conf_node_msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_conf_project`
--

DROP TABLE IF EXISTS `xxl_conf_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_conf_project` (
                                    `appname` varchar(100) NOT NULL COMMENT 'AppName',
                                    `title` varchar(100) NOT NULL COMMENT '项目名称',
                                    `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建者',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新者',
                                    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                    PRIMARY KEY (`appname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='config配置项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_conf_project`
--

LOCK TABLES `xxl_conf_project` WRITE;
/*!40000 ALTER TABLE `xxl_conf_project` DISABLE KEYS */;
INSERT INTO `xxl_conf_project` VALUES ('default','示例项目','',NULL,'',NULL);
/*!40000 ALTER TABLE `xxl_conf_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'melon'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-31 17:15:52
