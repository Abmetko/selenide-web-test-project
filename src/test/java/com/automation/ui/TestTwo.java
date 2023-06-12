package com.automation.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class TestTwo extends BaseTest {

    @Test
    public void openGoogleSearch() {
        Selenide.open("https://www.google.com/");
        SelenideElement searchField = $("textarea[name='q'][type='search']");
        searchField.shouldBe(Condition.enabled, Duration.ofSeconds(30))
                .sendKeys("Minsk");
        searchField.sendKeys(Keys.ENTER);
        $("div#center_col").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}