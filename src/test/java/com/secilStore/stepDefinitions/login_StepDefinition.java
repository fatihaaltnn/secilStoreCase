package com.secilStore.stepDefinitions;
import com.secilStore.pages.loginPage;
import com.secilStore.utilities.browserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class login_StepDefinition extends loginPage {


    @When("user go to the homepage, clicks the girisYap button")
    public void user_go_to_the_homepage_clicks_the_giris_yap_button() {
        browserUtils.clickElement(girisYapBtn);
    }

    @When("User fill the e-mail and password boxes with valid data and click the login button.")
    public void userFillTheEMailAndPasswordBoxesWithValidDataAndClickTheLoginButton() {
        browserUtils.login(inputEmailBox, inputPasswordBox, loginButton);
    }


    @Then("user checks the whether the login successfully.")
    public void user_checks_the_whether_the_login_successfully() {
        Assert.assertEquals("Hesabım", myAccountBtn.getText());

    }

    @When("User fill the e-mail and password boxes with invalid data and click the login button.")
    public void userFillTheEMailAndPasswordBoxesWithInvalidDataAndClickTheLoginButton() {
        browserUtils.invalidLogin(inputEmailBox, inputPasswordBox, loginButton);
    }

    @Then("User checks the whether the error message is displayed or not.")
    public void userChecksTheWhetherTheErrorMessageIsDisplayedOrNot() {
        String expectedMessage  = "E-Posta,Telefon numarası yada Şifre hatalı",
                actualMessage   = errorMessage.getText();

        Assert.assertEquals(expectedMessage, actualMessage);
    }


}
