package com.softserve.edu.oms.locators;

import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link com.softserve.edu.oms.pages.AAdminReportPage}.
 */
public enum AAdminReportPageLocators {

    SUBHEADER_CSS(By.cssSelector("#list h2")),
    USERS_FOUND_SPAN_ID(By.id("usersFound")),
    SEARCH_BY_LABEL_TAG_NAME(By.tagName("legend")),
    FIELD_FILTER_LABEL_CSS(By.cssSelector("#searchForm label")),
    SELECT_FIELD_ID(By.id("field")),
    SELECT_CONDITION_ID(By.id("condition")),
    SEARCH_FIELD_ID(By.id("searchField")),
    SEARCH_BUTTON_NAME(By.name("search")),
    SHOW_ITEMS_LINK_CSS(By.cssSelector("#list p a")),
    FIRST_NAME_LINK_CSS(By.cssSelector("th:nth-child(1) a")),
    LAST_NAME_LINK_CSS(By.cssSelector("th:nth-child(2) a")),
    LOGIN_LINK_CSS(By.cssSelector("th:nth-child(3) a")),
    ROLE_LINK_CSS(By.cssSelector("th:nth-child(4) a")),
    REGION_LINK_CSS(By.cssSelector("th:nth-child(5) a")),
    FIRST_BUTTON_ID(By.id("first")),
    BACKWARD_BUTTON_ID(By.id("previous")),
    FORWARD_BUTTON_ID(By.id("next")),
    LAST_BUTTON_ID(By.id("last")),
    PAGE_NUMBER_SPAN_ID(By.id("pageNumber")),
    PAGE_COUNT_SPAN_ID(By.id("pageCount")),
    LOGINS_XPATH(By.xpath(".//td[3]")),
    FIRST_NAMES_XPATH(By.xpath(".//td[1]")),
    LAST_NAMES_XPATH(By.xpath(".//td[2]")),
    TABLE_BODY_TAGNAME(By.tagName("tbody")),
    TR_TAGNAME(By.tagName("tr")),
    TD_TAGNAME(By.tagName("td")),
    GET_USER_BY_LOGIN_XPATH(By.xpath("#table tr:first-child td:first-child"));

    public final By by;
    AAdminReportPageLocators(final By by){
        this.by = by;
    }
}
