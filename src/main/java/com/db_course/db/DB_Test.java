package com.db_course.db;

import com.db_course.db.service.UserService;

public class DB_Test {

    public static void main(String[] args) {


        // person create test
        System.out.println(
                UserService.getInstance().createUser(
                        "cacaj",
                        "Arandjelovic",
                        "cacaj",
                        "123"
                )
        );

        // login success test
        UserService.getInstance().login("cacaj", "123");
        // login failed test
        UserService.getInstance().login("cacaj", "123");
        // list all people
        UserService.getInstance().processAllUsers(System.out::println);

    }


}
