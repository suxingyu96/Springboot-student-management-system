package com.example.demo.api;


import com.example.demo.exception.InvalidUniversityClassException;
import com.example.demo.exception.StudentEmptyNameException;
import com.example.demo.exception.StudentNonExistException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @RequiresPermissions("student:read")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/name")
    // localhost:8080/api/student/name?name=zhangsan
    public List<Student> getStudents(@RequestParam String name){
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/contain_name")
    // localhost:8080/api/student/containname?name=xxx
    public List<Student> getStudentsContainName(@RequestParam String name){
        return studentService.getStudentContainName(name);
    }

    @GetMapping("/class")
    // localhost:8080/api/student/class?year=2022&number=1
    public List<Student> getStudentsInClass(@RequestParam int year,@RequestParam int number){
        return studentService.getStudentInClass(year,number);
    }


    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody Student student){
        try {
            Student savedStudent=studentService.addStudent(student);
            return ResponseEntity.ok("Registered!"+ student.toString());
        }catch (StudentEmptyNameException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




    @PostMapping(path="assignclass/{sid}/{cid}")
    public ResponseEntity<String> assignClass(@PathVariable("sid") Long studentId,
                                              @PathVariable("cid") Long classId) {
        try {

            Student updatedStudent = studentService.assignClass(studentId, classId);
            return ResponseEntity.ok("Assgined!" + updatedStudent.toString());

        } catch (StudentNonExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (InvalidUniversityClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
