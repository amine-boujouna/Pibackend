package com.NovaMind.Project.NovaMind.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

@RestController
@RequestMapping("/images")
public class ImageController {

    @GetMapping("/cloud-computing.jpg")
    public Resource getCloudComputingImage() {
        return new ClassPathResource("static/images/cloud-computing.jpg");
    }
}
