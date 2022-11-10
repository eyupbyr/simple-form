package com.simpleform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalPage {

    private WebDriver driver;
    private By welcomeText = By.tagName("h1");

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getWelcomeText() {
        return driver.findElement(welcomeText).getText();
    }
}
