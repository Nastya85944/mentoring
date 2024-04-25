package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testFindById() {
        Long studentId = 1L;
        Student expectedStudent = new Student();
        expectedStudent.setId(1L);
        expectedStudent.setFirstName("Anna");
        expectedStudent.setLastName("Ivanova");
        expectedStudent.setEmail("anna@gmail.com");
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(expectedStudent));

        Optional<Student> actualStudent = studentService.findById(studentId);
        assertEquals(expectedStudent, actualStudent.orElse(null));
    }

    @Test
    public void testGetAllStudents() {
        List<Student> expectedStudents = List.of(new Student(1L, "Anna", "Ivanova", "anna@gmail.com"),
                new Student(2L, "Olga", "Petrova", "olga@gmail.com"));
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        List<Student> actualStudents = studentService.getAllStudents();
        assertEquals(expectedStudents.size(), actualStudents.size());
        assertEquals(expectedStudents, actualStudents);
    }

    @Test
    public void testSaveStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Anna");
        student.setLastName("Ivanova");
        student.setEmail("anna@gmail.com");

        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentService.saveStudent(student);
        assertEquals(student, savedStudent);
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Anna");
        student.setLastName("Ivanova");
        student.setEmail("anna@gmail.com");

        when(studentRepository.save(student)).thenReturn(student);

        Student updatedStudent = studentService.updateStudent(student);
        assertEquals(student, updatedStudent);
    }

    @Test
    public void testDeleteStudent() {
        Long studentId = 1L;
        studentService.deleteStudent(studentId);
        verify(studentRepository, times(1)).deleteById(studentId);
    }
}
