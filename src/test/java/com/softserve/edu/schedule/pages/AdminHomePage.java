package com.softserve.edu.schedule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.schedule.data.IUser;

public class AdminHomePage {

	// Fields
	private WebDriver driver;
	//
	private WebElement userDetails;
	private WebElement logout;

	public AdminHomePage(WebDriver driver) {
		this.driver = driver;
		this.userDetails = driver.findElement(By.xpath("//a[contains(@href,'/userDetails')]"));
		this.logout = driver.findElement(By.cssSelector("input.btn.btn-link"));
	}

	// get Data

	public WebElement getUserDetails() {
		return this.userDetails;
	}

	public WebElement getLogout() {
		return this.logout;
	}

	// Functional

	public String getUserDetailsText() {
		return getUserDetails().getText().trim();
	}

	public String getLogoutText() {
		return getLogout().getText().trim();
	}

	// set Data

	public void clickUserDetails() {
		getUserDetails().click();
	}

	public void clickLogout() {
		getLogout().click();
	}

	// Business Logic

	public UserProfilePage unsuccessfulLogin() {
		clickUserDetails();
		return new UserProfilePage(driver);
	}

	public StartPage logout() {
		clickLogout();
		// Return a new page object representing the destination.
		return new StartPage(driver);
	}

}
