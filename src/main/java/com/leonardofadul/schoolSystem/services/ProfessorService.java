package com.leonardofadul.schoolSystem.services;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.repositories.SubjectRepository;
import com.leonardofadul.schoolSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public Student findStudent(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id + ". Type: " + Student.class.getName()));
    }

    public Subject findSubject(Integer id){
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id + ". Type: " + Subject.class.getName()));
    }
}
