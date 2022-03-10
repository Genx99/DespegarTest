package com.genaro.tests;

import com.selenium.driver.Driver;
import com.selenium.pages.DespegarAlojamientosPage;
import com.selenium.pages.DespegarHotelPage;
import com.selenium.pages.DespegarMainPage;
import com.selenium.pages.DespegarResultsPage;
import com.selenium.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class DespegarTest {
    //create driver
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void initTest() {
        //init driver
        driver = Driver.LevantarBrowser(driver, "CHROME", "https://www.despegar.com.ar");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @DataProvider(name="lugares")
    public Object[][] lugaresProvider() {
        return new Object[][] {{"Cordoba"}, {"Mendoza"}, {"San Juan"}};
    }

    @Test(description = "Navegar a hotel y testear funcionalidad Modificar", dataProvider = "lugares")
    public void navegarHotel(String lugares) throws Exception {

        DespegarMainPage mainPage = new DespegarMainPage(driver);

        //ir a la pagina alojamientos
        DespegarAlojamientosPage alojamientosPage = mainPage.goToAlojamientos();

        //ingresar ciudad
        alojamientosPage.ingresarCiudad(lugares);

        //seleccionar fecha de entrada
        alojamientosPage.ingresarFechaEntrada();

        //seleccionar fecha de salida y aplicar fechas
        alojamientosPage.ingresarFechaSalida();

        //añadir adultos
        alojamientosPage.agregarAdulto();

        //añadir menores y aplicar cambios
        alojamientosPage.agregarMenor("5");

        //buscar
        DespegarResultsPage resultsPage = alojamientosPage.buscarHoteles();

        //seleccionar hotel
        DespegarHotelPage hotelPage = resultsPage.seleccionarHotel();

        //hacer focus en la nueva pestaña
        Utils.changePage(driver);

        //Testear funcionalidad "Modificar"
        Assert.assertTrue(hotelPage.modificarModalIsOpen(), "El modal no se abre");
    }

    @AfterMethod
    public void cerrarSesion() {
        Driver.CloseBrowser(driver);
    }
}
