package com.netease.course.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.netease.course.model.Person;
import com.netease.course.service.PersonService;
/**
 * 使用spring方式 测试
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class TestPerson {
	//通过日志输出Json数据
	private static final Logger LOGGER=Logger.getLogger(TestPerson.class);
	@Autowired
    private PersonService personService;
	
	@Test
	public void test1(){
		Person p = personService.getPersonById(1);
		LOGGER.info(JSON.toJSON(p));
	}
	@Test
	public void test2(){
		List<Person> p = personService.getAllPerson();
		LOGGER.info(JSON.toJSON(p));
	}
	
}
