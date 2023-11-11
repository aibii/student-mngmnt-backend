package com.example.demo.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import com.example.demo.dto.GroupDto;
import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherRepository;

@Service
public class GroupService {

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

    public Group saveGroup(GroupDto groupDto) {
            System.out.println(groupDto);
            // Find the teacher by name
        Teacher teacher = teacherRepository.findByName(groupDto.getTeacherName());
        if (teacher == null) {
            // Handle the case where the teacher is not found
            throw new RuntimeException("Teacher not found"); // Or use a custom exception
        }
        // Find the course by name
        Course course = courseRepository.findByName(groupDto.getCourseName());
        if(course == null) {
            throw new RuntimeException("Course not found"); // Or use a custom exception
        }
            // Create a new Group entity and set its properties from GroupDto
            Group group = new Group();
            group.setGroupName(groupDto.getGroupName());
        group.setTeacherId(teacher.getId()); // Directly set the teacher name
        group.setDescription(groupDto.getDescription());
        group.setCourseId(course.getId()); // Assuming courseId is directly stored
        //group.setStartDate(groupDto.getStartDate());
        //group.setEndDate(groupDto.getEndDate());
    
        // Save the group entity
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public Group updateGroup(Long id, Group Group) {
        return null;
    }


    
}
