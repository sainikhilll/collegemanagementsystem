package com.backend.service;

import java.util.Map;

import com.backend.domain.Course;
import com.backend.domain.Student;
import com.backend.domain.User;


public interface ApplicationService {


	Map<String, Object> saveUser(User user);

	Map<String, Object> checkUser(User user);

	Map<String, Object> getAllDepartments();

	Map<String, Object>  getAllStudents();

	Map<String, Object> enrollCourse(Student student);

	Map<String, Object> getDataByStudentId(int id);

	Map<String, Object> unenrollCourse(int courseId,int studentId);

	Map<String, Object> addNewCourse(Course course);


}
