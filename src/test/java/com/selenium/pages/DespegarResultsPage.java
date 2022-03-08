package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DespegarResultsPage {
    @FindBy(css = "div.results-items-wrapper div.results-cluster-container:nth-child(1) button>em")
    WebElement inputSeleccionar;

    private WebDriver driver = null;

    public DespegarResultsPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public DespegarHotelPage seleccionarHotel() {
        inputSeleccionar.click();

        return new DespegarHotelPage(this.driver);
    }
}
