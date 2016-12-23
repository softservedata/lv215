package com.softserve.edu.oms.locators;


import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link com.softserve.edu.oms.pages.ABasePage}.
 */
public enum ABasePageLocators {

    LOGOUT_BUTTON_CSS(By.cssSelector(".spec a")),
    OMS_LABEL_CSS(By.cssSelector("#logo h1")),
    SIMPLE_SLIM_GENIUS_LABEL_CSS(By.cssSelector("#logo h2")),
    INSPIRED_BY_GOOGLE_LINK_CSS(By.cssSelector("#footer a")),
    USER_INFO_TAB_CSS(By.cssSelector("*[href=\"/OMS/userInfo.htm\"]"));

    public final By by;
    ABasePageLocators(final By by){
        this.by = by;
    }
}
