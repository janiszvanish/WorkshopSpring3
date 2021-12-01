package com.skni.workshopspring3.repo;

import com.skni.workshopspring3.repo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    List<Course> findAll();

    @Override
    List<Course> findAllById(Iterable<Long> longs);

    @Override
    <S extends Course> S save(S entity);
}
