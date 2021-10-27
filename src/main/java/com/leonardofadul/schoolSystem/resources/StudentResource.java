package com.leonardofadul.schoolSystem.resources;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.dto.StudentDTO;
import com.leonardofadul.schoolSystem.dto.SubjectDTO;
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
@RequestMapping(value = "/students")
public class StudentResource {

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> find(@PathVariable Integer id){
        Student student = professorService.findStudent(id);
        return ResponseEntity.ok().body(student);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody StudentDTO studentDTO){
        Student student = professorService.fromStudentDTO(studentDTO);
        student = professorService.insertStudent(student);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody StudentDTO updatedStudentDTO, @PathVariable Integer id){
        Student student = professorService.fromStudentDTO(updatedStudentDTO);
        student.setId(id);

//        newStudent.setId(id);
//        newStudent.setSubjects(student.getSubjects());
        student = professorService.updateStudent(student);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> delete(@PathVariable Integer id){
        professorService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll(){
        List<Student> students = professorService.findAllStudents();
        List<StudentDTO> studentDTOList = students.stream().map(StudentDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(studentDTOList);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<StudentDTO>> findStudentsPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Student> students = professorService.findStudentPage(page, linesPerPage, orderBy, direction);
        Page<StudentDTO> studentDTOs = students.map(StudentDTO::new);
        return ResponseEntity.ok().body(studentDTOs);
    }
}
