package com.softserve.edu.oms.locators;

import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link com.softserve.edu.oms.pages.AUserDataPage}.
 */
public enum AUserDataPageLocators {

    LOGIN_INPUT_ID(By.id("login")),
    FIRST_NAME_INPUT_ID(By.id("firstName")),
    LAST_NAME_INPUT_ID(By.id("lastName")),
    PASSWORD_INPUT_ID(By.id("password")),
    CONFIRM_PASSWORD_INPUT_ID(By.id("confirmPassword")),
    EMAIL_INPUT_ID(By.id("email")),
    REGION_SELECT_ID(By.id("regionID")),
    ROLE_RADIO_BUTTON_NAME(By.name("roleID")),
    CREATE_BUTTON_CSS(By.cssSelector("input[value=\"Create\"]")),
    CANCEL_BUTTON_CSS(By.cssSelector("input[value=\"Cancel\"]")),
    ERROR_LOGIN_ID(By.id("nameError")),
    ERROR_FIRST_NAME_ID(By.id("firstNameError")),
    ERROR_LAST_NAME_ID(By.id("lastNameError")),
    ERROR_PASSWORD_ID(By.id("passwordError")),
    ERROR_CONFIRM_PASSWORD_ID(By.id("confirmError")),
    ERROR_EMAIL_ID(By.id("emailError"));

    public final By by;
    AUserDataPageLocators(final By by){
        this.by = by;
    }
}
