package com.edu.service.Impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.dao.QuesFillDao;
import com.edu.entity.QuesFill;
import com.edu.service.QuesFillService;
@Service
@Transactional
public class QuesFillServiceImpl implements QuesFillService {
	@Resource
	QuesFillDao quesFillDao;

	@Override
	public QuesFill findByQuesId(int quesId) {
		// TODO Auto-generated method stub
		return quesFillDao.findByQuesId(quesId);
	}

	@Override
	public void updateQuesFill(QuesFill quesFill) {
		// TODO Auto-generated method stub
		quesFillDao.saveAndFlush(quesFill);
	}
	

}
