package com.skni.workshopspring3.repo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
@Builder
@AllArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private int duration;

    @Column(name = "university")
    private String university;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_type")
    private CourseTypeEnum courseType;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    List<Student> students;
}