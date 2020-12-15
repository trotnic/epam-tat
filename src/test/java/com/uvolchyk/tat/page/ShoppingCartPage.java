package com.uvolchyk.tat.page;

import com.uvolchyk.tat.entity.ProductItem;
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

    @FindAll(@FindBy(xpath = "//*[@data-test-id='app-cart'//*[@class='grid__group details']"))
    private List<WebElement> itemsInCart;

    protected ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ShoppingCartPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public List<ProductItem> getItemsInCart() {
        logger.info("Shopping cart " + driver.getCurrentUrl());
        return itemsInCart.stream().map(item -> {
            String title = item.findElement(By.xpath("//a[@data-test-id='cart-item-link']")).getText();
            String priceMetaData = item.findElement(By.className("price-details")).getText();
            List<Double> price = new ArrayList<>();
            Pattern pattern = Pattern.compile("(?<=\\$)\\d+[.,]\\d+");
            Matcher matcher = pattern.matcher(priceMetaData);
            if (matcher.find()) {
                price.add(Double.valueOf(matcher.group().replaceAll(",", ".")));
            }
            return new ProductItem(title, price);
        }).collect(Collectors.toList());
    }

    public Boolean isProductOnCart(String productName) {
        By productLocator = By.xpath(String.format("//*[contains(., '%s')]", productName));
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(productLocator));
        } catch (TimeoutException e) {
            logger.info("Product not found");
            return false;
        }
        logger.info("Added the product - " + productName);
        return true;
    }
}
