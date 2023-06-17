package com.automation.ui;

import com.automation.ui.driver.DriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    WebDriver driver;

    @BeforeAll
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
    }

    @AfterAll
    public void tearDown() {
        DriverFactory.closeDriver();
    }
}