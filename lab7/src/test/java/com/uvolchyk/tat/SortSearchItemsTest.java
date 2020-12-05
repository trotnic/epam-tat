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

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortSearchItemsTest {

    WebDriver driver;

    private final Integer timeOutDuration = 10;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ebay.com");

        WebElement languageButton = clickableElement(By.xpath("//a[@id='gh-eb-Geo-a-default']"));
        if (!languageButton.getText().toLowerCase().contains("english")) {
            languageButton
                    .click();

            driver.findElement(By.xpath("//a[@id='gh-eb-Geo-a-en']"))
                    .click();
        }
    }

    @Test
    public void testItemsInSearchListAreSortedByMaxPrice() {
        visibleElementBy(By.xpath("//*[@id='gh-ac-box2']/input[@type='text']"))
                .sendKeys("comics");
        clickableElement(By.xpath("//*[@id='gh-btn']"))
                .click();
        clickableElement(By.xpath("//button[@aria-label='Sort selector. Best Match selected.']"))
                .click();
        clickableElement(By.xpath("//a[contains(., 'Price + Shipping: highest first')]"))
                .click();

        List<Integer> sortedByMaxPriceItems =
                visibleElements(By.xpath("//*[@id=\"srp-river-results\"]/ul/li")).stream()
                .map(item -> item.findElement(By.xpath("//span[@class='s-item__price']"))
                        .getText().replaceAll("[^0-9]",""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Assert.assertTrue(IntStream.range(0, sortedByMaxPriceItems.size() - 1)
                .noneMatch(i -> sortedByMaxPriceItems.get(i) > sortedByMaxPriceItems.get(i + 1)));
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

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
