package com.studentmanager.service;

import com.studentmanager.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentmanager.repository.StudentRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Service class handling student operations
 */
@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAllByOrderByIdAsc();
    }

    @Transactional
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        logger.info("Attempting to delete student with ID: {}", id);
        
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isEmpty()) {
            logger.error("Student not found with ID: {}", id);
            throw new RuntimeException("Student not found with id: " + id);
        }

        studentRepository.deleteById(id);
        logger.info("Student deleted, now resequencing IDs");
        
        // Get all remaining students and resequence their IDs
        List<Student> allStudents = studentRepository.findAllByOrderByIdAsc();
        int newId = 1;
        for (Student student : allStudents) {
            entityManager.createNativeQuery("UPDATE students SET id = ?1 WHERE email = ?2")
                .setParameter(1, newId)
                .setParameter(2, student.getEmail())
                .executeUpdate();
            newId++;
        }

        // Reset the auto-increment
        try {
            entityManager.createNativeQuery(
                "ALTER TABLE students ALTER COLUMN id RESTART WITH " + newId
            ).executeUpdate();
        } catch (Exception e) {
            logger.warn("Could not reset sequence. This is expected for some databases.", e);
        }

        // Clear persistence context to ensure fresh data
        entityManager.flush();
        entityManager.clear();
    }
} 