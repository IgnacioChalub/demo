package com.example.demo.student;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
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

        // // when
        //Optional<Student> studentOptional = underTest.findStudentByEmail(email);
        
        // // then
        // assertThat(studentOptional).isEqualTo(student);
    }
}
