package com.uvolchyk.tat.test;

import com.uvolchyk.tat.entity.SearchResultItem;
import com.uvolchyk.tat.model.SortType;
import com.uvolchyk.tat.page.EbayHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

public class SearchResultTest extends DefaultTestConfiguration {

    @Test
    public void testSearchResultItemsAreFiltered() {
        final Double lowerBound = reader.getDoubleData("testdata.test.filterwithprice.price.bound.lower");
        final Double upperBound = reader.getDoubleData("testdata.test.filterwithprice.price.bound.upper");
        final String searchTerm = reader.getStringData("testdata.test.filterwithprice.search.term");

        List<SearchResultItem> searchResultItems = new EbayHomePage(driver)
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

        List<SearchResultItem> sortedResultItems = new EbayHomePage(driver)
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
}
