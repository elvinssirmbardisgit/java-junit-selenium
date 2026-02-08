package project.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    private static WebDriver createDriver() {
        String browser = System.getProperty("browser").toLowerCase();

        switch (browser) {
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
            case "chrome":
            default:
                ChromeOptions opts = new ChromeOptions();
                opts.addArguments("--start-maximized");
                opts.addArguments("--disable-blink-features=AutomationControlled");
                return new ChromeDriver(opts);
        }
    }

    public static void quitDriver() {
        var d = driver.get();
        if (d == null) {
            return;
        }
        d.quit();
        driver.remove();
    }
}

