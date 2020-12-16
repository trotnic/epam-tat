package com.uvolchyk.tat.page;

import com.uvolchyk.tat.model.CountryType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EbayHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://ebay.com";

    @FindBy(id = "gh-ac")
    private WebElement searchField;

    @FindBy(id = "gh-btn")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='gh-top']//button[@aria-controls='gh-shipto-click-o']")
    private WebElement shipToButton;

    @FindBy(xpath = "//*[@id='gh-shipto-click-modal']//*[@class='menu-button']//button")
    private WebElement menuCountriesListButton;

    @FindAll(@FindBy(xpath = "//*[@id='gh-shipto-click-modal']//*[@class='menu-button__items']"))
    private WebElement countriesList;

    private final By menuCountriesListButtonLocator = By.xpath("//*[@id='gh-shipto-click-modal']//*[@class='menu-button']//button");
    private final By menuCountriesModalCloseButtonLocator = By.className("shipto__close-btn");

    public EbayHomePage(WebDriver driver) {
        super(driver);
    }

    public EbayHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("Opened page: " + driver.getCurrentUrl());
        return this;
    }

    public EbayHomePage openCountrySwitchModal() {
        shipToButton.click();
        return this;
    }

    public EbayHomePage switchCountry(CountryType country) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(menuCountriesListButtonLocator))
                .click();
        contryElementFor(country).click();
        return this;
    }

    public EbayHomePage closeCountrySwitchModal() {
        driver.findElement(menuCountriesModalCloseButtonLocator).click();
        return this;
    }

    private WebElement contryElementFor(CountryType country) {
        switch (country) {
            case RUSSIA:
                return countriesList.findElement(By.xpath(String.format("//*[@data-country='%s']","fad")));
            case BELARUS:
                return countriesList.findElement(By.xpath(String.format("//*[@data-country='%s']","BLR|BY")));
            case BELGIUM:
                return countriesList.findElement(By.xpath(String.format("//*[@data-country='%s']","BEL|BE")));
            default:
                return countriesList.findElement(By.xpath(String.format("//*[@data-makeup-index='%d']",1)));
        }
    }

    public Boolean isCountrySelected(CountryType country) {
        By productLocator = By.xpath(String.format("//*[@class='menu-button']//span[@data-country='%s']", getStringForCountry(country)));
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(productLocator));
        } catch (TimeoutException e) {
            logger.info("No country selected - " + country);
            return false;
        }
        logger.info("Country is selected - " + country);
        return true;
    }

    private String getStringForCountry(CountryType country) {
        switch (country) {
            case BELARUS:
                return "BLR|BY";
            case BELGIUM:
                return "BEL|BE";
            default:
                return "RUS|RU";
        }
    }

    public SearchResultsListPage searchForTerm(String term) {
        searchField.sendKeys(term);
        searchButton.click();
        logger.info("Searching for: " + term);
        return new SearchResultsListPage(driver, term);
    }
}
