package com.picarro.ui.pages;

import com.picarro.ui.utils.SmartDriver;
import org.openqa.selenium.By;

public class DashboardPage {

    private SmartDriver smartDriver;

    public DashboardPage(SmartDriver smartDriver) {
        this.smartDriver = smartDriver;
    }

    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By leaveMenu = By.xpath("//span[text()='Leave']");

    public boolean isDashboardDisplayed() {
        try {
            return smartDriver.findElement(dashboardHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPIM() {
        smartDriver.findElement(pimMenu).click();
    }

    public void clickLeave() {
        smartDriver.findElement(leaveMenu).click();
    }

    public boolean isPIMVisible() {
        return smartDriver.findElement(pimMenu).isDisplayed();
    }

    public boolean isLeaveVisible() {
        return smartDriver.findElement(leaveMenu).isDisplayed();
    }
}