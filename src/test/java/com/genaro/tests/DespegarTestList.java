package com.genaro.tests;

import com.selenium.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DespegarTestList {
    //create driver
    WebDriver driver;

    //create waiter
    WebDriverWait wait;

    @BeforeMethod
    public void initTest() {
        //init driver
        driver = Driver.LevantarBrowser(driver, "CHROME", "https://www.despegar.com.ar");

        //init waiter
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test(description = "Testear botones superiores")
    public void testButtons() throws Exception {
        //seleccionar el anchor link "Alojamiento" y darle click
        List<WebElement> buttonList = driver.findElements(By.cssSelector("div.header-products-container ul li"));

        for(WebElement button : buttonList) {
            System.out.println(button.getText());
            Assert.assertTrue(button.isDisplayed(), "El boton no es visible");
            Assert.assertNotNull(button.getText(), "El botton no tiene texto");
        }
    }

    @AfterMethod
    public void cerrarSesion() {
        Driver.CloseBrowser(driver);
    }
}
