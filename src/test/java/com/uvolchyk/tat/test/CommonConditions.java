package com.uvolchyk.tat.test;

import com.uvolchyk.tat.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class CommonConditions {

    protected WebDriver driver;
    private DriverFactory driverFactory;

    @BeforeClass
    public void setupAll() {
        driverFactory = new DriverFactory();
    }

    @BeforeMethod
    public void setUp() {
        driver = driverFactory.getDriver(Boolean.valueOf(System.getProperty("isHeadless")));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driverFactory.closeDriver();
    }
}
