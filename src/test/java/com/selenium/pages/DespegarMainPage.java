package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DespegarMainPage {
    @FindBy(css = "div.header-products-container>ul>li>a[title='Alojamientos']")
    WebElement alojamientoButton;
    @FindBy(css = "div.header-products-container ul li")
    List<WebElement> buttonsList;

    private WebDriver driver = null;

    public DespegarMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DespegarAlojamientosPage goToAlojamientos() {
        alojamientoButton.click();

        return new DespegarAlojamientosPage(this.driver);
    }

    public List<WebElement> getButtonsList() {
        return buttonsList;
    }
}
