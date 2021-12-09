package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import com.skni.workshopspring3.repo.entity.Student;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponse {
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private GenderEnum gender;
}
