package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public AdminsController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public AdminsController() {
    }

    @GetMapping
    public String AllUsers(Principal principal, Model model) {
        User user = userService.getUserByLogin(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(required = false, value = "role_id") Integer[] role_id) {
        user.setRoles(roleService.roleById(role_id));
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.userById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id,
                         @RequestParam (required = false, value = "role_id") Integer[] role_id) {
        user.setRoles(roleService.roleById(role_id));
        userService.update(id, user);
        return "redirect:/admin";
    }
}
