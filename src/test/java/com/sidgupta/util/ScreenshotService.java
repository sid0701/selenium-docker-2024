package com.sidgupta.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotService {

public static byte[] getScreenshotsAsBytes(WebDriver driver){
    TakesScreenshot d  = (TakesScreenshot) driver;
    return d.getScreenshotAs(OutputType.BYTES);
}

}
