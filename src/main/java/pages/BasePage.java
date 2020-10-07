package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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

    public BasePage click (String elementBy) {
        Waits waits = new Waits(driver);
        waits.waitVisibility(elementBy);
        driver.findElement(By.xpath(elementBy)).click();
        return this;
    }

    public BasePage clearField(String elementBy) {
        Waits waits = new Waits(driver);
        waits.waitVisibility(elementBy);
        driver.findElement(By.xpath(elementBy)).click();
        driver.findElement(By.xpath(elementBy)).clear();
        return this;
    }

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

    public void select(String element, String value) {
        Waits waits = new Waits(driver);
        waits.waitVisibility(element);
        Select select = new Select(driver.findElement(By.xpath(element)));
        select.selectByValue(value);
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

    public boolean elementExist(String elementBy) {
        try {
            driver.findElement(By.xpath(elementBy));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void moveToElementByCssSelector(String elementBy) {
        Waits waits = new Waits(driver);
        waits.waitVisibilityByCssSelector(elementBy);
        System.out.println("element is visible");
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(By.cssSelector(elementBy));
        action.moveToElement(elem);
        action.perform();
    }
}