package com.picarro.api.tests;

import com.picarro.api.ai.AIResponseAnalyzer;
import com.picarro.api.base.BaseApiTest;
import com.picarro.api.services.CountriesService;
import com.picarro.api.validators.ResponseValidator;
import com.picarro.api.validators.SchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CountriesApiTest extends BaseApiTest {

    CountriesService service = new CountriesService();

    @Test
    public void verifyGetCountryByName() {

        Response response = service.getCountryByName("india");

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateNotEmpty(response);
        ResponseValidator.validateFieldPresent(response, "name");
        ResponseValidator.validateFieldPresent(response, "capital");

        AIResponseAnalyzer.validateResponse(response.asString());
    }

    @Test
    public void verifyFullNameSearch() {

        Response response = service.getCountryByName("india?fullText=true");

        ResponseValidator.validateStatusCode(response, 200);
    }

    // =========================
    // 🔹 CODE APIs
    // =========================

    @Test
    public void verifyCountryByCode() {

        Response response = service.getCountryByCode("IN");

        ResponseValidator.validateStatusCode(response, 200);
    }

    // =========================
    // 🔹 ALL API
    // =========================

    @Test
    public void verifyAllCountriesWithFields() {

        Response response = service.getAllCountriesWithFields("name,capital,currencies");

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldPresent(response, "name");

        SchemaValidator.validateSchema(response, "schemas/country-schema.json");
    }

    @Test
    public void verifyRegionFilterProper() {

        String region = "asia";

        Response response = service.getByRegion(region);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateNotEmpty(response);

        List<String> regions = response.jsonPath().getList("region");

        for (String r : regions) {
            Assert.assertEquals(
                    r.toLowerCase(),
                    region,
                    "❌ Country not in expected region"
            );
        }

        AIResponseAnalyzer.validateResponse(response.asString());
    }

    @Test
    public void verifyCurrencyFilterProper() {

        String currency = "usd";

        Response response = service.getByCurrency(currency);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateNotEmpty(response);

        List<Object> currencies = response.jsonPath().getList("currencies");

        for (Object c : currencies) {
            String currencyStr = c.toString().toLowerCase();

            Assert.assertTrue(
                    currencyStr.contains(currency),
                    "❌ Currency mismatch"
            );
        }

        AIResponseAnalyzer.validateResponse(response.asString());
    }

    @Test
    public void verifyLanguageFilterProper() {

        String language = "english";

        Response response = service.getByLanguage(language);

        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateNotEmpty(response);

        List<Object> languages = response.jsonPath().getList("languages");

        for (Object lang : languages) {
            String langStr = lang.toString().toLowerCase();

            Assert.assertTrue(
                    langStr.contains(language),
                    "❌ Language mismatch"
            );
        }
    }



    @Test
    public void verifyInvalidCountry() {

        Response response = service.getCountryByName("invalid123");

        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test
    public void verifyMissingFields() {

        Response response = service.getAllCountriesWithFields("");

        ResponseValidator.validateStatusCode(response, 400);
    }


    @Test
    public void verifyCaseInsensitiveSearch() {

        Response response = service.getCountryByName("INDIA");

        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test
    public void verifyNumericInput() {

        Response response = service.getCountryByName("123");

        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test
    public void verifySpecialCharacterInput() {

        Response response = service.getCountryByName("@#$%");

        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test
    public void verifyAIResponseValidation() {

        Response response = service.getCountryByName("india");

        AIResponseAnalyzer.validateResponse(response.asString());
    }
}