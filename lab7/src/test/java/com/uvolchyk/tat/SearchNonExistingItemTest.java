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

public class SearchNonExistingItemTest {

    private final Integer timeOutDuration = 10;
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ebay.com");

        WebElement languageButton = clickableElement(By.xpath("//a[@id='gh-eb-Geo-a-default']"));
        if (!languageButton.getText().toLowerCase().contains("english")) {
            languageButton
                    .click();

            driver.findElement(By.xpath("//a[@id=\"gh-eb-Geo-a-en\"]"))
                    .click();
        }
    }

    @Test
    public void testNonExistingItemMessageShow() {
        visibleElementBy(By.xpath("//*[@id='gh-ac-box2']/input[@type='text']"))
                .sendKeys("dfsfhjdkalfkdnglf#$!@)(_!");
        clickableElement(By.xpath("//*[@id='gh-btn']"))
                .click();
        WebElement resultBox = visibleElementBy(By.xpath("//*[@id=\"srp-river-results\"]"));
        Assert.assertTrue(resultBox.getText().contains("No exact matches found"));
    }

    private WebElement clickableElement(By locator) {
        return new WebDriverWait(driver, timeOutDuration)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement visibleElementBy(By locator) {
        return new WebDriverWait(driver, timeOutDuration)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
