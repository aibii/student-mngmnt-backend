package com.example.demo.dto;

import java.time.LocalDate;

public class GroupDto {
    private String groupName;
    private String teacherName;
    private String description;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructor
    public GroupDto() {
        // Default constructor
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseId) {
        this.courseName = courseName;
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

    // Optionally, you can override toString() for debugging purposes
    @Override
    public String toString() {
        return "GroupDto{" +
                "groupName='" + groupName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", description='" + description + '\'' +
                ", courseId=" + courseName +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}