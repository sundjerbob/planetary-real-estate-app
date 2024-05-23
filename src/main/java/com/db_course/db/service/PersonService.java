package com.db_course.db.service;

import com.db_course.db.config.DB_Client;
import com.db_course.db.dao.PersonDao;
import com.db_course.db.entity_model.Person;

import java.sql.SQLException;
import java.util.function.Consumer;

public class PersonService {

    private static PersonService instance;
    private static final Object mutex = new Object();
    private final PersonDao personDao;

    private PersonService() {
        this.personDao = new PersonDao(DB_Client.getInstance().getConnection());
    }

    public static PersonService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new PersonService();
                }
            }
        }
        return instance;
    }

    public Person login(String username, String password) {
        try {

            Person person = personDao.getUserByUsername(username);
            if (person == null)
                throw new RuntimeException("username: " + username + " does not exist");

            else if (!password.equals(person.getPassword()))
                throw new RuntimeException("password does not match");


            return person;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Person createPerson(String username, String name, String lastName, String password) {
        try {
            if (personDao.getUserByUsername(username) != null)
                throw new RuntimeException("username already exists, please choose another one.");

            return personDao.insertUser(new Person(username, name, lastName, password));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void getAllPeople(int celestialBodyId, Consumer<Person> consumer) {

        try {
            personDao.processAllUsers(
                    person -> {
                        System.out.println(person);
                        consumer.accept(person);
                    });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
