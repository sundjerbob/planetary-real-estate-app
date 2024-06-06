package com.db_course;

import java.math.BigDecimal;

public class AppBootstrap {
    public static void main(String[] args) {
        BigDecimal num = new BigDecimal("0.01");
        boolean what =  num instanceof Number;
        System.out.println(what);
    }
}
