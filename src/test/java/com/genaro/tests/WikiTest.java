package com.genaro.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WikiTest {

    @DataProvider(name="busquedas")
    public Object[][] busquedasProvider() {
        return new Object[][] {{"Selenium"}, {"TestNG"}, {"Java"}};
    }

    @Test(description = "Validar que las busquedas en Wikipedia funcionan", dataProvider = "busquedas")
    public void ValidarBusquedaWikipedia(String busquedas) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Genaro\\Desktop\\QA Automation\\Drivers\\Chrome/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://wikipedia.org");
        WebElement searchInput = driver.findElement(By.id("searchInput"));
        Assert.assertTrue(searchInput.isDisplayed());
        searchInput.sendKeys(busquedas);
        searchInput.submit();
        WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
        System.out.println("Texto encontrado "+ tituloResultado.getText());
        Assert.assertTrue(tituloResultado.isDisplayed());
        driver.close();
    }
}
