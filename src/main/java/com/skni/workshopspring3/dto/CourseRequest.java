package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CourseRequest {
    private String name;
    private String university;
    private CourseTypeEnum courseType;
    private int duration;
}
