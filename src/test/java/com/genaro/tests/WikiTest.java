package com.genaro.tests;

import com.selenium.driver.Driver;
import com.selenium.pages.WikiHomePage;
import com.selenium.pages.WikiResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WikiTest {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void initTest(ITestContext context) {
        //recibir navegador de suite
        String navegadorSuite = context.getCurrentXmlTest().getParameter("Navegador");
        String navegador = navegadorSuite != null ? navegadorSuite : "CHROME";

        //establecer sitio
        String url = "http://wikipedia.org";

        //init driver
        driver = Driver.LevantarBrowser(driver, navegador, url);
    }


    @DataProvider(name="busquedas")
    public Object[][] busquedasProvider() {
        return new Object[][] {{"Selenium"}, {"TestNG"}, {"Java"}};
    }

    @Test(groups={"Test Busquedas"}, description = "Validar que las busquedas en Wikipedia funcionan", dataProvider = "busquedas")
    public void ValidarBusquedaWikipedia(String busquedas) throws Exception {
        WikiHomePage homePage = new WikiHomePage(driver);

        Assert.assertTrue(homePage.searchInputIsVisible(), "El input no esta visible");

        WikiResultsPage wikiResultsPage = homePage.searchText(busquedas);

        Assert.assertTrue(wikiResultsPage.tituloIsDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void cerrarSesion() {
        Driver.CloseBrowser(driver);
    }
}
