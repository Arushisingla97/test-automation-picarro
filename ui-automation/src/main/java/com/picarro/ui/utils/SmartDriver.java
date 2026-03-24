package com.picarro.ui.utils;

import com.picarro.ui.ai.AILocatorHealer;
import com.picarro.ui.ai.DOMFetcher;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class SmartDriver {

    private WebDriver driver;

    public SmartDriver(WebDriver driver) {
        this.driver = driver;
    }

    // =========================
    // 🔥 findElement
    // =========================
    public WebElement findElement(By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {

            System.out.println("❌ Original locator failed: " + locator);

            try {
                String dom = DOMFetcher.getDOM(driver);

                String healed = AILocatorHealer.healLocator(locator.toString(), dom);

                System.out.println("🤖 Healed locator: " + healed);

                // ❌ Reject same locator
                String brokenValue = "";
                if (locator.toString().contains("=")) {
                    brokenValue = locator.toString().split("=", 2)[1].trim();
                }

                if (healed.contains(brokenValue)) {
                    System.out.println("⚠️ AI returned same locator. Skipping AI.");
                    throw new RuntimeException("Element not found using locator: " + locator);
                }

                By newLocator = convertToBy(healed);

                return wait.until(ExpectedConditions.presenceOfElementLocated(newLocator));

            } catch (Exception aiError) {

                throw new RuntimeException(
                        "Both original and AI locator failed: " + locator,
                        aiError
                );
            }
        }
    }

    // =========================
    // 🔥 findElements
    // =========================
    public List<WebElement> findElements(By locator) {

        List<WebElement> elements = driver.findElements(locator);

        if (!elements.isEmpty()) {
            return elements;
        }

        System.out.println("❌ No elements found. Attempting AI healing...");

        try {
            String dom = DOMFetcher.getDOM(driver);

            String healed = AILocatorHealer.healLocator(locator.toString(), dom);

            System.out.println("🤖 Healed locator (findElements): " + healed);

            String brokenValue = "";
            if (locator.toString().contains("=")) {
                brokenValue = locator.toString().split("=", 2)[1].trim();
            }

            if (healed.contains(brokenValue)) {
                throw new RuntimeException("AI returned same locator");
            }

            By newLocator = convertToBy(healed);

            elements = driver.findElements(newLocator);

            if (!elements.isEmpty()) {
                return elements;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Both original and AI locator failed: " + locator);
    }

    // =========================
    // 🔥 convertToBy
    // =========================
    private By convertToBy(String locator) {

        String[] parts = locator.split("=", 2);

        if (parts.length < 2) {
            throw new RuntimeException("Invalid locator: " + locator);
        }

        String type = parts[0].trim();
        String value = parts[1].trim();

        switch (type) {
            case "id":
                return By.id(value);
            case "name":
                return By.name(value);
            case "xpath":
                return By.xpath(value);
            case "css":
                return By.cssSelector(value);
            default:
                throw new RuntimeException("Unsupported locator: " + locator);
        }
    }
}