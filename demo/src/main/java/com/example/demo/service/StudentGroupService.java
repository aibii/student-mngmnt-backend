package com.example.demo.service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.class_group.ClassGroup;
import com.example.demo.class_group.ClassGroupRepository;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Payment.PaymentStatus;
import com.example.demo.entity.StudentGroup;
import com.example.demo.entity.StudentGroupId;
import com.example.demo.repository.StudentGroupRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;

@Service
public class StudentGroupService {

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<StudentGroup> getAllStudentGroups() {
        return studentGroupRepository.findAll();
    }

    public StudentGroup addStudentToGroup(StudentGroup studentGroup) {
        return studentGroupRepository.save(studentGroup);
    }

    public void removeStudentFromGroup(StudentGroupId id) {
        studentGroupRepository.deleteById(id);
    }

    public int calculateRemainingLessons(LocalDate startDate, Date groupStartDate) {
    // Convert java.util.Date to LocalDate for consistency
    LocalDate groupStartLocalDate = new java.sql.Date(groupStartDate.getTime()).toLocalDate();

    // Ensure the calculation starts from the later of the group start date or the student's start date
    LocalDate effectiveStartDate = (startDate.isAfter(groupStartLocalDate)) ? startDate : groupStartLocalDate;

    // Get the last day of the current month
    LocalDate endOfMonth = effectiveStartDate.withDayOfMonth(effectiveStartDate.lengthOfMonth());

    // Count the remaining lessons (e.g., Mondays, Wednesdays, Fridays)
    int remainingLessons = 0;
    LocalDate currentDate = effectiveStartDate;

    while (!currentDate.isAfter(endOfMonth)) {
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        // Assuming lessons are on Monday, Wednesday, and Friday
        if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.FRIDAY) {
            remainingLessons++;
        }
        // Move to the next day
        currentDate = currentDate.plus(1, ChronoUnit.DAYS);
    }

    return remainingLessons;
    }

    public StudentGroup assignStudentToGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));
        ClassGroup classGroup = classGroupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));
    
        // Assign the student to the group
        StudentGroup studentGroup = new StudentGroup(new StudentGroupId(studentId, groupId), student, classGroup, LocalDate.now(), null);
        studentGroupRepository.save(studentGroup);
    
        // Calculate remaining lessons and create a payment with status "DUE"
        int remainingLessons = calculateRemainingLessons(LocalDate.now(), classGroup.getStartDate());
        double lessonPrice = classGroup.getMonthlyFee() / 12;  // Assuming 12 lessons per month
        double totalDebt = remainingLessons * lessonPrice;
    
        // Create payment with status "DUE"
        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setClassGroup(classGroup);
        payment.setAmount(totalDebt);
        payment.setStatus(PaymentStatus.DUE);  // Status should be DUE by default
        payment.setDatePaid(null);  // No payment yet
        payment.setDueDate(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()));  // End of month
    
        paymentRepository.save(payment);
    
        System.out.println("Created payment with status: " + payment.getStatus());

        return studentGroup;
    }

    public List<StudentGroup> getStudentGroupsByStudentId(Long studentId) {
        return studentGroupRepository.findByStudentId(studentId);
    }
}

