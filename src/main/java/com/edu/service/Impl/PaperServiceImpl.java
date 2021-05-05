package com.edu.service.Impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.dao.PaperDao;
import com.edu.entity.Paper;
import com.edu.service.PaperService;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {

	@Resource
	PaperDao paperDao;
	
	@Override
	public List<Paper> findByPage(int offset, int pageSize, Integer courseId) {
		// TODO Auto-generated method stub
		return paperDao.findByPage(offset,pageSize,courseId);
	}

	@Override
	public int count(Integer courseId) {
		// TODO Auto-generated method stub
		return paperDao.findByCourseId(courseId).size();
	}

	@Override
	public void insertPaper(Paper paper) {
		// TODO Auto-generated method stub
		paperDao.save(paper);
	}

	@Override
	public void deleteByPaperId(int paperId) {
		// TODO Auto-generated method stub
		paperDao.myDeleteByPaperId(paperId);
	}

	@Override
	public Paper findByPaperId(int paperId) {
		// TODO Auto-generated method stub
		return paperDao.findByPaperId(paperId);
	}

	@Override
	public void updatePaper(Paper paper) {
		// TODO Auto-generated method stub
		paperDao.saveAndFlush(paper);
		
	}

}
