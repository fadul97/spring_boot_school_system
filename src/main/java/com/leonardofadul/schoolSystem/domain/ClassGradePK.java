package com.leonardofadul.schoolSystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClassGradePK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassGradePK that = (ClassGradePK) o;
        return Objects.equals(subject, that.subject) && Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, student);
    }
}
