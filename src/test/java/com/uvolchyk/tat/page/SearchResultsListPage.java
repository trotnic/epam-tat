package com.uvolchyk.tat.page;

import com.uvolchyk.tat.entity.ProductItem;
import com.uvolchyk.tat.type.SortType;
import org.openqa.selenium.*;
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

    @FindBy(xpath = "//input[@class='x-textrange__input x-textrange__input--from']")
    private WebElement lowerPriceInputField;

    @FindBy(xpath = "//input[@class='x-textrange__input x-textrange__input--to']")
    private WebElement upperPriceInputField;

    @FindBy(xpath = "//*[@class='x-textrange x-refine__block-button--use-arrow']//button")
    private WebElement filterByPriceButton;

    @FindBy(xpath = "//div[@id='mainContent']//div[@class=' srp-controls__row-cells right clearfix']//button")
    private WebElement sortButton;

    private String sortTypeItemPath = "//*[@class='srp-controls__sort srp-controls__control']//*[@class='fake-menu-button__item']";
    private By productItemTitleLocator = By.className("s-item__title");
    private By productItemLinkLocator = By.className("s-item__link");
    private By productItemPriceLocator = By.className("s-item__price");


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
            String title = item.findElement(productItemTitleLocator).getText();
            List<Double> priceBounds = Arrays.stream(item.findElement(productItemPriceLocator)
                    .getText().split("[^\\d.,]"))
                    .filter(price -> !price.isEmpty())
                    .map(price -> price.trim()
                            .replaceAll("(?<=\\d)[,](?=\\d)", ".")
                            .replaceAll("[^\\d.]", ""))
                    .map(Double::valueOf)
                    .collect(Collectors.toList());
            return new ProductItem(title, priceBounds);
        }).collect(Collectors.toList());
    }

    public SearchResultsListPage setPriceBounds(Double lowerBound, Double upperBound) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", lowerPriceInputField);
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
        clickSortButtonWithText(type.ordinal());
        logger.info("Results are sorted by: " + type);
        return this;
    }

    public ProductPage goToSingleItemWithTitle(String title) {
        searchResults.stream()
                .filter(item -> item.findElement(productItemTitleLocator).getText().equals(title))
                .findFirst().get().findElement(productItemLinkLocator).click();
        return new ProductPage(driver);
    }

    public ProductPage goToSingleItemAtPosition(Integer position) {
        searchResults.get(position).findElement(productItemLinkLocator).click();
        return new ProductPage(driver);
    }

    public ProductPage goToSingleItem(ProductItem item) {
        return goToSingleItemWithTitle(item.getTitle());
    }

    private void clickSortButtonWithText(Integer index) {
        String path = String.format("%s[%d]", sortTypeItemPath, index + 1);
        clickableElement(By.xpath(path))
                .click();
    }

    private WebElement clickableElement(By locator) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
