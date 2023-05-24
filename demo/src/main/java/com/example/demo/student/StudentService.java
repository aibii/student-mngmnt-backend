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

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) throws StudentNotFoundException {
        Optional<Student> result = studentRepository.findById(id);
        if(result.isPresent())
        {
            return result.get();
        }
        else
        {
            throw new StudentNotFoundException("Could not find any students with id" + id);
        }
    }

public Student updateStudent(Student student) throws StudentNotFoundException {
    Optional<Student> existingStudentOpt = studentRepository.findById(student.getId());

    if (existingStudentOpt.isPresent()) {
        Student existingStudent = existingStudentOpt.get();
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDob(student.getDob());

        return studentRepository.save(existingStudent);
    } else {
        throw new StudentNotFoundException("Student not found");
    }
}

}
