package com.skni.workshopspring3.controller;

import com.skni.workshopspring3.dto.StudentRequest;
import com.skni.workshopspring3.dto.StudentResponse;
import com.skni.workshopspring3.repo.StudentRepository;
import com.skni.workshopspring3.repo.entity.Student;
import com.skni.workshopspring3.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @GetMapping("/students")
    public List<StudentResponse> getStudents(){return studentService.getAllStudents();}

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Optional<Student> result = studentRepository.findById(id);

        if(result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        if(studentRepository.findById(id).isPresent()){
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // przeczytałam że put jest do większej ilości informacji(?) w sensie patch lepiej użyć przy zmienianiu
    // jednego pola.

    @PatchMapping("/students/{id}/{lastname}")
    public ResponseEntity<?> updateStudentLastName(@PathVariable Long id, @PathVariable String lastname){
        if(!studentRepository.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentRepository.merge(id, lastname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<?> addNewStudent(@RequestBody StudentRequest studentRequest){
        return studentService.addStudentWithStudentRequest(studentRequest);
    }


}
