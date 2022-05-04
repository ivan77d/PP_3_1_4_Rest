package ru.kata.spring.boot_security.demo.service;

import com.example.pp_3_1_2_springboot.dao.RoleDaoImpl;
import com.example.pp_3_1_2_springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {


    private final RoleDaoImpl roleDao;

    @Autowired
    public RoleServiceImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
