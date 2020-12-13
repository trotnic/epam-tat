package com.uvolchyk.tat.test;

import com.uvolchyk.tat.driver.DriverFactory;
import com.uvolchyk.tat.entity.SearchResultItem;
import com.uvolchyk.tat.model.SortType;
import com.uvolchyk.tat.page.EbayEnglishHomePage;
import com.uvolchyk.tat.service.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.stream.IntStream;

public class SearchResultTest {

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
    public void testSearchResultItemsAreFiltered() {
        final Double lowerBound = reader.getDoubleData("testdata.test.filterwithprice.price.bound.lower");
        final Double upperBound = reader.getDoubleData("testdata.test.filterwithprice.price.bound.upper");
        final String searchTerm = reader.getStringData("testdata.test.filterwithprice.search.term");

        List<SearchResultItem> searchResultItems = new EbayEnglishHomePage(driver)
                .openPage()
                .searchForTerm(searchTerm)
                .setPriceBounds(lowerBound, upperBound)
                .filterByPrice()
                .searchResultItems();

        boolean itemsAreFiltered = searchResultItems.stream()
                .noneMatch(item -> item.getActualPrice() < lowerBound && item.getActualPrice() > upperBound);

        Assert.assertTrue(itemsAreFiltered);
    }

    @Test
    public void testItemsInSearchListAreSortedByMaxPrice() {
        final Double inaccuracy = reader.getDoubleData("testdata.test.sortbymaxprice.inaccuracy");
        final String searchTerm = reader.getStringData("testdata.test.sortbymaxprice.search.term");

        List<SearchResultItem> sortedResultItems = new EbayEnglishHomePage(driver)
                .openPage()
                .searchForTerm(searchTerm)
                .sortResultsBy(SortType.PRICE_HIGHEST_FIRST)
                .searchResultItems();

        boolean itemsAreSorted = IntStream.range(0, sortedResultItems.size() - 1)
                .noneMatch(i -> {
                    int currentPrice = (int) (sortedResultItems.get(i).getActualPrice() / inaccuracy) + 1;
                    int nextPrice = (int) (sortedResultItems.get(i + 1).getActualPrice() / inaccuracy);
                    return currentPrice < nextPrice;
                });

        Assert.assertTrue(itemsAreSorted);
    }

    @AfterMethod
    public void tearDown() {
        driverFactory.closeDriver();
    }
}