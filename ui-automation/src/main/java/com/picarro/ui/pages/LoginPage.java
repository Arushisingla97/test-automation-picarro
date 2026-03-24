package com.picarro.ui.pages;

import com.picarro.ui.utils.SmartDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {

    private SmartDriver smartDriver;

    public LoginPage(SmartDriver smartDriver) {
        this.smartDriver = smartDriver;
    }

    // 🔥 Locators (moved here)
    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
    private By requiredFieldError = By.xpath("//span[text()='Required']");
    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By leaveMenu = By.xpath("//span[text()='Leave']");



    public void login(String user, String pass){
        smartDriver.findElement(username).sendKeys(user);
        smartDriver.findElement(password).sendKeys(pass);
        smartDriver.findElement(loginBtn).click();
    }

    public void clickLogin(){
        smartDriver.findElement(loginBtn).click();
    }

    public boolean isErrorMessageDisplayed(){
        return smartDriver.findElement(errorMessage).isDisplayed();
    }

    public int getRequiredFieldErrorCount(){
        List<WebElement> errors = smartDriver.findElements(requiredFieldError);
        return errors.size();
    }

    public boolean isUsernameFieldHighlighted(){
        String borderColor = smartDriver.findElement(username)
                .getCssValue("border-color");
        return borderColor.contains("red");
    }

    public boolean isPasswordFieldHighlighted(){
        String borderColor = smartDriver.findElement(password)
                .getCssValue("border-color");
        return borderColor.contains("red");
    }

    public boolean isDashboardVisible() {
        return smartDriver.findElement(dashboardHeader).isDisplayed();
    }

    public boolean isPIMVisible() {
        return smartDriver.findElement(pimMenu).isDisplayed();
    }

    public boolean isLeaveVisible() {
        return smartDriver.findElement(leaveMenu).isDisplayed();
    }
}