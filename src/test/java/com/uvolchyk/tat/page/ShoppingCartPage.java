package com.uvolchyk.tat.page;

import com.uvolchyk.tat.entity.SearchResultItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShoppingCartPage extends AbstractPage {

    @FindAll(@FindBy(xpath = "//*[@data-test-id='app-cart'//*[@class='grid__group details']"))
    private List<WebElement> itemsInCart;

    protected ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }

    public List<SearchResultItem> getItemsInCart() {
        logger.info("Shopping cart " + driver.getCurrentUrl());
        return itemsInCart.stream().map(item -> {
            String title = item.findElement(By.xpath("//a[@data-test-id='cart-item-link']")).getText();
            String priceMetaData = item.findElement(By.className("price-details")).getText();
            List<Double> price = new ArrayList<>();
//            Pattern pattern = Pattern.compile("(?<=\\$)\\d+[.,]\\d+");
//            Matcher matcher = pattern.matcher(priceMetaData);
//            if (matcher.find()) {
//                price.add(Double.valueOf(matcher.group().replaceAll(",", ".")));
//            }
            return new SearchResultItem(title, price);
        }).collect(Collectors.toList());
    }
}
