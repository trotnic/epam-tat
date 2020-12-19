package com.uvolchyk.tat.test;

import com.uvolchyk.tat.type.SortType;
import com.uvolchyk.tat.page.EbayHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

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

        boolean itemsAreSorted = new EbayHomePage(driver)
                .openPage()
                .searchForTerm(searchTerm)
                .sortResultsBy(SortType.PRICE_HIGHEST_FIRST)
                .searchResultItemsAreSortedBy(SortType.PRICE_HIGHEST_FIRST);

        Assert.assertTrue(itemsAreSorted);
    }
}
