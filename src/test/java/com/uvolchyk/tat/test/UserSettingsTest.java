package com.uvolchyk.tat.test;

import com.uvolchyk.tat.page.EbayHomePage;
import com.uvolchyk.tat.page.HeaderPage;
import com.uvolchyk.tat.type.CountryType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSettingsTest extends CommonConditions {

    @Test
    public void testCountryIsChanged() {
        HeaderPage page = new EbayHomePage(driver)
                .openPage()
                .openCountrySwitchModal()
                .switchCountry(CountryType.BELARUS);

        CountryType currentCountry = page.getCurrentCountry();

        page.switchCountry(CountryType.BELGIUM);

        Assert.assertNotEquals(currentCountry, page.getCurrentCountry());
    }

}
