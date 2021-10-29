package com.leonardofadul.schoolSystem.dto;

import com.leonardofadul.schoolSystem.domain.Subject;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class SubjectOnlyDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Field 'name' required.")
    @Length(min = 2, max = 80, message = "Length must be between 2 and 80 characters.")
    private String name;

    public SubjectOnlyDTO(){
    }

    public SubjectOnlyDTO(Subject subject){
        this.id = subject.getId();
        this.name = subject.getName();
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
}
