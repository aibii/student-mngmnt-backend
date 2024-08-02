package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.class_group.ClassGroup;
import com.example.demo.class_group.ClassGroupRepository;
import com.example.demo.entity.StudentGroup;
import com.example.demo.entity.StudentGroupId;
import com.example.demo.repository.StudentGroupRepository;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;

@Service
public class StudentGroupService {

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;

    public List<StudentGroup> getAllStudentGroups() {
        return studentGroupRepository.findAll();
    }

    public StudentGroup addStudentToGroup(StudentGroup studentGroup) {
        return studentGroupRepository.save(studentGroup);
    }

    public void removeStudentFromGroup(StudentGroupId id) {
        studentGroupRepository.deleteById(id);
    }

    public StudentGroup assignStudentToGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));
        ClassGroup classGroup = classGroupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));

        StudentGroup studentGroup = new StudentGroup(new StudentGroupId(studentId, groupId), student, classGroup, LocalDate.now(), null);
        return studentGroupRepository.save(studentGroup);
    }

    public List<StudentGroup> getStudentGroupsByStudentId(Long studentId) {
        return studentGroupRepository.findByStudentId(studentId);
    }
}

