package com.automation.ui;

import com.automation.ui.components.search.SearchBar;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestTwo extends BaseTest {

    SearchBar searchBar;

    @BeforeClass
    void beforeAll() {
        open("https://www.google.com/");
        searchBar = new SearchBar();
    }

    @Test
    public void openGoogleSearch() {
        searchBar
                .inputDataInSearchField("Minsk")
                .sendKeys(Keys.ENTER);
        $("div#center_col").shouldBe(Condition.visible, Duration.ofSeconds(30));
        searchBar.waitUntil(ExpectedConditions.titleIs("Minsk - Google Search"), "Unexpectedly, title has incorrect name.");
    }
}