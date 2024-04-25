package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.StudentService;
import com.example.demo.service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Qualifier("studentServiceImpl")
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
        return studentService.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + studentId));
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@Valid @RequestBody Student studentDetails, @PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
        Student student = studentService.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + studentId));
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
        Student student = studentService.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + studentId));
        studentService.deleteStudent(student.getId());
    }
}
