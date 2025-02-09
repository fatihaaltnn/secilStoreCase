package com.secilStore.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class driver {

    private static final Logger LOGGER = Logger.getLogger(driver.class.getName());
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();


    private driver() {
    }
    /*
    Create a re-usable utility method which will return same driver instance whn we call it
    */
    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browserType = configurationReader.getProperty("browser");
            driverPool.set(createDriver(browserType));
        }
        return driverPool.get();
    }

    private static WebDriver createDriver(String browserType) {
        WebDriver driver;
        switch (browserType.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                // WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        configureDriver(driver);
        return driver;
    }

    private static void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /*
    THİS METHOD WİLL MAKE SURE OUR DRİVER VALUE İS ALWAYS NULL AFTER USİNG quit() METHOD.:
     */
    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit(); // this line will terminate the existing session. value will not even be null
            driverPool.remove();
        }
    }


}
