package com.netease.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.PersonMapper;
import com.netease.course.model.Person;
import com.netease.course.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMapper personMapper;

	@Override
	public Person getPersonById(int id) {
		return personMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Person> getAllPerson() {
		return personMapper.getAllPerson();
	}
	@Override
	public Person getPersonByUserName(String userName) {
		return personMapper.getPerson(userName);
	}

}
