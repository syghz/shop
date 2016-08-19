package com.netease.course.service;

import java.util.List;

import com.netease.course.model.Person;

public interface PersonService {
	 Person getPersonById(int id);
	 List<Person> getAllPerson();
	 Person getPersonByUserName(String userName);
}
