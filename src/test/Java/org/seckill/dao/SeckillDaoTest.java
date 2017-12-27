package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entiy.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;


/**
 * 配置Spring和Junit整合
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit Spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    private String a;

    @Test
    public void testGet() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.get(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);


    }

    @Test
    public void testQueryAll() throws Exception {
        // Caused by: org.apache.ibatis.binding.BindingException:
        // Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
        // java 没有保存形参的名字
        //
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        int i = seckillDao.reduceNumber(1000L, new Date());
        System.out.println(i);
    }

}