package com.leonardofadul.schoolSystem.domain;

import java.io.Serializable;

public class SubjectGrade implements Serializable {

    private String name;
    private Double grade1;
    private Double grade2;

    public SubjectGrade(){
    }

    public SubjectGrade(ClassGrade classGrade){
        this.name = classGrade.getClassName();
        this.grade1 = classGrade.getGrade1();
        this.grade2 = classGrade.getGrade2();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
