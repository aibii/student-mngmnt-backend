package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// API/Presentation Layer
@RestController
@RequestMapping(path = "/students")
// api/v1/student
public class StudentController {

    private final StudentService studentService;

    @Autowired //automatically inject dependencies into the class //automatically provide an instance of StudentService when creating an instance of StudentController
    public StudentController(StudentService studentService) {
        this.studentService = studentService; //By passing the StudentService object through the constructor, we are performing dependency injection
        //The injected StudentService object is assigned to the studentService instance variable
    }

    @GetMapping //The @GetMapping annotation on the getStudents() method indicates that it will handle HTTP GET requests
	public String getStudents(Model model) {
		// return studentService.getStudents();
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "student-list";
	}

    @GetMapping("students-create")
    public String createStudentForm(Student student){
        return "student-create";
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) { //tells Spring to automat convert the data in the requat body into a student obj
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}
