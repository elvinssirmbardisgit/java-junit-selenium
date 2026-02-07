package project.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Base class for Selenium web tests.
 * Provides WebDriver initialization with browser selection via environment variable.
 * 
 * Usage:
 *   - Default (Chrome): mvn test
 *   - Chrome: mvn test -Dbrowser=chrome
 *   - Firefox: mvn test -Dbrowser=firefox
 * 
 * Environment variables:
 *   - browser: "chrome" (default) or "firefox"
 */
public class TestBase {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final String BROWSER_PROPERTY = "browser";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final int WAIT_TIMEOUT_SECONDS = 10;
    
    /**
     * Initialize WebDriver based on browser property.
     * Called at the start of each test.
     */
    protected void initializeDriver() {
        String browser = System.getProperty(BROWSER_PROPERTY, DEFAULT_BROWSER).toLowerCase();
        
        switch (browser) {
            case "firefox":
                driver = initializeFirefoxDriver();
                break;
            case "chrome":
            default:
                driver = initializeChromeDriver();
                break;
        }
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }
    
    /**
     * Initialize Chrome WebDriver with common options.
     */
    private WebDriver initializeChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        
        WebDriver chromeDriver = new ChromeDriver(options);
        
        // Remove webdriver flag from navigator
        ((ChromeDriver) chromeDriver).executeScript(
            "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );
        
        return chromeDriver;
    }
    
    /**
     * Initialize Firefox WebDriver with common options.
     */
    private WebDriver initializeFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        
        WebDriver firefoxDriver = new FirefoxDriver(options);
        return firefoxDriver;
    }
    
    /**
     * Quit WebDriver and close all browser windows.
     * Called at the end of each test.
     */
    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    /**
     * Get the current browser name from system property.
     */
    protected String getBrowserName() {
        return System.getProperty(BROWSER_PROPERTY, DEFAULT_BROWSER).toLowerCase();
    }
}
