package com.example.demo.student_course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    // Custom query methods (if needed) can be added here
}
