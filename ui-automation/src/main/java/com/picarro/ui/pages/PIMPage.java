package com.picarro.ui.pages;

import com.picarro.ui.utils.SmartDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class PIMPage {

    private SmartDriver smartDriver;

    public PIMPage(SmartDriver smartDriver) {
        this.smartDriver = smartDriver;
    }

    private By pim = By.xpath("//span[text()='PIM']");
    private By addEmployeeBtn = By.xpath("//a[text()='Add Employee']");
    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By saveBtn = By.xpath("//button[@type='submit']");
    private By personalDetails = By.xpath("//h6[text()='Personal Details']");
    private By successMessage = By.xpath("//p[contains(@class,'oxd-text--toast-message')]");
    private By requiredFieldError = By.xpath("(//span[text()='Required'])[1]");
    private By employeeListSearch = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    private By searchBtn = By.xpath("//button[@type='submit']");
    private By employeeNameResult = By.xpath("//div[@role='row']//div[contains(text(),'%s')]");
    private By employeeIdField = By.xpath("//label[text()='Employee Id']/../following-sibling::div//input");
    private By employeeIdSearch = By.xpath("//label[text()='Employee Id']/../following-sibling::div//input");


    public void addEmployee(String fName, String lName) {
        smartDriver.findElement(pim).click();
        smartDriver.findElement(addEmployeeBtn).click();
        smartDriver.findElement(firstName).sendKeys(fName);
        smartDriver.findElement(lastName).sendKeys(lName);
        smartDriver.findElement(saveBtn).click();
    }

    public boolean isPersonalDetailsDisplayed() {
        Assert.assertTrue(smartDriver.findElement(personalDetails).isDisplayed());
        return true;
    }

    public boolean isSuccessMessageDisplayed() {
        return smartDriver.findElement(successMessage).isDisplayed();
    }

    public void addEmployeeWithoutName(){
        smartDriver.findElement(pim).click();
        smartDriver.findElement(addEmployeeBtn).click();
        smartDriver.findElement(saveBtn).click();
    }

    public boolean getRequiredFieldErrorCount() {
        return smartDriver.findElement(requiredFieldError).isDisplayed();

    }


    public boolean searchEmployee(String empId) {

        smartDriver.findElement(employeeIdSearch).clear();
        smartDriver.findElement(employeeIdSearch).sendKeys(empId);

        smartDriver.findElement(searchBtn).click();

        try {
            return smartDriver.findElement(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[2]/div")
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddEmployeeFormDisplayed() {
        smartDriver.findElement(pim).click();
        smartDriver.findElement(addEmployeeBtn).click();
        try {
            return smartDriver.findElement(firstName).isDisplayed()
                    && smartDriver.findElement(lastName).isDisplayed()
                    && smartDriver.findElement(employeeIdField).isDisplayed()
                    && smartDriver.findElement(saveBtn).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public String getEmployeeId() {
        return smartDriver.findElement(employeeIdField).getAttribute("value");
    }
}

