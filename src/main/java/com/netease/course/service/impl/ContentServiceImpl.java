package com.netease.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.ContentMapper;
import com.netease.course.model.Content;
import com.netease.course.service.ContentService;

@Service("contentService")
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public List<Content> getAllContent() {
		return contentMapper.getAllContent();
	}

	@Override
	public Content getByContentId(int id) {
		return contentMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteContentById(int id) {
		contentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateContent(Content content) {
		contentMapper.updateByPrimaryKey(content);
	}

	@Override
	public void insertContent(Content content) {
		contentMapper.insert(content);
		
	}

}
