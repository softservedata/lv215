package com.softserve.edu.oms.pages;

import static com.softserve.edu.oms.locators.UserHomePageLocators.ENG_LANG_LINK_XPATH;
import static com.softserve.edu.oms.locators.UserHomePageLocators.FIRST_NAME_LABEL_CSS;
import static com.softserve.edu.oms.locators.UserHomePageLocators.LAST_NAME_LABEL_CSS;
import static com.softserve.edu.oms.locators.UserHomePageLocators.ROLE_LABEL_CSS;
import static com.softserve.edu.oms.locators.UserHomePageLocators.UKR_LANG_LINK_XPATH;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends ABasePage {
	
	public static final Logger logger = LoggerFactory.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
	}

    // PageObject

	// get Data

	public WebElement getFirstname() {
		logger.debug("Start");
		return driver.findElement(FIRST_NAME_LABEL_CSS.by);
	}
	
	public WebElement getLastname() {
		logger.debug("Start");
		return driver.findElement(LAST_NAME_LABEL_CSS.by);
	}
	
	public WebElement getRole() {
		return driver.findElement(ROLE_LABEL_CSS.by);
	}

	public WebElement getEngLangLink() {
		return driver.findElement(ENG_LANG_LINK_XPATH.by);
	}

	public WebElement getUkrLangLink() {
		return driver.findElement(UKR_LANG_LINK_XPATH.by);
	}


	

	// Functional
	
	public String getFirstnameText() {
		return getFirstname().getText().trim();
	}

	public String getLastnameText() {
		return getLastname().getText().trim();
	}

	public String getRoleText() {
		return getRole().getText().trim();
	}

	public String getEngLangLinkText() {
		return getEngLangLink().getText().trim();
	}

	public String getUkrLangLinkText() {
		return getUkrLangLink().getText().trim();
	}

	
	// set Data

	public void clickEngLangLink() {
		getEngLangLink().click();
	}

	public void clickUkrLangLink() {
		logger.debug("Start");
		getUkrLangLink().click();
		logger.debug("Done");
	}


	@Override
	public HomePage waitForLoad(){
		super.waitForLoad();
		return this;
	}


	// Business Logic

	@Override
	public HomePage gotoUserInfoTab(){
		logger.debug("Start");
		String role = getRoleText();
		clickUserInfoTab();
		logger.trace("before switch");
		switch(role){
			case "Administrator":
				return new AdminHomePage(driver);

			case "Customer":
				return new CustomerHomePage(driver);

			case "Merchandiser":
				return new MerchandiserHomePage(driver);

			case "Supervisor":
				return new SupervisorHomePage(driver);

			default:
				return new HomePage(driver);
		}
	}

}
