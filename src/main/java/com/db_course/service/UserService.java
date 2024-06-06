package com.db_course.service;

import com.db_course.dao.UserDao;
import com.db_course.dto.UserDto;
import com.db_course.entity_model.User;

import java.sql.SQLException;
import java.util.function.Consumer;

import static com.db_course.obj_mapper.UserMapper.userToDto;

public class UserService {

    private static volatile UserService instance;
    private static final Object mutex = new Object();
    private final UserDao userDao;

    private UserService() {
        this.userDao = new UserDao();
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

    public UserDto login(String username, String password) {
        try {

            User person = userDao.getUserByUsername(username);
            if (person == null)
                throw new RuntimeException("username: " + username + " does not exist");

            else if (!password.equals(person.getPassword()))
                throw new RuntimeException("password does not match");


            return userToDto(person);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public UserDto createUser(String username, String name, String lastName, String password) {
        try {
            if (userDao.getUserByUsername(username) != null)
                throw new RuntimeException("username already exists, please choose another one.");

            return userToDto(userDao.insertUser(new User(username, name, lastName, password)));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void processAllUsers(Consumer<UserDto> consumer) {

        try {
            userDao.processAllUsers(
                    user -> {
                        UserDto userDto = userToDto(user);
                        consumer.accept(userDto);
                    });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
