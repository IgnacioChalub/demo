package com.example.demo.student;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class StudentRepositoryTest {
  
    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldFindStudentByEmail() {
        // given
        String email = "ignacio@mail.com"; 
        Student student = new Student(
            "ignacio",
            email,
            LocalDate.of(2000, Month.JANUARY,5)
        );
        underTest.save(student);

        // when
        Student savedStudent = underTest.findStudentByEmail(email).get();
        
        // then
        assertThat(student).isEqualTo(savedStudent); 
    }

    @Test
    void itShouldNotFindStudentByEmailIfStudentDoesNotExists() {
        String email  = "ignacio@mail.com";

        Optional<Student> studentOptional = underTest.findStudentByEmail(email);

        assertThat(studentOptional.isPresent()).isEqualTo(false);
    }

}
