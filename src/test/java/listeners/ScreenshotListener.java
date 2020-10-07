package listeners;

import io.qameta.allure.Attachment;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class ScreenshotListener extends BaseTest implements ITestListener {

    private static String getTestMethodName(@NotNull ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    // attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshotPNG (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
    // Text attachments for Allure
    @Contract(value = "_ -> param1", pure = true)
    @Attachment(value = "{0}", type = "text/plain")
    private static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        // Get driver from BaseTest and assign to local webdriver variable
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        //Allure ScreenShotRobot and SaveTestLog
        if (driver != null) {
            System.out.println("ScreenShot captured for test case: " + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }
        // Save a log on allure
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }
}
