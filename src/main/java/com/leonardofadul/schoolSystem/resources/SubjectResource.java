package com.leonardofadul.schoolSystem.resources;

import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.dto.SubjectDTO;
import com.leonardofadul.schoolSystem.dto.SubjectOnlyDTO;
import com.leonardofadul.schoolSystem.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/subjects")
public class SubjectResource {

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SubjectDTO> find(@PathVariable Integer id){
        Subject subject = professorService.findSubject(id);
        return ResponseEntity.ok().body(new SubjectDTO(subject));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody SubjectDTO subjectDTO){
        Subject subject = professorService.fromSubjectDTO(subjectDTO);
        subject = professorService.insertSubject(subject);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subject.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody SubjectDTO updatedSubjectDTO, @PathVariable Integer id){
        Subject newSubject = professorService.fromSubjectDTO(updatedSubjectDTO);

        Subject subject = professorService.findSubject(id);

        newSubject.setId(id);
        newSubject.setStudents(subject.getStudents());
        newSubject = professorService.updateSubject(newSubject);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Subject> delete(@PathVariable Integer id){
        professorService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SubjectOnlyDTO>> findAll(){
        List<Subject> subjects = professorService.findAllSubjects();
        List<SubjectOnlyDTO> subjectOnlyDTOList = subjects.stream().map(SubjectOnlyDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(subjectOnlyDTOList);
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
