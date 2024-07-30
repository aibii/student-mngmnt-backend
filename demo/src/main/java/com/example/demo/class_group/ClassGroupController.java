package com.example.demo.class_group;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.student.Student;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/api/groups")
public class ClassGroupController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ClassGroupController.class);

    @Autowired
    private ClassGroupService classGroupService;

    @GetMapping
    public List<ClassGroup> getAllGroups() {
        return classGroupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassGroup> getGroupById(@PathVariable Long id) {
        ClassGroup classGroup = classGroupService.getGroupById(id);
        return ResponseEntity.ok(classGroup);
    }

    @PostMapping
    public ResponseEntity<ClassGroup> createGroup(@RequestBody ClassGroup classGroup) {
    logger.debug("Received group: {}", classGroup);
    logger.debug("Course ID: {}", classGroup.getCourseId());
    
    if (classGroup.getGroupName() == null || classGroup.getTeacherId() == null || classGroup.getCourseId() == null || classGroup.getMonthlyFee() == null) {
        logger.error("Missing required field(s)");
        return ResponseEntity.badRequest().body(null);
    }
    
    ClassGroup savedGroup = classGroupService.saveGroup(classGroup);
    return ResponseEntity.ok(savedGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable Long id, @RequestBody ClassGroup classGroup) {
        try {
        // Optional: Validate the ID and the classGroup object
        classGroup.setId(id); // Ensure the ID in the path and the entity match
        ClassGroup updatedGroup = classGroupService.updateGroup(classGroup);
        return ResponseEntity.ok(updatedGroup);
    } 
    catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        classGroupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}
