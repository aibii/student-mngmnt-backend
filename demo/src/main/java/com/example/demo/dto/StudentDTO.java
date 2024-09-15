package com.example.demo.dto;

import java.util.Date;

public class StudentDTO {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private Date enrollmentDate;
    private Double debt;

    // Constructor, getters, and setters
    public StudentDTO(Long studentId, String studentFirstName, String studentLastName, java.util.Date enrollmentDate, Double debt) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.enrollmentDate = enrollmentDate;  // No casting needed
        this.debt = debt;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }
}