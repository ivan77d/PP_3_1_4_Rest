package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {

    public List<Role> getAllRoles();

  //  public Role getRoleByName(String name);

  //  public void save(Role role);

    Set<Role> roleById(Integer[] role_id);
}
