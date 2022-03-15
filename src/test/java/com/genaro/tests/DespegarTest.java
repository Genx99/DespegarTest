package com.genaro.tests;

import com.genaro.driver.Driver;
import com.genaro.pages.DespegarAlojamientosPage;
import com.genaro.pages.DespegarHotelPage;
import com.genaro.pages.DespegarMainPage;
import com.genaro.pages.DespegarResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.genaro.utils.Utils;

import java.util.List;


public class DespegarTest {
    //create driver
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void initTest(ITestContext context) {
        //recibir navegador de suite
        String navegadorSuite = context.getCurrentXmlTest().getParameter("Navegador");
        String navegador = navegadorSuite != null ? navegadorSuite : "CHROME";

        //establecer sitio
        String url = "https://www.despegar.com.ar";

        //init driver
        driver = Driver.LevantarBrowser(driver, navegador, url);
    }

    @DataProvider(name="lugares")
    public Object[][] lugaresProvider() {
        return new Object[][] {{"Cordoba"}, {"Mendoza"}, {"San Juan"}};
    }

    @Test(groups={"Navegar a Hotel"}, description = "Navegar a hotel y testear funcionalidad Modificar", dataProvider = "lugares")
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

    @Test(groups={"Test Buttons"}, description = "Test botones de seccion")
    public void testButtons() throws Exception {
        DespegarMainPage mainPage = new DespegarMainPage(driver);

        List<WebElement> buttonList = mainPage.getButtonsList();

        for(WebElement button : buttonList) {
            System.out.println(button.getText());
            Assert.assertTrue(button.isDisplayed(), "El boton no es visible");
            Assert.assertNotNull(button.getText(), "El botton no tiene texto");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void cerrarSesion() {
        Driver.CloseBrowser(driver);
    }
}
