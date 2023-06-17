package com.automation.ui.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//mvn clean test -Dbrowser.type=chrome
public class DriverFactory {

    private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

    /*
    it is not necessary to put and store WebDriver in ThreadLocal,
    in case if we use Selenide(it is already implemented out of the box there)
     */
    @Synchronized
    public static void initDriver() {
        driver.set(configureDriver());
        //to pass WebDriver to Selenide
        WebDriverRunner.setWebDriver(driver.get());
        Configuration.reportsFolder = "target/test-result/reports";
    }

    @SneakyThrows
    public static RemoteWebDriver configureDriver() {
        RemoteWebDriver remoteWebDriver;
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

        switch (System.getProperty("browser.type")) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("selenoid:options", map);
                remoteWebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
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
                remoteWebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            }
            default -> throw new IllegalArgumentException("Unknown browser");
        }
        return remoteWebDriver;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().close();
        driver.get().quit();
        driver.remove();
    }
}