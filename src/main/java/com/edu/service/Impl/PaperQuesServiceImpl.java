package com.edu.service.Impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.dao.PaperQuesDao;
import com.edu.entity.PaperQues;
import com.edu.entity.Question;
import com.edu.service.PaperQuesService;

@Service
@Transactional
public class PaperQuesServiceImpl implements PaperQuesService {

	@Resource
	PaperQuesDao paperQuesDao;
	@Override
	public List<Question> findQuesByPaperId(int paperId) {
		// TODO Auto-generated method stub
		return paperQuesDao.findQuesByPaperId(paperId);
	}
	@Override
	public void deleteByPaperId(int paperId) {
		// TODO Auto-generated method stub
		paperQuesDao.deleteByPaperId(paperId);
	}
	@Override
	public void save(PaperQues rf) {
		// TODO Auto-generated method stub
		paperQuesDao.save(rf);
	}

}
