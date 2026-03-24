package com.picarro.ui.ai;

import org.openqa.selenium.By;

public class LocatorAIEngine {

    public static By generateLocator(By originalLocator) {

        String locator = originalLocator.toString();

        if(locator.contains("username")) {
            return By.cssSelector("input[name='username']");
        }

        if(locator.contains("password")) {
            return By.cssSelector("input[name='password']");
        }

        if(locator.contains("submit")) {
            return By.xpath("//button[contains(text(),'Login')]");
        }

        return By.xpath("//button[contains(text(),'Login')]");
    }
}