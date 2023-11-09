package com.example.demo.student;

import java.lang.System.Logger;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    //private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        //logger.info("Saving student with details: {}", student);
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        if (studentRepository.existsById(id)) {
            //logger.info("Updating student with ID: {}", id);
            return studentRepository.save(updatedStudent);
        } else {
           // logger.error("Student with ID {} not found", id);
            // You might want to throw an exception here or handle it in some way
            return null;
        }
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
           // logger.info("Deleting student with ID: {}", id);
            studentRepository.deleteById(id);
        } else {
          //  logger.error("Student with ID {} not found", id);
            // Handle this case as you see fit (e.g., throw an exception)
        }
    }

    public List<Student> getStudentsByStatus(String status) {
       // logger.info("Fetching students with status: {}", status);
        return studentRepository.findAll();
    }

    // Implement any other student-related business logic here.
}
