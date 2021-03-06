package com.genaro.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DespegarHotelPage {
    @FindBy(css = "div.re-search-main-col>ul li>a em")
    private WebElement buttonModificar;
    @FindBy(css = "aloha-re-search>div.-show-content")
    private WebElement modificarMenu;

    private WebDriver driver = null;

    public DespegarHotelPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean modificarMenuIsOpen() {
        buttonModificar.click();

        return modificarMenu.isDisplayed();
    }
}
