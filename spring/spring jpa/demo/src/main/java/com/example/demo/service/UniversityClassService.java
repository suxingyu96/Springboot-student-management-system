package com.example.demo.service;

import com.example.demo.dao.UniversityClassDao;
import com.example.demo.exception.InvalidUniversityClassException;
import com.example.demo.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UniversityClassService {
    UniversityClassDao universityClassDao;


    @Autowired
    public UniversityClassService(UniversityClassDao universityClassDao) {
        this.universityClassDao = universityClassDao;
    }


    public List<UniversityClass> getAllUniversityClasses(){
        return (List<UniversityClass>) universityClassDao.findAll();
    }


    public UniversityClass addClass(UniversityClass universityClass){
        int currentYear= Calendar.getInstance().get(Calendar.YEAR);

        if(universityClass.getYear() < currentYear){
            throw new InvalidUniversityClassException("Cannot add the class in the past.");
        }
        if(universityClass.getYear() >currentYear+1)
            throw new InvalidUniversityClassException("Cannot add the class in the future.");

        return universityClassDao.save(universityClass);

    }
}
