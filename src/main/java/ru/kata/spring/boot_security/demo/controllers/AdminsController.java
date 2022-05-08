package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//        //юзер по логину
//        User user = (User) userService.loadUserByUsername(principal.getName());
//        model.addAttribute("currentuser", user); //сюда приходят данные текущего юзера админа
//        //передаем лист юзеров в форму
//        List<User> users = userService.allUsers();
//        model.addAttribute("users", users);
//        //передаем лист ролей в форму
//        List<Role> roles = roleService.getAllRoles();
//        model.addAttribute("roleS", roles);
//        //новый юзер
//        User newuser = new User();
//        model.addAttribute("newuser", newuser);
        User user = userService.getUserByLogin(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.allUsers());
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

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id,
                         @RequestParam (required = false, value = "role_id") Integer[] role_id) {
        user.setRoles(roleService.roleById(role_id));
        userService.update(id, user);
        return "redirect:/admin";
    }
}
//<select size="2" name="role_id" multiple>
//<option th:each="role : ${roles}"
//        th:value="${role.name}"
//        th:text="${role.getName()}">
//</option>
//</select>