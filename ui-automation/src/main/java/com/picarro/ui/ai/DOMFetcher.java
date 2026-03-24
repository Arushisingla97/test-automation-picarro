package com.picarro.ui.ai;

import org.openqa.selenium.WebDriver;

public class DOMFetcher {

    public static String getDOM(WebDriver driver) {

        return driver.getPageSource();

    }

}
