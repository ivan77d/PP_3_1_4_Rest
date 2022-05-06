package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDaoImpl userDao;

    @Autowired
    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> allUsers() {
        return userDao.userList();
    }

    @Override
    public User userById(int id) {
        return userDao.userById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(int id, User updateUser) {
        userDao.update(id, updateUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User getUserByLogin(String username) {
        return userDao.getUserByLogin(username);
    }

    @Override

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.getUserByLogin(s);
    }
}
