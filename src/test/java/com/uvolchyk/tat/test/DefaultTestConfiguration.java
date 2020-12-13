package com.uvolchyk.tat.test;

import com.uvolchyk.tat.driver.DriverFactory;
import com.uvolchyk.tat.service.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class DefaultTestConfiguration {

    protected WebDriver driver;
    protected TestDataReader reader;
    private DriverFactory driverFactory;

    @BeforeClass
    public void setupAll() {
        driverFactory = new DriverFactory();
        reader = new TestDataReader();
    }

    @BeforeMethod
    public void setUp() {
        driver = driverFactory.getDriver(true);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driverFactory.closeDriver();
    }
}
