package com.example.demo.class_group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {
    // Additional query methods can be defined here if needed
    
}
