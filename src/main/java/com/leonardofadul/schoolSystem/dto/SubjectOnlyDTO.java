package com.leonardofadul.schoolSystem.dto;

import com.leonardofadul.schoolSystem.domain.StudentGrade;
import com.leonardofadul.schoolSystem.domain.Subject;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SubjectOnlyDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Field 'name' required.")
    @Length(min = 2, max = 80, message = "Length must be between 2 and 80 characters.")
    private String name;

    private Set<StudentGrade> students = new HashSet<>();
    private Integer size;

    public SubjectOnlyDTO(){
    }

    public SubjectOnlyDTO(Subject subject){
        this.id = subject.getId();
        this.name = subject.getName();
        this.students = subject.getGrades().stream().map(StudentGrade::new).collect(Collectors.toSet());
        this.size = students.size();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(Set<StudentGrade> students) {
        this.students = students;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
