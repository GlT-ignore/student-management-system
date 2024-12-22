package com.studentmanager.model;

import jakarta.persistence.*;
import java.io.Serializable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Student entity class that extends Person
 */
@Entity
@Table(name = "students")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student extends Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "Course cannot be null")
    @Size(min = 2, max = 100, message = "Course must be between 2 and 100 characters")
    @Column(nullable = false)
    private String course;
    @Min(value = 0, message = "Grade cannot be less than 0")
    @Max(value = 100, message = "Grade cannot be more than 100")
    @Column(nullable = false)
    private double grade;
    @Column(name = "letter_grade", nullable = false, length = 2)
    private String letterGrade;
    @NotNull(message = "Status cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Student() {
        this.status = Status.ACTIVE;
    }

    @JsonCreator
    public Student(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("course") String course,
        @JsonProperty("grade") double grade) {
        super(name, email);
        this.course = course;
        this.grade = grade;
        this.status = Status.ACTIVE;
        calculateLetterGrade();
    }

    private String calculateLetterGrade(double grade) {
        if (grade >= 90) return "A+";
        if (grade >= 80) return "A";
        if (grade >= 70) return "B";
        if (grade >= 60) return "C";
        if (grade >= 50) return "D";
        if (grade >= 40) return "E";
        return "F";
    }

    public enum Status {
        ACTIVE, INACTIVE, GRADUATED
    }

    @Override
    public String getRole() {
        return "Student";
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCourse() { return course; }
    public double getGrade() { return grade; }
    public String getLetterGrade() { return letterGrade; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public void setCourse(String course) { this.course = course; }
    public void setGrade(double grade) { 
        this.grade = grade;
        this.letterGrade = calculateLetterGrade(grade);
    }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }

    @PrePersist
    @PreUpdate
    private void calculateLetterGrade() {
        if (grade >= 90) this.letterGrade = "A+";
        else if (grade >= 80) this.letterGrade = "A";
        else if (grade >= 70) this.letterGrade = "B";
        else if (grade >= 60) this.letterGrade = "C";
        else if (grade >= 50) this.letterGrade = "D";
        else this.letterGrade = "F";
    }
} 