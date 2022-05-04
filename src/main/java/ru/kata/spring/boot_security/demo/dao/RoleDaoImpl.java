package ru.kata.spring.boot_security.demo.dao;

import com.example.pp_3_1_2_springboot.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select role from Role role where role.name=:name", Role.class)
                .setParameter("name", name).getSingleResult();
    }
}
