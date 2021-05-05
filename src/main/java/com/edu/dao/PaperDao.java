package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.entity.Paper;

public interface PaperDao  extends JpaRepository<Paper, Integer>{

	@Query(nativeQuery=true, value = "select * from Paper where course_id= :courseId limit :offset, :pagesize")
	List<Paper> findByPage(@Param(value="offset")int offset,  @Param(value="pagesize")int pageSize, @Param(value="courseId")Integer courseId);

	List<Paper> findByCourseId(Integer courseId);

	@Modifying
    @Query("update Paper p set p.status = 0 where p.paperId = :paperId")
	void myDeleteByPaperId(int paperId);

	Paper findByPaperId(int paperId);

}
