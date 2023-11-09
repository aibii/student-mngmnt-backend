package com.example.demo.student_course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAllStudentCourses() {
        return studentCourseRepository.findAll();
    }

    public StudentCourse getStudentCourseById(Long id) {
        return studentCourseRepository.findById(id).orElse(null);
    }

    public StudentCourse saveStudentCourse(StudentCourse studentCourse) {
        return studentCourseRepository.save(studentCourse);
    }

    public void deleteStudentCourse(Long id) {
        studentCourseRepository.deleteById(id);
    }

    public StudentCourse updateStudentCourse(Long id, StudentCourse studentCourse) {
        return null;
    }



    // ... other relevant methods ...
}
