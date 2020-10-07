package selectors;

public enum SelectorsOfHomePage {

    SEARCH_INPUT_FIELD("//*[@class=\"search-main\"]//input[@name=\"search_text\"]"),
    SEARCH_BUTTON("//*[@class=\"search-main\"]//button[@class=\"search-btn\"]");

    private String xpathValue;

    SelectorsOfHomePage(String value) {this.xpathValue = value;}

    public String getValue() {return xpathValue;}
}
