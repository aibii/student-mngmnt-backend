package com.example.demo.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GroupWithStudentsDto {
    private Long groupId;
    private String groupName;
    private String teacherName;  // Add the teacher's name if needed
    private List<StudentDTO> students;  // List of students
    private Double monthlyFee;  // Add monthly fee
    private int totalLessonsInMonth = 8;  // Total lessons in a month

    public GroupWithStudentsDto(Long groupId, String groupName, String teacherName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.teacherName = teacherName;
        this.students = new ArrayList<>();  // Initialize the list
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}
