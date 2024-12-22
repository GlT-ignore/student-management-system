package com.studentmanager.repository;

import com.studentmanager.model.Student;
import com.studentmanager.model.Student.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for Student entity
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s ORDER BY s.id")
    List<Student> findAllByOrderByIdAsc();

    Optional<Student> findByEmail(String email);
    List<Student> findByStatus(Status status);
    List<Student> findByGradeGreaterThanEqual(double grade);
    boolean existsById(Integer id);
} 