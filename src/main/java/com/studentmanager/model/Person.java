package com.studentmanager.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Abstract base class for person entities
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Person {
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    protected String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    protected String email;

    protected Person() {} // Required by JPA

    protected Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public abstract String getRole();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
} 