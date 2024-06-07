package com.db_course.test;

import com.db_course.be.service.UserService;

public class UserTest {

    public static void main(String[] args) {
        UserTest userTest = new UserTest();

        userTest.testUserCreation();

        // Test login success
        userTest.testLoginSuccess();

        // Test login with username that doesn't exist
//        userTest.testLoginUsernameNotExist();

        // Test login with incorrect password
//        userTest.testLoginIncorrectPassword();

        // Test processing all users
        userTest.testProcessAllUsers();
    }

    void testUserCreation() {
        System.out.println(
                UserService.getInstance().createUser(
                        "cacaj",
                        "Arandjelovic",
                        "cacaj",
                        "123"
                )
        );
    }

    void testLoginSuccess() {
        System.out.println("login success");
        System.out.println(UserService.getInstance().login("cacaj", "123"));
    }

    void testLoginUsernameNotExist() {
        UserService.getInstance().login("cacaj12341", "123");
    }

    void testLoginIncorrectPassword() {
        UserService.getInstance().login("cacaj", "1234");
    }

    void testProcessAllUsers() {
        UserService.getInstance().processAllUsers(System.out::println);
    }
}
