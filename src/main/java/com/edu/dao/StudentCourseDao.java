package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.entity.StudentCourse;
import com.edu.entity.User;



public interface StudentCourseDao  extends JpaRepository<StudentCourse, Integer> {

//	
//	@Query(nativeQuery=true, value = "select * from User u where id in(select id from User u,Student_course sc where course_id= :courseId and u.id=sc.student_id) limit :offset, :pagesize")
//	List<User> findByPage(@Param(value="offset")int offset,  @Param(value="pagesize")int pageSize, @Param(value="courseId")Integer courseId);
	
//	//临时策略
//
//	@Query("select u from User u,StudentCourse sc where sc.courseId= :courseId and u.id=sc.studentId")
//	List<User> findByPage(Integer courseId);

	
	
	@Query("select u from User u,StudentCourse sc where sc.courseId= :courseId and u.id=sc.studentId")
	List<User> findByCourseId(Integer courseId);

	void deleteByCourseIdAndStudentId(Integer courseId, Integer courseId2);
	
	
	@Query("select u from User u where u.id not in(select u.id from User u,StudentCourse sc where sc.courseId= :courseId and u.id=sc.studentId) and u.roleId=2")
	List<User> findByCourseIdNot(Integer courseId);

	List<StudentCourse> findByStudentId(Integer studentId);

	
}
