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
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group saveGroup(Group group) {
        // Assuming group contains teacherId and courseId
        Long teacherId = group.getTeacher().getId();
        Long courseId = group.getCourse().getId();
    
        // Fetch the Teacher and Course from the database
        Teacher teacher = teacherRepository.findById(teacherId)
                         .orElseThrow(() -> new RuntimeException("Teacher not found"));
        Course course = courseRepository.findById(courseId)
                         .orElseThrow(() -> new RuntimeException("Course not found"));
    
        // Set the Teacher and Course in the Group object
        group.setTeacher(teacher);
        group.setCourse(course);
    
        // Save the Group entity
        return groupRepository.save(group);
    }
    
    
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public Group updateGroup(Long id, Group Group) {
        return null;
    } 
}
