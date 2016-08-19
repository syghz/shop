package com.netease.course.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.course.model.Json;
import com.netease.course.model.Person;
import com.netease.course.service.PersonService;

/**
 * 登录controller
 * 创建时间：2016-7-31 下午19:50
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/api")
public class PersonCotroller {
	
	@Autowired
	private PersonService personService;
	/**
	 * 登录处理
	 * @param userName
	 * @param password
	 * @param map
	 * @param hs
	 * @return
	 */
	@RequestMapping(value="login",method = RequestMethod.POST)
	@ResponseBody
	public Json login(@RequestParam("userName") String userName, @RequestParam("password") String password,
			ModelMap map, HttpSession hs){
		Person person = personService.getPersonByUserName(userName);
		if(person!=null && person.getPassword().equals(password)){
			Map<String, Object> user = new HashMap<String, Object>();
			user.put("id", person.getId());
			user.put("username", person.getUsername());
			user.put("usertype", person.getUsertype());
			hs.setAttribute("user", user);
			return new Json(200, "登录成功！", true);
		} else {
			return new Json(220, "登录失败！", false);
		}
	}
	
	
		
}
