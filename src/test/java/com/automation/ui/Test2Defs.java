package com.automation.ui;

import com.automation.ui.driver.DriverFactory;
import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Test2Defs {

    @Given("google maps page is open")
    public void openGoogleMaps() {
        DriverFactory.initDriver();
        open("https://www.google.com/maps/");
    }

    @When("input {string} in maps search field")
    public void inputDataInSearchMapField(String text) {
        $("input#searchboxinput").sendKeys(text);
    }

    @When("click search button")
    public void clickSearchButton() {
        $("button#searchbox-searchbutton")
                .shouldBe(Condition.enabled, Duration.ofSeconds(30)).click();
    }
}