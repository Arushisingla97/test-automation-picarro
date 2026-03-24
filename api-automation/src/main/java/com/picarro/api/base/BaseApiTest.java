package com.picarro.api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    @BeforeClass
    public void setup(){

        RestAssured.baseURI = "https://restcountries.com/v3.1";
    }
}