package com.uvolchyk.tat.test;

import com.uvolchyk.tat.page.EbayEnglishHomePage;
import com.uvolchyk.tat.service.TestDataReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EbayFunctionalTest {

    private WebDriver driver;
    private TestDataReader reader;

    @BeforeMethod
    public void setUp() {
        reader = new TestDataReader();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
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
        driver.quit();
        driver = null;
    }
}
