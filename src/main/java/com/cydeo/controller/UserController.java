package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    RoleService roleService;
    UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String getCreateUserForm(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());

        return "user/create";
    }

    @PostMapping("/create")
    public String createUser( UserDTO user, Model model) {

        userService.save(user);

        return "redirect:/user/create";

        // important note: / will omit all url part after domain, without / we will end up with some duplication in the url
    }

    @GetMapping("/update/{userName}")
    public String getEditUserForm(@PathVariable String userName, Model model) {

        model.addAttribute("user", userService.findById(userName));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());

        return "user/update";
    }

    @PostMapping("/update")
    public String updateUser(UserDTO user) {
        userService.update(user);
        return "redirect:/user/create";
    }

    @GetMapping("/delete/{userName}")
    public String delete(@PathVariable String userName) {
        userService.deleteById(userName);
        return "redirect:/user/create";
    }

}
