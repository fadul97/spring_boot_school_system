package com.leonardofadul.schoolSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ClassGrade implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private ClassGradePK id = new ClassGradePK();

    @NotEmpty(message = "Field 'name' required.")
    @Length(min = 2, max = 80, message = "Length must be between 2 and 80 characters.")
    private String className;

    private String studentName;
    private Double grade1;
    private Double grade2;

    // Constructors
    public ClassGrade(){
    }

    public ClassGrade(Subject subject, Student student) {
        this.id.setSubject(subject);
        this.id.setStudent(student);
        this.className = subject.getName();
        this.studentName = student.getName();
    }

    // Getters and Setters
    @JsonIgnore
    public Subject getSubject(){
        return this.id.getSubject();
    }

    @JsonIgnore
    public Student getStudent(){
        return this.id.getStudent();
    }

    public ClassGradePK getId() {
        return id;
    }

    public void setId(ClassGradePK id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getGrade1() {
        return grade1;
    }

    public void setGrade1(Double grade1) {
        this.grade1 = grade1;
    }

    public Double getGrade2() {
        return grade2;
    }

    public void setGrade2(Double grade2) {
        this.grade2 = grade2;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassGrade that = (ClassGrade) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
