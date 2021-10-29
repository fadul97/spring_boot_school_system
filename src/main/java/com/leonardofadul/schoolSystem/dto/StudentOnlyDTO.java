package com.leonardofadul.schoolSystem.dto;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.domain.SubjectGrade;
import com.leonardofadul.schoolSystem.services.validations.StudentUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@StudentUpdate
public class StudentOnlyDTO {

    private Integer id;

    @NotEmpty(message = "Field 'name' required.")
    @Length(min = 2, max = 120, message = "Length must be between 2 and 120 characters.")
    private String name;

    @NotEmpty(message = "Field 'email' required.")
    @Email
    private String email;

    public StudentOnlyDTO() {
    }

    public StudentOnlyDTO(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
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
}
