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
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setGender(updatedStudent.getGender());
            student.setSchool(updatedStudent.getSchool());
            student.setGrade(updatedStudent.getGrade());
            student.setSession(updatedStudent.getSession());
            student.setDateOfBirth(updatedStudent.getDateOfBirth());
            student.setAddress(updatedStudent.getAddress());
            student.setStudentPhone(updatedStudent.getStudentPhone());
            student.setParentPhone(updatedStudent.getParentPhone());
            student.setStatus(updatedStudent.getStatus());
            student.setRegistrationDate(updatedStudent.getRegistrationDate());
            return studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student assignStudentToGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with id: " + groupId));
    
        if (!student.getGroups().contains(group)) {
            student.getGroups().add(group);
            group.getStudents().add(student);
            studentRepository.save(student);
            groupRepository.save(group);
        }
        return student;
    }
    
    public void removeStudentFromGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with id: " + groupId));
    
        if (student.getGroups().contains(group)) {
            student.getGroups().remove(group);
            group.getStudents().remove(student);
            studentRepository.save(student);
            groupRepository.save(group);
        }
    }
}

