package com.picarro.ui.tests;

import com.picarro.ui.base.BaseTest;
import com.picarro.ui.pages.DashboardPage;
import com.picarro.ui.pages.LoginPage;
import com.picarro.ui.pages.PIMPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddEmployeeTest extends BaseTest {

    private DashboardPage dashboardPage;
    private PIMPage pimPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setupTest() {

        loginPage = new LoginPage(smartDriver);
        dashboardPage = new DashboardPage(smartDriver);
        pimPage = new PIMPage(smartDriver);
        loginPage.login("Admin", "admin123");

    }

    @Test
    public void verifyDashboard() {

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard not visible"
        );
    }

    @Test
    public void verifyAddEmployee() {
        dashboardPage.clickPIM();
        pimPage.addEmployee("Test", "User");

        Assert.assertTrue(
                pimPage.isPersonalDetailsDisplayed(),
                "Employee not added successfully"
        );
    }

    @Test
    public void verifyRequiredFieldError() {
        dashboardPage.clickPIM();
        pimPage.addEmployeeWithoutName();
        Assert.assertTrue(
                pimPage.getRequiredFieldErrorCount(),
                "Required field validation not shown"
        );
    }

    @Test
    public void verifyEmployeeAddedInList() {

        String firstName = "Test";
        String lastName = "User";
        dashboardPage.clickPIM();

        pimPage.addEmployee(firstName, lastName);
        String empId = pimPage.getEmployeeId();

        Assert.assertTrue(
                pimPage.isPersonalDetailsDisplayed(),
                "Employee not created"
        );

        dashboardPage.clickPIM();

        Assert.assertTrue(
                pimPage.searchEmployee(empId),
                "Employee not found using ID: " + empId
        );
    }

    @Test
    public void verifyAddEmployeeFormFields() {

        Assert.assertTrue(
                pimPage.isAddEmployeeFormDisplayed(),
                "Add Employee form fields not displayed properly"
        );
    }
}