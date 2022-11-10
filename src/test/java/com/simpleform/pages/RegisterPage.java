package com.simpleform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private By userNameInputField = By.name("username");
    private By emailInputField = By.name("email");
    private By passwordInputField = By.name("password");
    private By registerButton = By.name("sign-in");

    public void enterUserNameInputField(String username) {
        driver.findElement(userNameInputField).sendKeys(username);
    }

    public void enterEmailInputField(String email) {
        driver.findElement(emailInputField).sendKeys(email);
    }

    public void enterPasswordInputField(String password) {
        driver.findElement(passwordInputField).sendKeys(password);
    }


    public LoginPage clickRegisterButtonForSuccessfulRegister() {
        driver.findElement(registerButton).click();
        return new LoginPage(driver);
    }

    public ErrorPage clickRegisterButtonForUnsuccessfulRegister() {
        driver.findElement(registerButton).click();
        return new ErrorPage(driver);
    }

}
