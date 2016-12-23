package com.softserve.edu.oms.pages;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.enums.Region;
import com.softserve.edu.oms.enums.Role;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static com.softserve.edu.oms.locators.AUserDataPageLocators.*;

/**
 * Created by Oleh Lavrynenko on 15.12.2016.
 */
public abstract class AUserDataPage extends ABasePage {
    AUserDataPage(WebDriver driver) {
        super(driver);
    }

        // get Data
    public WebElement getLoginInput() {
        return this.driver.findElement(LOGIN_INPUT_ID.by);
    }

    public WebElement getFirstNameInput() {
        return this.driver.findElement(FIRST_NAME_INPUT_ID.by);
    }

    public WebElement getLastNameInput() {
        return this.driver.findElement(LAST_NAME_INPUT_ID.by);
    }

    public WebElement getPasswordInput() {
        return this.driver.findElement(PASSWORD_INPUT_ID.by);
    }

    public WebElement getConfirmPasswordInput() {
        return this.driver.findElement(CONFIRM_PASSWORD_INPUT_ID.by);
    }

    public WebElement getEmailInput() {
        return this.driver.findElement(EMAIL_INPUT_ID.by);
    }

    public WebElement getRegionDropDown() {
        return this.driver.findElement(REGION_SELECT_ID.by);
    }

    public List<WebElement> getRoleRadioButton() {
        return this.driver.findElements(ROLE_RADIO_BUTTON_NAME.by);
    }

    public WebElement getCreateButton() {
        return this.driver.findElement(CREATE_BUTTON_CSS.by);
    }

    public WebElement getCancelButton() {
        return this.driver.findElement(CANCEL_BUTTON_CSS.by);
    }

    public WebElement getLoginErrorMessage() {
        return this.driver.findElement(ERROR_LOGIN_ID.by);
    }

    public WebElement getFirstNameErrorMessage() {
        return this.driver.findElement(ERROR_FIRST_NAME_ID.by);
    }

    public WebElement getLastNameErrorMessage() {
        return this.driver.findElement(ERROR_LAST_NAME_ID.by);
    }

    public WebElement getPasswordErrorMessage() {
        return this.driver.findElement(ERROR_PASSWORD_ID.by);
    }

    @Step("getConfirmPasswordErrorMessage")
    public WebElement getConfirmPasswordErrorMessage() {
        return this.driver.findElement(ERROR_CONFIRM_PASSWORD_ID.by);
    }

    public WebElement getEmailErrorMessage() {
        return this.driver.findElement(ERROR_EMAIL_ID.by);
    }

    // functional

    public String getLoginInputText() {
        return getLoginInput().getText();
    }

    public String getFirstNameInputText() {
        return getFirstNameInput().getText();
    }

    public String getLastNameInputText() {
        return getLastNameInput().getText();
    }

    public String getPasswordInputText() {
        return getPasswordInput().getText();
    }

    public String getConfirmPasswordInputText() {
        return getConfirmPasswordInput().getText();
    }

    public String getEmailInputText() {
        return getEmailInput().getText();
    }

    public Select getSelectRegion() {
        return new Select(getRegionDropDown());
    }

    public String getCreateButtonText() {
        return getCreateButton().getText().trim();
    }

    public String getCancelButtonText() {
        return getCancelButton().getText().trim();
    }

    public String getLoginErrorMessageText() {
        return getLoginErrorMessage().getText();
    }

    public Boolean getLoginError() {
        return getLoginErrorMessage().isDisplayed();
    }

    public String getFirstNameErrorMessageText() {
        return getFirstNameErrorMessage().getText();
    }

    public String getLastNameErrorMessageText() {
        return getLastNameErrorMessage().getText();
    }

    public String getPasswordErrorMessageText() {
        return getPasswordErrorMessage().getText();
    }

    @Step("getConfirmPasswordErrorMessageText")
    public String getConfirmPasswordErrorMessageText() {
        return getConfirmPasswordErrorMessage().getText();
    }

    public String getEmailErrorMessageText() {
        return getEmailErrorMessage().getText();
    }

    //set data
    public AUserDataPage setFirstNameInput(String firstName) {
        getFirstNameInput().clear();
        getFirstNameInput().sendKeys(firstName);
        return this;
    }

    public AUserDataPage setLastNameInput(String lastName) {
        getLastNameInput().clear();
        getLastNameInput().sendKeys(lastName);
        return this;
    }

    public AUserDataPage setPasswordInput(String password) {
        getPasswordInput().clear();
        getPasswordInput().sendKeys(password);
        return this;
    }

    public AUserDataPage setConfirmPasswordInput(String confirmPassword) {
        getConfirmPasswordInput().clear();
        getConfirmPasswordInput().sendKeys(confirmPassword);
        return this;
    }

    public AUserDataPage setEmailInput(String email) {
        getEmailInput().clear();
        getEmailInput().sendKeys(email);
        return this;
    }

    public AUserDataPage setSelectRegion(Region region) {
        getSelectRegion().selectByVisibleText(region.getRegionType());
        return this;
    }

    public AUserDataPage setSelectRole(Role roleId) {
        driver.findElement(By.id(roleId.getRoleId())).click();
        return this;
    }


    public AUserDataPage clearFirstNameInput() {
        getFirstNameInput().clear();
        return this;
    }

    public AUserDataPage clearLastNameInput() {
        getLastNameInput().clear();
        return this;
    }

    public AUserDataPage clearPasswordInput() {
        getPasswordInput().clear();
        return this;
    }

    public AUserDataPage clearConfirmPasswordInput() {
        getConfirmPasswordInput().clear();
        return this;
    }

    public AUserDataPage clearEmailInput() {
        getEmailInput().clear();
        return this;
    }

    public AUserDataPage clickCreateButton() {
        getCreateButton().click();
        return this;
    }

    public AUserDataPage clickCancelButton() {
        getCancelButton().click();
        return this;
    }

    public AUserDataPage setLoginData(IUser user) {
        setFirstNameInput(user.getFirstname());
        setLastNameInput(user.getLastname());
        setPasswordInput(user.getPassword());
        setConfirmPasswordInput(user.getPassword());
        setEmailInput(user.getEmail());
        setSelectRegion(Region.getRegion(user.getRegion()));
        setSelectRole(Role.valueOf(user.getRole().toUpperCase()));
        return this;
    }

    // ???
    public AdministrationPage successCreateNewUser() {
        clickCreateButton();
        return new AdministrationPage(driver);
    }
    
    public AUserDataPage acceptAlert() {
        try {
            driver
                    .switchTo()
                    .alert()
                    .accept();
        } catch (NoAlertPresentException e) {

        }
        return this;
    }

    @Override
    public AUserDataPage waitForLoad() {
        super.waitForLoad();
        return this;
    }
}
