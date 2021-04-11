package by.a1qa.klimov.utils;

public class Constants {
    public static final String PATH_DATA_PROPERTIES = "./src/test/resources/testData.properties";
    public static final String PATH_CONFIGURATION_PROPERTIES = "./src/test/resources/config.properties";

    public static final String PROPERTY_FILE_UPLOAD_ERROR = "Property file upload error. File not found on path: ";
    public static final String ELEMENT_CREATED = "Element created with name: : ";


    public static final String XPATH_HOME_CLUSTER =
            "//div[contains(@class, 'home_page_body')]//div[contains(@class, 'home_cluster')]";
    public static final String XPATH_SEARCH_FIELD = "//div[@class='searchbox']//input";
    public static final String XPATH_BUTTON_SEARCH_FORM = "//*[@id='store_search_link']//img";
    public static final String XPATH_STORE_NAVIGATION_BAR = "//div[@class='store_nav']";
    public static final String XPATH_SEARCH_RESULT_CONTAINER =
            "//div[@id='search_result_container']//div[@id='search_resultsRows']";
    public static final String XPATH_WAIT_RESULT_SORT_CONTAINER = "//div[@id='search_result_container']";
    public static final String XPATH_SEARCH_BUTTON_SORT = "//*[@id='sort_by_trigger']";
    public static final String XPATH_SEARCH_BUTTON_SORT_PRICE_ASC = "//*[@id='Price_ASC']";
    public static final String XPATH_SEARCH_PRICE = "//div[contains(@class,'search_price_discount_combined')]";

    public static final String ATTRIBUTE_FOR_PRICE = "data-price-final";
    public static final String ATTRIBUTE_FOR_WAIT_SEARCH_RESULT = "style";
    public static final String EXPECTED_ATTRIBUTE_VALUE = "";
    public static final String XPATH_CONTAINER_TAG_SEARCH = ".//a";

}
