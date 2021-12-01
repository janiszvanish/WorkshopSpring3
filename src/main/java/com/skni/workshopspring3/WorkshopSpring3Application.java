package com.skni.workshopspring3;

import java.time.LocalDate;

import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import com.skni.workshopspring3.repo.entity.Student;
import com.skni.workshopspring3.service.CourseService;
import com.skni.workshopspring3.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorkshopSpring3Application {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopSpring3Application.class, args);
	}

	@Bean
	CommandLineRunner init(CourseService courseService, StudentService studentService) {
		return (args) -> {
			Course course = courseService.addCourse("Informatyka", 3, "SGH", CourseTypeEnum.INZYNIER);

			Student studentMale = studentService.addStudent(
					"Adam",
					"Nowak",
					LocalDate.of(1996, 05,10),
					GenderEnum.MALE,
					course
			);

			Student studentFemale = studentService.addStudent(
					"Anna",
					"Kowalska",
					LocalDate.of(1993, 10,22),
					GenderEnum.FEMALE,
					course
			);

			System.out.println(studentMale);
			System.out.println(studentFemale);

			System.out.println(studentService.findAllByLastName("Nowak"));

			System.out.println(studentService.getStudentByGenderAndByCourseType(GenderEnum.MALE, CourseTypeEnum.INZYNIER));
			System.out.println(studentService.getStudentByGenderAndByCourseType(GenderEnum.FEMALE, CourseTypeEnum.LICENCJAT));

			Student student = studentService.addStudent(
					"Anggna",
					"ggghg",
					LocalDate.of(1980, 4,20),
					GenderEnum.FEMALE,
					course
			);

			System.out.println(studentService.getAllStudents());
			System.out.println(studentService.deleteStudentById(student.getId()));
			System.out.println(studentService.getAllStudents());

		};
	}
}
