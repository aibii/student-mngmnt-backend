package com.example.demo.group;

import java.util.List;

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

import com.example.demo.dto.GroupDto;



@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

   @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody GroupDto groupDto) {
        try {
            Group createdGroup = groupService.saveGroup(groupDto);
            // Optionally, convert the created group to a DTO before returning
            // GroupDto createdGroupDto = convertToDto(createdGroup);

            // Return the created group (or DTO)
            return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions (e.g., entity not found, validation errors)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
