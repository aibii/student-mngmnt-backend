package com.example.demo.group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.teacher.Teacher;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
    @Query("SELECT g FROM Group g JOIN FETCH g.teacher JOIN FETCH g.course")
    List<Group> findAllWithDetails();
    
    // Other custom query methods (if needed) can be added here
}
