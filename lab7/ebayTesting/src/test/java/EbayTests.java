import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EbayTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://ebay.com");
        wait = new WebDriverWait(driver, 10);

        WebElement languageButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id=\"gh-eb-Geo-a-default\"]")));
        if (!languageButton.getText().toLowerCase().contains("english")) {
            languageButton
                    .click();

            driver.findElement(By.xpath("//a[@id=\"gh-eb-Geo-a-en\"]"))
                    .click();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
        wait = null;
    }

    @Test
    public void testResultsAreCorrectForANonExistingItem() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='gh-ac-box2']/input[@type='text']")))
                .sendKeys("dfsfhjdkalfkdnglf#$!@)(_!");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gh-btn']")))
                .click();

        WebElement resultBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"srp-river-results\"]")));
        Assert.assertTrue(resultBox.getText().contains("No exact matches found"));
    }

    @Test
    public void testItemIsTheShoppingCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='gh-ac-box2']/input[@type='text']")))
                .sendKeys("lego model");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='gh-btn']")))
                .click();

        WebElement firstItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"srp-river-results\"]/ul/li[1]")));

        WebElement firstItemTitle = firstItem.findElement(By.xpath(".//a[@class=\"s-item__link\"]"));
        firstItemTitle.click();

        WebElement addToCartButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id=\"isCartBtn_btn\"]")));
        addToCartButton.click();

        WebElement itemInCart = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"mainContent\"]//div[@class=\"cart-bucket-lineitem\"]"))).get(0);

        Assert.assertTrue(itemInCart.getText().contains(firstItemTitle.getText()));

    }

}
