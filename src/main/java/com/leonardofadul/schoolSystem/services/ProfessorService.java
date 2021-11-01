package com.leonardofadul.schoolSystem.services;

import com.leonardofadul.schoolSystem.domain.ClassGrade;
import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.dto.StudentDTO;
import com.leonardofadul.schoolSystem.dto.StudentNewDTO;
import com.leonardofadul.schoolSystem.dto.SubjectDTO;
import com.leonardofadul.schoolSystem.repositories.ClassGradeRepository;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.repositories.SubjectRepository;
import com.leonardofadul.schoolSystem.services.exceptions.DataIntegrityException;
import com.leonardofadul.schoolSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ProfessorService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassGradeRepository classGradeRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    // Students
    public Student findStudent(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id + ". Type: " + Student.class.getName()));
    }

    @Transactional
    public Student insertStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        Student newStudent = findStudent(student.getId());
        updateStudentData(newStudent, student);
        return studentRepository.save(newStudent);
    }

    private void updateStudentData(Student newStudent, Student student){
        newStudent.setName(student.getName());
        newStudent.setEmail(student.getEmail());
        newStudent.getGrades().forEach(classGrade -> {
            classGrade.setStudentName(newStudent.getName());
        });
        newStudent.setSubjects(student.getSubjects());
    }

    public void deleteStudent(Integer id) {
        findStudent(id);
        try{
            studentRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Failed. It is not possible to delete a student that still has classes.");
        }
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Page<Student> findStudentPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return studentRepository.findAll(pageRequest);
    }

    public Student fromStudentDTO(StudentDTO studentDTO) {
        return new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getEmail(), null);
    }

    public Student fromStudentNewDTO(StudentNewDTO studentNewDTO) {
        return new Student(studentNewDTO.getId(), studentNewDTO.getName(), studentNewDTO.getEmail(), pe.encode(studentNewDTO.getPassword()));
    }



    // Subjects
    public Subject findSubject(Integer id){
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id + ". Type: " + Subject.class.getName()));
    }

    public Subject findSubjectByName(String name){
        Optional<Subject> subject = Optional.ofNullable(subjectRepository.findByName(name));
        return subject.orElseThrow(() -> new ObjectNotFoundException("Object not found. Name: " + name + ". Type: " + Subject.class.getName()));
    }

    public Subject insertSubject(Subject subject) {
        subject.setId(null);
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Subject subject) {
        Subject newSubject = findSubject(subject.getId());
        updateSubjectData(newSubject, subject);
        return subjectRepository.save(newSubject);
    }

    private void updateSubjectData(Subject newSubject, Subject subject){
        newSubject.setName(subject.getName());
        newSubject.setStudents(subject.getStudents());
        newSubject.getGrades().forEach(classGrade -> {
            classGrade.setClassName(newSubject.getName());
        });
        newSubject.setGrades(subject.getGrades());
    }

    public void deleteSubject(Integer id) {
        findSubject(id);
        try{
            subjectRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Failed. It is not possible to delete a class that still has students enrolled.");
        }
    }

    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    public Page<Subject> findSubjectPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return subjectRepository.findAll(pageRequest);
    }

    public Subject fromSubjectDTO(SubjectDTO subjectDTO) {

        return new Subject(subjectDTO.getId(), subjectDTO.getName());
    }

    public Student addSubjectToStudent(Subject subject, Student student) {
        ClassGrade newCG = new ClassGrade(subject, student);
        classGradeRepository.save(newCG);

        student.getGrades().add(newCG);
        subject.getGrades().add(newCG);

        student.getSubjects().add(subject);

        AtomicBoolean isStudentEnrolled = new AtomicBoolean(false);
        subject.getStudents().forEach(student1 -> {
            if(student1.equals(student)){
                isStudentEnrolled.set(true);
            }
        });

        if(!isStudentEnrolled.get()){
            subject.getStudents().add(student);
            subject.setSize(subject.getStudents().size());
            System.out.println("ADICIONANDO MAIS UM ESTUDANTE");
            System.out.println("ADICIONANDO MAIS UM ESTUDANTE");
            System.out.println("ADICIONANDO MAIS UM ESTUDANTE");
            System.out.println("ADICIONANDO MAIS UM ESTUDANTE");
            System.out.println("ADICIONANDO MAIS UM ESTUDANTE");
            System.out.println("ADICIONANDO MAIS UM ESTUDANTE");
            System.out.println("ADICIONANDO MAIS UM ESTUDANTE");
        }

        subjectRepository.save(subject);
        return studentRepository.save(student);
    }



    // ClassGrades
    public ClassGrade findClassGradeWithName(Student student, ClassGrade classGradeFromRequest) {
        Optional<ClassGrade> classGrade = Optional.ofNullable(classGradeRepository.findByClassNameAndStudentName(classGradeFromRequest.getClassName(), student.getName()));
        return classGrade.orElseThrow(() -> new ObjectNotFoundException("Object not found. Class name: " + classGradeFromRequest.getClassName() + ". Type: " + Subject.class.getName() + ". Name: " + student.getName() + ". Type: " + Student.class.getName()));
    }

    public ClassGrade findClassGrade(ClassGrade classGradeFromRequest) {
        Optional<ClassGrade> classGrade = Optional.ofNullable(classGradeRepository.findByClassNameAndStudentName(classGradeFromRequest.getClassName(), classGradeFromRequest.getStudentName()));
        return classGrade.orElseThrow(() -> new ObjectNotFoundException("Object not found. Class name: " + classGradeFromRequest.getClassName() + ". Type: " + Subject.class.getName() + ". Student name: " + classGradeFromRequest.getStudentName() + ". Type: " + Student.class.getName()));
    }

    public ClassGrade updateGrade(Student student, ClassGrade classGrade){
        ClassGrade newClassGrade = findClassGradeWithName(student, classGrade);
        updateClassGradeData(newClassGrade, classGrade);
        return classGradeRepository.save(newClassGrade);
    }

    private void updateClassGradeData(ClassGrade newClassGrade, ClassGrade classGrade){
        if(classGrade.getGrade1() != null){
            newClassGrade.setGrade1(classGrade.getGrade1());
        }
        if(classGrade.getGrade2() != null){
            newClassGrade.setGrade2(classGrade.getGrade2());
        }
    }
}
