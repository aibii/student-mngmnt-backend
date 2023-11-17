package com.example.demo.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.group.Group;
import com.example.demo.teacher.Teacher;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Custom query methods (if needed) can be added here
    Course findByCourseName(String name);
}

