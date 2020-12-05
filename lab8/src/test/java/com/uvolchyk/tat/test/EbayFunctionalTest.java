package com.uvolchyk.tat.test;

import com.uvolchyk.tat.page.EbayEnglishHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EbayFunctionalTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testNonExistingItemMessageShow() {
        String evaluatedTitle = new EbayEnglishHomePage(driver)
                .openPage()
                .searchForTerm("dfsfhjdkalfkdnglf#$!@)(_!")
                .searchResultsTitle();
        String expectedContainment = "No exact matches found";
        Assert.assertTrue(evaluatedTitle.contains(expectedContainment));
    }

    @Test
    public void testSearchResultItemsAreFiltered() {
        System.out.println(new EbayEnglishHomePage(driver)
                .openPage()
                .searchForTerm("pie")
                .searchResultItems());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
