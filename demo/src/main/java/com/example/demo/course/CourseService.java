package com.example.demo.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.class_group.ClassGroupRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassGroupRepository groupRepository;

    public List<Course> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        courses.forEach(course -> System.out.println("Fetched course: " + course));
        return courses;
    }
    
    public Course getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        System.out.println("Course fetched by ID: " + id + ", Course: " + course);
        return course;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setStartDate(course.getStartDate());
        existingCourse.setEndDate(course.getEndDate());
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        boolean hasAssociatedGroups = groupRepository.existsById(id);
        if (hasAssociatedGroups) {
            throw new RuntimeException("Cannot delete course. Please delete associated groups first.");
        }
        courseRepository.deleteById(id);
    }
}
