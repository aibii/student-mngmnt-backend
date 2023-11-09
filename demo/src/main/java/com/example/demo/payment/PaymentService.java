package com.example.demo.payment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        if(paymentRepository.existsById(id)) {
            paymentDetails.setId(id); // Assuming Payment class has a setId() method
            return paymentRepository.save(paymentDetails);
        }
        return null; // or throw an exception based on your design choice
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // Implement other payment-related business logic here if needed.
}
