package com.uvolchyk.tat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EbayFunctionalTest {

    private final Integer timeOutDuration = 10;
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://ebay.com");

        WebElement languageButton = clickableElement(By.xpath("//a[@id='gh-eb-Geo-a-default']"));
        if (!languageButton.getText().toLowerCase().contains("english")) {
            languageButton.click();
            driver.findElement(By.xpath("//a[@id='gh-eb-Geo-a-en']"))
                    .click();
        }
    }

    @Test
    public void testItemsInSearchListAreSortedByMaxPrice() {
        visibleElement(By.xpath("//*[@id='gh-ac-box2']/input[@type='text']"))
                .sendKeys("comics");
        clickableElement(By.xpath("//*[@id='gh-btn']"))
                .click();
        clickableElement(By.xpath("//button[@aria-label='Sort selector. Best Match selected.']"))
                .click();
        clickableElement(By.xpath("//a[contains(., 'Price + Shipping: highest first')]"))
                .click();

        List<Integer> sortedByMaxPriceItems = visibleElements(By.xpath("//*[@id='srp-river-results']/ul/li")).stream()
                .map(item -> item.findElement(By.xpath("//span[@class='s-item__price']"))
                        .getText().replaceAll("[^0-9]", ""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Assert.assertTrue(IntStream.range(0, sortedByMaxPriceItems.size() - 1)
                .noneMatch(i -> sortedByMaxPriceItems.get(i) > sortedByMaxPriceItems.get(i + 1)));
    }

    @Test
    public void testSearchResultItemsAreFiltered() {
        final Double lowerBound = 0.0;
        final Double upperBound = 5.0;

        visibleElement(By.xpath("//*[@id='gh-ac-box2']/input[@type='text']"))
                .sendKeys("pie");
        clickableElement(By.xpath("//*[@id='gh-btn']"))
                .click();

        visibleElement(By.xpath("//input[@aria-label='Minimum Value in $']"))
                .sendKeys("0");
        visibleElement(By.xpath("//input[@aria-label='Maximum Value in $']"))
                .sendKeys("5");
        clickableElement(By.xpath("//button[@aria-label='Submit price range']"))
                .click();

        List<Double> filteredByPriceItems =
                visibleElements(By.xpath("//*[@id='srp-river-results']/ul//span[@class='s-item__price']")).stream()
                        .map(item -> item.getText().split("to"))
                        .map(i ->
                                Arrays.stream(i)
                                        .map(price -> price.trim().replaceAll("\\$", ""))
                                        .map(Double::valueOf)
                                        .min(Double::compareTo)
                                        .orElse(lowerBound)
                        )
                        .collect(Collectors.toList());

        Assert.assertTrue(filteredByPriceItems.stream()
                .noneMatch(price -> price > upperBound && price < lowerBound));
    }

    @Test
    public void testNonExistingItemMessageShow() {
        visibleElement(By.xpath("//*[@id='gh-ac-box2']/input[@type='text']"))
                .sendKeys("dfsfhjdkalfkdnglf#$!@)(_!");
        clickableElement(By.xpath("//*[@id='gh-btn']"))
                .click();
        WebElement resultBox = visibleElement(By.xpath("//*[@id='srp-river-results']"));
        Assert.assertTrue(resultBox.getText().contains("No exact matches found"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    private WebElement clickableElement(By locator) {
        return new WebDriverWait(driver, timeOutDuration)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement visibleElement(By locator) {
        return new WebDriverWait(driver, timeOutDuration)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private List<WebElement> visibleElements(By locator) {
        return new WebDriverWait(driver, timeOutDuration)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
