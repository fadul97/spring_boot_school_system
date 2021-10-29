package com.leonardofadul.schoolSystem.resources;

import com.leonardofadul.schoolSystem.domain.ClassGrade;
import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.Subject;
import com.leonardofadul.schoolSystem.dto.StudentDTO;
import com.leonardofadul.schoolSystem.dto.StudentNewDTO;
import com.leonardofadul.schoolSystem.dto.StudentOnlyDTO;
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
    public ResponseEntity<StudentDTO> find(@PathVariable Integer id){
        Student student = professorService.findStudent(id);
        return ResponseEntity.ok().body(new StudentDTO(student));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody StudentNewDTO studentNewDTO){
        Student student = professorService.fromStudentNewDTO(studentNewDTO);
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
    public ResponseEntity<List<StudentOnlyDTO>> findAll(){
        List<Student> students = professorService.findAllStudents();
        List<StudentOnlyDTO> studentOnlyDTOList = students.stream().map(StudentOnlyDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(studentOnlyDTOList);
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

    @RequestMapping(value = "/{id}/addSubjects", method = RequestMethod.PUT)
    public ResponseEntity<Student> addSubject(@PathVariable Integer id, @Valid @RequestBody SubjectDTO subjectDTO){
        Student student = professorService.findStudent(id);
        Subject subject = professorService.findSubjectByName(subjectDTO.getName());
        student = professorService.addSubjectToStudent(subject, student);
        return ResponseEntity.ok().body(student);
    }

    @RequestMapping(value = "/{id}/changeGrades", method = RequestMethod.PUT)
    public ResponseEntity<ClassGrade> changeGrade(@PathVariable Integer id, @Valid @RequestBody ClassGrade classGradeFromRequest){
        Student student = professorService.findStudent(id);
        ClassGrade classGrade = professorService.findClassGradeWithName(student, classGradeFromRequest);
        classGrade = professorService.updateGrade(student, classGradeFromRequest);
        return ResponseEntity.ok().body(classGrade);
    }
}
