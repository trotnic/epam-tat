package pageObject.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EbayEnglishHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://ebay.com";

    @FindBy(xpath = "//a[@id='gh-eb-Geo-a-default']")
    private WebElement currentLanguageField;

    @FindBy(xpath = "//a[@id=\"gh-eb-Geo-a-en\"]")
    private WebElement languageSwitchButton;

    @FindBy(xpath = "//*[@id='gh-ac-box2']/input[@type='text']")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='gh-btn']")
    private WebElement searchButton;

    public EbayEnglishHomePage(WebDriver driver) {
        super(driver);
    }

    public EbayEnglishHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        if(!currentLanguageField.getText().toLowerCase().contains("english")) {
            currentLanguageField.click();
            languageSwitchButton.click();
        }
        return this;
    }

    public SearchResultsListPage searchForTerm(String term) {
        searchField.sendKeys(term);
        searchButton.click();
        return new SearchResultsListPage(driver, term);
    }
}
