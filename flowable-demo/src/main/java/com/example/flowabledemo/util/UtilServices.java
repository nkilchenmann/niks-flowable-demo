package com.example.flowabledemo.util;


import java.util.Random;

public class UtilServices {
    public static String getDropoutOrCosimaResult() {
        Random random = new Random();
        if (random.nextBoolean()) {
            System.out.println("Result is OK");
            return "OK";
        } else {
            System.out.println("Result is NOK");
            return "NOK";
        }
    }
}
