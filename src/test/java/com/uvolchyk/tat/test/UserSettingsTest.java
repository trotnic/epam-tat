package com.uvolchyk.tat.test;

import com.uvolchyk.tat.model.CountryType;
import com.uvolchyk.tat.page.EbayHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSettingsTest extends CommonConditions {

    @Test
    public void testCountryIsChanged() {
        Assert.assertTrue(new EbayHomePage(driver)
                .openPage()
                .openCountrySwitchModal()
                .switchCountry(CountryType.BELGIUM)
                .isCountrySelected(CountryType.BELGIUM));
    }

}
