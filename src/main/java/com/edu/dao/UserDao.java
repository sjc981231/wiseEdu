package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.entity.User;

public interface UserDao  extends JpaRepository<User, Integer>{

	User findByUserNameAndPassword(String username, String password);
	
	@Query(nativeQuery=true, value = "select * from User u where id in(select id from User u,Student_course sc where course_id= :courseId and u.id=sc.student_id) limit :offset, :pagesize")
	List<User> findByPage(@Param(value="offset")int offset,  @Param(value="pagesize")int pageSize, @Param(value="courseId")Integer courseId);

}
