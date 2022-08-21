package com.example.demo.student;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
   
    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void canGetStudents() {
        // when
        underTest.getStudents();
        // then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddNewStudent() {
        // given
        Student student = new Student(
            "ignacio",
            "ignacio@mail.com",
            LocalDate.of(2000, Month.JANUARY,5)
        );
        // when 
        underTest.addNewStudent(student);
        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }


    @Test
    void canAddNewStudentThrowErrorIfEmailIsTaken() {
        // given
        Student student = new Student(
            "ignacio",
            "ignacio@mail.com",
            LocalDate.of(2000, Month.JANUARY,5)
        );
        Optional<Student> studentOptional = Optional.of(student);
        given(studentRepository.findStudentByEmail(student.getEmail())).willReturn(studentOptional);

        // when
        // then
        assertThatThrownBy(() -> underTest.addNewStudent(student))
            .isInstanceOf(IllegalStateException.class)   
            .hasMessageContaining("email taken");

        verify(studentRepository, never()).save(student);
    }
}
