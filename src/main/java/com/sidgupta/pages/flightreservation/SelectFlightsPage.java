package com.sidgupta.pages.flightreservation;

import com.sidgupta.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SelectFlightsPage extends AbstractPage {

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightsButton;

    public SelectFlightsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
        return this.confirmFlightsButton.isDisplayed();
    }

    public void selectFlights(){
        int random = (int)(Math.random() * this.departureFlightOptions.size());
        this.departureFlightOptions.get(random).click();
        this.actions.moveToElement(arrivalFlightOptions.get(random)).build().perform();
        this.arrivalFlightOptions.get(random).click();
    }

    public void confirmFlights(){
        this.actions.moveToElement(this.confirmFlightsButton).build().perform();
        this.confirmFlightsButton.click();
    }

}
