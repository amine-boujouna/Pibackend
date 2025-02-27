package com.NovaMind.Project.NovaMind.controller;

import com.NovaMind.Project.NovaMind.Documents.Course;
import com.NovaMind.Project.NovaMind.Services.CoursEService;
import com.NovaMind.Project.NovaMind.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private CoursEService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    @Autowired
    private PaymentService paymentService;


}

