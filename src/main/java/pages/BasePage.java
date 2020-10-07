package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import params.Environment;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Waits;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage (WebDriver driver, int waitTime) {
        this.driver = driver;
        wait = new WebDriverWait(driver, waitTime);
    }

    // Create full Page URLS based on domain set in Jenkins or Environment class
    public ArrayList<String> setURLS (String[] urlEndPoints) {
        ArrayList <String> URLS = new ArrayList<>();
        String domain = Environment.getDomain();
        for (int i = 0; i < urlEndPoints.length; i++) {
            URLS.add(domain + urlEndPoints[i]);
        }
        return URLS;
    }

    public void createNewBrowserTab() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
    }

    public void switchBrowserTab(int tabNum) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNum)); //switches to new tab
    }

    //    @Step("Open page url: {0}")
    public BasePage openPage(String pageUrl) {
        if (Environment.getDomain().contains("dev")) {
            String[] pageUrlParts = pageUrl.split("(?<=//)");
            String baseURL = pageUrlParts[0] + "serpstat:devserptest@" + pageUrlParts[1];
            System.out.println(baseURL);
            driver.get(baseURL);
        } else {
            driver.get(pageUrl);
        }
        return this;
    }

    //    @Step("Click element by path: {0}")
    public BasePage click (String elementBy) {
        Waits waits = new Waits(driver);
        waits.waitVisibility(elementBy);
        driver.findElement(By.xpath(elementBy)).click();
        return this;
    }

    //    @Step("Click element by path: {0}")
    public void clickElement(String elementBy) {
        Waits waits = new Waits(driver);
        waits.waitVisibility(elementBy);
        boolean staleElement = true;
        while(staleElement){
            try{
                driver.findElement(By.xpath(elementBy)).click();
                staleElement = false;
            } catch(StaleElementReferenceException e){
                //might be chance for infinity--coz (if the try-block keep on -failing.it won't resolve the issue,--> My Percepton in one case.)
            }
        }
    }

    public BasePage clearField(String elementBy, String text) {
        Waits waits = new Waits(driver);
        waits.waitVisibility(elementBy);
        driver.findElement(By.xpath(elementBy)).click();
        driver.findElement(By.xpath(elementBy)).clear();
        return this;
    }

    //   @Step("Enter data: {1} to element path: {0}")
    public BasePage enterData(String elementBy, String text) {
        Waits waits = new Waits(driver);
        waits.waitVisibility(elementBy);
        driver.findElement(By.xpath(elementBy)).click();
        driver.findElement(By.xpath(elementBy)).sendKeys(text);
        return this;
    }

    public void scrollUp() {
        Robot scroll = null;
        try {
            scroll = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        scroll.keyPress(KeyEvent.VK_PAGE_UP);
    }

    public void scrollDown() {
        Robot scroll = null;
        try {
            scroll = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        scroll.keyPress(KeyEvent.VK_PAGE_DOWN);
    }

    public void clickButtonEnter() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    public void clickButtonBackspace() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
    }

    public boolean elementExist(String elementBy) {
        try {
            driver.findElement(By.xpath(elementBy));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    //    @Step ("Move cursor to element path: {0}")
    public void moveToElementByCssSelector(String elementBy) {
        Waits waits = new Waits(driver);
        waits.waitVisibilityByCssSelector(elementBy);
        System.out.println("element is visible");
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(By.cssSelector(elementBy));
        action.moveToElement(elem);
        action.perform();
    }

    public BasePage avoidCapture() {
        Cookie avoidCaptcha = new Cookie("serpautotest", "true");
        driver.manage().addCookie(avoidCaptcha);
        return this;
    }
}
