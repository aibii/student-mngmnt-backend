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

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAllWithDetails();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group saveGroup(Group group) {
        logger.debug("Saving group: {}", group);
    
        Teacher teacher = teacherRepository.findById(group.getTeacher().getId())
                        .orElseThrow(() -> new RuntimeException("Teacher not found"));
        logger.debug("Fetched teacher: {}", teacher);
    
        Course course = courseRepository.findById(group.getCourse().getId())
                        .orElseThrow(() -> new RuntimeException("Course not found"));
        logger.debug("Fetched course: {}", course);
    
        group.setTeacher(teacher);
        group.setCourse(course);
    
        Group savedGroup = groupRepository.save(group);
        logger.debug("Group saved with ID: {}", savedGroup.getId());
    
        return savedGroup;
    }
    
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public Group updateGroup(Long id, Group Group) {
        return null;
    } 
}
