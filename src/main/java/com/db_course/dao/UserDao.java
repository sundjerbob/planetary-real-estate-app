package com.db_course.dao;

import com.db_course.db_config.DB_Client;
import com.db_course.entity_model.User;

import java.sql.*;
import java.util.function.Consumer;

public class UserDao {


    private final Connection connection;
    private static final String table = "USERS";


    public UserDao() {
        this.connection = DB_Client.getInstance().getConnection();
    }


    /******************************************************************************************************************/
    public void processAllUsers(Consumer<User> personConsumer) throws SQLException {

        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT user_id, name, last_name, username, password FROM " + table
                );
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                int userId = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                User user = new User(userId, name, lastName, username, password);

                personConsumer.accept(user);
            }
        }

    }


    /******************************************************************************************************************/
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT user_id, name, last_name, username, password FROM " + table + " WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("last_name");
                    String password = resultSet.getString("password");
                    return new User(userId, name, lastName, username, password);
                }
            }
        }
        return null;
    }


    /******************************************************************************************************************/
    public User insertUser(User user) throws SQLException {
        String sql = "INSERT INTO " + table + " (name, last_name, username, password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }

        return user;
    }

}
