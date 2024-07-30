package com.example.demo.student;

import java.lang.System.Logger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.net.http.HttpClient;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.class_group.ClassGroup;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        if (student.getRegistrationDate() == null) {
            student.setRegistrationDate(new Date());  // Set to current date
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        // Fetch the existing student from the database
        Optional<Student> existingStudentOptional = studentRepository.findById(student.getStudentId());
        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            
            // Retain the original registration date
            student.setRegistrationDate(existingStudent.getRegistrationDate());
            
            // Proceed with updating other fields
            return studentRepository.save(student);
        } else {
            throw new EntityNotFoundException("Student not found with ID: " + student.getStudentId());
        }
    }


    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

