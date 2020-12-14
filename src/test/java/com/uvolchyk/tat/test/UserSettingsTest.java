package com.uvolchyk.tat.test;

import com.uvolchyk.tat.model.CountryType;
import com.uvolchyk.tat.page.EbayHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSettingsTest extends DefaultTestConfiguration {

    @Test
    public void testCountryIsChanged() {
        Assert.assertEquals(new EbayHomePage(driver)
                .openPage()
                .openCountrySwitchModal()
                .switchCountry(CountryType.BELGIUM)
                .currentCountry(), CountryType.BELGIUM);
    }

}
