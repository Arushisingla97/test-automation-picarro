package com.picarro.ui.tests;

import com.picarro.ui.base.BaseTest;
import com.picarro.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(smartDriver);
    }

    @Test
    public void verifyValidLogin() {

        loginPage.login("Admin", "admin123");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("dashboard"),
                "User not redirected to dashboard"
        );
    }

    @Test
    public void verifyInvalidLogin() {

        loginPage.login("wrong", "wrong");

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message not displayed"
        );
    }

    @Test
    public void verifyBlankLogin() {

        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isUsernameFieldHighlighted(),
                "Username field not highlighted"
        );

        Assert.assertTrue(
                loginPage.isPasswordFieldHighlighted(),
                "Password field not highlighted"
        );
    }
}