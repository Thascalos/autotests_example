package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Browsers.CHROME;
import static helpers.Environment.*;


public class CustomWebDriver implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        capabilities.setBrowserName(CHROME);
        capabilities.setCapability("profile.password_manager_enabled", false);
        capabilities.setCapability("credentials_enable_service", false);
        if(isSelenoid) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            capabilities.setCapability("videoFrameRate", 24);
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        WebDriverManager.chromedriver().setup();

        if(isSelenoid) {
            return getRemoteWebDriver(capabilities);
        } else {
            return new ChromeDriver(capabilities);
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if(isHeadless) chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--lang=ru");

        return chromeOptions;
    }

    private WebDriver getRemoteWebDriver(DesiredCapabilities capabilities) {
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(getRemoteWebdriverUrl(), capabilities);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }

    private URL getRemoteWebdriverUrl() {
        try {
            return new URL( "http://" + selenoidUrl + ":4444/wd/hub/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}