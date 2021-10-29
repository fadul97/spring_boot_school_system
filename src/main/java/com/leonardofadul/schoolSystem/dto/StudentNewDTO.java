package com.leonardofadul.schoolSystem.dto;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.SubjectGrade;
import com.leonardofadul.schoolSystem.services.validations.StudentInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@StudentInsert
public class StudentNewDTO {

    private Integer id;

    @NotEmpty(message = "Field 'name' required.")
    @Length(min = 2, max = 120, message = "Length must be between 2 and 120 characters.")
    private String name;

    @NotEmpty(message = "Field 'email' required.")
    @Email
    private String email;

    private Set<SubjectGrade> subjects = new HashSet<>();

    public StudentNewDTO() {
    }

    public StudentNewDTO(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.subjects = student.getGrades().stream().map(SubjectGrade::new).collect(Collectors.toSet());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<SubjectGrade> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectGrade> subjects) {
        this.subjects = subjects;
    }
}
