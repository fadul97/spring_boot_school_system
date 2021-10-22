package com.leonardofadul.schoolSystem.resources;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> find(@PathVariable Integer id){
        Student student = professorService.findStudent(id);
        return ResponseEntity.ok().body(student);
    }
}
