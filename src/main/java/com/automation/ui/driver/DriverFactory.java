package com.automation.ui.driver;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static void initDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriverRunner.setWebDriver(new ChromeDriver());
    }

    public static WebDriver getDriver() {
        return Selenide.webdriver().driver().getWebDriver();
    }

    public static void closeDriver() {
        getDriver().close();
    }
}