package com.db_course.test;

import com.db_course.db.service.UserService;

public class UserTest {

    public static void main(String[] args) {



    }


    void testUserCreate() {
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
        UserService.getInstance().login("cacaj", "123");
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
