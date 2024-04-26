package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.StudentService;
import com.example.demo.service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
        return studentService.findById(studentId);
    }

    @GetMapping
    public Page<Student> getAllStudents(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return studentService.getAllStudents(pageable);
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping
    public Student updateStudent(@Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
        return studentService.updateStudent(studentDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
        studentService.deleteStudent(studentId);
    }
}
