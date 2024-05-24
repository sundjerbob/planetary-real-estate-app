package com.db_course.db;

import com.db_course.db.service.UserService;
import com.db_course.dto.UserDto;

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
        UserDto user = UserService.getInstance().login("cacaj", "123");
        System.out.println(user);
        // login failed test
        UserService.getInstance().login("cacaj", "123");
        // list all people
        UserService.getInstance().processAllUsers(System.out::println);

    }


}
