package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.*;
import java.time.Duration;

/**
 * Selenium web test example for Google search functionality.
 * 
 * Prerequisites:
 * - ChromeDriver must be installed and in system PATH
 * - Internet connection required
 */
public class SeleniumWebTest {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    @Before
    public void setUp() {
        // Initialize Chrome driver with options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Remove webdriver flag from navigator
        ((ChromeDriver) driver).executeScript( "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})" );

    }
    
    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
    
    /**
     * Helper method to accept cookies on Google page.
     */
    private void acceptGoogleCookies() {
        try {
            // Wait for and click the "Accept all" button
            WebElement acceptButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button/div[contains(text(), 'Piekrist visiem')]"))
            );
            acceptButton.click();
        } catch (Exception e) {
            // Cookie banner may not appear in all contexts
        }
    }

    @Test
    public void testGooglePageTitle() {
        // Navigate to Google
        driver.get("https://www.google.com");
        
        // Accept cookies
        acceptGoogleCookies();
        
        // Verify page title contains "Google"
        assertTrue(driver.getTitle().contains("Google"));
    }
    
    @Test
    public void testSearchBoxExists() {
        // Navigate to Google
        driver.get("https://www.google.com");
        
        // Accept cookies
        acceptGoogleCookies();
        
        // Wait for and find search box
        WebElement searchBox = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.name("q"))
        );
        
        // Verify search box is displayed
        assertTrue(searchBox.isDisplayed());
    }
    @Test
    public void testPerformSearch() {
        // Navigate to Google
        driver.get("https://www.google.com");

        // Sleep before sending keys to allow page to stabilize
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Accept cookies
        acceptGoogleCookies();
        
        // Find and fill search box
        WebElement searchBox = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.name("q"))
        );
        
        // Sleep before sending keys to allow page to stabilize
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        searchBox.sendKeys("Selenium");

        // Sleep before sending keys to allow page to stabilize
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        searchBox.submit();
        
        // Wait for results page to load
        wait.until(ExpectedConditions.urlContains("search?q="));
        
        // Verify we're on search results page
        assertTrue(driver.getCurrentUrl().contains("search?q="));
        assertTrue(driver.getTitle().contains("Selenium"));
    }
}
