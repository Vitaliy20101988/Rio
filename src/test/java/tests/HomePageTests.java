package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import listeners.ScreenshotListener;
import listeners.VideoListener;
import org.seleniumhq.jetty9.util.UrlEncoded;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AdvertisementSearchPage;
import pages.BasePage;
import pages.HomePage;
import params.SearchingParams;
import selectors.SelectorsOfHomePage;
import utils.Asserts;
import utils.FieldsInteraction;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

//@Epic("Regression")
//@Feature("Authorization Tests")
//@Listeners({ScreenshotListener.class, VideoListener.class})

public class HomePageTests extends BaseTest {

    @Test(priority = 1, description = "")
    public void verifySearchInputField() {
        Asserts asserts = new Asserts(driver);
        asserts.assertElementPresent(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue());
    }

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifySearchInputFieldIsOperable(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        Asserts asserts = new Asserts(driver);
        FieldsInteraction fieldsInteraction = new FieldsInteraction(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue);
        asserts.assertTextEquals(fieldsInteraction.getAttributeValue(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), "value"), searchingValue);
        basePage.clearField(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), "");
        Assert.assertTrue(fieldsInteraction.getAttributeValue(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), "value").isEmpty());
    }

    @Test(priority = 1, description = "")
    public void verifySearchButton() {
        Asserts asserts = new Asserts(driver);
        asserts.assertElementPresent(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
    }

    @Test(priority = 1, description = "")
    public void verifyClickSearchButton() {
        BasePage basePage = new HomePage(driver);
        Asserts asserts = new Asserts(driver);
        basePage.click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        asserts.assertURLContainsToCurrent(AdvertisementSearchPage.URL_OF_ADVERTISEMENT_SEARCH_PAGE);
    }

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifyGetParamsInURL(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        Asserts asserts = new Asserts(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue);
        basePage.click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        asserts.assertURLContainsToCurrent(AdvertisementSearchPage.URL_OF_ADVERTISEMENT_SEARCH_PAGE  + URLEncoder.encode(searchingValue, StandardCharsets.UTF_8));
    }

    @DataProvider(name="searchingValue")
    public static Object[][] validSearchingValues() {
        return new Object[][]{
                {SearchingParams.getTECHNICS()}};
    }
}
