package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Save a new payment
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        System.out.println("Fetched payments: " + payments);
        return payments;
    }
    

    // Get payments by student
    public List<Payment> getPaymentsByStudent(Long studentId) {
        return paymentRepository.findByStudentStudentId(studentId);
    }

    // Delete a payment
    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    public Double getStudentDebtInGroup(Long studentId, Long groupId) {
        return paymentRepository.calculateDebtForStudentInGroup(studentId, groupId);
    }
}