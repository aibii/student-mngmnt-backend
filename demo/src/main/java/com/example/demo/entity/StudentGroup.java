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
public class StudentGroup implements Serializable {

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
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    // Getters and Setters
}

