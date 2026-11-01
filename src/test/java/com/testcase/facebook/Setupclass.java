package com.testcase.facebook;

import static org.testng.Assert.assertEquals;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Setupclass {
    public WebDriver driver;

    @BeforeMethod
    public void setupbrowser() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }
    
    // FIX 1: Added String standard parameters to accept DataProvider items
    @Test( priority = 0, dataProvider = "inputgoogleSearchtext")
    public void testing(String searchText) {
        assertEquals(driver.getTitle(), "Google");
        
        // FIX 2: Fixed the syntax error in By.name
        WebElement inputtext = driver.findElement(By.name("q")); 
        
        // FIX 3: Replaced hardcoded text with the data provider variable
        inputtext.sendKeys(searchText);
    }
    
    @DataProvider(name = "inputgoogleSearchtext")
    public Object[][] getLoginData() {
        return new Object[][] {
            { "testing" },
            { "githubh" }
        };
    }
    @Test(priority = 1)
    public void seracch() {
    	driver.findElement(By.cssSelector("#input")).sendKeys("testing");
    }

    @AfterMethod
    public void teardwon() {
        if (driver != null) {
            driver.quit(); // Best practice: use quit instead of close to clean up background processes
        }
    }
}
