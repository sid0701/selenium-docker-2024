package com.sidgupta.pages.vendorapplication;

import com.sidgupta.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.usernameInput));
        return this.usernameInput.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void login(String uname, String password){
        this.usernameInput.sendKeys(uname);
        this.passwordInput.sendKeys(password);
        this.loginButton.click();
    }

}
