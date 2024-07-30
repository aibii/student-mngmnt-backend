package com.example.demo.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

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

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
