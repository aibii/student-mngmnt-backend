package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import com.example.demo.class_group.ClassGroup;
import com.example.demo.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "student_group")
public class StudentGroup {

    @EmbeddedId
    private StudentGroupId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private ClassGroup classGroup;

    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    // Constructors, Getters, and Setters
    public StudentGroup() {}

    /*public StudentGroup(Student student, ClassGroup classGroup, Date startDate) {
        this.student = student;
        this.classGroup = classGroup;
        this.startDate = startDate;
        this.id = new StudentGroupId(student.getStudentId(), classGroup.getId());
    }*/

    public StudentGroup(StudentGroupId id, Student student, ClassGroup classGroup, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.student = student;
        this.classGroup = classGroup;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public StudentGroupId getId() {
        return id;
    }

    public void setId(StudentGroupId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassGroup getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

