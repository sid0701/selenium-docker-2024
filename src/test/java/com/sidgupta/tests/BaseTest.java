package com.sidgupta.tests;

import com.sidgupta.listener.TestListener;
import com.sidgupta.util.Config;
import com.sidgupta.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public class BaseTest {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void configSetup(){
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);
    }

    public WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().browserVersion(Config.get(Constants.BROWSER_VERSION)).setup();
        return new ChromeDriver();
    }

    public WebDriver getRemoteDriver() throws MalformedURLException {

        Capabilities capabilities = new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER)))
            capabilities = new FirefoxOptions();

        String url = String.format(Config.get(Constants.GRID_URL_FORMAT),Config.get(Constants.GRID_HUB_HOST));
        log.info("Url : {}",url);
        return new RemoteWebDriver(new URL(url),capabilities);

    }

    @AfterTest
    public void tearDown(){
        this.driver.quit();
    }

//    @AfterMethod
//    public void afterEachMethod(){
//        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
//    }

}
