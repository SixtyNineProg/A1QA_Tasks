package by.a1qa.klimov.utils;

public class Constants {
    public static final String PROPERTY_FILE_UPLOAD_ERROR = "Property file upload error";

    public static final String STEAM_SHOP_URL = "https://store.steampowered.com/";

    public static final String XPATH_LOGO_HOLDER_WITH_URL =
            "//span[@id='logo_holder']//*[contains(@href,'https://store.steampowered.com/')]";
    public static final String XPATH_HOME_PAGE_BODY = "//div[contains(@class, 'home_page_body_ctn')]";
    public static final String XPATH_HOME_CLUSTER = "//div[contains(@class, 'home_cluster_ctn')]";
    public static final String XPATH_SEARCH_RESULT_ELEMENT = "//div[@id='search_results']";
    public static final String XPATH_HOME_SEARCH_FORM = "//div[@class='searchbox']//input";
    public static final String XPATH_HOME_BUTTON_SEARCH_FORM = "//*[@id='store_search_link']//img";
    public static final String XPATH_SEARCH_RESULT_ELEMENTS = "//div[@id='search_resultsRows']/a";
    public static final String XPATH_SEARCH_BUTTON_SORT = "//*[@id='sort_by_trigger']";
    public static final String XPATH_SEARCH_BUTTON_SORT_PRICE_ASC = "//*[@id='Price_ASC']";
    public static final String XPATH_SEARCH_PRICE = "//div[contains(@class,'search_price_discount_combined')]";

    public static final String SEARCH_REQUEST = "The witcher";
    public static final Integer LIMIT_CHECKED_GAMES = 10;

    public static final String ATTRIBUTE_FOR_PRICE = "data-price-final";

}
