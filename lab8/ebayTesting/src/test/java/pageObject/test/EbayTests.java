package pageObject.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.page.EbayEnglishHomePage;

public class EbayTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void searchForNonExistingPosition() {
        String evaluatedTitle = new EbayEnglishHomePage(driver)
                .openPage()
                .searchForTerm("dfsfhjdkalfkdnglf#$!@)(_!")
                .searchResultsTitle();
        String expectedContainment = "No exact matches found";
        Assert.assertTrue(evaluatedTitle.contains(expectedContainment));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
