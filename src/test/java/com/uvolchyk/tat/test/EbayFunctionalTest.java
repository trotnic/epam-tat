package com.uvolchyk.tat.test;

import com.uvolchyk.tat.entity.SearchResultItem;
import com.uvolchyk.tat.model.SortType;
import com.uvolchyk.tat.page.EbayEnglishHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

public class EbayFunctionalTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
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
        System.out.println("The thread ID for testNonExistingItemMessageShow is "+ Thread.currentThread().getId());
        String evaluatedTitle = new EbayEnglishHomePage(driver)
                .openPage()
                .searchForTerm("dfsfhjdkalfkdnglf#$!@)(_!")
                .searchResultsTitle();
        String expectedContainment = "No exact matches found";
        Assert.assertTrue(evaluatedTitle.contains(expectedContainment));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
