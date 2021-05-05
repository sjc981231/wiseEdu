package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.entity.Course;


public interface CourseDao  extends JpaRepository<Course, Integer>{
	@Query("select c from Course c where c.userId= :userId")
	public List<Course> findByUserId(@Param(value="userId")Integer userId);

	@Query(nativeQuery=true, value = "select * from Course where user_id= :userId limit :offset, :pagesize")
	public List<Course> findByPage(@Param(value="offset")int offset, @Param(value="pagesize")int pageSize, @Param(value="userId")Integer userId);

	public Course findByCourseId(Integer courseId);

}