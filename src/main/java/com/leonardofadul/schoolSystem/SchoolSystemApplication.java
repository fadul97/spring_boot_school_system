package com.leonardofadul.schoolSystem;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SchoolSystemApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SchoolSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student st1 = new Student(null, "Bruno", "bruno@gmail.com");
		Student st2 = new Student(null, "Renato", "renato@gmail.com");
		Student st3 = new Student(null, "Ana Claudia", "ana@gmail.com");
		Student st4 = new Student(null, "Beatriz", "bia@gmail.com");

		studentRepository.saveAll(Arrays.asList(st1, st2, st3, st4));
	}
}
