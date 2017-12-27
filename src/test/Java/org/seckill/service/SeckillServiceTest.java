package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entiy.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);

    }

    @Test
    public void testGet() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.get(id);
        logger.info("seckill={}", seckill);

    }

//    @Test
//    public void testExportSeckillUrl() throws Exception {
//        long id = 1000;
//        Exposer exposer = seckillService.exportSeckillUrl(id);
//        logger.info("exposer={}", exposer);
//    }

//    @Test
//    public void testExecuteSeckill() throws Exception {
//        long id = 1000;
//        long phone = 13590084688L;
//        String md5 = "068922bd64a45a8505f357b5396ae49c";
//        try {
//            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
//            logger.info("result={}", seckillExecution);
//        } catch (RepeatKillException e) {
//            logger.info(e.getMessage());
//        } catch (SeckillCloseException e) {
//            logger.info(e.getMessage());
//        }
//    }

    //测试代码完整逻辑,注意可重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 135900846889L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("result={}", seckillExecution);
            } catch (RepeatKillException e) {
                logger.info(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.info(e.getMessage());
            }
        } else {
            logger.warn("exposer={}", exposer);
        }
    }

    @Test
    public void testProcedure() {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 135900846889L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(id, phone, md5);
                logger.info("result={}", seckillExecution);
            } catch (RepeatKillException e) {
                logger.info(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.info(e.getMessage());
            }
        } else {
            logger.warn("exposer={}", exposer);
        }
    }



}