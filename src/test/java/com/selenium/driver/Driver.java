package com.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class Driver {
    private enum browsers {
        CHROME, FIREFOX, EDGE
    }

    public static WebDriver LevantarBrowser(WebDriver driver, String browserName) {
        switch(browsers.valueOf(browserName)) {
            case CHROME:
            {
                System.setProperty("webdriver.chrome.driver", "src\\resources\\drivers\\chromedriver.exe");
                Reporter.log("Abrir Navegador Chrome");
                driver = new ChromeDriver();
                break;
            }
            case FIREFOX:
            {
                System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\geckodriver.exe");
                Reporter.log("Abrir Navegador Firefox");
                driver = new FirefoxDriver();
                break;
            }
            case EDGE:
            {
                System.setProperty("webdriver.edge.driver", "src\\resources\\drivers\\msedgedriver.exe");
                Reporter.log("Abrir Navegador Edge");
                driver = new EdgeDriver();
                break;
            }
            default:
            {
                Reporter.log("No se selecciono un navegador correcto, se abre Chrome por defecto");
                System.setProperty("webdriver.chrome.driver", "src\\resources\\drivers\\chromedriver.exe");
                Reporter.log("Abrir Navegador Chrome");
                driver = new ChromeDriver();
                break;
            }
        }

        //maximizamos el navegador
        driver.manage().window().maximize();

        return driver;
    }

    public static void CloseBrowser(WebDriver driver) {
        driver.quit();
    }
}
