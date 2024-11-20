package com.sidgupta.bdd;

import com.sidgupta.pages.flightreservation.RegistrationConfirmationPage;
import com.sidgupta.pages.flightreservation.RegistrationPage;
import com.sidgupta.tests.BaseTest;
import com.sidgupta.util.Config;
import com.sidgupta.util.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class FlightReservationSteps extends CucumberHooks {

    RegistrationPage registrationPage;
    RegistrationConfirmationPage registrationConfirmationPage;

    @Given("I am on Customer Registration Page")
    public void landingOnRegistrationPage() {
        registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
    }

    @When("I enter passenger personal details such as {string} {string} {string} {string}")
    public void enterPassengerDetails(String first_name, String last_name, String email, String password) {
        registrationPage.enterUserDetails(first_name,last_name);
        registrationPage.enterCredentials(email,password);
    }

    @And("I enter passenger address such as {string} {string} {string} and {string}")
    public void enterPassengerAddress(String street, String city, String state, String zip) {
        registrationPage.enterAddress(street, city, state, zip);
    }

    @And("I click Submit")
    public void clickSubmit() {
        registrationPage.submit();
    }

    @Then("passenger should be registered")
    public void passengerRegistered() {
        registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
    }
}
