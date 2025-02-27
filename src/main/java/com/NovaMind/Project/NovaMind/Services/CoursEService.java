package com.NovaMind.Project.NovaMind.Services;

import com.NovaMind.Project.NovaMind.Documents.Course;
import com.NovaMind.Project.NovaMind.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoursEService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

}
