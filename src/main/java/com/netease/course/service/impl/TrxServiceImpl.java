package com.netease.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.TrxMapper;
import com.netease.course.model.Trx;
import com.netease.course.service.TrxService;

@Service("trxService")
public class TrxServiceImpl implements TrxService {

	@Autowired
	private TrxMapper trxMapper;
	@Override
	public Trx getTrxByContentId(int contentId) {
		return trxMapper.getTrxByContentId(contentId);
	}
	@Override
	public int insertTrx(Trx trx) {
		return trxMapper.insertSelective(trx);
	}
	@Override
	public List<Trx> getAllTrx() {
		return trxMapper.getAllTrx();
	}

}
