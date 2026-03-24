package com.picarro.api.validators;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidator {

    public static void validateSchema(Response response, String schemaFile) {
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath(schemaFile));
    }
}