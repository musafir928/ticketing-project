package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping("/project")
public class ProjectController {
    ProjectService projectService;
    UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String getCreateProjectForm(Model model) {
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("managers", userService.findManagers());
        return "/project/create";
    }

    @PostMapping("/create")
    public String createProject(ProjectDTO project) {

        projectService.save(project);

        return "redirect:/project/create";
    }

    @GetMapping ("/delete/{projectCode}")
    public String deleteProject(@PathVariable String projectCode) {

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping ("/complete/{projectCode}")
    public String completeProject(@PathVariable String projectCode) {

        projectService.complete(projectService.findById(projectCode));

        return "redirect:/project/create";
    }
}
