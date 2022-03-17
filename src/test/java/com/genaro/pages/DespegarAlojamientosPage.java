package com.genaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DespegarAlojamientosPage {
    private WebDriver driver = null;
    private WebDriverWait wait = null;

    @FindBy(css = "div#searchbox-sbox-box-hotels div.sbox-places-destination--1xd0k input")
    private WebElement inputCiudad;
    @FindBy(css = "div.ac-wrapper.-show>div>div>ul>li")
    private WebElement modalCiudad;
    @FindBy(css = "div#searchbox-sbox-box-hotels div.sbox5-box-dates-checkbox-container div.sbox5-dates-input1")
    private WebElement inputFechaEntrada;
    @FindBy(css = "div.sbox5-floating-tooltip-opened div.calendar-container div.-today")
    private WebElement fechaHoy;
    @FindBy(css = "div#searchbox-sbox-box-hotels div.sbox5-box-dates-checkbox-container div.sbox5-dates-input2")
    private WebElement inputFechaSalida;
    @FindBy(css = "div.sbox5-floating-tooltip-opened div.calendar-container div.-selected + div")
    private WebElement fechaManana;
    @FindBy(css = "div.sbox5-floating-tooltip-opened div.calendar-footer em.btn-text")
    private WebElement aplicarFechas;
    @FindBy(css = "div.sbox5-box-content div.sbox5-3-distribution-passengers input")
    private WebElement inputHabitaciones;
    @FindBy(css = "div.sbox5-floating-tooltip-opened div.stepper__distribution_container>div:nth-child(1) button.steppers-icon-right")
    private WebElement agregarAdulto;
    @FindBy(css = "div.sbox5-floating-tooltip-opened div.stepper__distribution_container>div:nth-child(2) button.steppers-icon-right")
    private WebElement agregarMenor;
    @FindBy(css = "div.sbox5-floating-tooltip-opened div.stepper__room__footer>a:nth-child(1)")
    private WebElement aplicarHabitacion;
    @FindBy(css = "div#searchbox-sbox-box-hotels button")
    private WebElement buttonBuscar;


    public DespegarAlojamientosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        PageFactory.initElements(driver, this);
    }

    public void ingresarCiudad(String text) throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(inputCiudad));
        inputCiudad.click();
        inputCiudad.sendKeys(text);
        Thread.sleep(100);
        inputCiudad.sendKeys(Keys.CONTROL);
        wait.until(ExpectedConditions.elementToBeClickable(modalCiudad));
        inputCiudad.sendKeys(Keys.ENTER);
    }

    public void ingresarFechaEntrada() {
        inputFechaEntrada.click();

        wait.until(ExpectedConditions.visibilityOf(fechaHoy));
        fechaHoy.click();
    }

    public void ingresarFechaSalida() {
        inputFechaSalida.click();

        wait.until(ExpectedConditions.elementToBeClickable(fechaManana));
        fechaManana.click();

        aplicarFechas.click();
    }

    public void agregarAdulto() {
        inputHabitaciones.click();

        wait.until(ExpectedConditions.elementToBeClickable(agregarAdulto));

        agregarAdulto.click();

    }

    public void agregarMenor(String edad) {
        agregarMenor.click();

        Select edadMenor = new Select(driver.findElement(By.cssSelector("div.sbox5-floating-tooltip-opened div.stepper__distribution_container>div:nth-child(3) select")));
        edadMenor.selectByValue(edad);

        aplicarHabitacion.click();
    }

    public DespegarResultsPage buscarHoteles() {
        buttonBuscar.click();

        return new DespegarResultsPage(this.driver);
    }
}
