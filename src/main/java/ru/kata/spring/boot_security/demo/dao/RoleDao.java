package ru.kata.spring.boot_security.demo.dao;

import com.example.pp_3_1_2_springboot.model.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);
}
