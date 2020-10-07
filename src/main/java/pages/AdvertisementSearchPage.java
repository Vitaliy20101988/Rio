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
        Assert.assertTrue(!ticketPrice.equals("null") && !ticketName.trim().isEmpty());
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

    public void verifyFilter(String filterElement, String expectedVisibleElement) {
        Loader loader = new Loader(driver);
        int sumResultBeforeFilter = receiveTotalQuantityOfTickets();
        click(filterElement);
        asserts.assertElementPresent(expectedVisibleElement);
        loader.waitLoader();
        int sumResultAfterFilter = receiveTotalQuantityOfTickets();
        String errMsg = "sumResultTicketsAfterFilter: " + sumResultAfterFilter + " cannot be less then sumResultBeforeFilter " + sumResultBeforeFilter;
        Assert.assertTrue(sumResultAfterFilter <= sumResultBeforeFilter, errMsg);
    }

    public void verifyFilter(String filterElement) {
        Loader loader = new Loader(driver);
        int sumResultBeforeFilter = receiveTotalQuantityOfTickets();
        click(filterElement);
        loader.waitLoader();
        int sumResultAfterFilter = receiveTotalQuantityOfTickets();
        String errMsg = "sumResultTicketsAfterFilter: " + sumResultAfterFilter + " cannot be less then sumResultBeforeFilter " + sumResultBeforeFilter;
        Assert.assertTrue(sumResultAfterFilter <= sumResultBeforeFilter, errMsg);
    }

}