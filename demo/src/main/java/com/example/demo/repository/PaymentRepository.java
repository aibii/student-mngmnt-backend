package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentStudentId(Long studentId);

    // Custom query to calculate the total debt for a student in a specific group
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.student.studentId = :studentId AND p.classGroup.id = :groupId AND p.status = 'DUE'")
    Double calculateDebtForStudentInGroup(@Param("studentId") Long studentId, @Param("groupId") Long groupId);
}