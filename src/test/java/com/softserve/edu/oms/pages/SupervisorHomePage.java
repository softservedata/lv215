package com.softserve.edu.oms.pages;


import com.softserve.edu.oms.locators.UserHomePageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SupervisorHomePage extends HomePage{

    public SupervisorHomePage (WebDriver driver){
        super(driver);
    }

    // get Data

    public WebElement getItemManagementTab() {
        return driver.findElement(UserHomePageLocators.ITEM_MANAGEMENT_TAB_XPATH.by);
    }


    // set Data

    public void clickItemManagementTab() {
        getItemManagementTab().click();
    }


    // Business Logic

    public ItemManagementPage gotoItemManagementPage() {
        clickItemManagementTab();
        return new ItemManagementPage(driver);
    }


}
