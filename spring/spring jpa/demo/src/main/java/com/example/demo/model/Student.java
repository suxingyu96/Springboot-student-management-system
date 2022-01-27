package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {
    @Id  //primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // auto-increment
    private Long id;


    @ManyToOne
    @JoinColumn(name="university_class_id")
    private UniversityClass universityClass;


    @Column(nullable=false,name="name")
    private String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }



    public Student(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setUniversityClass(UniversityClass universityClass) {
        this.universityClass = universityClass;
    }

    public UniversityClass getUniversityClass() {
        return universityClass;
    }

    @Override
    public String toString() {
        String str="";
        str+="PK: "+getId();
        str+=" Name: "+getName();
        return str;
    }
}
