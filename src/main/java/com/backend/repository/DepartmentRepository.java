package com.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.domain.Department;
import com.backend.domain.Student;
import com.backend.domain.User;


@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
	
	

}
