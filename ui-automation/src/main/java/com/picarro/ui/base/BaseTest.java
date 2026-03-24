package com.picarro.ui.base;

import org.testng.annotations.Parameters;
import com.picarro.ui.utils.SmartDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;

public class BaseTest {

    public WebDriver driver;
    protected SmartDriver smartDriver;

    @Parameters({"headless"})
    @BeforeMethod
    public void setup(@Optional("false") String headless) {

        boolean isHeadless = Boolean.parseBoolean(headless);

        driver = DriverFactory.initDriver(isHeadless);

        // ✅ IMPORTANT FIX
        smartDriver = new SmartDriver(driver);

        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}