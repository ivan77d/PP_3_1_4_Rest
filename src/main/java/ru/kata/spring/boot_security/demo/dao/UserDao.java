package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> userList();

    User userById(int id);

    void save(User user);

    void update(User user);

    void delete(int id);

    User getUserByLogin(String username);
}