package com.example.demo.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (teacher.getFirstName() == null) {
            throw new IllegalArgumentException("Teacher's first name must not be null.");
        }
        System.out.println(teacher);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public Object updateTeacher(Long id, Teacher teacher) {
        return null;
    }

    // ... other relevant methods ...
}
