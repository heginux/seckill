package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entiy.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 秒杀Service层
 * 业务接口:站在“使用者”角度设计接口
 * 三个方面:方法定义粒度,参数,返回类型（return 类型/异常）
 *
 * @author hegx
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return 秒杀对象
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId 秒杀id
     * @return 秒杀实体
     */
    Seckill get(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址,
     * 否则输出系统时间和秒杀时间
     *
     * @param seckillId 秒杀id
     * @return 接口暴露实体
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作
     *
     * @param seckillId 秒杀商品id
     * @param userPhone 用户手机号
     * @param md5       加密值
     * @return 秒杀结果
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;


    /**
     * 执行秒杀操作(使用存储过程)
     *
     * @param seckillId 秒杀商品id
     * @param userPhone 用户手机号
     * @param md5       加密值
     * @return 秒杀结果
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;

}
