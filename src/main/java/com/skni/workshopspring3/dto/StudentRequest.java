package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StudentRequest {
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private GenderEnum gender;
    private Long course_id;

}
