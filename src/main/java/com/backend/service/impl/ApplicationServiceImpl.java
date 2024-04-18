package com.backend.service.impl;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.backend.domain.Department;
import com.backend.domain.Instructor;
import com.backend.domain.Student;
import com.backend.domain.User;
import com.backend.domain.Course;
import com.backend.domain.DashboardList;
import com.backend.repository.CourseRepository;
import com.backend.repository.DepartmentRepository;
import com.backend.repository.InstructorRepository;
import com.backend.repository.StudentRepository;
import com.backend.repository.UserRepository;
import com.backend.service.ApplicationService;



@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	InstructorRepository instructorRepository;
	
	@Override
	public Map saveUser(User user) {
		Map<String, Object> resultMap = new HashMap<>();
		Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
		if(userOpt.isPresent()) {
			resultMap.put("isSuccess", false);
			resultMap.put("error", "email already exists");
			return resultMap;
		}
		user = userRepository.save(user);
		resultMap.put("isSuccess", true);
		resultMap.put("user", user);
		return resultMap;
	}

	@Override
	public Map<String, Object> checkUser(User user) {
		Map<String, Object> resultMap = new HashMap<>();
		Optional<User> userOpt = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if(userOpt.isPresent()) {
			resultMap.put("isSuccess", true);
			resultMap.put("user", userOpt.get());
		}
		else {
			resultMap.put("isSuccess", false);
			resultMap.put("error", "Invalid username/password");
		}
			
		return resultMap;
	}

	@Override
	public Map<String, Object> getAllDepartments() {
		Map resultMap = new HashMap<>();
		List<DashboardList> dashboardList=new ArrayList<>();
		List<Department> departmentList = (List<Department>)departmentRepository.findAll();
		departmentList.stream().forEach(dep->{
			DashboardList dashboard = new DashboardList();
			dashboard.setDeptId(dep.getDeptId());
			dashboard.setDeptName(dep.getDeptName());
			dashboard.setHod(dep.getHod());
			List<Course> courseList = courseRepository.findByDeptId(dep.getDeptId());
			dashboard.setCourseList(courseList);
			dashboardList.add(dashboard);
		});
		resultMap.put("data", dashboardList);
		return resultMap;
	}

	@Override
	public Map<String, Object> getAllStudents() {
		Map resultMap = new HashMap<>();
		List<Student> studentList= (List<Student>)studentRepository.findAll();
		resultMap.put("data", studentList);
		return resultMap;
	}

	@Override
	public Map<String, Object> enrollCourse(Student student) {
		Map<String, Object> resultMap = new HashMap<>();
		Optional<Student> studentOpt = studentRepository.findByCourseIdAndStudentId(student.getCourseId(),student.getStudentId());
		if(studentOpt.isPresent()) {
			resultMap.put("isSuccess", false);
			resultMap.put("error", "Already you enrolled this course");
			return resultMap;
		}
		student = studentRepository.save(student);
		resultMap.put("isSuccess", true);
		resultMap.put("data", student);
		return resultMap;
	}

	@Override
	public Map<String, Object> getDataByStudentId(int id) {
		Map<String, Object> resultMap = new HashMap<>();
		List<Student> studentList = studentRepository.findByStudentId(id);
		resultMap.put("data", studentList);
		return resultMap;
	}

	@Override
	public Map<String, Object> unenrollCourse(int courseId,int studentId) {
		Map<String, Object> resultMap = new HashMap<>();
		long deleteRecord=studentRepository.findByCourseIdAndStudentId(courseId, studentId).get().getId();
		studentRepository.deleteById(deleteRecord);
		resultMap.put("isSuccess", true);
		return resultMap;
	}

	@Override
	public Map<String, Object> addNewCourse(Course course) {
		Map<String, Object> resultMap = new HashMap<>();
		Optional<Course> courseOpt = courseRepository.findByCourseId(course.getCourseId());
		if(courseOpt.isPresent()) {
			resultMap.put("isSuccess", false);
			resultMap.put("errormsg", "Course Id already exists");
			return resultMap;
		}
		Optional<Instructor> instructorOpt = instructorRepository.findByInstructorId(course.getInsId());
		if(instructorOpt.isPresent()) {
			if(instructorOpt.get().getDeptId()==course.getDeptId()) {
				Course courseDetail = courseRepository.save(course);
				resultMap.put("isSuccess", true);
				resultMap.put("data", courseDetail);
			}
			else {
				resultMap.put("isSuccess", false); 
				resultMap.put("errormsg", "Instructor should be same department");
			}
		}else {
			resultMap.put("isSuccess", false);
			resultMap.put("errormsg", "Invalid Instructor Details");
		}
	
		return resultMap;
	}

}
