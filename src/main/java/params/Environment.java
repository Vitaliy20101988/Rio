package params;

public class Environment {

    private static final String domain = "https://www.ria.com/";
    private static final String browser = "chrome";

    //    private static final String browser = "ff";
//    private static final String browser = "safari";
//    private static final String browser = "opera";

    //    RU = 0, UA = 1;
    private static final int LANGUAGE_INDEX = 1;

    public static int getLanguageIndex() {
        return LANGUAGE_INDEX;
    }
    public static String getDomain() {
        return domain;
    }
    public static String getBrowser() { return browser; }
}
