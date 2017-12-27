package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entiy.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 秒杀Dao层
 * 向Mybatis提供接口
 *
 * @author heguixing
 */
public interface SeckillDao {

    /**
     * 减库存
     *
     * @param seckillId 秒杀id
     * @param killTime  秒杀时间
     * @return 影响行数
     */
    int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询
     *
     * @param seckillId 秒杀id
     * @return 秒杀实体
     */
    Seckill get(long seckillId);


    /**
     * 分页查询
     *
     * @param offset 偏移
     * @param limit  个数
     * @return 秒杀列表
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 使用存储过程秒杀
     *
     * @param paramMap 参数列表
     */
    void killByProcedure(Map<String, Object> paramMap);
}
