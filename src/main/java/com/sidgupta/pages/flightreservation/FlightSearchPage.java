package com.sidgupta.pages.flightreservation;

import com.sidgupta.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement noOfPassengersDropdown;

    @FindBy(id = "search-flights")
    private WebElement searchFlightButton;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchFlights(String noOfPassengers){
        Select selPassengers = new Select(this.noOfPassengersDropdown);
        selPassengers.selectByValue(noOfPassengers);
        this.actions.moveToElement(this.searchFlightButton).build().perform();
        this.searchFlightButton.click();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.noOfPassengersDropdown));
        return this.noOfPassengersDropdown.isDisplayed();
    }
}
