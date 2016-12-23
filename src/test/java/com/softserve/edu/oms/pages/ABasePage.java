package com.softserve.edu.oms.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import static com.softserve.edu.oms.locators.ABasePageLocators.*;

public abstract class ABasePage {

    protected WebDriver driver;

    ABasePage(WebDriver driver) {
        this.driver = driver;
    }

    // get Data

    public WebElement getLogoutButton() {
        return driver.findElement(LOGOUT_BUTTON_CSS.by);
    }

    public WebElement getOmsLabel() {
        return driver.findElement(OMS_LABEL_CSS.by);
    }

    public WebElement getSimpleSlimGeniusLabel() {
        return driver.findElement(SIMPLE_SLIM_GENIUS_LABEL_CSS.by);
    }

    public WebElement getInspiredByGoogleLink() {
        return driver.findElement(INSPIRED_BY_GOOGLE_LINK_CSS.by);
    }

    public WebElement getUserInfoTab() {
        return driver.findElement(USER_INFO_TAB_CSS.by);
    }

    // Functional

    public String getOmsLabelText() {
        return getOmsLabel().getText().trim();
    }

    public String getSimpleSlimGeniusLabelText() {
        return getSimpleSlimGeniusLabel().getText().trim();
    }

    public String getInspiredByGoogleLinkText() {
        return getInspiredByGoogleLink().getText().trim();
    }

    public String getUserInfoTabText() {
        return getUserInfoTab().getText().trim();
    }


    // set Data

    public void clickLogoutButton() {
        getLogoutButton().click();
    }

    public void clickInspiredByGoogleLink() {
        getInspiredByGoogleLink().click();
    }

    @Step("Just a click on User Info Tab")
    public void clickUserInfoTab() {
        getUserInfoTab().click();
    }

    // Business Logic

    public HomePage gotoUserInfoTab() {
        clickUserInfoTab();
        return new HomePage(driver);
    }

    public LoginPage logout() {
        clickLogoutButton();
        return new LoginPage(driver);
    }

    public ABasePage waitForLoad() {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10)
                .ignoring(StaleElementReferenceException.class);
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            WebElement element = webDriver.findElement(INSPIRED_BY_GOOGLE_LINK_CSS.by);
            return element != null && element.isDisplayed();
        });
        return this;
    }
}