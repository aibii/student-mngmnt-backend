package com.example.demo.student_group;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.class_group.ClassGroupService;
import com.example.demo.entity.StudentGroup;
import com.example.demo.entity.StudentGroupId;
import com.example.demo.service.StudentGroupService;


@RestController
@RequestMapping("/api/student-groups")
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    private final ClassGroupService classGroupService;

    public StudentGroupController(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
        this.classGroupService = null;
    }

    @GetMapping
    public List<StudentGroup> getAllStudentGroups() {
        return studentGroupService.getAllStudentGroups();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentGroup>> getStudentGroups(@PathVariable Long studentId) {
        List<StudentGroup> studentGroups = studentGroupService.getStudentGroupsByStudentId(studentId);
        return ResponseEntity.ok(studentGroups);
    }

    @PostMapping
public ResponseEntity<StudentGroup> assignStudentToGroup(@RequestBody StudentGroup studentGroup) {
    // Debugging line to check the incoming data
    System.out.println("Received StudentGroup: " + studentGroup);

    if (studentGroup.getId() == null) {
        return ResponseEntity.badRequest().body(null);
    }

    Long studentId = studentGroup.getId().getStudentId();
    Long groupId = studentGroup.getId().getGroupId();
    
    // Assign student to group using the start date from the request body
    StudentGroup savedGroup = studentGroupService.assignStudentToGroup(studentId, groupId, studentGroup.getStartDate());

    // Return the saved group
    return ResponseEntity.ok(savedGroup);
    }

    @DeleteMapping("/{studentId}/{groupId}")
    public ResponseEntity<Void> removeStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        StudentGroupId id = new StudentGroupId(studentId, groupId);
        studentGroupService.removeStudentFromGroup(id);
        return ResponseEntity.noContent().build();
    }
}


