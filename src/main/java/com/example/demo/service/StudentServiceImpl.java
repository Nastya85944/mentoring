package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findById(Long studentId) throws ResourceNotFoundException {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + studentId));
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository
                .findById(studentDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + studentDetails.getId()));
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + studentId));
        studentRepository.deleteById(student.getId());
    }
}
