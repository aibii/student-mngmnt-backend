package com.example.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.student.Student;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@RestController  //This means that the class will handle incoming HTTP requests and generate appropriate responses
public class DemoApplication {

	//bare-bone spring boot application
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
