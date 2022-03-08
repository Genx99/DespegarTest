package com.genaro.tests;

import com.selenium.driver.Driver;
import com.selenium.pages.WikiHomePage;
import com.selenium.pages.WikiResultsPage;
import org.openqa.selenium.WebDriver;
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
        WikiHomePage homePage = new WikiHomePage(driver);

        Assert.assertTrue(homePage.searchInputIsVisible(), "El input no esta visible");

        WikiResultsPage wikiResultsPage = homePage.searchText(busquedas);

        Assert.assertTrue(wikiResultsPage.tituloIsDisplayed());
    }

    @AfterMethod
    public void cerrarSesion() {
        Driver.CloseBrowser(driver);
    }
}
