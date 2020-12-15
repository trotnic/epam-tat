package com.uvolchyk.tat.test;

import com.uvolchyk.tat.entity.ProductItem;
import com.uvolchyk.tat.page.EbayHomePage;
import com.uvolchyk.tat.page.ProductPage;
import com.uvolchyk.tat.page.SearchResultsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class AddToListsTest extends CommonConditions {

    @Test
    public void testItemIsAddedToTheShoppingCart() {
        final String searchTerm = reader.getStringData("testdata.test.itemaddedtocart.search.term");
        final Integer itemPositionToSelect = reader.getIntegerData("testdata.test.itemaddedtocart.search.position");

        Assert.assertTrue(new ProductPage(driver, reader.getStringData("testdata.test.product.url"))
                .openPage()
                .addToCart()
                .goToCart()
                .isProductOnCart(reader.getStringData("testdata.test.product.name")));

//        SearchResultsListPage searchListPage = new EbayHomePage(driver)
//                .openPage()
//                .searchForTerm(searchTerm);
//        System.out.println(searchListPage.getSearchResultItems());
//
//        ProductItem item = searchListPage
//                .getSearchResultItems()
//                .get(itemPositionToSelect);
//
//        List<ProductItem> itemsInCart = searchListPage
//                .goToSingleItem(item)
//                .addToCart()
//                .goToCart()
//                .getItemsInCart();
//
//        System.out.println(itemsInCart);


//        Assert.assertTrue(itemsInCart.stream().anyMatch(itemInCart -> itemInCart.equals(item)));
    }

}

