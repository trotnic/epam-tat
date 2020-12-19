package com.uvolchyk.tat.page;

import com.uvolchyk.tat.entity.ProductItem;
import com.uvolchyk.tat.wait.CustomConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShoppingCartPage extends AbstractPage {

    private String PAGE_URL = "https://cart.payments.ebay.com";

    @FindAll(@FindBy(xpath = "//*[@class='listsummary-content-itemdetails']"))
    private List<WebElement> itemsInCart;

    private By productItemTitleLocator = By.xpath("//*[@class='listsummary-content-itemdetails']//h3");

    protected ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ShoppingCartPage openPage() {
        driver.get(PAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.waitForLoad());
        return this;
    }

    public List<ProductItem> getItemsInCart() {
        logger.info("Shopping cart " + driver.getCurrentUrl());
        return itemsInCart.stream().map(item -> {
            String title = item.findElement(productItemTitleLocator).getText();
            List<Double> price = new ArrayList<>();
            logger.info(title);
            return new ProductItem(title, price);
        }).collect(Collectors.toList());
    }
}
