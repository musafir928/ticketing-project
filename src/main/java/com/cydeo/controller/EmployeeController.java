package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import com.cydeo.service.impl.ProjectServiceIMPL;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("/employee")
public class EmployeeController {

    TaskService taskService;
    UserService userService;

    public EmployeeController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/pending-tasks")
    public String getPendingStatus(Model model) {
        UserDTO manager = userService.findById("john@cydeo.com");
        model.addAttribute("tasks", taskService.findTasksByManager(manager));
        return "task/pending-tasks";
    }
}
