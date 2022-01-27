package com.example.demo.api;

import com.example.demo.exception.InvalidUniversityClassException;
import com.example.demo.exception.StudentEmptyNameException;
import com.example.demo.exception.StudentNonExistException;
import com.example.demo.model.UniversityClass;
import com.example.demo.service.StudentService;
import com.example.demo.service.UniversityClassService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/class")
public class UniversityClassController {
    private UniversityClassService universityClassService;

    @Autowired
    public UniversityClassController(UniversityClassService universityClassService) {
        this.universityClassService = universityClassService;
    }

    @GetMapping
    public List<UniversityClass> getAllClasses(){
        return universityClassService.getAllUniversityClasses();
    }



    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody UniversityClass universityClass){
        try{
            UniversityClass savedUniversityClass=universityClassService.addClass(universityClass);
            return ResponseEntity.ok("Added class: "+ savedUniversityClass.toString());
        }catch (InvalidUniversityClassException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
