package com.cydeo.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/project")
public class ProjectController {
    @GetMapping("/create")
    public String createProject(Model model) {
        return "/project/create";
    }
}
