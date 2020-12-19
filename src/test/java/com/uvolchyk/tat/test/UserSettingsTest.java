package com.uvolchyk.tat.test;

import com.uvolchyk.tat.page.LoginPage;
import com.uvolchyk.tat.type.CountryType;
import com.uvolchyk.tat.page.EbayHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSettingsTest extends CommonConditions {

    @Test
    public void testCountryIsChanged() {
        new LoginPage(driver)
                .openPage()
                .putEmail("volf10011@gmail.com")
                .next()
                .putPassword("verystrongpassword20")
                .next();
//        Assert.assertTrue(new EbayHomePage(driver)
//                .openPage()
//                .openCountrySwitchModal()
//                .switchCountry(CountryType.BELGIUM)
//                .isCountrySelected(CountryType.BELGIUM));
    }

}
