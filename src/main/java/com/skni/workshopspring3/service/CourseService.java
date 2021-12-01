package com.skni.workshopspring3.service;

import com.skni.workshopspring3.repo.CourseRepository;
import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public Course addCourse(String name, int duration, String university, CourseTypeEnum courseType) {
        var course = Course.builder()
                .courseType(courseType)
                .duration(duration)
                .name(name)
                .build();

        courseRepository.save(course);

        return course;
    }


}
