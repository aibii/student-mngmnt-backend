package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


//data access layer, will work with databases
//this interface is responsible for data access

@Repository //repository responsible for data access operations
public interface StudentRepository
       extends JpaRepository<Student, Long> { //inherits various CRUD (Create, Read, Update, Delete) operations and additional query methods.
    
//  SELECT * from student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1") //Student is a class here
    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findById(Long id);
}
