package com.uvolchyk.tat.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EbayEnglishHomePage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private static final String HOMEPAGE_URL = "https://ebay.com";

    @FindBy(id = "gh-ac")
    private WebElement searchField;

    @FindBy(id = "gh-btn")
    private WebElement searchButton;

    public EbayEnglishHomePage(WebDriver driver) {
        super(driver);
    }

    public EbayEnglishHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("Opened page: " + driver.getCurrentUrl());
        return this;
    }

    public SearchResultsListPage searchForTerm(String term) {
        searchField.sendKeys(term);
        searchButton.click();
        return new SearchResultsListPage(driver, term);
    }
}
