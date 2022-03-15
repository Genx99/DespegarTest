package com.genaro.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiHomePage {
    @FindBy(id = "searchInput")
    WebElement searchInput;

    private WebDriver driver = null;

    public WikiHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean searchInputIsVisible() {
        return this.searchInput.isDisplayed();
    }

    public WikiResultsPage searchText(String text) {
        searchInput.sendKeys(text);
        searchInput.submit();

        return new WikiResultsPage(this.driver);
    }
}
