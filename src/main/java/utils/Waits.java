package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class Waits extends BasePage {

    public Waits(WebDriver driver) {
        super(driver, 20);
    }

    public Waits(WebDriver driver, int waitTime) {
        super(driver, waitTime);
    }


    public void waitVisibility (String elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(elementBy)));
    }

    public void waitVisibilityByCssSelector (String elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(elementBy)));
    }

    public void waitInvisibility(String elementBy) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementBy)));
    }

    public void waitUrlContains(String urlPart) {
        wait.until(ExpectedConditions.urlContains(urlPart));
    }

    public void waitUrlToBe(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void waitElementClickable(String element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
        } catch (TimeoutException e) {
            System.out.println("Element is not clickable");
        }
    }
}
