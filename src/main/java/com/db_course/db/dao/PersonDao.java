package com.db_course.db.dao;

import com.db_course.db.entity_model.Person;

import java.sql.*;
import java.util.function.Consumer;

public class PersonDao {

    private final Connection connection;

    public PersonDao(Connection connection) {
        this.connection = connection;
    }

    public void processAllUsers(Consumer<Person> personConsumer) throws SQLException {

        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT person_id, name, last_name, username, password FROM PERSON"
                );
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                int personId = resultSet.getInt("person_id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                Person user = new Person(personId, name, lastName, username, password);

                personConsumer.accept(user);
            }
        }

    }

    public Person getUserByUsername(String username) throws SQLException {
        String sql = "SELECT person_id, name, last_name, username, password FROM PERSON WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int personId = resultSet.getInt("person_id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("last_name");
                    String password = resultSet.getString("password");

                    return new Person(personId, name, lastName, username, password);
                }
            }
        }
        return null;
    }

    public Person insertUser(Person person) throws SQLException {
        String sql = "INSERT INTO PERSON (name, last_name, username, password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getLastName());
            statement.setString(3, person.getUsername());
            statement.setString(4, person.getPassword());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setPersonId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }

        return person;
    }

}
