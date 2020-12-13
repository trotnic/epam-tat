package com.uvolchyk.tat.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EbayHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://ebay.com";

    @FindBy(id = "gh-ac")
    private WebElement searchField;

    @FindBy(id = "gh-btn")
    private WebElement searchButton;

    public EbayHomePage(WebDriver driver) {
        super(driver);
    }

    public EbayHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("Opened page: " + driver.getCurrentUrl());
        return this;
    }

    public SearchResultsListPage searchForTerm(String term) {
        searchField.sendKeys(term);
        searchButton.click();
        logger.info("Searching for: " + term);
        return new SearchResultsListPage(driver, term);
    }
}
