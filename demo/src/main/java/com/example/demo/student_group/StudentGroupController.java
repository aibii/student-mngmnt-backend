package com.example.demo.student_group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StudentGroupService;

@RestController
@RequestMapping("/api/students")
public class StudentGroupController {

    @Autowired
    private StudentGroupService service;

    @DeleteMapping("/{studentId}/groups/{groupId}")
    public void removeStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
    service.removeStudentFromGroup(studentId, groupId);
}
}


