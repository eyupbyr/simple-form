package com.simpleform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorPage {
    private WebDriver driver;
    private By errorText = By.tagName("h1");

    public ErrorPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getErrorText() {
        return driver.findElement(errorText).getText();
    }
}
