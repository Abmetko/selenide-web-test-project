package com.automation.ui;

import com.automation.ui.components.search.SearchBar;
import com.automation.ui.driver.DriverFactory;
import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Test1Defs {

    @Given("google page is open")
    public void openGoogleSearch() {
        DriverFactory.initDriver();
        open("https://www.google.com/");
    }

    @Given("input {string} in search field")
    public void inputDataInSearchField(String text) {
        new SearchBar().inputDataInSearchField(text)
                .sendKeys(Keys.ENTER);
    }

    @Then("data should be displayed")
    public void dataShouldBeDisplayed() {
        $("div#center_col").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}