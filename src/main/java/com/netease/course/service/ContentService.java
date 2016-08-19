package com.netease.course.service;

import java.util.List;
import com.netease.course.model.Content;

public interface ContentService {
	
	Content getByContentId(int id);
	
	List<Content> getAllContent();
	
	void deleteContentById(int id);
	
	void updateContent(Content content); 
	
	void insertContent(Content content);
	
}
