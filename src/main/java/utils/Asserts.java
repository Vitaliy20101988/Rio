package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.BasePage;
import params.Environment;

public class Asserts extends BasePage{

    private final Waits waits = new Waits(driver);
    private final FieldsInteraction fieldsInteraction = new FieldsInteraction(driver);


    public Asserts(WebDriver driver) {
        super(driver, 60);
    }

    public Asserts(WebDriver driver, int waitTime) {
        super(driver, waitTime);
    }

    public void assertInputValue(String elementBy, String expectedValue) {
        Assert.assertEquals(fieldsInteraction.getInputValue(elementBy), expectedValue);
    }

    public void assertElementText(String elementBy, String expectedText) {
        waits.waitVisibility(elementBy);
        Assert.assertEquals(fieldsInteraction.getText(elementBy), expectedText);
    }

    public void assertURLEqualsToCurrent(String expectedURL) {
        wait.until(ExpectedConditions.urlToBe(Environment.getDomain() + expectedURL));
        Assert.assertEquals(driver.getCurrentUrl(), Environment.getDomain() + expectedURL);
    }

    public void assertURLContainsToCurrent(String expectedURL) {
        wait.until(ExpectedConditions.urlContains(expectedURL));
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedURL));
    }

    public void assertElementContainsText (String elementBy, String textPart) {
        String text = fieldsInteraction.getText(elementBy);
        assert text.contains(textPart);
    }

    public void assertElementPresent(String elementBy) {
        try {
            waits.waitVisibility(elementBy);
            Assert.assertTrue(driver.findElement(By.xpath(elementBy)).isDisplayed());
        } catch (TimeoutException e) {
            System.err.println(elementBy + "is not displayed");
            Assert.assertTrue(driver.findElement(By.xpath(elementBy)).isDisplayed());
        }
    }


    public void assertElementIsNotPresent(String elementBy) {
        waits.waitInvisibility(elementBy);
        Assert.assertFalse(elementExist(elementBy));
    }

    public void assertPositiveValue(String elementBy) {
        Assert.assertTrue(Integer.parseInt(fieldsInteraction.getText(elementBy)) >= 0);
        System.out.println(fieldsInteraction.getText(elementBy));
    }

    public void assertTextEquals(String actualResult, String expectedResult) {
        Assert.assertEquals(actualResult, expectedResult);
    }

    public void assertTextContains(String actualText, String expectedTextPart) {
        Assert.assertTrue(actualText.contains(expectedTextPart));
    }

    public void assertElementIsClickable(String element) {
        waits.waitElementClickable(element);
    }

}