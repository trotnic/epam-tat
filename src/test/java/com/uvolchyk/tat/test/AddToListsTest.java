package com.uvolchyk.tat.test;

import com.uvolchyk.tat.entity.ProductItem;
import com.uvolchyk.tat.page.ProductPage;
import com.uvolchyk.tat.service.ProductItemFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AddToListsTest extends CommonConditions {

    ProductItem productItem = ProductItemFactory.productWithDescribedProperties();

    @Test
    public void testItemIsAddedToTheShoppingCart() {

        boolean productIsInACart = new ProductPage(driver, productItem.getUrl())
                .openPage()
                .addToCart()
                .goToCart()
                .getItemsInCart()
                .stream().anyMatch(item -> item.getTitle().toLowerCase().contains(productItem.getTitle().toLowerCase()));

        Assert.assertTrue(productIsInACart);
    }

}

