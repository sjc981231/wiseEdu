package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.entity.Lesson;
import com.edu.entity.Question;

public interface LessonDao  extends JpaRepository<Lesson, Integer>{

	@Query(nativeQuery=true, value = "select * from Lesson where course_id= :courseId order by sort_num limit :offset, :pagesize ")
	List<Lesson> findByPage(@Param(value="offset")int offset,  @Param(value="pagesize")int pageSize, @Param(value="courseId")Integer courseId);

	List<Lesson> findByCourseId(Integer courseId);

	@Modifying
    @Query("update Lesson l set l.status = 0 where l.lessonId = :lessonId")
	void myDeleteByLessonId(@Param(value="lessonId")int lessonId);

	Lesson findByLessonId(int lessonId);
	

}
