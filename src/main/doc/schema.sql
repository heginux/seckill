-- 数据库初始化

-- 创建数据库
CREATE DATABASE seckill;

SHOW DATABASES;
-- 创建秒杀库存表
CREATE TABLE seckill (
  `id`            BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '商品库存id',
  `name`          VARCHAR(120) NOT NULL
  COMMENT '商品名称',
  `number`        INT          NOT NULL
  COMMENT '库存数量',
  `start_time`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '秒杀开始时间',
  `end_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '秒杀结束时间',
  `create_time`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `modified_time` TIMESTAMP    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT = '秒杀数据表';

-- 修改字段信息
ALTER TABLE seckill CHANGE COLUMN create_time  created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- 初始化数据
INSERT INTO seckill (name, number, start_time, end_time)
VALUES ('1000元秒杀iPhone8', 100, '2017-09-09 00:00:00', '2017-09-10 00:00:00'),
  ('500元秒杀iPad2', 100, '2017-09-09 00:00:00', '2017-09-10 00:00:00'),
  ('600元秒杀小米5', 100, '2017-09-09 00:00:00', '2017-09-10 00:00:00'),
  ('300元秒杀魅蓝note', 100, '2017-09-09 00:00:00', '2017-09-10 00:00:00');

-- 秒杀成功明细表
CREATE TABLE success_killes (
  `id`            BIGINT    NOT NULL  AUTO_INCREMENT
  COMMENT '秒杀成功id',
  `seckill_id`    BIGINT    NOT NULL
  COMMENT '秒杀库存id',
  `user_phone`    BIGINT    NOT NULL
  COMMENT '用户手机号',
  `state`         TINYINT   NOT NULL  DEFAULT -1
  COMMENT '状态标示:-1:无效 0:成功 1:已付款',
  `created_time`  TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `modified_time` TIMESTAMP NULL      DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (seckill_id, user_phone), /*联合主键*/
  KEY idx_created_time(created_time),
  KEY idx_id(id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT = '秒杀成功明细表';
