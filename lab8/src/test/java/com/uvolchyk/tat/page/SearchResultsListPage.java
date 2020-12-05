package com.uvolchyk.tat.page;

import com.uvolchyk.tat.entity.SearchResultItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsListPage extends AbstractPage {

    private String searchTerm;

    @FindAll(@FindBy(xpath = "//div[@id='srp-river-results']//li[@class='s-item']"))
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
                            .map(price -> price.trim().replaceAll("\\$", ""))
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

    public String firstTitle() {
        System.out.println(searchResultsList);
        return "";
    }

}
