package com.skni.workshopspring3.controller;

import com.skni.workshopspring3.dto.StudentRequest;
import com.skni.workshopspring3.dto.StudentResponse;
import com.skni.workshopspring3.repo.StudentRepository;
import com.skni.workshopspring3.repo.entity.Student;
import com.skni.workshopspring3.service.StudentService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @GetMapping("/students")
    public List<StudentResponse> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> result = studentRepository.findById(id);

        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // przeczytałam że put jest do większej ilości informacji(?) w sensie patch lepiej użyć przy zmienianiu
    // jednego pola.

    @PatchMapping("/students/{id}/{lastname}")
    public ResponseEntity<?> updateStudentLastName(@PathVariable Long id, @PathVariable @NotBlank @Size(min = 3, max = 30) String lastname) {
        if (!studentRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentRepository.merge(id, lastname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<?> addNewStudent(@Valid @RequestBody StudentRequest studentRequest) {
        return studentService.addStudentWithStudentRequest(studentRequest);
    }

    // szczerze zerżnęłam wszystko z waszego kodu bo nie bardzo mam pomysł na to jak inaczej to zzrobić
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
