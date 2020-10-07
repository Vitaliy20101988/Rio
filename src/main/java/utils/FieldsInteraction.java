package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class FieldsInteraction extends BasePage {

    private Waits waits = new Waits(driver);

    public FieldsInteraction(WebDriver driver) {
        super(driver, 10);
    }

    public String getText(String elementBy) {
        waits.waitVisibility(elementBy);
        return driver.findElement(By.xpath(elementBy)).getText();
    }

    public String getAttributeValue(String elementBy, String attributeName) {
        waits.waitVisibility(elementBy);
        return driver.findElement(By.xpath(elementBy)).getAttribute(attributeName);
    }
}
