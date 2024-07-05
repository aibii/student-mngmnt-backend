package com.example.demo.student;

import java.lang.System.Logger;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        if (studentRepository.existsById(id)) {
            updatedStudent.setId(id);
            return studentRepository.save(updatedStudent);
        } else {
            return null;
        }
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByStatus(String status) {
        return studentRepository.findAll();
    }

    public Student assignStudentToGroup(Long studentId, Long groupId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Group> groupOpt = groupRepository.findById(groupId);

        if (studentOpt.isPresent() && groupOpt.isPresent()) {
            Student student = studentOpt.get();
            Group group = groupOpt.get();
            student.getGroups().add(group);
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student or Group not found");
        }
    }
}

