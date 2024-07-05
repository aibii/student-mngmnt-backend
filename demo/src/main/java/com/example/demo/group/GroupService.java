package com.example.demo.group;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherRepository;

import jakarta.transaction.Transactional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public Group updateGroup(Long id, Group updatedGroup) {
        if (groupRepository.existsById(id)) {
            updatedGroup.setId(id);
            return groupRepository.save(updatedGroup);
        } else {
            return null;
        }
    }
}

