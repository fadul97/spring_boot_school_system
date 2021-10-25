package com.leonardofadul.schoolSystem.resources;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Subject subject){
        subject = professorService.insertSubject(subject);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subject.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Subject updatedSubject, @PathVariable Integer id){
        Subject subject = professorService.findSubject(id);

        updatedSubject.setId(id);
        updatedSubject.setStudents(subject.getStudents());
        updatedSubject = professorService.updateSubject(updatedSubject);

        return ResponseEntity.noContent().build();
    }
}
