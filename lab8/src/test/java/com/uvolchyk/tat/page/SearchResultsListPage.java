package com.uvolchyk.tat.page;

import com.uvolchyk.tat.entity.SearchResultItem;
import com.uvolchyk.tat.model.SortType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsListPage extends AbstractPage {

    private String searchTerm;

    @FindAll(@FindBy(xpath = "//div[@id=\"srp-river-results\"]//li[@class=\"s-item\"]"))
    private List<WebElement> searchResultsList;

    @FindBy(xpath = "//div[@id='srp-river-main']//div[@class='srp-save-null-search__title']")
    private WebElement searchResultsTitle;

    @FindAll(@FindBy(xpath = "//*[@id='srp-river-results']//li[@class='s-item    ']"))
    private List<WebElement> searchResults;

    @FindBy(xpath = "//input[@aria-label='Minimum Value in $']")
    private WebElement lowerPriceInputField;

    @FindBy(xpath = "//input[@aria-label='Maximum Value in $']")
    private WebElement upperPriceInputField;

    @FindBy(xpath = "//button[@aria-label='Submit price range']")
    private WebElement filterByPriceButton;

    @FindBy(xpath = "//div[@id='mainContent']//div[@class=' srp-controls__row-cells right clearfix']//button")
    private WebElement sortButton;

    public SearchResultsListPage(WebDriver driver, String term) {
        super(driver);
        this.searchTerm = term;
    }

    @Override
    protected AbstractPage openPage() {
        throw new RuntimeException("lolkek cheburek");
    }

    public String searchResultsTitle() {
        return searchResultsTitle.getText();
    }

    public List<SearchResultItem> searchResultItems() {
        return searchResults.stream().map(item -> {
                    String title = item.findElement(By.className("s-item__title")).getText();
                    List<Double> priceBounds = Arrays.stream(item.findElement(By.className("s-item__price"))
                            .getText().split("to"))
                            .map(price -> price.trim().replaceAll("[$,]", ""))
                            .map(Double::valueOf)
                            .collect(Collectors.toList());
                    return new SearchResultItem(title, priceBounds);
                })
                .collect(Collectors.toList());
    }

    public SearchResultsListPage setPriceBounds(Double lowerBound, Double upperBound) {
        lowerPriceInputField.sendKeys(lowerBound.toString());
        upperPriceInputField.sendKeys(upperBound.toString());
        return this;
    }

    public SearchResultsListPage filterByPrice() {
        filterByPriceButton.click();
        return this;
    }

    public SearchResultsListPage sortResultsBy(SortType type) {
        sortButton.click();
        switch (type) {
            case BEST_MATCH:
                clickSortButtonWithText("Best Match");
            case TIME_NEWEST:
                clickSortButtonWithText("Time: newly listed");
            case TIME_OLDEST:
                clickSortButtonWithText("Time: ending soonest");
            case DISTANCE_NEAREST:
                clickSortButtonWithText("Distance: nearest first");
            case PRICE_LOWEST_FIRST:
                clickSortButtonWithText("Price + Shipping: lowest first");
            case PRICE_HIGHEST_FIRST:
                clickSortButtonWithText("Price + Shipping: highest first");
        }
        return this;
    }

    public String firstTitle() {
        System.out.println(searchResultsList);
        return "";
    }

    private void clickSortButtonWithText(String text) {
        String path = String.format("//span[@class='fake-menu-button srp-controls__control']//a[contains(., '%s')]", text);
        clickableElement(By.xpath(path))
                .click();
    }

    private WebElement clickableElement(By locator) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
