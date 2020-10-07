package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import selectors.SelectorsOfAdvertisementSearch;
import utils.Asserts;
import utils.FieldsInteraction;
import utils.Loader;

public class AdvertisementSearchPage extends BasePage {

    FieldsInteraction fieldsInteraction = new FieldsInteraction(driver);
    Asserts asserts = new Asserts(driver);

    public static final String URL_OF_ADVERTISEMENT_SEARCH_PAGE = "advertisement/search/?search_text=";

    public AdvertisementSearchPage(WebDriver driver) {
        super(driver, 15);
    }

    public void verifyTicketParams() {
        // receive name from first ticket
        String ticketName = String.valueOf(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_NAME_IN_SEARCH_RESULT.getValue()));
        Assert.assertEquals(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_NAME_IN_SEARCH_RESULT.getValue()).getClass().getName(),
                "java.lang.String");
        Assert.assertTrue(!ticketName.equals("null") && !ticketName.trim().isEmpty());
        // receive price from first ticket
        String ticketPrice = String.valueOf(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue()));
        Assert.assertEquals(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue()).getClass().getName(),
                "java.lang.String");
        Assert.assertTrue(!ticketPrice.equals("null") && !ticketPrice.trim().isEmpty());
    }

    public void verifyMinPriceParam(int expectedValue) {
        // receive price from first ticket
        Loader loader = new Loader(driver);
        loader.waitLoader();
        String ticketPrice = String.valueOf(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue()));
        Assert.assertEquals(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue()).getClass().getName(),
                "java.lang.String");
        Assert.assertTrue(!ticketPrice.equals("null") && !ticketPrice.trim().isEmpty());
        try {
            System.out.println(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue()));
            Assert.assertTrue(Integer.parseInt(
                    fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue())) >= expectedValue);
        } catch (NumberFormatException e) {
            Assert.fail(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue()) + " cannot be parse to type int");
        }
    }

        public void verifyMaxPriceParam(int expectedValue) {
            // receive price from first ticket
            String ticketPrice = String.valueOf(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue()));
            Assert.assertEquals(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue()).getClass().getName(),
                    "java.lang.String");
            Assert.assertTrue(!ticketPrice.equals("null") && !ticketPrice.trim().isEmpty());
            try {
                Assert.assertTrue(Integer.parseInt(
                        fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue())) <= expectedValue);
            } catch (NumberFormatException e) {
                Assert.fail(fieldsInteraction.getText(SelectorsOfAdvertisementSearch.FIRST_TICKET_PRICE_IN_SEARCH_RESULT.getValue()) + " cannot be parse to type int");
            }
    }

    private int receiveTotalQuantityOfTickets() {
        String resultValue;
        if (fieldsInteraction.getText(SelectorsOfAdvertisementSearch.SUM_RESULT_OF_SEARCHING.getValue()).contains(" ")) {
            resultValue = fieldsInteraction.getText(SelectorsOfAdvertisementSearch.SUM_RESULT_OF_SEARCHING.getValue()).replace(" ", "");
        } else {
            resultValue = fieldsInteraction.getText(SelectorsOfAdvertisementSearch.SUM_RESULT_OF_SEARCHING.getValue());
        }
        int resultValueOfTickets = -1;
        try {
            resultValueOfTickets = Integer.parseInt(resultValue);
        } catch (NumberFormatException e) {
            System.err.println(resultValue + " cannot be cast to type int");
        }
        return resultValueOfTickets;
    }

    public void verifyCheckboxFilter(String filterElement, String expectedVisibleElement) {
        Loader loader = new Loader(driver);
        int sumResultBeforeFilter = receiveTotalQuantityOfTickets();
        click(filterElement);
        asserts.assertElementPresent(expectedVisibleElement);
        loader.waitLoader();
        int sumResultAfterFilter = receiveTotalQuantityOfTickets();
        String errMsg = "sumResultTicketsAfterFilter: " + sumResultAfterFilter + " cannot be less then sumResultBeforeFilter " + sumResultBeforeFilter;
        Assert.assertTrue(sumResultAfterFilter <= sumResultBeforeFilter, errMsg);
    }

    public void verifyCheckboxFilter(String filterElement) {
        Loader loader = new Loader(driver);
        int sumResultBeforeFilter = receiveTotalQuantityOfTickets();
        click(filterElement);
        loader.waitLoader();
        int sumResultAfterFilter = receiveTotalQuantityOfTickets();
        String errMsg = "sumResultTicketsAfterFilter: " + sumResultAfterFilter + " cannot be less then sumResultBeforeFilter " + sumResultBeforeFilter;
        Assert.assertTrue(sumResultAfterFilter <= sumResultBeforeFilter, errMsg);
    }

    public void verifyInputFilter(String filterElement, String filterValue, String expectedVisibleElement) {
        Loader loader = new Loader(driver);
        int sumResultBeforeFilter = receiveTotalQuantityOfTickets();
        enterData(filterElement, filterValue);
        clickButtonEnter();
        asserts.assertElementPresent(expectedVisibleElement);
        loader.waitLoader();
        int sumResultAfterFilter = receiveTotalQuantityOfTickets();
        String errMsg = "sumResultTicketsAfterFilter: " + sumResultAfterFilter + " cannot be less then sumResultBeforeFilter " + sumResultBeforeFilter;
        Assert.assertTrue(sumResultAfterFilter <= sumResultBeforeFilter, errMsg);
    }
}