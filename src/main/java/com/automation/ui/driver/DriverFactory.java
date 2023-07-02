package com.automation.ui.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DriverFactory {

    public static void initDriver() {
        //to pass WebDriver to Selenide
        WebDriverRunner.setWebDriver(configureDriver());
        Configuration.reportsFolder = "target/test-result/reports";
    }

    @SneakyThrows
    public static WebDriver configureDriver() {
        WebDriver driver;
        HashMap<String, Object> map = new HashMap<>() {{
            put("name", "SELENOID TEST");
            put("sessionTimeout", "15m");
            put("-timeout", "60s");
            put("-limit", "10");
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            put("enableVNC", true);
        }};

        if (System.getProperty("browser.type") == null) {
            System.setProperty("browser.type", "chrome");
        }

        switch (System.getProperty("browser.type")) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("selenoid:options", map);
                options.addPreference("intl.accept_languages", "en-EN");
                driver = new FirefoxDriver(options);
            }
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setCapability("selenoid:options", map);
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 1);
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");
                options.setExperimentalOption("prefs", prefs);
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.addArguments("–-no-sandbox");
                options.addArguments("--incognito");
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unknown browser");
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return Selenide.webdriver().driver().getWebDriver();
    }

    public static void closeDriver() {
        getDriver().close();
    }
}