package com.automation.ui.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//mvn clean test -Dbrowser.type=chrome
@SuppressWarnings("deprecation")
public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    /*
    it is not necessary to put and store WebDriver in ThreadLocal,
    in case if we use Selenide(it is already implemented out of the box there)
     */

    public static void initDriver() {
        switch (System.getProperty("browser.type")) {
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            case "chrome" -> WebDriverManager.chromedriver().setup();
            default -> throw new IllegalArgumentException();
        }
        driver.set(configureDriver());
        WebDriverRunner.setWebDriver(driver.get());//to pass WebDriver to Selenide
        Configuration.reportsFolder = "target/test-result/reports";
    }

    @SneakyThrows
    public static RemoteWebDriver configureDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "SELENOID TEST");
            /* How to set session timeout */
            put("sessionTimeout", "15m");
            put("-timeout", "60s");
            put("-limit", "10");
            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});
            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            /* How to enable video recording */
            put("enableVNC", true);
//            put("enableVideo", true);
        }});
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
//        prefs.put("intl.accept_languages", "ru,ru_RU");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setHeadless(false);
        options.addArguments("–-no-sandbox");
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().close();
        driver.remove();
    }
}