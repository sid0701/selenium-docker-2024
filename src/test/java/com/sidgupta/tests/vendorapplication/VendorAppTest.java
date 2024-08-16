package com.sidgupta.tests.vendorapplication;

import com.sidgupta.pages.vendorapplication.DashboardPage;
import com.sidgupta.pages.vendorapplication.LoginPage;
import com.sidgupta.tests.BaseTest;
import com.sidgupta.tests.vendorapplication.model.VendorAppTestData;
import com.sidgupta.util.Config;
import com.sidgupta.util.Constants;
import com.sidgupta.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorAppTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorAppTestData data;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        data = JsonUtil.getData(testDataPath, VendorAppTestData.class);
    }

    @Test
    public void loginTest(){

        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(data.username(), data.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),data.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),data.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),data.profitMargin());
        int acutalCount = dashboardPage.getResult(data.searchKeyword());
        Assert.assertEquals(acutalCount,Integer.parseInt(data.searchResultCount()));
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
