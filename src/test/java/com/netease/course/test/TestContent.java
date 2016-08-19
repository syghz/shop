package com.netease.course.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.netease.course.model.Content;
import com.netease.course.service.ContentService;


/**
 * 测试Content
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class TestContent {
	
	private static final Logger LOGGER=Logger.getLogger(TestContent.class);
	@Autowired
	private ContentService contentService;
	@Test
	public void test1(){
		List<Content> c = contentService.getAllContent();
		LOGGER.info(JSON.toJSON(c));
	}
}
