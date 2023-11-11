package com.example.demo.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.teacher.Teacher;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    // Custom query methods (if needed) can be added here
}
