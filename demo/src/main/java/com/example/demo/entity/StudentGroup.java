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

/*@Entity
@Table(name = "student_groups_join")
public class StudentGroupJoin implements Serializable {

    @EmbeddedId
    private StudentGroupId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(name = "status")
    private String status;

    public StudentGroupJoin() {
        this.id = new StudentGroupId();
    }

    public StudentGroupJoin(Student student, Group group, LocalDate enrollmentDate, String status) {
        this.id = new StudentGroupId(student.getId(), group.getId());
        this.student = student;
        this.group = group;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Getters and setters

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}*/

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

