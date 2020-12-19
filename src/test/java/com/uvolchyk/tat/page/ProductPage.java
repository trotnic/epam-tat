package com.uvolchyk.tat.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractPage {

    private String url;

    @FindBy(id = "binBtn_btn")
    private WebElement buyItNowButton;

    @FindBy(xpath = "//*[@id='mainContent']//*[@id='atcWrapperId']//a")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductPage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
        PageFactory.initElements(driver, this);
    }

    @Override
    public ProductPage openPage() {
        driver.navigate().to(url);
        logger.info("Open product page - " + url);
        return this;
    }

    public ProductPage addToCart() {
        addToCartButton.click();
        logger.info("Added the item to shopping cart");
        return this;
    }

    public ShoppingCartPage goToCart() {
        logger.info("Shopping cart opened");
        return new ShoppingCartPage(driver).openPage();
    }

}
