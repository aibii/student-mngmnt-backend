package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.StudentGroup;
import com.example.demo.entity.StudentGroupId;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, StudentGroupId> {
    @Query("SELECT sg FROM StudentGroup sg WHERE sg.id.studentId = :studentId")
    List<StudentGroup> findByStudentId(@Param("studentId") Long studentId);
}
