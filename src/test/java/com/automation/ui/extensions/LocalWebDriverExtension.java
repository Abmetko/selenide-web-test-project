package com.automation.ui.extensions;

import com.automation.ui.driver.DriverFactory;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LocalWebDriverExtension implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        DriverFactory.initDriver();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        DriverFactory.closeDriver();
    }
}