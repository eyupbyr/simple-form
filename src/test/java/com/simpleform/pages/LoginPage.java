package com.simpleform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By userNameInputField = By.name("username");
    private By passwordInputField = By.name("password");
    private By loginButton = By.name("sign-in");

    public void enterUserNameInputField(String username) {
        driver.findElement(userNameInputField).sendKeys(username);
    }

    public void enterPasswordInputField(String password) {
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public PersonalPage clickLoginButtonForSuccesfulLogin() {
        driver.findElement(loginButton).click();
        return new PersonalPage(driver);
    }

    public ErrorPage clickLoginButtonForUnuccesfulLogin() {
        driver.findElement(loginButton).click();
        return new ErrorPage(driver);
    }
}
