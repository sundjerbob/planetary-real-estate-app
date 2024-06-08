package com.db_course.be.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private int id;
    private String name;
    private String lastName;
    private String username;
    private String password;

    public User(String name, String lastName, String username, String password) {

        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

    }
}