package com.picarro.common.utils;

import com.aventstack.extentreports.*;
import org.testng.*;
import com.picarro.ui.base.BaseTest;
import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    public void onTestSuccess(ITestResult result) {
        test.get().pass("✅ Test Passed");
    }

    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        try {
            WebDriver driver = ((BaseTest) result.getInstance()).driver;

            String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());

            if (path != null) {
                test.get().addScreenCaptureFromPath(path);
            } else {
                test.get().info("Screenshot failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}