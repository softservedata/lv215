package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateReportPage extends AAdminReportPage {

    private static final String ADMINISTRATION_LINK_CSS = "#nav .cur a";
    private static final String SAVE_REPORT_LINK_CSS = "#list>a";

    public CreateReportPage(WebDriver driver) {
        super(driver);
    }

    // get Data

    public WebElement getAdministrationLink() {
        return driver.findElement(By.cssSelector(ADMINISTRATION_LINK_CSS));
    }

    public WebElement getSaveReportLink() {
        return driver.findElement(By.cssSelector(SAVE_REPORT_LINK_CSS));
    }

    // Functional

    public String getAdministrationLinkText(){
        return getAdministrationLink().getText();
    }

    public String getSaveReportLinkText(){
        return getSaveReportLink().getText();
    }

    // set Data

    public void clickAdministrationLink(){
        getAdministrationLink().click();
    }

    public void clickSaveReportLink(){
        getSaveReportLink().click();
    }

    // Business Logic

    AdministrationPage goToAdministrationPage(){
        clickAdministrationLink();
        return new AdministrationPage(driver);

    }

}

     
