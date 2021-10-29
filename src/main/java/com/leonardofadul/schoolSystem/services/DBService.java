package com.leonardofadul.schoolSystem.services;

import com.leonardofadul.schoolSystem.domain.ClassGrade;
import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.repositories.ClassGradeRepository;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassGradeRepository classGradeRepository;

    public void instantiateTestDataBase(){
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

        ClassGrade cg1 = new ClassGrade(s1, st1);

        ClassGrade cg2 = new ClassGrade(s2, st1);
        ClassGrade cg3 = new ClassGrade(s2, st2);
        ClassGrade cg4 = new ClassGrade(s2, st3);
        ClassGrade cg5 = new ClassGrade(s2, st4);

        ClassGrade cg6 = new ClassGrade(s3, st3);
        ClassGrade cg7 = new ClassGrade(s3, st4);

        ClassGrade cg8 = new ClassGrade(s4, st1);
        ClassGrade cg9 = new ClassGrade(s4, st2);
        ClassGrade cg10 = new ClassGrade(s4, st6);

        ClassGrade cg11 = new ClassGrade(s5, st1);
        ClassGrade cg12 = new ClassGrade(s5, st2);
        ClassGrade cg13 = new ClassGrade(s5, st3);
        ClassGrade cg14 = new ClassGrade(s5, st4);
        ClassGrade cg15 = new ClassGrade(s5, st5);

        st1.getGrades().addAll(Arrays.asList(cg1, cg2, cg8, cg11));
        st2.getGrades().addAll(Arrays.asList(cg3, cg9, cg12));
        st3.getGrades().addAll(Arrays.asList(cg4, cg6, cg13));
        st4.getGrades().addAll(Arrays.asList(cg5, cg7, cg14));
        st5.getGrades().add(cg15);
        st6.getGrades().add(cg10);

        s1.getGrades().add(cg1);
        s2.getGrades().addAll(Arrays.asList(cg2, cg3, cg4, cg5));
        s3.getGrades().addAll(Arrays.asList(cg6, cg7));
        s4.getGrades().addAll(Arrays.asList(cg8, cg9, cg10));
        s5.getGrades().addAll(Arrays.asList(cg11, cg12, cg13, cg14, cg15));

        classGradeRepository.saveAll(Arrays.asList(cg1, cg2, cg3, cg4, cg5, cg6, cg7, cg8, cg9, cg10, cg11, cg12, cg13, cg14, cg15));
    }
}
