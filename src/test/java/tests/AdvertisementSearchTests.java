package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import listeners.ScreenshotListener;
import listeners.VideoListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AdvertisementSearchPage;
import pages.BasePage;
import pages.HomePage;
import params.Environment;
import params.SearchingParams;
import selectors.SelectorsOfAdvertisementSearch;
import selectors.SelectorsOfHomePage;
import texts.Texts;
import utils.Asserts;
import utils.FieldsInteraction;
import utils.Loader;

@Epic("Regression")
@Feature("Authorization Tests")
@Listeners({ScreenshotListener.class, VideoListener.class})

public class AdvertisementSearchTests extends BaseTest {

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifySearchInputField(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        FieldsInteraction fieldsInteraction = new FieldsInteraction(driver);
        Asserts asserts = new Asserts(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue)
                .click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        asserts.assertTextEquals(fieldsInteraction.getAttributeValue(SelectorsOfAdvertisementSearch.SEARCH_INPUT_FIELD.getValue(),
                "value"), searchingValue);
    }

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifyExpandedSearchInputField(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        FieldsInteraction fieldsInteraction = new FieldsInteraction(driver);
        Asserts asserts = new Asserts(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue)
                .click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        asserts.assertTextEquals(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.SEARCH_REQUEST_EXPANDED_SEARCH_INPUT_FIELD.getValue()),
                Texts.getSearchRequest()[Environment.getLanguageIndex()] + searchingValue + "\"");
    }

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifyFirstResultTicket(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        AdvertisementSearchPage advertisementSearchPage = new AdvertisementSearchPage(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue)
                .click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        advertisementSearchPage.verifyTicketParams();
    }

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifySearchResultWithActiveCheckboxWithPicture(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        AdvertisementSearchPage advertisementSearchPage = new AdvertisementSearchPage(driver);
        Asserts asserts = new Asserts(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue)
                .click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        advertisementSearchPage.verifyCheckboxFilter(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_PICTURE.getValue(), SelectorsOfAdvertisementSearch.WITH_PICTURE_EXPANDED_SEARCH_INPUT_FIELD.getValue());
        basePage.click(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_PICTURE.getValue());
        asserts.assertElementIsNotPresent(SelectorsOfAdvertisementSearch.WITH_PICTURE_EXPANDED_SEARCH_INPUT_FIELD.getValue());

    }

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifySearchResultWithActiveCheckboxWithVideo(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        AdvertisementSearchPage advertisementSearchPage = new AdvertisementSearchPage(driver);
        Asserts asserts = new Asserts(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue)
                .click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        advertisementSearchPage.verifyCheckboxFilter(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_VIDEO.getValue(), SelectorsOfAdvertisementSearch.WITH_VIDEO_EXPANDED_SEARCH_INPUT_FIELD.getValue());
        basePage.click(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_VIDEO.getValue());
        asserts.assertElementIsNotPresent(SelectorsOfAdvertisementSearch.WITH_VIDEO_EXPANDED_SEARCH_INPUT_FIELD.getValue());
    }

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifySearchResultWithActiveCheckboxWithVideoAndPicture(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        AdvertisementSearchPage advertisementSearchPage = new AdvertisementSearchPage(driver);
        Asserts asserts = new Asserts(driver);
        Loader loader = new Loader(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue)
                .click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        advertisementSearchPage.verifyCheckboxFilter(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_VIDEO.getValue(), SelectorsOfAdvertisementSearch.WITH_VIDEO_EXPANDED_SEARCH_INPUT_FIELD.getValue());
        advertisementSearchPage.verifyCheckboxFilter(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_PICTURE.getValue(), SelectorsOfAdvertisementSearch.WITH_PICTURE_EXPANDED_SEARCH_INPUT_FIELD.getValue());
        basePage.click(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_VIDEO.getValue());
        loader.waitLoader();
        basePage.click(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_PICTURE.getValue());
        asserts.assertElementIsNotPresent(SelectorsOfAdvertisementSearch.WITH_VIDEO_EXPANDED_SEARCH_INPUT_FIELD.getValue());
        asserts.assertElementIsNotPresent(SelectorsOfAdvertisementSearch.WITH_PICTURE_EXPANDED_SEARCH_INPUT_FIELD.getValue());
    }

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifySearchResultWithActiveCheckboxWorkNow(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        AdvertisementSearchPage advertisementSearchPage = new AdvertisementSearchPage(driver);
        Loader loader = new Loader(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue)
                .click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        advertisementSearchPage.verifyCheckboxFilter(SelectorsOfAdvertisementSearch.CHECKBOX_WORK_NOW.getValue());
        loader.waitLoader();
        basePage.click(SelectorsOfAdvertisementSearch.CHECKBOX_WORK_NOW.getValue());
    }

    @Test(priority = 1, description = "", dataProvider = "searchingValue")
    public void verifySearchResultWithActiveCheckboxWithDiscount(String searchingValue) {
        BasePage basePage = new HomePage(driver);
        AdvertisementSearchPage advertisementSearchPage = new AdvertisementSearchPage(driver);
        Asserts asserts = new Asserts(driver);
        basePage.enterData(SelectorsOfHomePage.SEARCH_INPUT_FIELD.getValue(), searchingValue)
                .click(SelectorsOfHomePage.SEARCH_BUTTON.getValue());
        advertisementSearchPage.verifyCheckboxFilter(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_DISCOUNT.getValue(), SelectorsOfAdvertisementSearch.WITH_DISCOUNT_EXPANDED_SEARCH_INPUT_FIELD.getValue());
        basePage.click(SelectorsOfAdvertisementSearch.CHECKBOX_WITH_DISCOUNT.getValue());
        asserts.assertElementIsNotPresent(SelectorsOfAdvertisementSearch.WITH_DISCOUNT_EXPANDED_SEARCH_INPUT_FIELD.getValue());
    }

    @DataProvider(name = "searchingValue")
    public static Object[][] validSearchingValues() {
        return new Object[][]{
                {SearchingParams.getTECHNICS()}};
    }

}
