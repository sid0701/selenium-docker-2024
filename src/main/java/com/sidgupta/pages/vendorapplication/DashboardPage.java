package com.sidgupta.pages.vendorapplication;

import com.sidgupta.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement resultInfoElement;

    @FindBy(css = ".img-profile")
    private WebElement imgProfileElement;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement logoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningElement));
        return this.monthlyEarningElement.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarningElement.getText();
    }

    public String getAnnualEarning(){
        return this.annualEarningElement.getText();
    }

    public String getProfitMargin(){
        return this.profitMarginElement.getText();
    }

    public Integer getResult(String keyword){
        this.searchInput.sendKeys(keyword);
        this.actions.moveToElement(this.resultInfoElement).build().perform();
        String result = this.resultInfoElement.getText();
        String[] resultText = result.split(" ");
        int count = Integer.parseInt(resultText[5]);
        log.info("Results count : {}",count);
        return count;
    }

    public void logout(){
        this.actions.moveToElement(this.imgProfileElement).build().perform();
        this.imgProfileElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutButton));
        this.logoutButton.click();
    }

}
