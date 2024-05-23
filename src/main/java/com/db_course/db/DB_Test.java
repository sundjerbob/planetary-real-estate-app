package com.db_course.db;

import com.db_course.db.service.PersonService;

public class DB_Test {

    public static void main(String[] args) {


        System.out.println(
                PersonService.getInstance().createPerson(
                        "Mihajlo ",
                        "Arandjelovic",
                        "cacaj",
                        "123"
                )
        );

    }
}
