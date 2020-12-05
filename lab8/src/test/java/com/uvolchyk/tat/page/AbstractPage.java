package com.uvolchyk.tat.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected final int WAIT_TIMEOUT_SECONDS = 20;
    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    protected abstract AbstractPage openPage();
}
