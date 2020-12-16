package com.uvolchyk.tat.page;

import com.uvolchyk.tat.entity.ProductItem;
import com.uvolchyk.tat.model.SortType;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchResultsListPage extends AbstractPage {

    private final String searchTerm;

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

    @FindBy(xpath = "//div[@id='mainContent']//div[@class=' srp-controls__row-cells right clearfix']//button")
    private WebElement sortButton;

    public SearchResultsListPage(WebDriver driver, String term) {
        super(driver);
        this.searchTerm = term;
    }

    @Override
    protected AbstractPage openPage() {
        throw new RuntimeException("You shouldn't open this page as is, only pass the driver and the relevant search term");
    }

    public Boolean containsTitle(String title) {
        By productLocator = By.xpath(String.format("//*[contains(., '%s')]", title));
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(productLocator));
        } catch (TimeoutException e) {
            logger.info("No containment of - " + title);
            return false;
        }
        logger.info("Page contains - " + title);
        return true;
    }

    public Boolean searchResultItemsAreSortedBy(SortType type) {
        switch (type) {
            case PRICE_HIGHEST_FIRST:
                List<ProductItem> items = getSearchResultItems();
                return IntStream.range(0, items.size() - 1)
                        .noneMatch(i -> {
                            int currentPrice = (int) (items.get(i).getActualPrice() / 10.0) + 1;
                            int nextPrice = (int) (items.get(i + 1).getActualPrice() / 10.0);
                            return currentPrice < nextPrice;
                        });
            default:
                return true;
        }
    }

    public Boolean itemsAreFilteredByPrice(Double lowerBound, Double upperBound) {
        return getSearchResultItems().stream()
                .noneMatch(item -> item.getActualPrice() < lowerBound && item.getActualPrice() > upperBound);
    }

    public List<ProductItem> getSearchResultItems() {
        return searchResults.stream().map(item -> {
            String title = item.findElement(By.className("s-item__title")).getText();
            List<Double> priceBounds = Arrays.stream(item.findElement(By.className("s-item__price"))
                    .getText().split("[^\\d.,]"))
                    .filter(price -> !price.isEmpty())
                    .map(price -> price.trim().replaceAll("(?<=\\d)[,](?=\\d)", ".").replaceAll("[^\\d.]", ""))
                    .map(Double::valueOf)
                    .collect(Collectors.toList());
            return new ProductItem(title, priceBounds);
        }).collect(Collectors.toList());
    }

    public SearchResultsListPage setPriceBounds(Double lowerBound, Double upperBound) {
        lowerPriceInputField.sendKeys(lowerBound.toString());
        upperPriceInputField.sendKeys(upperBound.toString());
        return this;
    }

    public SearchResultsListPage filterByPrice() {
        filterByPriceButton.click();
        logger.info("Results are filtered by price");
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
        logger.info("Results are sorted by: " + type);
        return this;
    }

    public ProductPage goToSingleItemWithTitle(String title) {
        searchResults.stream()
                .filter(item -> item.findElement(By.className("s-item__title")).getText().equals(title))
                .findFirst().get().findElement(By.className("s-item__link")).click();
        return new ProductPage(driver);
    }

    public ProductPage goToSingleItemAtPosition(Integer position) {
        searchResults.get(position).findElement(By.className("s-item__link")).click();
        return new ProductPage(driver);
    }

    public ProductPage goToSingleItem(ProductItem item) {
        return goToSingleItemWithTitle(item.getTitle());
    }

    private void clickSortButtonWithText(String text) {
        String path = String.format("//span[@class='fake-menu-button srp-controls__control']//a[contains(., '%s')]", text);
        clickableElement(By.xpath(path))
                .click();
    }

    private WebElement clickableElement(By locator) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
