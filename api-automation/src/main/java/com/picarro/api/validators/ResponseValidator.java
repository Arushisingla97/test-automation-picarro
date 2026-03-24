package com.picarro.api.validators;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    public static void validateStatusCode(Response response, int expected) {
        Assert.assertEquals(response.getStatusCode(), expected, "Status code mismatch");
    }

    public static void validateNotEmpty(Response response) {
        Assert.assertTrue(response.asString().length() > 0, "Empty response");
    }

    public static void validateFieldPresent(Response response, String field) {
        Assert.assertTrue(response.asString().contains(field),
                "Missing field: " + field);
    }
}