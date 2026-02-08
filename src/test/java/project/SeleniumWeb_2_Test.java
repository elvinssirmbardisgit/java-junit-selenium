package project;


import static org.junit.jupiter.api.Assertions.assertTrue;
// import static project.core.TestBase.WAIT_TIMEOUT_SECONDS;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import project.core.DriverManager;
// import project.core.TestBase;

/**
 * Selenium web test example for Google search functionality.
 * 
 * Extends TestBase for multi-browser support (Chrome, Firefox).
 * 
 * Prerequisites:
 * - ChromeDriver or GeckoDriver must be installed and in system PATH
 * - Internet connection required
 * 
 * Usage:
 *   - Default (Chrome): mvn test
 *   - Chrome: mvn test -Dtest=SeleniumWebTest -Dbrowser=chrome
 *   - Firefox: mvn test -Dtest=SeleniumWebTest -Dbrowser=firefox
 */
public class SeleniumWeb_2_Test {
    private WebDriver driver = DriverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    
    @BeforeEach
    public void setUp() {
        DriverManager.getDriver();
    }
    
    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
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
    public void testSearchBox_2_Exists() {
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
        // assertTrue(driver.getTitle().contains("Selenium"));
    }

     @Test
    public void testSearchBox_3_Exists() {
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
    public void testSearchBox_4_Exists() {
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
    public void testPerform_5_Search() {
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
        // assertTrue(driver.getTitle().contains("Selenium"));
    }
}
