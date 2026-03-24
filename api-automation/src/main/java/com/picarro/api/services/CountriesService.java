package com.picarro.api.services;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CountriesService {

    public Response getCountryByName(String name) {
        return given()
                .when()
                .get("/name/" + name)
                .then()
                .extract()
                .response();
    }

    public Response getCountryByCode(String code) {
        return given()
                .when()
                .get("/alpha/" + code)
                .then()
                .extract()
                .response();
    }

    public Response getAllCountriesWithFields(String fields) {
        return given()
                .queryParam("fields", fields)
                .when()
                .get("/all")
                .then()
                .extract()
                .response();
    }

    public Response getByRegion(String region) {
        return given()
                .when()
                .get("/region/" + region)
                .then()
                .extract()
                .response();
    }

    public Response getByCurrency(String currency) {
        return given()
                .when()
                .get("/currency/" + currency)
                .then()
                .extract()
                .response();
    }

    public Response getByLanguage(String language) {
        return given()
                .when()
                .get("/lang/" + language)
                .then()
                .extract()
                .response();
    }
}