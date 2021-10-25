package com.leonardofadul.schoolSystem.dto;

import com.leonardofadul.schoolSystem.domain.ClassGrade;
import com.leonardofadul.schoolSystem.domain.Subject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SubjectDTO implements Serializable {

    private Integer id;
    private String name;
    private Set<ClassGrade> students = new HashSet<>();

    public SubjectDTO(){
    }

    public SubjectDTO(Subject subject){
        this.id = subject.getId();
        this.name = subject.getName();
        this.students = subject.getGrades();
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

    public Set<ClassGrade> getStudents() {
        return students;
    }

    public void setStudents(Set<ClassGrade> students) {
        this.students = students;
    }
}
