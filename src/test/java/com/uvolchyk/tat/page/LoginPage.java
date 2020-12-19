package com.uvolchyk.tat.page;

import com.uvolchyk.tat.wait.CustomConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    private final String PAGE_URL = "https://signin.ebay.com/signin/";

    @FindBy(id = "userid")
    private WebElement credentialInput;

    @FindBy(id = "signin-continue-btn")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public LoginPage putEmail(String email) {
//        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.waitForLoad());
        credentialInput.sendKeys(email);
        return this;
    }

    public LoginPage putPassword(String password) {

        credentialInput.sendKeys(password);
        return this;
    }

    public LoginPage next() {
        signInButton.click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.waitForLoad());
        logger.info(driver.getCurrentUrl());
        return this;
    }
}
