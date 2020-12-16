package com.uvolchyk.tat.service;

import com.uvolchyk.tat.entity.ProductItem;

import java.util.ArrayList;

public class ProductItemFactory {
    private static String TESTDATA_TEST_PRODUCT_NAME = "testdata.test.product.name";
    private static String TESTDATA_TEST_PRODUCT_URL = "testdata.test.product.url";

    public static ProductItem productWithDescribedProperties() {
        ProductItem product = new ProductItem(TestDataReader.getStringData(TESTDATA_TEST_PRODUCT_NAME), new ArrayList<>());
        product.setUrl(TestDataReader.getStringData(TESTDATA_TEST_PRODUCT_URL));
        return product;
    }

}
