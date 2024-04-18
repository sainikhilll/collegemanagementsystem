package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.domain.Instructor;
import com.backend.domain.Student;
import com.backend.domain.User;


@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
	
//	Optional<Student> findByCourseId(int id);
//	
	Optional<Instructor> findByInstructorId(int id);
//	
//	Optional<Student> findByCourseIdAndStudentId(int courseId,int studentId);

	

}
