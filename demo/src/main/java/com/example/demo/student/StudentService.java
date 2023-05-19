package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service //a service class. SERVICE LAYER, business logic
public class StudentService { //a class that needs to be instantiated, i.e needs to be a spring bean
    
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

	public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
	}

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(exists) {
            studentRepository.deleteById(studentId);
        }
        else {
            throw new IllegalStateException(
                "student with id " + studentId + "does not exists");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Optional<Student> studentToUpdate = studentRepository.findById(studentId);
        if (studentToUpdate.isEmpty()) {
            throw new IllegalStateException("Student with id " + studentId + "does not exists");
        }
        Student student = studentToUpdate.get();
        if(name != null && name.length() > 0 &&
            !Objects.equals(student.getName(), name)) {
                student.setName(name);
            }
        if(email != null && email.length() > 0 &&
            !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setName(name);
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
