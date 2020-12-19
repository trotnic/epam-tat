package com.uvolchyk.tat.page;

import com.uvolchyk.tat.type.CountryType;
import com.uvolchyk.tat.wait.CustomConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class HeaderPage extends AbstractPage {

    protected String url;

    @FindBy(css = "#gh-shipto-click > div > button")
    private WebElement shipToButton;

    @FindBy(xpath = "//*[@id='gh-shipto-click-modal']//*[@class='menu-button']//button")
    private WebElement menuCountriesListButton;

    @FindAll(@FindBy(xpath = "//*[@id='gh-shipto-click-modal']//*[@class='menu-button__items']"))
    private WebElement countriesList;

    private final By shipToButtonLocator = By.cssSelector("#gh-shipto-click > div > button");
    private final By menuCountriesListButtonLocator = By.xpath("//*[@id='gh-shipto-click-modal']//*[@class='menu-button']//button");
    private final By menuCountriesModalCloseButtonLocator = By.className("shipto__close-btn");
    private final By selectedCountryTagLocator = By.xpath("//*[@aria-checked='true']/span/span[1]");

    public HeaderPage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    public HeaderPage openCountrySwitchModal() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(CustomConditions.waitForLoad());
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(shipToButton))
                .click();
        return this;
    }

    public HeaderPage switchCountry(CountryType country) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(menuCountriesListButtonLocator))
                .click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", countryElementFor(country));
        countryElementFor(country).click();
        return this;
    }

    public CountryType getCurrentCountry() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(menuCountriesListButtonLocator));
        String country = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(selectedCountryTagLocator))
                .getAttribute("data-country");
        return Arrays.stream(CountryType.values())
                .filter(item -> item.getTag().equals(country))
                .findFirst().orElse(CountryType.RUSSIA);
    }

    private WebElement countryElementFor(CountryType country) {
        return countriesList.findElement(buildLocatorForCountry(country));
    }

    private By buildLocatorForCountry(CountryType countryType) {
        return By.xpath(String.format("//*[@data-country='%s']", countryType.getTag()));
    }

    @Override
    public HeaderPage openPage() {
        driver.get(url);
        return this;
    }
}
