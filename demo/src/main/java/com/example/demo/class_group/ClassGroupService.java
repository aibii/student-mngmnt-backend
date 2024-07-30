package com.example.demo.class_group;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassGroupService {
    @Autowired
    private ClassGroupRepository classGroupRepository;

    public List<ClassGroup> getAllGroups() {
        return classGroupRepository.findAll();
    }

    public ClassGroup getGroupById(Long id) {
        return classGroupRepository.findById(id).orElse(null);
    }

    public ClassGroup saveGroup(ClassGroup classGroup) {
        return classGroupRepository.save(classGroup);
    }

    public ClassGroup updateGroup(ClassGroup classGroup) {
        // Fetch the existing group by ID (or throw an exception if not found)
        Optional<ClassGroup> existingGroup = classGroupRepository.findById(classGroup.getId());
        if (!existingGroup.isPresent()) {
            throw new RuntimeException("Group not found with ID: " + classGroup.getId());
        }

        // Update the group details
        ClassGroup groupToUpdate = existingGroup.get();
        groupToUpdate.setGroupName(classGroup.getGroupName());
        groupToUpdate.setTeacherId(classGroup.getTeacherId());
        groupToUpdate.setCourseId(classGroup.getCourseId());
        groupToUpdate.setDescription(classGroup.getDescription());
        groupToUpdate.setStartDate(classGroup.getStartDate());
        groupToUpdate.setEndDate(classGroup.getEndDate());
        groupToUpdate.setMonthlyFee(classGroup.getMonthlyFee());

        // Save the updated group
        return classGroupRepository.save(groupToUpdate);
    }

    public void deleteGroup(Long id) {
        classGroupRepository.deleteById(id);
    }
}
