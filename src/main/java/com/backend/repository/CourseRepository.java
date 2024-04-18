package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.domain.Course;
import com.backend.domain.Department;
import com.backend.domain.Student;
import com.backend.domain.User;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
	
	List<Course> findByDeptId(int id);
	
	List<Course> findByInsIdAndDeptId(int insId,int deptId);
	
	Optional<Course> findByCourseId(int id);

}
