package com.example.demo.mapper;


import com.example.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

//    SELECT * from student where name LIKE %T%;
    @Select("SELECT * from student where name LIKE #{name}")
    List<Student> getStudentsContainStringInName(@Param("name") String name);


//    SELECT * from student where university_class_id IN
//    (SELECT id from university_class where year=2022 AND number =1);
    @Select("SELECT * from student where university_class_id IN \n" +
            "(SELECT id from university_class where year=#{year} AND number =#{number});")
    List<Student> getStudentsInClass(@Param("year") int year, @Param("number") int number);
}
