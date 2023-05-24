package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// API/Presentation Layer
@Controller
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
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "student-list";
	}

    @GetMapping("/student-create")
    public String createStudentForm(Student student){
        return "student-create"; //returns student-create view
    }

    @PostMapping("/student-create")
    public String createStudent(@ModelAttribute("student") Student student) { //tells Spring to automat convert the data in the requat body into a student obj
        //studentService.addNewStudent(student);
        studentService.addNewStudent(student);
        return "redirect:/students";
    }

    @GetMapping("student-delete/{id}")
    public String deleteStudent(@PathVariable("id") Long studentId){
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    @GetMapping("/student-update/{id}")
    public String updateStudentForm(@PathVariable("id") Long id, Model model){
        /*Optional<Student> student = studentService.findById(id);
        model.addAttribute("student", student);
        return "/student-update";*/

        try {
            Student student = studentService.findById(id);
            model.addAttribute("student", student);
            return "/student-update";
        } catch (StudentNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "redirect:/students";
        }
    }

    @PostMapping("/student-update")
    public String updateStudent(Student student, Model model){
        //studentService.updateStudent(student);
        System.out.println("Update student method called");
        try {
            System.out.println("ID: " + student.getId());
            studentService.updateStudent(student);
        } catch (StudentNotFoundException e) {
            String errorMessage = "Update method is not called: " + e.getMessage();
            // TODO Auto-generated catch block
            System.out.println(errorMessage);
        }
        return "redirect:/students";
    }

   /* @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    } */
}