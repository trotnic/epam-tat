package com.uvolchyk.tat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterSearchResultTest {

    private final Double lowerBound = 0.0;
    private final Double upperBound = 5.0;

    private final Integer timeOutDuration = 10;
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
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
    public void testSearchResultItemsAreFiltered() {
        visibleElementBy(By.xpath("//*[@id='gh-ac-box2']/input[@type='text']"))
                .sendKeys("pie");
        clickableElement(By.xpath("//*[@id='gh-btn']"))
                .click();

        visibleElementBy(By.xpath("//input[@aria-label='Minimum Value in $']"))
                .sendKeys("0");
        visibleElementBy(By.xpath("//input[@aria-label='Maximum Value in $']"))
                .sendKeys("5");
        clickableElement(By.xpath("//button[@aria-label='Submit price range']"))
                .click();

        List<Double> filteredByPriceItems =
                visibleElements(By.xpath("//*[@id=\"srp-river-results\"]/ul//span[@class='s-item__price']")).stream()
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

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    private WebElement clickableElement(By locator) {
        return new WebDriverWait(driver, timeOutDuration)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement visibleElementBy(By locator) {
        return new WebDriverWait(driver, timeOutDuration)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private List<WebElement> visibleElements(By locator) {
        return new WebDriverWait(driver, timeOutDuration)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
