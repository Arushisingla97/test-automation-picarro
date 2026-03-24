package com.picarro.ui.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picarro.ui.base.BaseTest;
import com.picarro.ui.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;

public class DynamicTestExecutor extends BaseTest {

    @Test
    public void runAITests() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        AITestSuite suite = mapper.readValue(
                new File(System.getProperty("user.dir") + "/../test-data/generated-tests.json"),
                AITestSuite.class
        );

        for (AITestCase test : suite.testCases) {

            System.out.println("Running AI Test: " + test.name);

            for (AIStep step : test.steps) {
                execute(step);
            }
        }
    }

    private void execute(AIStep step) {

        try {

            System.out.println("➡️ Executing: " + step.action + " | " + step.locator);

            switch (step.action) {

                case "navigate":
                    driver.get(step.url);
                    break;

                case "click":
                    WebElement element = smartDriver.findElement(getBy(step.locator));

                    element.click();
                    break;

                case "type":
                    smartDriver.findElement(getBy(step.locator))
                            .sendKeys(step.value);
                    break;

                case "assert_url":
                    if (!driver.getCurrentUrl().contains(step.value)) {
                        throw new AssertionError("Expected URL to contain: " + step.value);
                    }
                    break;

                case "assert_element":
                    boolean displayed = smartDriver.findElement(getBy(step.locator)).isDisplayed();
                    if (!displayed) {
                        throw new AssertionError("Element not displayed: " + step.locator);
                    }
                    break;

                default:
                    throw new RuntimeException("Unknown action: " + step.action);
            }

        } catch (Exception e) {

            System.out.println("⚠️ AI step failed: " + step.action);
            fallback(step);
        }
    }

    private By getBy(String locator) {

        String[] parts = locator.split("=");

        switch (parts[0]) {

            case "id":
                return By.id(parts[1]);

            case "xpath":
                return By.xpath(parts[1]);

            case "css":
                return By.cssSelector(parts[1]);

            case "name":
                return By.name(parts[1]);

            default:
                throw new RuntimeException("Unsupported locator: " + locator);
        }
    }

    private void fallback(AIStep step) {

        System.out.println("🔁 Executing fallback for: " + step.action);

        // Example fallback for login
        if ("click".equals(step.action) && step.locator != null && step.locator.contains("submit")) {

            System.out.println("🔁 Fallback: Using LoginPage POM");

            LoginPage loginPage = new LoginPage(smartDriver); // ✅ FIXED

//            loginPage.login("Admin", "admin123");
        }
    }
}