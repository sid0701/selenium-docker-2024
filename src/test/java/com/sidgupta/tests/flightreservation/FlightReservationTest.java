package com.sidgupta.tests.flightreservation;

import com.sidgupta.pages.flightreservation.*;
import com.sidgupta.tests.BaseTest;
import com.sidgupta.tests.flightreservation.model.PassengerDetail;
import com.sidgupta.util.Config;
import com.sidgupta.util.Constants;
import com.sidgupta.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends BaseTest {

    private PassengerDetail passengerDetail;

    @BeforeTest
    @Parameters("testDataPath")
    public void setupParameters(String testDataPath)
    {
        passengerDetail = JsonUtil.getData(testDataPath, PassengerDetail.class);
    }

    @Test
    public void customerRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(passengerDetail.fname(),passengerDetail.lname());
        registrationPage.enterCredentials(passengerDetail.email(),passengerDetail.password());
        registrationPage.enterAddress(passengerDetail.street(),passengerDetail.city(),passengerDetail.state(),passengerDetail.zip());
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "customerRegistrationTest")
    public void goToFlightSearchTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.goToFlightSearch();
    }

    @Test(dependsOnMethods = "goToFlightSearchTest")
    public void searchFlightTest(){
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.searchFlights(passengerDetail.noOfPassengers());
    }

    @Test(dependsOnMethods = "searchFlightTest")
    public void selectFlightsTest(){
        SelectFlightsPage selectFlightsPage = new SelectFlightsPage(driver);
        Assert.assertTrue(selectFlightsPage.isAt());
        selectFlightsPage.selectFlights();
        selectFlightsPage.confirmFlights();
    }

    @Test(dependsOnMethods = "selectFlightsTest")
    public void flightConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getTotalPrice(),passengerDetail.price());
    }

}
