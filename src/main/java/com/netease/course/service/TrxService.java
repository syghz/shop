package com.netease.course.service;

import java.util.List;

import com.netease.course.model.Trx;

public interface TrxService {
	
	Trx getTrxByContentId(int contentId);
	
	int insertTrx(Trx trx);
	
	List<Trx> getAllTrx();
}
