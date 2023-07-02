package com.automation.ui.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.Wait;

@SuppressWarnings("UnusedReturnValue")
public abstract class BaseUiComponent {

    private final SelenideElement container;

    public BaseUiComponent(SelenideElement container) {
        this.container = container;
    }

    public SelenideElement getContainer() {
        return container;
    }

    public SelenideElement getElement(By locator) {
        return container.$(locator);
    }

    public ElementsCollection getElements(By locator) {
        return container.$$(locator);
    }

    public void clickOnElement(SelenideElement element) {
        element.shouldBe(Condition.enabled).click();
    }

    public SelenideElement sendKeys(SelenideElement element, String text) {
        element.shouldBe(Condition.enabled, Duration.ofSeconds(60)).sendKeys(text);
        return element;
    }

    public void waitUntil(ExpectedCondition<Boolean> function, String message) {
        Wait()
                .ignoring(StaleElementReferenceException.class)
                .pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(30))
                .withMessage(message)
                .until(function);
    }
}