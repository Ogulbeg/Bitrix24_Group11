package automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Design pattern - is a general reusable solution to a commonly occurring problem with a given context in software design.
 * Patterns are formalized best practices that programmer can use to solve some common software design problems
 * There are lots of design patterns and 3 categories:
 * Creational, Structural, Behavioral.
 * One of the most simplest creational design pattern is a Singleton.
 * Singleton - means single instance/object for entire project.
 * Rules:
 * - private static instance
 * - private constructor
 * - public getter method, that returns instance already initialized.
 * <p>
 * Singleton pattern, every single test will use same webdriver object
 */

public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    // Headless mode make executions faster it does everything except file uploading
                    WebDriverManager.chromedriver().version("79.0").setup();
                    driver = new ChromeDriver();
                    break;
                case "chromeheadless":
                    //to run chrome without interface
                    WebDriverManager.chromedriver().version("79").setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);      //to run browser without interface
              //      options.addArguments("--start-maximized");
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!!!");
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
