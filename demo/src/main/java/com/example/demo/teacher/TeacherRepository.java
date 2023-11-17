package com.example.demo.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Custom query methods (if needed) can be added here
    Teacher findByFirstName(String name);
}
