package com.leonardofadul.schoolSystem.resources;

import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subjects")
public class SubjectResource {

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Subject> find(@PathVariable Integer id){
        Subject subject = professorService.findSubject(id);
        return ResponseEntity.ok().body(subject);
    }
}
