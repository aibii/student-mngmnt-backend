package com.example.demo.class_group;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GroupWithStudentsDto;
import com.example.demo.dto.StudentDTO;
import com.example.demo.repository.PaymentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassGroupService {
    @Autowired
    private ClassGroupRepository classGroupRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<ClassGroup> getAllGroups() {
        return classGroupRepository.findAll();
    }

    public ClassGroup getGroupById(Long id) {
        return classGroupRepository.findById(id).orElse(null);
    }

    public ClassGroup saveGroup(ClassGroup classGroup) {
        return classGroupRepository.save(classGroup);
    }

    public List<GroupWithStudentsDto> getAllGroupsWithStudents() {
        List<Object[]> results = classGroupRepository.findAllGroupsWithStudentsNative();
        Map<Long, GroupWithStudentsDto> groupMap = new HashMap<>();
    
        for (Object[] result : results) {
            Long groupId = (Long) result[0];
            String groupName = (String) result[1];
            Long studentId = (Long) result[2];
            String studentFirstName = (String) result[3];
            String studentLastName = (String) result[4];
            java.util.Date enrollmentDate = (java.util.Date) result[5];
    
            // Calculate the debt for this student in the group
            Double debt = paymentRepository.calculateDebtForStudentInGroup(studentId, groupId);
    
            // Get or create the group DTO
            GroupWithStudentsDto groupDTO = groupMap.computeIfAbsent(groupId, id -> new GroupWithStudentsDto(groupId, groupName, "Teacher Name"));
    
            // Add the student with the calculated debt
            groupDTO.getStudents().add(new StudentDTO(studentId, studentFirstName, studentLastName, enrollmentDate, debt));
        }
    
        return new ArrayList<>(groupMap.values());
    }

    public ClassGroup updateGroup(ClassGroup classGroup) {
    // Fetch the existing group by ID (or throw a more specific exception if not found)
    ClassGroup existingGroup = classGroupRepository.findById(classGroup.getId())
        .orElseThrow(() -> new EntityNotFoundException("Group not found with ID: " + classGroup.getId()));

    // Update the group details
    existingGroup.setGroupName(classGroup.getGroupName());
    existingGroup.setTeacherId(classGroup.getTeacherId());
    existingGroup.setCourseId(classGroup.getCourseId());
    existingGroup.setDescription(classGroup.getDescription());
    existingGroup.setStartDate(classGroup.getStartDate());
    existingGroup.setEndDate(classGroup.getEndDate());
    existingGroup.setMonthlyFee(classGroup.getMonthlyFee());

    // Save the updated group
    return classGroupRepository.save(existingGroup);
}

    public void deleteGroup(Long id) {
        classGroupRepository.deleteById(id);
    }
}
