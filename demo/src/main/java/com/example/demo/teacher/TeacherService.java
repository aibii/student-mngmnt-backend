package com.example.demo.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;


    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        // Fetch the existing teacher by ID (or throw an exception if not found)
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + id));

        // Update the teacher details
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setDateOfBirth(teacher.getDateOfBirth());
        existingTeacher.setAddress(teacher.getAddress());
        existingTeacher.setPhoneNumber(teacher.getPhoneNumber());

        // Save the updated teacher
        return teacherRepository.save(existingTeacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
