package com.secilStore.pages;

import com.secilStore.utilities.driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {


    public loginPage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id=\"__next\"]/main/header/div[5]/div/ul/li[5]/a/span")
    public WebElement girisYapBtn;
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/div/div[2]/div/form/div[1]/div/input")
    public WebElement inputEmailBox;
    @FindBy(xpath = "//*[@id=\"login\"]/div/div/div/div[2]/div/form/div[2]/div/input")
    public WebElement inputPasswordBox;
    @FindBy (xpath = "//*[@id=\"login\"]/div/div/div/div[2]/div/form/div[4]/button")
    public WebElement loginButton;
    @FindBy (xpath = "//*[@id=\"__next\"]/main/header/div[5]/div/ul/li[6]/label/div/span")
    public WebElement myAccountBtn;
    @FindBy (css = ".p-1.text-center")
    public WebElement errorMessage;


}
