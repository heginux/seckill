-- 秒杀执行存储过程
-- MySQL的console 是用分号;隔离的,存储过程也是用分号换行
DELIMITER $$ --console ; 转换为 $$
-- 定义存储过程
-- 参数:IN 输入参数;OUT 输出参数
-- row_count():返回上一条修改类型SQL（insert,delete,update）的影响行数
-- row_count: =0未修改数据 >0修改的行数 <0SQL错误/未执行修改SQL
-- r_result: 0库存不足/时间不合理 1成功 -1重复秒 -2执行错误
CREATE PROCEDURE execute_seckill
  (IN v_seckill_id BIGINT, IN v_phone BIGINT, IN v_kill_time TIMESTAMP, OUT r_result INT)
  BEGIN
    DECLARE insert_count INT DEFAULT 0;
    START TRANSACTION;
    INSERT IGNORE INTO success_killes (seckill_id, state, user_phone, created_time)
    VALUES (v_seckill_id,0, v_phone, v_kill_time);
    SELECT row_count() INTO insert_count;
    IF (insert_count = 0) THEN
      ROLLBACK;
      SET r_result = -1;
    ELSEIF (insert_count < 0) THEN
        ROLLBACK;
        SET r_result = -2;
    ELSE
      UPDATE seckill SET number = number - 1
      WHERE id = v_seckill_id
            AND start_time < v_kill_time
            AND end_time > v_kill_time
            AND number > 0;
      SELECT row_count() INTO insert_count;
      IF (insert_count = 0) THEN
        ROLLBACK;
        SET r_result = 0;
      ELSEIF (insert_count < 0) THEN
          ROLLBACK;
          SET r_result = -2;
      ELSE
        COMMIT;
        SET r_result = 1;
      END IF;
    END IF;
  END;
$$

-- 调用存储过程
SET @r_result = -3;
CALL execute_seckill(1004,13432948554,now(),@r_result);
SELECT @r_result ;