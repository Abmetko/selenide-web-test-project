package com.automation.ui.components.search;

import com.automation.ui.components.BaseUiComponent;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SearchBar extends BaseUiComponent {

    public SearchBar() {
        super($("form[role='search']"));
    }

    public SelenideElement getSearchField() {
        return getElement(By.cssSelector("textarea[name='q'][type='search']"));
    }

    public SelenideElement inputDataInSearchField(String text) {
        SelenideElement searchField = getSearchField();
        sendKeys(searchField, text);
        return searchField;
    }
}