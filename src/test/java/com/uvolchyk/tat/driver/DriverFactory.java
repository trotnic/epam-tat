package com.uvolchyk.tat.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    private WebDriver driver;

    public WebDriver getDriver(Boolean isHeadless) {
        if (null == driver){
            switch (System.getProperty("browser")){
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    if (isHeadless) {
                        FirefoxOptions options = new FirefoxOptions();
                        options.addArguments("--headless");
                        driver = new FirefoxDriver(options);
                    } else {
                        driver = new FirefoxDriver();
                    }
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    if (isHeadless) {
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--headless");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--incognito");
                        driver = new ChromeDriver(options);
                    } else {
                        driver = new ChromeDriver();

                    }
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public void closeDriver(){
        driver.quit();
        driver = null;
    }

}
