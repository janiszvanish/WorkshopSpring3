package com.skni.workshopspring3.service;

import com.skni.workshopspring3.dto.StudentRequest;
import com.skni.workshopspring3.dto.StudentResponse;
import com.skni.workshopspring3.repo.CourseRepository;
import com.skni.workshopspring3.repo.StudentRepository;
import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import com.skni.workshopspring3.repo.entity.Student;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public Student addStudent(String firstname, String lastname, LocalDate dateOfBirth, GenderEnum gender, Course course){
        var student = Student.builder()
                .firstname(firstname)
                .dateOfBirth(dateOfBirth)
                .lastname(lastname)
                .course(course)
                .gender(gender)
                .build();

        studentRepository.save(student);
        return student;

    }

    public ResponseEntity<?> addStudentWithStudentRequest(StudentRequest studentRequest){
        var student = Student.builder()
                .firstname(studentRequest.getFirstname())
                .dateOfBirth(studentRequest.getDateOfBirth())
                .lastname(studentRequest.getLastname())
                .course(courseRepository.findById(studentRequest.getCourse_id()).get())
                .gender(studentRequest.getGender())
                .build();

        studentRepository.save(student);

        if(studentRepository.findById(student.getId()).isPresent())
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

//    private boolean isStudentPresent(List<Student> students, Student student){
//        for(Student s : students){
//            if(student.equals(s))
//                return true;
//        }
//        return false;
//    }

    public List<Student> findAllByLastName(String lastname) {
        return studentRepository.findAllByLastname(lastname);
    }

    public List<Student> getStudentByGenderAndByCourseType(GenderEnum gender, CourseTypeEnum courseType) {
        var studentsGender = getStudentByGender(gender);

        List<Student> students = new LinkedList<>();

        for(Student s : studentsGender){
            if(s.getCourse().getCourseType() == courseType)
                students.add(s);
        }
        return students;
    }

    private List<Student> getStudentByGender(GenderEnum gender){
        var students = studentRepository.findAllByGender(gender);
        return students;
    }

    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<StudentResponse> result = new ArrayList<>();
        for(Student s : students){
            StudentResponse studentResponse = modelMapper.map(s, StudentResponse.class);
            result.add(studentResponse);
        }
        return result;
    }

    public boolean deleteStudentById(Long id){
        studentRepository.deleteById(id);
        if(studentRepository.findById(id).isPresent())
            return false;
        return true;
    }

    // TUTAJ WYKORZYSTANIE MOJEGO PIEKNEGO QUERY Z REPO
    public String getLastnameById(Long id){
        return studentRepository.findStudentLastNameById(id);
    }
}
