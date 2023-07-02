package com.automation.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideWait;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;

public class TestOne extends BaseTest {

    private static final String TEXT = "Minsk";

    @Test
    public void minskShouldBeDisplayedAfterSearch() {
        Selenide.open("https://www.google.com/maps/");
        $("input#searchboxinput").sendKeys(TEXT);
        $("button#searchbox-searchbutton")
                .shouldBe(Condition.enabled, Duration.ofSeconds(30)).click();
        $("h1.fontHeadlineLarge")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.text(TEXT));

        Wait()
                .ignoring(StaleElementReferenceException.class)
                .pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(30))
                .until(ExpectedConditions.titleIs("Minsk - Google Maps1"));
    }
}