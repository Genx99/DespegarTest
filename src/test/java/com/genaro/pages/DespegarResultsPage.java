package com.genaro.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class DespegarResultsPage {
    private WebDriver driver = null;

    @FindBy(css = "div.results-items-wrapper div.results-cluster-container:nth-child(1) button>em")
    private WebElement inputSeleccionar;
    @FindBy(css = "div.message-header")
    private WebElement noHotelMessage;

    public DespegarResultsPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public DespegarHotelPage seleccionarHotel() {
        return new DespegarHotelPage(this.driver);
    }

    public boolean hotelExist() {
        try{
            inputSeleccionar.click();
            return true;
        }catch (Exception e){
            Reporter.log("No hay hoteles disponibles en esta locacion");
            return false;
        }
    }

    public boolean noHotelsMessageIsVisible() {
        return noHotelMessage.isDisplayed();
    }
}
