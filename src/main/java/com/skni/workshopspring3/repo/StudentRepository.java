package com.skni.workshopspring3.repo;

import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import com.skni.workshopspring3.repo.entity.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Override
    List<Student> findAll();

    @Override
    List<Student> findAllById(Iterable<Long> longs);

    @Override
    Student getById(Long aLong);

    @Override
    <S extends Student> List<S> findAll(Example<S> example);

    List<Student> findAllByLastname(String lastname);

    List<Student> findAllByGender(GenderEnum gender);

    @Override
    <S extends Student> S save(S entity);

//    @Override
//    Optional<Student> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Query(
            value = "SELECT s.lastname FROM Student s WHERE s.id=?1",
            nativeQuery = true)
    String findStudentLastNameById(Long id);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE Student s SET s.lastname = ?2 WHERE s.id = ?1",
            nativeQuery = true
    )
    void merge(Long id, String lastname);
}
