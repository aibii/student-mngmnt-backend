package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.group.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    // Additional query methods can be defined here
}
