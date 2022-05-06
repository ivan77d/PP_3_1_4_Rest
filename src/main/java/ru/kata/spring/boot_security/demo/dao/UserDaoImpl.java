package ru.kata.spring.boot_security.demo.dao;



import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> userList() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public User userById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }


    public void update(int id, User updateUser) {
        User user = userById(id);
        user.setName(updateUser.getName());
        user.setSurname(updateUser.getSurname());
        user.setAge(updateUser.getAge());
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        user.setRoles(updateUser.getRoles());
        entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserByLogin(String username) {
//        return entityManager
//                .createQuery("select user from User user where user.username = :username", User.class)
//                .setParameter("username", username).getSingleResult();

        TypedQuery<User> q = entityManager
                .createQuery("select user from User user where user.username = :username", User.class);
        q.setParameter("username", username);
        return q.getSingleResult();
    }
}
