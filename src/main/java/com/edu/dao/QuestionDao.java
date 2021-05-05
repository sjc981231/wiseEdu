package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.entity.Course;
import com.edu.entity.Question;

public interface QuestionDao extends JpaRepository<Question, Integer> {

	@Query(nativeQuery=true, value = "select * from Question where course_id= :courseId limit :offset, :pagesize")
	List<Question> findByPage(@Param(value="offset")int offset,  @Param(value="pagesize")int pageSize, @Param(value="courseId")Integer courseId);

	List<Question> findByCourseId(Integer courseId);

	@Modifying
    @Query("update Question q set q.status = 0 where q.quesId = :quesId")
	void myDeleteByQuesId(@Param(value="quesId")int quesId);

	Question findByQuesId(int quesId);
}
