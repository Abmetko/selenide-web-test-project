package com.automation.ui;

import com.automation.ui.driver.DriverFactory;
import com.automation.ui.extensions.LocalWebDriverExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(LocalWebDriverExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = DriverFactory.getDriver();
    }
}