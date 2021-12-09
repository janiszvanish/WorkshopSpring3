package com.skni.workshopspring3.controller;

import com.skni.workshopspring3.dto.CourseRequest;
import com.skni.workshopspring3.dto.CourseResponse;
import com.skni.workshopspring3.dto.StudentRequest;
import com.skni.workshopspring3.dto.StudentResponse;
import com.skni.workshopspring3.repo.CourseRepository;
import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.Student;
import com.skni.workshopspring3.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CourseController {
    //1 : 1 do StudentService bo jestem len i nie chcialo mi sie wymyslac nic szalonego
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    @GetMapping("/courses")
    public List<CourseResponse> getCourses(){return courseService.getAllCourses();}

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id){
        Optional<Course> result = courseRepository.findById(id);

        if(result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        if(courseRepository.findById(id).isPresent()){
            courseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/courses/{id}/{name}")
    public ResponseEntity<?> updateCourseName(@PathVariable Long id, @PathVariable String name){
        if(!courseRepository.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseRepository.merge(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<?> addNewCourse(@RequestBody CourseRequest courseRequest){
        return courseService.addCourseWithRequest(courseRequest);
    }
}
