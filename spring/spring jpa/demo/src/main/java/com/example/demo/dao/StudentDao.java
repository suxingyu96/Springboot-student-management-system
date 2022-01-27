package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface StudentDao extends CrudRepository<Student, Long> {
    //CrudRepository<TABLE-NAME, OK> to achieve the basic add, delete, update,search operations

    List<Student> findByName(String name);
//    find by name automatically
}
