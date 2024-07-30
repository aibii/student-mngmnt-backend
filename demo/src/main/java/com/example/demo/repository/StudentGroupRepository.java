package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.class_group.ClassGroup;
import com.example.demo.entity.StudentGroupId;

public interface StudentGroupRepository extends JpaRepository<ClassGroup, Long> {

}
