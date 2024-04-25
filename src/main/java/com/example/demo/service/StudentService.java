package com.example.demo.service;

import com.example.demo.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> findById(Long studentId);

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Long studentId);
}
