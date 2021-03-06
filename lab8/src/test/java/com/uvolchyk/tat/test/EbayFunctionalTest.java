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

    private static final String RESOURCE_PATH = "src/test/resources/";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
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
        final Double lowerBound = 0.0;
        final Double upperBound = 5.0;

        List<SearchResultItem> searchResultItems = new EbayEnglishHomePage(driver)
                .openPage()
                .searchForTerm("pie")
                .setPriceBounds(lowerBound, upperBound)
                .filterByPrice()
                .searchResultItems();

        Assert.assertTrue(searchResultItems.stream()
                .noneMatch(item -> item.getActualPrice() < lowerBound && item.getActualPrice() > upperBound));
    }

    @Test
    public void testItemsInSearchListAreSortedByMaxPrice() {
        final int inaccuracy = 10;

        List<SearchResultItem> sortedResultItems = new EbayEnglishHomePage(driver)
                .openPage()
                .searchForTerm("comics")
                .sortResultsBy(SortType.PRICE_HIGHEST_FIRST)
                .searchResultItems();

        Assert.assertTrue(IntStream.range(0, sortedResultItems.size() - 1)
                .noneMatch(i -> {
                    int currentPrice = (int) (sortedResultItems.get(i).getActualPrice() / inaccuracy) + 1;
                    int nextPrice = (int) (sortedResultItems.get(i + 1).getActualPrice() / inaccuracy);
                    return currentPrice < nextPrice;
                }));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
