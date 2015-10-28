package core.factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ResourceBundle;


public class DriverFactory {

    private static WebDriver driver;
    private ResourceBundle resource = ResourceBundle.getBundle("driver");

    public WebDriver getDriverInstance() {
        String driverType=resource.getString("driver.type");
        if (driverType.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (driverType.equals("firefox")) {
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException(driverType + " not supported!");
        }
        return driver;
    }

}
