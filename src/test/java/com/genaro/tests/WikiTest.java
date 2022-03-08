package com.genaro.tests;

import com.selenium.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WikiTest {
    WebDriver driver;

    @BeforeMethod
    public void initTest() {
        //init driver
        driver = Driver.LevantarBrowser(driver, "CHROME", "http://wikipedia.org");
    }


    @DataProvider(name="busquedas")
    public Object[][] busquedasProvider() {
        return new Object[][] {{"Selenium"}, {"TestNG"}, {"Java"}};
    }

    @Test(description = "Validar que las busquedas en Wikipedia funcionan", dataProvider = "busquedas")
    public void ValidarBusquedaWikipedia(String busquedas) throws Exception {
        WebElement searchInput = driver.findElement(By.id("searchInput"));
        Assert.assertTrue(searchInput.isDisplayed());
        searchInput.sendKeys(busquedas);
        searchInput.submit();

        WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
        System.out.println("Texto encontrado "+ tituloResultado.getText());
        Assert.assertTrue(tituloResultado.isDisplayed());
    }

    @AfterMethod
    public void cerrarSesion() {
        Driver.CloseBrowser(driver);
    }
}
