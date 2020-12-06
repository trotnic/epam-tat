package com.uvolchyk.tat.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EbayEnglishHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://ebay.com";

    @FindBy(xpath = "//;[@id='gh-eb-Geo-a-default']")
    private WebElement currentLanguageField;

    @FindBy(xpath = "//*[@id='gh-eb-Geo-a-en']")
    private WebElement languageSwitchButton;

    @FindBy(xpath = "//*[@id='gh-ac-box2']/input[@type='text']")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='gh-btn']")
    private WebElement searchButton;

    public EbayEnglishHomePage(WebDriver driver) {
        super(driver);
    }

    public EbayEnglishHomePage openPage() {
        driver.get(HOMEPAGE_URL);

//        if (!clickableElement(By.xpath("//span[@class='gh-eb-Geo-flag gh-sprRetina']"))
//                .getText().toLowerCase().contains("english")) {
//            clickableElement(By.xpath("//span[@class='gh-eb-Geo-flag gh-sprRetina']")).click();
//            languageSwitchButton.click();
//        }
        if (!currentLanguageField.getText().toLowerCase().contains("english")) {
            currentLanguageField.click();
            languageSwitchButton.click();
        }
        return this;
    }

    public SearchResultsListPage searchForTerm(String term) {
        searchField.sendKeys(term);
        searchButton.click();
        return new SearchResultsListPage(driver, term);
    }

    private WebElement clickableElement(By locator) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
