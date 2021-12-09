package com.skni.workshopspring3.repo;

import com.skni.workshopspring3.repo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    List<Course> findAll();

    @Override
    List<Course> findAllById(Iterable<Long> longs);

    @Override
    <S extends Course> S save(S entity);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE Course c SET c.name = ?2 WHERE c.id = ?1",
            nativeQuery = true
    )
    void merge(Long id, String name);
}
