package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import com.skni.workshopspring3.repo.entity.Student;

import java.util.List;

public class CourseResponse {
    private String name;
    private String university;
    private CourseTypeEnum courseType;
    private int duration;
    private List<Student> students;
}
