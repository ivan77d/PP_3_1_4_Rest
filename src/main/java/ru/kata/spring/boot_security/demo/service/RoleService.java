package ru.kata.spring.boot_security.demo.service;

import com.example.pp_3_1_2_springboot.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);
}
