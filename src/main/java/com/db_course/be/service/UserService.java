package com.db_course.be.service;

import com.db_course.be.dao.UserDao;
import com.db_course.be.entity_model.User;
import com.db_course.be.filter.entity_filters.impl.UserFilter;
import com.db_course.dto.UserDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.UserMapper.userToDto;

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

        User person = userDao.getUserByUsername(username);
        if (person == null)
            throw new RuntimeException("username: " + username + " does not exist");

        else if (!password.equals(person.getPassword()))
            throw new RuntimeException("password does not match");


        return userToDto(person);

    }


    public UserDto createUser(String username, String name, String lastName, String password) {
        if (userDao.getUserByUsername(username) != null)
            throw new RuntimeException("username already exists, please choose another one.");

        return userToDto(userDao.insertUser(new User(username, name, lastName, password)));


    }


    public void processAllUsers(Consumer<UserDto> consumer) {

        userDao.processAllUsers(
                user -> {
                    UserDto userDto = userToDto(user);
                    consumer.accept(userDto);
                });
    }

    public void processFilteredUsers(Consumer<UserDto> consumer, UserFilter filter) {

        userDao.processFilteredUsers(
                user -> {
                    UserDto userDto = userToDto(user);
                    consumer.accept(userDto);
                },
                filter
        );
    }

    public UserDto getUserById(int userId) {

        User u = userDao.getUserById(userId);
        return u == null ? null : userToDto(u);
    }

}
