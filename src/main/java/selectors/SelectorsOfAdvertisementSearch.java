package selectors;

import params.Environment;
import texts.Texts;

public enum SelectorsOfAdvertisementSearch {

    SEARCH_INPUT_FIELD("//*[@class=\"flexed-search-form\"]//input[@name=\"search_text\"]"),
    SEARCH_REQUEST_EXPANDED_SEARCH_INPUT_FIELD(
            "//*[@class=\"item-selected-name\" and contains(text(), \"" + Texts.getSearchRequest()[Environment.getLanguageIndex()] + "\")]"),
    WITH_PICTURE_EXPANDED_SEARCH_INPUT_FIELD(
            "//*[@class=\"item-selected-name\" and contains(text(), \"" + Texts.getWithPicture()[Environment.getLanguageIndex()] + "\")]"),
    WITH_VIDEO_EXPANDED_SEARCH_INPUT_FIELD(
            "//*[@class=\"item-selected-name\" and contains(text(), \"" + Texts.getWithVideo()[Environment.getLanguageIndex()] + "\")]"),
    FIRST_TICKET_NAME_IN_SEARCH_RESULT("//*[@id=\"js-search-ads\"]/div[@class=\"box-panel30 clearfix search-items\"]/div[@class=\"ticket-clean intop\"]//div[@class=\"tticket\"]/a"),
    FIRST_TICKET_PRICE_IN_SEARCH_RESULT("//*[@id=\"js-search-ads\"]/div[@class=\"box-panel30 clearfix search-items\"]/div[@class=\"ticket-clean intop\"]//div[@class=\"price-ticket\"]//strong"),
    CHECKBOX_WITH_PICTURE("//*[@for=\"with_photo\"]"),
    CHECKBOX_WITH_VIDEO("//*[@for=\"with_video\"]"),
    SUM_RESULT_OF_SEARCHING("//*[@class=\"bold d-block js-total-ads\"]"),
    CHECKBOX_WITH_DISCOUNT("//*[@for=\"with_discount\"]"),
    CHECKBOX_WORK_NOW("//*[@for=\"shopOpenFilterLeft\"]"),
    PRICE_FROM_INPUT_FIELD("//*[@name=\"price[price_from]\"]"),
    PRICE_TILL_INPUT_FIELD("//*[@name=\"price[price_till]\"]"),
    FILING_PERIOD_INPUT_FIELD("//*[@name=\"search_period\"]");

    private String xpathValue;

    SelectorsOfAdvertisementSearch(String value) {this.xpathValue = value;}

    public String getValue() {return xpathValue;}
}