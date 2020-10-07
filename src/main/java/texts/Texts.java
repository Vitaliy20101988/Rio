package texts;

public class Texts {

    private static final String[] SEARCH_REQUEST = {
            "Пошуковий запит: \"",
            "Поисковый запрос: \""
    };

    private static final String[] WITH_PICTURE = {
            " Тільки з фото ",
            " Только с фото "
    };

    private static final String[] WITH_VIDEO = {
            " Тільки з відео ",
            " Только с видео "
    };

    private static final String[] WITH_DISCOUNT = {
            " Тільки зі знижками ",
            " Только  со скидками "
    };

    private static final String[] FROM = {
            "От",
            "Від"
    };

    private static final String[] TILL = {
            "До",
            "До"
    };

    public static String[] getSearchRequest() {
        return SEARCH_REQUEST;
    }

    public static String[] getWithPicture() {
        return WITH_PICTURE;
    }

    public static String[] getWithVideo() {
        return WITH_VIDEO;
    }

    public static String[] getWithDiscount() {
        return WITH_DISCOUNT;
    }

    public static String[] getFROM() {
        return FROM;
    }

    public static String[] getTILL() {
        return TILL;
    }
}