package com.NovaMind.Project.NovaMind.Services;

import com.NovaMind.Project.NovaMind.Documents.CourseModule;
import com.NovaMind.Project.NovaMind.Repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private  ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<CourseModule> getModulesByCourse(Long courseId) {
        List<CourseModule> list =moduleRepository.findByCourseId(courseId);
        System.out.println(list);
        return  list;
    }
}
