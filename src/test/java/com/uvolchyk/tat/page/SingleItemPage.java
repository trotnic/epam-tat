package com.uvolchyk.tat.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingleItemPage extends AbstractPage {

    @FindBy(id = "binBtn_btn")
    private WebElement buyItNowButton;

    @FindBy(id = "isCartBtn_btn")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[@id='gh-minicart-hover']//a")
    private WebElement shoppingCartButton;

    protected SingleItemPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        throw new RuntimeException("You shouldn't open this page as is");
    }

    public SingleItemPage addToCart() {
        addToCartButton.click();
        logger.info("Added the item to shopping cart");
        return this;
    }

    public ShoppingCartPage goToCart() {
        shoppingCartButton.click();
        logger.info("Shopping cart opened");
        return new ShoppingCartPage(driver);
    }

}
