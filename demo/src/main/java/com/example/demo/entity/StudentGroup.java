package com.example.demo.entity;

import com.example.demo.group.Group;
import com.example.demo.student.Student;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_groups_join")
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
    private Group group;

    public StudentGroup() {
        this.id = new StudentGroupId();
    }

    public StudentGroup(Student student, Group group) {
        this.id = new StudentGroupId(student.getId(), group.getId());
        this.student = student;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}



