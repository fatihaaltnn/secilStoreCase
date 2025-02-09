package com.secilStore.stepDefinitions;

import com.secilStore.utilities.browserUtils;
import com.secilStore.utilities.configurationReader;
import com.secilStore.utilities.driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.ResourceBundle.clearCache;

public class _hooks {
    byte[] screenshot = null;

    @Before
    public void setupMethod() {
        driver.getDriver().get(configurationReader.getProperty("url"));
        browserUtils.sleep(1);
        clearCache();
    }

    @After
    public void tearDownMethod(Scenario scenario) {

        if (scenario.isFailed()) {
            browserUtils.sleep(1);
            screenshot = ((TakesScreenshot) driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

            String filePath = "target/screenshots/" + scenario.getName() + ".png";

            try {
                Files.createDirectories(Paths.get("target/screenshots"));
                Files.write(Paths.get(filePath), screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        driver.closeDriver();
    }

}
