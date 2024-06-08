package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.User;
import com.db_course.be.filter.entity_filters.impl.UserFilter;

import java.sql.*;
import java.util.function.Consumer;

public class UserDao {


    private final Connection connection;
    private static final String TABLE = "USERS";


    public UserDao() {
        this.connection = DB_Client.getInstance().getConnection();
    }


    /******************************************************************************************************************/
    public void processAllUsers(Consumer<User> consumer) {

        String sql = "SELECT user_id, name, last_name, username, password FROM " + TABLE;

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("UserDao.processAllUsers() says: " + e.getMessage());
        }

    }


    /******************************************************************************************************************/
    public void processFilteredUsers(Consumer<User> consumer, UserFilter userFilter) {

        try (
                PreparedStatement statement = userFilter.generatePreparedStatement();
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("UserDao.processFilteredUsers() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM " + TABLE + " WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? mapToUser(resultSet) : null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("UserDao.getUserByUsername() says: " + e.getMessage());
        }
    }

    /******************************************************************************************************************/
    public User getUserById(int id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? mapToUser(resultSet) : null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("UserDao.getUserByUsername() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    public User insertUser(User user) {
        String sql = "INSERT INTO " + TABLE + " (name, last_name, username, password) VALUES (?, ?, ?, ?)";

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
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("UserDao.insertUser() says: " + e.getMessage());
        }

        return user;
    }


    User mapToUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("user_id");
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("last_name");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        return new User(id, name, lastName, username, password);
    }
}
