package com.leonardofadul.schoolSystem;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SchoolSystemApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	public static void main(String[] args) {
		SpringApplication.run(SchoolSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student st1 = new Student(null, "Bruno", "bruno@gmail.com");
		Student st2 = new Student(null, "Renato", "renato@gmail.com");
		Student st3 = new Student(null, "Ana Claudia", "ana@gmail.com");
		Student st4 = new Student(null, "Beatriz", "bia@gmail.com");
		Student st5 = new Student(null, "Roberto Carlos", "roberto@gmail.com");
		Student st6 = new Student(null, "Ronaldo Nazario", "ronaldo@gmail.com");
		Student st7 = new Student(null, "Cecilia Lima", "cecilia@gmail.com");
		Student st8 = new Student(null, "Lucas Rodriguez", "lucas@gmail.com");

		Subject s1 = new Subject(null, "Math");
		Subject s2 = new Subject(null, "English");
		Subject s3 = new Subject(null, "Biology");
		Subject s4 = new Subject(null, "Chemistry");
		Subject s5 = new Subject(null, "Portuguese");

		st1.getSubjects().addAll(Arrays.asList(s1, s2, s4, s5));
		st2.getSubjects().addAll(Arrays.asList(s2, s4, s5));
		st3.getSubjects().addAll(Arrays.asList(s2, s3, s5));
		st4.getSubjects().addAll(Arrays.asList(s2, s3, s5));
		st5.getSubjects().add(s5);
		st6.getSubjects().add(s4);

		s1.getStudents().add(st1);
		s2.getStudents().addAll(Arrays.asList(st1, st2, st3, st4));
		s3.getStudents().addAll(Arrays.asList(st3, st4));
		s4.getStudents().addAll(Arrays.asList(st1, st2, st6));
		s5.getStudents().addAll(Arrays.asList(st1, st2, st3, st4, st5));

		studentRepository.saveAll(Arrays.asList(st1, st2, st3, st4, st5, st6, st7, st8));
		subjectRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));
	}
}
