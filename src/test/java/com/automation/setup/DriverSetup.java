package com.automation.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverSetup {
    public static WebDriver driver;
    public static ConfigFileReader filereader = new ConfigFileReader();

    public static WebDriver getDriver() {   // DO NOT CHANGE OR MODIFY THE METHOD SIGNATURE

        if(driver == null) {
            //Get the drivername and driverpath by reading the configuration file.

            System.setProperty(filereader.getDriverName(), filereader.getDriverPath());

            FirefoxBinary firefoxBinary = new FirefoxBinary();
//            firefoxBinary.addCommandLineOptions("--headless");
            FirefoxProfile profile=new FirefoxProfile();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBinary(firefoxBinary);
            firefoxOptions.setProfile(profile);

            driver = new FirefoxDriver(firefoxOptions);
        }

        return driver;
    }
}
