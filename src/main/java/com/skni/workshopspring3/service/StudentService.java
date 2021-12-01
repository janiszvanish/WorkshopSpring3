package com.skni.workshopspring3.service;

import com.skni.workshopspring3.repo.StudentRepository;
import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import com.skni.workshopspring3.repo.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public Student addStudent(String firstname, String lastname, LocalDate dateOfBirth, GenderEnum gender, Course course){
        var student = Student.builder()
                .firstname(firstname)
                .dateOfBirth(dateOfBirth)
                .lastname(lastname)
                .course(course)
                .gender(gender)
                .build();

        //nie wiem czy equals wystarczy do sprawdzenia czy obiekt istnieje w bazie
        if(!isStudentPresent(studentRepository.findAll(), student)){
            studentRepository.save(student);
            return student;
        }
        return null;
    }

    private boolean isStudentPresent(List<Student> students, Student student){
        for(Student s : students){
            if(student.equals(s))
                return true;
        }
        return false;
    }

    public List<Student> findAllByLastName(String lastname) {
        List<Student> students = studentRepository.findAllByLastname(lastname);
        if(students.isEmpty())
            return null;
        return students;
    }

    public List<Student> getStudentByGenderAndByCourseType(GenderEnum gender, CourseTypeEnum courseType) {
        var students = getStudentByGender(gender);
        if(students.isEmpty())
            return null;
        for(Student s : students){
            if(s.getCourse().getCourseType() != courseType)
                students.remove(s);
        }
        return students;
    }

    private List<Student> getStudentByGender(GenderEnum gender){
        var students = studentRepository.findAllByGender(gender);
        if(students.isEmpty())
            return null;
        return students;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
        //nie chciało mi sie już ifa
    }

    public boolean deleteStudentById(Long id){
        studentRepository.deleteById(id);
        if(studentRepository.findById(id).isPresent())
            return false;
        return true;
    }
}
