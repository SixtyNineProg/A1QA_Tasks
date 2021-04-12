package by.a1qa.klimov.utils;

public class Constants {
    public static final String PATH_DATA_PROPERTIES = "./src/test/resources/testData.properties";
    public static final String PATH_CONFIGURATION_PROPERTIES = "./src/test/resources/config.properties";

    public static final String PROPERTY_FILE_UPLOAD_ERROR = "Property file upload error. File not found on path: ";
    public static final String ELEMENT_CREATED = "Element created with name: ";
    public static final String ELEMENT_DISPLAYED = "Element displayed with name: ";
    public static final String ELEMENT_CLICKED = "Element clicked with name: ";
    public static final String ELEMENT_GET_ATTRIBUTE = "Get an attribute from an element with name: ";
    public static final String GET_PAGE_WITH_URL = "Get page with URL: ";
    public static final String WAIT_PRESENCE_OF_ELEMENT = "Wait presence of element with name: ";
    public static final String FIND_ELEMENT = "Find element with name: ";

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
    public static final String XPATH_CONTAINER_SEARCH_PRICES = ".//div[@data-price-final]";
    public static final String XPATH_CONTAINER_TAG_SEARCH = ".//a";

    public static final String ATTRIBUTE_FOR_PRICE = "data-price-final";
    public static final String ATTRIBUTE_FOR_WAIT_SEARCH_RESULT = "style";
    public static final String EXPECTED_ATTRIBUTE_VALUE = "";

    public static final String ASSERT_AT_HOME_PAGE = "Home page is't open.";
    public static final String ASSERT_AT_SEARCH_PAGE = "Search page is't open.";
    public static final String ASSERT_SEARCH_RESULTS_PRESENT = "Search results are't present.";
    public static final String ASSERT_SEARCH_RESULT_SORTED = "Search results are't sorted in ascending order";
}
