package com.softserve.edu.oms.locators;

import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link com.softserve.edu.oms.pages.ItemManagementPage}.
 */
public enum ItemManagementPageLocators {
    
     USER_INFO_TAB_CSS (By.cssSelector("#nav li:nth-child(2) a")),
     PAGE_IS_APPOINTED_LABEL_CSS(By.cssSelector("#list h2")),
     ADD_PRODUCT_LINK_CSS (By.cssSelector("#list>a")),
     PRODUCTS_FOUND_LABEL_CSS (By.cssSelector("#list h4:first-of-type")),
     PRODUCTS_COUNT_LABEL_ID (By.id("recordsFound")),
     SEARCH_BY_LABEL_TAG_NAME (By.tagName("legend")),
     FIELD_FILTER_LABEL_CSS (By.cssSelector("#searchForm label")),
     FIELD_FILTER_SELECT_ID (By.id("field")),
     SEARCH_FIELD_ID (By.id("searchField")),
     SEARCH_BUTTON_CSS (By.cssSelector("input[value='Search']")),
     SHOW_ITEMS_LINK_CSS (By.cssSelector("#list p a")),
     TABLE_NAME_LINK_CSS (By.cssSelector("th:nth-child(1) a")),
     TABLE_DESCRIPTION_LINK_CSS (By.cssSelector("th:nth-child(2) a")),
     TABLE_PRICE_LINK_CSS (By.cssSelector("th:nth-child(3) a")),
     TABLE_EDIT_LABEL_CSS (By.cssSelector("th:nth-last-of-type(2)")),
     TABLE_DELETE_LABEL_CSS (By.cssSelector("th:nth-last-of-type(1)")),
     COLUMN_NAME_CSS (By.cssSelector("td:nth-child(1)")),
     COLUMN_DESCRIPTION_CSS (By.cssSelector("td:nth-child(2)")),
     COLUMN_PRICE_CSS (By.cssSelector("td:nth-child(3)")),
     COLUMN_EDIT_CSS (By.cssSelector("td:nth-child(4)")),
     COLUMN_DELETE_CSS (By.cssSelector("td:nth-child(5)")),
     FIRST_BUTTON_ID (By.id("first")),
     BACKWARD__BUTTON_ID (By.id("previous")),
     FORWARD__BUTTON_ID (By.id("next")),
     LAST__BUTTON_ID (By.id("last")),
     PAGE_LABEL_CSS (By.cssSelector("#list h4:last-of-type")),
     PAGE_NUMBER_LABEL_ID (By.id("pageNumber")),
     PAGE_COUNT_LABEL_ID (By.id("pageCount")),
     CREATE_REPORT_LINK_CSS (By.cssSelector("#list h4:last-of-type + a"));
    
    
    public final By by;
    
    ItemManagementPageLocators(final By by){
        this.by = by;
    }

}
