package com.uvolchyk.tat.test;

import com.uvolchyk.tat.page.EbayHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EbayFunctionalTest extends DefaultTestConfiguration {

    @Test
    public void testNonExistingItemMessageShow() {
        final String searchTerm = reader.getStringData("testdata.test.searchnonexisting.search.term");
        final String expectedMessage = reader.getStringData("testdata.test.searchnonexisting.message.expected");
        final String unexpectedMessage = reader.getStringData("testdata.test.searchnonexisting.message.unexpected");

        String evaluatedTitle = new EbayHomePage(driver)
                .openPage()
                .searchForTerm(searchTerm)
                .searchResultsTitle();

        Assert.assertTrue(evaluatedTitle.contains(expectedMessage));
        Assert.assertFalse(evaluatedTitle.contains(unexpectedMessage));
    }

}
