package com.sidgupta.bdd;

import com.sidgupta.util.Config;
import com.sidgupta.util.Constants;
import com.sidgupta.util.ScreenshotService;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class CucumberHooks {

protected WebDriver driver;
private static final Logger log = LoggerFactory.getLogger(CucumberHooks.class);

@BeforeAll
    public static void configSetup(){
    Config.initialize();
}

@Before
    public void setDriver() throws MalformedURLException {
    this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
}

public WebDriver getLocalDriver(){
    WebDriverManager.chromedriver().browserVersion(Config.get(Constants.BROWSER_VERSION)).setup();
    return new ChromeDriver();
}

public WebDriver getRemoteDriver() throws MalformedURLException {
    Capabilities capabilities = new ChromeOptions();

    if(Constants.FIREFOX.equalsIgnoreCase((Config.get(Constants.BROWSER))))
        capabilities = new FirefoxOptions();

    String url = String.format(Config.get(Constants.GRID_URL_FORMAT),Config.get(Constants.GRID_HUB_HOST));
    log.info("Url : {}",url);
    return new RemoteWebDriver(new URL(url), capabilities);
}

@AfterStep
    public void afterStep(Scenario scenario){
    if (scenario.isFailed())
        scenario.attach(ScreenshotService.getScreenshotsAsBytes(driver),"image/png", scenario.getName());

}

    @After
    public void afterScenario(){
        driver.quit();
    }

}
