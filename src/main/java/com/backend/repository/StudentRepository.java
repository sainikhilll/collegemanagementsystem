package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.domain.Student;
import com.backend.domain.User;


@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	
	Optional<Student> findByCourseId(int id);
	
	List<Student> findByStudentId(int id);
	
	Optional<Student> findByCourseIdAndStudentId(int courseId,int studentId);

	

}
