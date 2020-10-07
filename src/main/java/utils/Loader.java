package utils;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class Loader extends BasePage {

    private static final String LOADER = "//*[@class=\"fullwidth open dhide\"]";

    public Loader(WebDriver driver) {
        super(driver, 15);
    }

    public Loader waitLoader() {
        Waits waits = new Waits(driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waits.waitInvisibility(LOADER);
        return this;
    }
}
