package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.StudentGroup;
import com.example.demo.entity.StudentGroupId;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, StudentGroupId> {
    void deleteByGroupId(Long groupId);
}
