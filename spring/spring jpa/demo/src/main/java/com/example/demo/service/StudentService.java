package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.dao.UniversityClassDao;
import com.example.demo.exception.InvalidUniversityClassException;
import com.example.demo.exception.StudentEmptyNameException;
import com.example.demo.exception.StudentNonExistException;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import com.example.demo.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class StudentService {
    private StudentDao studentDao;
    private UniversityClassDao universityClassDao;
    private StudentMapper studentMapper;

    @Autowired

    public StudentService(StudentDao studentDao, UniversityClassDao universityClassDao, StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.universityClassDao = universityClassDao;
        this.studentMapper = studentMapper;
    }




    public Student addStudent(Student student){
        if(student.getName().isEmpty()){
            throw  new StudentEmptyNameException("Student name cannot be empty");
        }
        return studentDao.save(student);
    }


    public Student updateStudent(Student student){
        if(student.getId()==null || !studentDao.existsById(student.getId()))
            throw  new StudentNonExistException("Student id is not exist.");
        return studentDao.save(student);
    }


    @Autowired
    public List<Student> getAllStudents(){
        return (List<Student>) studentDao.findAll();
    }

    public Student assignClass(Long studentId,Long classId){
        if(!studentDao.existsById(studentId)){
            throw new StudentNonExistException("Cannot find the student Id"+studentId);
        }
        if(!universityClassDao.existsById(classId)){
            throw new InvalidUniversityClassException("Cannot find class Id"+ classId);
        }

        Student student=getStudentById(studentId).get();
        UniversityClass universityClass=universityClassDao.findById(classId).get();

        student.setUniversityClass(universityClass);
        return studentDao.save(student);

    }


    public Optional<Student> getStudentById(Long id){
        return studentDao.findById(id);
    }

    public List<Student> getStudentsByName(String name){
        return studentDao.findByName(name);
    }
    public List<Student> getStudentContainName(String name){
        return studentMapper.getStudentsContainStringInName("%"+name+"%");
    }

    public List<Student> getStudentInClass(int year, int number){
        return studentMapper.getStudentsInClass(year,number);
    }


}
