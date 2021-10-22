package com.leonardofadul.schoolSystem.services;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private StudentRepository studentRepository;

    public Student findStudent(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id + ". Type: " + Student.class.getName()));
    }
}
