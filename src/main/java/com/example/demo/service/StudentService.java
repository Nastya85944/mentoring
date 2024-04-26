package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    Student findById(Long studentId) throws ResourceNotFoundException;

    Page<Student> getAllStudents(Pageable pageable);

    Student saveStudent(Student student);

    Student updateStudent(Student student) throws ResourceNotFoundException;

    void deleteStudent(Long studentId) throws ResourceNotFoundException;
}
