package com.sidgupta.pages.flightreservation;

import com.sidgupta.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(css = ".card-body div.row:nth-child(1) p")
    private WebElement flightConfirmationElement;

    @FindBy(css = ".card-body div.row:nth-child(3) p")
    private WebElement totalPriceElement;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationElement));
        return this.flightConfirmationElement.isDisplayed();
    }

    public String getTotalPrice(){

        String confirmationNo = this.flightConfirmationElement.getText();
        String price = this.totalPriceElement.getText();

        log.info("Flight Confirmation # : {}",confirmationNo);
        log.info("Total Price : {}",price);

        return this.totalPriceElement.getText();
    }

}
