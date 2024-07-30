package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentGroupId;
import com.example.demo.repository.StudentGroupRepository;

@Service
public class StudentGroupService {

    @Autowired
    private StudentGroupRepository repository;

    public void removeStudentFromGroup(Long studentId, Long groupId) {
        //StudentGroupId id = new StudentGroupId(studentId, groupId);
        //repository.deleteById(id);
    }
}

