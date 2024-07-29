package com.example.demo.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "class_groups")
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groupName;

    //@ManyToOne
    //@JoinColumn(name = "teacher_id", nullable = false)
    //private Teacher teacher;

    //@ManyToOne
    //@JoinColumn(name = "course_id", nullable = false)
    //private Course course;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(nullable = false)
    private Double monthlyFee;

    @OneToMany(mappedBy = "classGroup")
    private Set<StudentGroup> studentGroups;

    // Getters and Setters
}

