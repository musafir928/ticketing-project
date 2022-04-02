package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/update/{projectCode}")
    public String getEditProjectForm(@PathVariable String projectCode, Model model) {
        model.addAttribute("project", projectService.findById(projectCode));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("managers", userService.findManagers());
        return "/project/update";
    }




    @PostMapping("/update")
    public String updateProject(ProjectDTO project) {
        projectService.update(project);
        return "redirect:/project/create";
    }

    @GetMapping("/manager/project-status")
    public String getProjectByManager(Model model){

        UserDTO manager = userService.findById("john@cydeo.com");

        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);

        model.addAttribute("projects",projects);

        return "/manager/project-status";
    }

    @GetMapping("/manager/complete/{projectCode}")
    public String managerCompleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.complete(projectService.findById(projectCode));
        return "redirect:/project/manager/project-status";
    }
}
