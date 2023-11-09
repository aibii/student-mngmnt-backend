package com.example.demo.student_course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student_courses")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping
    public List<StudentCourse> getAllStudentCourses() {
        return studentCourseService.getAllStudentCourses();
    }


    @PostMapping
    public StudentCourse createStudentCourse(@RequestBody StudentCourse studentCourse) {
        return studentCourseService.saveStudentCourse(studentCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentCourse> updateStudentCourse(@PathVariable Long id, @RequestBody StudentCourse studentCourse) {
        StudentCourse updatedStudentCourse = studentCourseService.updateStudentCourse(id, studentCourse);
        if (updatedStudentCourse != null) {
            return ResponseEntity.ok().body(updatedStudentCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentCourse(@PathVariable Long id) {
        studentCourseService.deleteStudentCourse(id);
        return ResponseEntity.ok().build();
    }
}
