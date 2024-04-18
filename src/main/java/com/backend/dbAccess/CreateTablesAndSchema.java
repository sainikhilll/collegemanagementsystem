package com.backend.dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTablesAndSchema {
	
	public static void main(String[] args) {
		try(Connection conn = createNewDBconnection()){
			createSchemaAndTables(conn);
			System.out.println("Tables and schema created");
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	private static void createSchemaAndTables(Connection connection) {
		try {
			connection.prepareStatement("DROP SCHEMA if exists CollegeManagementSystem").execute();
			PreparedStatement statement = connection.prepareStatement("CREATE DATABASE CollegeManagementSystem"); 
			statement.execute();
			connection.setSchema("CollegeManagementSystem");
			String createUser = "CREATE TABLE CollegeManagementSystem.user ("+ 
					"					id MEDIUMINT NOT NULL AUTO_INCREMENT,"+ 
					"					student_id MEDIUMINT NOT NULL,"+
					"					user_name varchar(150)," +
					"					email varchar(150)," +
					"					phone_number varchar(150)," +
					"					password varchar(150)," +
					"					Constraint PK Primary Key(id)"+ 
					"					)";
			String createdepartment = "CREATE TABLE CollegeManagementSystem.department (" + 
					"					dept_id MEDIUMINT NOT NULL,"+
					"					dept_name varchar(150)," +
					"					hod varchar(150)," +
					"					Constraint PK Primary Key(dept_id)"+ 
					")";
			String createcourse = "CREATE TABLE CollegeManagementSystem.course (" +
					"					id MEDIUMINT NOT NULL AUTO_INCREMENT,"+ 
					"					course_id MEDIUMINT NOT NULL,"+
					"					course_name varchar(150)," +
					"					dept_id MEDIUMINT NOT NULL,"+
					"					dept_name varchar(150)," +
					"					ins_id MEDIUMINT NOT NULL,"+
					"					ins_name varchar(150)," +
					"					Constraint PK Primary Key(id),"+ 
					" 	 				Constraint course_dept_fk FOREIGN KEY (dept_id) REFERENCES department(dept_id)"+
					")";
			String createstudent = "CREATE TABLE CollegeManagementSystem.student (" +
					"					id MEDIUMINT NOT NULL AUTO_INCREMENT,"+ 
					"					student_id MEDIUMINT NOT NULL,"+
					"					student_name varchar(150)," +
					"					student_email varchar(150)," +
					"					course_id MEDIUMINT NOT NULL,"+
					"					course_name varchar(150)," +
					"					dept_id MEDIUMINT NOT NULL,"+
					"					dept_name varchar(150)," +
					"					hod varchar(150)," +
					"					ins_id MEDIUMINT NOT NULL,"+
					"					ins_name varchar(150)," +
					"					Constraint PK Primary Key(id),"+ 
					" 	 				Constraint student_dept_fk FOREIGN KEY (dept_id) REFERENCES department(dept_id)"+
					")";
			
			String createInstructor = "CREATE TABLE CollegeManagementSystem.instructor (" +
					"					instructor_id MEDIUMINT NOT NULL,"+
					"					instructor_name varchar(150)," +
					"					dept_id MEDIUMINT NOT NULL,"+
					"					dept_name varchar(150)," +
					"					Constraint PK Primary Key(instructor_id),"+ 
					" 	 				Constraint instructor_dept_fk FOREIGN KEY (dept_id) REFERENCES department(dept_id)"+
					")";
		

			
			connection.prepareStatement(createUser).execute();
			connection.prepareStatement(createdepartment).execute();
			connection.prepareStatement(createcourse).execute();
			connection.prepareStatement(createstudent).execute();
			connection.prepareStatement(createInstructor).execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String dbhost = "jdbc:mysql://cmsdb.cdmouo4ecv8y.us-east-2.rds.amazonaws.com:3306";
	private static String username = "root";
	private static String password = "779944nik";
	private static Connection conn;
	
	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try  {	
			conn = DriverManager.getConnection(
					dbhost, username, password);	
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}
}
