package com.automation.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

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
        Selenide.sleep(50000);
    }
}