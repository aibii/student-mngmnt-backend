package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentGroupId implements Serializable {

    private Long studentId;
    private Long groupId;

    // Default constructor
    public StudentGroupId() {}

    // Parameterized constructor
    public StudentGroupId(Long studentId, Long groupId) {
        this.studentId = studentId;
        this.groupId = groupId;
    }

    // Getters and Setters, equals(), and hashCode() methods
}



