package com.NovaMind.Project.NovaMind.controller;

import com.NovaMind.Project.NovaMind.Documents.Course;
import com.NovaMind.Project.NovaMind.Documents.CourseModule;
import com.NovaMind.Project.NovaMind.Services.CoursEService;
import com.NovaMind.Project.NovaMind.Services.ModuleService;
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
    private  CoursEService courseService;
@Autowired
    private  ModuleService moduleService;

    public CourseController(CoursEService courseService, ModuleService moduleService) {
        this.courseService = courseService;
        this.moduleService = moduleService;
    }

    @GetMapping

    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        for (Course course : courses) {
            String imageUrl = "http://localhost:8081/images/" + course.getImageUrl().trim();
            course.setImageUrl(imageUrl);
        }
        System.out.println("Courses: " + courses);
        return ResponseEntity.ok(courses);
    }



    @GetMapping("/{courseId}/modules")
    public List<CourseModule> getModulesByCourse(@PathVariable Long courseId) {
        return moduleService.getModulesByCourse(courseId);
    }

    @Autowired
    private PaymentService paymentService;


}

