package com.leonardofadul.schoolSystem.services;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.dto.StudentDTO;
import com.leonardofadul.schoolSystem.dto.SubjectDTO;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.repositories.SubjectRepository;
import com.leonardofadul.schoolSystem.services.exceptions.DataIntegrityException;
import com.leonardofadul.schoolSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    // Students
    public Student findStudent(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id + ". Type: " + Student.class.getName()));
    }

    public Student insertStudent(Student student) {
//        student.setId(null);
//        return studentRepository.save(subject);
        throw new UnsupportedOperationException();
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
        try{
            findStudent(id);
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
        return new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getEmail());
    }



    // Subjects
    public Subject findSubject(Integer id){
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id + ". Type: " + Subject.class.getName()));
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
        newSubject.setGrades(subject.getGrades());
    }

    public void deleteSubject(Integer id) {
        try{
            findSubject(id);
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
}
