package com.skni.workshopspring3.service;

import com.skni.workshopspring3.dto.CourseRequest;
import com.skni.workshopspring3.dto.CourseResponse;
import com.skni.workshopspring3.repo.CourseRepository;
import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public Course addCourse(String name, int duration, String university, CourseTypeEnum courseType) {
        var course = Course.builder()
                .courseType(courseType)
                .duration(duration)
                .name(name)
                .build();

        courseRepository.save(course);

        return course;
    }
    public ResponseEntity<?> addCourseWithRequest(CourseRequest courseRequest) {
        var course = Course.builder()
                .courseType(courseRequest.getCourseType())
                .name(courseRequest.getName())
                .duration(courseRequest.getDuration())
                .university(courseRequest.getUniversity())
                .build();

        courseRepository.save(course);
        if(courseRepository.findById(course.getId()).isPresent())
            return new ResponseEntity<>(HttpStatus.OK);

        return  new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        List<CourseResponse> result = new ArrayList<>();
        for(Course c : courses){
            CourseResponse courseResponse = modelMapper.map(c, CourseResponse.class);
            result.add(courseResponse);
        }

        return result;
    }
}
