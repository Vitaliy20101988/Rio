package utils;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import params.Environment;

public class SelectLanguage extends BasePage {

    public SelectLanguage(WebDriver driver) {
        super(driver, 15);
    }

    public static ImmutableMap<String, String> languageElements = new ImmutableMap.Builder<String, String>()
            .put("UA element", "//a[@href=\"https://www.ria.com/uk/\"]")
            .put("RU element", "//*[@class=\"app-head-bar\"]//a[@href=\"https://www.ria.com/\"]")
            .build();

    public void selectLanguage() {
        if (Environment.getLanguageIndex() == 1) {
            click(languageElements.get("RU element"));
        }
    }
}