package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entiy.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redisDao 层
 *
 * @author hgx
 * @date 2017/10/15
 */

public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    /**
     * 从Redis中获取 Seckill对象
     *
     * @param seckillId 秒杀id
     * @return Seckill对象 或 null
     */
    public Seckill getSeckill(long seckillId) {
        //Redis操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill_" + seckillId;
                //Redis并没有实现内部序列化操作
                //get->byte[]->反序列化->Object
                //采用Protostuff序列化,空间比JDK序列化少50%以上,速度快两个数量级,节省CPU
                byte[] bytes = jedis.get(key.getBytes());
                //缓存获取到
                if (bytes != null) {
                    //空对象
                    Seckill seckill = schema.newMessage();
                    //seckill 被反序列化
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    /**
     * 设置缓存
     *
     * @param seckill 秒杀实体
     * @return ‘ok’ 或 null 或 错误信息
     */
    public String setSeckill(Seckill seckill) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill_" + seckill.getId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //设置超时缓存,1小时
                int timeout = 60 * 60;
                return jedis.setex(key.getBytes(), timeout, bytes);
            } finally {
                jedis.close();
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


}
