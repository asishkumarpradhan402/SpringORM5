package com.main;

import org.springframework.context.ApplicationContext;

import com.main.config.ApplicationContextLoad;
import com.main.dao.StudentDao;
import com.main.model.Course;
import com.main.model.Student;

public class SpringORMApplication {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextLoad.getXmlApplicationContext();
//		ApplicationContext applicationContext = ApplicationContextLoad.getAnnotationConfigApplicationContext();
		StudentDao studentDao = applicationContext.getBean("studentDao", StudentDao.class);

		Student student = new Student();
		student.setStudentName("Asish");
		student.setStudentAddress("address");
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("cnane");
		student.setCourse(course);
		int save = studentDao.save(student);

		System.out.println(save);
	}
}
