package com.picarro.ui.tests;

import com.picarro.ui.base.BaseTest;
import com.picarro.ui.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelfHealingTest extends BaseTest {

    @Test
    public void verifySelfHealingLocator() {

        LoginPage loginPage = new LoginPage(smartDriver);

        // 🔥 Intentionally WRONG locator
        By brokenUsername = By.name("wrong_username");

        // SmartDriver should heal this
        smartDriver.findElement(brokenUsername).sendKeys("Admin");

        smartDriver.findElement(By.name("password")).sendKeys("admin123");

        smartDriver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("dashboard"),
                "❌ Self-healing failed. Login unsuccessful"
        );
    }
}
