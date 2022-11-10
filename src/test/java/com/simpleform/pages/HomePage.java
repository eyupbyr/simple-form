package com.simpleform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By navigateRegistrationButton = By.id("register");
    private By navigateLoginButton = By.id("login");

    public RegisterPage clickRegistrationNavigation() {
        driver.findElement(navigateRegistrationButton).click();
        return new RegisterPage(driver);
    }

    public LoginPage clickLoginNavigation() {
        driver.findElement(navigateLoginButton).click();
        return new LoginPage(driver);
    }
}
