package com.db_course.server.db.dao;

import com.db_course.server.db.entity_model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    private final Connection connection;

    public PersonDao(Connection connection) {
        this.connection = connection;
    }

    public List<Person> getAllUsers() throws SQLException {
        List<Person> userList = new ArrayList<>();

        // Prepare SQL query
        String sql = "SELECT person_id, name, last_name, username, password FROM PERSON";

        try (
                // Create PreparedStatement
                PreparedStatement statement = connection.prepareStatement(sql);
                // Execute query and get ResultSet
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve user data from the result set
                int personId = resultSet.getInt("person_id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                // Create a User object and add it to the list
                Person user = new Person(personId, name, lastName, username, password);
                userList.add(user);
            }
        }

        return userList;
    }

    // Method to fetch a user by username
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

    // Method to check if a username already exists
    public boolean doesUsernameExist(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM PERSON WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
