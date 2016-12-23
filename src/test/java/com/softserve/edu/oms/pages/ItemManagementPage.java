package com.softserve.edu.oms.pages;

import com.softserve.edu.oms.enums.FieldFilterSupervisor;

import com.softserve.edu.oms.locators.ItemManagementPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

/**
 * PageObject class that represents ItemManagement page.  
 * 
 * @version  1.0
 * @since 15.12.16
 * @author  Roman Raba 
 * 
 */
public class ItemManagementPage extends ABasePage {

    
    /** Creates new object
    */
    public ItemManagementPage(WebDriver driver) {
        super(driver);
    }

    private boolean showFiveItems = true; // true - showing 5 items, false -
                                          // showing 10 items in table

    // get Elements

    public WebElement getUserInfoTab() {
        return driver.findElement(ItemManagementPageLocators.USER_INFO_TAB_CSS.by);
    }

    public WebElement getPageIsAppointedLabel() {
        return driver.findElement(ItemManagementPageLocators.PAGE_IS_APPOINTED_LABEL_CSS.by);
    }

    public WebElement getAddProductLink() {
        return driver.findElement(ItemManagementPageLocators.ADD_PRODUCT_LINK_CSS.by);
    }

    public WebElement getProductsFoundLabel() {
        return driver.findElement(ItemManagementPageLocators.PRODUCTS_FOUND_LABEL_CSS.by);
    }

    public WebElement getProductsCountLabel() {
        return driver.findElement(ItemManagementPageLocators.PRODUCTS_COUNT_LABEL_ID.by);
    }

    public WebElement getSearchByLabel() {
        return driver.findElement(ItemManagementPageLocators.SEARCH_BY_LABEL_TAG_NAME.by);
    }

    public WebElement getFieldFilterLabel() {
        return driver.findElement(ItemManagementPageLocators.FIELD_FILTER_LABEL_CSS.by);
    }

    public WebElement getFieldFilterSelect() {
        return driver.findElement(ItemManagementPageLocators.FIELD_FILTER_SELECT_ID.by);
    }

    public WebElement getSearchField() {
        return driver.findElement(ItemManagementPageLocators.SEARCH_FIELD_ID.by);
    }

    public WebElement getSearchButton() {
        return driver.findElement(ItemManagementPageLocators.SEARCH_BUTTON_CSS.by);
    }

    public WebElement getShowItemsLink() {
        return driver.findElement(ItemManagementPageLocators.SHOW_ITEMS_LINK_CSS.by);
    }

    public WebElement getTableNameLink() {
        return driver.findElement(ItemManagementPageLocators.TABLE_NAME_LINK_CSS.by);
    }

    public WebElement getTableDescriptionLink() {
        return driver.findElement(ItemManagementPageLocators.TABLE_DESCRIPTION_LINK_CSS.by);
    }

    public WebElement getTablePriceLink() {
        return driver.findElement(ItemManagementPageLocators.TABLE_PRICE_LINK_CSS.by);
    }

    public WebElement getTableEditLabel() {
        return driver.findElement(ItemManagementPageLocators.TABLE_EDIT_LABEL_CSS.by);
    }

    public WebElement getTableDeleteLabel() {
        return driver.findElement(ItemManagementPageLocators.TABLE_DELETE_LABEL_CSS.by);
    }

    public WebElement getFirstButton() {
        return driver.findElement(ItemManagementPageLocators.FIRST_BUTTON_ID.by);
    }

    public WebElement getBackwardButton() {
        return driver.findElement(ItemManagementPageLocators.BACKWARD__BUTTON_ID.by);
    }

    public WebElement getForwardButton() {
        return driver.findElement(ItemManagementPageLocators.FORWARD__BUTTON_ID.by);
    }

    public WebElement getLastButton() {
        return driver.findElement(ItemManagementPageLocators.LAST__BUTTON_ID.by);
    }

    public WebElement getPageLabel() {
        return driver.findElement(ItemManagementPageLocators.PAGE_LABEL_CSS.by);
    }

    public WebElement getPageNumberLabel() {
        return driver.findElement(ItemManagementPageLocators.PAGE_NUMBER_LABEL_ID.by);
    }

    public WebElement getPageCountLabel() {
        return driver.findElement(ItemManagementPageLocators.PAGE_COUNT_LABEL_ID.by);
    }

    public WebElement getCreateReportLink() {
        return driver.findElement(ItemManagementPageLocators.CREATE_REPORT_LINK_CSS.by);
    }

    public Select getChangeFieldFilter() {
        return new Select(getFieldFilterSelect());
    }

    public String getChangeFieldFilterSelectedText() {
        return getChangeFieldFilter().getFirstSelectedOption().getText();
    }
 
    // set Data

    public void setChangeFieldFilter(FieldFilterSupervisor fieldFilter) {
        getChangeFieldFilter().selectByVisibleText(fieldFilter.toString());
    }

    public void clickFirstButton() {
        getFirstButton().click();
    }

    public void clickBackwardButton() {
        getBackwardButton().click();
    }

    public void clickForwardButton() {
        getForwardButton().click();
    }

    public void clickLastButton() {
        getLastButton().click();
    }

    // business logic
    
    /** Method, which creates list of webelements from table by column "Name"
     * on ItemManagement page. 
     */
    public List<WebElement> getListName() {
        List<WebElement> listName = driver.findElements(ItemManagementPageLocators.COLUMN_NAME_CSS.by);
        return listName;
    }
    
    
    /** Method, which creates list of webelements from table by column "Description"
     * on ItemManagement page. 
     */
    public List<WebElement> getListDescription() {
        List<WebElement> listDescription = driver.findElements(ItemManagementPageLocators.COLUMN_DESCRIPTION_CSS.by);
        return listDescription;
    }

    
    /** Method, which creates list of webelements from table by column "Price"
     * on ItemManagement page. 
     */
    public List<WebElement> getListPrice() {
        List<WebElement> listPrice = driver.findElements(ItemManagementPageLocators.COLUMN_PRICE_CSS.by);
        return listPrice;
    }

    
    /** Method, which creates list of webelements from table by column "Edit"
     * on ItemManagement page. 
     */
    public List<WebElement> getListEditLinks() {
        List<WebElement> getListEditLinks = driver.findElements(ItemManagementPageLocators.COLUMN_EDIT_CSS.by);
        return getListEditLinks;
    }

    
    /** Method, which creates list of webelements from table by column "Delete"
     * on ItemManagement page. 
     */
    public List<WebElement> getListDeleteLinks() {
        List<WebElement> getListDeleteLinks = driver.findElements(ItemManagementPageLocators.COLUMN_DELETE_CSS.by);
        return getListDeleteLinks;
    }

    
    /** Method, which clicks on "Show link" and changes number of rows in table
     * which are shown on ItemManagement page.
     */
    public ItemManagementPage clickShowItemsLink() {
        getShowItemsLink().click();
        showFiveItems = !showFiveItems;
        return this;
    }

    
    
    /** Method, which finds by "Product Name".
     * @param name - name for search.
     */
    public ItemManagementPage searchByName(String name) {
        setChangeFieldFilter(FieldFilterSupervisor.NAME);
        getFieldFilterSelect().click();
        getFieldFilterSelect().clear();
        getFieldFilterSelect().sendKeys(name);
        getSearchButton().click();
        return this;
    }

    
    /** Method, which finds by "Description".
     * @param description - description for search.
     */
    public ItemManagementPage searchByDescription(String description) {
        setChangeFieldFilter(FieldFilterSupervisor.DESCRIPTION);
        getFieldFilterSelect().click();
        getFieldFilterSelect().clear();
        getFieldFilterSelect().sendKeys(description);
        getSearchButton().click();
        return this;
    }

    
    /** Method, which deletes product by index.
     * @param index - index for deleting.
     */
    public ItemManagementPage deleteProduct(int index) {
        getListDeleteLinks().get(index).click();
        return this;
    }

    
    /** Method, which goes to AddProduct page.
     */
    public AddProductPage gotoAddProductPage() {
        getAddProductLink().click();
        return new AddProductPage(driver);
    }

    
    /** Method, which goes to CreateReport page.
     */
    public CreateReportPage gotoCreateReportPage() {
        getCreateReportLink().click();
        return new CreateReportPage(driver);
    }

    /** Method, which goes to EditProduct page.
     * @param index - index of product for redirecting to EditProduct page.
     */
    public EditProductPage gotoEditProductPage(int index) {
        getListEditLinks().get(index).click();
        return new EditProductPage(driver);
    }

}
