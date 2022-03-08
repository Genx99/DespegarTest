package com.genaro.tests;

import com.selenium.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    //create waiter
    WebDriverWait wait;

    @BeforeMethod
    public void initTest() {
        //init driver
        driver = Driver.LevantarBrowser(driver, "CHROME", "https://www.despegar.com.ar");

        //init waiter
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @DataProvider(name="lugares")
    public Object[][] lugaresProvider() {
        return new Object[][] {{"Cordoba"}, {"Mendoza"}, {"San Juan"}};
    }

    @Test(description = "Navegar a hotel y testear funcionalidad Modificar", dataProvider = "lugares")
    public void navegarHotel(String lugares) throws Exception {
        //seleccionar el anchor link "Alojamiento" y darle click
        WebElement alojamientoAnchor = driver.findElement(By.cssSelector("div.header-products-container>ul>li>a[title='Alojamientos']"));
        alojamientoAnchor.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#searchbox-sbox-box-hotels div.sbox-places-destination--1xd0k input")));

        //seleccionar el input "Destino",escribir y seleccionar "Cordoba"
        WebElement destinoInput = driver.findElement(By.cssSelector("div#searchbox-sbox-box-hotels div.sbox-places-destination--1xd0k input"));
        destinoInput.click();
        destinoInput.sendKeys(lugares);
        destinoInput.sendKeys(Keys.CONTROL);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ac-wrapper.-show>div>div>ul>li")));
        destinoInput.sendKeys(Keys.ENTER);


        //seleccionar input fecha de entrada
        WebElement fechaEntrada = driver.findElement(By.cssSelector("div#searchbox-sbox-box-hotels div.sbox5-box-dates-checkbox-container div.sbox5-dates-input1"));
        fechaEntrada.click();

        //seleccionar la fecha actual como fecha de entrada
        WebElement fechaActual = driver.findElement(By.cssSelector("div.sbox5-floating-tooltip-opened div.calendar-container div.-today"));
        wait.until(ExpectedConditions.visibilityOf(fechaActual));
        fechaActual.click();

        //seleccionar input fecha de salida
        WebElement fechaSalida = driver.findElement(By.cssSelector("div#searchbox-sbox-box-hotels div.sbox5-box-dates-checkbox-container div.sbox5-dates-input2"));
        fechaSalida.click();

        //seleccionar la fecha siguiente a la fecha de entrada
        WebElement fechaSiguiente = driver.findElement(By.cssSelector("div.sbox5-floating-tooltip-opened div.calendar-container div.-selected + div"));
        wait.until(ExpectedConditions.elementToBeClickable(fechaSiguiente));
        fechaSiguiente.click();

        //aplicar fechas
        WebElement aplicarFechas = driver.findElement(By.cssSelector("div.sbox5-floating-tooltip-opened div.calendar-footer em.btn-text"));
        aplicarFechas.click();

        //seleccionar input habitaciones
        WebElement habitaciones = driver.findElement(By.cssSelector("div.sbox5-box-content div.sbox5-3-distribution-passengers input"));
        habitaciones.click();

        //añadir adulto
        WebElement adulto = driver.findElement(By.cssSelector("div.sbox5-floating-tooltip-opened div.stepper__distribution_container>div:nth-child(1) button.steppers-icon-right"));
        wait.until(ExpectedConditions.elementToBeClickable(adulto));
        adulto.click();

        //añadir menor
        WebElement menor = driver.findElement(By.cssSelector("div.sbox5-floating-tooltip-opened div.stepper__distribution_container>div:nth-child(2) button.steppers-icon-right"));
        menor.click();

        //seleccionar edad
        Select edad = new Select(driver.findElement(By.cssSelector("div.sbox5-floating-tooltip-opened div.stepper__distribution_container>div:nth-child(3) select")));
        edad.selectByValue("3");

        //aplicar habitacion
        WebElement aplicarHab = driver.findElement(By.cssSelector("div.sbox5-floating-tooltip-opened div.stepper__room__footer>a:nth-child(1)"));
        aplicarHab.click();

        //buscar
        WebElement buscar = driver.findElement(By.cssSelector("div#searchbox-sbox-box-hotels button"));
        buscar.click();

        //seleccionar hotel
        WebElement seleccionarHotel = driver.findElement(By.cssSelector("div.results-items-wrapper div.results-cluster-container:nth-child(1) button>em"));
        seleccionarHotel.click();

        //hacer focus en la nueva pestaña
        Utils.changePage(driver);

        //Testear funcionalidad "Modificar"
        WebElement modificar = driver.findElement(By.cssSelector("div.re-search-main-col>ul li>a em"));
        modificar.click();
        WebElement modalIsOpen = driver.findElement(By.cssSelector("aloha-re-search>div.-show-content"));
        Assert.assertTrue(modalIsOpen.isDisplayed(), "El modal no se abre");

    }

    @AfterMethod
    public void cerrarSesion() {
        Driver.CloseBrowser(driver);
    }
}
