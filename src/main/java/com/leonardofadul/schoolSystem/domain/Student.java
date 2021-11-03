package com.leonardofadul.schoolSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leonardofadul.schoolSystem.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "STUDENT_PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "id.student", cascade = CascadeType.ALL)
    private Set<ClassGrade> grades = new HashSet<>();

    public Student() {
        addProfile(Profile.STUDENT);
    }

    public Student(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        addProfile(Profile.STUDENT);
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile){
        profiles.add(profile.getCode());
    }

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
