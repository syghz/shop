package com.netease.course.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.netease.course.model.Trx;
import com.netease.course.service.TrxService;


/**
 * 测试Trx
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class TestTrx {
	
	private static final Logger LOGGER=Logger.getLogger(TestTrx.class);
	@Autowired
	private TrxService trxService;
	@Test
	public void test1(){
		Trx t = trxService.getTrxByContentId(1111);
		LOGGER.info(JSON.toJSON(t));
	}
}
