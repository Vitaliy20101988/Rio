package tests;

import org.openqa.selenium.Cookie;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import utils.SelectLanguage;
import utils.VideoRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.BeforeMethod;
import params.Environment;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup () {

        String osName = System.getProperty("os.name").toLowerCase();
        boolean isMacOS = osName.startsWith("mac os x");
        String browser = Environment.getBrowser();

        if (browser.equals("ff")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            driver = new FirefoxDriver(options);
        }
        else if (browser.equals("opera")) {
            OperaOptions options = new OperaOptions();
            options.addArguments("--disable-notifications");
            driver = new OperaDriver(options);
        }
        else if (browser.equals("safari")) {
            SafariOptions options = new SafariOptions();
            // TODO: add options to Safari
            driver = new SafariDriver(options);
        }
        else { // default browser: Chrome
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        }

        if (isMacOS) {
            System.out.println(osName);
            driver.manage().window().fullscreen();
        } else {
            driver.manage().window().maximize();
        }
        VideoRecorder.startRecording(driver);

        driver.get(Environment.getDomain());

       SelectLanguage selectLanguage = new SelectLanguage(driver);
       selectLanguage.selectLanguage();

    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void teardown (ITestResult iTestResult) {
        driver.close();
        driver.quit();
    }
}
