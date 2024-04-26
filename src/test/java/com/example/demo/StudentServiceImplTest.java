package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void shouldFindStudentByIdWhenStudentExists() throws ResourceNotFoundException {
        Long studentId = 1L;
        Student expectedStudent = new Student();
        expectedStudent.setId(studentId);
        expectedStudent.setFirstName("Anna");
        expectedStudent.setLastName("Ivanova");
        expectedStudent.setEmail("anna@gmail.com");
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(expectedStudent));

        Student actualStudent = studentService.findById(studentId);
        System.out.println(actualStudent);
        assertEquals(expectedStudent, actualStudent);
        assertNotNull(actualStudent);
    }

    @Test
    public void shouldFindAllStudents() {
        List<Student> expectedStudents = List.of(new Student(1L, "Anna", "Ivanova", "anna@gmail.com"),
                new Student(2L, "Olga", "Petrova", "olga@gmail.com"));
        Page<Student> page = new PageImpl<>(expectedStudents);
        when(studentRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Student> actualStudents = studentService.getAllStudents(Pageable.unpaged());
        assertNotNull(actualStudents);
        assertEquals(2, actualStudents.getContent().size());
    }

    @Test
    public void shouldSaveStudent() {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);
        student.setFirstName("Anna");
        student.setLastName("Ivanova");
        student.setEmail("anna@gmail.com");

        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentService.saveStudent(student);
        assertEquals(student, savedStudent);
    }

    @Test
    public void shouldUpdateStudent() throws ResourceNotFoundException {
        Long studentId = 1L;
        Student existingStudent = new Student();
        existingStudent.setId(studentId);
        existingStudent.setFirstName("Anna");
        existingStudent.setLastName("Ivanova");
        existingStudent.setEmail("anna@gmail.com");

        Student updatedStudentDetails = new Student();
        updatedStudentDetails.setId(studentId);
        updatedStudentDetails.setFirstName("Anna");
        updatedStudentDetails.setLastName("Smirnova");
        updatedStudentDetails.setEmail("anna@gmail.com");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Student updatedStudent = studentService.updateStudent(updatedStudentDetails);
        assertNotNull(updatedStudent);
        assertEquals(updatedStudentDetails.getId(), updatedStudent.getId());
        assertEquals(updatedStudentDetails.getFirstName(), updatedStudent.getFirstName());
        assertEquals(updatedStudentDetails.getLastName(), updatedStudent.getLastName());
        assertEquals(updatedStudentDetails.getEmail(), updatedStudent.getEmail());

    }

    @Test
    public void shouldDeleteStudentWhenStudentExists() throws ResourceNotFoundException {
        Long studentId = 1L;
        Student existingStudent = new Student();
        existingStudent.setId(studentId);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
        assertDoesNotThrow(() -> studentService.deleteStudent(studentId));
        verify(studentRepository, times(1)).deleteById(studentId);
    }
}
