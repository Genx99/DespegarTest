package com.genaro.utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class Utils {
    public static void changePage(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();

        for(String actual : handles) {
            if(!actual.equalsIgnoreCase(driver.getWindowHandle())) {
                driver.switchTo().window(actual);
            }
        }
    }
}
