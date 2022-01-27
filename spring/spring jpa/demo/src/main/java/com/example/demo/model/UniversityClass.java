package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="UniversityClass")
public class UniversityClass {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    Integer year;

    @Column(nullable = false)
    Integer number;

    @OneToMany(mappedBy = "universityClass")
    List<Student> student;

    public UniversityClass(Long id, Integer year, Integer number) {
        this.id = id;
        this.year = year;
        this.number = number;
    }

    public UniversityClass(){}

    public Long getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getNumber() {
        return number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        String str="";
        str+="PK: "+getId();
        str+=" year: "+getYear();
        str+=" number: "+getNumber();
        return str;
    }
}
