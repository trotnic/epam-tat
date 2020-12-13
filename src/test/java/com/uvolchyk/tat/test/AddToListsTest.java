package com.uvolchyk.tat.test;

import com.uvolchyk.tat.entity.SearchResultItem;
import com.uvolchyk.tat.page.EbayHomePage;
import com.uvolchyk.tat.page.SearchResultsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class AddToListsTest extends DefaultTestConfiguration {

    @Test
    public void testItemIsAddedToTheShoppingCart() {
        final String searchTerm = reader.getStringData("testdata.test.itemaddedtocart.search.term");
        final Integer itemPositionToSelect = reader.getIntegerData("testdata.test.itemaddedtocart.search.position");

        SearchResultsListPage searchListPage = new EbayHomePage(driver)
                .openPage()
                .searchForTerm(searchTerm);
        System.out.println(searchListPage.getSearchResultItems());

        SearchResultItem item = searchListPage
                .getSearchResultItems()
                .get(itemPositionToSelect);

        List<SearchResultItem> itemsInCart = searchListPage
                .goToSingleItem(item)
                .addToCart()
                .goToCart()
                .getItemsInCart();

        System.out.println(itemsInCart);


        Assert.assertTrue(itemsInCart.stream().anyMatch(itemInCart -> itemInCart.equals(item)));
    }

}

