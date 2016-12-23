package com.softserve.edu.oms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.softserve.edu.oms.locators.AddProductPageLocators.*;

/**
 * PageObject class that represents Add Product page.
 *
 * @version 1.0
 * @since 15.12.16
 * @author Iryna Kyselchuk
 *
 */
public class AddProductPage extends ABasePage {

    /** Class constructor */
    public AddProductPage(WebDriver driver) {
        super(driver);
    }

    // get Elements

    public WebElement getCreatingNewProductLabel() {
        return driver.findElement(CREATING_PRODUCT_LABEL_CSS.by);
    }

    public WebElement getProductNameInput() {
        return driver.findElement(PRODUCT_NAME_INPUT_ID.by);
    }

    public WebElement getProductDescriptionTextarea() {
        return driver.findElement(PRODUCT_DESCRIPTION_TEXTAREA_ID.by);
    }

    public WebElement getProductPriceInput() {
        return driver.findElement(PRODUCT_PRICE_INPUT_ID.by);
    }

    public WebElement getOkButton() {
        return driver.findElement(OK_BUTTON_CSS.by);
    }

    public WebElement getCancelButton() {
        return driver.findElement(CANCEL_BUTTON_CSS.by);
    }

    public WebElement getProductNameErrorMessage() {
        return driver.findElement(PRODUCT_NAME_ERROR_MESSAGE_ID.by);
    }

    public WebElement getProductPriceErrorMessage() {
        return driver.findElement(PRODUCT_PRICE_ERROR_MESSAGE_ID.by);
    }

    // functional getters

    public String getCreatingNewProductLabelText() {
        return getCreatingNewProductLabel().getText();
    }

    public String getProductNameInputText() {
        return getProductNameInput().getText();
    }

    public String getProductDescriptionTextareaText() {
        return getProductDescriptionTextarea().getText();
    }

    public String getProductPriceInputText() {
        return getProductPriceInput().getText();
    }

    public String getOkButtonText() {
        return getOkButton().getText().trim();
    }

    public String getCancelButtonText() {
        return getCancelButton().getText().trim();
    }

    public String getProductNameErrorMessageText() {
        return getProductNameErrorMessage().getText();
    }

    public String getProductPriceErrorMessageText() {
        return getProductPriceErrorMessage().getText();
    }

    public double getProductPriceInputValue() {
        return Double.parseDouble(getProductPriceInputText());
    }

    // set Data

    public AddProductPage setProductName(String productNameValue) {
        getProductNameInput().clear();
        getProductNameInput().sendKeys(productNameValue);
        return this;
    }

    public AddProductPage setProductDescription(String productDescriptionValue) {
        getProductDescriptionTextarea().clear();
        getProductDescriptionTextarea().sendKeys(productDescriptionValue);
        return this;
    }

    public AddProductPage setProductPrice(double productPriceValue) {
        getProductPriceInput().clear();
        getProductPriceInput().sendKeys(String.valueOf(productPriceValue));
        return this;
    }

    public AddProductPage clearProductNameInput() {
        getProductNameInput().clear();
        return this;
    }

    public AddProductPage clearProductDescriptionTextarea() {
        getProductDescriptionTextarea().clear();
        return this;
    }

    public AddProductPage clearProductPriceInput() {
        getProductPriceInput().clear();
        return this;
    }

    public AddProductPage clickOkButton() {
        getOkButton().click();
        return this;
    }

    public AddProductPage clickCancelButton() {
        getCancelButton().click();
        return this;
    }

    // business logic

    /** Method for creating new product with valid parameters
     * @param productName - name of creating product
     * @param description - description of creating product
     * @param price - price of creating product
     * @return new object of ItemManagementPage
     * @see ItemManagementPage
     */
    public ItemManagementPage successCreateNewProduct(String productName, String description, double price) {
        setProductName(productName);
        setProductDescription(description);
        setProductPrice(price);
        clickOkButton();
        return new ItemManagementPage(driver);
    }

    /** Method for attempt create new product with invalid parameters
     * @param productName - name of creating product
     * @param description - description of creating product
     * @param price - price of creating product
     * @return the same page with filled out fields and corresponding error messages
     */
    public AddProductPage unsuccessCreateNewProduct(String productName, String description, double price) {
        setProductName(productName);
        setProductDescription(description);
        setProductPrice(price);
        clickOkButton();
        return this;
    }

    /** Method for attempt create new product, but cancel it
     * @param productName - name of creating product
     * @param description - description of creating product
     * @param price - price of creating product
     * @return new object of ItemManagementPage
     * @see ItemManagementPage
     */
    public ItemManagementPage createProductAndCancel(String productName, String description, double price) {
        setProductName(productName);
        setProductDescription(description);
        setProductPrice(price);
        clickCancelButton();
        return new ItemManagementPage(driver);
    }
}
