package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentGroupId implements Serializable {

    private Long studentId;
    private Long groupId;

    // Constructors
    public StudentGroupId() {}

    public StudentGroupId(Long studentId, Long groupId) {
        this.studentId = studentId;
        this.groupId = groupId;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    // Override equals and hashCode for proper comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroupId that = (StudentGroupId) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, groupId);
    }
}



