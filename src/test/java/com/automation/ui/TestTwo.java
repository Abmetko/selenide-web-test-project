package com.automation.ui;

import com.automation.ui.components.search.SearchBar;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestTwo extends BaseTest {

    SearchBar searchBar;

    @BeforeAll
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
        Selenide.sleep(50000);
    }
}