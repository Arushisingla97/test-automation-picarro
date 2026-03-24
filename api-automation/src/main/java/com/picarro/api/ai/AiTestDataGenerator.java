package com.picarro.api.ai;

import java.util.Random;

public class AiTestDataGenerator {

    public static String randomCountry(){

        String[] countries = {"india","germany","france","brazil","canada"};

        Random random = new Random();

        return countries[random.nextInt(countries.length)];
    }
}
