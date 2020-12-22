package com.uvolchyk.tat.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvancedSearchPage extends AbstractPage {

    private final String PAGE_URL = "https://www.ebay.com/sch/ebayadvsearch";

    @FindBy(id = "_nkw")
    private WebElement keywordInputField;

    @FindBy(xpath = "//*[@id='adv_search_from']//div[@class='adv-s mb space-top']/button")
    private WebElement submitSearchButton;

    @FindBy(name = "_udlo")
    private WebElement lowBoundPriceInputField;

    @FindBy(name = "_udhi")
    private WebElement highBoundPriceInputField;

    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public AdvancedSearchPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public AdvancedSearchPage inputKeyword(String key) {
        keywordInputField.sendKeys(key);
        return this;
    }

    public AdvancedSearchPage inputLowPrice(Double price) {
        lowBoundPriceInputField.sendKeys(price.toString());
        return this;
    }

    public AdvancedSearchPage inputHighPrice(Double price) {
        highBoundPriceInputField.sendKeys(price.toString());
        return this;
    }

    public SearchResultsListPage search() {
        String keyword = keywordInputField.getText();
        submitSearchButton.click();
        return new SearchResultsListPage(driver, keyword);
    }

}
