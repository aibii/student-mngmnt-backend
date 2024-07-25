package com.example.demo.group;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.StudentGroupRepository;
import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherRepository;

import jakarta.transaction.Transactional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Transactional
    public void deleteGroup(Long id) {
        studentGroupRepository.deleteByGroupId(id); // Remove associations
        groupRepository.deleteById(id);             // Delete the group
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}

