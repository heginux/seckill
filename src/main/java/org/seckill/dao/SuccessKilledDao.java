package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entiy.SuccessKilled;

/**
 * 秒杀成功Dao接口
 * 1.插入购买明细
 * 2.查询秒杀成功数据
 *
 * @author heguixing
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细,可过滤重复
     * 使用数据库唯一索引,进行过滤重复数据
     *
     * @param seckillId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 插入行数
     */
    int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);


    /**
     * 根据id查询SuccessKilled,并携带秒杀商品对象实体
     *
     * @param seckillId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 秒杀成功实体
     */
    SuccessKilled queryBySeckillIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}
