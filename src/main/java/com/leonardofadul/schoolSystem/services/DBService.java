package com.leonardofadul.schoolSystem.services;

import com.leonardofadul.schoolSystem.domain.ClassGrade;
import com.leonardofadul.schoolSystem.domain.Professor;
import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.repositories.ClassGradeRepository;
import com.leonardofadul.schoolSystem.repositories.ProfessorRepository;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassGradeRepository classGradeRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public void instantiateTestDataBase(){
        Professor professor1 = new Professor(null, "Aline", "aline@gmail.com", pe.encode("aline123"));

        professorRepository.save(professor1);

        Student st1 = new Student(null, "Bruno", "bruno@gmail.com", pe.encode("123"));
        Student st2 = new Student(null, "Renato", "renato@gmail.com", pe.encode("0000"));
        Student st3 = new Student(null, "Ana Claudia", "ana@gmail.com", pe.encode("ana123"));
        Student st4 = new Student(null, "Beatriz", "bia@gmail.com", pe.encode("bia123"));
        Student st5 = new Student(null, "Roberto Carlos", "roberto@gmail.com", pe.encode("roberto123"));
        Student st6 = new Student(null, "Ronaldo Nazario", "ronaldo@gmail.com", pe.encode("ronaldo123"));
        Student st7 = new Student(null, "Cecilia Lima", "cecilia@gmail.com", pe.encode("cecilia123"));
        Student st8 = new Student(null, "Lucas Rodriguez", "lucas@gmail.com", pe.encode("lucas123"));
        Student st9 = new Student(null, "Leandro Algane", "leandro@gmail.com", pe.encode("leandro123"));
        Student st10 = new Student(null, "José Eduardo", "jose@gmail.com", pe.encode("jose123"));
        Student st11 = new Student(null, "Thiago Oliveira", "thiago@gmail.com", pe.encode("thiago123"));

        Subject s1 = new Subject(null, "Math");
        Subject s2 = new Subject(null, "English");
        Subject s3 = new Subject(null, "Biology");
        Subject s4 = new Subject(null, "Chemistry");
        Subject s5 = new Subject(null, "Portuguese");
        Subject s6 = new Subject(null, "Data Structure");
        Subject s7 = new Subject(null, "Physics");
        Subject s8 = new Subject(null, "Algorithms");
        Subject s9 = new Subject(null, "Embedded Systems");
        Subject s10 = new Subject(null, "Operating Systems");
        Subject s11 = new Subject(null, "Software Engineering");
        Subject s12 = new Subject(null, "Database");
        Subject s13 = new Subject(null, "Artificial Intelligence");
        Subject s14 = new Subject(null, "Biochemistry");
        Subject s15 = new Subject(null, "Compilers");
        Subject s16 = new Subject(null, "Electronics");
        Subject s17 = new Subject(null, "Subject 17");
        Subject s18 = new Subject(null, "Subject 18");
        Subject s19 = new Subject(null, "Subject 19");
        Subject s20 = new Subject(null, "Subject 20");
        Subject s21 = new Subject(null, "Subject 21");
        Subject s22 = new Subject(null, "Subject 22");
        Subject s23 = new Subject(null, "Subject 23");
        Subject s24 = new Subject(null, "Subject 24");
        Subject s25 = new Subject(null, "Subject 25");
        Subject s26 = new Subject(null, "Subject 26");
        Subject s27 = new Subject(null, "Subject 27");
        Subject s28 = new Subject(null, "Subject 28");

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

        s1.setSize(s1.getStudents().size());
        s2.setSize(s2.getStudents().size());
        s3.setSize(s3.getStudents().size());
        s4.setSize(s4.getStudents().size());
        s5.setSize(s5.getStudents().size());

        studentRepository.saveAll(Arrays.asList(st1, st2, st3, st4, st5, st6, st7, st8, st9, st10, st11));
        subjectRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28));

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
