package com.example.demo.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.group.Group;
import com.example.demo.payment.Payment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data  // Generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor  // Generates a no-args constructor
@EqualsAndHashCode(exclude = {"payments", "Groups"})  // Exclude relationships to prevent potential stack overflow issues
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "school")
    private String school;

    @Column(name = "grade")
    private String grade;

    @Column(name = "session")
    @Enumerated(EnumType.STRING)
    private Session session;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "student_phone")
    private String studentPhone;

    @Column(name = "parent_phone")
    private String parentPhone;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate = LocalDate.now();

    @Column(name = "status", nullable = false)
    private String status = "active";

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Group> Groups = new ArrayList<>();

    // Enum definitions
    public enum Gender {
        MALE, FEMALE
    }

    public enum Session {
        MORNING, AFTERNOON
    }
}
