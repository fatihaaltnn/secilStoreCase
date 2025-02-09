package com.secilStore.utilities;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


/*
In this class only general utility methos that are not related to some specific page.
*/
public class browserUtils {

    /*
    This method will accept int(in second) and execute Thread.sleep
    forgiven duration
  */
    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
    }


    /*
    Creating a utility method for ExpilicitWait(visibilityOF), so we don't have to repeat the lines
    */
    public static void waitForVisibilityOf(WebElement webElement, int second) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitUntilClickable(WebElement element, int second) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void scrollAndClick(WebElement element) {
        waitUntilClickable(element, 15);
        scrollToElement(element);
        element.click();
        browserUtils.sleep(1);
    }

    public static void clickElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }


    /*
    Created a helper method to handle alert notifications.
    With this method, we pass the alert notification and do not have to write lines of code repeatedly.
     */
    public static Alert handleAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        return alert;
    }


    /*
    Thanks to this method; to login we just input username, password and webelement's locate
     */
    public static void login(WebElement usernameField, WebElement passwordField, WebElement loginButton, String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    /*
    Thanks to this method; to login we just input webelement's locate and then
    the method itself gets the username and password data from configuration properties
     */
    //**OVERLOADİNG**
    public static void login(WebElement usernameField, WebElement passwordField, WebElement loginButton) {
        String username = configurationReader.getProperty("username");
        String password = configurationReader.getProperty("password");
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    /*
   Thanks to this method; we check the log in with unvalid data we just input webelement's locate and then
    the method itself gets the username and password data from Faker library.
    */
    public static void invalidLogin(WebElement usernameField, WebElement passwordField, WebElement loginButton) {
        Faker faker = new Faker();
        String username = faker.internet().emailAddress();
        String password = faker.internet().password();
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }


    /*
   Thanks to this method; to select any options of dropdown menu, we input options' İNDEX NUMBER which we want to select
    */
    public static String getDropdownText(WebElement element) {
        Select dropdownMenu = new Select(element);
        String text = dropdownMenu.getFirstSelectedOption().getText();
        return text;
    }

    /*
   Thanks to this method; to select any options of dropdown menu, we input option's VALUE or  option's TEXT which we want to select.
   Because of VALUE's and TEXT's datatype is same (String),  we use enum and then we use "Type" parameter to select which parameter that want to use with the method TEXT or VALUE.
    */
    public enum SelectionType {
        VALUE,
        TEXT,
        INTEGER,
    }

    //**OVERLOADİNG**
    public static void selectDropdown(WebElement element, String indexORvalueORtext, SelectionType type) {

        Select dropdownMenu = new Select(element);
        if (type == SelectionType.VALUE) {
            dropdownMenu.selectByValue(indexORvalueORtext);
        } else if (type == SelectionType.TEXT) {
            dropdownMenu.selectByVisibleText(indexORvalueORtext);
        } else if (type == SelectionType.INTEGER) {
            dropdownMenu.selectByVisibleText(indexORvalueORtext);
        }
    }



}