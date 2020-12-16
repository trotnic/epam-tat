package com.uvolchyk.tat.test;

import com.uvolchyk.tat.page.EbayHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorMessagesTest extends CommonConditions {

    @Test
    public void testNonExistingItemMessageShow() {
        boolean resultsListIsEmpty = new EbayHomePage(driver)
                .openPage()
                .searchForTerm("dfsfhjdkalfkdnglf#$!@)(_!")
                .containsTitle("No exact matches found");

        Assert.assertTrue(resultsListIsEmpty);
    }

}
