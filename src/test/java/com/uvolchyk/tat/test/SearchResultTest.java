package com.uvolchyk.tat.test;

import com.uvolchyk.tat.entity.ProductItem;
import com.uvolchyk.tat.page.EbayHomePage;
import com.uvolchyk.tat.type.SortType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

public class SearchResultTest extends CommonConditions {

    @Test
    public void testSearchResultItemsAreFiltered() {
        final Double lowerBound = 0.0;
        final Double upperBound = 5.0;
        final String searchTerm = "pie";

        boolean itemsAreFiltered = new EbayHomePage(driver)
                .openPage()
                .searchForTerm(searchTerm)
                .setPriceBounds(lowerBound, upperBound)
                .filterByPrice()
                .itemsAreFilteredByPrice(lowerBound, upperBound);

        Assert.assertTrue(itemsAreFiltered);
    }

    @Test
    public void testItemsInSearchListAreSortedByMaxPrice() {
        final String searchTerm = "comics";
        final Double inaccuracy = 10.0;

        List<ProductItem> items = new EbayHomePage(driver)
                .openPage()
                .searchForTerm(searchTerm)
                .sortResultsBy(SortType.PRICE_HIGHEST_FIRST)
                .getSearchResultItems();

        boolean itemsAreSorted = IntStream.range(0, items.size() - 1)
                .noneMatch(i -> {
                    int currentPrice = (int) (items.get(i).getActualPrice() / inaccuracy) + 1;
                    int nextPrice = (int) (items.get(i + 1).getActualPrice() / inaccuracy);
                    return currentPrice < nextPrice;
                });

        Assert.assertTrue(itemsAreSorted);
    }

    @Test
    public void testNonExistingItemMessageShow() {
        boolean resultsListIsEmpty = new EbayHomePage(driver)
                .openPage()
                .searchForTerm("dfsfhjdkalfkdnglf#$!@)(_!")
                .getSearchResultItems()
                .isEmpty();

        Assert.assertTrue(resultsListIsEmpty);
    }
}
