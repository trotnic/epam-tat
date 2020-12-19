package com.uvolchyk.tat.page;

import com.uvolchyk.tat.type.CountryType;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage extends AbstractPage {

    protected String url;

    @FindBy(xpath = "//*[@id='gh-top']//button[@aria-controls='gh-shipto-click-o']")
    private WebElement shipToButton;

    @FindBy(xpath = "//*[@id='gh-shipto-click-modal']//*[@class='menu-button']//button")
    private WebElement menuCountriesListButton;

    @FindAll(@FindBy(xpath = "//*[@id='gh-shipto-click-modal']//*[@class='menu-button__items']"))
    private WebElement countriesList;

    private final By menuCountriesListButtonLocator = By.xpath("//*[@id='gh-shipto-click-modal']//*[@class='menu-button']//button");
    private final By menuCountriesModalCloseButtonLocator = By.className("shipto__close-btn");

    public HeaderPage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    public HeaderPage openCountrySwitchModal() {
        shipToButton.click();
        return this;
    }

    public HeaderPage switchCountry(CountryType country) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(menuCountriesListButtonLocator))
                .click();
        countryElementFor(country).click();
        return this;
    }

    public HeaderPage closeCountrySwitchModal() {
        driver.findElement(menuCountriesModalCloseButtonLocator).click();
        return this;
    }

    private WebElement countryElementFor(CountryType country) {
        return countriesList.findElement(buildLocatorForCountry(country));
    }

    public Boolean isCountrySelected(CountryType country) {
        By productLocator = By.xpath(String.format("//*[@class='menu-button']//span[@data-country='%s']", getStringForCountry(country)));
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(productLocator));
        } catch (TimeoutException e) {
            logger.info("No such country selected - " + country);
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

    private By buildLocatorForCountry(CountryType countryType) {
        return By.xpath(String.format("//*[@data-country='%s']",getStringForCountry(countryType)));
    }

    @Override
    public HeaderPage openPage() {
        driver.get(url);
        return this;
    }
}
