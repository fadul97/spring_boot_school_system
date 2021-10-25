package com.leonardofadul.schoolSystem.resources;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.dto.SubjectDTO;
import com.leonardofadul.schoolSystem.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Subject> delete(@PathVariable Integer id){
        professorService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> findAll(){
        List<Subject> subjects = professorService.findAllSubjects();
        List<SubjectDTO> subjectDTOList = subjects.stream().map(SubjectDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(subjectDTOList);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<SubjectDTO>> findSubjectsPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Subject> subjects = professorService.findSubjectPage(page, linesPerPage, orderBy, direction);
        Page<SubjectDTO> subjectDTOs = subjects.map(SubjectDTO::new);
        return ResponseEntity.ok().body(subjectDTOs);
    }
}
