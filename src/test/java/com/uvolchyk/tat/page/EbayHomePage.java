package com.uvolchyk.tat.page;

import com.uvolchyk.tat.model.CountryType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EbayHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://ebay.com";

    @FindBy(id = "gh-ac")
    private WebElement searchField;

    @FindBy(id = "gh-btn")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='gh-shipto-click']//*[@aria-controls='gh-shipto-click-o']")
    private WebElement shipToButton;

    @FindBy(xpath = "//*[@id='gh-shipto-click-modal']//*[@class='menu-button']//button")
    private WebElement menuCountriesListButton;

    @FindAll(@FindBy(xpath = "//*[@id='gh-shipto-click-modal']//*[@class='menu-button__item']"))
    private List<WebElement> countriesList;

    public EbayHomePage(WebDriver driver) {
        super(driver);
    }

    public EbayHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("Opened page: " + driver.getCurrentUrl());
        return this;
    }

    public EbayHomePage openCountrySwitchModal() {
        System.out.println(shipToButton);
        shipToButton.click();
        return this;
    }

    public EbayHomePage switchCountry(CountryType country) {
        countriesList.stream()
                .filter(item -> item.getText().toLowerCase().contains(country.toString().toLowerCase()))
                .findFirst().ifPresent(WebElement::click);
        return this;
    }

    public CountryType currentCountry() {
        return CountryType.valueOf(menuCountriesListButton.getText());
    }

    public SearchResultsListPage searchForTerm(String term) {
        searchField.sendKeys(term);
        searchButton.click();
        logger.info("Searching for: " + term);
        return new SearchResultsListPage(driver, term);
    }
}
