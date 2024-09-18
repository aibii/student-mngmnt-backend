package com.example.demo.service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
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

    // Initialize the logger
    private static final Logger logger = LoggerFactory.getLogger(StudentGroupService.class);

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
    
    
    // Helper method to map string to DayOfWeek enum
    private DayOfWeek mapStringToDayOfWeek(String day) {
        switch (day.toLowerCase()) {
            case "mon": return DayOfWeek.MONDAY;
            case "tue": return DayOfWeek.TUESDAY;
            case "wed": return DayOfWeek.WEDNESDAY;
            case "thu": return DayOfWeek.THURSDAY;
            case "fri": return DayOfWeek.FRIDAY;
            case "sat": return DayOfWeek.SATURDAY;
            case "sun": return DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("Unknown day: " + day);
        }

    }

    public LocalDate calculateNextLessonDate(LocalDate startDate, List<DayOfWeek> lessonDays) {
        DayOfWeek currentDay = startDate.getDayOfWeek();
        
        // Find the next lesson day in the list that comes after the current day
        for (DayOfWeek lessonDay : lessonDays) {
            if (lessonDay.compareTo(currentDay) > 0) {
                return startDate.with(TemporalAdjusters.next(lessonDay));
            }
        }
        
        // If no lesson is after the current day, return the first lesson day of next week
        return startDate.with(TemporalAdjusters.next(lessonDays.get(0)));
    }
    

    // Assign a student to a group and calculate their remaining lessons and debt based on the provided start date
public StudentGroup assignStudentToGroup(Long studentId, Long groupId, LocalDate startDate) {
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));
    ClassGroup classGroup = classGroupRepository.findById(groupId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));

    LocalDate endDate = classGroup.getEndDate().toLocalDate();

    // Log start date and group details
    logger.info("Assigning student '{}' to group '{}', start date: {}", student.getFirstName(), classGroup.getGroupName(), startDate);
    logger.info("Class group schedule: {}, end date: {}", classGroup.getSchedule(), endDate);

    // Create the student group with the provided start date
    StudentGroup studentGroup = new StudentGroup(new StudentGroupId(studentId, groupId), student, classGroup, startDate, null);
    studentGroupRepository.save(studentGroup);

    // Calculate remaining lessons based on the provided start date
    int totalLessons = 12;  // Assuming 12 lessons per month
    int remainingLessons = calculateRemainingLessons(startDate, endDate, classGroup.getSchedule(), classGroup.getLessonTime());

    // Log lessons info
    logger.info("Total lessons in month: {}, Remaining lessons for student: {}", totalLessons, remainingLessons);

    // Calculate the total debt
    double lessonPrice = classGroup.getMonthlyFee() / totalLessons;
    double totalDebt = remainingLessons * lessonPrice;

    // Log payment calculation
    logger.info("Monthly fee: {}, Lesson price: {}, Calculated debt for student: {}", classGroup.getMonthlyFee(), lessonPrice, totalDebt);

    // Create the payment record
    Payment payment = new Payment();
    payment.setStudent(student);
    payment.setClassGroup(classGroup);
    payment.setAmount(totalDebt);
    payment.setStatus(PaymentStatus.DUE);
    payment.setDatePaid(null);  // No payment yet
    payment.setDueDate(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()));  // Due at end of month

    paymentRepository.save(payment);

    logger.info("Created payment record with debt: {}", payment.getAmount());

    return studentGroup;
}

    public int calculateRemainingLessons(LocalDate startDate, LocalDate endDate, String schedule, LocalTime lessonTime) {
    // Split schedule string and map to DayOfWeek
    List<DayOfWeek> daysOfWeek = Arrays.stream(schedule.split("-"))
            .map(day -> mapStringToDayOfWeek(day))
            .collect(Collectors.toList());

    // Initialize remaining lessons count
    int remainingLessons = 0;
    LocalDate currentDate = startDate;
    
    // Get the current time
    LocalTime currentTime = LocalTime.now();

    // Ensure endDate is not after the end of the current month
    LocalDate lastDayOfMonth = startDate.withDayOfMonth(startDate.lengthOfMonth());
    endDate = endDate.isAfter(lastDayOfMonth) ? lastDayOfMonth : endDate;

    // Loop through dates and count the days that match the schedule
    while (!currentDate.isAfter(endDate)) {
        if (daysOfWeek.contains(currentDate.getDayOfWeek())) {
            // Check if it's the current day and the lesson time has already passed
            if (currentDate.equals(LocalDate.now()) && currentTime.isAfter(lessonTime)) {
                // Skip today if the lesson has already happened
                logger.info("Skipping today's lesson as it has already passed.");
            } else {
                remainingLessons++;
            }
        }
        currentDate = currentDate.plusDays(1);
    }

    // Log remaining lessons calculation
    logger.info("Start date: {}, End date: {}, Days of lessons: {}, Remaining lessons: {}",
                startDate, endDate, daysOfWeek, remainingLessons);

    return remainingLessons;
}
    


    // Query to get the total debt for a student in a specific group
    public Double calculateDebtForStudentInGroup(Long studentId, Long groupId) {
        Double debt = paymentRepository.calculateDebtForStudentInGroup(studentId, groupId);

        if (debt == null) {
            debt = 0.0;  // No debt if no payment found
        }

        logger.info("Calculated debt for student {} in group {}: {}", studentId, groupId, debt);
        return debt;
    }

    public List<StudentGroup> getStudentGroupsByStudentId(Long studentId) {
        return studentGroupRepository.findByStudentId(studentId);
    }

    public boolean existsById(Long studentId) {
        return studentRepository.existsById(studentId);
    }

    // Method to save the StudentGroup entity to the database
    public StudentGroup save(StudentGroup studentGroup) {
        return studentGroupRepository.save(studentGroup);
    }
}

