package com.leonardofadul.schoolSystem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;

    @JsonBackReference
    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "id.student")
    private Set<ClassGrade> grades = new HashSet<>();

    // Constructors
    public Student() {
    }

    public Student(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    @JsonIgnore
    public List<Subject> getAllSubjects(){
        List<Subject> subjectList = new ArrayList<>();
        for(ClassGrade classGrade: grades){
            subjectList.add(classGrade.getSubject());
        }
        return subjectList;
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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<ClassGrade> getGrades() {
        return grades;
    }

    public void setGrades(Set<ClassGrade> grades) {
        this.grades = grades;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
