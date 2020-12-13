package com.uvolchyk.tat.test;

import com.uvolchyk.tat.driver.DriverFactory;
import com.uvolchyk.tat.page.EbayEnglishHomePage;
import com.uvolchyk.tat.service.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class EbayFunctionalTest {

    private WebDriver driver;
    private TestDataReader reader;
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

    @Test
    public void testNonExistingItemMessageShow() {
        final String searchTerm = reader.getStringData("testdata.test.searchnonexisting.search.term");
        final String expectedMessage = reader.getStringData("testdata.test.searchnonexisting.message.expected");
        final String unexpectedMessage = reader.getStringData("testdata.test.searchnonexisting.message.unexpected");

        String evaluatedTitle = new EbayEnglishHomePage(driver)
                .openPage()
                .searchForTerm(searchTerm)
                .searchResultsTitle();

        Assert.assertTrue(evaluatedTitle.contains(expectedMessage));
        Assert.assertFalse(evaluatedTitle.contains(unexpectedMessage));
    }

    @AfterMethod
    public void tearDown() {
        driverFactory.closeDriver();
    }
}
