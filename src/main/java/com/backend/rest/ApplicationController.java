package com.backend.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.domain.Course;
import com.backend.domain.Student;
import com.backend.domain.User;
import com.backend.service.ApplicationService;


@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/collegeManagementSystem")
public class ApplicationController {
	
	private ApplicationService applicationService;
	
	@Autowired
	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
	@PostMapping(value = "/userSignup")
	ResponseEntity<Map> saveUser(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.saveUser(user));
	}
	
	@PostMapping(value = "/userSignin")
	ResponseEntity<Map> checkUser(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.checkUser(user));
	}
	
	@GetMapping(value = "/loadAllCourses")
	ResponseEntity<Map> getAllDepartments(){
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.getAllDepartments());
	}
	
	@GetMapping(value = "/loadStudentDetails")
	ResponseEntity<Map> getAllStudents(){
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.getAllStudents());
	}
	
	@GetMapping(value = "/getDataByStudentId/{id}")
	ResponseEntity<Map> getDataByStudentId(@PathVariable(name = "id",required = true) int id){
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.getDataByStudentId(id));
	}
	
	@PostMapping(value = "/enrollCourse")
	ResponseEntity<Map> enrollCourse(@RequestBody Student student){
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.enrollCourse(student));
	}
	
	@DeleteMapping(value = "/unenrollCourse/{courseId}/{studentId}")
	ResponseEntity<Map> unenrollCourse(@PathVariable(name = "courseId",required = true) int courseId,
			@PathVariable(name = "studentId",required = true) int studentId){
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.unenrollCourse(courseId,studentId));
	}
	
	@PostMapping(value = "/addNewCourse")
	ResponseEntity<Map> addNewCourse(@RequestBody Course course){
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.addNewCourse(course));
	}
	
	
}
