package com.db_course.db.service;

import com.db_course.db.config.DB_Client;
import com.db_course.db.dao.UserDao;
import com.db_course.db.entity_model.User;

import java.sql.SQLException;
import java.util.function.Consumer;

public class UserService {

    private static UserService instance;
    private static final Object mutex = new Object();
    private final UserDao userDao;

    private UserService() {
        this.userDao = new UserDao(DB_Client.getInstance().getConnection());
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public User login(String username, String password) {
        try {

            User person = userDao.getUserByUsername(username);
            if (person == null)
                throw new RuntimeException("username: " + username + " does not exist");

            else if (!password.equals(person.getPassword()))
                throw new RuntimeException("password does not match");


            return person;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public User createUser(String username, String name, String lastName, String password) {
        try {
            if (userDao.getUserByUsername(username) != null)
                throw new RuntimeException("username already exists, please choose another one.");

            return userDao.insertUser(new User(username, name, lastName, password));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void processAllUsers(Consumer<User> consumer) {

        try {
            userDao.processAllUsers(
                    user -> {
                        /*System.out.println(user);*/
                        consumer.accept(user);
                    });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
